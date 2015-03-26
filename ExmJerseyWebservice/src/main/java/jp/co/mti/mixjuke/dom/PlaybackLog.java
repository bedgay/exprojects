package jp.co.mti.mixjuke.dom;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: nhphuoc Date: 11/28/13 Time: 2:30 PM
 */
@Entity
@Table(name = "MJ_STREAMING_LOG")
public class PlaybackLog extends AbstractDomain implements java.io.Serializable {
	private static final long serialVersionUID = 8528204332143838027L;
	// id
	// uid
	private String uid;
	// play_session_id
	private String sessionId;
	// streaming_dt
	private Timestamp serverDate;
	// prod_id
	private String productId;
	// device_cd
	private String deviceCD;
	// device_nm
	private String deviceModel;
	// unit_acc
	private boolean accountType;
	// play_sec
	private int playSec;
	// imei_no
	private String deviceId;
	// course_id
	private short courseId;
	// log_type
	private int logType;
	// app_streaming_dt
	private Timestamp playEndTime;
	// upd_nm
	private String nameOfUpdate;
	// upd_pg
	private String programOfUpdate;
	// upd_dt
	private Timestamp dateOfUpdate;
	// preview_flag
	private int previewFlag;
	// seed_artist_id
	private Integer seedArtistId;
	// seed_genre_id
	private Integer seedGenreId;

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return id;
	}

	@Column(name = "uid", nullable = false)
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "device_cd", nullable = false)
	public String getDeviceCD() {
		return deviceCD;
	}

	public void setDeviceCD(String deviceCD) {
		this.deviceCD = deviceCD;
	}

	@Column(name = "imei_no", nullable = true)
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "device_nm", nullable = false)
	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	@Column(name = "prod_id", nullable = false)
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "play_sec", nullable = false)
	public int getPlaySec() {
		return playSec;
	}

	public void setPlaySec(int playSec) {
		this.playSec = playSec;
	}

	@Column(name = "app_streaming_dt", nullable = false)
	public Timestamp getPlayEndTime() {
		return playEndTime;
	}

	public void setPlayEndTime(Timestamp playEndTime) {
		this.playEndTime = playEndTime;
	}

	@Column(name = "course_id", nullable = false)
	public short getCourseId() {
		return courseId;
	}

	public void setCourseId(short courseId) {
		this.courseId = courseId;
	}

	@Column(name = "play_session_id", nullable = false)
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name = "unit_acc", nullable = false)
	public boolean isAccountType() {
		return accountType;
	}

	public void setAccountType(boolean accountType) {
		this.accountType = accountType;
	}

	@Column(name = "log_type", nullable = false)
	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

	@Column(name = "upd_nm", nullable = false)
	public String getNameOfUpdate() {
		return nameOfUpdate;
	}

	public void setNameOfUpdate(String nameOfUpdate) {
		this.nameOfUpdate = nameOfUpdate;
	}

	@Column(name = "upd_pg", nullable = false)
	public String getProgramOfUpdate() {
		return programOfUpdate;
	}

	public void setProgramOfUpdate(String programOfUpdate) {
		this.programOfUpdate = programOfUpdate;
	}

	@Column(name = "upd_dt", nullable = false)
	public Timestamp getDateOfUpdate() {
		return dateOfUpdate;
	}

	public void setDateOfUpdate(Timestamp dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}

	@Column(name = "streaming_dt", nullable = false)
	public Timestamp getServerDate() {
		return serverDate;
	}

	public void setServerDate(Timestamp serverDate) {
		this.serverDate = serverDate;
	}

	@Column(name = "preview_flag", nullable = false)
	public int getPreviewFlag() {
		return previewFlag;
	}

	public void setPreviewFlag(int previewFlag) {
		this.previewFlag = previewFlag;
	}

	@Column(name = "seed_artist_id", nullable = true)
	public Integer getSeedArtistId() {
		return seedArtistId;
	}

	public void setSeedArtistId(Integer seedArtistId) {
		this.seedArtistId = seedArtistId;
	}
	
	@Column(name = "seed_genre_id", nullable = true)
	public Integer getSeedGenreId() {
		return seedGenreId;
	}

	public void setSeedGenreId(Integer seedGenreId) {
		this.seedGenreId = seedGenreId;
	}

}
