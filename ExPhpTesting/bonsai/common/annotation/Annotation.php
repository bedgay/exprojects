<?php
/**
 * Get Annotation config from all comments of the class
 * @author ngoanhtu
 * @date 11/21/2011
 * For example:
 * @key1(value1)
 * @key2(value2) . . .
 */

abstract class Annotation {
	
	/**
	 * Get Annotation config of class
	 * @param string $class Class name
	 * @return array<key, value>
	 */
	protected function getOfClass($class) {
		$class = new ReflectionClass($class);
		return $this->parse($class->getDocComment());
	}
	
	/**
	 * Get Annotation config of all methods
	 * @param string $class Class name
	 * @return array<methodName, array<key, value>>
	 */
	protected function getOfMethods($class) {
		$result = array();
		
		$methods = get_class_methods($class);
		foreach ($methods as $name => $value) {
			$result[$name] = $this->getOfMethod($class, $value);
		}
		
		return $result;		
	}

	/**
	 * Get Annotation config of a method name
	 * @param string $class Class name
	 * @param string $method Method name
	 * @return array<key, value>
	 */
	protected function getOfMethod($class, $method) {
		$method = new ReflectionMethod($class, $method);
		return $this->parse($method->getDocComment());
	}
	
	/**
	 * Get Annotation config of all properties
	 * @param String $class
	 * @return array<propertyName, array<key, value>>
	 */
	protected function getProperties($class) {
		$result = array();
		
		$vars = get_class_vars($class);
		foreach ($vars as $name => $value) {
			$result[$name] = $this->getProperty($class, $name);
		}
		
		return $result;
	} 
	
	/**
	 * Get Annotation of a method
	 * @param string $class Class name
	 * @param string $property Property name
	 * @return array<key, value>
	 */
	protected function getProperty($class, $property) {
		$property = new ReflectionProperty($class, $property);
		return $this->parse($property->getDocComment());
	}
	
	/**
	 * Parse the Annotation data
	 * @param string $comment All comment of
	 * @return array<key, value>
	 */
	private function parse($comment) {
		$result = array();  
		
		$token1 = strpos($comment, "@", 0);
		$token2 = 0;
		$token3 = 0;
		while($token1 > 0) {
			$token2 = strpos($comment, "(", $token1);
			$token3 = strpos($comment, ")", $token2);
			
			$beforeToken2 = substr($comment, 0, $token2);
			$token4 = strrpos($comment, '@');
			
			if ($token4 > $token1 && $token4 < $token2) {
				$token1 = $token4;
			}
			
			if ($token3 > 0) {
				$result[substr($comment, $token1 + 1, $token2 - $token1 - 1)] = substr($comment, $token2 + 1, $token3 - $token2 - 1);
				$token1 = strpos($comment, "@", $token3++);
			} else {
				break;
			}
		}
		
		return $result;
	}
}
?>