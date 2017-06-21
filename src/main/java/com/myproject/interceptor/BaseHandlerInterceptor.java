package com.myproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class BaseHandlerInterceptor implements HandlerInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(BaseHandlerInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView modelAndView) throws Exception {
		if(request.getServletPath().endsWith(".json")){
			//如果是ajax的请求，则在控制台中打印出返回的结果
			logger.info("=========="+response.getStatus()+":"+response.toString());
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
	
		return true;
	}

}
