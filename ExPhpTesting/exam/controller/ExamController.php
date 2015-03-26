<?php

$PATH = 'bonsai/common';
require_once "$PATH/file/File.php";
require_once "$PATH/file/Image.php";
require_once "$PATH/util/Collection.php";

$PATH = 'exam';
require_once "$PATH/model/entity/Category.php";
require_once "$PATH/model/entity/Section.php";
require_once "$PATH/model/entity/Question.php";
require_once "$PATH/model/entity/Answer.php";
require_once "$PATH/model/dao/CategoryDAO.php";
require_once "$PATH/model/dao/SectionDAO.php";
require_once "$PATH/model/dao/QuestionDAO.php";
require_once "$PATH/model/dao/AnswerDAO.php";
require_once "$PATH/business/CommonBusiness.php";
require_once "$PATH/business/ExamBusiness.php";

require_once "exam/controller/SecurityController.php";

/**
 * Enter description here ...
 * @author NgoAnhTu
 *
 */
final class ExamController extends SecurityController {
	
	const VIEW_PATH = 'exam/view/exam/';
	const NAME = 'exam';
	const INDEX = 'index';
	const MARK = 'mark';
	const MINUTES = 'minutes';
	const TOTAL_MINUTES = 'totalminute';
	
	private $examService;
	
	/**
	 * Constructor
	 */
	public function ExamController () {
		parent::SecurityController();
		$this->examService = ExamBusiness::getInstance();
	}
	
	/**
	 * (non-PHPdoc)
	 * @see SecurityController::checkSecurity()
	 */
	protected function checkSecurity() {
		if (!isset($_SESSION[Dispatcher::USER_SESSION])) {
			$this->redirect('user', 'login');
		}
	}

	/**
	 * Action: View all category
	 * @action(cats)
	 */
	public function categories() {	
		$cats = $this->examService->findAllCategories();
		require_once self::VIEW_PATH . 'categories.html';
	}	
	
	/**
	 * Action: Make an exam
	 * @action(exam)
	 */
	public function exam($id, $total = 0, $minutes = 0, $secIDs = null) {	
		$cat = $this->checkCategory($id);
		$secs = $this->examService->findSectionsByCategory($id);
		
		if (!HTTP::isGet()) {
			unset($_SESSION[self::NAME]);
			unset($_SESSION[self::INDEX]);
			unset($_SESSION[self::MARK]);
			unset($_SESSION[self::MINUTES]);
			unset($_SESSION[self::TOTAL_MINUTES]);
			
			$ques = $this->examService->randomQestionBySections($secIDs, $total);
			$_SESSION[self::NAME] = serialize($ques);
			$_SESSION[self::INDEX] = 0;
			$_SESSION[self::MINUTES] = $minutes;
			$_SESSION[self::TOTAL_MINUTES] = $minutes * sizeof($ques) * 60;
			$this->redirect(self::NAME, "testing", "id=$id");
		}
		
		require_once self::VIEW_PATH . 'exam.html';
	}	
	
	/**
	 * Action: View all category
	 * @action(testing)
	 */
	public function testing($id, $answers = null, $submitType = null, $checkmark = null, $selectMark = null, $spentTime = 0) {	
		// Question# / 207 minute(s) left
		// Marked []	 Answered 2/65
		// Previous / Next / Find next unanswered
		$index = $_SESSION[self::INDEX];
		$findIndex = $index;
		$marks = array();
		$isCheckMark = false;
		
		$cat = $this->checkCategory($id);
		$ques = unserialize($_SESSION[self::NAME]);
		
		$_SESSION[self::TOTAL_MINUTES] = $_SESSION[self::TOTAL_MINUTES] - $spentTime;
		
		if (!HTTP::isGet()) {
			$this->examService->checking($ques, $index, $answers);
			
			if ($submitType == 'btnNext') {
				$findIndex++;
				if ($findIndex >= sizeof($ques)) {
					$findIndex = 0;
				}
			} else if($submitType == 'btnPrevious') {
				$findIndex--;
				if ($findIndex < 0) {
					$findIndex = sizeof($ques) - 1;
				}
			} else if($submitType == 'btnFindNextUnanswered') {
				$findIndex = $this->examService->getUncheckIndex($ques, $index + 1, sizeof($ques));
				if ($findIndex == -1) {
					$findIndex = $this->examService->getUncheckIndex($ques, 0, sizeof($ques));
				}
				if ($findIndex == -1) {
					$findIndex = $index;
				}
			} else if ($submitType == 'selectMark') {
				if ($selectMark != null && $selectMark != '') {
					$findIndex = $selectMark;
				} else {
					$findIndex = $index;
				}
			} else if($submitType == 'btnDone') {
				$this->redirect(self::NAME, "result", "id=$id");
			}
			
			$marks = $this->processCheckMark($index, $checkmark);			
			$index = $findIndex;
			$_SESSION[self::INDEX] = $findIndex;
			$_SESSION[self::NAME] = serialize($ques);
		}
		
		$isCheckMark = $this->isCheckMark($index);
		$que = $this->examService->getQuestion($ques[$findIndex]->id, true);
		for ($i = 0; $i < sizeof($que->answers); $i++) {
			for ($j = 0; $j < sizeof($ques[$index]->answers); $j++) {
				if ($que->answers[$i]->id == $ques[$index]->answers[$j]->id) {
					$que->answers[$i]->isTrue = $ques[$index]->answers[$j]->isTrue;
					break;
				}
			}
		}
		//$que->answers = $ques[$index]->answers;
		require_once self::VIEW_PATH . 'testing.html';
	}	
	
	/**
	 * Action: View all category
	 * @action(result)
	 */
	public function result($id) {	
		$cat = $this->checkCategory($id);
		$ques = unserialize($_SESSION[self::NAME]);
		$result = $this->examService->getResult($ques);
		$list = $result['list'];
		$notchosen = $result['notchosen'];
		require_once self::VIEW_PATH . 'result.html';
	}	

	private function checkCategory($id) {
		$cat = $this->examService->getCategory($id);
		
		if ($cat == null) {
			$msg = 'Category with ID [' . $id . '] is not exist.';
			$this->msg(self::NAME, 'cats', '', $msg, 1);
		} 
		
		return $cat;
	}
	
	private function processCheckMark($index, $checkmark) {
		$marks = array();
		if (isset($_SESSION[self::MARK])) {
			$marks = $_SESSION[self::MARK];
		}
		
		if ($index == $checkmark) {
			$marks[$index] = $index;
		} else {
			unset($marks[$index]);
		}
		
		$_SESSION[self::MARK] = $marks;
		return $marks;
	}

	private function isCheckMark($index) {
		$marks = array();
		if (isset($_SESSION[self::MARK])) {
			$marks = $_SESSION[self::MARK];
		}
		return isset($marks[$index]) && $marks[$index] == $index;
	}	
	
}
?>