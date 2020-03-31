package com.smartup.manageorderapplication.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SizeDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Double height;
	private Double width;
	private Double depth;
}
