package com.ceiba.parking.dao;

import java.util.List;
import com.ceiba.parking.model.Vehiculo;

public interface VehiculoDao {

	void guardarVehiculo(Vehiculo vehiculo);
	
	List<Vehiculo> obtenerTodosLosVehiculos();
	
	
	
	
	
	void retirarVehiculo(Vehiculo vehiculo);
	void actualizarVehiculoAParqueado(Vehiculo vehiculo);
	
  
    
    Vehiculo obtenerVehiculoPorCilindraje(String  cilindraje );
    Vehiculo obtenerVehiculoPorPlaca(String  placa );
    Vehiculo obtenerVehiculoGuardadoSinParquear(String placa);
    Vehiculo obtenerVehiculoPorPlacaParqueado(String  placa );
    int obtenerCantidadDeVehiculosCarros();
    int obtenerCantidadDeVehiculosMotos();
    int obtenerCantidadDeVehiculos();
	
}
