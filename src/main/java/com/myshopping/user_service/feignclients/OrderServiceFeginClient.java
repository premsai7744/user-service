package com.myshopping.user_service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("orders-service")
public interface OrderServiceFeginClient {
	@GetMapping("/orders/search/orders/{paidBy}")
	ResponseEntity<List<com.myshopping.user_service.dto.OrdersInfoDTO>> searchOrders(@PathVariable String paidBy);
}
