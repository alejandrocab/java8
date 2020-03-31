package com.smartup.manageorderapplication.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.DoubleStream;

import org.springframework.stereotype.Component;

import com.smartup.manageorderapplication.dto.ItemOrderDto;

@Component
public class CalculateTotalPriceComponent {
	
	public Double calculate (Double unitPrice, Double iva) {
		Double percent = iva / 100;
		Double taxPrice = unitPrice * percent;
		return unitPrice + taxPrice;
	}
	
	public Double calculateTotalPriceOrder (List<ItemOrderDto> items) {
		DoubleStream streamTotalPricePerProduct = items.stream().mapToDouble(item ->{
			Long productAmount = item.getAmount();
			Double productPrice = item.getProduct().getTotalPrice();
			BigDecimal bdProductPrice = BigDecimal.valueOf(productPrice.doubleValue());
			bdProductPrice = bdProductPrice.multiply(BigDecimal.valueOf(productAmount.longValue()));
			return bdProductPrice.doubleValue();
		});
		return streamTotalPricePerProduct.sum();
	}
}
