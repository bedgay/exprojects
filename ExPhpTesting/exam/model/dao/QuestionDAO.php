<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 27/09/2013
 */
final class QuestionDAO extends DAO {
	private static $instance = null;
	
//	const RANDOM_SQL = "SELECT * FROM tblquestions AS r1 JOIN (SELECT (RAND() * (SELECT MAX(id) FROM tblquestions)) AS id) AS r2
// 							WHERE r1.id >= r2.id ORDER BY r1.id ASC LIMIT @LIMIT";
	
	const RANDOM_SQL = "SELECT qs.* FROM tblquestions AS qs, tblsections AS ss 
								WHERE qs.SectionID=ss.ID AND ss.ID IN(@SIDs) ORDER BY RAND() LIMIT @limit";
	
	/**
	 * QuestionDAO instance only
	 * @return QuestionDAO
	 */
	public static function getInstance() {
		if (self::$instance == null)
			self::$instance = new QuestionDAO("Question");
		return self::$instance;
	}
	
	/**
	 * 
	 * Enter description here ...
	 * @param integer $ids
	 * @param array $limit
	 */
	public function randomQestionBySections($ids, $limit) {
		$sql = self::RANDOM_SQL;
		$hash = array();
		
		$sql = str_replace('@SIDs', implode(',', $ids), $sql);
		$sql = str_replace('@limit', $limit, $sql);
		
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
}
?>