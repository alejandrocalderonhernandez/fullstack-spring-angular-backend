package com.alejandro.example.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StaticControllerMessages {
	
	public Map<String, Object>   getMesssageNotFound(Long id) {
		Map<String, Object> responce = new HashMap<>();
		responce.put("MESSAGE: ",  "The client with id ".concat(id.toString().concat(" dosent exist")));
		return responce;
	}
	
	public Map<String, Object>   getMesssageForSQLException(String message) {
		Map<String, Object> responce = new HashMap<>();
		responce.put("MESSAGE: ",  "Database error");
		responce.put("ERROR: ",  message);
		return responce;
	}
	
	public Map<String, Object>   getMesssageAlreadyExist() {
		Map<String, Object> responce = new HashMap<>();
		responce.put("MESSAGE: ",  "This  client already exist");
		return responce;
	}
	
	public Map<String, Object>   getMesssageNotExist() {
		Map<String, Object> responce = new HashMap<>();
		responce.put("MESSAGE: ",  "This  client not exist");
		return responce;
	}
	
	public Map<String, Object>   getMesssageDeleted() {
		Map<String, Object> responce = new HashMap<>();
		responce.put("MESSAGE: ",  "Deleted");
		return responce;
	}
	
	public Map<String, Object>   getMesssageBinding(List<String> messages) {
		Map<String, Object> responce = new HashMap<>();
		responce.put("ERRORS:: ", responce);
		return responce;
	}
	
	public Map<String, Object>   getMesssageUploadFileError() {
		Map<String, Object> responce = new HashMap<>();
		responce.put("ERROR: ",  "Error to upload file");
		return responce;
	}
	
	public Map<String, Object>   getMesssageFileError() {
		Map<String, Object> responce = new HashMap<>();
		responce.put("Error: ",  "Error to get file");
		return responce;
	}	

}
