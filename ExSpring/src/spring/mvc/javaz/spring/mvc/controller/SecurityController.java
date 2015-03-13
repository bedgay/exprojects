package javaz.spring.mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaz.common.consts.SecurityConst;
import javaz.common.logger.CustomLogger;
import javaz.hibernate.entity.User;
import javaz.spring.event.publisher.CustomEventPublisher;
import javaz.spring.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/security")
public class SecurityController extends AbstractController {
	
	private CustomLogger log = CustomLogger.getLogger(this.getClass());
	
	@Autowired
	private SecurityService securityService;

	@Autowired
	private CustomEventPublisher securityEventPublisher;

	@RequestMapping(value = "/main", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.ALL_VALUE, method = RequestMethod.GET)
	public String main(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "jsp/main";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Map<String, Object> loginPost(HttpServletRequest request,
			HttpServletResponse response, User user, Model model)
			throws Exception {
		log.info("loginPost");
		Map<String, Object> data = this.createModel(Boolean.TRUE, Boolean.TRUE);
		Boolean result = securityService.checkLogin(user);
		data.put("RESULT", result);
		
		if (result) {
			request.getSession().setAttribute(SecurityConst.SESSION_USER, user.getUsername());
			request.getServletContext().setAttribute(SecurityConst.CONTEXT_USER, user.getUsername());
		}
		
//		securityEventPublisher.publish("Login action: " + user.getUsername() + " login " + (result ? "success" : "fail") + "!");
//		securityEventPublisher.asynPublish("Login action: " + user.getUsername() + " login " + (result ? "success" : "fail") + "!");
		securityEventPublisher.publish("Login action: " + user.getUsername() + " login " + (result ? "success" : "fail") + "!");
		return data;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Map<String, Object> logout(HttpServletRequest request,
			HttpServletResponse response, Model model)
			throws Exception {
		log.info("logout");
		Map<String, Object> data = this.createModel(Boolean.TRUE, Boolean.TRUE);
		data.put("RESULT", true);
		request.getSession().removeAttribute(SecurityConst.SESSION_USER);
		request.getServletContext().removeAttribute(SecurityConst.CONTEXT_USER);
		return data;
	}

}
