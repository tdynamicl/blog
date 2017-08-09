package priv.tdll.blog.service;

import java.util.List;

import priv.tdll.blog.dto.in.LoginDto;
import priv.tdll.blog.dto.out.ArticlePreview;
import priv.tdll.blog.dto.out.ArticleShow;
import priv.tdll.blog.entity.InfoUserEntity;

public interface UserService {

	/**
	 * 登录
	 * @param account
	 * @param password
	 * @return jwt
	 * @throws Exception
	 */
	public String login(LoginDto loginDto) throws Exception;
	
	/**
	 * 退出登录
	 * @param jwt
	 * @throws Exception
	 */
	public void logout(String jwt) throws Exception;
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InfoUserEntity loadUserInfo(String jwt) throws Exception;
	
	public List<ArticlePreview> loadArticlePreview(long index) throws Exception;
	
	/**
	 * 加载文章
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ArticleShow loadArticle(String id) throws Exception;	
}
