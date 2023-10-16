package group.boboaigaowei.utils;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final long EXPIRE_TIME = 20 * 1000; // token 過期時間 20 秒

	private static final SecureRandom NUMBER_GENERATOR = new SecureRandom();
	
	private static final String TOKEN_SECRET = "cuAihCz53DZRjZwbsGcZJ2Ai6At+T142uphtJMsk7iQ=";


	/**
	 * <pre>
	 * generate token by JWT
	 * 1. claims - payload，是一個 Map 的結構，想放什麼可以放在這 
	 * 2. subject - 簽核主體，通常是 user name
	 * 3. expiration - token 過期時間
	 * 4. secret - 加密用的密鑰
	 * </pre>
	 * 
	 * @param claims
	 * @param subject
	 * @return
	 */
	public String generateToken(Map<String, Object> claims, String subject) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis); // 登入時間

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject) // 簽核主體
				.setIssuedAt(now) // 簽發時間
				.setExpiration(new Date(nowMillis + EXPIRE_TIME)) // 設定過期時效
				.signWith(generateKey(), SignatureAlgorithm.HS256) // signature 的部分，可指定用什麼 hash function
				.compact();
	}

	/**
	 * <pre>
	 * parse JWT token
	 * </pre>
	 * 
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> parseToken(String token) throws Exception {
		System.out.println("=====>" + token);
		Claims claims =  Jwts.parserBuilder()
				.setSigningKey(generateKey()) // 注入要解密的密鑰
				.build()
				.parseClaimsJws(token) // 將 token 轉換回原本的 JWS
				.getBody();

		Map<String, String> map = new HashMap<>();
		map.put("account", claims.getSubject());
		map.put("loginTimeStamp", (String)claims.get("loginTimeStamp"));

		return map;
	}
	
//	public Claims parseToken(String token) throws Exception {
//		return Jwts.parserBuilder()
//				.setSigningKey(generateKey()) // 注入要解密的密鑰
//				.build()
//				.parseClaimsJws(token).getBody(); // 將 token 轉換回原本的 JWS
//	}
	
	/**
	 * <pre>
	 * - generate key by using Keys.hmacShaKeyFor() method
   *  - 但因為還沒有實作隨機密鑰，故先用相同的
	 * </pre>
	 * 
	 * @return
	 */
	private Key generateKey() {
		byte[] encodeKey = Decoders.BASE64.decode(TOKEN_SECRET);
		
		Key key = Keys.hmacShaKeyFor(encodeKey);
		return key;
	}

	/**
	 * <pre>
	 * - generate key by using Keys.hmacShaKeyFor() method
	 * 	- 使用 UUID 隨機產生密鑰
	 * </pre>
	 * 
	 * @param uuid
	 * @return
	 */
	private Key generateKey(String uuid) {
		byte[] encodeKey = Decoders.BASE64.decode(uuid);
		Key key = Keys.hmacShaKeyFor(encodeKey);
		return key;
	}

	/**
	 * <pre>
	 * generate random UUID for secret key
	 * - 由於上面的 singWith() 用的是 HS256，會生成 256 位 (32字節)，因此至少需要 32 字節長的密鑰
	 * </pre>
	 * 
	 * @return
	 */
	private UUID randomUUID() {
		byte[] randomBytes = new byte[32];
		NUMBER_GENERATOR.nextBytes(randomBytes);
		return new UUID(32, 32);
	}
}
