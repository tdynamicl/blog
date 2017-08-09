package priv.tdll.blog.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 基本的CRUD
 * @author 唐东灵
 *
 * @param <T>
 */
public interface BaseDao<T> {

	public void create(T value) throws Exception;
	
	public T retrieve(@Param("uf")String uniqField, @Param("value")Object value) throws Exception;
	
	public void update(T value) throws Exception;
	
	public void delete(@Param("uf")String uniqField, @Param("value")Object value) throws Exception;
	
}
