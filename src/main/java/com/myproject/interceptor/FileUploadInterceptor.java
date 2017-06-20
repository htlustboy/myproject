package com.myproject.interceptor;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
				if(repeatDataVaildator(request)){
					logger.warn("表单重复提交了，上传失败");
					return false;
				}else{
					return true;
				}
			}
			return true;
		}else{
			return true;
		}
		
	}

	/**
	 * 验证同一个url数据是否相同提交，相同则返回false
	 * @param request
	 * @return
	 */
	private boolean repeatDataVaildator(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		Map<String, String> map = new HashMap<String,String>();
		map.put(url, request.getParameterMap().toString());
		Object token = request.getSession().getAttribute("token");
		if(token==null){
			request.getSession().setAttribute("token", map.toString());
			return false;
		}else{
			if(token.toString().equals(map.toString())){
				return true;
			}else{
				request.getSession().setAttribute("token",  map.toString());
				return false;
			}
		}
	}

}
