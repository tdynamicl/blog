package priv.tdll.blog.common;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public static String uploadFile(MultipartFile mFile, String path, boolean isUseUUIDFileName) throws Exception {
		try {
			File targetFile;
			String originalFileName = mFile.getOriginalFilename();
			if (isUseUUIDFileName) {
				String suffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
				originalFileName = Util.generateUUID() + suffix;
			}
			targetFile = new File(path, originalFileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			mFile.transferTo(targetFile);
			return targetFile.getName();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(ResponseCode.ERROR_UPLOAD_FILE);
		}
	}
	
	
}
