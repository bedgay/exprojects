<?php
/**
 * Generate all html tag
 * @author Anh Tu Ngo
 * @date 12/12/2011
 */
final class Html {
	
	/**
	 * Generate radio button tag
	 * @param string $name
	 * @param string $value
	 * @param string $checkedVal
	 * @param string $id
	 * @param string $class
	 * @param string $label
	 * @return string
	 */
	public static function radio($name, $value, $checkedVal = '', $id = '', $class = '', $label = null) {
		$html = "<input type='radio' name='$name' value='$value' id='$id' class='$class' " .
				($value == $checkedVal ? "checked='checked'" : '') . '/>';
				
		if ($id != '' && $label != null) {
			$html .= "<label for='$id'>$label</label>";
		}		
		
		return $html;
	}
	
	/**
	 * Generate hidden tag
	 * @param string $name
	 * @param string $value
	 * @param string $id
	 * @return string
	 */
	public static function hidden($name, $value, $id='') {
		return "<input type='hidden' name='$name' value='$value' id='$id'/>";
	}
	
	/**
	 * Generate select tag
	 * @param string $name
	 * @param Array $list
	 * @param string $optionVal
	 * @param string $optionText
	 * @param string $selectedVal
	 * @param string $id
	 * @return string
	 */
	public static function select($name, $list, $optionVal, 
								  $optionText, $selectedVal = '', $id='', $defaultText = '') {
		$html = "<select name='$name' id='$id'>";
		
		if ($defaultText != '') {
			$html .= "<option value=''>$defaultText</option>";
		}
		
		foreach ($list as $item) {
			$html .= "<option value='" . $item->$optionVal . "' " . 
					 ($item->$optionVal == $selectedVal ? 'selected' : '') . ">" . 
					 $item->$optionText . "</option>";
		}
		
		return $html . '</select>';
	}
	
}
?>