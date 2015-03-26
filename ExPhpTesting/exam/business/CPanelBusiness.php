<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 *
 */
final class CPanelBusiness extends CommonBusiness {
	private static $instance = null;
		
	/**
	 * CPanelBusiness instance only
	 * @return CPanelBusiness
	 */
	public static function getInstance() {
		if (self::$instance == null)
			self::$instance = new CPanelBusiness();
		return self::$instance;
	}
	
	/**
	 * Check the exist Category
	 * @param Number $id
	 * @param String $name
	 * @return boolean
	 */
	public function isCategoryExist($id = 0, $name = '') {
		return $this->categoryDAO->isExist(new Category($id, $name));
	}
	
	/**
	 * Create new Category with new name
	 * @param String $name
	 */
	public function newCategory($name) {
		$cat = new Category();
		$cat->name = $name;
		return $this->categoryDAO->insert($cat);
	}
	
	/**
	 * Save Category
	 * @param Category $cat
	 * @return Ambigous <Entity, NULL>
	 */
	public function saveCategory($cat) {
		return $this->categoryDAO->update($cat);
	}
	
	/**
	 * Delete Category
	 * @param Number $id
	 */
	public function delCategory($id) {
		$cat = new Category();
		$cat->id = $id;
		return $this->categoryDAO->delete($cat);
	}
	
	/**
	 * Check the exist Section
	 * @param integer $categoryID
	 * @param integer $id
	 * @param string $name
	 * @return boolean
	 */
	public function isSectionExist($categoryID, $id = 0, $name = '') {
		return $this->sectionDAO->isExist(new Section($id, $name, 0, $categoryID));
	}
	
	/**
	 * Create new Section with new name
	 * @param integer $cid
	 * @param integer $order
	 * @param String $name
	 */
	public function newSection($cid, $order, $name) {
		return $this->sectionDAO->insert(new Section(0, $name, $order, $cid));
	}
	
	/**
	 * Save Section
	 * @param Section $sec
	 * @return Ambigous <Entity, NULL>
	 */
	public function saveSection($sec) {
		return $this->sectionDAO->update($sec);
	}
	
	/**
	 * Delete Section
	 * @param Number $id
	 */
	public function delSection($id) {
		$sec = new Section();
		$sec->id = $id;
		return $this->sectionDAO->delete($sec);
	}

	/**
	 * 
	 * Enter description here ...
	 * @param unknown_type $sid
	 * @param unknown_type $content
	 * @param unknown_type $order
	 * @param unknown_type $ansOrder
	 * @param unknown_type $ansContent
	 * @param unknown_type $ansIsRight
	 */
	public function newQuestion($sid, $content, $order, $ansOrder, $ansContent, $ansIsRight) {
		$this->questionDAO->beginTransaction();
		
		$que = $this->questionDAO->insert(new Question(0, $content, $order, $sid));
		if ($que == null) {
			$this->questionDAO->rollback();
			return false;
		} else {
			for ($i = 0; $i < sizeof($ansOrder); $i++) {
				$right = 0;
				foreach ($ansIsRight as $isRight) {
					if ($isRight == $i) {
						$right = 1;
						break;
					}
				}
				if ($ansOrder[$i] != '') {
					$ans = $this->answerDAO->insert(new Answer(0, $ansContent[$i], $ansOrder[$i], $right, $que->id));
					if ($ans == null) {
						$this->answerDAO->rollback();
						return false;
					}
				}
				$right = 0;
			}
		}
		
		$this->questionDAO->commit();
		return true;
	}
	
	/**
	 * Enter description here ...
	 * @param unknown_type $sid
	 * @param unknown_type $id
	 * @param unknown_type $content
	 * @param unknown_type $order
	 * @param unknown_type $ansOrder
	 * @param unknown_type $ansContent
	 * @param unknown_type $ansIsRight
	 */
	public function saveQuestion($sid, $id, $content, $order, $ansOrder, $ansContent, $ansIsRight) {
		$this->questionDAO->beginTransaction();
		
		$que = $this->questionDAO->update(new Question($id, $content, $order, $sid));
		if ($que == null) {
			$this->questionDAO->rollback();
			return false;
		} else {
			$this->answerDAO->deleteByQuestion($id);
			for ($i = 0; $i < sizeof($ansOrder); $i++) {
				$right = 0;
				foreach ($ansIsRight as $isRight) {
					if ($isRight == $i) {
						$right = 1;
						break;
					}
				}
				if ($ansOrder[$i] != '') {
					$ans = $this->answerDAO->insert(new Answer(0, $ansContent[$i], $ansOrder[$i], $right, $que->id));
					if ($ans == null) {
						$this->answerDAO->rollback();
						return false;
					}
				}
				$right = 0;
			}
		}
		
		$this->questionDAO->commit();
		return true;
	}

	/**
	 * Delete Question
	 * @param Number $id
	 */
	public function delQuestion($id) {
		$que = new Question($id);
		return $this->questionDAO->delete($que);
	}
	
}
?>