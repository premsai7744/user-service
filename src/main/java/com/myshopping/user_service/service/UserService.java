package com.myshopping.user_service.service;

import com.myshopping.user_service.dto.UserCredentialsRequestDTO;
import com.myshopping.user_service.dto.UserDetailsResponseDTO;
import com.myshopping.user_service.dto.UserRegistrationRequestDTO;

public interface UserService {

	String userRegistration(UserRegistrationRequestDTO userRegistrationRequestDTO);

	UserDetailsResponseDTO login(UserCredentialsRequestDTO userCredentialsRequestDTO);
	
}
