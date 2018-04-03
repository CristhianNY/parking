package com.ceiba.parking.dao;

import java.util.List;
import com.ceiba.parking.model.Vehiculo;

public interface VehiculoDao {

	void guardarVehiculo(Vehiculo vehiculo);
	
	List<Vehiculo> obtenerTodosLosVehiculos();
	
	void eliminarVehiculoPorId(Long idVehiculo);
	void eliminarVehiculoPorPlaca(String placa);
	
	
	void actualizarVehiculo(Vehiculo vehiculo);
	
    Vehiculo obtenerVehiculoPorId(Long idVehiculo);
    
    Vehiculo obtenerVehiculoPorCilindraje(String  cilindraje );
    Vehiculo obtenerVehiculoPorPlaca(String  placa );
    Vehiculo obtenerVehiculoPorPlacaParqueado(String  placa );
    int obtenerCantidadDeVehiculosCarros();
    int obtenerCantidadDeVehiculosMotos();
    int obtenerCantidadDeVehiculos();
	
}
