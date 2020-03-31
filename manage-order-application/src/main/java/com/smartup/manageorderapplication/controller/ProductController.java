package com.smartup.manageorderapplication.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartup.manageorderapplication.dto.ProductDto;
import com.smartup.manageorderapplication.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productSrv;
	
	public ProductController(ProductService productSrv) {
		this.productSrv=productSrv;
	}
	
	@PostMapping
	public ResponseEntity<ProductDto> createProduct (@Valid @RequestBody ProductDto inDto){
		ProductDto serviceResponse = productSrv.createProduct(inDto);
		return ResponseEntity.ok(serviceResponse);
	}
}
