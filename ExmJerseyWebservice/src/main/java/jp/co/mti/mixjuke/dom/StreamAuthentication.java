/**
 * 
 */
package jp.co.mti.mixjuke.dom;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author ntnxuan
 * 
 */
@Entity
@Table(name = "MJ_STR_AUTH")
public class StreamAuthentication extends AbstractDomain {

    // region_id
    private Song song;
    private Date contentReleaseDate;// content_release_date
    private int windowDays;// window_days
    private boolean androidDeliveryEnableFlg;// android_delivery_enable_flg
    private boolean androidSearchFlg;// android_search_flg;
    private Date androidReleaseStartDatetime;// android_release_start_datetime
    private Date androidReleaseEndDatetime;// android_release_end_datetime
    private boolean androidFreeEnableFlg;// android_free_enable_flg
    private boolean androidTrialEnableFlg;// android_trial_enable_flg
    private boolean androidPayingEnableFlg;// android_paying_enable_flg
    private boolean iosDeliveryEnableFlg;// ios_delivery_enable_flg
    private boolean iosSearchFlg;// ios_search_flg
    private Date iosReleaseStartDatetime;// ios_release_start_datetime
    private Date iosReleaseEndDatetime;// ios_release_end_datetime
    private boolean iosFreeEnableFlg;// ios_free_enable_flg
    private boolean iosTrialEnableFlg;// ios_trial_enable_flg
    private boolean iosPayingEnableFlg;// ios_paying_enable_flg

    @Override
    @Id
    @Column(name = "region_id", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    /**
     * @return the contentReleaseDate
     */
    @Column(name = "content_release_date")
    public Date getContentReleaseDate() {
        return contentReleaseDate;
    }

    /**
     * @param contentReleaseDate
     *            the contentReleaseDate to set
     */
    public void setContentReleaseDate(Date contentReleaseDate) {
        this.contentReleaseDate = contentReleaseDate;
    }

    /**
     * @return the windowDays
     */
    @Column(name = "window_days")
    public int getWindowDays() {
        return windowDays;
    }

    /**
     * @param windowDays
     *            the windowDays to set
     */
    public void setWindowDays(int windowDays) {
        this.windowDays = windowDays;
    }

    /**
     * @return the androidDeliveryEnableFlg
     */
    @Column(name = "android_delivery_enable_flg")
    public boolean isAndroidDeliveryEnableFlg() {
        return androidDeliveryEnableFlg;
    }

    /**
     * @param androidDeliveryEnableFlg
     *            the androidDeliveryEnableFlg to set
     */
    public void setAndroidDeliveryEnableFlg(boolean androidDeliveryEnableFlg) {
        this.androidDeliveryEnableFlg = androidDeliveryEnableFlg;
    }

    /**
     * @return the androidSearchFlg
     */
    @Column(name = "android_search_flg")
    public boolean isAndroidSearchFlg() {
        return androidSearchFlg;
    }

    /**
     * @param androidSearchFlg
     *            the androidSearchFlg to set
     */
    public void setAndroidSearchFlg(boolean androidSearchFlg) {
        this.androidSearchFlg = androidSearchFlg;
    }

    /**
     * @return the androidReleaseStartDatetime
     */
    @Column(name = "android_release_start_datetime")
    public Date getAndroidReleaseStartDatetime() {
        return androidReleaseStartDatetime;
    }

    /**
     * @param androidReleaseStartDatetime
     *            the androidReleaseStartDatetime to set
     */
    public void setAndroidReleaseStartDatetime(Date androidReleaseStartDatetime) {
        this.androidReleaseStartDatetime = androidReleaseStartDatetime;
    }

    /**
     * @return the androidReleaseEndDatetime
     */
    @Column(name = "android_release_end_datetime")
    public Date getAndroidReleaseEndDatetime() {
        return androidReleaseEndDatetime;
    }

    /**
     * @param androidReleaseEndDatetime
     *            the androidReleaseEndDatetime to set
     */
    public void setAndroidReleaseEndDatetime(Date androidReleaseEndDatetime) {
        this.androidReleaseEndDatetime = androidReleaseEndDatetime;
    }

    /**
     * @return the androidFreeEnableFlg
     */
    @Column(name = "android_free_enable_flg")
    public boolean isAndroidFreeEnableFlg() {
        return androidFreeEnableFlg;
    }

    /**
     * @param androidFreeEnableFlg
     *            the androidFreeEnableFlg to set
     */
    public void setAndroidFreeEnableFlg(boolean androidFreeEnableFlg) {
        this.androidFreeEnableFlg = androidFreeEnableFlg;
    }

    /**
     * @return the androidTrialEnableFlg
     */
    @Column(name = "android_trial_enable_flg")
    public boolean isAndroidTrialEnableFlg() {
        return androidTrialEnableFlg;
    }

    /**
     * @param androidTrialEnableFlg
     *            the androidTrialEnableFlg to set
     */
    public void setAndroidTrialEnableFlg(boolean androidTrialEnableFlg) {
        this.androidTrialEnableFlg = androidTrialEnableFlg;
    }

    /**
     * @return the androidPayingEnableFlg
     */
    @Column(name = "android_paying_enable_flg")
    public boolean isAndroidPayingEnableFlg() {
        return androidPayingEnableFlg;
    }

    /**
     * @param androidPayingEnableFlg
     *            the androidPayingEnableFlg to set
     */
    public void setAndroidPayingEnableFlg(boolean androidPayingEnableFlg) {
        this.androidPayingEnableFlg = androidPayingEnableFlg;
    }

    /**
     * @return the iosDeliveryEnableFlg
     */
    @Column(name = "ios_delivery_enable_flg")
    public boolean isIosDeliveryEnableFlg() {
        return iosDeliveryEnableFlg;
    }

    /**
     * @param iosDeliveryEnableFlg
     *            the iosDeliveryEnableFlg to set
     */
    public void setIosDeliveryEnableFlg(boolean iosDeliveryEnableFlg) {
        this.iosDeliveryEnableFlg = iosDeliveryEnableFlg;
    }

    /**
     * @return the iosSearchFlg
     */
    @Column(name = "ios_search_flg")
    public boolean isIosSearchFlg() {
        return iosSearchFlg;
    }

    /**
     * @param iosSearchFlg
     *            the iosSearchFlg to set
     */
    public void setIosSearchFlg(boolean iosSearchFlg) {
        this.iosSearchFlg = iosSearchFlg;
    }

    /**
     * @return the iosReleaseStartDatetime
     */
    @Column(name = "ios_release_start_datetime")
    public Date getIosReleaseStartDatetime() {
        return iosReleaseStartDatetime;
    }

    /**
     * @param iosReleaseStartDatetime
     *            the iosReleaseStartDatetime to set
     */
    public void setIosReleaseStartDatetime(Date iosReleaseStartDatetime) {
        this.iosReleaseStartDatetime = iosReleaseStartDatetime;
    }

    /**
     * @return the iosReleaseEndDatetime
     */
    @Column(name = "ios_release_end_datetime")
    public Date getIosReleaseEndDatetime() {
        return iosReleaseEndDatetime;
    }

    /**
     * @param iosReleaseEndDatetime
     *            the iosReleaseEndDatetime to set
     */
    public void setIosReleaseEndDatetime(Date iosReleaseEndDatetime) {
        this.iosReleaseEndDatetime = iosReleaseEndDatetime;
    }

    /**
     * @return the iosFreeEnableFlg
     */
    @Column(name = "ios_free_enable_flg")
    public boolean isIosFreeEnableFlg() {
        return iosFreeEnableFlg;
    }

    /**
     * @param iosFreeEnableFlg
     *            the iosFreeEnableFlg to set
     */
    public void setIosFreeEnableFlg(boolean iosFreeEnableFlg) {
        this.iosFreeEnableFlg = iosFreeEnableFlg;
    }

    /**
     * @return the iosTrialEnableFlg
     */
    @Column(name = "ios_trial_enable_flg")
    public boolean isIosTrialEnableFlg() {
        return iosTrialEnableFlg;
    }

    /**
     * @param iosTrialEnableFlg
     *            the iosTrialEnableFlg to set
     */
    public void setIosTrialEnableFlg(boolean iosTrialEnableFlg) {
        this.iosTrialEnableFlg = iosTrialEnableFlg;
    }

    /**
     * @return the iosPayingEnableFlg
     */
    @Column(name = "ios_paying_enable_flg")
    public boolean isIosPayingEnableFlg() {
        return iosPayingEnableFlg;
    }

    /**
     * @param iosPayingEnableFlg
     *            the iosPayingEnableFlg to set
     */
    public void setIosPayingEnableFlg(boolean iosPayingEnableFlg) {
        this.iosPayingEnableFlg = iosPayingEnableFlg;
    }

    /**
     * @return the song
     */
    @JoinColumn(name = "region_id")
    @OneToOne(fetch = FetchType.LAZY)
    // @PrimaryKeyJoinColumn
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

}
