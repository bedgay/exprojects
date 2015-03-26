<?php
/**
 * Enter description here ...
 * @author Anh Tu Ngo
 * @date 02/19/2012
 */
final class Collection {
	
	/**
	 * Convert array to list
	 * @param array<Object> $array
	 * @param string $keyName
	 * @param string $valName
	 * @return List
	 */
	public static function arrayToList($array, $keyName, $valName = null) {
		$list = array();
		
		foreach ($array as $obj) {
			$key = $obj->$keyName;
			$list[$key] = ($valName == null) ? $obj : $obj->$valName;
		}
		
		return $list;
	}
	
}
?>