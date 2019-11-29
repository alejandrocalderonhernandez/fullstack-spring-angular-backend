package com.alejandro.example.util.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHelper {
	
	private static final String PATH= "uploads";
	private static final Logger LOG = LogManager.getLogger(FileHelper.class);
	
	public boolean saveFile(Path path, MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), path);
			LOG.info("SUCCESS -> New file added: " + file.getOriginalFilename() );
			return true;
		} catch (IOException e) {
			LOG.error("ERROR -> " + e.getMessage());
			return false;
		}
	}
	
	public boolean deleteFile( String name) {
		File file = Paths.get(PATH).resolve(name).toFile();
		if(file.exists() && file.canRead()) {
			if(file.delete()) {
				LOG.info("SUCCESS -> New file deleted: " + name );
				return true;
			}		
		}
		LOG.error("ERROR ->  Cant delete file with name " + name  );
		return false;
	}
	
	public Resource getFile(Path path) {
		try {
			return new UrlResource(path.toUri());
		} catch (IOException e) {
			return null;
		}
	}
	
}
