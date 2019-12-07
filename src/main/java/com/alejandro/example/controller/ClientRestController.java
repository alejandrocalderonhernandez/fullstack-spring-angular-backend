package com.alejandro.example.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alejandro.example.model.dto.ClientDTO;
import com.alejandro.example.model.entity.ClientEntity;
import com.alejandro.example.service.interfaces.IClientService;
import com.alejandro.example.util.GenericControllerMessages;
import com.alejandro.example.util.StaticControllerMessages;
import com.alejandro.example.util.StringUtil;
import com.alejandro.example.util.helper.FileHelper;
import com.alejandro.example.util.mapper.Mapper;

@CrossOrigin()
@RestController()
@RequestMapping("clients")
public class ClientRestController {
	
	private static final Logger LOG = LogManager.getLogger(ClientRestController.class);
	private static final String PATH_IMG = "uploads";
	private static  final int LIMIT = 5;

	@Autowired
	@Qualifier("clientServiceImpl")
	IClientService service;
	
	@Autowired
	GenericControllerMessages<ClientEntity> genericMesages;
	
	@Autowired
	FileHelper fileHelper;
	
	@Autowired
	StringUtil string;
	
	@Autowired
	StaticControllerMessages staticMessages;
	

	@GetMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<ClientDTO> responce = service.findAllClients().stream()
				.map(Mapper::mapClient).collect(Collectors.toList());
		if(!responce.isEmpty()) {
			LOG.info("Find all, total size: " + responce.size());
			return new ResponseEntity<List<ClientDTO>>(responce, HttpStatus.OK) ;
		} 
			return new ResponseEntity<List<ClientDTO>>(HttpStatus.NO_CONTENT);			
	}
	
	@GetMapping(path = "/clients/page/{page}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Page<ClientEntity>> findAllPageable(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, LIMIT);
		Page<ClientEntity> responce = service.findAllClients(pageable);
		if(!responce.isEmpty()) {
			LOG.info("Find all  ");
			return new ResponseEntity<Page<ClientEntity>>(responce, HttpStatus.OK) ;
		} 
			return new ResponseEntity<Page<ClientEntity>>(HttpStatus.NO_CONTENT);			
	}
	
	@GetMapping(path = "client/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> getClientById( @PathVariable Long id) {
		ClientDTO responce = null;
		try {
			 responce = Mapper.mapClient(service.findById(id));
		} catch (DataAccessException e) {
			return new ResponseEntity<Map<String, Object>>(
					staticMessages.getMesssageForSQLException(e.getMessage())
					, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(responce == null ) {
			return new ResponseEntity<Map<String, Object>>(staticMessages.getMesssageNotFound(id)
					, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ClientDTO>(responce, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping(path = "/client", consumes={"application/json"}) 
	public ResponseEntity<?> create( @Valid @RequestBody ClientDTO client, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<>();
			
			bindingResult.getAllErrors().forEach( error -> {
				errors.add(error.getDefaultMessage());
			});
			return new ResponseEntity<Map<String, Object>>(staticMessages.getMesssageBinding(errors)
					, HttpStatus.BAD_REQUEST);
		}
		if(service.findByEmail(client.getEmail()) != null) {
			return new ResponseEntity<Map<String, Object>>(staticMessages.getMesssageAlreadyExist()
					, HttpStatus.BAD_REQUEST);
		}
		try {
			client.setCreatedAt(LocalDateTime.now());
			ClientEntity clientEntity = Mapper.mapClient(client);
			service.save(clientEntity);
			LOG.info("New client with id: " + client.getId());
			return new ResponseEntity<Map<String, ClientEntity>>(genericMesages.getMesssageCreatedSuccess(clientEntity)
					, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			LOG.error("Fail creating client with id: " + client.getId());
			return new ResponseEntity<Map<String, Object>>(staticMessages.getMesssageForSQLException(e.getMessage())
					, HttpStatus.INTERNAL_SERVER_ERROR); 
	   }
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PutMapping(path = "client/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes={"application/json"})
	public ResponseEntity<?> update( @Valid  @RequestBody ClientDTO client, 
			BindingResult bindingResult,  @PathVariable Long id){
		
		if(bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<>();
			bindingResult.getAllErrors().forEach( error -> {
				errors.add(error.getDefaultMessage());
			});
		}
		
		ClientEntity clientToUpdate = service.findById(id);
		if( clientToUpdate != null) {
			clientToUpdate.setEmail(client.getEmail());
			clientToUpdate.setLastname(client.getLastname());
			clientToUpdate.setName(client.getName());
			clientToUpdate.setRegion(client.getRegion());
			service.save(clientToUpdate);
			LOG.info("Client edited woth id: " + clientToUpdate.getId());
			return new ResponseEntity<Map<String, ClientEntity>>(genericMesages.getMesssageUpdatedSuccess(clientToUpdate)
					, HttpStatus.OK);
		}
		
		return new ResponseEntity<Map<String, Object>>(staticMessages.getMesssageNotExist()
				, HttpStatus.BAD_REQUEST);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping(path = "client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?>   delete(@PathVariable Long id) { 
		ClientEntity client = service.findById(id);
		if( client != null ) {
			String beforePhotoName =  client.getPhoto();
			if(beforePhotoName != null && beforePhotoName.length() > 0) {
				fileHelper.deleteFile( beforePhotoName);
			}
			service.delete(id);
			LOG.info("Client deleted id: " + id);
			return new ResponseEntity<Map<String, Object>>(staticMessages.getMesssageDeleted()
					, HttpStatus.OK); 
		} else {
			return new ResponseEntity<Map<String, Object>>(staticMessages.getMesssageNotExist()
					, HttpStatus.BAD_REQUEST);
		}
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping(path = "clients/upload")
	public ResponseEntity<?> uploadImg(@RequestParam("img") MultipartFile img, @RequestParam("id") Long id) {
		ClientEntity client = service.findById(id);
		if(!img.isEmpty()) {
			String fileName =  UUID.randomUUID().toString() + "_" +  img.getOriginalFilename();
			String beforePhotoName =  client.getPhoto();
			if(beforePhotoName != null && beforePhotoName.length() > 0) {
				fileHelper.deleteFile( beforePhotoName);
			}
			Path path = Paths.get(PATH_IMG).resolve(fileName).toAbsolutePath();
			if(fileHelper.saveFile(path, img)) {
				client.setPhoto(fileName);
				service.save(client);
				return new ResponseEntity<Map<String, Object>>(genericMesages.getMesssageUploadFileSuccess(client)
						,HttpStatus.CREATED);
			}
			client.setPhoto(fileName);
		}
		return new ResponseEntity<Map<String, Object>>(staticMessages.getMesssageUploadFileError()
				, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping(path = "/clients/img/{name:.+}")
	public ResponseEntity<?> viewPhoto(@PathVariable String name) {
		Path path = Paths.get(PATH_IMG).resolve(name).toAbsolutePath();
		Resource resource = fileHelper.getFile(path);
		if(resource.exists() && resource.isReadable()) {
			HttpHeaders headesr =  new HttpHeaders();
			headesr.add(HttpHeaders.CONTENT_DISPOSITION, this.string.getAttachment(resource.getFilename()));
			return new  ResponseEntity<Resource>(resource, headesr, HttpStatus.OK);
		} 
			return new  ResponseEntity<Map<String, Object>>(
					staticMessages.getMesssageFileError(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
