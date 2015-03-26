/**
 * 
 */
package jp.co.mti.mixjuke.dom;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jp.co.mti.mixjuke.util.PropertyUtil;
import jp.co.mti.mixjuke.ws.response.AlbumInfo;

/**
 * @author Xuan Nguyen
 * 
 */
@Entity
@Table(name = "MJ_ALBUMS")
public class Album extends AbstractDomain {

    private String title;
    private String titleKana;
    private String jacketImg;
    private Date saleDate;
    private Set<Performance> performances = new HashSet<Performance>();

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id", unique = true, nullable = false)
    public String getId() {
        return this.id;
    }

    /**
     * @return the title
     */
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the titleKana
     */
    @Column(name = "title_kana", nullable = false)
    public String getTitleKana() {
        return titleKana;
    }

    /**
     * @param titleKana
     *            the titleKana to set
     */
    public void setTitleKana(String titleKana) {
        this.titleKana = titleKana;
    }

    /**
     * @return the jacketImg
     */
    @Column(name = "jacket_img")
    public String getJacketImg() {
        return jacketImg;
    }

    /**
     * @param jacketImg
     *            the jacketImg to set
     */
    public void setJacketImg(String jacketImg) {
        this.jacketImg = jacketImg;
    }

    /**
     * @return the saleDate
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "sale_date")
    public Date getSaleDate() {
        return saleDate;
    }

    /**
     * @param saleDate
     *            the saleDate to set
     */
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    /**
     * @return the performances
     */
    @OneToMany(mappedBy = "album")
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

    public AlbumInfo toAlbumInfo() {
        AlbumInfo albumInfo = new AlbumInfo();
        albumInfo.setAlbumId(this.getId());
        albumInfo.setAlbumTitle(this.getTitle());
        for (Performance p : this.getPerformances()) {
            Artist a = p.getArtist();
            albumInfo.setArtistId(a.getId());
            albumInfo.setArtistName(a.getName());
            break;
        }

        if (this.getJacketImg() != null) {
            albumInfo.setAlbumArtUrl(MessageFormat.format(
                    PropertyUtil.getProperty("song_album_art_url"),
                    this.getJacketImg()));
        }
        if (this.getId() != null) {
            String mix_url = PropertyUtil.getProperty("base_url")
                    + "albums/{0}/songs";
            albumInfo.setSongsUrl(MessageFormat.format(mix_url, this.getId()));
        }
        return albumInfo;
    }
}
