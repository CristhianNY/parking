package com.ceiba.parking.dao;

import java.util.List;

import com.ceiba.parking.model.Cupo;

public interface CupoDao {

	List<Cupo> obtenerTodosLosCupos();
	Cupo obtenerCupoPorId(Long id);
	
	void actualizarCantidadCupo(int cantidad);
}
