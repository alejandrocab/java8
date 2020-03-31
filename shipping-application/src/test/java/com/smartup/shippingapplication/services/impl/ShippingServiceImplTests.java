package com.smartup.shippingapplication.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.smartup.shippingapplication.dto.ItemProductDto;
import com.smartup.shippingapplication.dto.ListShippingDto;
import com.smartup.shippingapplication.dto.ShippingDto;
import com.smartup.shippingapplication.entities.Shipping;
import com.smartup.shippingapplication.mappers.ListShippingMapper;
import com.smartup.shippingapplication.mappers.ShippingMapper;
import com.smartup.shippingapplication.repositories.ShippingRepository;

@RunWith(MockitoJUnitRunner.class)
public class ShippingServiceImplTests {

	
	private  ItemProductDto p1;
	private ItemProductDto p2;
	
	@InjectMocks
	private ShippingServiceImpl service;
	@Mock
	private ShippingRepository repository;
	@Mock
	private ShippingMapper mapper;
	@Mock
	private ListShippingMapper listMapper;
	
	@Before
	public void setUpTest() {
		p1 = ItemProductDto.builder().idProduct(new Long(1)).amount(new Long(1)).build();
		p2 = ItemProductDto.builder().idProduct(new Long(2)).amount(new Long(1)).build();
	}
	
	@Test
	public void whenInputValidThenReturnCreated(){
		ShippingDto shipping1Dto = ShippingDto.builder()
				.idClient(new Long(1))
				.city("Madrid")
				.address("C/ Puerto Rico")
				.postalCode(28900)
				.itemsProduct(Arrays.asList(p1,p2))
				.build();
		
		List<ShippingDto> shippings = Arrays.asList(shipping1Dto);
		ListShippingDto inDto = ListShippingDto.builder().shippings(shippings).build();
		
		when(mapper.dto2Entity(shipping1Dto)).thenReturn(Mockito.mock(Shipping.class));
		when(repository.saveAndFlush(Mockito.any(Shipping.class))).thenReturn(Mockito.mock((Shipping.class)));
		when(mapper.entity2Dto(Mockito.any((Shipping.class)))).thenReturn(shipping1Dto);
		when(listMapper.list2Dto(Mockito.<ShippingDto>anyList())).thenReturn(inDto);
		
		
		ListShippingDto outDto =  service.createShipping(inDto);
		
		assertEquals(inDto.getShippings().size(), outDto.getShippings().size());
	}
}
