package com.myshopping.user_service.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PriceDetailsDTO {
	
	@NotNull(message="Listing price must be present.")
	private Double listingPrice;
	
	@NotNull(message="Special price must be present.")
	private Double specialPrice;
	
	@NotNull(message="Total fees must be present.")
	private Double totalFees;

	@NotNull(message="Total amount must be present.")
	private Double totalAmount;
	
	@NotBlank(message="Paid by must be present.")
	private String paidBy;
}
