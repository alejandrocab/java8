package com.smartup.manageorderapplication.utils;

public enum Cities {

	ALAVA,
	ALBACETE,
	ALICANTE,
	ALMERIA,
	AVILA,
	BADAJOZ,
	BALEARES,
	BARCELONA,
	BURGOS,
	CACERES,
	CADIZ,
	CASTELLON,
	CIUDAD_REAL,
	CORDOBA,
	ACORUÑA,
	CUENCA,
	GIRONA,
	GRANADA,
	GUADALAJARA,
	GUIPUZCOA,
	HUELVA,
	HUESCA,
	JAEN,
	LEON,
	LLEIDA,
	LA_RIOJA,
	LUGO,
	MADRID,
	MALAGA,
	MURCIA,
	NAVARRA,
	OURENSE,
	ASTURIAS,
	PALENCIA,
	LAS_PALMAS,
	PONTEVEDRA,
	SALAMANCA,
	SANTA_CRUZ_DE_TENERIFE,
	CANTABRIA,
	SEGOVIA,
	SEVILLA,
	SORIA,
	TARRAGONA,
	TERUEL,
	TOLEDO,
	VALENCIA,
	VALLADOLID,
	VIZCAYA,
	ZAMORA,
	ZARAGOZA,
	CEUTA,
	MELILLA
	;
	

	public static boolean contains (String city) {
		city = city.replaceAll(" ", "_");
		for (Cities c: Cities.values()) {
			if (c.name().equalsIgnoreCase(city))
				return true;
		}
		return false;
	}
}
