/**
 * 
 */
package jp.co.mti.mixjuke.dom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import jp.co.mti.mixjuke.util.DateUtil;
import jp.co.mti.mixjuke.ws.request.MemberStatus;
import jp.co.mti.mixjuke.ws.response.UserProfileInfo;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Xuan Nguyen
 * 
 */
@Entity
@Table(name = "MJ_USERS")
public class User extends AbstractDomain implements java.io.Serializable {

    private static final long serialVersionUID = 8528204332143838026L;

    private String name;
    private String uid;
    private String mopitaid;
    private int gender = 0;
    private Date birthday;
    private String avatarUrl;
    private short memberStatus = (short) MemberStatus.FREE_TRIAL.getStatus();
    private int shareProfile = 0;
    private String devid;
    private boolean initial = true;
    private boolean accountType;
    private Date freeSubscribeDatetime;
    Set<Favorite> favorites = new HashSet<Favorite>();
    List<Purchase> purchaseHistories = new ArrayList<Purchase>();

    /*
     * (non-Javadoc)
     * 
     * @see jp.co.mti.mixjuke.dom.AbstractDomain#getId()
     */
    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mjuid", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the uid
     */
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Mobita user ID
     * 
     * @return
     */
    @Column(name = "mopitaid")
    public String getMopitaid() {
        return mopitaid;
    }

    /**
     * @param mopitaid
     *            Mobita user ID
     */
    public void setMopitaid(String mopitaid) {
        this.mopitaid = mopitaid;
    }

    @Column(name = "gender", nullable = false)
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * Just use to map DB. Outside should NOT call this method to get member
     * status, call getMemberStatus instead.
     * 
     * @return status of member profile 
     */
    @Column(name = "member_status", nullable = false)
    public short getStatus() {
        return memberStatus;
    }

    public void setStatus(short memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    /**
     * @return the initial
     */
    @Column(name = "initial")
    public boolean isInitial() {
        return initial;
    }

    /**
     * @param initial
     *            the initial to set
     */
    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    @Column(name = "share_profile", nullable = false)
    public int getShareProfile() {
        return shareProfile;
    }

    public void setShareProfile(int shareProfile) {
        this.shareProfile = shareProfile;
    }

    /**
     * @return the favorites
     */
    @OneToMany(mappedBy = "user")
    @Cascade({ CascadeType.ALL })
    public Set<Favorite> getFavorites() {
        return favorites;
    }

    /**
     * @param favorites
     *            the favorites to set
     */
    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    @OneToMany(mappedBy = "user")
    @Cascade({ CascadeType.ALL })
    public List<Purchase> getPurchaseHistories() {
        return purchaseHistories;
    }

    public void setPurchaseHistories(List<Purchase> purchaseHistories) {
        this.purchaseHistories = purchaseHistories;
    }

    /**
     * Build UserProfileInfo
     * 
     * @return UserProfileInfo object
     */
    public UserProfileInfo toUserProfileInfo() {
        UserProfileInfo userInfo = new UserProfileInfo();
        userInfo.setMjuid(this.getId());
        userInfo.setName(this.getName());
        userInfo.setGender(this.getGender());
        userInfo.setAvatorUrl(this.getAvatarUrl());
        if (this.getBirthday() != null) {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
            userInfo.setBirthday(dateformat.format(this.getBirthday()));
        }
        userInfo.setMemberStatus(this.getMemberStatus().getStatus());
        userInfo.setShareProfile(this.getShareProfile() == 0);
        userInfo.setInitial(this.isInitial());
        return userInfo;
    }

    @Column(name = "unit_acc", nullable = false)
    public boolean isAccountType() {
        return accountType;
    }

    public void setAccountType(boolean accountType) {
        this.accountType = accountType;
    }

    /**
     * @return the freeSubscribeDatetime
     */
    @Column(name = "free_subscribe_dt")
    public Date getFreeSubscribeDatetime() {
        return freeSubscribeDatetime;
    }

    /**
     * @param freeSubscribeDatetime
     *            the freeSubscribeDatetime to set
     */
    public void setFreeSubscribeDatetime(Date freeSubscribeDatetime) {
        this.freeSubscribeDatetime = freeSubscribeDatetime;
    }

    @Transient
    /**
     * if member status is MemberStatus.FREE, the current date is in 14 days
     * from the user registration, return MemberStatus.FREE_TRIAL.
     * else return the current member status.
     * @return MemberStatus
     */
    public MemberStatus getMemberStatus() {
        if ((this.getStatus() == MemberStatus.FREE.getStatus())
                && (this.getFreeSubscribeDatetime() != null)) {
            
           //CHG-S nhphuoc 140204 Fix bug 5712
        	//The time count in the midnight of the track date
        	//Take example track date is 2014-01-21 23:59:00
        	//2014-01-21 is counted as one day.
        	Date trackdate = this.getFreeSubscribeDatetime();
        	Date trackday = DateUtil.cutoffTime(trackdate);
        	//Date checkDatetime = DateUtil.addDate(
            //        this.getFreeSubscribeDatetime(), 14, Calendar.DAY_OF_YEAR);
            Date checkDatetime = DateUtil.addDate(trackday, 14, Calendar.DAY_OF_YEAR);
            //if (checkDatetime.compareTo(new Date()) >= 0) {
            Date currentDay = DateUtil.cutoffTime(new Date());
            if (checkDatetime.compareTo(currentDay) > 0) {
            //CHG-E nhphuoc
                return MemberStatus.FREE_TRIAL;
            }
        }
        return MemberStatus.fromShort(this.getStatus());
    }
}
