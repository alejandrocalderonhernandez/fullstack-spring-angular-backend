package com.alejandro.example.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.example.model.dto.RegionDTO;
import com.alejandro.example.service.interfaces.IClientService;
import com.alejandro.example.util.StaticControllerMessages;
import com.alejandro.example.util.mapper.Mapper;

@CrossOrigin(origins = "*", maxAge = 3600,
methods = {
		RequestMethod.GET,
		RequestMethod.POST,
		RequestMethod.PUT,
		RequestMethod.PATCH,
		RequestMethod.DELETE,
		RequestMethod.OPTIONS,
		RequestMethod.HEAD})
@RestController
@RequestMapping("/region")
public class RegionRestController {
	
	@Autowired
	@Qualifier("clientServiceImpl")
	IClientService service;
	
	@Autowired
	StaticControllerMessages staticMessages;
	
	@GetMapping(path = "/findAll",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRegions() {
		List<RegionDTO> responce = service.getAllRegions().stream().map(Mapper::mapRegion).collect(Collectors.toList());
		if(responce.size() > 0 && !responce.isEmpty() ) {
			return new ResponseEntity<List<RegionDTO>>(responce, HttpStatus.OK);
		}
		return new ResponseEntity<Map<String,Object>>(staticMessages.getMesssageNotExist(), HttpStatus.NO_CONTENT);
	}
}
