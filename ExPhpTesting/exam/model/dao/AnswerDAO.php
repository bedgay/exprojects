<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 27/09/2013
 */
final class AnswerDAO extends DAO {
	private static $instance = null;
	
	/**
	 * AnswerDAO instance only
	 * @return AnswerDAO
	 */
	public static function getInstance() {
		if (self::$instance == null)
			self::$instance = new AnswerDAO("Answer");
		return self::$instance;
	}
	
	/**
	 * 
	 * Enter description here ...
	 * @param integer $questionID
	 */
	public function findByQuestion($questionID) {
		return $this->select(DAO::FIRST_PAGE, DAO::MAX_SIZE, "QuestionID=$questionID", "Order");
	}
	
	/**
	 * Enter description here ...
	 * @param integer $questionID
	 */
	public function deleteByQuestion($questionID) {
		$sql = DAO::SQL_DELETE;
		$this->addTable($sql);
		$this->addCondition($sql, "QuestionID=$questionID");
		return mysql_query($sql);
	}
}
?>