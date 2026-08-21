package com.myshopping.user_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.myshopping.user_service.dto.UserDetailsResponseDTO;
import com.myshopping.user_service.dto.UserRegistrationRequestDTO;
import com.myshopping.user_service.dto.UserUpdateDTO;
import com.myshopping.user_service.entity.UserDetailsResponse;
import com.myshopping.user_service.entity.UserRegistration;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(target="userId",ignore = true)
	UserRegistration toUserRegistrationEntity(UserRegistrationRequestDTO userRegistrationRequestDTO);
		
	UserRegistrationRequestDTO toDTO(UserRegistration userRegistration);
	
	UserDetailsResponseDTO toUserDetailsResponseDTO(UserRegistration userRegistration);
	
	@Mapping(target="userId",ignore = true)
	@Mapping(target="email",ignore = true)
	@Mapping(target="password",ignore = true)
	void toUpdateRegistrationEntity(UserUpdateDTO userUpdateDTO,@MappingTarget UserRegistration userRegistration);
	
	
     
}
