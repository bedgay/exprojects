<?php
/**
 * Enter description here ...
 * @author Anh Tu Ngo
 * @date 12/20/2011
 */
class File {
	
	const SIZE = 'size';
	
	/**
	 * Check and get new file path
	 * @param string $filename
	 * @return string
	 */
	public function checkFile($filename) { // folder/filename.ext
		if (file_exists($filename)) {
			$index = strrpos($filename, '/');
			$path = substr($filename, 0, $index + 1);
			$filename = substr($filename, $index + 1, strlen($filename) - $index);
		
			$arr = preg_split("/\[[\d,]\]/", $filename);
			$prefix = '';
			$posfix = '';
			
			if (sizeof($arr) == 1) {
				$index = strrpos($filename, '.');
				$prefix = substr($filename, 0, $index);
				$posfix = substr($filename, $index, strlen($filename) - $index);
			} else {
				for ($i = 0; $i < sizeof($arr) - 1; $i++) {
					$prefix .= $arr[$i];
				}
				$posfix = $arr[sizeof($arr) - 1];
			}
			
			$i = 2;
			$filename = $path . $prefix . '[' . $i . ']' . $posfix;
			while (file_exists($filename)) {
				$i++;
				$filename = $path . $prefix . '[' . $i . ']' . $posfix;
			}
		}
		
		return $filename;
	}
	
	/**
	 * Check the empty upload file
	 * @param File $file
	 * @return boolean
	 */
	public static function isUploadEmpty($file) {
		return $file == null || $file[self::SIZE] == 0;
	}
	
	/**
	 * Copy file
	 * @param string $file
	 * @param string $toFile
	 * @return boolean
	 */
	public static function copy($file, $toFile) {
		return copy($file, $toFile);
	}
}
?>