package com.smartup.shippingapplication.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.smartup.shippingapplication.dto.ListShippingDto;
import com.smartup.shippingapplication.dto.ShippingDto;

@Component
public class ListShippingMapper {

	public ListShippingDto list2Dto(List<ShippingDto> list) {
		return ListShippingDto.builder()
				.shippings(list)
				.build();
	}
}
