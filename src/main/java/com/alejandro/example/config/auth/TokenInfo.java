package com.alejandro.example.config.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.example.model.entity.UserEntity;
import com.alejandro.example.service.interfaces.IUserService;

@Component
public class TokenInfo implements TokenEnhancer{
	
	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService service;

	@Override
	@Transactional(readOnly = true)
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, 
				OAuth2Authentication authentication) {
		UserEntity user = service.getByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("USERNAME: ", user.getUsername());
		info.put("USER_ID: ", user.getIdUser());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
