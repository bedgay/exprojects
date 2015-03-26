<?php

/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 28/09/2013
 */
final class UserBusiness {
	private static $instance = null;
	
	const SECURITY_CODE = 'SECURITY_CODE';	
	/**
	 * @var UserDAO
	 */
	private $userDAO;
	
	/**
	 * UserBusiness instance only
	 * @return UserBusiness
	 */
	public static function getInstance() {
		if (self::$instance == null)
			self::$instance = new UserBusiness();
		return self::$instance;
	}
	
	/**
	 * Constructor
	 */
	private function UserBusiness() {
		$this->userDAO = UserDAO::getInstance();
	}

	/**
	 * Generate new security code
	 * @return string
	 */
	public function newSecurityCode() {
		$code = String::addSpace(String::random(8));
		$_SESSION[self::SECURITY_CODE] = $code; // Will check this code
		return $code;
	}

	/**
	 * Check security code
	 * @param string $code
	 * @return boolean
	 */
	public function checkSecurityCode($code) {
		if (isset($_SESSION[self::SECURITY_CODE]) &&
					String::addSpace(strtoupper($code)) == $_SESSION[self::SECURITY_CODE]) {
			return true;	
		}
		return false;
	}

	/**
	 * Check User login
	 * @param string $username
	 * @param string $password
	 * @return boolean
	 */
	public function checkUserLogin($username, $password) {
		$user = new User($username);
		$user = $this->userDAO->selectById($user);
		
		if ($user != null && String::md5($password) == $user->password) {
			$user->password = '';
			$session = new Object();
			$session->USER = $user;
			$_SESSION[Dispatcher::USER_SESSION] = serialize($session);
			return true;
		}
		return false;
	}

	/**
	 * Remove security code
	 */
	public function removeSecurityCode() {
		unset($_SESSION[self::SECURITY_CODE]);
	}
	
}
?>