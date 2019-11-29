package com.alejandro.example.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alejandro.example.model.entity.ClientEntity;

@Component
public class GenericControllerMessages<T extends Serializable> {
	
	private static final String CREATED= "CREATED: ";
	private static final String UPDATED = "UPDATED: ";

	public Map<String, T>   getMesssageCreatedSuccess(T model) {
		Map<String, T> responce = new HashMap<>();
		responce.put(CREATED,  model);
		return responce;
	}

	public Map<String, T>   getMesssageUpdatedSuccess(T  model) {
		Map<String, T> responce = new HashMap<>();
		responce.put(UPDATED,  model);
		return responce;
	}
	
	public Map<String, Object>   getMesssageUploadFileSuccess(ClientEntity client) {
		Map<String, Object> responce = new HashMap<>();
		responce.put("CLIENT: ",  client.getPhoto());
		return responce;
	}
	
}
