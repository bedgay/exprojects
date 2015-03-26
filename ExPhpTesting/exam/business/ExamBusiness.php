<?php

/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 29/09/2013
 */
final class ExamBusiness extends CommonBusiness {
	private static $instance = null;
		
	/**
	 * CPanelBusiness instance only
	 * @return ExamBusiness
	 */
	public static function getInstance() {
		if (self::$instance == null)
			self::$instance = new ExamBusiness();
		return self::$instance;
	}
	
	/**
	 * Enter description here ...
	 * @param array $ids
	 * @param integer $total
	 */
	public function randomQestionBySections($ids, $total) {
		$ques = $this->questionDAO->randomQestionBySections($ids, $total);
		
		for ($i = 0; $i < sizeof($ques); $i++) {
			$ques[$i]->content = '';
			$ques[$i]->order = $i + 1;
			$ans = $this->answerDAO->findByQuestion($ques[$i]->id);
			for ($j = 0; $j < sizeof($ans); $j++) {
				$ans[$j]->content = '';
				$ans[$j]->isTrue = 0;
			}
			$ques[$i]->answers = $ans;
		}
		
		return $ques;
	}
	
	/**
	 * Enter description here ...
	 * @param unknown_type $ids
	 */
	public function findByIDs($ids) {
		$ques = $this->questionDAO->select(DAO::FIRST_PAGE, DAO::MAX_SIZE, 'ID IN (' . implode(',', $ids) . ')');
		for ($i = 0; $i < sizeof($ques); $i++) {
			$ques[$i]->answers = $this->answerDAO->findByQuestion($ques[$i]->id);
		}
		return $ques;
	}

	/**
	 * Enter description here ...
	 * @param unknown_type $questions
	 * @param unknown_type $index
	 * @param unknown_type $answers
	 */
	public function checking(& $questions, $index, $answers) {
		$anss = $questions[$index]->answers;
		for ($i = 0; $i < sizeof($anss); $i++) {
			$anss[$i]->isTrue = 0;
			if ($answers != null && sizeof($answers) > 0) {
				for ($j = 0; $j < sizeof($answers); $j++) {
					if ($anss[$i]->id == $answers[$j]) {
						$anss[$i]->isTrue = 1;
						break;
					}
				}
			}
		}
		$questions[$index]->answers = $anss;
	}
	
	/**
	 * Enter description here ...
	 * @param unknown_type $ques
	 * @param unknown_type $fromIndex
	 * @param unknown_type $toIndex
	 */
	public function getUncheckIndex($ques, $fromIndex, $toIndex) {
		if ($toIndex > sizeof($ques)) {
			$toIndex = sizeof($ques);
		}
		
		$findIndex = -1;
		for ($i = $fromIndex; $i < $toIndex; $i++) {
			$check = false;
			foreach ($ques[$i]->answers as $ans) {
				if ($ans->isTrue > 0) {
					$check = true;
					break;
				}
			}
			
			if (!$check) {
				$findIndex = $i;
				break;
			}
		}
		
		return $findIndex;
	}
		
	/**
	 * Enter description here ...
	 * @param unknown_type $ques
	 */
	public function getResult($ques) {
		$ids = array();
		for ($i = 0; $i < sizeof($ques); $i++) {
			$ids[$i] = $ques[$i]->id;
		}
		
		$result = array();
		$resultIndex = 0;
		$notChosendCount = 0;
		
		$datas = $this->findByIDs($ids);
		foreach ($ques as $que) {
			foreach ($datas as $data) {
				if ($que->id == $data->id) {
					$isWrong = false;
					$isNotChosen = true;
					for ($i = 0; $i < sizeof($que->answers); $i++) {
						$data->answers[$i]->isChosen = $que->answers[$i]->isTrue;
						if ($que->answers[$i]->isTrue != $data->answers[$i]->isTrue) {
							$isWrong = true;
						}
						if ($que->answers[$i]->isTrue == 1) {
							$isNotChosen = false;
						}
					}
					if ($isWrong) {
						$data->order = $que->order;
						$result[$resultIndex] = $data;
						$resultIndex++;
					}
					if ($isNotChosen) {
						$notChosendCount++;
					}
				}
			}	
		}
		
		$array = array();
		$array['list'] = $result;
		$array['notchosen'] = $notChosendCount;
		return $array;
	}
	
}
?>