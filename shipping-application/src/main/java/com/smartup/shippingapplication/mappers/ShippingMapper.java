package com.smartup.shippingapplication.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.smartup.shippingapplication.dto.ShippingDto;
import com.smartup.shippingapplication.entities.Shipping;

@Component
public class ShippingMapper {

	private ItemProductMapper productMapper;
	
	public ShippingMapper(ItemProductMapper productMapper) {
		this.productMapper=productMapper;
	}
	
	public Shipping dto2Entity (ShippingDto dto) {
		return Shipping.builder()
				.id(dto.getId())
				.idClient(dto.getIdClient())
				.address(dto.getAddress())
				.city(dto.getCity())
				.postalCode(dto.getPostalCode())
				.itemsProduct(dto.getItemsProduct().stream().map(productMapper::dto2Entity).collect(Collectors.toList()))
				.build();
	}
	
	public ShippingDto entity2Dto (Shipping entity) {
		return ShippingDto.builder()
				.id(entity.getId())
				.idClient(entity.getIdClient())
				.address(entity.getAddress())
				.city(entity.getCity())
				.postalCode(entity.getPostalCode())
				.itemsProduct(entity.getItemsProduct().stream().map(productMapper::entity2Dto).collect(Collectors.toList()))
				.build();
	}
}
