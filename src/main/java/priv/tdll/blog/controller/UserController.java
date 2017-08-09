package priv.tdll.blog.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import priv.tdll.blog.common.JwtUtil;
import priv.tdll.blog.common.MyException;
import priv.tdll.blog.common.ResponseCode;
import priv.tdll.blog.common.ServerResponse;
import priv.tdll.blog.common.ValidateCodeUtil;
import priv.tdll.blog.dto.in.LoginDto;
import priv.tdll.blog.dto.out.ArticlePreview;
import priv.tdll.blog.dto.out.ArticleShow;
import priv.tdll.blog.entity.InfoUserEntity;
import priv.tdll.blog.service.impl.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(LoginDto loginDto, HttpServletRequest req, HttpServletResponse resp) throws Exception{
		if (loginDto.getVerifyCode()==null||loginDto.getVerifyCode().trim().length()==0) {
			ServerResponse<String> serverResponse = ServerResponse.createByFailure(ResponseCode.EMPTY_VALIDATE_CODE);
			return serverResponse.toModelAndView();
		}
		String verCode = (String) req.getSession().getAttribute("vc");
		if (!loginDto.getVerifyCode().equals(verCode)) {
			ServerResponse<String> serverResponse = ServerResponse.createByFailure(ResponseCode.ERROR_VALIDATE_CODE);
			return serverResponse.toModelAndView();
		}
		Cookie cookie = new Cookie("uauth", userServiceImpl.login(loginDto));
		cookie.setHttpOnly(true);
		if (loginDto.isRemember()) {
			cookie.setMaxAge(JwtUtil.jwtAgeSecs);
		}
		resp.addCookie(cookie);
		ServerResponse<String> serverResponse = ServerResponse.createBySuccess();
		return serverResponse.toModelAndView();
	}
	
	@RequestMapping(value="/gvc.do", method=RequestMethod.GET)
	public void getValidateCode(@RequestParam("t")String time, 
			HttpServletRequest req, 
			HttpServletResponse resp) throws Exception {
		resp.setHeader("Pragma", "No-cache"); 
		resp.setHeader("Cache-Control", "no-cache"); 
		resp.setDateHeader("Expires", 0); 
		resp.setContentType("image/jpeg");
		//生成随机字串 
		String verifyCode = ValidateCodeUtil.generateVerifyCode(4); 
		//存入会话session 
		HttpSession session = req.getSession(true); 
		//删除以前的
		session.removeAttribute("vc");
		session.setAttribute("vc", verifyCode.toLowerCase()); 
		//生成图片 
		int w = 200, h = 80; 
		ValidateCodeUtil.outputImage(w, h, resp.getOutputStream(), verifyCode);
	}
	
	@RequestMapping(value="/logout.do", method=RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest req, HttpServletResponse resp, @CookieValue(value="uauth", required=false)String userAuthJwt) throws Exception{
		userServiceImpl.logout(userAuthJwt);
		Cookie cookie = new Cookie("uauth", null);
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		ServerResponse<String> serverResponse = ServerResponse.createBySuccess();
		return serverResponse.toModelAndView();
	}
	
	@RequestMapping(value="/loadUserInfo.do", method=RequestMethod.POST)
	public ModelAndView loadUserInfo(
			@CookieValue(value="uauth", required=false)String userAuthJwt) throws Exception{
		
		ServerResponse<InfoUserEntity> serverResponse = ServerResponse.createBySuccess();
		serverResponse.setData(userServiceImpl.loadUserInfo(userAuthJwt));
		return serverResponse.toModelAndView();
	}

	@RequestMapping(value="/loadArticlePreview.do", method=RequestMethod.POST)
	public ModelAndView loadArticlePreview(@RequestParam("index")long index) throws Exception {
		ServerResponse<List<ArticlePreview>> serverResponse = ServerResponse.createBySuccess();
		serverResponse.setData(userServiceImpl.loadArticlePreview(index));
		return serverResponse.toModelAndView();
	}
	
	@RequestMapping(value="/loadArticle.do", method=RequestMethod.POST)
	public ModelAndView loadArticle(@RequestParam("id")String id) throws Exception {
		ServerResponse<ArticleShow> serverResponse = ServerResponse.createBySuccess();
		serverResponse.setData(userServiceImpl.loadArticle(id));
		return serverResponse.toModelAndView();
	}
	
	//-----------------------------
	@ExceptionHandler(MyException.class)
	public ModelAndView exceptionHandler(MyException e) throws Exception{
		ServerResponse<String> serverResponse = ServerResponse.createByFailure(
				e.getCode(), e.getMessage());
		return serverResponse.toModelAndView();
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView defExceptionHandler(Exception e) throws Exception{
		e.printStackTrace();
		ServerResponse<String> serverResponse = ServerResponse.createByFailure(
				ResponseCode.ERROR_UNKNOWN.getCode(), 
				ResponseCode.ERROR_UNKNOWN.getMessage());
		return serverResponse.toModelAndView();
	}
	
}
