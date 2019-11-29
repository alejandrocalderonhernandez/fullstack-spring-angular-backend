package com.alejandro.example.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {
	
	StringBuilder builder = new StringBuilder();
	private static final String ATTACHMENT = "attachment; filename=";
	
	public String getAttachment (String fileName) {
		this.builder.append(ATTACHMENT).append("\"").append(fileName).append("\"");
		String responce = builder.toString();
		this.builder.setLength(0);
		return responce;
	}

}
