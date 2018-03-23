package com.ceiba.parking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/hola")
	@ResponseBody
	public String index() {
		
		String response ="Bienvenido a el parqueadero Ceiba";
		
		return response;
	}
}
