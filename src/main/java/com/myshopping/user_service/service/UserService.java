package com.myshopping.user_service.service;

import com.myshopping.user_service.dto.UserRegistrationRequestDTO;

public interface UserService {

	String userRegistration(UserRegistrationRequestDTO userRegistrationRequestDTO);
	
}
