<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 27/09/2013
 * @table(tblquestions)
 *
 */
final class Question extends Entity {
	
	/**
	 * Content of Question
	 * @column(Content)
	 * @type(string)
	 */
	public $content;
				
	/**
	 * Order of Question
	 * @column(Order)
	 * @type(string)
	 */
	public $order;
	
	/**
	 * Forein key to Section
	 * @column(SectionID)
	 * @type(number)
	 */
	public $sectionID;
	
	/**
	 * List of Answer
	 */
	public $answers;
		
	/**
	 * Constructor
	 * @param number $id
	 */
	public function Question($id = 0, $content = '', $order = '', $sectionID = 0) {
		$this->id = $id;
		$this->content = $content;
		$this->order = $order;
		$this->sectionID = $sectionID;
	}
	
}
?>