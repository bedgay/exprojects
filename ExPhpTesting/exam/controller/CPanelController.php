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
require_once "$PATH/business/CPanelBusiness.php";

require_once "exam/controller/SecurityController.php";

/**
 * Enter description here ...
 * @author NgoAnhTu
 *
 */
final class CPanelController extends SecurityController {
	
	const VIEW_PATH = 'exam/view/cpanel/';
	const NAME = 'cpanel';
	
	private $cpanelService;
	
	/**
	 * Constructor
	 */
	public function CPanelController () {
		parent::SecurityController();
		$this->cpanelService = CPanelBusiness::getInstance();
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
		$cats = $this->cpanelService->findAllCategories();
		require_once self::VIEW_PATH . 'categories.html';
	}

	/**
	 * Action: Add new category
	 * @action(catnew)
	 */
	public function catnew($name) {	
		$msg = '';
		
		if (!HTTP::isGet()) {
			if ($this->cpanelService->isCategoryExist(0, $name)) {
				$msg = 'Category [' . $name . '] is already exist.';
				$this->msg(self::NAME, 'catnew', '', $msg, 1);
			} else {
				if ($this->cpanelService->newCategory($name) != null) {
					$msg = "Category [" . $name . "] has just added.";
				} else {
					$msg = "Can not add Category [" . $name . "].";
				}
				$this->msg(self::NAME, 'cats', '', $msg, 1);
			}
		}
		
		require_once self::VIEW_PATH . 'catnew.html';
	}
	
	/**
	 * Action: Edit category
	 * @action(catedit)
	 */
	public function catedit($id, $name) {
		$msg = '';
		
		$cat = $this->cpanelService->getCategory($id);
		if ($cat == null) {
			$msg = 'Category with ID [' . $id . '] is not exist.';
			$this->msg(self::NAME, 'cats', '', $msg, 1);
		} else {
			if (HTTP::isGet()) {
				require_once self::VIEW_PATH . 'catedit.html';
			} else {
				$cat->name = $name;
				if ($this->cpanelService->isCategoryExist($id, $name)) {
					$msg = 'Category [' . $name . '] is already exist.';
				} else {
					if ($this->cpanelService->saveCategory($cat)) {
						$msg = "Category [" . $name . "] has just saved.";
					} else {
						$msg = "Can not save Category [" . $name . "].";
					}
				}
				$this->msg(self::NAME, 'cats', '', $msg, 1);
			}
		}
	}
	
	/**
	 * Action: Delete category
	 * @action(catdel)
	 */
	public function catdel($id) {
		$msg = '';
	
		if ($this->cpanelService->getCategory($id) == null) {
			$msg = 'Category with ID [' . $id . '] is not exist.';
		} else {
			if ($this->cpanelService->delCategory($id) != null) {
				$msg = 'Category with ID [' . $id . '] has just deleted.';
			} else {
				$msg = 'Can not delete Category with ID [' . $id . '].';
			}
		}
		
		$this->msg(self::NAME, 'cats', '', $msg, 1);
	}
	
	/**
	 * Action: View list of Sections
	 * @action(secs)
	 */
	public function secs($cid) {
		$cat = $this->checkCategory($cid);
		$secs = $this->cpanelService->findSectionsByCategory($cid);
		require_once self::VIEW_PATH . 'secs.html';
	}
	
	/**
	 * Action: Add new Section
	 * @action(secnew)
	 */
	public function secnew($cid, $order, $name) {
		$msg = '';
		
		$cat = $this->checkCategory($cid);		
	
		if (!HTTP::isGet()) {
			if ($this->cpanelService->isSectionExist($cid, 0, $name)) {
				$msg = 'Section [' . $name . '] is already exist.';
				$this->msg(self::NAME, 'secnew', 'cid=' . $cid, $msg, 1);
			} else {
				if ($this->cpanelService->newSection($cid, $order, $name) != null) {
					$msg = "Section [" . $name . "] has just added.";
				} else {
					$msg = "Can not add Section [" . $name . "].";
				}
				$this->msg(self::NAME, 'secs', 'cid=' . $cid, $msg, 1);
			}
		}
	
		require_once self::VIEW_PATH . 'secnew.html';
	}
	
	/**
	 * Action: Edit Section
	 * @action(secedit)
	 */
	public function secedit($cid, $id, $order, $name) {
		$msg = '';
		
		$cat = $this->checkCategory($cid);
		
		$sec = $this->cpanelService->getSection($id);
		if ($sec == null) {
			$msg = 'Section with ID [' . $id . '] is not exist.';
			$this->msg(self::NAME, 'secs', 'cid=' . $cid, $msg, 1);
		} else {
			if (HTTP::isGet()) {
				require_once self::VIEW_PATH . 'secedit.html';
			} else {
				$sec->name = $name;
				$sec->order = $order;
				if ($this->cpanelService->isSectionExist($cid, $id, $name)) {
					$msg = 'Section [' . $name . '] is already exist.';
				} else {
					if ($this->cpanelService->saveSection($sec)) {
						$msg = "Section [" . $name . "] has just saved.";
					} else {
						$msg = "Can not save Section [" . $name . "].";
					}
				}
				$this->msg(self::NAME, 'secs', 'cid=' . $cid, $msg, 1);
			}
		}
	}
	
	/**
	 * Action: Delete Section
	 * @action(secdel)
	 */
	public function secdel($cid, $id) {
		$msg = '';
		
		$cat = $this->checkCategory($cid);
	
		if ($this->cpanelService->getSection($id) == null) {
			$msg = 'Section with ID [' . $id . '] is not exist.';
		} else {
			if ($this->cpanelService->delSection($id) != null) {
				$msg = 'Section with ID [' . $id . '] has just deleted.';
			} else {
				$msg = 'Can not delete Section with ID [' . $id . '].';
			}
		}
	
		$this->msg(self::NAME, 'secs', 'cid=' . $cid, $msg, 1);
	}
	
	/**
	 * Action: View list of Question
	 * @action(ques)
	 */
	public function ques($sid, $page = 1, $pattern = '') {
		$sec = $this->checkSection($sid);
		$cat = $this->checkCategory($sec->categoryID);
		
		$ques = $this->cpanelService->findQuestionsBySection($sid, $page, $pattern);
		require_once self::VIEW_PATH . 'ques.html';
	}
	
	/**
	 * Action: Create new Question
	 * @action(quenew)
	 */
	public function quenew($sid, $content, $order, $ansOrder, $ansContent, $ansIsRight) {
		$sec = $this->checkSection($sid);
		$cat = $this->checkCategory($sec->categoryID);
		if (!HTTP::isGet()) {
			$msg = '';
			if ($this->cpanelService->newQuestion($sid, $content, $order, $ansOrder, $ansContent, $ansIsRight) != null) {
				$msg = "Question [" . $order . "] has just added.";
			} else {
				$msg = "Can not add Question [" . $order . "].";
			}
			$this->msg(self::NAME, 'ques', 'sid=' . $sid, $msg, 1);
		}
		
		require_once self::VIEW_PATH . 'quenew.html';
	}

	/**
	 * Action: Create new Question
	 * @action(queedit)
	 */
	public function queedit($sid, $id, $content, $order, $ansOrder, $ansContent, $ansIsRight) {
		$que = $this->checkQuestion($sid, $id, true);
		$sec = $this->checkSection($sid);
		$cat = $this->checkCategory($sec->categoryID);
		if (!HTTP::isGet()) {
			$msg = '';
			if ($this->cpanelService->saveQuestion($sid, $id, $content, $order, $ansOrder, $ansContent, $ansIsRight) != null) {
				$msg = "Question [" . $order . "] has just save.";
			} else {
				$msg = "Can not save Question [" . $order . "].";
			}
			$this->msg(self::NAME, 'ques', 'sid=' . $sid, $msg, 1);
		}
		
		require_once self::VIEW_PATH . 'queedit.html';
	}

	/**
	 * Action: Delete Section
	 * @action(quedel)
	 */
	public function quedel($sid, $id) {
		$msg = '';
		
		$que = $this->checkQuestion($sid, $id);
		$sec = $this->checkSection($sid);
	
		if ($que != null) {
			if ($this->cpanelService->delQuestion($id) != null) {
				$msg = 'Question with ID [' . $que->order . '] has just deleted.';
			} else {
				$msg = 'Can not delete Question with ID [' . $que->order . '].';
			}
		}
	
		$this->msg(self::NAME, 'ques', 'sid=' . $sid, $msg, 1);
	}

	private function checkCategory($cid) {
		$cat = $this->cpanelService->getCategory($cid);
		
		if ($cat == null) {
			$msg = 'Category with ID [' . $cid . '] is not exist.';
			$this->msg(self::NAME, 'cats', '', $msg, 1);
		} 
		
		return $cat;
	}

	private function checkSection($sid) {
		$sec = $this->cpanelService->getSection($sid);
	
		if ($sec == null) {
			$msg = 'Section with ID [' . $sid . '] is not exist.';
			$this->msg(self::NAME, 'cats', '', $msg, 1);
		}
	
		return $sec;
	}

	private function checkQuestion($sid, $id, $eagerMode = false) {
		$sec = $this->cpanelService->getQuestion($id, $eagerMode);
	
		if ($sec == null) {
			$msg = 'Question with ID [' . $id . '] is not exist.';
			$this->msg(self::NAME, 'ques', 'sid=' . $sid, $msg, 1);
		}
	
		return $sec;
	}
	
}
?>