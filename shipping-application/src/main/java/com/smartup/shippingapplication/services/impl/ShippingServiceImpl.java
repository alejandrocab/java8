package com.smartup.shippingapplication.services.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartup.shippingapplication.dto.ListShippingDto;
import com.smartup.shippingapplication.mappers.ListShippingMapper;
import com.smartup.shippingapplication.mappers.ShippingMapper;
import com.smartup.shippingapplication.repositories.ShippingRepository;
import com.smartup.shippingapplication.services.ShippingService;

@Service
public class ShippingServiceImpl implements ShippingService {
	
	private ShippingRepository repository;
	private ShippingMapper mapper;
	private ListShippingMapper listMapper;
	
	public ShippingServiceImpl(ShippingRepository repository, ShippingMapper mapper, ListShippingMapper listMapper) {
		this.repository=repository;
		this.mapper=mapper;
		this.listMapper=listMapper;
	}
	
	@Override
	@Transactional(readOnly = false)
	public ListShippingDto createShipping(ListShippingDto inDto) {
		return listMapper.list2Dto(inDto.getShippings().stream()
				.map(mapper::dto2Entity)
				.map(repository::saveAndFlush)
				.map(mapper::entity2Dto)
				.collect(Collectors.toList()));
	}

}
