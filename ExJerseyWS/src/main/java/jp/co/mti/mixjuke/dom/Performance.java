/**
 * 
 */
package jp.co.mti.mixjuke.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Xuan
 * 
 */
@Entity
@Table(name = "MJ_PERFORMANCE")
public class Performance extends AbstractDomain {

    private Artist artist;
    private Song song;
    private Album album;

    /*
     * (non-Javadoc)
     * 
     * @see jp.co.mti.mixjuke.dom.AbstractDomain#getId()
     */
    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    public String getId() {
        return this.id;
    }

    /**
     * @return the artist
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aid")
    public Artist getArtist() {
        return artist;
    }

    /**
     * @param artist
     *            the artist to set
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * @return the song
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid")
    public Song getSong() {
        return song;
    }

    /**
     * @param song
     *            the song to set
     */
    public void setSong(Song song) {
        this.song = song;
    }

    /**
     * @return the album
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    public Album getAlbum() {
        return album;
    }

    /**
     * @param album
     *            the album to set
     */
    public void setAlbum(Album album) {
        this.album = album;
    }

}
