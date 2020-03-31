package com.smartup.manageorderapplication.services;

import java.util.List;

import com.smartup.manageorderapplication.dto.OrderDto;

public interface OrderService {
	public OrderDto createOrder (OrderDto inDto);
	public List<OrderDto> getOrders();
}
