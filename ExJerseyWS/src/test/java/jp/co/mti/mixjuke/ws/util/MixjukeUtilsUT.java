/**
 * 
 */
package jp.co.mti.mixjuke.ws.util;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import jp.co.mti.mixjuke.dom.Album;
import jp.co.mti.mixjuke.dom.Artist;
import jp.co.mti.mixjuke.dom.Performance;
import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.StreamAuthentication;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.util.DateUtil;
import jp.co.mti.mixjuke.util.MixjukeUtils;
import jp.co.mti.mixjuke.ws.request.MemberStatus;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ntnxuan
 * 
 */
public class MixjukeUtilsUT {
    private StreamAuthentication createNewAuth(boolean androidFree,
            boolean androidTrial, boolean androidPaying,
            boolean androidDelivery, Date contentReleaseDate, int windowDays,
            Date androidReleaseStartDate, Date androidReleaseEndDate) {
        StreamAuthentication auth = new StreamAuthentication();
        auth.setAndroidFreeEnableFlg(androidFree);
        auth.setAndroidTrialEnableFlg(androidTrial);
        auth.setAndroidPayingEnableFlg(androidPaying);
        auth.setAndroidDeliveryEnableFlg(androidDelivery);
        auth.setContentReleaseDate(contentReleaseDate);
        auth.setWindowDays(windowDays);
        auth.setAndroidReleaseStartDatetime(androidReleaseStartDate);
        auth.setAndroidReleaseEndDatetime(androidReleaseEndDate);
        return auth;
    }

    private Song createNewSong(String songid, StreamAuthentication auth) {
        Song song = new Song();
        song.setId(songid);
        if (auth != null) {
            song.setAuth(auth);
        }
        return song;
    }

    private Artist createNewArtist(String aid) {
        Artist ar = new Artist();
        ar.setId(aid);
        return ar;
    }

    private Album createNewAlbum(String albumid) {
        Album al = new Album();
        al.setId(albumid);
        return al;
    }

    private Performance createNewPerformance(String songid, String artistid,
            String albumid) {
        Performance per = new Performance();
        per.setAlbum(createNewAlbum(albumid));
        per.setArtist(createNewArtist(artistid));
        per.setSong(createNewSong(songid, null));
        return per;
    }

    @Test
    public void testCheckRule1And2() {
        List<Song> songs = new ArrayList<Song>();
        User user = new User();
        user.setStatus((short) MemberStatus.FREE.getStatus());
        boolean enableRule1 = true;

        StreamAuthentication strValid1 = createNewAuth(true, true, true, true,
                new Date(), 0, new Date(), null);
        StreamAuthentication strValid2 = createNewAuth(true, true, true, false,
                new Date(), 0, new Date(), null);
        StreamAuthentication strInValid1 = createNewAuth(false, false, false,
                false, new Date(), 0, new Date(), null);
        StreamAuthentication strInValid2 = createNewAuth(false, false, false,
                false, new Date(), 0, new Date(),
                DateUtil.addDate(new Date(), 5, Calendar.DAY_OF_YEAR));
        
        final Song songValid1 = createNewSong("1", strValid1);
        final Song songValid2 = createNewSong("2", strValid2);
        final Song songInValid1 = createNewSong("3", strInValid1);
        final Song songInValid2 = createNewSong("4", strInValid2);

        songs.addAll(new ArrayList<Song>() {
            private static final long serialVersionUID = -5431558015901501970L;
            {
                add(songValid1);
                add(songValid2);
                add(songInValid1);
                add(songInValid2);
            }
        });

        List<Song> resultList = MixjukeUtils.checkRule1And2(songs, user,
                enableRule1);
        assertTrue(resultList.size() == 2);
    }

    @Test
    public void testCheckMax3SongPerArtistRule() {
        List<Song> songs = new ArrayList<Song>();
        int count = 10;

        // collection 1
        String artistId1 = "11";
        final Song s1 = createNewSong("1", null);
        final Song s2 = createNewSong("2", null);
        final Song s3 = createNewSong("3", null);
        final Song s4 = createNewSong("4", null);
        final Performance per1 = createNewPerformance(s1.getId(), artistId1,
                "22");
        final Performance per2 = createNewPerformance(s2.getId(), artistId1,
                "22");
        final Performance per3 = createNewPerformance(s3.getId(), artistId1,
                "22");
        final Performance per4 = createNewPerformance(s4.getId(), artistId1,
                "22");
        s1.setPerformances(new HashSet<Performance>() {
            private static final long serialVersionUID = -7038436744411278155L;

            {
                add(per1);
            }
        });
        s2.setPerformances(new HashSet<Performance>() {
            private static final long serialVersionUID = 6209808774067874507L;

            {
                add(per2);
            }
        });
        s3.setPerformances(new HashSet<Performance>() {
            private static final long serialVersionUID = -4173108807443820520L;

            {
                add(per3);
            }
        });
        s4.setPerformances(new HashSet<Performance>() {
            private static final long serialVersionUID = -1050636722307709093L;

            {
                add(per4);
            }
        });

        // collection 2
        String artistId2 = "22";
        final Song s11 = createNewSong("11", null);
        final Song s22 = createNewSong("22", null);
        final Song s33 = createNewSong("33", null);
        final Performance per11 = createNewPerformance(s11.getId(), artistId2,
                "22");
        final Performance per22 = createNewPerformance(s22.getId(), artistId2,
                "22");
        final Performance per33 = createNewPerformance(s33.getId(), artistId2,
                "22");
        s11.setPerformances(new HashSet<Performance>() {
            private static final long serialVersionUID = -731349700645581999L;

            {
                add(per11);
            }
        });
        s22.setPerformances(new HashSet<Performance>() {
            private static final long serialVersionUID = 515097660636101317L;

            {
                add(per22);
            }
        });
        s33.setPerformances(new HashSet<Performance>() {
            private static final long serialVersionUID = 6690659806639055785L;

            {
                add(per33);
            }
        });

        // collection 3
        String artistId3 = "33";
        final Song s111 = createNewSong("111", null);
        final Performance per111 = createNewPerformance(s111.getId(),
                artistId3, "22");
        s111.setPerformances(new HashSet<Performance>() {
            private static final long serialVersionUID = 8740687718443169355L;

            {
                add(per111);
            }
        });

        songs.addAll(new ArrayList<Song>() {
            private static final long serialVersionUID = -5431558015901501970L;

            {
                add(s1);
                add(s2);
                add(s3);
                add(s4);
                add(s11);
                add(s22);
                add(s33);
                add(s111);
            }
        });
        List<Song> resultList = MixjukeUtils.checkMax3SongPerArtistRule(songs,
                count, null);
        checkResultMax3SongRule(resultList);
    }

    private void checkResultMax3SongRule(List<Song> resultList) {
        Map<String, Integer> mapArtistCount = new HashMap<String, Integer>();
        for (Song s : resultList) {
            for (Performance p : s.getPerformances()) {
                String aid = p.getArtist().getId();
                if (mapArtistCount.containsKey(aid)) {
                    mapArtistCount.put(aid, mapArtistCount.get(aid) + 1);
                } else {
                    mapArtistCount.put(aid, 1);
                }
                if (mapArtistCount.get(aid) > 3) {
                    assertTrue(false);
                }
            }
        }
    }

    @Test
    public void testConvert() {
        String json = "{\"result\": {\"code\": \"E025\",\"args\": \"ãƒªã‚¯ã‚¨ã‚¹ãƒˆã�®æœ‰åŠ¹æœŸé™�ã�Œåˆ‡ã‚Œã�¦ã�„ã�¾ã�™ã€‚\"}}";
        json = MixjukeUtils.convertArgsAsArray(json);
        Assert.assertEquals(
                "{\"result\": {\"code\": \"E025\",\"args\": [\"ãƒªã‚¯ã‚¨ã‚¹ãƒˆã�®æœ‰åŠ¹æœŸé™�ã�Œåˆ‡ã‚Œã�¦ã�„ã�¾ã�™ã€‚\"]}}",
                json);
    }

    @Test
    public void testRequestString1() {
        String input = "{\"iai_aver\":\"1.0\",\"iai_akey\":\"d20c3daceb6d07902e\",\"iai_atms\":\"20100917184601001\"}";
        String sig = null;
        try {
            sig = MixjukeUtils.getURLEncoderString(MixjukeUtils.getSignature(
                    input, "nYa3cKHrfTgAonxWvCqKKg=="));
        } catch (Exception e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        String req = null;
        try {
            req = MixjukeUtils.getBase64URLEncoderString(input);
        } catch (UnsupportedEncodingException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        Assert.assertEquals(
                "eyJpYWlfYXZlciI6IjEuMCIsImlhaV9ha2V5IjoiZDIwYzNkYWNlYjZkMDc5MDJlIiwiaWFpX2F0bXMiOiIyMDEwMDkxNzE4NDYwMTAwMSJ9",
                req);
        Assert.assertEquals("bYPgeNWB9NogEXWmPqmM1eGGF9AYKLyvQG1JTpfJWEg%3D",
                sig);

    }

    @Test
    public void testRequestString2() {
        String input = "{\"iai_aver\":\"1.0\",\"iai_akey\":\"724b4e0b54761d6eec\",\"iai_atms\":\"20100917184601001\"}";
        String sig = null;
        try {
            sig = MixjukeUtils.getURLEncoderString(MixjukeUtils.getSignature(
                    input, "zR7OdrFK41THGsijZwYjVQ=="));
        } catch (Exception e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        String req = null;
        try {
            req = MixjukeUtils.getBase64URLEncoderString(input);
        } catch (UnsupportedEncodingException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        Assert.assertEquals(
                "eyJpYWlfYXZlciI6IjEuMCIsImlhaV9ha2V5IjoiNzI0YjRlMGI1NDc2MWQ2ZWVjIiwiaWFpX2F0bXMiOiIyMDEwMDkxNzE4NDYwMTAwMSJ9",
                req);
        Assert.assertEquals(
                "g8E7OWKT9xUq2%2FUmrZ%2BiwtZ0v%2FKYLuVP1UrjaHxO8sQ%3D", sig);

    }

    @Test
    public void testRequestString3() {
        String input = "{\"iai_aver\":\"1.0\",\"iai_akey\":\"efc88d5b9a75106073\",\"iai_atms\":\"19700101121212001\",\"iai_foo\":\"abcdefg\",\"iai_bar\":\"12345\"}";
        String sig = null;
        try {
            sig = MixjukeUtils.getURLEncoderString(MixjukeUtils.getSignature(
                    input, "57t3zF/dotAFI9PSa7Qv6Q=="));
        } catch (Exception e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        String req = null;
        try {
            req = MixjukeUtils.getBase64URLEncoderString(input);
        } catch (UnsupportedEncodingException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        Assert.assertEquals(
                "eyJpYWlfYXZlciI6IjEuMCIsImlhaV9ha2V5IjoiZWZjODhkNWI5YTc1MTA2MDczIiwiaWFpX2F0bXMiOiIxOTcwMDEwMTEyMTIxMjAwMSIsImlhaV9mb28iOiJhYmNkZWZnIiwiaWFpX2JhciI6IjEyMzQ1In0%3D",
                req);
        Assert.assertEquals(
                "IzbMeRL2z2rLR6ErgHXZe%2BodTg892c9k%2B9xydQk5NN0%3D", sig);

    }
}
