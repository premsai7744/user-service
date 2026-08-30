package com.myshopping.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeliveryDetailsDTO {
	@NotBlank(message="Home address must be present.")
	private String home;
	
	@NotBlank(message="Phone number must be present. ")
	private String phone;
}
