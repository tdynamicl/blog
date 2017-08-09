package priv.tdll.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import priv.tdll.blog.dto.out.ArticlePreview;
import priv.tdll.blog.dto.out.ArticleShow;
import priv.tdll.blog.entity.ArticleEntity;

public interface ArticleDao extends BaseDao<ArticleEntity> {

	public List<ArticlePreview> queryArticlePreview(@Param("start")long start, @Param("limit")long limit) throws Exception;
	
	public ArticleShow queryArticle(@Param("id")String id) throws Exception;
	
}
