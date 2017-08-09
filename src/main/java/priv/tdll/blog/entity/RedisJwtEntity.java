package priv.tdll.blog.entity;

import java.io.Serializable;

public class RedisJwtEntity implements Serializable {
	private static final long serialVersionUID = 3199134837418282639L;

	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
