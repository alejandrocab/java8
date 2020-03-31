package com.smartup.manageorderapplication.utils;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderMatch {
	private Long amount;
	private Long idProduct;
}
