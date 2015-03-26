<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 15/09/2013
 * @table(tblcategories)
 *
 */
final class Category extends Entity {
	
	/**
	 * Name of Category
	 * @unique(true)
	 * @column(Name)
	 * @type(string)
	 */
	public $name;
		
	/**
	 * Image of Category
	 * @column(Image)
	 * @type(string)
	 */
	public $image;
	
	/**
	 * Order of Category
	 * @column(Order)
	 * @type(number)
	 */
	public $order;
	
	/**
	 * Constructor
	 * @param number $id
	 */
	public function Category($id = 0, $name = '', $image = '', $order = 0) {
		$this->id = $id;
		$this->name = $name;
		$this->image = $image;
		$this->order = $order;
	}
	
}
?>