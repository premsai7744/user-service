package com.myshopping.user_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myshopping.user_service.dto.UserRegistrationRequestDTO;
import com.myshopping.user_service.service.UserService;


@RestController
@RequestMapping(path="/user")
public class UserServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);
	
	@Autowired
	UserService userService;
	
	@PostMapping(path="/register")
	public ResponseEntity<String> userRegistration(@RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO){
		
		logger.info("User registration request received by controller {}",userRegistrationRequestDTO);
		
		String response = userService.userRegistration(userRegistrationRequestDTO);
		
		if(response.contains("Registration successful")) {
			logger.info("Response received from service {}",response);
			return ResponseEntity.status(HttpStatus.CREATED)
					             .body(response);
		} else {
			logger.info("Response received from service {}",response);
			return ResponseEntity.status(HttpStatus.CONFLICT)
								 .body(response);
		}
	}
}
