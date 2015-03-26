<?php
/**
 * Data Access Object 
 * @author Anh Tu Ngo
 * @date 11/21/2011 Create
 * @date 11/23/2011 Improve func: insert(), update()
 */
abstract class DAO {
	
	const STRING	= "string";
	const NUMBER	= "number";
	const DATETIME	= "datetime";
	
	const ASC		= 'asc';
	const DESC		= 'desc';
	
	const TABLE			= "@table";
	const INSERT_FIELDS = "@insert_fields";
	const INSERT_VALUE	= "@insert_values";
	const UPDATE_VALUE	= "@update_values";
	const CONDICTION	= "@condition";
	const ORDER_BY		= "@order_by";
	const LIMIT			= "@limit";
	
	const SIZE			= 'size';
	const PAGES			= 'pages';
	
	const PAGE_SIZE		= 20;
	const MAX_SIZE		= 18000;
	const FIRST_PAGE	= 1;
	
	const SQL_INSERT = "insert into @table(@insert_fields) values(@insert_values)";
	const SQL_DELETE = "delete from @table @condition";
	const SQL_UPDATE = "update @table set @update_values @condition";
	const SQL_SELECT = "select * from @table @condition @order_by @limit";
	const SQL_COUNT  = "select count(*) as `size` from @table @condition";
	
	/**
	 * The name of Entity object
	 * @var string
	 */
	protected $className = null;
	
	/**
	 * Entity configuration
	 * @var array<fields, array<key, value>>
	 */
	protected $config = null;
	
	/**
	 * Constructor
	 * @param $className The name of Entity object
	 * @param $isPersistent Flag of Persistent state
	 */
	protected function DAO($className = null, $isPersistent = true) {
		$this->className = $className;
		//$this->local($isPersistent);
		$this->server($isPersistent);
		//$this->hostinger($isPersistent);
	}
	
	private function server($isPersistent) {
		if ($isPersistent) {
			$connection	= mysql_pconnect('localhost', 'ocs_exam', '12345678@X') or die('Not connected: ' . mysql_error());
		} else {
			$connection	= mysql_connect('localhost', 'ocs_exam', '12345678@X') or die('Not connected: ' . mysql_error());
		}
		mysql_select_db('ocs_exam', $connection) or die ('Can\'t use [ocs_exam] db : ' . mysql_error());
		
		$modelAnno = new ModelAnnotation();
		$this->config	 = $modelAnno->getAll($this->className);
	}

	private function hostinger($isPersistent) {
		if ($isPersistent) {
			$connection	= mysql_pconnect('mysql.hostinger.vn', 'u788499180_ocs', '12345678@X') or die('Not connected: ' . mysql_error());
		} else {
			$connection	= mysql_connect('mysql.hostinger.vn', 'u788499180_ocs', '12345678@X') or die('Not connected: ' . mysql_error());
		}
		mysql_select_db('u788499180_ocs', $connection) or die ('Can\'t use [ocs] db : ' . mysql_error());
		
		$modelAnno = new ModelAnnotation();
		$this->config	 = $modelAnno->getAll($this->className);
	}

	private function local($isPersistent) {
		if ($isPersistent) {
			$connection	= mysql_pconnect('localhost', 'root', '') or die('Not connected: ' . mysql_error());
		} else {
			$connection	= mysql_connect('localhost', 'root', '') or die('Not connected: ' . mysql_error());
		}
		mysql_select_db('ocs', $connection) or die ('Can\'t use [ocs] db : ' . mysql_error());
		
		$modelAnno = new ModelAnnotation();
		$this->config	 = $modelAnno->getAll($this->className);
	}
	
	/**
	 * Add table name for SQL
	 * @param string $sql
	 */
	protected function addTable(& $sql) {
		$sql = str_replace(self::TABLE, $this->config[ModelAnnotation::TABLE], $sql);
	}
	
	/**
	 * Add insert fields name
	 * @param string $sql
	 */
	protected function addInsertFields(& $sql) {
		$fields = "";
		
		foreach($this->config[ModelAnnotation::FIELDS] as $field => $config) {
			if (isset($config[ModelAnnotation::COLUMN])) {
				$fields .= ',`' . $config[ModelAnnotation::COLUMN] . '`';
			}
		}
		
		$sql = str_replace(self::INSERT_FIELDS, substr($fields, 1), $sql);
	}
	
	/**
	 * Add insert values for sql
	 * @param string $sql
	 * @param Entity $entity
	 */
	protected function addInsertValues(& $sql, $entity) {
		$values = "";
		
		foreach($this->config[ModelAnnotation::FIELDS] as $field => $config) {
			if (isset($config[ModelAnnotation::GENERATOR]))
				$entity->$field = 'null';
				
			if (isset($config[ModelAnnotation::TYPE])) {
				if (strtolower($config[ModelAnnotation::TYPE]) == self::NUMBER) {
					$values .= ',' . $entity->$field;
				} else {
					$values .= ',' . '\'' . addcslashes($entity->$field, "'") . '\'';					
				}
			}
		}
		
		$sql = str_replace(self::INSERT_VALUE, substr($values, 1), $sql);
	}
	
	/**
	 * Add the condition for sql
	 * @param string $sql
	 * @param string $condition
	 */
	protected function addCondition(& $sql, $condition = false) {
		$sql = str_replace(self::CONDICTION, !$condition ? '' : ' where ' . $condition, $sql);
	}
	
	/**
	 * Add update values from Entity to SQL
	 * @param Entity $entity
	 * @param string $sql
	 */
	protected function addUpdateValues(& $sql, $entity) {
		$values = '';
		
		foreach($this->config[ModelAnnotation::FIELDS] as $field => $config) {
			if (isset($config[ModelAnnotation::KEY]))
				continue;
				
			if (isset($config[ModelAnnotation::COLUMN])) {
				if (strtolower($config[ModelAnnotation::TYPE]) == self::NUMBER) {
					$values .= ',`' . $config[ModelAnnotation::COLUMN] . '`=' . $entity->$field;
				} else {
					$values .= ',`' . $config[ModelAnnotation::COLUMN] . '`=\'' . addcslashes($entity->$field, "'") . '\'';					
				}
			}
		}
		
		$sql = str_replace(self::UPDATE_VALUE, substr($values, 1), $sql);
	}
	
	/**
	 * Add order by column for SQL
	 * @param string $sql
	 * @param string $orderBy
	 */
	protected  function addOrderBy(& $sql, $orderBy = false, $orderType = self::ASC) {
		$sql = str_replace(self::ORDER_BY, !$orderBy ? '' : ' order by `' . $orderBy . '` ' . $orderType, $sql);
	}
	
	/**
	 * Add limitation for SQL
	 * @param string $sql
	 * @param number $pageIndex
	 * @param number $pageItems
	 */
	protected function addLimit(& $sql, $pageIndex = 1, $pageItems = 10) {
		$sql = str_replace(self::LIMIT, ' limit ' . (($pageIndex - 1) * $pageItems) . ',' . $pageItems, $sql);
	}

	/**
	 * Get Entity
	 * @param $row Row result from sql
	 * @return Entity
	 */
	protected function getEntityFromRow($row) { 
		$entity = null;
		//$objectID = $this->className;
		
		eval("\$entity = new $this->className();");
		foreach ($this->config[ModelAnnotation::FIELDS] as $field => $construc) {
			if (isset($construc[ModelAnnotation::COLUMN])) {
				$entity->$field = $row[$construc[ModelAnnotation::COLUMN]];
			}
			//if (isset($construc[ModelAnnotation::KEY])) {
				//$objectID .= '$' . $row[$construc[ModelAnnotation::COLUMN]];
			//}
		}
		
		//$entity->ID = $objectID;
		return $entity;
	}
	
	/**
	 * Get other fields in annotation configuration
	 * @param Entity $entity
	 * @param $row Row result from sql
	 * @param array $otherFields
	 */
	protected function getField(&$entity, $row, $otherFields) {
		foreach ($otherFields as $field) {
			eval("\$entity->$field = $row[$field];");
		}
	}
	
	/**
	 * Get ID condition for sql
	 * @param Entity $entity
	 * @return string
	 */
	protected function getIdCondition($entity) {
		$condition = '';
		
		foreach ($this->config[ModelAnnotation::FIELDS] as $field => $construc) {
			if (isset($construc[ModelAnnotation::KEY])) {
				$condition .= ' AND `';
				if ($construc[ModelAnnotation::TYPE] == self::NUMBER) {
					$condition .= $construc[ModelAnnotation::COLUMN] . '`=' . $entity->$field;
				} else {
					$condition .= $construc[ModelAnnotation::COLUMN] . '`=\'' . addcslashes($entity->$field, "'") . '\'';						
				}
			}
		}
		
		return '(' . substr($condition, 4) . ')';
	}

	/**
	 * Enter description here ...
	 */
	public function beginTransaction() {
		mysql_query('SET AUTOCOMMIT=0');
		mysql_query('START TRANSACTION');
	}
	
	/**
	 * Enter description here ...
	 */
	public function commit() {
		mysql_query('SET AUTOCOMMIT=1');
	}
	
	/**
	 * Enter description here ...
	 */
	public function rollback() {
		mysql_query('ROLLBACK');//nothing will be done, I assume it's for testing
		mysql_query('SET AUTOCOMMIT=1');
	}
		
	/**
	 * Insert Entity to database
	 * @param Entity $entity
	 * @return Entity
	 */
	public function insert($entity) {
		$sql = self::SQL_INSERT;
		
		$this->addTable($sql);
		$this->addInsertFields($sql);
		$this->addInsertValues($sql, $entity);
		
		$check = mysql_query($sql);
		$newID = mysql_insert_id();
		
		if ($check) {
			if ($newID > 0) {
				foreach ($this->config[ModelAnnotation::FIELDS] as $field => $construc) {
					if (isset($construc[ModelAnnotation::KEY]) &&
							isset($construc[ModelAnnotation::GENERATOR])) {
						$entity->$field = $newID;
						return $this->selectById($entity);
					}
				}
			} else {
				return $this->selectById($entity);
			}
		}
		
		return null;
	}
	
	/**
	 * Delete the Entity from database
	 * @param Entity $entity
	 * @return boolean
	 */
	public function delete($entity) {
		$sql = self::SQL_DELETE;
		
		$this->addTable($sql);
		$this->addCondition($sql, $this->getIdCondition($entity));
		
		return mysql_query($sql);
	}
	
	/**
	 * Update the Entity to database
	 * @param Entity $entity
	 * @param string $condiction
	 * @return Entity
	 */
	public function update($entity, $condition = false) {
		$sql = self::SQL_UPDATE;
		
		$this->addTable($sql);
		$this->addCondition($sql, !$condition ? $this->getIdCondition($entity) : $condition);
		$this->addUpdateValues($sql, $entity);
		
		return mysql_query($sql) ? $this->selectById($entity) : null;
	}
	
	/**
	 * Check to insert/update Entity
	 * @param Entity $entity
	 * @return Entity
	 */
	public function insertOrUpdate($entity) {
		$object = $this->selectById($entity);
		if ($object != null) {
			return $this->update($entity);
		} else {
			return $this->insert($entity);
		}
	}

	/**
	 * Get Entity by identify
	 * @param Entity $object
	 * @return Entity
	 */
	public function selectById($object) {
		$entity = null;
		
		$sql	= self::SQL_SELECT;
		$this->addTable($sql);
		$this->addCondition($sql, $this->getIdCondition($object));
		$this->addOrderBy($sql);
		$this->addLimit($sql);
		
		$result = mysql_query($sql);
		if ($result) {
			while ($row = mysql_fetch_array($result)) {
				$entity = self::getEntityFromRow($row);
				break;
			}
		}
		
		return $entity;
	}
	
	/**
	 * Get list of Entity
	 * @param number $pageIndex
	 * @param number $pageItems
	 * @param string $condition
	 * @param string $orderBy
	 * @param string $orderType
	 * @return array<index, Entity>
	 */
	public function select($pageIndex = 1, $pageItems = 10, $condition = '', $orderBy = false, $orderType = self::ASC) {
		$sql = self::SQL_SELECT;
		$hash = array();
		
		$this->addTable($sql);
		$this->addCondition($sql, $condition);
		$this->addOrderBy($sql, $orderBy, $orderType);
		$this->addLimit($sql, $pageIndex, $pageItems);
		
		$result = mysql_query($sql);
		if ($result) {
			$i = 0; 
			while ($row = mysql_fetch_array($result)) {
				$entity = self::getEntityFromRow($row);
				$hash[$i] = $entity;
				$i++;
			}
		}
		
		return $hash;
	}
	
	/**
	 * Enter description here ...
	 * @param number $pageIndex
	 * @param number $pageItems
	 * @param string $condition
	 * @param string $orderBy
	 * @param string $orderType
	 * @return Object(list, size, pages, start)
	 */
	public function selectPage($pageIndex = 1, $pageItems = 10, $condition = '', $orderBy = false, $orderType = self::ASC) {
		$obj = new Object();
		$obj->list = $this->select($pageIndex, $pageItems, $condition, $orderBy, $orderType);
		
		$sql = self::SQL_COUNT;
		$this->addCondition($sql, $condition);
		$this->addTable($sql);
		
		$result = mysql_query($sql);
		$count = 0;
		if ($result) {
			while ($row = mysql_fetch_array($result)) {
				$count = $row[self::SIZE];
				break;
			}
		}
		
		$obj->size = $count;
		$count = $count / $pageItems;
		$count = intval($count) + (is_float ($count) ? 1 : 0);
		$obj->pages = $count;
		$obj->start = (($pageIndex - 1) * $pageItems) + 1;
		
		return $obj;
	}
	
	/**
	 * Get data by sql query string
	 * @param string $sql Query string
	 * @return array<index, Entity>
	 */
	public function selectBySql($sql, $otherFields = array()) {
		$hash = array();
				
		$result = mysql_query($sql);
		if ($result) {
			$i = 0; 
			while ($row = mysql_fetch_array($result)) {
				$entity = self::getEntityFromRow($row);
				self::getField($entity, $row, $otherFields);
				$hash[$i] = $entity;
				$i++;
			}
		}
		
		return $hash;
	}
	
	/**
	 * Check for exist item
	 * @param string $condition
	 * @return boolean
	 */
	public function isExist($entity, $condition = '') {
		$sql = self::SQL_COUNT;	
		foreach ($this->config[ModelAnnotation::FIELDS] as $field => $config) {
			if ($this->isInit($entity->$field)) {
				if (isset($config[ModelAnnotation::UNIQUE])) {
					if ($condition != '') {
						$condition .= ' AND ';
					}
					if (strtolower($config[ModelAnnotation::TYPE]) == self::NUMBER) {
						$condition .= '`' . $config[ModelAnnotation::COLUMN] . '`=' . $entity->$field;
					} else {
						$condition .= '`' . $config[ModelAnnotation::COLUMN] . '`=\'' . addcslashes($entity->$field, "'") . '\'';					
					}
				} else if (isset($config[ModelAnnotation::KEY])) {
					if ($condition != '') {
						$condition .= ' AND ';
					}
					if (strtolower($config[ModelAnnotation::TYPE]) == self::NUMBER) {
						$condition .= '`' . $config[ModelAnnotation::COLUMN] . '`<>' . $entity->$field;
					} else {
						$condition .= 'LOWER(`' . $config[ModelAnnotation::COLUMN] . '`)<>LOWER(\'' . addcslashes($entity->$field, "'") . '\')';					
					}
				}
			}
		}
		
		$this->addTable($sql);
		$this->addCondition($sql, $condition); 
		$result = mysql_query($sql);
		if ($result) {
			while ($row = mysql_fetch_array($result)) {
				return $row[self::SIZE] > 0;
				break;
			}
		}
		return false;
	}
	
	/**
	 * Execute a sql query
	 * @param string $sql
	 */
	public function exec($sql) {
		mysql_query($sql);
	}
	
	/**
	 * 
	 * @param unknown_type $val
	 * @return boolean
	 */
	private function isInit($val) {
		$type = gettype($val);
		if (isset($val)) {
			if ($type == 'integer' && $val != 0) {
				return true;
			} else if ($type == 'string' && $val != '') {
				return true;
			}
		}
		return false;
	}
	
}
?>