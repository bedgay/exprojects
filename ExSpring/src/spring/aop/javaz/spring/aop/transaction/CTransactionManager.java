package javaz.spring.aop.transaction;

import java.util.Map;
import java.util.TreeMap;

import javaz.spring.aop.annotation.CTransaction;
import javaz.spring.aop.transaction.session.CSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * @author SUCCESS\tungo
 * @date Aug 11, 2014 5:24:44 PM
 */
@Component
@Aspect
public class CTransactionManager {
	
	private Map<Integer, CSession> map = new TreeMap<>();

	@Before("execution(public * *(..)) && @annotation(cTransaction) && args(cSession ,..)")
	public void before(JoinPoint joinPoint, CTransaction cTransaction, CSession cSession) {
		map.put(joinPoint.hashCode(), cSession);
	}

//	@AfterThrowing(pointcut = "execution(public * *(..)) && @annotation(cTransaction)", throwing = "error")
//	public void afterThrowing(JoinPoint joinPoint, Throwable error, CTransaction cTransaction) {
//		map.get(joinPoint.hashCode()).clean();
//	}
//
//	@AfterReturning("execution(public * *(..)) && @annotation(cTransaction)")
//	public void afterReturning(JoinPoint joinPoint, CTransaction cTransaction) {
//		CSession session = map.get(joinPoint.hashCode());
//		if (session.isComplete()) {
//			session.flush();
//		} else {
//			session.clean();
//		}
//		map.remove(joinPoint.hashCode());
//	}

	@After("execution(public * *(..)) && @annotation(cTransaction)")
	public void after(JoinPoint joinPoint, CTransaction cTransaction) {
		CSession session = map.get(joinPoint.hashCode());
		if (session.isComplete()) {
			session.flush();
		} else {
			session.clean();
		}
		map.remove(joinPoint.hashCode());
	}

}
