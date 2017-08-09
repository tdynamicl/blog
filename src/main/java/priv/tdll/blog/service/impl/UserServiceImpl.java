package priv.tdll.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.tdll.blog.common.MyException;
import priv.tdll.blog.common.ResponseCode;
import priv.tdll.blog.common.JwtUtil;
import priv.tdll.blog.dao.ArticleDao;
import priv.tdll.blog.dao.InfoUserDao;
import priv.tdll.blog.dao.RedisJwtDao;
import priv.tdll.blog.dao.SysUserDao;
import priv.tdll.blog.dto.in.LoginDto;
import priv.tdll.blog.dto.out.ArticlePreview;
import priv.tdll.blog.dto.out.ArticleShow;
import priv.tdll.blog.entity.InfoUserEntity;
import priv.tdll.blog.entity.RedisJwtEntity;
import priv.tdll.blog.entity.SysUserEntity;
import priv.tdll.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private InfoUserDao infoUserDao;
	@Autowired
	private RedisJwtDao redisJwtDao;
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public String login(LoginDto loginDto) throws Exception {
		if (loginDto.getAccount()==null||loginDto.getAccount().trim().length()==0) {
			throw new MyException(ResponseCode.EMPTY_ACCOUNT);
		}
		if (loginDto.getPassword()==null||loginDto.getPassword().trim().length()==0) {
			throw new MyException(ResponseCode.EMPTY_PASSWORD);
		}
		SysUserEntity sysUserEntity = sysUserDao.retrieveUser("account", loginDto.getAccount());
		if (sysUserEntity==null) {
			throw new MyException(ResponseCode.NONEXIST_ACCOUNT);
		}
		if (!loginDto.getPassword().equals(sysUserEntity.getPassword())) {
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
	public InfoUserEntity loadUserInfo(String jwt) throws Exception {
		if (jwt==null||jwt.trim().length()==0) {
			throw new MyException(ResponseCode.EMPTY_AUTHJWT);
		}
		String userId = JwtUtil.parseJwt(jwt);
		RedisJwtEntity redisJwtEntity = redisJwtDao.retrieve("key", userId);
		if (redisJwtEntity==null||!(jwt.equals(redisJwtEntity.getValue()))) {
			throw new MyException(ResponseCode.ILLEGAL_AUTHJWT);
		}
		return infoUserDao.retrieve("id", userId);
	}

	@Override
	public List<ArticlePreview> loadArticlePreview(long index) throws Exception {
		return articleDao.queryArticlePreview(10*(index-1), 10);
	}

	@Override
	public ArticleShow loadArticle(String id) throws Exception {
		return articleDao.queryArticle(id);
	}

}
