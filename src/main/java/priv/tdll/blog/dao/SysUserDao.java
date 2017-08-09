package priv.tdll.blog.dao;

import org.apache.ibatis.annotations.Param;

import priv.tdll.blog.entity.SysUserEntity;

public interface SysUserDao extends BaseDao<SysUserEntity> {

	public SysUserEntity retrieveUser(@Param("uf")String uniqField, @Param("value")Object value) throws Exception;

	public SysUserEntity retrieveAdmin(@Param("uf")String uniqField, @Param("value")Object value) throws Exception;
	
}
