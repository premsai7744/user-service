package com.myshopping.user_service.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshopping.user_service.dto.UserRegistrationRequestDTO;
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
		
		//Dto to entity conversion
		UserRegistration userRegistration = userMapper.toEntity(userRegistrationRequestDTO);
		logger.info("Dto to entity conversion : userRegistraion entity :  {}",userRegistration);
		
		if(userRepository.findByEmail(userRegistrationRequestDTO.getEmail()).isPresent()) {
			return "User already exists with email "+userRegistrationRequestDTO.getEmail()+", Please try again.";
		} else {
			UserRegistration savedEntity = userRepository.save(userRegistration);
			return "Registration successful for user : "+savedEntity.getFirstName()+", Please login with your credentials.";	
		}
	}

	
	
}
