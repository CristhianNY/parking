package com.ceiba.parking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ceiba.parking.model.Cupo;
import com.ceiba.parking.service.CupoService;
import com.ceiba.parking.util.CustomErrorType;

@Controller
@RequestMapping("/v1")
public class CupoController {

	@Autowired	
	CupoService _cupoService;
	//GET
	@RequestMapping(value="/cupos", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<List<Cupo>> obtenerCupos(){
		
		List<Cupo> cupos = new ArrayList<>();
		
		cupos = _cupoService.obtenerTodosLosCupos();
		if(cupos.isEmpty()) {
			return new ResponseEntity(new CustomErrorType("Ningun Dato Retornado"),HttpStatus.CONFLICT);
		}
		return new  ResponseEntity<List<Cupo>>(cupos,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/cupos/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Cupo> obtenerCupoPorId(@PathVariable("id") Long id){
		
		if(id ==null) {
			return new ResponseEntity(new CustomErrorType("id requerido"),HttpStatus.CONFLICT);
		}
		Cupo cupo = _cupoService.obtenerCupoPorId(id);
		
		if(cupo == null) {
			return new ResponseEntity(new CustomErrorType("Ningun Dato retornado"),HttpStatus.CONFLICT);
		}
		
		
		return new ResponseEntity<Cupo>(cupo, HttpStatus.OK);
		
		
	}
}
