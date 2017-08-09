package priv.tdll.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import priv.tdll.blog.common.ConstString;
import priv.tdll.blog.common.MyException;
import priv.tdll.blog.common.PaginationResult;
import priv.tdll.blog.common.ResponseCode;
import priv.tdll.blog.common.ServerResponse;
import priv.tdll.blog.common.ValidateCodeUtil;
import priv.tdll.blog.dto.in.AddAriticleDto;
import priv.tdll.blog.dto.in.CUImageDto;
import priv.tdll.blog.entity.ArticleCategoryEntity;
import priv.tdll.blog.entity.ArticleEntity;
import priv.tdll.blog.entity.SysUserEntity;
import priv.tdll.blog.service.impl.BackendServiceImpl;

@Controller
@RequestMapping("/backend")
public class BackendController {

	@Autowired
	private BackendServiceImpl backendServiceImpl;
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("a")String account, 
			@RequestParam("p")String password, 
			@RequestParam("vc")String validatecode, 
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (validatecode==null||validatecode.trim().length()==0) {
			ServerResponse<String> serverResponse = ServerResponse.createByFailure(ResponseCode.EMPTY_VALIDATE_CODE);
			return serverResponse.toModelAndView();
		}
		String verCode = (String) req.getSession().getAttribute("verCode");
		if (!validatecode.equals(verCode)) {
			ServerResponse<String> serverResponse = ServerResponse.createByFailure(ResponseCode.ERROR_VALIDATE_CODE);
			return serverResponse.toModelAndView();
		}
		Cookie cookie = new Cookie("aauth", backendServiceImpl.login(account, password));
		cookie.setHttpOnly(true);
//		cookie.setPath("backend/");
		resp.addCookie(cookie);
		ServerResponse<String> serverResponse = ServerResponse.createBySuccess();
		return serverResponse.toModelAndView();
	}
	
	@RequestMapping(value="/logout.do", method=RequestMethod.POST)
	public ModelAndView logout(@CookieValue(value="aauth")String aauth, 
			HttpServletResponse resp)throws Exception {
		backendServiceImpl.logout(aauth);
		Cookie cookie = new Cookie("aauth", null);
		cookie.setMaxAge(0);
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
		String verifyCode = ValidateCodeUtil.generateVerifyCode(4); 
		HttpSession session = req.getSession(true); 
		session.removeAttribute("verCode");
		session.setAttribute("verCode", verifyCode.toLowerCase()); 
		ValidateCodeUtil.outputImage(200, 80, resp.getOutputStream(), verifyCode);
	}
	
	@RequestMapping(value="/check_login.do", method=RequestMethod.POST)
	public ModelAndView checkLogin(@CookieValue(value="aauth", required=false)String aauth) throws Exception {
		ServerResponse<SysUserEntity> serverResponse = ServerResponse.createBySuccess();
		serverResponse.setData(backendServiceImpl.checkLogin(aauth));
		return serverResponse.toModelAndView();
	}
	
	@RequestMapping(value="/uploadImage.do", method=RequestMethod.POST)
	public ModelAndView uploadImage(CUImageDto cuImageDto, HttpServletRequest req) throws Exception {
		backendServiceImpl.uploadImage(cuImageDto, req);
		ServerResponse<String> serverResponse = ServerResponse.createBySuccess();
		return serverResponse.toModelAndView();
	}
	
	@RequestMapping(value="/uploadFileFromArticle.do", method=RequestMethod.POST)
	public ModelAndView uploadFileFromArticle(
			@RequestParam("imgFile")MultipartFile file, 
			@RequestParam("dir")String dir, 
			HttpServletRequest req) throws Exception {
		String path = req.getSession().getServletContext().getRealPath(
				ConstString.ARTICLE_PATH.getString() + dir + "/");
		String fileUrl = backendServiceImpl.uploadFile(file, path);
		fileUrl = req.getSession().getServletContext().getContextPath() 
				+ ConstString.ARTICLE_IMAGE_PATH.getString() + fileUrl;
		ModelAndView mav = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		Map<String, Object> result = new HashMap<>();
		result.put("error", 0);
		result.put("url", fileUrl);
		jsonView.setAttributesMap(result);
		mav.setView(jsonView);
		return mav;
	}
	
	@RequestMapping(value="/fileManagerFromArticle.do", method=RequestMethod.GET)
	public ModelAndView fileManagerFromArticle(){
		
		
		return null;
	}
	
	@RequestMapping(value="/addArticle.do", method=RequestMethod.POST)
	public ModelAndView addArticle(AddAriticleDto addAriticleDto) throws Exception {
		backendServiceImpl.addArticle(addAriticleDto);
		ServerResponse<String> serverResponse = ServerResponse.createBySuccess("添加成功");
		return serverResponse.toModelAndView();
	}
	
	@RequestMapping(value="loadArticleCategorys.do", method=RequestMethod.POST)
	public ModelAndView loadArticleCategorys() throws Exception {
		ServerResponse<List<ArticleCategoryEntity>> serverResponse = ServerResponse.createBySuccess();
		serverResponse.setData(backendServiceImpl.loadArticleCategorys());
		return serverResponse.toModelAndView();
	}
	
	@RequestMapping(value="loadArticleList.do", method=RequestMethod.POST)
	public ModelAndView loadArticleList(@RequestParam("pageIndex")long pageIndex, 
			@RequestParam("pageSize")int pageSize) throws Exception {
		ServerResponse<PaginationResult<ArticleEntity>> serverResponse = ServerResponse.createBySuccess();
		serverResponse.setData(backendServiceImpl.loadArticleList(pageIndex, pageSize));
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
