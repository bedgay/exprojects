package javaz.spring.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaz.common.logger.CustomLogger;
import javaz.hibernate.entity.User;
import javaz.spring.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author SUCCESS\tungo
 * @date Aug 5, 2014 2:38:17 PM
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private CustomLogger log = CustomLogger.getLogger(this.getClass());

	@Autowired
	private SecurityService securityService;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                   Authentication authentication) throws ServletException, IOException {
    	log.info("Login success handler here!");
    	log.info("Session set up for 60min");
        request.getSession().setMaxInactiveInterval(60 * 60); //one hour
        
        User user = securityService.findUserByUsername(authentication.getName());
        if (user != null) {
        	request.getSession().setAttribute(SecurityService.USER_AUTH, user);
        }
        
        super.onAuthenticationSuccess(request, response, authentication);
     }
}
