package com.codechallenge.application.enums;

public enum ChannelEnum {

	CLIENT,
	ATM,
	INTERNAL;
	
	public static boolean contains (String channel) {
		for (ChannelEnum c: ChannelEnum.values()) {
			if (c.name().equalsIgnoreCase(channel))
				return true;
		}
		return false;
	}
}
