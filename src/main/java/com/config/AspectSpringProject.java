package com.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectSpringProject {

	private static Logger logger = LogManager.getLogger(AspectSpringProject.class);

	@Pointcut("execution(* com..*.*(..))")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void before() {
		System.out.println("before");
	}

	@AfterReturning(pointcut = "pointcut()", returning = "returnValue")
	public void afterReturning(Object returnValue) {
		System.out.println("AfterReturning : " + returnValue);
	}

	@AfterThrowing(pointcut = "pointcut()", throwing = "e")
	public void afterThrowing(Exception e) {
		e.printStackTrace();
		System.out.println("AfterThrowing : " + e.getMessage());
		logger.error("AOP", e);
	}

	@After("pointcut()")
	public void after() {
		System.out.println("After.");
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Around 1.");
		Object obj = pjp.proceed();
		System.out.println("Around 2.");
		System.out.println("Around : " + obj);
		return obj;
	}
}
