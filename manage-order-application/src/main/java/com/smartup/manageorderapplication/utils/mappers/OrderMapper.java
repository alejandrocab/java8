package com.smartup.manageorderapplication.utils.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.smartup.manageorderapplication.dto.OrderDto;
import com.smartup.manageorderapplication.entities.Client;
import com.smartup.manageorderapplication.entities.Order;
import com.smartup.manageorderapplication.exceptions.ClientNotFoundException;
import com.smartup.manageorderapplication.repositories.ClientRepository;
import com.smartup.manageorderapplication.utils.CalculateTotalPriceComponent;
import com.smartup.manageorderapplication.utils.OrderStatus;

@Component
public class OrderMapper {

	private ClientMapper clientMapper;
	private ItemOrderMapper itemOrderMapper;
	private ClientRepository clientRepository;
	private CalculateTotalPriceComponent calculateTotalPriceComponent;
	
	public OrderMapper(ClientMapper clientMapper, ItemOrderMapper itemOrderMapper, ClientRepository clientRepository, CalculateTotalPriceComponent calculateTotalPriceComponent) {
		this.clientMapper=clientMapper;
		this.itemOrderMapper=itemOrderMapper;
		this.clientRepository=clientRepository;
		this.calculateTotalPriceComponent=calculateTotalPriceComponent;
	}
	
	public Order dto2Entity(OrderDto inDto) {
		Client c = clientRepository.findById(inDto.getClient().getId()).orElseThrow(()-> new ClientNotFoundException(inDto.getClient().getId()));
		return Order.builder()
				.id(inDto.getId())
				.address(inDto.getAddress())
				.shippingCity(inDto.getShippingCity())
				.postalCode(inDto.getPostalCode())
				.status(OrderStatus.getRandomStatus())
				.client(c)
				.items(inDto.getItems().stream().map(itemOrderMapper::dto2Entity).collect(Collectors.toList()))
				.build();
	}
	
	public OrderDto entity2Dto (Order entity) {
		OrderDto response = OrderDto.builder()
				.id(entity.getId())
				.address(entity.getAddress())
				.shippingCity(entity.getShippingCity())
				.postalCode(entity.getPostalCode())
				.status(entity.getStatus())
				.client(clientMapper.entity2Dto(entity.getClient()))
				.items(entity.getItems().stream().map(itemOrderMapper::entity2Dto).collect(Collectors.toList()))
				.build();
		response.setTotalPriceOrder(calculateTotalPriceComponent.calculateTotalPriceOrder(response.getItems()));
		return response;
	}
}
