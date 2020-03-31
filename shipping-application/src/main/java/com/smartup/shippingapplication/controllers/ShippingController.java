package com.smartup.shippingapplication.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartup.shippingapplication.dto.ListShippingDto;
import com.smartup.shippingapplication.services.ShippingService;

@RestController
@RequestMapping("/shippings")
public class ShippingController {

	private ShippingService service;
	
	public ShippingController(ShippingService service) {
		this.service=service;
	}
	
	@PostMapping
	public ResponseEntity<ListShippingDto> createShipping (@RequestBody @Valid ListShippingDto inDto){
		ListShippingDto response = service.createShipping(inDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
