package com.alejandro.example.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class FileReaderUtil {
	
	private FileReaderUtil() {}
	
	private static final Logger LOG = LogManager.getLogger(FileReaderUtil.class);
	
	public static String  read(Path path) {
		Stream<String> lines = null;
		StringBuilder stringBuilder =  new StringBuilder();
		try {
			lines  = Files.lines(path, StandardCharsets.UTF_8);
			lines.forEach(stringBuilder::append);
		} catch (Exception e) {
			LOG.error("ERROR " + e.getMessage());
		} finally {
			try {
				lines.close();
			} catch (Exception e2) {
				
			}
		}
		return stringBuilder.toString();
	}

}
