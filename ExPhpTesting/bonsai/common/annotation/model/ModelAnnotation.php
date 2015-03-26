<?php
/**
 * Get Annotation configuration for any Entity
 * @author Anh Tu Ngo
 * @date 11/21/2011 Create
 */
class ModelAnnotation extends Annotation {
	
	const TABLE		= "table";
	const FIELDS	= "fields";
	const COLUMN	= "column";
	const TYPE		= "type";
	const UNIQUE	= "unique";
	const LENGHT	= "lenght";
	const GENERATOR = "generator";
	const KEY		= "key";
	const SESSION	= "ModelAnnotation";
	
	/**
	 * Get all annotation configuration of the Entity
	 * @param string $class Class name
	 * @return array<key, object>
	 * For detail:
	 * array<table, table name>
	 * array<fields, array<key, value>>
	 */
	public function getAll($class) {
		$result = array();
/*
		$currentSessionId = session_id();
		session_commit();
		session_id(self::SESSION);
		session_start();

		if (!isset($_SESSION[$class])) {
			$result = $this->getOfClass($class);
			//$result[self::TABLE] = $result[self::TABLE];
			//$result[self::GENERATOR] = $result[self::GENERATOR];
			$result[self::FIELDS]= $this->getProperties($class);
			$_SESSION[$class] = $result;
		} else {
			$result = $_SESSION[$class];
		}

		session_commit();
		session_id($currentSessionId);
		session_start();
		*/
		
		$result = $this->getOfClass($class);
		//$result[self::TABLE] = $result[self::TABLE];
		//$result[self::GENERATOR] = $result[self::GENERATOR];
		$result[self::FIELDS]= $this->getProperties($class);
		
		return $result;
	}
	
}
?>