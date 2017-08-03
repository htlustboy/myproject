package com.myproject.interceptor;


import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.myproject.annotation.FormData;
import com.myproject.util.Resultinfo;

public class FileUploadInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(FileUploadInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView modelAndView) throws Exception {
		
		for(Entry<String, Object> entry : modelAndView.getModelMap().entrySet()){
			if(entry.getKey().equals("resultinfo")){
				Resultinfo result = (Resultinfo) entry.getValue();
				if(result.getMessage()!=null && result.getMessage().length()>0){
					modelAndView.addObject("message", result.getMessage());
				}
				modelAndView.setViewName(result.getUrl());
				logger.info("result:"+result.getMessage());
				break;
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			FormData annotation = method.getAnnotation(FormData.class);
			if(annotation!=null){
				boolean need2SaveSession = annotation.save();
				if(need2SaveSession){
					request.getSession(false).setAttribute("token", UUID.randomUUID().toString().replaceAll("-",""));
				}
			}
			boolean need2RemoveSession = annotation.remove();
			if(need2RemoveSession){
				if(isRepearSubmit(request)){
					response.sendRedirect("/toerror");
					return false;
				}
				request.getSession(false).removeAttribute("token");
			}
			return true;
		}else{
			return true;
		}
	}
	
	
	private boolean isRepearSubmit(HttpServletRequest request){
		String token = (String)request.getSession(false).getAttribute("token");
		if(token==null){
			return true;
		}
		String clientToken = request.getParameter("token");
		if(clientToken==null){
			return true;
		}
		if(!token.equals(clientToken)){
			return true;
		}
		return false;
	}
}
