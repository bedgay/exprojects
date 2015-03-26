<?php
/**
 * Enter description here ...
 * @author Anh Tu Ngo
 * @date 13/01/2012
 */
final class Date {
	
	/**
	 * Convert string date to timestame
	 * @param string $strDateTime format: yyyy-mm-dd hh:ii:ss 
	 * @return number
	 */
	public static function toTimestame($strDateTime) {
		$date = getdate(strtotime($strDateTime));
		return $date[0];
	}
	
	/**
	 * Get timestame
	 * @param number $yyyy
	 * @param number $mm
	 * @param number $dd
	 * @param number $hh
	 * @param number $ii
	 * @param number $ss
	 * @return number
	 */
	public static function getTimestame($yyyy, $mm, $dd, 
										$hh = 0, $ii = 0, $ss = 0) {
		return date(mktime($hh, $ii, $ss, $mm, $dd, $yyyy));
	}
	
	/**
	 * Get current date
	 * @param string $format yyyy-mm-dd hh:ii:ss
	 * @return string yyyy-mm-dd hh:ii:ss
	 */
	public static function now($format = null) {
		return date($format == null ? "Y-m-d H:i:s" : $format, time());
	}
	
	/**
	 * Get current timestame
	 * @param string $format yyyy-mm-dd hh:ii:ss
	 * @return number
	 */
	public static function time($format = null) {
		if ($format == null) {
			return time();
		} 
		return self::toTimestame(self::now($format));
	}
	
	/**
	 * Get date from timestame
	 * @param number $timestame
	 * @return string yyyy-mm-dd hh:ii:ss
	 */
	public static function toDate($timestame) {
		return date("Y-m-d H:i:s", $timestame);
	}

	/**
	 * Get date
	 * @param number $yyyy
	 * @param number $mm
	 * @param number $dd
	 * @param number $hh
	 * @param number $ii
	 * @param number $ss
	 * @return string
	 */
	public static function getDate($yyyy, $mm, $dd, 
										$hh = 0, $ii = 0, $ss = 0) {
		return date('Y-m-d H:i:s', mktime($hh, $ii, $ss, $mm, $dd, $yyyy));
	}
	
	/**
	 * Enter description here ...
	 * @param string $str
	 * @param string $pattern
	 * @return string
	 */
	public static function formatToDate($str, $pattern = 'm/d/Y') {
		if ($pattern == 'm/d/Y') {
			if (!String::isEmpty($str) && $str != '0000-00-00 00:00:00') {
				$arr = @split('[/]', $str);
				return date('Y-m-d H:i:s', mktime(0, 0, 0, $arr[0], $arr[1], $arr[2]));
			} else {
				return null;
			}
		} else {
			// TODO . . .		
			return '';	
		}
	}

	/**
	 * Enter description here ...
	 * @param string $str
	 * @param string $pattern
	 * @return string
	 */
	public static function formatToString($str, $pattern = 'm/d/Y') {
		if ($pattern == 'm/d/Y') {
			if (!String::isEmpty($str) && $str != '0000-00-00 00:00:00') {
				$arr = @split('[-]', $str);
				return @date('m/d/Y', mktime(0, 0, 0, $arr[1], $arr[2], $arr[0]));
			} else {
				return null;
			}
		} else {
			// TODO . . .		
			return '';	
		}
	}
	
	/*
	$date="30/07/2010 13:24"; //date example
	list($day, $month, $year, $hour, $minute) = split('[/ :]', $date); 
	//the variables should be arranged acording to your date format and so the separators
	$timestamp=mktime($hour, $minute,0, $month, $day, $year);
	echo date("r", $timestamp);
	*/
	
}
?>