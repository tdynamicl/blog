package priv.tdll.blog.common;

public class MyException extends Exception {
	private static final long serialVersionUID = -5826477341462530165L;
	private int code;
	public MyException(String message, int code) {
		super(message);
		this.code = code;
	}
	public MyException(ResponseCode responseCode){
		super(responseCode.getMessage());
		this.code = responseCode.getCode();
	}
	public int getCode() {
		return this.code;
	}
}
