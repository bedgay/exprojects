<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 *
 */
abstract class SecurityController extends Controller {
	
	protected $TEMPLATE;
	
	public function SecurityController() {
		parent::Controller();
		$this->TEMPLATE = "exam/template/blue";
		$this->checkSecurity();
	} 
	
	protected function import() {
		echo '<link type="text/css" href="' . $this->TEMPLATE . '/css/layout.css' . '" rel="stylesheet"/>';
		echo '<script type="text/javascript" src="' . $this->TEMPLATE . '/js/jquery-1.7.1.min.js"></script>';
	}
	
	protected function richtext() {
		echo '<link type="text/css" href="' . $this->TEMPLATE . '/js/jquery-te-1.4.0.css' . '" rel="stylesheet"/>';
		echo '<script type="text/javascript" src="' . $this->TEMPLATE . '/js/jquery-te-1.4.0.min.js"></script>';
	}	
	
	protected function header() {
		require_once $this->TEMPLATE . '/header.html';
	}
	
	protected function footer() {
		require_once $this->TEMPLATE . '/footer.html';		
	}
	
	protected abstract function checkSecurity();
	
}
?>