package com.ceiba.parking.excepciones;

public class NotFoundVehiculoPlacaException extends Exception {
	
	private static final long SerivalVersionUID = 12345L;
	
	public static final String DESCRIPTION = "No se puede encontrar un vehiculo con la placa indicada";
	
	public static final int CODE = 333;
	
	
	public NotFoundVehiculoPlacaException() {
		this("");
	}
	
	public NotFoundVehiculoPlacaException(String detail) {
		
		super(DESCRIPTION+"."+detail+".CODE:"+CODE);
	}

}
