package com.alejandro.example.config.auth;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	private static final List<String> METHODS_HTTP = 
			Arrays.asList("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS");
	
	private static final List<String> HEADERS_HTTP = 
			Arrays.asList("Content-Type", 
										  "Access-Control-Allow-Origin", 
										  "Access-Control-Max-Age",
										  "Access-Control-Allow-Methods",
										  "Authorization");

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, 
										   "/api/clients/clients", 
										   "/api/clients/clients/page/**",
										   "/api/clients/client/{id}",
										   "/uploads/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.cors()
			.configurationSource(corsConfigurationSource());
	}
	
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(METHODS_HTTP);
        config.setAllowCredentials(true);
        config.setAllowedHeaders(HEADERS_HTTP);
        config.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
    	 FilterRegistrationBean<CorsFilter>bean = 
    			 new  FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
    	 bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    	 return bean;
    }
	
}
