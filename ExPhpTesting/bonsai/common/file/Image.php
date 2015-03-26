<?php
/**
 * Process for image
 * @author Anh Tu Ngo
 * @date 15/182011
 */
final class Image extends File {
	
	const TYPE_JPG	= 'image/jpeg';
	const TYPE_PNG	= 'image/png';
	const TYPE_GIF	= 'image/gif';
	
	const EXT_JPG	= 'jpg';
	const EXT_JPEG	= 'jpeg';
	const EXT_PNG	= 'png';
	const EXT_GIF	= 'gif';
	
	public $file;
	public $name;
	public $type;
	public $size;
	public $ext;
	public $image;
	public $width;
	public $height;
	
	/**
	 * Constructor
	 * @param File $uploadImage
	 * @param number $i
	 */
	public function Image($uploadImage = null, $i = -1) {
		if ($i != -1) {
			/*
			array(5) { 
				["name"]=> array(4) { 
					[0]=> string(16) "1fo68dmdfml3.jpg" [1]=> string(16) "1jsatqkzwhi6.jpg" [2]=> string(16) "1n5aoaq0b7qe.jpg" [3]=> string(0) "" 
				} 
				["type"]=> array(4) { 
					[0]=> string(10) "image/jpeg" [1]=> string(10) "image/jpeg" [2]=> string(10) "image/jpeg" [3]=> string(0) "" 
				} 
				["tmp_name"]=> array(4) { 
					[0]=> string(24) "C:\xampp\tmp\php3581.tmp" [1]=> string(24) "C:\xampp\tmp\php3591.tmp" [2]=> string(24) "C:\xampp\tmp\php35B1.tmp" [3]=> string(0) "" 
				} 
				["error"]=> array(4) { 
					[0]=> int(0) [1]=> int(0) [2]=> int(0) [3]=> int(4) 
				} 
				["size"]=> array(4) { 
					[0]=> int(450826) [1]=> int(1217427) [2]=> int(1349305) [3]=> int(0) 
				} 
			} 
			*/
			list($this->width, $this->height) = @getimagesize($uploadImage['tmp_name'][$i]); 
			if ($this->width != null &&
					($uploadImage['type'][$i] == self::TYPE_JPG || 
						$uploadImage['type'][$i] == self::TYPE_PNG ||  
						$uploadImage['type'][$i] == self::TYPE_GIF)) {
				$this->file		= $uploadImage['tmp_name'][$i];
				$this->name		= $uploadImage['name'][$i];
				$this->type		= $uploadImage['type'][$i];
				$this->size		= $uploadImage['size'][$i];
				$this->ext		= substr($this->name, strrpos($this->name, '.') + 1);
				$this->image	= $this->getImage($this->type, $this->file);
			}			
		} else {
			list($this->width, $this->height) = @getimagesize($uploadImage['tmp_name']); 
			if ($this->width != null &&
					($uploadImage['type'] == self::TYPE_JPG || 
						$uploadImage['type'] == self::TYPE_PNG ||  
						$uploadImage['type'] == self::TYPE_GIF)) {
				$this->file		= $uploadImage['tmp_name'];
				$this->name		= $uploadImage['name'];
				$this->type		= $uploadImage['type'];
				$this->size		= $uploadImage['size'];
				$this->ext		= substr($this->name, strrpos($this->name, '.') + 1);
				$this->image	= $this->getImage($this->type, $this->file);
			}
		}
	}
	
	/**
	 * Save image as
	 * @param string $path
	 * @param string $name
	 * @param string $newWidth
	 * @param boolean $isReplace
	 */
	public function saveAs($path, $name = null, $newWidth = null, $isReplace = false) {
		if ($this->width == null) {
			return null;
		}
		
		$newHeight	= 0;
		$newExt		= '';
		
		if ($name == null)
			$name = $this->name;
		
		$name = $path . '/' . $name;
		$newExt	= substr($name, strrpos($name, '.') + 1);

		list($width, $height) = getimagesize($this->file);
		if ($newWidth != null) {
			$newHeight = ($height / $width) * $newWidth;
		} else {
			$newWidth  = $width;
			$newHeight = $height;
		}
		
		$image = imagecreatetruecolor($newWidth, $newHeight);
		imagesavealpha($image, true);
		$color = imagecolorallocatealpha($image, 0x00, 0x00, 0x00, 127); 
		imagefill($image, 0, 0, $color); 
		imagecopyresampled($image, $this->image, 0, 0, 0, 0, $newWidth, $newHeight, $width, $height);
		
		$name = $this->saveImage($image, $name, $isReplace);
		imagedestroy($image);
		//imagedestroy($this->image);
		
		return $name;
	} // Ex: $image->saveAs('../file/', null, null, false);
	
	/**
	 * Enter description here ...
	 * @param string $path
	 * @param string $name
	 * @param number $newWidth
	 * @param number $newHeight
	 * @param boolean $isReplace
	 * @return string full path of image
	 */
	public function cutAs($path, $name = null, $newWidth = null, $newHeight = null, $isReplace = false) {
		if ($this->width == null) {
			return null;
		}
		
		list($width, $height) = getimagesize($this->file);
		
		if ($newWidth == null)
			$newWidth = $width;
			
		if ($newHeight == null)
			$newHeight = $height;
			
		if ($name == null)
			$name = $this->name;
		$name = $path . '/' . $name;
			
		if ($width >= $newWidth && $height >= $newHeight) {
			$image = imagecreatetruecolor($newWidth, $newHeight);
			imagesavealpha($image, true);
			$color = imagecolorallocatealpha($image, 0x00, 0x00, 0x00, 127); 
			imagefill($image, 0, 0, $color); 
			imagecolortransparent($image, $color); 
			
			imagecopyresampled($image, $this->image, 0, 0, 0, 0, $newWidth, $newHeight, $newWidth, $newHeight);
			$name = $this->saveImage($image, $name, $isReplace);
			
			imagedestroy($image);
			//imagedestroy($this->image);
			return $name;
		}
		return null;
	} // Ex: $image->cutAs('../file/', null, null, null, false);
	
	/**
	 * Enter description here ...
	 * @param string $path
	 * @param number $width
	 * @param number $height
	 * @param string $name
	 * @param boolean $isReplace
	 * @return string
	 */
	public function thumbnail($path, $width, $height, $name = null, $isReplace = false) {
		if ($this->width == null) {
			return null;
		}
		
		$thumbWidth = $width;
		$thumbHeight = $height;
		list($width, $height) = getimagesize($this->file);
		
		if ($name == null)
			$name = $this->name;
		$name = $path . '/' . $name;
		
		//if ($width >= $thumbWidth && $height >= $thumbHeight) {
			$newWidth = ($thumbWidth / $thumbHeight) * $height;
			if ($newWidth < $width) {
				$newHeight = ($thumbHeight / $thumbWidth) * $newWidth;
			} else {
				$newHeight = ($thumbHeight / $thumbWidth) * $width;
				$newWidth = ($thumbWidth / $thumbHeight) * $newHeight;
			}
			
			$x = ($width - $newWidth) / 2;
			$y = ($height - $newHeight) / 2;
			
			$image = imagecreatetruecolor($thumbWidth, $thumbHeight);
			imagesavealpha($image, true);
			$color = imagecolorallocatealpha($image, 0x00, 0x00, 0x00, 127); 
			imagefill($image, 0, 0, $color); 
			imagecolortransparent($image, $color); 
			imagecopyresampled($image, $this->image, 0, 0, $x, $y, $thumbWidth, $thumbHeight, $newWidth, $newHeight);
			
			$name = $this->saveImage($image, $name, $isReplace);
			
			imagedestroy($image);
			//imagedestroy($this->image);
			return $name;
		//}
		//return null;
	}
	
	/**
	 * Enter description here ...
	 * @param array<string> $images
	 * @param string $toImage
	 * @param boolean $isReplace
	 * @return string
	 */
	public function mergePng($images, $toImage, $isReplace = false) {
		list($width, $height) = getimagesize($images[0]);
		$bg = imagecreatefrompng($images[0]);
		imagesavealpha($bg, true);
		//$color = imagecolorallocatealpha($bg, 0x00, 0x00, 0x00, 127); 
		$color = imagecolorallocatealpha($bg, 255, 255, 255, 127);
		//imagefill($bg, 0, 0, $color); 
		//imagecolortransparent($bg, $color); 
		
		for ($i = 1; $i < sizeof($images); $i++) {
			$layer = imagecreatefrompng($images[$i]);
			imagesavealpha($layer, true);
			//imagefill($layer, 0, 0, $color); 
			imagecolortransparent($layer, $color); 
			imagecopymerge($bg, $layer, 0, 0, 0, 0, $width, $height, 100);
		}
		
		return Image::saveImage($bg, $toImage, $isReplace);
	}
	
	/**
	 * Image destroy
	 */
	public function destroy() {
		@imagedestroy($this->image);
	}
		
	/*
		$src_img1 = imagecreatefrompng("src_img1.png");
		$src_img2 = imagecreatefrompng("src_img2.png");
		$image = imagecreatetruecolor(300, 300);
		imagesavealpha($image, true);
		imagesavealpha($src_img1, true);
		imagesavealpha($src_img2, true);
		$color = imagecolorallocatealpha($image, 255, 255, 255, 127);
		imagecolortransparent($src_img1, $color); 
		imagecolortransparent($src_img2, $color); 
		
		imagecopymerge($src_img1, $src_img2, 10, 10, 0, 0, 300, 300, 100);
	*/
	/**
	 * Enter description here ...
	 * @param string $type
	 * @param File $file
	 * @return Image
	 */
	private function getImage($type, $file) {
		$image = null;
		
		switch($type) {
			case self::TYPE_JPG : $image = imagecreatefromjpeg($file);
				break;
			case self::TYPE_PNG : $image = imagecreatefrompng($file);
				break;
			case self::TYPE_GIF : $image = imagecreatefromgif($file);
				break;
		}
		
		return $image;
	}
	
	/**
	 * Save image
	 * @param Image $image
	 * @param string $imageName
	 * @param string $isReplace
	 */
	private function saveImage($image, $imageName, $isReplace) {
		$newExt	= substr($imageName, strrpos($imageName, '.') + 1);
	
		if (!$isReplace) {
			$imageName = $this->checkFile($imageName);
		}
		
		switch($newExt) {
			case self::EXT_JPG : imagejpeg($image, $imageName, 100); 
				break;
			case self::EXT_JPEG : imagejpeg($image, $imageName, 100); 
				break;
			case self::EXT_PNG : imagepng($image, $imageName, 0, PNG_ALL_FILTERS); 
				break;
			case self::EXT_GIF : imagegif($image, $imageName); 
				break;
			default: $imageName = null;
		}
		
		return $imageName;
	}
}
?>