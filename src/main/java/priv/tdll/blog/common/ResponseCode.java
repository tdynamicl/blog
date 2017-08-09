package priv.tdll.blog.common;

public enum ResponseCode {

	SUCCESS(0, "SUCCESS"),
	SUCCESS_UPLOAD_IMAGE(0, "图片上传成功"),
	ERROR_UNKNOWN(1000, "发生未知错误，请稍后重试"),
	
	EMPTY_ACCOUNT(1001, "用户名不能为空"),
	EMPTY_PASSWORD(1002, "密码不能为空"),
	NONEXIST_ACCOUNT(1003, "用户不存在"),
	ERROR_ACCOUNT_OR_PASSWORD(1004, "用户名或密码错误"),
	
	EMPTY_AUTHJWT(1201, "还未登录"),
	ILLEGAL_AUTHJWT(1202, "无效的登录信息，请重新登录"),
	EXPIRED_AUTHJWT(1211, "登录信息已过期，请重新登录"),
	
	EMPTY_VALIDATE_CODE(1301, "请填写验证码"),
	ERROR_VALIDATE_CODE(1302, "验证码错误"),
	
	ERROR_UPLOAD_FILE(2001, "上传文件失败"),
	;
	
	
	private int code;
	private String message;
	
	private ResponseCode(int code, String message){
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
}
