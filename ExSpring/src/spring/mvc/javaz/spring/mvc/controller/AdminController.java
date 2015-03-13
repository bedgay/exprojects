package javaz.spring.mvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaz.common.logger.CustomLogger;
import javaz.hibernate.entity.User;
import javaz.spring.aop.annotation.CustomAnnotation;
import javaz.spring.aop.transaction.session.CSession;
import javaz.spring.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author SUCCESS\tungo
 * @date Aug 5, 2014 9:55:13 AM
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {
	
	private CustomLogger log = CustomLogger.getLogger(this.getClass());

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/cpanel", method = RequestMethod.GET)
	public ModelAndView cpanel() throws Exception {
		log.info("cpanel() GET");
		return new ModelAndView("jsp/admin/cpanel");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		log.info("login() GET");
		return new ModelAndView("jsp/admin/login");
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> accessDenied() {
		log.info("accessDenied() GET");
		return createModel(Boolean.FALSE, Boolean.FALSE);
	}
	
	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> loginSuccess() {
		log.info("loginSuccess() GET");
		return createModel(Boolean.TRUE, Boolean.TRUE);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CustomAnnotation("Test for AOP annotation pointcut")
	public @ResponseBody Map<String, Object> users(HttpServletRequest request, HttpServletResponse response) {
		log.info("users(..) GET");
		
		User user = (User) request.getSession().getAttribute(SecurityService.USER_AUTH);
		user.getUsername();
		
		Map<String, Object> model = createModel(Boolean.TRUE, Boolean.TRUE);
		List<User> users = securityService.findAllUserWithGroup();
		
		try {
			securityService.generateAllUserFiles(new CSession(), users);
		} catch (IOException e) {
			log.error(e);
		}
		
		model.put(MODEL_DATA, users);
		return model;
	}	

	/**
	 * Test for AOP @AfterThrowing
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/throwe", method = RequestMethod.GET)
	public ModelAndView throwe() throws Exception {
		log.info("throwe() GET");
		throw(new Exception("Throw exception so that AOP will catch it."));
	}	

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public Boolean addUser(/*@RequestBody*/ User user) {
		return false;
	}
	
}
