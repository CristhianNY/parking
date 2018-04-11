package com.ceiba.parking.excepciones;

public class NotFoundVehiculoException extends Exception {

	private static final long SerivalVersionUID = 123455L;
		
		public static final String DESCRIPTION = "No se encontro Vehiculos en la db";
		
		public static final int CODE = 334;
	
		public NotFoundVehiculoException() {
			this("");
		}
		
		public NotFoundVehiculoException(String detail) {
			
			super(DESCRIPTION+"."+detail+".CODE:"+CODE);
		}

	

}
