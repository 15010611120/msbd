package com.yxd.intercptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	  
		@Override  
	     public  boolean preHandle(HttpServletRequest request,  
	            HttpServletResponse response, Object handler) throws Exception { 
	        String url = request.getRequestURI();  
	        if(url.endsWith("login"))
	        	return true;
	        HttpSession session = request.getSession();
	        if(session != null && session.getAttribute("email") != null)   
	            return true;  
	        if (request.getHeader("x-requested-with") != null  
	                && request.getHeader("x-requested-with")  
	                        .equalsIgnoreCase("XMLHttpRequest"))//如果是ajax请求响应头会有，x-requested-with；  
	        {  
	            response.setHeader("sessionstatus", "timeout");//在响应头设置session状态 
	            return false;
	        }
	        response.sendRedirect(request.getContextPath() + "/login");      
	        return false;
	    }  

}
