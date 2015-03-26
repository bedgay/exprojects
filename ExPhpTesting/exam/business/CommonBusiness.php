<?php
/**
 * 
 * Enter description here ...
 * @author NgoAnhTu
 * @date 29/09/2013
 */
class CommonBusiness {
	
	/**
	 * Enter description here ...
	 * @var CategoryDAO
	 */
	protected $categoryDAO;
	
	/**
	 * @var SectionDAO
	 */
	protected $sectionDAO;
	
	/**
	 * @var QuestionDAO
	 */
	protected $questionDAO;
	
	/**
	 * @var AnswerDAO
	 */
	protected $answerDAO;
	
	/**
	 * Constructor
	 */
	protected function CommonBusiness() {
		$this->categoryDAO = CategoryDAO::getInstance();
		$this->sectionDAO = SectionDAO::getInstance();
		$this->questionDAO = QuestionDAO::getInstance();
		$this->answerDAO = AnswerDAO::getInstance();
	}
	
	/**
	 * Enter description here ...
	 * @param number $id
	 * @return Category
	 */
	public function getCategory($id) {
		return $this->categoryDAO->selectById(new Category($id));
	}
	
	/**
	 * Enter description here ...
	 * @return Ambigous <array<index,, multitype:, Entity, NULL>
	 */
	public function findAllCategories() {
		return $this->categoryDAO->select($pageIndex = 1, $pageItems = 255, $condition = '', $orderBy = 'Order');
	}

	/**
	 * Find all Sections by Category
	 * @param integer $cid
	 * @return Ambigous <array<index,, multitype:Ambigous <Entity, NULL> >
	 */
	public function findSectionsByCategory($cid) {
		return $this->sectionDAO->select(DAO::FIRST_PAGE, DAO::MAX_SIZE, "CategoryID=" . $cid, "Order`, `Name");
	}

	/**
	 * Enter description here ...
	 * @param number $id
	 * @return Section
	 */
	public function getSection($id) {
		return $this->sectionDAO->selectById(new Section($id));
	}

	/**
	 * Find Questions by Section
	 * @param integer $sid
	 * @return Ambigous <array<index,, multitype:Ambigous <Entity, NULL> >
	 */
	public function findQuestionsBySection($sid, $pageIndex = 1, $pattern = '') {
		return $this->questionDAO->selectPage($pageIndex, DAO::PAGE_SIZE, "SectionID=$sid AND Content LIKE '$pattern%'", "Order");
	}

	/**
	 * Enter description here ...
	 * @param number $id
	 * @return Question
	 */
	public function getQuestion($id, $eagerMode = false) {
		$que = $this->questionDAO->selectById(new Question($id));
		if ($que != null && $eagerMode) {
			$que->answers = $this->answerDAO->findByQuestion($id);
		}
		return $que;
	}
	
}
?>