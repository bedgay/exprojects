package javaz.spring.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author TuNgo
 * @date Sep 20, 2014 9:13:16 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authority {
	
	/**
	 * Permission array of this authorization
	 * @return
	 */
	AuthorityPermission[] permissions();
	
	/**
	 * Ex: USER/ROLE/PERMISSION
	 * @return
	 */
	AuthoritySubject subject() default AuthoritySubject.NONE;
	
	/**
	 * Argument name of Entity ID
	 * @return
	 */
	String idArgName() default "";
	
	/**
	 * PAGE/SECTION/JSON
	 * @return
	 */
	AuthorityWarning warning() default AuthorityWarning.JSON;
}
