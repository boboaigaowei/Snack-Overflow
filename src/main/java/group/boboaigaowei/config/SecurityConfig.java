package group.boboaigaowei.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication // 用於設定 OAuth 授權伺服機制，並定義與授權伺服器互動時各端點的行為
public class SecurityConfig {
	
//	@Autowired
//	private AuthenticationManager authenticationManager;

//	@Autowired
//    private CustomUserDetailsService userDetailsService;
	
	
	/**
	 * <pre>
	 * 用來儲存授權後的 user 資訊
	 * 再丟到 Authentication Server 去驗證是否可以登入我們的 application 取得資料
	 * </pre>
	 * 
	 * @return
	 */
//	@Bean
//	public UserDetailsService userDetailsService() {
//		// @formatter:off
//		UserDetails user = User.withUsername("user")
//				.password("password")
//				.roles("USER")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//		// @formatter:on
//	}

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		// @formatter:off
//		http.csrf(csrf -> csrf.disable())
//			.authorizeHttpRequests((authorizeRequests) -> 
//				authorizeRequests
////					.requestMatchers("/login", "/error", "/webjars/**")
////					.permitAll()
//					.anyRequest()
//					.authenticated())
//			.exceptionHandling((exceptionHandling) -> 
//				exceptionHandling.accessDeniedPage("/error"))
//			.oauth2Login(Customizer.withDefaults());
//			.oauth2Client(Customizer.withDefaults());
//			.formLogin(Customizer.withDefaults())
//			.oauth2ResourceServer(oauth2ResourceServer -> 
//				oauth2ResourceServer.jwt(jwt -> 
//				jwt.decoder(jwtDecoder()))
//			);
//			.oauth2ResourceServer(oauth2ResourceServer -> 
//				oauth2ResourceServer.jwt(jwt -> 
//					jwt.decoder(jwtDecoder()))
//			);
//			.rememberMe(Customizer.withDefaults())
//			.logout((logout) -> 
//				logout.logoutUrl("/logout")
//					.logoutSuccessUrl("/logout-success")
//					.permitAll()
//			);

//		return http.build();
		// @formatter:on
//	}

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public JwtDecoder jwtDecoder() {
//		return NimbusJwtDecoder.withPublicKey(this.key).build();
//	}

//	@Bean
//	public ClientRegistrationRepository clientRegistrationRepository() {
//		return new InMemoryClientRegistrationRepository(getGoogleClientRegistration());
//	}
//
//	private ClientRegistration getGoogleClientRegistration() {
//		// @formatter:off
//		return ClientRegistration.withRegistrationId("google")
//				.clientId(clientID)
//				.clientSecret(clientSecret)
//				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//				.redirectUri("http://localhost:8080/login/oauth2/code/google")
//				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//				.scope("email", "profile")
//				.authorizationUri("https://accounts.google.com/o/oauth2/auth")
//				.tokenUri("https://accounts.google.com/o/oauth2/token")
//				.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//				.userNameAttributeName("sub")
//				.clientName("Google")
//				.build();
//		// @formatter:on
//	}
}
