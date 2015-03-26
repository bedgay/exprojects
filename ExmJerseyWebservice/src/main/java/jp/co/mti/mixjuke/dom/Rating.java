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
 * @author Xuan Nguyen
 * 
 */
@Entity
@Table(name = "MJ_RATING", uniqueConstraints = { @UniqueConstraint(columnNames = {
        "uid", "sid" }) })
public class Rating extends AbstractDomain implements java.io.Serializable {

    private static final long serialVersionUID = 3733223318519765769L;

    private User user;
    private int sflag;
    private Song song;
    private Date updateDatetime;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    /**
     * @return the sflag
     */
    @Column(name = "sflag", nullable = false)
    public int getSflag() {
        return sflag;
    }

    /**
     * @param sflag
     *            the sflag to set
     */
    public void setSflag(int sflag) {
        this.sflag = sflag;
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
     * @return the updateDatetime
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "update_datetime")
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * @param updateDatetime
     *            the updateDatetime to set
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}
