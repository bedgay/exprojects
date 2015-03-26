package jp.co.mti.mixjuke.batch.archive;

import jp.co.mti.mixjuke.util.PropertyUtil;

/**
 * Database configuration to archive
 *
 * @author natu
 */
public class ArchiveConfiguration {

    public static final String ARCHIVE_LOGGER_NAME="MJDB Archive Logger";
	public static final String MDL_PREFIX = "mdl";
    public static final String JDBC_PREFIX = "jdbc";
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public ArchiveConfiguration() {
        readConfig(JDBC_PREFIX);
    }

    public ArchiveConfiguration(String prefix) {
        readConfig(prefix);
    }

    private void readConfig(String prefix) {
        driverClassName = PropertyUtil.getProperty(prefix + ".driverClassName");
        url = PropertyUtil.getProperty(prefix + ".url");
        username = PropertyUtil.getProperty(prefix + ".username");
        password = PropertyUtil.getProperty(prefix + ".password");
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
