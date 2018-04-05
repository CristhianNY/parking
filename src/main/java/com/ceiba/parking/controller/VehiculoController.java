package com.ceiba.parking.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
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
		
		if(vehiculos == null) {
			return new ResponseEntity(new CustomErrorType("Ya Esta parqueado"),HttpStatus.CONFLICT);
		}
		
		return new  ResponseEntity<List<Vehiculo>>(vehiculos,HttpStatus.OK);
	
	}
	
	//GET
	
	public VehiculoController() {
		
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value="/vehiculos/{placa}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Vehiculo> obtenerVehiculoPorPlaca(@PathVariable("placa") String placa){
		if(placa ==null) {
			return respondedorErrores("La Placa es requeridad");
		}
		Vehiculo vehiculo = _vHiculoService.obtenerVehiculoPorPlaca(placa);
		
		
		if(vehiculo==null) {
			return respondedorErrores("Ningun dato retornado");
		}
		
		return new ResponseEntity<Vehiculo>(vehiculo, HttpStatus.OK);
	}


	//GET
	
	@RequestMapping(value="/cobros/{placa}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<HashMap<String,BigDecimal>> obtenerPrecio(@PathVariable("placa") String placa){
		if(placa ==null) {
			return new ResponseEntity(new CustomErrorType("Placa es requeridad"),HttpStatus.CONFLICT);
		}
		HashMap<String, BigDecimal> cobro = _vHiculoService.verCobroPorPlaca(placa);
		
		if(cobro==null) {
			return new ResponseEntity(new CustomErrorType("Ningun Dato retornado"),HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<HashMap<String,BigDecimal>>(cobro, HttpStatus.OK);
	}
	
	//
	//UPDATE
	@RequestMapping(value="/vehiculos/{placa}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	
	public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable("placa") String placa,@RequestBody Vehiculo vehiculo){
		Vehiculo currentVehiculo = _vHiculoService.obtenerVehiculoPorPlacaParqueado(placa);
		if(currentVehiculo==null) {
			return respondedorErrores("Ningun dato retornado");
		}
		
		currentVehiculo.setEstado(1);
		_vHiculoService.retirarVehiculo(currentVehiculo);
		return new ResponseEntity<Vehiculo>(currentVehiculo,HttpStatus.OK);
	}
	
	//DELETE
	
	@RequestMapping(value="/vehiculos/{placa}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	
	public ResponseEntity<Vehiculo> eliminarVehiculo(@PathVariable("placa") String placa){
		if(placa ==null) {
			return respondedorErrores("Placa Requeridad");
		}
		
		Vehiculo vehiculo=  _vHiculoService.obtenerVehiculoPorPlaca(placa);
		if(vehiculo==null) {
			return respondedorErrores("Vehiculo no existe");
		}
		
		_vHiculoService.eliminarVehiculoPorPlaca(placa);
		
		return new ResponseEntity<Vehiculo>(HttpStatus.OK);
	}
	// insertar Vehiculo 
	//POST
	@RequestMapping(value="/vehiculos", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> guardarVehiculo(@RequestBody Vehiculo vehiculo, UriComponentsBuilder uriComponentsBuilder){
		
		vehiculo.setFechaEntrada(new Date());
		vehiculo.setEstado(1);
		Vehiculo currentVehiculo = _vHiculoService.obtenerVehiculoGuardadoSinParquear(vehiculo.getPlaca());

		if(currentVehiculo != null) {
			vehiculo.setIdVehiculo(currentVehiculo.getIdvehiculo());
			currentVehiculo.setEstado(2);
			
			_vHiculoService.actualizarVehiculoAParqueado(vehiculo);			
		}else {
			if(_vHiculoService.guardarVehiculo(vehiculo)== null) {
				return respondedorErrores("No se puede ingresar el vehiculo");
			}
		}
	
			
	
		
		
		
		
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
		
		String response ="Welcom to parking ceiba";
		
		return response;
	}
	
	private ResponseEntity<Vehiculo> respondedorErrores(String mensaje) {
		return new ResponseEntity(new CustomErrorType(mensaje),HttpStatus.CONFLICT);
	}
	
	
}
