package priv.tdll.blog.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	public static final String key = "tdynamicl@#@#@#@#@hotmail.,.,.,.,.com";
	public static final SignatureAlgorithm sa = SignatureAlgorithm.HS256;
	public static final int jwtAgeSecs = 7*24*60*60;
	
	public static String createJwt(String userId, long ttlMillis) throws Exception {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		Map<String, Object> claimMap = new HashMap<>();
		claimMap.put("user_id", userId);
		String compactJwt = Jwts.builder()
				.setClaims(claimMap)
				.setIssuer("blog.tdll.priv")
				.setIssuedAt(now)
				.setExpiration(new Date(nowMillis + ttlMillis))
				.signWith(sa, key)
				.compact();
		return compactJwt;
	}
	
	public static String parseJwt(String jwt) throws Exception {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(key).parseClaimsJws(jwt).getBody();
			if (claims.getExpiration().compareTo(Util.nowDate())<0) {
				throw new MyException(ResponseCode.EXPIRED_AUTHJWT);
			}
			if (!("blog.tdll.priv".equals(claims.getIssuer()))) {
				throw new MyException(ResponseCode.ILLEGAL_AUTHJWT);
			}
			String uid = claims.get("user_id").toString();
			if (uid==null||uid.trim().length()==0) {
				throw new MyException(ResponseCode.ILLEGAL_AUTHJWT);
			}
			return uid;
		} catch(Exception e) {
			throw new MyException(ResponseCode.ILLEGAL_AUTHJWT);
		}
	}
	
}
