package org.shitoryu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.shitoryu.common.log.CommonLogger;
import org.shitoryu.data.dao.CategoryDAO;
import org.shitoryu.data.dao.SubCategoryDAO;
import org.shitoryu.data.dto.CategoryDTO;
import org.shitoryu.data.dto.SubCategoryDTO;
import org.shitoryu.data.entity.Category;
import org.shitoryu.data.entity.Category.CType;
import org.shitoryu.data.entity.SubCategory;
import org.shitoryu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 10:07:24 AM
 */
@SuppressWarnings("unchecked")
@Service
public class CategoryServiceImpl extends ServiceImpl implements CategoryService {
	

	static {
		System.out.println("......................Static CategoryServiceImpl test!......................");
	}

	private final CommonLogger log = CommonLogger.getLogger(this.getClass());

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private SubCategoryDAO subCategoryDAO;

	@Override
	public void init() {
		if (!CollectionUtils.isEmpty(categoryDAO.findAll())) {
			return;
		}
		log.info("--------------------------------------------------");
		log.info("Start to initialize data . . .");
		Category imageCate = new Category(CType.IMAGE.name(),
				"Hajashi images 2014");
		Category videoCate = new Category(CType.VIDEO.name(),
				"Hajashi videos 2014");
		Category bookCate = new Category(CType.BOOKS.name(),
				"Hajashi books 2014");
		categoryDAO.save(imageCate, videoCate, bookCate);

		SubCategory iSub1 = new SubCategory("Hajashi images 2014 quater 1");
		SubCategory iSub2 = new SubCategory("Hajashi images 2014 quater 2");
		SubCategory iSub3 = new SubCategory("Hajashi images 2014 quater 3");
		iSub1.setCategory(imageCate);
		iSub2.setCategory(imageCate);
		iSub3.setCategory(imageCate);
		subCategoryDAO.save(iSub1, iSub2, iSub3);

		SubCategory vSub1 = new SubCategory("Hajashi videos 2014 quater 1");
		SubCategory vSub2 = new SubCategory("Hajashi videos 2014 quater 2");
		SubCategory vSub3 = new SubCategory("Hajashi videos 2014 quater 3");
		vSub1.setCategory(videoCate);
		vSub2.setCategory(videoCate);
		vSub3.setCategory(videoCate);
		subCategoryDAO.save(vSub1, vSub2, vSub3);

		SubCategory bSub1 = new SubCategory("Hajashi books 2014 quater 1");
		SubCategory bSub2 = new SubCategory("Hajashi books 2014 quater 2");
		SubCategory bSub3 = new SubCategory("Hajashi books 2014 quater 3");
		bSub1.setCategory(bookCate);
		bSub2.setCategory(bookCate);
		bSub3.setCategory(bookCate);
		subCategoryDAO.save(bSub1, bSub2, bSub3);
		log.info("Initialize data is done!");
		log.info("--------------------------------------------------");
	}

	@Override
	public List<Map<String, Object>> findAll4Menu() {
		List<Map<String, Object>> menuItems = new ArrayList<>();

		Map<String, Object> imageItem = findOneMenuItem("Images", CType.IMAGE);
		Map<String, Object> videoItem = findOneMenuItem("Videos", CType.VIDEO);
		Map<String, Object> bookItem = findOneMenuItem("Books", CType.BOOKS);

		menuItems.add(imageItem);
		menuItems.add(videoItem);
		menuItems.add(bookItem);

		return menuItems;
	}

	private Map<String, Object> findOneMenuItem(String name, CType cType) {
		Map<String, Object> menuItem = new TreeMap<>();
		menuItem.put("name", name);

		List<Category> cats = categoryDAO.findAllByType(cType.name());
		List<Map<String, Object>> catItems = new ArrayList<>();
		for (Category cat : cats) {
			Map<String, Object> catItem = new TreeMap<>();
			catItem.put("name", cat.getName());
			catItems.add(catItem);
			List<Map<String, Object>> subItems = new ArrayList<>();
			for (SubCategory sub : cat.getSubCategories()) {
				Map<String, Object> subItem = new TreeMap<>();
				subItem.put("id", sub.getId());
				subItem.put("name", sub.getName());
				subItems.add(subItem);
			}
			catItem.put("subs", subItems);
		}

		menuItem.put("cats", catItems);
		return menuItem;
	}

	@Override
	public List<CategoryDTO> findAllCategories() {
		List<Category> cats = categoryDAO.findAll();
		return (List<CategoryDTO>) toDTOs(cats);
	}

	@Override
	public List<CategoryDTO> findAllCategoriesByType(CType ctype) {
		return findAllCategoriesByType(ctype.name());
	}

	@Override
	public List<CategoryDTO> findAllCategoriesByType(String ctype) {
		List<Category> cats = categoryDAO.findAllByType(ctype);
		return (List<CategoryDTO>) toDTOs(cats);
	}

	@Override
	public List<SubCategoryDTO> findAllSubByCategory(Integer id) {
		List<SubCategory> subCats = subCategoryDAO.findByCategory(id);
		return (List<SubCategoryDTO>) toDTOs(subCats);
	}

	@Override
	public CategoryDTO getCategory(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubCategoryDTO getSubCategory(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDTO saveCategory(Integer id, String name, String type) {
		Category cat = null;
		if (id == null) { // Add new
			cat = categoryDAO.save(new Category(type, name));
		} else { // Update
			cat = categoryDAO.get(id);
			if (cat != null) {
				cat.setName(name);
				cat.setType(type);
				categoryDAO.update(cat);
			}
		}
		return cat == null ? null : cat.toDTO();
	}

	@Override
	public SubCategoryDTO saveSubCategory(Integer id, String name,
			Integer categoryId) {
		SubCategory sub = null;
		
		Category cat = categoryDAO.get(categoryId);
		if (cat != null) {
			if (id == null) { // Add new
				sub = subCategoryDAO.save(new SubCategory(name, cat));
			} else { // Update
				sub = subCategoryDAO.get(id);
				if (sub != null) {
					sub.setName(name);
					sub.setCategory(cat);
					subCategoryDAO.update(sub);
				}
			}
		}
		
		return sub == null ? null : sub.toDTO();
	}

	@Override
	public boolean deleteCategory(Integer id) {
		return categoryDAO.delete(id);
	}

	@Override
	public boolean deleteSubCategory(Integer id) {
		return subCategoryDAO.delete(id);
	}

}
