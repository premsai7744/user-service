package com.myshopping.user_service.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myshopping.user_service.dto.DeleteUserDTO;
import com.myshopping.user_service.dto.UserCredentialsRequestDTO;
import com.myshopping.user_service.dto.UserDetailsResponseDTO;
import com.myshopping.user_service.dto.UserRegistrationRequestDTO;
import com.myshopping.user_service.dto.UserUpdateDTO;
import com.myshopping.user_service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;


@RestController
public class UserServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);
	
	@Autowired
	UserService userService;
	
	@PostMapping(path="/register")
	public ResponseEntity<String> userRegistration(@Valid @RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO){
		
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
	
	
	
	@PostMapping(path="/login")
	public ResponseEntity<UserDetailsResponseDTO> userLogin(@Valid @RequestBody UserCredentialsRequestDTO userCredentialsRequestDTO){
		
		logger.info("Login request received by controller : userCredentialsRequestDTO {}",userCredentialsRequestDTO);
		UserDetailsResponseDTO userDetailsResponseDTO = userService.login(userCredentialsRequestDTO);
		
		if(userDetailsResponseDTO==null) {
			logger.info("userDetailsResposneDTO is null {}",userDetailsResponseDTO);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
								 .body(userDetailsResponseDTO);
		} else {
			logger.info("userDetailsResponseDTO is not null {}",userDetailsResponseDTO);
			return ResponseEntity.status(HttpStatus.OK)
					             .body(userDetailsResponseDTO);
		}
		
	}
	
	
	@PutMapping("/update/profile/{email}")
	public ResponseEntity<String> updateUser(@PathVariable("email") String emailId,
			                                 @Valid @RequestBody UserUpdateDTO userUpdateDTO){
		logger.info("Update request received by controller. ResourceId : {}",emailId);
		
		String response = userService.updateUser(emailId,userUpdateDTO);
		logger.info("Response received by controller. response : {}",response);
		
		if(response!=null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(response);
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(response);
		}
	}
	
	
	@DeleteMapping("/delete/account")
	public ResponseEntity<String> deleteUser(@Valid @RequestBody DeleteUserDTO deleteUserDTO){
		logger.info("Delete request recieved by controller.");
		
		String response = userService.deleteUser(deleteUserDTO);
		logger.info("Response received from service, response : {}",response);
		
		if(response.equalsIgnoreCase("Email or password entered wrong, Unable to delete account.")) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(response);
		} else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(response);
		}
	}
	
	@CircuitBreaker(name = "user-service",fallbackMethod = "createOrderFailure")
	@GetMapping("/search/orders/{paidBy}")
	public ResponseEntity<List<com.myshopping.user_service.dto.OrdersInfoDTO>> searchOrders(@PathVariable String paidBy){
		return userService.searchOrders(paidBy);
		
	}
	
	public ResponseEntity<List<com.myshopping.user_service.dto.OrdersInfoDTO>> createOrderFailure(Throwable ex){
		System.out.println(ex.getMessage());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				             .body(Collections.emptyList());
	}

	
}

























