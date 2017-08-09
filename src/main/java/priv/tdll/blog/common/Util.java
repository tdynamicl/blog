package priv.tdll.blog.common;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class Util {

	public static String getPath(ConstString subPathEnum, HttpServletRequest req) throws Exception {
		return req.getSession().getServletContext().getRealPath(subPathEnum.getString());
	}
	
	public static String generateUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static Date nowDate(){
		return new Date(System.currentTimeMillis());
	}
	
}
