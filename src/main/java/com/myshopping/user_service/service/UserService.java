package com.myshopping.user_service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.myshopping.user_service.dto.DeleteUserDTO;
import com.myshopping.user_service.dto.OrdersInfoDTO;
import com.myshopping.user_service.dto.UserCredentialsRequestDTO;
import com.myshopping.user_service.dto.UserDetailsResponseDTO;
import com.myshopping.user_service.dto.UserRegistrationRequestDTO;
import com.myshopping.user_service.dto.UserUpdateDTO;

public interface UserService {

	String userRegistration(UserRegistrationRequestDTO userRegistrationRequestDTO);

	UserDetailsResponseDTO login(UserCredentialsRequestDTO userCredentialsRequestDTO);

	String updateUser(String emailId, UserUpdateDTO userUpdateDTO);

	String deleteUser(DeleteUserDTO deleteUserDTO);

	ResponseEntity<List<OrdersInfoDTO>> searchOrders(String paidBy);
	
}
