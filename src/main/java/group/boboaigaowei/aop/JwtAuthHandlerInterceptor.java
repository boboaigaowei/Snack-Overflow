package group.boboaigaowei.aop;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import group.boboaigaowei.exception.TokenAuthExpiredException;
import group.boboaigaowei.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthHandlerInterceptor implements HandlerInterceptor {

//	private static Logger log = LoggerFactory.getLogger(JwtAuthHandlerInterceptor.class);
	
//	@Value("${token.refreshTime}")
//	private Long refreshTime;

	@Value("${token.expireTime}")
	private Long expireTime;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("======= interceptor ========");
		// 如果不是 mapping 到 method，可以直接訪問
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		// 是 null 就拋錯
		String token = request.getHeader("token");
//		if (null == token || "".equals(token.trim())) {
//			return false;
//		}

		log.info("============== token:" + token);

		// 計算是否過期以及刷新 token 用
		Map<String, String> map = jwtUtil.parseToken(token);
//		String account = map.get("account");

		long timeOfUse = System.currentTimeMillis() - Long.parseLong(map.get("loginTimeStamp"));

		// 1. 判斷 token 使否過期
		if (timeOfUse < expireTime) {
			log.info("token 驗證成功");
			return true;

		} else { // token 過期
			throw new TokenAuthExpiredException();
		}
	}

}
