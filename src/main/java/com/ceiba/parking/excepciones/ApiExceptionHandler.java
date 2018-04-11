package com.ceiba.parking.excepciones;

import org.apache.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.ceiba.parking.excepciones.NotFoundVehiculoPlacaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({NotFoundVehiculoPlacaException.class})
	@ResponseBody
	public ErrorMessage notFoundRequest(Exception exception) {
		
		ErrorMessage errorMessage = new ErrorMessage(exception);
		LogManager.getLogger(this.getClass()).info("ERROR: NOT_FOUND,"+ errorMessage);
		return errorMessage;
	}
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({NotFoundVehiculoException.class})
	@ResponseBody
	public ErrorMessage notFoundVehiculosRequest(Exception exception) {
		
		ErrorMessage errorMessage = new ErrorMessage(exception);
		LogManager.getLogger(this.getClass()).info("ERROR: NOT_FOUND,"+ errorMessage);
		return errorMessage;
	}
	
	

}
