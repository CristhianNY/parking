package com.ceiba.parking.service;

import java.util.List;

import com.ceiba.parking.model.Cupo;

public interface CupoService {

	List<Cupo> obtenerTodosLosCupos();
	Cupo obtenerCupoPorId(Long id);
	void actualizarCantidadCupo(int cantidad);
}
