package com.tedu.cloudnote.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CheckLoginInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		//获取请求带过来的UserId和Token
		//然后调用Dao跟cn_user表比对是否匹配
		//检测是否过期
		//通过return true，执行后续处理
		//未通过检测，返回{"status":9,"data":null,"msg":"未通过检测"} 
		//return false
		
		return false;
	}

}
