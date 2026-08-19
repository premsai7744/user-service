package com.myshopping.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserCredentialsRequestDTO {
	@NotBlank(message = "Invalid email, Please try again.")
	@Email(message = "Invalid email, Please try again.")
	private String email;
	
	@NotBlank(message = "Invalid password, Please try again.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message="Invalid password, Please try again.")
	private String password;
}
