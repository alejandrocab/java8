package com.smartup.manageorderapplication.utils.mappers;

import org.springframework.stereotype.Component;

import com.smartup.manageorderapplication.dto.ItemOrderDto;
import com.smartup.manageorderapplication.entities.ItemOrder;
import com.smartup.manageorderapplication.entities.Product;
import com.smartup.manageorderapplication.exceptions.ProductNotFoundException;
import com.smartup.manageorderapplication.repositories.ProductRepository;

@Component
public class ItemOrderMapper {

	private ProductMapper productMapper;
	private ProductRepository productRepository;
	
	public ItemOrderMapper(ProductMapper productMapper, ProductRepository productRepository) {
		this.productMapper=productMapper;
		this.productRepository=productRepository;
	}
	
	public ItemOrder dto2Entity (ItemOrderDto inDto) {
		Product p = productRepository.findById(inDto.getProduct().getId()).orElseThrow(()-> new ProductNotFoundException(inDto.getProduct().getId()));
		return ItemOrder.builder()
				.id(inDto.getId())
				.amount(inDto.getAmount())
				.product(p)
				.build();
	}
	
	public ItemOrderDto entity2Dto (ItemOrder entity) {
		
		return ItemOrderDto.builder()
				.id(entity.getId())
				.amount(entity.getAmount())
				.product(productMapper.entity2Dto(entity.getProduct()))
				.build();
	}
}
