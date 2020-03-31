package com.smartup.manageorderapplication.utils.mappers;

import org.springframework.stereotype.Component;

import com.smartup.manageorderapplication.dto.SizeDto;
import com.smartup.manageorderapplication.entities.Size;

@Component
public class SizeMapper {

	public Size dto2Entity (SizeDto inDto) {
		return inDto!= null ? Size.builder()
		.height(inDto.getHeight())
		.width(inDto.getWidth())
		.depth(inDto.getDepth())
		.build() : null;
	}
	
	public SizeDto entity2Dto (Size data) {
		return data!= null ? SizeDto.builder()
		.height(data.getHeight())
		.width(data.getWidth())
		.depth(data.getDepth())
		.build() : null;
	}
}
