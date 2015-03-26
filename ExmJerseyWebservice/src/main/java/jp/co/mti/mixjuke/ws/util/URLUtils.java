package jp.co.mti.mixjuke.ws.util;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * User: nhphuoc
 * Date: 11/19/13
 * Time: 8:32 AM
 */
public class URLUtils {
    public static final String UTF8_ENCODING = "UTF-8";
	private static final Logger LOGGER = LogManager.getLogger(URLUtils.class.getName());
    /**
     * Parse URL query string into key-value map.
     * @param query The query string.
     * @return  a key-value map. Return null if parsing fails.
     */
    public static Map<String, String> parseQuery(String query) {
        if (query == null) return null;
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            try {
                query_pairs.put(
                        URLDecoder.decode(pair.substring(0, idx), UTF8_ENCODING),
                        URLDecoder.decode(pair.substring(idx + 1), UTF8_ENCODING));
            } catch (Exception e) {
              LOGGER.warn("parseQuery error",e);
                return null;
            }
        }
        return query_pairs;
    }

    /**
     * Parse URL query string into key-value map.
     * @param query The query string.
     * @param isDecode The decode flag
     * @return  a key-value map. Return null if parsing fails.
     */
    public static Map<String, String> parseQuery(String query, boolean isDecode) {
        if (query == null) return null;
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            try {
            	if (isDecode) {
	                query_pairs.put(
	                        URLDecoder.decode(pair.substring(0, idx), UTF8_ENCODING),
	                        URLDecoder.decode(pair.substring(idx + 1), UTF8_ENCODING));
            	} else {
	                query_pairs.put(pair.substring(0, idx), pair.substring(idx + 1).trim());
            	}
            } catch (Exception e) {
              LOGGER.warn("parseQuery error",e);
                return null;
            }
        }
        return query_pairs;
    }
    
    /**
     * Parse any POJO object to post data
     * @param object
     * @param clazz
     * @return
     */
    public static String parseObjectTopostData(Object object, Class<?> clazz) {
    	String postData = "";
    	
    	try {
    		Method[] methods = clazz.getDeclaredMethods();
    		Object val;
    		for (Method method : methods) {
    			if (method.getName().startsWith("get")) {
    				if (!postData.equals("")) {
    					postData += "&";
    				}
    				postData += method.getName().replace("get", "").toLowerCase();
    				val = method.invoke(object);
    				if (val == null) {
    					val = "";
    				}
    				postData += "=" + val;
    			}
    		}
    		return postData;
    	} catch (Exception e) { 
    		return null;
    	}
    }
}
