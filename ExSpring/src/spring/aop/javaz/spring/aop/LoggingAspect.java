package javaz.spring.aop;

import java.util.Date;
import java.util.Hashtable;

import javaz.common.logger.CustomLogger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.dao.PessimisticLockingFailureException;

/**
 * @author SUCCESS\tungo
 * @date Aug 6, 2014 3:26:20 PM
 */
@Aspect
public class LoggingAspect {

	private CustomLogger log = CustomLogger.getLogger(this.getClass());

	private Hashtable<Integer, Date> content = new Hashtable<>();

	@Before("execution(* javaz.spring.mvc.controller.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		content.put(joinPoint.hashCode(), new Date());
		log.info("@Before : " + joinPoint.getSignature().getName());
	}

	@After("execution(* javaz.spring.mvc.controller.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		Date before = content.get(joinPoint.hashCode());
		Date after = new Date();
		log.info("@After : " + joinPoint.getSignature().getName() + " "
				+ (after.getTime() - before.getTime()) + " ms");
		content.remove(joinPoint.hashCode());
	}

	@AfterReturning(pointcut = "execution(* javaz.spring.mvc.controller.*.*(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("@AfterReturning : " + joinPoint.getSignature().getName()
				+ " " + result);
	}

	@AfterThrowing(pointcut = "execution(* javaz.spring.mvc.controller.*.*(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		log.info("@AfterThrowing : " + joinPoint.getSignature().getName() + " "
				+ (error == null ? "" : error));
	}
	
	@Around("execution(* javaz.spring.mvc.controller.*.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		PessimisticLockingFailureException lockFailureException;
		log.info("@Around : " + joinPoint.getSignature().getName() + " ");
		
		try {
			return joinPoint.proceed();
		} catch (PessimisticLockingFailureException ex) {
			lockFailureException = ex;
		}
		
		throw lockFailureException;
	}

}
