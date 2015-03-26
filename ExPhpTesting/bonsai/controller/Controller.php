<?php
/**
 * Abstract Controller object
 * @author Anh Tu Ngo
 * @date 11/30/2011 Create
 */
abstract class Controller {
	
	const ERROR				= 0;
	const SUCCESS			= 1;
	const EXISTENCE			= 2;
	
	const VIEW_PATH			= 'bonsai/view/';
	const REFERER			= 'REFERER';
	const WAITTING			= 1;
	
	const LANG_DEFAULT		= 'default';
	const LANGUAGE			= 'LANGUAGE';
	const LANG_RESOURCE		= 'languages/resource/';
	
	/**
	 * Constructor
	 */
	public function Controller() { }
	
	/**
	 * Load Language detail
	 */
	protected function useLang() {
		// Support for Language choose
		$code = isset($_COOKIE[self::LANGUAGE]) ? $_COOKIE[self::LANGUAGE] 
												: self::LANG_DEFAULT;
		@include self::LANG_RESOURCE . $code . '.php';
	}
	
	/**
	 * Back to referer url
	 * @param string $msg
	 * @param number $secomd
	 */
	public function referer($msg = '', $second = self::WAITTING) {
		$url = $_SERVER["HTTP_REFERER"];
		require_once self::VIEW_PATH . 'referer.html';
		exit(0);
	}
	
	/**
	 * Redirect to URL
	 * @param string $url
	 */
	protected function direct($url) {
		$second = 0;
		$msg = '';
		require_once self::VIEW_PATH . 'referer.html';
		exit(0);
	}
	
	/**
	 * Redirect to other action
	 * @param string $controller Controller name
	 * @param string $action Action name
	 * @param string $params Query string paramters
	 */
	protected function redirect($controller, $action, $params = null) {
		$url = HTTP::url($controller, $action, $params);
		echo "<meta http-equiv='refresh' content='0;url=$url'>";
		exit(0);  
	}
	
	/**
	 * Go to an other action with message view
	 * @param string $controller
	 * @param string $action
	 * @param string $params
	 * @param string $msg
	 * @param string $second
	 */
	protected function msg($controller, $action, $params = null, $msg = '', $second = self::WAITTING) {
		$url = HTTP::url($controller, $action, $params);
		require_once self::VIEW_PATH . 'msg.html';
		exit(0);
	}
	
	/**
	 * Display the contents of URL
	 * @param string $controller
	 * @param string $action
	 * @param string $params
	 */
	protected function display($controller, $action, $params = null) {
		echo file_get_contents(HTTP::url($controller, $action, $params));
	}
		
}
?>