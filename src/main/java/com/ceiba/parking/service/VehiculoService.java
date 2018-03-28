package com.ceiba.parking.service;

import java.math.BigDecimal;
import java.util.List;

import com.ceiba.parking.model.Vehiculo;



public interface VehiculoService {

void guardarVehiculo(Vehiculo vehiculo);
	
	List<Vehiculo> obtenerTodosLosVehiculos();
	
	void eliminarVehiculoPorId(Long idVehiculo);
	void eliminarVehiculoPorPlaca(String placa);
	
	void actualizarVehiculo(Vehiculo vehiculo);
	
    Vehiculo obtenerVehiculoPorId(Long idVehiculo);
    
    Vehiculo obtenerVehiculoPorCilindraje(String  cilindraje );
    Vehiculo obtenerVehiculoPorPlaca(String placa);
    int obtenerCantidadDeVehiculos();
    BigDecimal verCobroPorPlaca(String placa);
}
