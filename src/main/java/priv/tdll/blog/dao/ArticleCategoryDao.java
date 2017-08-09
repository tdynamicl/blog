package priv.tdll.blog.dao;

import java.util.LinkedList;

import priv.tdll.blog.entity.ArticleCategoryEntity;

public interface ArticleCategoryDao extends BaseDao<ArticleCategoryEntity> {

	public LinkedList<ArticleCategoryEntity> queryAll() throws Exception;
	
}
