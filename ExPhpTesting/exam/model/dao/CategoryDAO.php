<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 *
 */
final class CategoryDAO extends DAO {
	private static $instance = null;
	
	/**
	 * CategoryDAO instance only
	 * @return CategoryDAO
	 */
	public static function getInstance() {
		if (self::$instance == null)
			self::$instance = new CategoryDAO("Category");
		return self::$instance;
	}
}
?>