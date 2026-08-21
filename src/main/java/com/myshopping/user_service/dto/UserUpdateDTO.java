package com.myshopping.user_service.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDTO {
	@NotBlank(message = "Invalid first name, Please try again.")
	@Size(min=3,max=50,message="Invalid first name, min chars allowed is 3, max chars allowed is 50")
	private String firstName;
	
	@NotBlank(message = "Invalid last name, Please try again.")
	@Size(min=3,max=50,message="Invalid last name, min chars allowed is 3, max chars allowed is 50")
	private String lastName;
	
	@NotNull(message = "Invalid date of birth, Please try again.")
	private LocalDate dateOfBirth;
	
	@NotBlank(message = "Invalid gender, Please try again.")
	private String gender;
	
	@NotBlank(message = "Invalid contact, Please try again.")
	@Pattern(regexp = "^[6-9]\\d{9}", message = "Invalid contact, Please try again.")
	private String contact;
}
