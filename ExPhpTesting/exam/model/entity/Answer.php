<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 27/09/2013
 * @table(tblanswers)
 *
 */
final class Answer extends Entity {
	
	/**
	 * Name of Answer
	 * @column(Content)
	 * @type(string)
	 */
	public $content;
			
	/**
	 * Order of Answer
	 * @column(Order)
	 * @type(string)
	 */
	public $order;
	
	/**
	 * Good Answer
	 * @column(IsTrue)
	 * @type(number)
	 */
	public $isTrue;
	
	public $isChosen = 0;
	
	/**
	 * Forein key to Question
	 * @column(QuestionID)
	 * @type(number)
	 */
	public $questionID;
		
	/**
	 * Constructor
	 * @param number $id
	 */
	public function Answer($id = 0, $content = '', $order = '', $isTrue = 0, $questionID = 0) {
		$this->id = $id;
		$this->content = $content;
		$this->order = $order;
		$this->isTrue = $isTrue;
		$this->questionID = $questionID;
	}
	
}
?>