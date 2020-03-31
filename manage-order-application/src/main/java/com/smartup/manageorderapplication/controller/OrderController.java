package com.smartup.manageorderapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartup.manageorderapplication.dto.OrderDto;
import com.smartup.manageorderapplication.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private OrderService orderSrv;
	
	public OrderController(OrderService orderSrv) {
		this.orderSrv=orderSrv;
	}
	
	@PostMapping
	public ResponseEntity<OrderDto> createOrder (@Valid @RequestBody OrderDto inDto){
		OrderDto response = orderSrv.createOrder(inDto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<List<OrderDto>> getAllOrders() {
		List<OrderDto> response = orderSrv.getOrders();
		return ResponseEntity.ok(response);
	}
}
