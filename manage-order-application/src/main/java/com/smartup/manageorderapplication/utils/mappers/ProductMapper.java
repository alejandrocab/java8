package com.smartup.manageorderapplication.utils.mappers;

import org.springframework.stereotype.Component;

import com.smartup.manageorderapplication.dto.ProductDto;
import com.smartup.manageorderapplication.entities.Product;
import com.smartup.manageorderapplication.utils.CalculateTotalPriceComponent;

@Component
public class ProductMapper {
	
	private SizeMapper sizeMapper;
	private CalculateTotalPriceComponent calculateComponent;
	
	public ProductMapper(SizeMapper sizeMapper, CalculateTotalPriceComponent calculateComponent) {
		this.sizeMapper=sizeMapper;
		this.calculateComponent=calculateComponent;
	}
	
	public Product dto2Entity (ProductDto inDto) {
		return Product.builder()
				.id(inDto.getId())
				.name(inDto.getName())
				.model(inDto.getModel())
				.brand(inDto.getBrand())
				.weight(inDto.getWeight())
				.price(inDto.getPrice())
				.iva(inDto.getIva())
				.size(sizeMapper.dto2Entity(inDto.getSize()))
				.build();
	}
	
	public ProductDto entity2Dto (Product data) {
		return ProductDto.builder()
				.id(data.getId())
				.name(data.getName())
				.model(data.getModel())
				.brand(data.getBrand())
				.weight(data.getWeight())
				.price(data.getPrice())
				.iva(data.getIva())
				.size(sizeMapper.entity2Dto(data.getSize()))
				.totalPrice(calculateComponent.calculate(data.getPrice(), data.getIva()))
				.build();
	}
}
