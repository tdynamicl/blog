package priv.tdll.blog.dto.in;

import org.springframework.web.multipart.MultipartFile;

public class CUImageDto {

	private String title;
	private String description;
	private MultipartFile file;

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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "CUImageDto [title=" + title + ", description=" + description + ", file=" + file + "]";
	}

}
