/**
 * 
 */
package jp.co.mti.mixjuke.web.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * @author Xuan Nguyen
 * 
 */
public class MixjukeUtils {
    private static Charset SIG_ENCODING = Charset.forName("UTF-8");
    private static String SIG_ALGORITHM = "HmacSHA256";

    public static String getSignature(String srcString, String secretKey)
            throws InvalidKeyException, NoSuchAlgorithmException {
        Key key = new SecretKeySpec(secretKey.getBytes(SIG_ENCODING),
                SIG_ALGORITHM);
        Mac mac = Mac.getInstance(key.getAlgorithm());
        mac.init(key);
        byte[] srcBytes = srcString.getBytes(SIG_ENCODING);
        byte[] bs = mac.doFinal(srcBytes);
        return new String(Base64.encodeBase64(bs), SIG_ENCODING);
    }

    public static String getBase64URLEncoderString(String srcString)
            throws UnsupportedEncodingException {

        byte[] srcBytes = srcString.getBytes();
        String base64Str = new String(Base64.encodeBase64(srcBytes));
        return getURLEncoderString(base64Str);
    }

    public static String getURLEncoderString(String srcString)
            throws UnsupportedEncodingException {
        return URLEncoder.encode(srcString, "UTF-8");
    }

    public static String getTimeStampString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        return sdf.format(new Date());
    }

    private static String randomString(String chars, int length) {
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();
    }

    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private static String getHexSHA256(String data) {
        return DigestUtils.sha256Hex(data);
    }

    public static String getTransactionString() {
        String randomString = randomString("abcdefghijklmnopqrstuvwxyz",
                randInt(1, 64));
        return getHexSHA256(randomString);
    }

    public static void saveOrUpdateCookie(HttpServletRequest request,
            HttpServletResponse response, String domain, String name,
            String value) {
        Cookie cookies[] = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals(name)) {
                c.setMaxAge(0);// delete
                c.setValue(value);
                break;
            }
        }

        Cookie cookie = new Cookie(name, value);
        int expiry = 5256000;// 2 months
        cookie.setMaxAge(expiry);
        cookie.setPath("/");
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    public static Object getObjectFromCookie(HttpServletRequest request,
            HttpServletResponse response, String name) {
        Cookie cookies[] = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals(name)) {
                return c.getValue();
            }
        }
        return null;
    }

    public static void saveOrUpdateSession(HttpServletRequest request,
            HttpServletResponse response, String name, Object object) {

        HttpSession session = request.getSession();
        session.setAttribute(name, object);
    }

    public static Object getObjectFromSession(HttpServletRequest request,
            HttpServletResponse response, String name) {

        HttpSession session = request.getSession();
        return session.getAttribute(name);
    }

    public static boolean acctIdIsCorrectFormat(String acctID) {
        if (StringUtils.isBlank(acctID)) {
            return false;
        }

        if (!Pattern.matches("^[a-zA-Z0-9.]{6,30}$", acctID)) {
            return false;
        }

        if (acctID.indexOf("..") != -1) {
            return false;
        }
        if (acctID.startsWith(".") || acctID.endsWith(".")) {
            return false;
        }
        if (StringUtils.countMatches(acctID, acctID.substring(0, 1)) == acctID
                .length()) {
            return false;
        }
        return true;
    }

    public static boolean passwordIsCorrectFormat(String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        if (!Pattern.matches("^[a-zA-Z0-9._-]{4,40}$", password)) {
            return false;
        }
        if (StringUtils.countMatches(password, password.substring(0, 1)) == password
                .length()) {
            return false;
        }
        if (NumberUtils.isNumber(password)
                && !password.matches("^[0-9]{7,40}$")) {
            return false;
        }

        return true;
    }

    public static boolean mailAddrIsCorrectFormat(String mailAddr) {
        if (StringUtils.isBlank(mailAddr)) {
            return false;
        }
        if (!Pattern
                .matches(
                        mailAddr,
                        "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.+[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*(\\.+)?@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$")) {
            return false;
        }
        return true;
    }

    public static boolean telephoneIsCorrectFormat(String telephone) {
        if (!Pattern.matches("^[0-9]{9,14}$", telephone)) {
            return false;
        }
        return true;
    }

    public static boolean expireIsCorrectFormat(int expireInMinute) {
        return expireInMinute >= 0 && expireInMinute <= 120;
    }

    public static boolean birdthdayIsCorrectFormat(String birdthday) {
        if (!Pattern.matches(
                "((19|20)\\d\\d)(0?[1-9]|[12][0-9]|3[01])(0?[1-9]|1[012])",
                birdthday)) {
            return false;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = formatter.parse("19000101");
            if (formatter.parse(birdthday).compareTo(date) < 0
                    || formatter.parse(birdthday).compareTo(new Date()) > 0) {
                return false;
            }
        } catch (ParseException e) {
            System.out.println("Parse Date error");
            return false;
        }
        return true;
    }

    public static boolean sireIdIsCorrectFormat(String siteID) {
        return true;
    }

    public static boolean transactionIsCorrectFormat(String transaction) {
        return true;
    }

    public static boolean ifidsIsCorrectFormat(String ifids) {
        return true;
    }

    public static boolean tokenIsCorrectFormat(String tokenID) {
        return true;
    }

    public static boolean muidIsCorrectFormat(String muid) {
        return true;
    }

    public static boolean ridIsCorrectFormat(String rid) {
        return true;
    }

    public static boolean argIsCorrectFormat(String arg) {
        return true;
    }
}
