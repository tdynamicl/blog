package priv.tdll.blog.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import priv.tdll.blog.common.PaginationResult;
import priv.tdll.blog.dto.in.AddAriticleDto;
import priv.tdll.blog.dto.in.CUImageDto;
import priv.tdll.blog.entity.ArticleCategoryEntity;
import priv.tdll.blog.entity.ArticleEntity;
import priv.tdll.blog.entity.SysUserEntity;

public interface BackendService {

	public String login(String account, String password) throws Exception;
	
	public void logout(String jwt) throws Exception;
	
	public SysUserEntity checkLogin(String jwtString) throws Exception;
	
	public void uploadImage(CUImageDto cuImageDto, HttpServletRequest req) throws Exception;
	
	public String updateImage(CUImageDto cuImageDto) throws Exception;
	
	/**
	 * 通用上传文件
	 * @param file
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(MultipartFile file,String path) throws Exception;
	
	public void addArticle(AddAriticleDto addAriticleDto) throws Exception;
	
	public List<ArticleCategoryEntity> loadArticleCategorys() throws Exception;
	
	public PaginationResult<ArticleEntity> loadArticleList(long pageIndex, int pageSize) throws Exception;
	
}