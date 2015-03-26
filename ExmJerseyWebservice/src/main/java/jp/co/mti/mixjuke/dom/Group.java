/**
 * 
 */
package jp.co.mti.mixjuke.dom;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author ntnxuan
 * 
 */
@Entity
@Table(name = "MJ_GROUPS")
public class Group extends AbstractDomain implements java.io.Serializable {

    private static final long serialVersionUID = -9095708900000574259L;

    private String name;
    private Genre genre;
    private Set<GroupArtist> artists;

    @Override
    @Id
    @Column(name = "id", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    /**
     * @return the name
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the artists
     */
    @OneToMany(mappedBy = "group")
    public Set<GroupArtist> getArtists() {
        return artists;
    }

    /**
     * @param artists
     *            the artists to set
     */
    public void setArtists(Set<GroupArtist> artists) {
        this.artists = artists;
    }


}
