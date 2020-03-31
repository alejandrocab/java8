package com.smartup.manageorderapplication.services.impl;



import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartup.manageorderapplication.dto.OrderDto;
import com.smartup.manageorderapplication.entities.Order;
import com.smartup.manageorderapplication.exceptions.OrderIdsInputInvalidException;
import com.smartup.manageorderapplication.exceptions.OrderUnavailableException;
import com.smartup.manageorderapplication.exceptions.ShippingCityUnavailableException;
import com.smartup.manageorderapplication.repositories.OrderRepository;
import com.smartup.manageorderapplication.services.OrderService;
import com.smartup.manageorderapplication.utils.Cities;
import com.smartup.manageorderapplication.utils.OrderMatch;
import com.smartup.manageorderapplication.utils.mappers.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private OrderMapper orderMapper;
	
	public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
		this.orderRepository=orderRepository;
		this.orderMapper=orderMapper;
	}
	
	@Override
	@Transactional(readOnly = false)
	public OrderDto createOrder(OrderDto inDto) {
		checkInDto(inDto);
		//tratamiento pk
		inDto.setId(null);
		inDto.getItems().stream().forEach(item -> item.setId(null));
		Order currentOrder = orderMapper.dto2Entity(inDto);
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime oldTenMinutesTime = LocalDateTime.now().minusMinutes(10);
		List<Order> lastOrders = orderRepository.findByClientIdAndOrderDateBetweenOrderByOrderDate(inDto.getClient().getId(), oldTenMinutesTime, now);
		System.out.println("*****Ultimos pedidos en 10 minutos atras:");
		lastOrders.stream().forEach(System.out::println);
		if (!lastOrders.isEmpty()) {
			//comprobar si hay algun pedido identico
			containSameOrder(lastOrders, currentOrder);
		}
		currentOrder = orderRepository.saveAndFlush(currentOrder);
		System.out.println(currentOrder);
		return orderMapper.entity2Dto(currentOrder);
	}
	
	private void checkInDto(OrderDto inDto) {		
		if (inDto.getClient().getId() == null || inDto.getItems().stream().anyMatch(item -> item.getProduct().getId()==null))
			throw new OrderIdsInputInvalidException();
		if (!Cities.contains(inDto.getShippingCity())) 
			throw new ShippingCityUnavailableException(inDto.getShippingCity());
	}
	
	private void containSameOrder (List<Order> lastOrders, Order currentOrder) {
		List<OrderMatch> orderMatch = currentOrder.getItems().stream().map(item -> {
			return OrderMatch.builder()
					.amount(item.getAmount())
					.idProduct(item.getProduct().getId())
					.build();
		}).collect(Collectors.toList());
		lastOrders.stream().forEachOrdered(oldOrder ->{
			List<OrderMatch> oldOrdersMatch = oldOrder.getItems().stream().map(item -> {
				return OrderMatch.builder()
						.amount(item.getAmount())
						.idProduct(item.getProduct().getId())
						.build();
			}).collect(Collectors.toList());
			
			boolean sameOrder = orderMatch.stream().allMatch(i -> oldOrdersMatch.contains(i));
			if (sameOrder)
				throw new OrderUnavailableException(currentOrder.getClient().getId()); 
		});
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrderDto> getOrders() {
		return orderRepository.findAll().stream().map(orderMapper::entity2Dto).collect(Collectors.toList());
	}
}
