package priv.tdll.blog.dto.out;

import java.io.Serializable;
import java.util.Date;

public class ArticleShow implements Serializable {
	private static final long serialVersionUID = -7147769738033400885L;

	private String id;
	private String title;
	private String content;
	private Date addTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "ArticleShow [id=" + id + ", title=" + title + ", content=" + content + ", addTime=" + addTime + "]";
	}

}
