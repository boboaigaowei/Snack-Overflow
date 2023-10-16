package group.boboaigaowei.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import group.boboaigaowei.aop.JwtAuthHandlerInterceptor;

@Configuration
public class JwtAuthConfig implements WebMvcConfigurer {

	@Autowired
	private JwtAuthHandlerInterceptor authHandlerInterceptor;

	/**
	 * <pre>
	 * 設定除了進入 /login 這個 path 需要驗證
	 * 注入 authHandlerInterceptor
	 * </pre>
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authHandlerInterceptor)
			.addPathPatterns("/blog/**")
			.excludePathPatterns("/**",
					"/login",
					"/v3/api-docs",
	                "/swagger-ui.html",
	                "/swagger-ui/**",
	                "/swagger-resources/**"
            );
	}
}
