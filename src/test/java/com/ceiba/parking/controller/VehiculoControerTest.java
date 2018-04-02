package com.ceiba.parking.controller;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.aspectj.weaver.ast.And;

import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VehiculoControerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private VehiculoController vehiculoController;
	
	
	@Before
	public void setUp()throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(vehiculoController).build();
		
	}
	@Test
	public void testHelloWord() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/prue"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Welcom to parking ceiba"));
	}
	
	@Test	
	public void testBuscarVehiculoPorPlaca() throws Exception{
		
	/**	mockMvc.perform(MockMvcRequestBuilders.get("/v1/vehiculos/EEE").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.idvehiculo", Matchers.is(3L)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.placa", Matchers.is("EEE")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.cilindraje", Matchers.is(432)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.fechaEntrada", Matchers.is("2018-04-01")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.estado", Matchers.is(1)));**/
		   
	    
	    ResultMatcher ok = MockMvcResultMatchers.status()
                .isOk();
	    ResultMatcher idVehiculo = MockMvcResultMatchers.jsonPath("$.idvehiculo", Matchers.is(3));
	    ResultMatcher placa = MockMvcResultMatchers.jsonPath("$.placa", Matchers.is("EEE"));
	    ResultMatcher cilindraje = MockMvcResultMatchers.jsonPath("$.cilindraje", Matchers.is(432));
	    ResultMatcher fechaEntrada = MockMvcResultMatchers.jsonPath("$.fechaEntrada", Matchers.is("2018-04-01"));
	    ResultMatcher estado = MockMvcResultMatchers.jsonPath("$.estado", Matchers.is(1));

	    	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/v1/vehiculos/eee");
	    	this.mockMvc.perform(builder)
	    	.andExpect(ok)
	    	.andExpect(idVehiculo)
	    	.andExpect(placa)
	    	.andExpect(cilindraje)
	    	.andExpect(fechaEntrada)
	    	.andExpect(estado);
		
		
	}
	
	
	


}
