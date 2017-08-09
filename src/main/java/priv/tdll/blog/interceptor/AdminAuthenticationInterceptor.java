package priv.tdll.blog.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import priv.tdll.blog.common.MyException;
import priv.tdll.blog.common.ResponseCode;
import priv.tdll.blog.entity.SysUserEntity;
import priv.tdll.blog.service.BackendService;

public class AdminAuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private BackendService backendService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		boolean isLoggedin = false;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if ("aauth".equals(cookie.getName())) {
				// check jwt
				String jwt = cookie.getValue();
				SysUserEntity sysUserEntity = backendService.checkLogin(jwt);
				isLoggedin = !(sysUserEntity==null);
			}
		}
		if (!isLoggedin) {
			throw new MyException(ResponseCode.ILLEGAL_AUTHJWT);
		}
		return true;
	}
}
