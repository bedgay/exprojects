package org.shitoryu.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.shitoryu.data.dto.AlbumDTO;
import org.shitoryu.data.dto.BookDTO;
import org.shitoryu.data.dto.CategoryDTO;
import org.shitoryu.data.dto.DTO;
import org.shitoryu.data.dto.SubCategoryDTO;
import org.shitoryu.data.dto.VideoDTO;
import org.shitoryu.data.entity.Category.CType;
import org.shitoryu.data.json.JSon;
import org.shitoryu.data.json.JSon.JSonResult;
import org.shitoryu.service.BookService;
import org.shitoryu.service.CategoryService;
import org.shitoryu.service.ImageService;
import org.shitoryu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 1:09:43 PM
 */
@Controller
@RequestMapping("/cats")
public class CategoryController {

	@Autowired
	private CategoryService categoryService; 

	@Autowired
	private ImageService imageService; 
	@Autowired
	private VideoService videoService; 
	@Autowired
	private BookService bookService; 
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public JSon<CategoryDTO> list(Integer jtStartIndex, Integer jtPageSize) {
		return new JSon<CategoryDTO>(JSonResult.OK, categoryService.findAllCategories());
	}

	@RequestMapping(value = "/options")
	@ResponseBody
	public JSon<CategoryDTO> options() {
		List<CategoryDTO> cats = categoryService.findAllCategories();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
		for (CategoryDTO cat : cats) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Value", cat.getId());
			map.put("DisplayText", cat.getType() + "-" + cat.getName());
			list.add(map);
		}
		return new JSon<CategoryDTO>(JSonResult.OK.name(), "list", list);
	}

	@RequestMapping(value = "/subList")
	@ResponseBody
	public JSon<SubCategoryDTO> subList(Integer categoryId, Integer jtStartIndex, Integer jtPageSize) {
		return new JSon<SubCategoryDTO>(JSonResult.OK, categoryService.findAllSubByCategory(categoryId));
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public JSon<CategoryDTO> save(Integer id, String name, String type) {
		CategoryDTO cat = categoryService.saveCategory(id, name, type);
		if (cat != null) {
			return new JSon<CategoryDTO>(JSonResult.OK, cat);
		} else {
			return new JSon<CategoryDTO>(JSonResult.ERROR, "Can not save Category.");
		}
	}

	@RequestMapping(value = "/subSave")
	@ResponseBody
	public JSon<SubCategoryDTO> subSave(Integer id, String name, Integer categoryId) {
		SubCategoryDTO sub = categoryService.saveSubCategory(id, name, categoryId);
		if (sub != null) {
			return new JSon<SubCategoryDTO>(JSonResult.OK, sub);
		} else {
			return new JSon<SubCategoryDTO>(JSonResult.ERROR, "Can not save SubCategory.");
		}
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public JSon<CategoryDTO> del(Integer id) {
		if (categoryService.deleteCategory(id)) {
			return new JSon<CategoryDTO>(JSonResult.OK);
		} else {
			return new JSon<CategoryDTO>(JSonResult.ERROR, "Can not delete Category.");
		}
	}

	@RequestMapping(value = "/subDel")
	@ResponseBody
	public JSon<SubCategoryDTO> subDel(Integer id) {
		if (categoryService.deleteSubCategory(id)) {
			return new JSon<SubCategoryDTO>(JSonResult.OK);
		} else {
			return new JSon<SubCategoryDTO>(JSonResult.ERROR, "Can not delete Sub Category.");
		}
	}

	@RequestMapping(value = "/details")
	@ResponseBody
	public JSon<? extends DTO> details(String type, Integer subCategoryId) {
		if (CType.IMAGE.name().equals(type)) {
			return new JSon<AlbumDTO>(JSonResult.OK, imageService.findAllAlbums(subCategoryId));
		} else if (CType.VIDEO.name().equals(type)) {
			return new JSon<VideoDTO>(JSonResult.OK, videoService.findAllVideos(subCategoryId));
		} else if (CType.BOOKS.name().equals(type)) {
			return new JSon<BookDTO>(JSonResult.OK, bookService.findAllBooks(subCategoryId));
		}
		return new JSon<DTO>(JSonResult.ERROR, "Can not open the detail of " + type);
	}

}
