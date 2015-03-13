package org.shitoryu.service;

import java.util.List;
import java.util.Map;

import org.shitoryu.data.dto.CategoryDTO;
import org.shitoryu.data.dto.SubCategoryDTO;
import org.shitoryu.data.entity.Category.CType;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 9:54:58 AM
 */
public interface CategoryService extends Service {

	/**
	 * Initialize data for the first time
	 */
	void init();
	
	/**
	 * @return
	 */
	List<Map<String, Object>> findAll4Menu();

	/**
	 * @return
	 */
	List<CategoryDTO> findAllCategories();

	/**
	 * @param ctype
	 * @return
	 */
	List<CategoryDTO> findAllCategoriesByType(CType ctype);
	
	/**
	 * @param ctype
	 * @return
	 */
	List<CategoryDTO> findAllCategoriesByType(String ctype);
	
	/**
	 * @param id
	 * @return
	 */
	List<SubCategoryDTO> findAllSubByCategory(Integer id);
	
	/**
	 * @param id
	 * @return
	 */
	CategoryDTO getCategory(Integer id);

	/**
	 * @param id
	 * @return
	 */
	SubCategoryDTO getSubCategory(Integer id);
	
	/**
	 * @param id
	 * @param name
	 * @return
	 */
	CategoryDTO saveCategory(Integer id, String name, String type);

	/**
	 * @param id
	 * @param name
	 * @param categoryId
	 * @return
	 */
	SubCategoryDTO saveSubCategory(Integer id, String name, Integer categoryId);
	
	/**
	 * @param id
	 * @return
	 */
	boolean deleteCategory(Integer id);
	
	/**
	 * @param id
	 * @return
	 */
	boolean deleteSubCategory(Integer id);
	
}
