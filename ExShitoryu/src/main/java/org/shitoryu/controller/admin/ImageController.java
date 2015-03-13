package org.shitoryu.controller.admin;

import org.shitoryu.data.dto.CategoryDTO;
import org.shitoryu.data.json.JSon;
import org.shitoryu.data.json.JSon.JSonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author SUCCESS\tungo
 * @date Nov 7, 2014 4:12:31 PM
 */
@Controller
@RequestMapping("/imgs")
public class ImageController {

	@RequestMapping(value = "/save")
	@ResponseBody
	public JSon<CategoryDTO> saveAlBum(Integer id, String name, Integer subCategoryId) {
		CategoryDTO cat = null;//categoryService.saveCategory(id, name, type);
		if (cat != null) {
			return new JSon<CategoryDTO>(JSonResult.OK, cat);
		} else {
			return new JSon<CategoryDTO>(JSonResult.ERROR, "Can not save Category.");
		}
	}
	
}
