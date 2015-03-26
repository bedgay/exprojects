<?php

$PATH = 'bonsai/common';
require_once "$PATH/file/File.php";
require_once "$PATH/file/Image.php";
require_once "$PATH/util/Collection.php";

$PATH = 'exam';
require_once "$PATH/model/entity/User.php";
require_once "$PATH/model/dao/UserDAO.php";
require_once "$PATH/business/UserBusiness.php";

require_once "exam/controller/SecurityController.php";

/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 28/09/2013
 */
final class UserController extends SecurityController {
	
	const VIEW_PATH = 'exam/view/user/';
	const NAME = 'user';
	const SECURITY_IMAGE = "exam/resource/images/security.png";
	
	private $userService;
	
	public function UserController () {
		parent::SecurityController();
		$this->userService = UserBusiness::getInstance();
	}

	protected function checkSecurity() { }

	/**
	 * Generate security image
	 * @action(security)
	 */
	public function security() {
		$text = $this->userService->newSecurityCode();
		
		$image = imagecreatefrompng(self::SECURITY_IMAGE);
		$color = imagecolorallocate($image, 255, 255, 255);
		imagestring($image, 7, 7, 6,  $text, $color);

		header('Content-Type: image/png');
		imagepng($image);
		imagedestroy($image);
		exit();
	}

	/**
	 * User login action
	 * @param string $username
	 * @param string $password
	 * @param string $code
	 * @action(login)
	 */
	public function login($username = null, $password = null, $code = null) {
		$msg	= '';
		
		// NoPass:bbb342d0df6afa927cc873e08d9b3a4e
		// EmptyPass:16ff531df76765f8d1c7bd3c2dbd8508
		if (!HTTP::isGet()) {
			// Check security code
			if ($this->userService->checkSecurityCode($code)) {
				// Check user 
				if ($this->userService->checkUserLogin($username, $password)) {
					$this->userService->removeSecurityCode();
					// Login successfully
					$this->redirect('cpanel', 'cats');
				}	
			}
			$msg = 'Login failed!';
		}
		// Login error
		require_once self::VIEW_PATH . 'login.html';
	}
	
	/**
	 * User login action
	 * @action(logout)
	 */
	public function logout() {
		session_destroy();
		$this->redirect('user', 'login');
	}
	
}
?>