package priv.tdll.blog.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import priv.tdll.blog.common.ConstString;
import priv.tdll.blog.common.FileUtil;
import priv.tdll.blog.common.JwtUtil;
import priv.tdll.blog.common.MyException;
import priv.tdll.blog.common.PaginationResult;
import priv.tdll.blog.common.ResponseCode;
import priv.tdll.blog.common.Util;
import priv.tdll.blog.dao.ArticleCategoryDao;
import priv.tdll.blog.dao.ArticleDao;
import priv.tdll.blog.dao.InfoImageDao;
import priv.tdll.blog.dao.RedisJwtDao;
import priv.tdll.blog.dao.SysUserDao;
import priv.tdll.blog.dto.in.AddAriticleDto;
import priv.tdll.blog.dto.in.CUImageDto;
import priv.tdll.blog.entity.ArticleCategoryEntity;
import priv.tdll.blog.entity.ArticleEntity;
import priv.tdll.blog.entity.InfoImageEntity;
import priv.tdll.blog.entity.RedisJwtEntity;
import priv.tdll.blog.entity.SysUserEntity;
import priv.tdll.blog.service.BackendService;

@Service
public class BackendServiceImpl implements BackendService {

	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private RedisJwtDao redisJwtDao;
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private InfoImageDao infoImageDao;
	@Autowired
	private ArticleCategoryDao articleCategoryDao;
	
	@Override
	public String login(String account, String password) throws Exception {
		if (account==null||account.trim().length()==0) {
			throw new MyException(ResponseCode.EMPTY_ACCOUNT);
		}
		if (password==null||password.trim().length()==0) {
			throw new MyException(ResponseCode.EMPTY_PASSWORD);
		}
		SysUserEntity sysUserEntity = sysUserDao.retrieveAdmin("account", account);
		if (sysUserEntity==null) {
			throw new MyException(ResponseCode.NONEXIST_ACCOUNT);
		}
		if (!password.equals(sysUserEntity.getPassword())) {
			throw new MyException(ResponseCode.ERROR_ACCOUNT_OR_PASSWORD);
		}
		String jwtString = JwtUtil.createJwt(sysUserEntity.getId(), JwtUtil.jwtAgeSecs*1000);
		RedisJwtEntity redisJwtEntity = new RedisJwtEntity();
		redisJwtEntity.setKey(sysUserEntity.getId());
		redisJwtEntity.setValue(jwtString);
		redisJwtDao.delete("key", sysUserEntity.getId());
		redisJwtDao.create(redisJwtEntity);
		return jwtString;
	}

	@Override
	public void logout(String jwt) throws Exception {
		try {
			if (jwt==null||jwt.trim().length()==0) {
				return;
			}
			String userId = JwtUtil.parseJwt(jwt);
			redisJwtDao.delete("key", userId);
		} catch (Exception e) {
			return;
		}
	}
	
	@Override
	public SysUserEntity checkLogin(String jwtString) throws Exception {
		String uid = JwtUtil.parseJwt(jwtString);
		SysUserEntity sysUserEntity = sysUserDao.retrieve("id", uid);
		if (sysUserEntity==null) {
			throw new MyException(ResponseCode.ILLEGAL_AUTHJWT);
		}
		sysUserEntity.setPassword(null);
		return sysUserEntity;
	}

	@Override
	public void uploadImage(CUImageDto cuImageDto, HttpServletRequest req) throws Exception {
		String path = Util.getPath(ConstString.IMAGE_PATH, req);
		InfoImageEntity imageEntity = new InfoImageEntity();
		imageEntity.setPath(ConstString.IMAGE_PATH.getString() 
				+ FileUtil.uploadFile(cuImageDto.getFile(), path, true));
		imageEntity.setId(Util.generateUUID());
		imageEntity.setTitle(cuImageDto.getTitle());
		imageEntity.setDescription(cuImageDto.getDescription());
		imageEntity.setAddTime(Util.nowDate());
		infoImageDao.create(imageEntity);
	}

	@Override
	public String updateImage(CUImageDto cuImageDto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uploadFile(MultipartFile file, String path) throws Exception {
		return FileUtil.uploadFile(file, path, true);
	}

	@Override
	public void addArticle(AddAriticleDto addAriticleDto) throws Exception {
		ArticleEntity articleEntity = new ArticleEntity();
		articleEntity.setId(Util.generateUUID());
		articleEntity.setCategoryId(addAriticleDto.getCategoryId());
		articleEntity.setTitle(addAriticleDto.getTitle());
		articleEntity.setPreview(addAriticleDto.getPreview());
		articleEntity.setContent(addAriticleDto.getContent());
		articleEntity.setAddTime(Util.nowDate());
		articleEntity.setModifyTime(Util.nowDate());
		articleEntity.setViewTimes(0);
		articleEntity.setStatus(1);
		articleDao.create(articleEntity);
	}

	@Override
	public List<ArticleCategoryEntity> loadArticleCategorys() throws Exception {
		return articleCategoryDao.queryAll();
	}

	@Override
	public PaginationResult<ArticleEntity> loadArticleList(long pageIndex, int pageSize) throws Exception {
		
		return null;
	}

}
