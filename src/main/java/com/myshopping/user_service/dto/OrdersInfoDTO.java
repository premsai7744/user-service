package com.myshopping.user_service.dto;


import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdersInfoDTO {
	
	@NotBlank(message="Product name must be present.")
	private String orderProductName; 
	
	@NotNull(message="Order price must be present.")
	private Double orderPrice; 

	@NotNull(message="Order confirmation date should be present.")
	private LocalDateTime orderConfirmedDate; 
	
	@NotNull(message="Order delivery date should be present.")
	private LocalDateTime orderDeliverdDate;
	
	@Valid
	@NotNull(message="Delivery details should be present.")
	private DeliveryDetailsDTO deliveryDetails;
	
	@Valid
	@NotNull(message="Price details should be present.")
	private PriceDetailsDTO priceDetails;
}
