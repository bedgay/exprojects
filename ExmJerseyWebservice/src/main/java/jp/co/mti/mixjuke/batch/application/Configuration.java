package jp.co.mti.mixjuke.batch.application;

import jp.co.mti.mixjuke.util.PropertyUtil;

/**
 * @User: naminh
 * @Date: 10/10/13
 * @Time: 3:17 PM
 */
public class Configuration {
	private String dbSource;
	private String dbDest;
	private String dbSourceUsername;
	private String dbDestUsername;
	private String dbSourcePassword;
	private String dbDestPassword;
	private String dbDestDriver;
	private String dbSourceDriver;
	// The logger's name is not longer than 26 characters
	public static final String SYNC_LOGGER_NAME = "MJDB Synchronization";

	public Configuration() {
		this.dbSource = PropertyUtil.getProperty("mics.url");
		this.dbDest = PropertyUtil.getProperty("jdbc.url");
		this.dbSourceUsername = PropertyUtil.getProperty("mics.username");
		this.dbSourcePassword = PropertyUtil.getProperty("mics.password");
		this.dbDestUsername = PropertyUtil.getProperty("jdbc.username");
		this.dbDestPassword = PropertyUtil.getProperty("jdbc.password");
		this.dbSourceDriver = PropertyUtil.getProperty("mics.driverClassName");
		this.dbDestDriver = PropertyUtil.getProperty("jdbc.driverClassName");
	}

	public String getDbSource() {
		return dbSource;
	}

	public String getDbDest() {
		return dbDest;
	}

	public String getDbSourceUsername() {
		return dbSourceUsername;
	}

	public String getDbSourcePassword() {
		return dbSourcePassword;
	}

	public String getDbDestUsername() {
		return dbDestUsername;
	}

	public String getDbDestPassword() {
		return dbDestPassword;
	}

	public String getDbDestDriver() {
		return this.dbDestDriver;
	}

	public String getDbSourceDriver() {
		return this.dbSourceDriver;
	}
}