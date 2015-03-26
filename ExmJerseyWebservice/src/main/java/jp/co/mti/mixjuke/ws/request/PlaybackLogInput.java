package jp.co.mti.mixjuke.ws.request;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * User: nhphuoc Date: 11/28/13 Time: 2:57 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaybackLogInput {
	private String deviceCd;
	private String devUniqueId;
	private String deviceName;
	private String courseId;
	private List<PlaybackLogItem> log;

	public PlaybackLogInput() {
		super();
	}

	@JsonProperty("device_cd")
	public String getDeviceCd() {
		return deviceCd;
	}

	public void setDeviceCd(String deviceCd) {
		this.deviceCd = deviceCd;
	}

	@JsonProperty("dev_unique_id")
	public String getDevUniqueId() {
		return devUniqueId;
	}

	public void setDevUniqueId(String deviceUniqueId) {
		this.devUniqueId = deviceUniqueId;
	}

	@JsonProperty("device_nm")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@JsonProperty("course_id")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@JsonProperty("log")
	public List<PlaybackLogItem> getLog() {
		return log;
	}

	public void setLog(List<PlaybackLogItem> log) {
		this.log = log;
	}
}
