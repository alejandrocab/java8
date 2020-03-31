package com.smartup.manageorderapplication.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartup.manageorderapplication.dto.ProductDto;
import com.smartup.manageorderapplication.entities.Product;
import com.smartup.manageorderapplication.repositories.ProductRepository;
import com.smartup.manageorderapplication.services.ProductService;
import com.smartup.manageorderapplication.utils.mappers.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService{

	private ProductRepository productRepository;
	private ProductMapper productMapper;
	
	public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
		this.productRepository=repository;
		this.productMapper=mapper;
	}
	
	@Override
	@Transactional(readOnly = false)
	public ProductDto createProduct(ProductDto inDto) {
		inDto.setId(null);
		Product entity = productRepository.save(productMapper.dto2Entity(inDto));
		return productMapper.entity2Dto(entity);
	}
}
