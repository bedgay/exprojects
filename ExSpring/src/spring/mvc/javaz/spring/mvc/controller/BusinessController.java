package javaz.spring.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaz.spring.service.BusinessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/business")
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;

	@RequestMapping("/countries/init")
	public String init(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		businessService.initCountries();
		
		return "helloworld";
	}

}
