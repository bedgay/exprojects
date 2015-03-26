package jp.co.mti.mixjuke.util;

/**
 * User: nhphuoc
 * Date: 10/22/13
 * Time: 4:25 PM
 */
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class MySQLDriverRegistrationListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(MySQLDriverRegistrationListener.class
            .getName());
    private Driver driver = null;

    /**
     * Registers the MySQL JDBC driver
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            this.driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        boolean manualRegistartion = true;
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver instanceof  com.mysql.jdbc.Driver) {
                com.mysql.jdbc.Driver alreadyRegistered = (com.mysql.jdbc.Driver) driver;
                if (alreadyRegistered.getClass() == this.driver.getClass()) {
                    // same class in the VM already registered itself
                    manualRegistartion = false;
                    this.driver = alreadyRegistered;
                    break;
                }
            }
        }

        try {
            if (manualRegistartion) {
                DriverManager.registerDriver(driver);
            } else {
                LOGGER.debug("Driver was registered automatically");
            }
            LOGGER.info("Registered jdbc driver:"+driver.getClass().getName()+" v"+driver.getMajorVersion()+"."+driver.getMinorVersion());
        } catch (SQLException e) {
            LOGGER.error("Error registering jdbc driver, database connectivity might be unavailable!",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Deregisters MySQL JDBC driver
     *
     * Prevents Tomcat 7 from complaining about memory leaks.
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (this.driver != null) {
            try {
                LOGGER.info("Deregistering jdbc driver:"+driver.getClass().getName());
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOGGER.warn("Error deregistering driver:"+driver.getClass().getName(),e);
            }
            this.driver = null;
       }else{
            LOGGER.warn("No driver to deregister");
        }

    }
}
