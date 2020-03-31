package com.smartup.manageorderapplication.utils;

import java.util.Random;

public enum OrderStatus {

	OK,
	ERROR,
	FINALIZED;
	
	public static OrderStatus getRandomStatus () {
		return values()[new Random().nextInt(values().length-1)];
	}
}
