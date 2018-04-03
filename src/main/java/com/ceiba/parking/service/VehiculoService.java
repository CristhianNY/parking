package com.ceiba.parking.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ceiba.parking.model.Vehiculo;



public interface VehiculoService {

Vehiculo guardarVehiculo(Vehiculo vehiculo);
	
	List<Vehiculo> obtenerTodosLosVehiculos();
	
	void eliminarVehiculoPorId(Long idVehiculo);
	void eliminarVehiculoPorPlaca(String placa);
	
	void actualizarVehiculo(Vehiculo vehiculo);
    Vehiculo obtenerVehiculoPorId(Long idVehiculo);
    Vehiculo obtenerVehiculoPorCilindraje(String  cilindraje );
    Vehiculo obtenerVehiculoPorPlaca(String placa);
    int obtenerCantidadDeVehiculosMotos();
    int obtenerCantidadDeVehiculosCarros();
    HashMap<String, BigDecimal> verCobroPorPlaca(String placa);
	double calcularHoraVehiculoParqueado(Date fechaEntrada, LocalDateTime fechaSalida);
	BigDecimal calcularPrecioSegunTiempo(double horas,Vehiculo vehiculo);
	boolean verificarPlacaQueIniciaConA(String placa);
	boolean verificarSiEsDomingoOLunes(Date fechaEntrada);
	Vehiculo obtenerVehiculoPorPlacaParqueado(String  placa );
	
}
