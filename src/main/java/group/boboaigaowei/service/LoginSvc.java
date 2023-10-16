package group.boboaigaowei.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group.boboaigaowei.controller.dto.AuthRequest;
import group.boboaigaowei.exception.TokenAuthExpiredException;
import group.boboaigaowei.utils.JwtUtil;

@Service
public class LoginSvc {
	private static Map<String, String> userMap = new HashMap<>();
	static {
		userMap.put("test", "123456");
	}

	@Autowired
	private JwtUtil jwtUtil;

	public String login(AuthRequest request) throws TokenAuthExpiredException {
		// 先驗證帳號密碼，再簽發 token
		String account = request.getAccount();
		if(!userMap.containsKey(account) || !request.getPassword().equals(userMap.get(account))) {
			throw new TokenAuthExpiredException();
		}
		
		// 組 payload
		Map<String, Object> claims = new HashMap<>();
		claims.put("account", account);
		
		// 簽發 token
		String token = jwtUtil.generateToken(claims, account);
		System.out.println(token);
		return token;
	}
}
