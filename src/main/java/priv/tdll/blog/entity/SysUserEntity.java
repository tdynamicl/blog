package priv.tdll.blog.entity;

import java.io.Serializable;

public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 5172206008315473384L;

	private String id;
	private String account;
	private String password;
	private int role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "SysUserEntity [id=" + id + ", account=" + account + ", password=" + password + ", role=" + role + "]";
	}
	
}
