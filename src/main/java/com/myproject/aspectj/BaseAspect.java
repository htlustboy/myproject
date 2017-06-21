package com.myproject.aspectj;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BaseAspect {
	
	private static Logger logger = LoggerFactory.getLogger(BaseAspect.class);
	
	@Pointcut("execution(public * com.myproject.service.*.*Service.*(..))")
	public void pointCut(){  }
	
	@Pointcut("execution(public * com.myproject.controller.*.*DataController.*(..))")
	public void pointCut2(){  }
	
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint pointJoinPoint) throws Throwable{
		logger.info("执行"+pointJoinPoint.getSignature()+"方法...");
		try {
			Object result = pointJoinPoint.proceed();
			return result;
		} catch (Exception e) {
			logger.info("目标方法发生错误..."+e.getMessage());
			return null;
		}
	}
	
	@Around("pointCut2()")
	public Object around2(ProceedingJoinPoint pointJoinPoint) throws Throwable{
		try {
			Object result = pointJoinPoint.proceed();
			logger.info("result:"+result.toString());
			return result;
		} catch (Exception e) {
			logger.info(pointJoinPoint.getClass().getSimpleName()+"方法发生错误..."+e.getMessage());
			return null;
		}
	}
	
}
