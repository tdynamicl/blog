package priv.tdll.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = -6894057678260418310L;
	private String id;
	private String categoryId;
	private String title;
	private String preview;
	private String content;
	private long viewTimes;
	private Date addTime;
	private Date modifyTime;
	private int status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(long viewTimes) {
		this.viewTimes = viewTimes;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ArticleEntity [id=" + id + ", categoryId=" + categoryId + ", title=" + title + ", preview=" + preview
				+ ", content=" + content + ", viewTimes=" + viewTimes + ", addTime=" + addTime + ", modifyTime="
				+ modifyTime + ", status=" + status + "]";
	}

}
