package javaz.spring.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaz.data.dto.UserDTO;
import javaz.spring.config.AppConfig;
import javaz.spring.controller.UserController;
import javaz.spring.service.PermissionService;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author TuNgo
 * @date Sep 20, 2014 9:16:02 PM
 */
@Component 
@Aspect
public class AuthorityService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private static PermissionService permissionService;
	private static AppConfig appConfig;

	@Value("${permission.error.page}")
	private String errorPageUrl;
	@Value("${permission.error.section}")
	private String errorSectionUrl;
	@Value("${permission.error.json}")
	private String errorJsonUrl;

//	@Before("execution(* javaz.spring.controller.*.*(..)) && @annotation(authority)")
//	public void before(JoinPoint joinPoint, Authority authority) {
//
//	}
//

	@Autowired
	public AuthorityService(PermissionService permissionServicem, AppConfig appConfigem) {
		permissionService = permissionServicem;
		appConfig = appConfigem;
	}
	
	@After("execution(* javaz.spring.controller.*.*(..)) && @annotation(authority)")
	public void after(JoinPoint joinPoint, Authority authority) {
		log.error("abc");
	}

	@Around("execution(* javaz.spring.controller.*.*(..)) && @annotation(authority)")
	public Object around(ProceedingJoinPoint joinPoint, Authority authority)
			throws Throwable {
		Object result = null;

		AuthorityPermission[] permissions = authority.permissions();
		AuthoritySubject subject = authority.subject();
		String idArgName = authority.idArgName();
		AuthorityWarning warning = authority.warning();

		String sessionName = joinPoint.getSignature().getDeclaringType()
				.getName()
				+ "." + joinPoint.getSignature().getName();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();

		// Bypass permission from configuration
		if (appConfig.isPermissionBypass()) {
			if (StringUtils.isNotEmpty(idArgName)) {
				result = Boolean.TRUE;
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(0);
				result = list;
			}
			request.getSession().setAttribute(sessionName, result);
			return joinPoint.proceed();
		} else {
			Object obj = request.getSession().getAttribute(UserController.CURRENT_USER);
			UserDTO userDTO;
			if (obj == null) {
				result = null;
			} else {
				userDTO = (UserDTO) obj;
				if (subject.equals(AuthoritySubject.NONE)) {
					result = permissionService.checkPermis(userDTO, permissionService.castPermissions(permissions));
				} else {
					if (StringUtils.isNotEmpty(idArgName)) { // Check permissions for one Subject
						result = checkPermissionDataWithID(joinPoint, subject.name(),
								permissions, idArgName, request, userDTO);
					} else { // Check permissions for many Subjects
						result = permissionService.checkPermisForAll(userDTO, subject.name(), permissionService.castPermissions(permissions));
					}
				}
			}
		}
		
		if (result == null || 
				(result instanceof List && ((List<?>) result).size() == 0)
				|| (result instanceof Boolean && !((Boolean) result))) {
			HttpServletResponse response = null;
			for (Object obj : joinPoint.getArgs()) {
				if (obj instanceof HttpServletResponse) {
					response = (HttpServletResponse) obj;
					break;
				}
			}
			
			if (response != null) {
				if (warning.equals(AuthorityWarning.PAGE)) {
					response.sendRedirect(errorPageUrl);
				} else if (warning.equals(AuthorityWarning.SECTION)) {
					response.sendRedirect(errorSectionUrl);
				} else {
					response.sendRedirect(errorJsonUrl);
				}
			} else {
				log.error(sessionName + " does not have HttpServletResponse arg");
			}
			return null;
		}
		
		request.getSession().setAttribute(sessionName, result);
		return joinPoint.proceed();
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getAccessableIDs() {
		Object result = preCheck();
		if (result != null && result instanceof List) {
			return (List<Integer>) result;
		}	
		return new ArrayList<>();
	}

	/**
	 * @param subject
	 * @param subjectId
	 * @param permis
	 * @return
	 */
	public static boolean canAccess(String subject, Integer subjectId, String permis) {
		if (appConfig.isPermissionBypass()) {
			return Boolean.TRUE;
		}
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		Object obj = request.getSession().getAttribute(UserController.CURRENT_USER);
		
		if (obj == null) {
			return Boolean.FALSE;
		} else {
			return permissionService.checkPermisForOne((UserDTO) obj, subject, permis.split(","), subjectId);
		}
	}
	
	/**
	 * @param permis
	 * @return
	 */
	public static boolean canAccess(String permis) {
		if (appConfig.isPermissionBypass()) {
			return Boolean.TRUE;
		}
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		Object obj = request.getSession().getAttribute(UserController.CURRENT_USER);
		
		if (obj == null) {
			return Boolean.FALSE;
		} else {
			return permissionService.checkPermis((UserDTO) obj, permis.split(","));
		}
	}

	private Object preCheck() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest();
		StackTraceElement[] items = new Exception().getStackTrace();
		String sessionName = items[2].getClassName() + "." + items[2].getMethodName();
		Object result = request.getSession().getAttribute(sessionName);
		return result;
	}

	private Object checkPermissionDataWithID(ProceedingJoinPoint joinPoint,
			String subject, AuthorityPermission[] permissions, String idArgName,
			HttpServletRequest request, UserDTO userDTO) {
		Object result = null;
		String subjectId = request.getParameter(idArgName);
		if (!StringUtils.isEmpty(subjectId)) {
			// Get action method
			Method method = null;
			for (Method m : joinPoint.getSignature().getDeclaringType()
					.getDeclaredMethods()) {
				if (m.getName().equals(
						joinPoint.getSignature().getName())) {
					method = m;
					break;
				}
			}

			// Check argument name
			DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
			int index = -1;
			String[] args = discoverer.getParameterNames(method);
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals(idArgName)) {
					index = i;
					break;
				}
			}

			if (index >= 0) {
				// If exist the idArgName
				subjectId = String.valueOf(joinPoint.getArgs()[index]);
				result = permissionService.checkPermisForOne(userDTO, subject,
							permissionService.castPermissions(permissions),
							Integer.valueOf(subjectId));
			} else {
				// If not exist the idArgName
				result = Boolean.FALSE;
			}
		}
		return result;
	}

}
