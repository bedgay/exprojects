package org.shitoryu.data.init;

import javax.annotation.PostConstruct;

import org.shitoryu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataInit {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostConstruct
	public void init() {
		categoryService.init();
	}
	
}
