/**
 * 
 */
package jp.co.mti.mixjuke.dom;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * @author Xuan
 * 
 */
@Entity
@Table(name = "MJ_FAVORITE_ARTIST", uniqueConstraints = { @UniqueConstraint(columnNames = {
        "uid", "aid" }) })
public class Favorite extends AbstractDomain {

    private User user;
    private Artist artist;
    private Date addDatetime;

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
     * @return the user
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
     * @return the addDatetime
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "add_datetime")
    public Date getAddDatetime() {
        return addDatetime;
    }

    /**
     * @param addDatetime
     *            the addDatetime to set
     */
    public void setAddDatetime(Date addDatetime) {
        this.addDatetime = addDatetime;
    }

}
