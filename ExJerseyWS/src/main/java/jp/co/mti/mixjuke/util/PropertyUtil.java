package jp.co.mti.mixjuke.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

/**
 * User: nhphuoc
 * Date: 11/19/13
 * Time: 2:18 PM
 */
public class PropertyUtil extends PropertyPlaceholderConfigurer {
    private static final Logger LOGGER = LogManager.getLogger(PropertyUtil.class.getName());
    private static Map properties;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
                                     Properties props) throws BeansException {
        LOGGER.info("processProperties");
        super.processProperties(beanFactory, props);

        properties = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            properties.put(keyStr, parseStringValue(props.getProperty(keyStr),
                    props, new HashSet()));
        }
    }

    /**
     * Get property value from property name
     * @param name Property name
     * @return Property value
     */
    public static String getProperty(String name) {
//        LOGGER.info("getProperty");
        return (String) properties.get(name);
    }
}
