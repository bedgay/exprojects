<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 27/09/2013
 */
final class SectionDAO extends DAO {
	private static $instance = null;
	
	/**
	 * SectionDAO instance only
	 * @return SectionDAO
	 */
	public static function getInstance() {
		if (self::$instance == null)
			self::$instance = new SectionDAO("Section");
		return self::$instance;
	}
}
?>