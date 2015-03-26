<?php
/**
 * All method for html
 * @author Anh Tu Ngo
 * @date 12/03/2011
 */
final class HTTP {
	
	const GET_REQUEST_METHOD	= 'GET';
	const POST_REQUEST_METHOD	= 'POST';
	
	/**
	 * Check request get method
	 * @return boolean
	 */
	public static function isGet() {
		return $_SERVER['REQUEST_METHOD'] == Http::GET_REQUEST_METHOD;
	}
	
	/**
	 * Gemerate the url
	 * @param string $controller Controller name
	 * @param string $action Action name
	 * @param string $params Query string paramters
	 * @return string URL
	 */
	public static function url($controller, $action, $params = null) {
		$url = (!empty($_SERVER['HTTPS'])) ? "https://".$_SERVER['SERVER_NAME'].$_SERVER['REQUEST_URI'] : "http://".$_SERVER['SERVER_NAME'].$_SERVER['REQUEST_URI'];
		$url = preg_split('/[\?]/', $url);
		return $url[0] . "?c=$controller&a=$action" . self::getParams($params);
	}
	/**
	 * Enter description here ...
	 * @param string $params
	 * @return string
	 */
	private static function getParams($params) {
		$lan = isset($_REQUEST['lan']) ? ('&lan=' . $_REQUEST['lan']) : null;
		if ($params != null) {
			return ($lan != null) ? ($lan . "&$params") : "&$params";
		}
		return ($lan != null) ? $lan : '';
	}
}
?>