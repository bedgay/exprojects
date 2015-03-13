package javaz.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaz.data.consts.MsgConst;
import javaz.data.dto.UserDTO;
import javaz.data.json.JsonObject;
import javaz.data.json.JsonObject.ErrorCode;
import javaz.hibernate.entity.User;
import javaz.spring.aop.Authority;
import javaz.spring.aop.AuthorityPermission;
import javaz.spring.aop.AuthorityService;
import javaz.spring.aop.AuthoritySubject;
import javaz.spring.aop.AuthorityWarning;
import javaz.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author TuNgo
 * @date Sep 22, 2014 10:47:53 PM
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	public static final String CURRENT_USER = "CurrentUser";

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "permis/user/login";
	}

	@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public JsonObject login(User user, HttpServletRequest req, HttpServletResponse res) {
		UserDTO userDTO = userService.login(user);
		if (userDTO == null) {
			return JsonObject.wrapError(messageSource.getMessage(MsgConst.LOGIN_MSG_FAIL, null, "Default", null));
		}
		req.getSession().setAttribute(CURRENT_USER, userDTO);
		return JsonObject.wrapData(userDTO);
	}

	@RequestMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public JsonObject logout(HttpServletRequest req, HttpServletResponse res) {
		req.getSession().removeAttribute(CURRENT_USER);
		return JsonObject.wrapData("Thanks you!");
	}

	@Authority(subject = AuthoritySubject.USER, permissions = {AuthorityPermission.USER_READ})	
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody 
	public JsonObject list(HttpServletRequest req, HttpServletResponse res) {
		List<Integer> userIDs = authorityService.getAccessableIDs();
		return JsonObject.wrapData(userService.findAllUser());
	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ModelAndView rolesForm(Integer userId, HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("permis/user/roles");
		mv.addObject("user", userService.getUser(userId));
		return mv;
	}

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	@ResponseBody
	public JsonObject rolesSave(Integer userId, Integer[] roleIDs, HttpServletRequest req, HttpServletResponse res) {
		if (userService.applyRoles4User(userId, roleIDs)) {
			return JsonObject.wrapData(true);
		} else {
			return JsonObject.wrapError(ErrorCode.SAVE_ERROR.value(), messageSource.getMessage("user.roles.apply.fail.msg", new Object[]{}, req.getLocale()));
		}
	}

}
