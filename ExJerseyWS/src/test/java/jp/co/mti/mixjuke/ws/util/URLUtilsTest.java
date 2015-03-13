package jp.co.mti.mixjuke.ws.util;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
/**
 * User: qcmb
 * Date: 11/19/13
 * Time: 8:36 AM
 */
public class URLUtilsTest {
    /**
     * Test parseQuery method with null input
     * Input: query = null
     * Expected result: null
     */
    @Test
   public void testParseQueryNull(){
        Map<String, String> out = URLUtils.parseQuery(null);
        assertNull(out);
    }

    /**
     * Test parseQuery method with empty query input
     * Input: query = empty string.
     * Expected result: null.
     */
    @Test
    public void testParseQueryEmpty(){
        Map<String, String> out = URLUtils.parseQuery("");
        assertNull(out);
    }
    /**
     * Test parseQuery method with exception case
     * Input: Invalid query string (a=1&b&=2)
     * Exptected result:null.
     */
    @Test
    public void testParseQueryException(){
        Map<String, String> out = URLUtils.parseQuery("a=1&b&=2");
        assertNull(out);
    }

    /**
     * Test parseQuery method with OK case
     * Input: a=1&b=2
     * Expected result: The output map has two key-value pairs.
     */
    @Test
    public void testParseQueryOK(){
        Map<String, String> out = URLUtils.parseQuery("a=1&b=2");
        assertEquals(out.size(),2);
    }
}
