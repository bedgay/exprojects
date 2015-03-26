<?php
/**
 * String util
 * @author Anh Tu Ngo
 * @date 12/03/2011
 */
final class String {
	
	/**
	 * Enter description here ...
	 * @param string $str
	 * @return boolean
	 */
	public static function isEmpty($str) {
		return $str == '' || $str == null;
	}
	
	/**
	 * Random string
	 * @param number $lenght
	 * @return string
	 */
	public static function random($lenght = 8) {
		$time = microtime();
		return strtoupper(substr(md5("random($time)"), 0, $lenght));
	}
	
	/**
	 * Add space to any charactor
	 * @param string $string
	 * @return string
	 */
	public static function addSpace($string) {
		$str = '';
		
		for ($i = 0; $i < strlen($string); $i ++) {
			$str .= ($i > 0 ? ' ' : '') . $string[$i];
		}
		
		return $str;
	}
	
	/**
	 * MD5 encoding
	 * @param string $string
	 * @return string
	 */
	public static function md5($string) {
		return md5("md5($string);");
	}
	
	/**
	 * Replace string with a charactor
	 * @param string $string
	 * @param char $char
	 * @param number $beginIndex
	 * @return string
	 */
	public static function replaceWith($string, $char, $beginIndex = 0) {
		$str = $string;
		
		$len	= strlen($string);
		if ($len > $beginIndex) {
			$str = substr($string, 0, $beginIndex);
			for ($i = $beginIndex; $i < $len; $i++)
				$str .= $char; 
		}
		
		return $str;
	}
	
	public static function wrap($str, $limit = 10) {
		$str = strip_tags($str);
		$strNext = " ...";
		$array = str_word_count(strip_tags($str), 1, '0..3');
		
		if (sizeof($array) <= $limit) {
			$limit = sizeof($array);
			$strNext = "";
		}
		
		$str = '';
		for ($i = 0; $i < $limit; $i++) {
			$str .= ' ' . $array[$i];
		}
		return $str . $strNext;
	}
}
?>