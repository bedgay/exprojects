package javaz.spring.controller;

import javax.servlet.http.HttpServletResponse;

import javaz.spring.aop.Authority;
import javaz.spring.aop.AuthorityPermission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author TuNgo
 * @date Sep 22, 2014 10:39:42 PM
 */
@Controller
@RequestMapping("/cpanel")
public class CPanelController {

//	@Authority(permissions = {AuthorityPermission.USER_READ})	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView cpanel(HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("cpanel");
		return mv;
	}

}
