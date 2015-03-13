package javaz.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaz.spring.aop.Authority;
import javaz.spring.aop.AuthorityPermission;
import javaz.spring.aop.AuthorityService;
import javaz.spring.aop.AuthoritySubject;
import javaz.spring.aop.AuthorityWarning;
import javaz.spring.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private AuthorityService authorityService;

	@Authority(subject = AuthoritySubject.NEWS, warning = AuthorityWarning.SECTION, permissions = {AuthorityPermission.NEWS_READ, AuthorityPermission.NEWS_WRITE})
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("news/list");

		List<Integer> ids = authorityService.getAccessableIDs();
		mv.addObject("news", newsService.findByIDs(ids));
		
		return mv;
	}

}
