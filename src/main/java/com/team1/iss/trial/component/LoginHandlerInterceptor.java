package com.team1.iss.trial.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * login check
 * @author WUYUDING
 *
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        Object user = request.getSession().getAttribute("loginUser");
	        if(user == null){
	            //not login go back to login page
	            request.setAttribute("msg","authority disabled");
	            request.getRequestDispatcher("/").forward(request, response);
	            return false;
	        }else{
	            //has loggin
	            return true;
	        }

	    }

	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	    }
}
