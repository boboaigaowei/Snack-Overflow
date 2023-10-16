package group.boboaigaowei.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import group.boboaigaowei.controller.dto.AuthRequest;
import group.boboaigaowei.exception.TokenAuthExpiredException;
import group.boboaigaowei.service.LoginSvc;
import group.boboaigaowei.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private LoginSvc loginSvc;

	@PostMapping("/login")
	@Operation(summary = "登入", description = "登入驗證後會簽發 token")
	public String loginWithJwt(@RequestBody AuthRequest request) throws TokenAuthExpiredException {
		// 先驗證帳號密碼，再簽發 token
		return loginSvc.login(request);
	}
	
	@PostMapping("/testToken")
	@Operation(summary = "驗證 token", description = "登入後操作都需要驗證 token", security = @SecurityRequirement(name = "Bearer Authentication"))
	public String testToken(HttpServletRequest request) throws Exception {
		String token = request.getHeader("token");
		System.out.println("token = " + token);
		Map<String, String> map = jwtUtil.parseToken(token);
		return map.get("account") + ": " + token;
	}
}
