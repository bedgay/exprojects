<?php
/**
 * Enter description here ...
 * @author NgoAnhTu
 * @date 28/09/2013
 * @table(tblusers)
 *
 */
final class User {
	
	/**
	 * The primary key of the User
	 * @column(UserName)
	 * @type(string)
	 * @key(indentify)
	 */
	public $userName;
	
	/**
	 * Password of User
	 * @column(Password)
	 * @type(string)
	 */
	public $password;
	
	/**
	 * Fullname of User
	 * @column(FullName)
	 * @type(string)
	 */
	public $fullName;
	
	/**
	 * Email of User
	 * @column(Email)
	 * @type(string)
	 */
	public $email;
			
	/**
	 * Status of User
	 * @column(IsActive)
	 * @type(number)
	 */
	public $isActive;
			
	/**
	 * Constructor
	 * @param number $id
	 */
	public function User($userName = '', $password = '', $fullName = '', $email = '', $isActive = 0) {
		$this->userName = $userName;
		$this->password = $password;
		$this->fullName = $fullName;
		$this->email = $email;
		$this->isActive = $isActive;
	}
	
}
?>