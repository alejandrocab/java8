package com.smartup.manageorderapplication.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.smartup.manageorderapplication.dto.ProductDto;
import com.smartup.manageorderapplication.entities.Product;
import com.smartup.manageorderapplication.repositories.ProductRepository;
import com.smartup.manageorderapplication.utils.mappers.ProductMapper;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	@Mock
	private ProductRepository repository;
	
	@Mock
	private ProductMapper transformProduct;
	
	@InjectMocks
	private ProductServiceImpl service;
	
	@Test
	public void shouldCreateAProductWhenInputValid() {
		Product entity = Product.builder().build();
		ProductDto dto = ProductDto.builder().build();
		when(repository.save(Mockito.any(Product.class))).thenReturn(entity);
		when(transformProduct.dto2Entity(Mockito.any(ProductDto.class))).thenReturn(entity);
		when(transformProduct.entity2Dto(Mockito.any(Product.class))).thenReturn(dto);
		
		ProductDto response = service.createProduct(dto);
		
		assertEquals(dto.getId(), response.getId());
	}
}
