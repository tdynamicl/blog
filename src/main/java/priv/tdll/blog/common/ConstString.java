package priv.tdll.blog.common;

public enum ConstString {

	NEW_INSTANCE(null),
	// 文章资源目录
	ARTICLE_PATH("/resource/article/"),
	// 文章各种上传文件的路径
	ARTICLE_IMAGE_PATH("/resource/article/image/"),
	ARTICLE_FLASH_PATH("/resource/article/flash/"),
	ARTICLE_MEDIA_PATH("/resource/article/media/"),
	ARTICLE_FILE_PATH("/resource/article/file/"),
	// 文章各种上传文件的类型参数
	ARTICLE_IMAGE_TYPE("image"),
	ARTICLE_FLASH_TYPE("flash"),
	ARTICLE_MEDIA_TYPE("media"),
	ARTICLE_FILE_TYPE("file"),	
	// 文章各种上传文件的类型后缀
	ARTICLE_IMAGE_SUFFIX("gif,jpg,jpeg,png,bmp"),
	ARTICLE_FLASH_SUFFIX("swf,flv"),
	ARTICLE_MEDIA_SUFFIX("swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb"),
	ARTICLE_FILE_SUFFIX("doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2"),
	
	// 
	IMAGE_PATH("/resource/image/"),
	
	;
	
	private String string;
	
	private ConstString(String string){
		this.string = string;
	}

	public static ConstString newInstance(String string){
		ConstString constString = NEW_INSTANCE;
		constString.string = string;
		return constString;
	}
	
	public String getString() {
		return string;
	}
	
}
