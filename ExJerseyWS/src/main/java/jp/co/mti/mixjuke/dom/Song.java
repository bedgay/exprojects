package jp.co.mti.mixjuke.dom;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import jp.co.mti.mixjuke.util.PropertyUtil;
import jp.co.mti.mixjuke.ws.response.SongInfo;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@FilterDef(name = "ratingFilter", parameters = @ParamDef(name = "userRatingFilterParam", type = "java.lang.String"))
@Table(name = "MJ_SONGS")
public class Song extends AbstractDomain implements java.io.Serializable {

    private static final long serialVersionUID = -1155763686400729416L;

    private String title;
    private String titleKana;
    private String titleAlpha;
    private String productId;
    private int trialLength;
    private String fileName;
    private int length;
    private Set<Rating> ratings = new HashSet<Rating>();
    private Set<Performance> performances = new HashSet<Performance>();
    private StreamAuthentication auth;

    //@Transient
    private String realId = "";

    @Id
    @Column(name = "pid", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "title_kana")
    public String getTitleKana() {
        return titleKana;
    }

    public void setTitleKana(String titleKana) {
        this.titleKana = titleKana;
    }

    @Column(name = "title_alpha")
    public String getTitleAlpha() {
        return titleAlpha;
    }

    public void setTitleAlpha(String titleAlpha) {
        this.titleAlpha = titleAlpha;
    }

    @Column(name = "trial_length", nullable = false)
    public int getTrialLength() {
        return trialLength;
    }

    public void setTrialLength(int trialLength) {
        this.trialLength = trialLength;
    }

    /**
     * @return the productId
     */
    @Column(name = "product_id", nullable = false)
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the fileName
     */
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the length
     */
    @Column(name = "length")
    public int getLength() {
        return length;
    }

    /**
     * @param length
     *            the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the ratings
     */
    @OneToMany(mappedBy = "song")
    @Filter(name = "ratingFilter", condition = "uid = :userRatingFilterParam")
    public Set<Rating> getRatings() {
        return ratings;
    }

    /**
     * @param ratings
     *            the ratings to set
     */
    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    /**
     * @return the performances
     */
    @OneToMany(mappedBy = "song")
    public Set<Performance> getPerformances() {
        return performances;
    }

    /**
     * @param performances
     *            the performances to set
     */
    public void setPerformances(Set<Performance> performances) {
        this.performances = performances;
    }

    /**
     * @return the auth
     */
    @OneToOne(mappedBy = "song", fetch = FetchType.LAZY)
    public StreamAuthentication getAuth() {
        return auth;
    }

    /**
     * @param auth
     *            the auth to set
     */
    public void setAuth(StreamAuthentication auth) {
        this.auth = auth;
    }

    @Transient
    public String getRealId() {
		return realId;
	}

	public void setRealId(String realId) {
		this.realId = realId;
	}

	/**
     * Convert from Song to SongInfo.
     * Seriously NOTE: Must call songService.addReadID(songList) method before you call this method
     * @param user
     *            User object.
     * @param isGetStreamUrl
     *            Decide return streamUrl or not.
     * @param seedArtistId
     *            Artist's song should same as seedArtist if any.(in case
     *            ArtistMIX)
     * @return SongInfo object.
     */
    public SongInfo toSongInfo(User user, boolean isGetStreamUrl,
            String seedArtistId) {
        String mjuid = user.getId();
        SongInfo songInfo = new SongInfo();
        songInfo.setSid(this.getId());
        songInfo.setTitle(this.getTitle());
        songInfo.setProdId(this.getProductId());
        if (this.getFileName() != null && this.getProductId() != null) {

            if (isGetStreamUrl) {
                songInfo.setStreamUrl(MessageFormat.format(
                        PropertyUtil.getProperty("stream_url"),
                        this.getFileName(), this.getProductId(),
                        PropertyUtil.getProperty("sv")));
            }
            songInfo.setTrialUrl(MessageFormat.format(
                    PropertyUtil.getProperty("trial_url"), this.getFileName(),
                    this.getProductId(), PropertyUtil.getProperty("sv")));
        }
        songInfo.setTrialLength(this.getTrialLength());

        for (Performance p : this.getPerformances()) {
            songInfo.setArtist(p.getArtist().toArtistInfo(mjuid));
            if (p.getAlbum() != null && p.getAlbum().getJacketImg() != null) {
                if (p.getAlbum().getJacketImg() != null) {
                    songInfo.setAlbumArtUrl(MessageFormat.format(PropertyUtil
                            .getProperty("song_album_art_url"), p.getAlbum()
                            .getJacketImg()));
                }
            }
            if (p.getArtist().getId() != null && this.getRealId() != null) {
                songInfo.setDetailUrl(MessageFormat.format(PropertyUtil
                        .getProperty("song_detail_url"), p.getArtist().getId(),
                        this.getRealId()));
            }

            if (seedArtistId != null) {
                // loop to find artist same as seed artist, if among artist
                // perform this song exist seed artist.
                if (seedArtistId.equals(p.getArtist().getId())) {
                    break;
                }
            }
        }// for performance

        for (Rating r : this.getRatings()) {
            if (mjuid.equals(r.getUser().getId())) {
                songInfo.setSflag(r.getSflag());
            }
        }
        return songInfo;
    }

}
