package priv.tdll.blog.dto.in;

import java.io.Serializable;

public class AddAriticleDto implements Serializable {
	private static final long serialVersionUID = 6533455516963957126L;
	private String title;
	private String preview;
	private String content;
	private String categoryId;

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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "AddAriticleDto [title=" + title + ", preview=" + preview + ", content=" + content + ", categoryId="
				+ categoryId + "]";
	}

}
