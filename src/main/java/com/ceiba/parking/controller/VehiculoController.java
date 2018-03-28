package com.ceiba.parking.controller;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.service.VehiculoService;
import com.ceiba.parking.util.CustomErrorType;

@Controller
@RequestMapping("/v1")
public class VehiculoController {

	//Get
	@Autowired
	 VehiculoService _vHiculoService;
	
	//GET
	
	//@RequestMapping(value="/vehiculos", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/vehiculos", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<List<Vehiculo>> obtenerVehiculos(){
		
		List<Vehiculo> vehiculos = new ArrayList<>();
		
		vehiculos = _vHiculoService.obtenerTodosLosVehiculos();
		if(vehiculos.isEmpty()) {
			return new ResponseEntity(new CustomErrorType("Ningun Dato retornado"),HttpStatus.CONFLICT);
		}
		
		return new  ResponseEntity<List<Vehiculo>>(vehiculos,HttpStatus.OK);
	
	}
	
	//GET
	
	@RequestMapping(value="/vehiculos/{placa}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Vehiculo> obtenerVehiculoPorPlaca(@PathVariable("placa") String placa){
		if(placa ==null) {
			return new ResponseEntity(new CustomErrorType("Placa es requeridad"),HttpStatus.CONFLICT);
		}
		Vehiculo vehiculo = _vHiculoService.obtenerVehiculoPorPlaca(placa);
		
		if(vehiculo==null) {
			return new ResponseEntity(new CustomErrorType("Ningun Dato retornado"),HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Vehiculo>(vehiculo, HttpStatus.OK);
	}
	
	//GET
	
	@RequestMapping(value="/cobros/{placa}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<BigDecimal> obtenerPrecio(@PathVariable("placa") String placa){
		if(placa ==null) {
			return new ResponseEntity(new CustomErrorType("Placa es requeridad"),HttpStatus.CONFLICT);
		}
		BigDecimal cobro = _vHiculoService.verCobroPorPlaca(placa);
		
		if(cobro==null) {
			return new ResponseEntity(new CustomErrorType("Ningun Dato retornado"),HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<BigDecimal>(cobro, HttpStatus.OK);
	}
	
	//
	//UPDATE
	@RequestMapping(value="/vehiculos/{placa}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	
	public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable("placa") String placa,@RequestBody Vehiculo vehiculo){
		Vehiculo currentVehiculo = _vHiculoService.obtenerVehiculoPorPlaca(placa);
		if(currentVehiculo==null) {
			return new ResponseEntity(new CustomErrorType("Ningun Dato retornado"),HttpStatus.CONFLICT);
		}
		currentVehiculo.setPlaca(vehiculo.getPlaca());
		currentVehiculo.setCilindraje(vehiculo.getCilindraje());
		currentVehiculo.setFechaEntrada(vehiculo.getFechaEntrada());
		_vHiculoService.actualizarVehiculo(currentVehiculo);
		return new ResponseEntity<Vehiculo>(currentVehiculo,HttpStatus.OK);
	}
	
	//DELETE
	
	@RequestMapping(value="/vehiculos/{placa}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	
	public ResponseEntity<Vehiculo> eliminarVehiculo(@PathVariable("placa") String placa){
		if(placa ==null) {
			return new ResponseEntity(new CustomErrorType("la placa es requeridad"),HttpStatus.CONFLICT);
		}
		
		Vehiculo vehiculo=  _vHiculoService.obtenerVehiculoPorPlaca(placa);
		
		if(vehiculo==null) {
			return new ResponseEntity(new CustomErrorType("No existe el Vehiculo"),HttpStatus.CONFLICT);
		}
		_vHiculoService.eliminarVehiculoPorPlaca(placa);
		
		return new ResponseEntity<Vehiculo>(HttpStatus.OK);
	}
	// insertar Vehiculo 
	//POST
	@RequestMapping(value="/vehiculos", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> guardarVehiculo(@RequestBody Vehiculo vehiculo, UriComponentsBuilder uriComponentsBuilder){
		
		if(vehiculo.getPlaca().equals(null)|| vehiculo.getPlaca().isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		vehiculo.setFechaEntrada(new Date());
		
		if (_vHiculoService.obtenerVehiculoPorPlaca(vehiculo.getPlaca())!=null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
	
		
		
		_vHiculoService.guardarVehiculo(vehiculo);
		
		HttpHeaders headers = new HttpHeaders();
		Vehiculo vehiculo2=	_vHiculoService.obtenerVehiculoPorPlaca(vehiculo.getPlaca());
		headers.setLocation(
				uriComponentsBuilder.path("/v1/vehiculos/{id}")
				.buildAndExpand(vehiculo2.getIdvehiculo())
				.toUri());
		
		return new ResponseEntity<String>(headers,HttpStatus.CREATED);
		
	}
	@RequestMapping("/prue")
	@ResponseBody
	public String index() {
		
		String response ="Bienvenido a el parqueadero Ceiba";
		
		return response;
	}
	
}
