<?php
/**
 * Controller Dispatcher
 * @author Anh Tu Ngo
 * @date 11/30/2011
 */
final class Dispatcher {
	const CONTROLLER_KEY = 'c';
	const ACTION_KEY	 = 'a';
	const USER_SESSION	 = 'user_session';
	const DEFAULT_PERMIS = 0;

	const ERROR_PERMIS_DENY	= 'Permission deny.';
	const ERROR_WRONG_URL	= 'The url is wrong.';
	
	/**
	 * Go to the action
	 */
	public static function go() {
		$controller	= null;
		$action		= null;
		$msg		= '';
		
		// Get controller from url
		if (isset($_REQUEST[self::CONTROLLER_KEY]))
			$controller = $_REQUEST[self::CONTROLLER_KEY];
			
		// Get action from url
		if (isset($_REQUEST[self::ACTION_KEY]))
			$action = $_REQUEST[self::ACTION_KEY];
		
		if ($controller != null && $action != null) {
			// Import enveroment configuration
			require_once 'bonsai/controller-env.php';
			if (isset($CONFIG_ENV->$controller)) {
				$module = $CONFIG_ENV->$controller; // <= $CONFIG_ENV->user = 'users'
				
				// Import enveroment configuration of module
				require "$module/$module-env.php"; // Must use require() because It will import again in other action
				if (isset($CONFIG_ENV->$controller)) {
					$config		= $CONFIG_ENV->$controller; // <= $CONFIG_ENV->user = new Object()
					$className	= $config->CLASS_NAME; // <= $CONFIG_ENV->user->CLASS_NAME = 'UserController'
					
					if (isset($config->$action)) {
						$action		  = $config->$action; // <= $CONFIG_ENV->user->login = new Object()
						$methodName	  = $action->METHOD; // <= $CONFIG_ENV->user->login->METHOD = 'login'
						// $CONFIG_ENV->user->login->PERMIS = 0 // =>
						$actionPermis = isset($action->PERMIS) ? $action->PERMIS : self::DEFAULT_PERMIS;
						$userPermis	  = self::DEFAULT_PERMIS;
						
						// Get User permission
						if ($actionPermis != self::DEFAULT_PERMIS)
							$userPermis = self::getUserPermis($module);
						
						// Check permission and invock the action
						if ($userPermis >= $actionPermis) {
							require_once("$module/controller/$className.php");
							self::invocke($className, $methodName);
						} else
							$msg = self::ERROR_PERMIS_DENY;
					} else
						$msg = self::ERROR_WRONG_URL;
				} else
					$msg = self::ERROR_WRONG_URL;
			} else
				$msg = self::ERROR_WRONG_URL;
		} else
			$msg = self::ERROR_WRONG_URL;
		
		echo $msg;
	}
	
	/**
	 * Get User permission
	 * @param string $module Module key
	 * @return number
	 */
	private static function getUserPermis($module) {
		if (isset($_SESSION[self::USER_SESSION])) {
			$session = unserialize($_SESSION[self::USER_SESSION]);
			if (isset($session->MODULES)) {
				$modules = $session->MODULES;
				if (isset($modules->$module)) 
					return $modules->$module;
			}
		}
		return self::DEFAULT_PERMIS;
	}
	
	/**
	 * Invoke the action
	 * @param string $className
	 * @param string $methodName
	 */
	private static function invocke($className, $methodName) {
		$paramConstruc = self::getParameters($className, $methodName);
		$params = array();
		$paramStr = '';
		
		$i = 0;
		foreach ($paramConstruc as $construc) {
			if (isset($_REQUEST[$construc->name]))
				$params[$i] = $_REQUEST[$construc->name];
			else if (isset($_FILES[$construc->name]))
				$params[$i] = $_FILES[$construc->name];
			else {
				if ($construc->isOptional())
					$params[$i] = $construc->getDefaultValue();
				else
					$params[$i] = null;
			}
				
			$paramStr .= ',' . "\$params[$i]"; 
			$i++;
		}
		$paramStr = substr($paramStr, 1);
		
		eval("\$ctrl = new $className();");
		eval("\$ctrl = \$ctrl->$methodName($paramStr);");
	}
	
	/**
	 * Get parameters of action method
	 * @param string $className
	 * @param string $methodName
	 * @return array<ReflectionParameter>
	 */
	private static function getParameters($className, $methodName) {
		$method = new ReflectionMethod($className, $methodName);
		$params = $method->getParameters();
		return $params;
	}
}
?>