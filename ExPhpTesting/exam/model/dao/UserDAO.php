<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 28/09/2013
 */
final class UserDAO extends DAO {
	private static $instance = null;
	
	/**
	 * UserDAO instance only
	 * @return UserDAO
	 */
	public static function getInstance() {
		if (self::$instance == null)
			self::$instance = new UserDAO("User");
		return self::$instance;
	}
}
?>