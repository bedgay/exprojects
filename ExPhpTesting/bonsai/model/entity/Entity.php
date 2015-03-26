<?php
/**
 * Enter description here ...
 * @author Anh Tu Ngo
 * @date 11/21/2011
 */
abstract class Entity {
	
	/**
	 * The primary key of the Entity
	 * @column(ID)
	 * @type(number)
	 * @key(indentify)
 	 * @generator(increment)
	 */
	public $id;
	
}
?>