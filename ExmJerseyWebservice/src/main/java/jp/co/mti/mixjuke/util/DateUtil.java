package jp.co.mti.mixjuke.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Date/Time convert methods
 * @author natu
 *
 */
public class DateUtil {

	public static final String SQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	

	/**
	 * Parse Date string value to Date objewct
	 * @param val
	 * @param format Ex: yyyyMMDDHHmmss
	 * @return Date
	 * @throws ParseException
	 */
	public static Date stringToDate(String val, String format) throws ParseException {
		DateFormat f = new SimpleDateFormat(format);
		return f.parse(val);
	}

	/**
	 * Parse Date string value to Date objewct
	 * @param val
	 * @param format Ex: yyyyMMDDHHmmss
	 * @param timeZone Ex: GMT+9
	 * @return Date
	 * @throws ParseException
	 */
	public static Date stringToDate(String val, String format, String timeZone) throws ParseException {
		DateFormat f = new SimpleDateFormat(format);
		f.setTimeZone(TimeZone.getTimeZone(timeZone));
		return f.parse(val);
	}
	
	/**
	 * Format Date to String by format pattern
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * Format Date to String by format pattern
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(Date date) {
		return toString(date, SQL_DATE_FORMAT);
	}
	
	/**
	 * @param date
	 * @param value
	 * @param calendarType
	 * @return
	 */
	public static Date addDate(Date date, int value, int calendarType) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(calendarType, calendar.get(calendarType) + value);
	    return calendar.getTime();
	}
	/**
	 * Cut off the time information
	 * @param date the date with time information
	 * @return the date without time information
	 */
public static Date cutoffTime(Date date){
	Calendar cal = Calendar.getInstance(); // locale-specific
	cal.setTime(date);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	return  new Date(cal.getTimeInMillis());
}
}
