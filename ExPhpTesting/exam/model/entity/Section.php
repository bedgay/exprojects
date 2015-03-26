<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 27/09/2013
 * @table(tblsections)
 *
 */
final class Section extends Entity {
	
	/**
	 * Name of Section
	 * @unique(true)
	 * @column(Name)
	 * @type(string)
	 */
	public $name;
			
	/**
	 * Order of Section
	 * @column(Order)
	 * @type(number)
	 */
	public $order;
	
	/**
	 * Forein key to Category
	 * @unique(true)
	 * @column(CategoryID)
	 * @type(number)
	 */
	public $categoryID;
		
	/**
	 * Constructor
	 * @param number $id
	 */
	public function Section($id = 0, $name = '', $order = 0, $categoryID = 0) {
		$this->id = $id;
		$this->name = $name;
		$this->order = $order;
		$this->categoryID = $categoryID;
	}
	
}
?>