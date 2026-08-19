package com.myshopping.user_service.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequestDTO {
	@NotBlank(message = "Invalid first name, Please try again.")
	@Size(min=3,max=50,message="Invalid first name, min chars allowed is 3, max chars allowed is 50")
	private String firstName;
	
	@NotBlank(message = "Invalid last name, Please try again.")
	@Size(min=3,max=50,message="Invalid last name, min chars allowed is 3, max chars allowed is 50")
	private String lastName;
	
	@NotBlank(message = "Invalid date of birth, Please try again.")
	private LocalDate dateOfBirth;
	
	@NotBlank(message = "Invalid gender, Please try again.")
	private String gender;
	
	@NotBlank(message = "Invalid email, Please try again.")
	@Email(message = "Invalid email, Please try again.")
	private String email;
	
	@NotBlank(message = "Invalid password, Please try again.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message="Invalid password, Please try again.")
	private String password;
	
	@NotBlank(message = "Invalid contact, Please try again.")
	@Pattern(regexp = "^[6-9]/d{9}", message = "Invalid contact, Please try again.")
	private String contact;
}
