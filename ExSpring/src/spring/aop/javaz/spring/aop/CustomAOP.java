package javaz.spring.aop;

import javax.servlet.http.HttpServletRequest;

import javaz.common.logger.CustomLogger;
import javaz.hibernate.entity.User;
import javaz.spring.aop.annotation.CustomAnnotation;
import javaz.spring.service.SecurityService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Component;

import com.sun.istack.internal.NotNull;

/**
 * @author SUCCESS\tungo
 * @date Aug 6, 2014 3:34:50 PM
 */
@Component
@Aspect
public class CustomAOP {
	
	private CustomLogger log = CustomLogger.getLogger(this.getClass());
	
	@Before("execution(* javaz.spring.mvc.controller.*.*(..)) && args(request,..)")
	public void before(HttpServletRequest request) {
		log.info("before(HttpServletRequest request) -> get User object from Session");
		User user = (User) request.getSession().getAttribute(
				SecurityService.USER_AUTH);
		user.setUsername("ADMIN");
	}

	@Before("execution(* javaz.spring.mvc.controller.*.*(..)) && @annotation(cusAnnotation)")
	public void before(CustomAnnotation cusAnnotation) {
		log.info("before(CustomAnnotation cusAnnotation) -> get Annotation value");
		String value = cusAnnotation.value();
		value.hashCode();
	}

	@Before("execution(* javaz.spring.mvc.controller.*.*(..)) && target(bean)")
	public void before(@NotNull Object bean) {
		String value = bean.getClass().getName();
		value.hashCode();
	}

	@Before("bean(*Service)")
	public void beforeWithBean() {
	}
	
	@Before("execution(* org.springframework.transaction.TransactionStatus.*(..))")
	public void beforeWithTransactionStatus(JoinPoint joinPoint) {
		log.info(joinPoint.getSignature().getName());
	}

	@Around("execution(* javaz.spring.mvc.controller.*.*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		int numAttempts = 0;
		int maxRetries = 2;
		PessimisticLockingFailureException lockFailureException;
		
		do {
			numAttempts++;
			try {
				return pjp.proceed();
			} catch (PessimisticLockingFailureException ex) {
				lockFailureException = ex;
			}
		} while (numAttempts <= maxRetries);
		
		throw lockFailureException;
	}

}
