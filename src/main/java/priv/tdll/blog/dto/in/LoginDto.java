package priv.tdll.blog.dto.in;

public class LoginDto {

	private String account;
	private String password;
	private String verifyCode;
	private String remember;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}

	public boolean isRemember(){
		return "true".equals(remember);
	}
	
	@Override
	public String toString() {
		return "LoginDto [account=" + account + ", password=" + password + ", verifyCode=" + verifyCode + ", remember="
				+ remember + "]";
	}

}
