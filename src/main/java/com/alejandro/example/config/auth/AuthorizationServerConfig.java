package com.alejandro.example.config.auth;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.alejandro.example.util.FileReaderUtil;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	private static final String KEY_ACCESS = "permitAll()";
	private static final String TOKEN_ACCESS = "siAunthenticated()";
	
	private static final String CLIENT = "angular-app";
	private static final String PASSWORD = "4ngul4r";
	private static final String[] SCOOPE  = {"read", "write"};
	private static final String[] GRANT_TYPES = {"password", "refresh_token"};
	private static final int TIME_VALID = 3600;
	private static final Path PATH_RSA_PRIVATE = Paths.get("/home/alejandro/Projects/Spring/fullstack-spring-angular-example/src/main/resources/rsa-private.txt");
	private static final Path PATH_RSA_PUBLIC = Paths.get("/home/alejandro/Projects/Spring/fullstack-spring-angular-example/src/main/resources/rsa-public.txt");
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenInfo tokenInfo;
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter =new JwtAccessTokenConverter();
		return jwtAccessTokenConverter;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
			.tokenKeyAccess(KEY_ACCESS)
			.checkTokenAccess(TOKEN_ACCESS);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			.withClient(CLIENT)
			.secret(passwordEncoder.encode(PASSWORD))
			.scopes(SCOOPE)
			.authorizedGrantTypes(GRANT_TYPES)
			.accessTokenValiditySeconds(TIME_VALID)
			.refreshTokenValiditySeconds(TIME_VALID);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(this.tokenInfo, accessTokenConverter() ));
		endpoints
			.authenticationManager(authenticationManager)
			.tokenStore(tokenStore())
			.accessTokenConverter(accessTokenConverter())
			.tokenEnhancer(tokenEnhancerChain);
	}	
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter1() {
		String privateRSA = FileReaderUtil.read(PATH_RSA_PRIVATE);
		String publicRSA = FileReaderUtil.read(PATH_RSA_PUBLIC);
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(publicRSA);
		jwtAccessTokenConverter.setVerifierKey(privateRSA);
		return jwtAccessTokenConverter;
	}
	
}
