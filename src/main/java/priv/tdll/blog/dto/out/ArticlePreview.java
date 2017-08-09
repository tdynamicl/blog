package priv.tdll.blog.dto.out;

import java.io.Serializable;
import java.util.Date;

public class ArticlePreview implements Serializable {
	private static final long serialVersionUID = -5168155341751524447L;
	private String id;
	private String title;
	private String preview;

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

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
