package com.smartup.manageorderapplication.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.smartup.manageorderapplication.dto.ListShippingDto;

@FeignClient(name="shipping-service", url = "${shipping-service.url}")
public interface ShippingService {

	@PostMapping("/shippings")
	ResponseEntity<ListShippingDto> shipping (@RequestBody ListShippingDto inDto);
}
