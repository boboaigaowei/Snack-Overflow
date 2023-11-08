package group.boboaigaowei.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.boboaigaowei.exception.TokenAuthExpiredException;
import group.boboaigaowei.service.AuthSvc;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class UserController {

//	@Autowired
//	private AuthSvc loginSvc;

	@GetMapping("/login")
	@Operation(summary = "登入驗證", description = "OAuth 第三方登入驗證後簽發 token")
	public String login(@AuthenticationPrincipal Authentication principal) throws TokenAuthExpiredException {
//		return loginSvc.login(principal);
		return "Hello OAuth";
	}

	/**
	 * 登入錯誤訊息顯示面
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/error")
	public String error(HttpServletRequest request) {
		String message = (String) request.getSession().getAttribute("error.message");
		request.getSession().removeAttribute("error.message");
		return message;
	}

}
