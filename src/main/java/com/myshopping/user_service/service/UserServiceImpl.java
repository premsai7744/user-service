package com.myshopping.user_service.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshopping.user_service.dto.UserCredentialsRequestDTO;
import com.myshopping.user_service.dto.UserDetailsResponseDTO;
import com.myshopping.user_service.dto.UserRegistrationRequestDTO;
import com.myshopping.user_service.entity.UserDetailsResponse;
import com.myshopping.user_service.entity.UserRegistration;
import com.myshopping.user_service.mapper.UserMapper;
import com.myshopping.user_service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public String userRegistration(UserRegistrationRequestDTO userRegistrationRequestDTO) {
		
		logger.info("User registration request received by service {}",userRegistrationRequestDTO);
		
		//Dto to Entity conversion.
		UserRegistration userRegistration = userMapper.toUserRegistrationEntity(userRegistrationRequestDTO);
		logger.info("Dto to entity conversion : userRegistraion entity :  {}",userRegistration);
		
		if(userRepository.findByEmail(userRegistrationRequestDTO.getEmail()).isPresent()) {
			return "User already exists with email "+userRegistrationRequestDTO.getEmail()+", Please try again.";
		} else {
			UserRegistration savedEntity = userRepository.save(userRegistration);
			return "Registration successful for user : "+savedEntity.getFirstName()+", Please login with your credentials.";	
		}
	}

	@Override
	public UserDetailsResponseDTO login(UserCredentialsRequestDTO userCredentialsRequestDTO) {
		logger.info("User credentials received by service : userCredentialsRequestDTO {}",userCredentialsRequestDTO);
		
		Optional<UserRegistration> optionalOfUserRegistration = 
				userRepository.findByEmailAndPassword(userCredentialsRequestDTO.getEmail(),
													  userCredentialsRequestDTO.getPassword());
		
		UserDetailsResponseDTO userDetailsResponseDTO = null;
		
		if(optionalOfUserRegistration.isPresent()) {
			logger.info("User exists, valid credentials.");
			UserRegistration userRegistration = optionalOfUserRegistration.get(); 
			
			//Dto to Entity conversion.
			userDetailsResponseDTO = userMapper.toUserDetailsResponseDTO(userRegistration);
			logger.info("User details {}",userDetailsResponseDTO);
			return userDetailsResponseDTO;
		} else {
			logger.info("User doesn't exist, Invalid credentials.");
			logger.info("User details {}",userDetailsResponseDTO);
			return userDetailsResponseDTO;
		}
	}
	
}
