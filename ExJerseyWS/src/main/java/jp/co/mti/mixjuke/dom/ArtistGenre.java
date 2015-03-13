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
import javax.persistence.UniqueConstraint;

/**
 * @author ntnxuan
 * 
 */
@Entity
@Table(name = "MJ_ARTIST_GENRE", uniqueConstraints = { @UniqueConstraint(columnNames = {
        "gid", "aid" }) })
public class ArtistGenre extends AbstractDomain {

    private Genre genre;
    private Artist artist;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    public String getId() {
        return this.id;
    }

    /**
     * @return the genre
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gid")
    public Genre getGenre() {
        return genre;
    }

    /**
     * @param genre
     *            the genre to set
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
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

}
