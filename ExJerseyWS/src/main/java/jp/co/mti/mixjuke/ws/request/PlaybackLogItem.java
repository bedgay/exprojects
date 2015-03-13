package jp.co.mti.mixjuke.ws.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @User: nhphuoc 
 * @Date: 11/28/13 
 * @Time: 3:05 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaybackLogItem {
	private String streamingDt;
	private String prodId;
	private int playSec;
	private String playSessionId;
	private int logType;
	private int playType;
	private Integer seedArtistId;
	private Integer seedGenreId;

	public PlaybackLogItem() {
		super();
	}

	@JsonProperty("streaming_dt")
	public String getStreamingDt() {
		return streamingDt;
	}

	public void setStreamingDt(String streamingDt) {
		this.streamingDt = streamingDt;
	}

	@JsonProperty("prod_id")
	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	@JsonProperty("play_sec")
	public int getPlaySec() {
		return playSec;
	}

	public void setPlaySec(int playSec) {
		this.playSec = playSec;
	}

	@JsonProperty("play_session_id")
	public String getPlaySessionId() {
		return playSessionId;
	}

	public void setPlaySessionId(String playSessionId) {
		this.playSessionId = playSessionId;
	}

	@JsonProperty("log_type")
	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

	@JsonIgnore
	public Date getPlayEndInDate() throws ParseException {
		if (this.streamingDt != null) {
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMDDHHmmss");
			f.setTimeZone(TimeZone.getTimeZone("GMT+9"));
			return f.parse(this.streamingDt);
		} else {
			return null;
		}
	}

	@JsonProperty("play_type")
	public int getPlayType() {
		return playType;
	}

	public void setPlayType(int playType) {
		this.playType = playType;
	}

	@JsonProperty("seed_artist_id")
	public Integer getSeedArtistId() {
		return seedArtistId;
	}

	public void setSeedArtistId(Integer seedArtistId) {
		this.seedArtistId = seedArtistId;
	}

	@JsonProperty("seed_genre_id")
	public Integer getSeedGenreId() {
		return seedGenreId;
	}

	public void setSeedGenreId(Integer seedGenreId) {
		this.seedGenreId = seedGenreId;
	}

}
