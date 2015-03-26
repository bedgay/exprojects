/**
 * 
 */
package jp.co.mti.mixjuke.web.test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import jp.co.mti.mixjuke.web.utils.MixjukeUtils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Xuan Nguyen
 * 
 */
public class MixjukeUtilsTest {

    @Test
    public void testRequestString1() {
        String input = "{\"iai_aver\":\"1.0\",\"iai_akey\":\"d20c3daceb6d07902e\",\"iai_atms\":\"20100917184601001\"}";
        String sig = null;
        try {
            sig = MixjukeUtils.getURLEncoderString(MixjukeUtils.getSignature(
                    input, "nYa3cKHrfTgAonxWvCqKKg=="));
        } catch (InvalidKeyException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        String req = null;
        try {
            req = MixjukeUtils.getBase64URLEncoderString(input);
        } catch (UnsupportedEncodingException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        Assert.assertEquals(
                "eyJpYWlfYXZlciI6IjEuMCIsImlhaV9ha2V5IjoiZDIwYzNkYWNlYjZkMDc5MDJlIiwiaWFpX2F0bXMiOiIyMDEwMDkxNzE4NDYwMTAwMSJ9",
                req);
        Assert.assertEquals("bYPgeNWB9NogEXWmPqmM1eGGF9AYKLyvQG1JTpfJWEg%3D",
                sig);

    }

    @Test
    public void testRequestString2() {
        String input = "{\"iai_aver\":\"1.0\",\"iai_akey\":\"724b4e0b54761d6eec\",\"iai_atms\":\"20100917184601001\"}";
        String sig = null;
        try {
            sig = MixjukeUtils.getURLEncoderString(MixjukeUtils.getSignature(
                    input, "zR7OdrFK41THGsijZwYjVQ=="));
        } catch (InvalidKeyException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        String req = null;
        try {
            req = MixjukeUtils.getBase64URLEncoderString(input);
        } catch (UnsupportedEncodingException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        Assert.assertEquals(
                "eyJpYWlfYXZlciI6IjEuMCIsImlhaV9ha2V5IjoiNzI0YjRlMGI1NDc2MWQ2ZWVjIiwiaWFpX2F0bXMiOiIyMDEwMDkxNzE4NDYwMTAwMSJ9",
                req);
        Assert.assertEquals(
                "g8E7OWKT9xUq2%2FUmrZ%2BiwtZ0v%2FKYLuVP1UrjaHxO8sQ%3D", sig);

    }

    @Test
    public void testRequestString3() {
        String input = "{\"iai_aver\":\"1.0\",\"iai_akey\":\"efc88d5b9a75106073\",\"iai_atms\":\"19700101121212001\",\"iai_foo\":\"abcdefg\",\"iai_bar\":\"12345\"}";
        String sig = null;
        try {
            sig = MixjukeUtils.getURLEncoderString(MixjukeUtils.getSignature(
                    input, "57t3zF/dotAFI9PSa7Qv6Q=="));
        } catch (InvalidKeyException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        String req = null;
        try {
            req = MixjukeUtils.getBase64URLEncoderString(input);
        } catch (UnsupportedEncodingException e) {
            Assert.assertTrue(false);
            e.printStackTrace();
        }

        Assert.assertEquals(
                "eyJpYWlfYXZlciI6IjEuMCIsImlhaV9ha2V5IjoiZWZjODhkNWI5YTc1MTA2MDczIiwiaWFpX2F0bXMiOiIxOTcwMDEwMTEyMTIxMjAwMSIsImlhaV9mb28iOiJhYmNkZWZnIiwiaWFpX2JhciI6IjEyMzQ1In0%3D",
                req);
        Assert.assertEquals(
                "IzbMeRL2z2rLR6ErgHXZe%2BodTg892c9k%2B9xydQk5NN0%3D", sig);

    }

    @Test
    public void testAcctIdIsCorrectFormat() {
        Assert.assertEquals(true,MixjukeUtils.acctIdIsCorrectFormat("xuan123"));
        Assert.assertEquals(true,MixjukeUtils.acctIdIsCorrectFormat("123XuaN"));
        Assert.assertEquals(true,MixjukeUtils.acctIdIsCorrectFormat("xUAn.123"));
        Assert.assertEquals(true,MixjukeUtils.acctIdIsCorrectFormat("xuannn"));
        Assert.assertEquals(true,MixjukeUtils.acctIdIsCorrectFormat("xuannnnnnnnnnnnnnnnnnnnnnnnnnn"));//30character
        
        Assert.assertEquals(false,MixjukeUtils.acctIdIsCorrectFormat(""));
        Assert.assertEquals(false,MixjukeUtils.acctIdIsCorrectFormat(null));
        Assert.assertEquals(false,MixjukeUtils.acctIdIsCorrectFormat(" "));
        Assert.assertEquals(false,MixjukeUtils.acctIdIsCorrectFormat(".xuan123"));
        Assert.assertEquals(false,MixjukeUtils.acctIdIsCorrectFormat("xuan123."));
        Assert.assertEquals(false,MixjukeUtils.acctIdIsCorrectFormat("xuan..123"));
        Assert.assertEquals(false,MixjukeUtils.acctIdIsCorrectFormat("xuan.@123"));
        Assert.assertEquals(false,MixjukeUtils.acctIdIsCorrectFormat("xuan.#123"));
        Assert.assertEquals(false,MixjukeUtils.passwordIsCorrectFormat("xua字nnnn"));
        Assert.assertEquals(false,MixjukeUtils.acctIdIsCorrectFormat("nnnnnnnnnn"));
        Assert.assertEquals(false,MixjukeUtils.acctIdIsCorrectFormat("xuann"));
        Assert.assertEquals(false,MixjukeUtils.acctIdIsCorrectFormat("xuannnnnnnnnnnnnnnnnnnnnnnnnnnn"));
    }

    @Test
    public void testPasswordIsCorrectFormat() {
        Assert.assertEquals(true,MixjukeUtils.passwordIsCorrectFormat("xuan123"));
        Assert.assertEquals(true,MixjukeUtils.passwordIsCorrectFormat("123XuaN"));
        Assert.assertEquals(true,MixjukeUtils.passwordIsCorrectFormat("xUAn.-_123"));
        Assert.assertEquals(true,MixjukeUtils.passwordIsCorrectFormat("1234567"));
        Assert.assertEquals(true,MixjukeUtils.passwordIsCorrectFormat("xuan"));
        Assert.assertEquals(true,MixjukeUtils.passwordIsCorrectFormat("xuannnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"));//40character
        
        Assert.assertEquals(false,MixjukeUtils.passwordIsCorrectFormat(""));
        Assert.assertEquals(false,MixjukeUtils.passwordIsCorrectFormat(null));
        Assert.assertEquals(false,MixjukeUtils.passwordIsCorrectFormat(" "));
        Assert.assertEquals(false,MixjukeUtils.passwordIsCorrectFormat("xuan@123"));
        Assert.assertEquals(false,MixjukeUtils.passwordIsCorrectFormat("1234"));
        Assert.assertEquals(false,MixjukeUtils.passwordIsCorrectFormat("xuan.#123"));
        Assert.assertEquals(false,MixjukeUtils.passwordIsCorrectFormat("xua字nnnn"));
        Assert.assertEquals(false,MixjukeUtils.passwordIsCorrectFormat("xua"));
        Assert.assertEquals(false,MixjukeUtils.passwordIsCorrectFormat("xuannnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"));
    }
}
