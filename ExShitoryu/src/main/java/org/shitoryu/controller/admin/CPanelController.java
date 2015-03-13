package org.shitoryu.controller.admin;

import org.shitoryu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 1:13:56 PM
 */
@Controller
@RequestMapping("/cpanel")
public class CPanelController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView cpanel() {
		ModelAndView mv = new ModelAndView("admin/cpanel");
		mv.addObject("menuData", categoryService.findAll4Menu());
		return mv;
	}
	
}
