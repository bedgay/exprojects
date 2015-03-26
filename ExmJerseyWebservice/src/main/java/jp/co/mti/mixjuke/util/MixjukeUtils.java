/**
 * 
 */
package jp.co.mti.mixjuke.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import jp.co.mti.mixjuke.dom.Performance;
import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.StreamAuthentication;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.ws.request.MemberStatus;
import jp.co.mti.mixjuke.ws.response.SongInfo;

/**
 * @author ntnxuan
 * 
 */
public class MixjukeUtils {
    private static Charset SIG_ENCODING = Charset.forName("UTF-8");
    private static String SIG_ALGORITHM = "HmacSHA256";

    public static String getSignature(String srcString, String secretKey)
            throws InvalidKeyException, NoSuchAlgorithmException {
        Key key = new SecretKeySpec(secretKey.getBytes(SIG_ENCODING),
                SIG_ALGORITHM);
        Mac mac = Mac.getInstance(key.getAlgorithm());
        mac.init(key);
        byte[] srcBytes = srcString.getBytes(SIG_ENCODING);
        byte[] bs = mac.doFinal(srcBytes);
        return new String(Base64.encodeBase64(bs), SIG_ENCODING);
    }

    public static String getTimeStampString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        return sdf.format(new Date());
    }

    public static String getURLEncoderString(String srcString)
            throws UnsupportedEncodingException {
        return URLEncoder.encode(srcString, "UTF-8");
    }
    
    public static String getBase64URLEncoderString(String srcString)
            throws UnsupportedEncodingException {

        byte[] srcBytes = srcString.getBytes();
        String base64Str = new String(Base64.encodeBase64(srcBytes));
        return getURLEncoderString(base64Str);
    }

	/**
	 * Songs from the same artist should not in continuous order in the song
	 * list.
	 * 
	 * @param songs
	 */
	public static void mixSongs(List<SongInfo> songs) {
		int size = songs.size();
		for (int i = 1; i < size - 1;) {
			String before = songs.get(i - 1).getArtistId();
			String current = songs.get(i).getArtistId();
			String after = songs.get(i + 1).getArtistId();
			// in case 3 items in continuous in order -> just move center item.
			if (current.equals(before) && current.equals(after)) {
				int indxSwap = 0;
				// in case 3 item at begin of list -> move to end of list
				// in case 3 item NOT at begin of list -> move to begin of list.
				if (i == 1) {
					indxSwap = size - 1;
				} else if (i != 1) {
					indxSwap = 0;
				}
				Collections.swap(songs, indxSwap, i);
				// move pointer over same items
				i += 3;
			} else if (current.equals(before)) {
				// maybe i - 1 at begin of list -> move to end of list
				if (current.equals(songs.get(size - 1))) {
					// in case current same as last item
					Collections.swap(songs, i - 1, size - 3);
				} else {
					Collections.swap(songs, i - 1, size - 1);
				}
				i++;
			} else if (current.equals(after)) {
				// maybe i + 1 at end of list -> move to begin of list
				Collections.swap(songs, i + 1, 0);
				i++;
			} else {
				i += 2;
			}
		}
	}
	
	/**
	 * Convert single value as array. Ex: ..."args" : "Msg"... => ..."args" : ["Msg"]...
	 * @param jsonResult
	 * @return
	 */
	public static String convertArgsAsArray(String jsonResult) {
		if (StringUtils.isEmpty(jsonResult)) {
			return jsonResult;
		}
		return jsonResult.replaceAll("\"args\": \"(:?.*)\"", "\"args\": [\"$1\"]");
	}
	

    /**
     * Start time = MAX ( CONTENT_RELEASE_DATE + WINDOW_DAYS,
     * ANDROID_RELEASE_START_DATETIME )
     * <p/>
     * End time = ANDROID_RELEASE_END_DATETIME
     * <p/>
     * Availability = ANDROID_{MEMBER_STATUS}_ENABLE_FLG |
     * ANDROID_DELIVERY_ENABLE_FLG
     * 
     * @param songs
     * @param user
     * @return
     */
    public static List<Song> checkRule1And2(List<Song> songs, User user, boolean enableRule1) {
        List<Song> result = new ArrayList<Song>();
        int memberStatus = user.getMemberStatus().getStatus();
        for (Song song : songs) {
            StreamAuthentication strAut = song.getAuth();
            boolean memberShip = false;
            boolean duration = false;
            if (strAut != null) {
                // rule 2
                if (memberStatus == MemberStatus.PREMIUM.getStatus()) {
                    memberShip = strAut.isAndroidPayingEnableFlg();
                } else if (memberStatus == MemberStatus.FREE_TRIAL.getStatus()) {
                    memberShip = strAut.isAndroidTrialEnableFlg();
                } else {
                    memberShip = strAut.isAndroidFreeEnableFlg();
                }
                memberShip |= strAut.isAndroidDeliveryEnableFlg();
                // rule 1
                Calendar c = Calendar.getInstance();
                c.setTime(strAut.getContentReleaseDate());
                c.add(Calendar.DATE, strAut.getWindowDays());
                Date today = new Date();
                Date maxDate = (c.getTime().compareTo(
                        strAut.getAndroidReleaseStartDatetime()) >= 0) ? c
                        .getTime() : strAut.getAndroidReleaseStartDatetime();
                if (maxDate.compareTo(today) <= 0
                        && (strAut.getAndroidReleaseEndDatetime() == null || strAut
                                .getAndroidReleaseEndDatetime()
                                .compareTo(today) >= 0)) {
                    duration = true;
                }
            }
            if(enableRule1){
                if (memberShip && duration) {
                    result.add(song);
                } 
            }else if (memberShip) {
                result.add(song);
            }
        }
        return result;
    }

    /**
     * Max 3 songs can be chosed from the same artist
     * 
     * @param songs
     * @return
     */
    public static List<Song> checkMax3SongPerArtistRule(List<Song> songs,
            final int count, String seedArtist) {
        List<Song> songList = new ArrayList<Song>();
        Map<String, Integer> mapArtistCount = new HashMap<String, Integer>();

        Iterator<Song> iterator = songs.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            // song has same artist -> rank will increase
            boolean isAddSong = Boolean.TRUE;
            for (Performance p : song.getPerformances()) {
                String aid = p.getArtist().getId();
                if (mapArtistCount.containsKey(aid)) {
                    mapArtistCount.put(aid, mapArtistCount.get(aid) + 1);
                } else {
                    mapArtistCount.put(aid, 1);
                }

                if (mapArtistCount.get(aid) > 3) {
                    isAddSong = !isAddSong;
                    break;
                }
            }

            if (songList.size() < count && isAddSong) {
                songList.add(song);
            }
            if (songList.size() == count) {
                if(seedArtist != null){
                 // ensure there is at least a song belong to seed artist.
                    boolean isExistSeedArtistSong = false;
                    for (Song s : songList) {
                        for (Performance per : s.getPerformances()) {
                            if (per.getArtist().getId().equals(seedArtist)) {
                                isExistSeedArtistSong = true;
                                break;
                            }
                        }
                        if (isExistSeedArtistSong) {
                            break;
                        }
                    }
                    if (!isExistSeedArtistSong) {
                        songList.remove(songList.size() - 1);
                    }
                }
                if (songList.size() == count){
                    break;
                }
            }
        }

        return songList;
    }

}
