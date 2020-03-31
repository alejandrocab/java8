package com.smartup.shippingapplication.mappers;

import org.springframework.stereotype.Component;

import com.smartup.shippingapplication.dto.ItemProductDto;
import com.smartup.shippingapplication.entities.ItemProduct;

@Component
public class ItemProductMapper {

	public ItemProduct dto2Entity (ItemProductDto dto) {
		return ItemProduct.builder()
				.idProduct(dto.getIdProduct())
				.amount(dto.getAmount())
				.build();
	}
	
	public ItemProductDto entity2Dto (ItemProduct entity) {
		return ItemProductDto.builder()
				.idProduct(entity.getIdProduct())
				.amount(entity.getAmount())
				.build();
	}
}
