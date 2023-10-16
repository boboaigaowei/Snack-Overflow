package group.boboaigaowei.controller;

import java.util.Enumeration;
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
	@SecurityRequirement(name = "Bearer Authentication") // Mapping 到 Swagger 設定驗證的名稱
	@Operation(summary = "驗證 token", description = "登入後操作都需要驗證 token", security = @SecurityRequirement(name = "Bearer Authentication"))
	public String testToken(HttpServletRequest request) throws Exception {
		String token = request.getHeader("Authorization"); // 目前只能用 Authorization 的名字，用其他的不行
		token = token.replace("Bearer", ""); // 因為從 Swagger 進來的 token 前會帶有 Bearer 字串
		Map<String, String> map = jwtUtil.parseToken(token);
		return map.get("account") + ": " + token;
	}
}
