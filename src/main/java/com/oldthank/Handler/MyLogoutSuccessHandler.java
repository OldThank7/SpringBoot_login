package com.oldthank.Handler;

import com.oldthank.utils.Result.Result;
import com.oldthank.utils.Result.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();

		System.out.println("session = " + session);

		if (session != null){
			Map<String,Object> map = new HashMap<>();

			Result success = new Result(ResultCode.LOGOUT_SUCCESS,"操作成功！");

			map.put("successInfo",success);
			map.put("authenticationInfo",authentication);

			response.setContentType("application/json;charset=UTF-8");

			response.getWriter().println(map);
		}else {
			response.setContentType("application/json;charset=UTF-8");

			response.getWriter().println("还未登陆，不需要退出！");
		}
	}
}
