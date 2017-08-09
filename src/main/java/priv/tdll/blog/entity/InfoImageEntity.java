package priv.tdll.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class InfoImageEntity implements Serializable {
	private static final long serialVersionUID = 3685002259231123232L;
	
	private String id;
	private String title;
	private String description;
	private String path;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
