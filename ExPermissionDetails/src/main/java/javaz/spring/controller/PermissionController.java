package javaz.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaz.data.json.JsonObject;
import javaz.data.json.JsonObject.ErrorCode;
import javaz.spring.aop.Authority;
import javaz.spring.aop.AuthorityPermission;
import javaz.spring.aop.AuthorityWarning;
import javaz.spring.service.PermissionService;
import javaz.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 5:40:19 PM
 */
@Controller
@RequestMapping("/")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;

	@Authority(permissions = {AuthorityPermission.PERMIS_READ, AuthorityPermission.PERMIS_WRITE})	
	@RequestMapping(value = "/permis/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public JsonObject permisList(HttpServletRequest req, HttpServletResponse res) {
		return JsonObject.wrapData(permissionService.findAllPermis());
	}

	@Authority(permissions = {AuthorityPermission.ROLE_READ, AuthorityPermission.ROLE_WRITE})	
	@RequestMapping(value = "/role/list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public JsonObject roleList(HttpServletRequest req, HttpServletResponse res) {
		return JsonObject.wrapData(permissionService.findAllRole());
	}

	@RequestMapping(value="/role/edit", method = RequestMethod.GET)
	public ModelAndView roleEdit(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("permis/role/edit");
		mv.addObject("permissions", permissionService.findAllPermis());
		return mv;
	}

	@RequestMapping(value="/role/edit", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public JsonObject roleEdit(Integer roleId, String name, Integer[] permisIDs, HttpServletRequest req, HttpServletResponse res) {
		if (permissionService.saveRole(roleId, name, permisIDs)) {
			return JsonObject.wrapData(true);
		} else {
			return JsonObject.wrapError(ErrorCode.SAVE_ERROR.value(), messageSource.getMessage("user.permis.apply.fail.msg", new Object[]{}, req.getLocale()));
		}
	}
	
	// Apply Roles to User for a Subject
	//--------------------------------------------------------------------------
	@Authority(permissions = {AuthorityPermission.USER_WRITE, AuthorityPermission.ROLE_WRITE}, warning = AuthorityWarning.SECTION)	
	@RequestMapping(value="/role/apply", method = RequestMethod.GET)
	public ModelAndView applyRoleForm(String subject, Integer subjectId, HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("permis/role/apply");
		mv.addObject("subject", subject);
		mv.addObject("subjectId", subjectId);
		mv.addObject("roles", permissionService.findAllRole(subject, subjectId));
		return mv;
	}

	@Authority(permissions = {AuthorityPermission.USER_WRITE, AuthorityPermission.ROLE_WRITE})	
	@RequestMapping(value="/role/apply", method = RequestMethod.POST)
	@ResponseBody
	public JsonObject applyRole(String subject, Integer subjectId, Integer[] roleIDs,
			HttpServletRequest req, HttpServletResponse res) {
		if (permissionService.applyRole(subject, subjectId, roleIDs)) {
			return JsonObject.wrapData(true);
		}
		return JsonObject.wrapError(ErrorCode.SAVE_ERROR.value(), messageSource.getMessage("user.roles.detail.apply.fail.msg", new Object[]{}, req.getLocale()));
	}
	
	// Error controller actions
	//--------------------------------------------------------------------------
	@RequestMapping(value="/permis/error/page")
	public ModelAndView errorPage() {
		ModelAndView mv = new ModelAndView("permis/permis/error/page");
		return mv;
	}

	@RequestMapping(value = "/permis/error/section", produces = "text/html")
	public void errorSection(HttpServletRequest request, HttpServletResponse response) throws NoSuchMessageException, IOException {
		response.getWriter().write(messageSource.getMessage("permission.error.msg", new Object[]{}, request.getLocale()));
	}

	@RequestMapping(value = "/permis/error/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JsonObject errorJson(HttpServletRequest request) {
		return JsonObject.wrapError(messageSource.getMessage("permission.error.msg", new Object[]{}, request.getLocale()));
	}

}
