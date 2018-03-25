package com.ceiba.parking.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.parking.dao.TipoVehiculoDao;
import com.ceiba.parking.model.TipoVehiculo;

public class TipoVehiculoServiceImpl implements TipoVehiculoService {
	
	@Autowired
	private TipoVehiculoDao _tipoVehiculo;

	@Override
	public TipoVehiculo obtenerTipoVehiculoPorId(Long id) {
		return _tipoVehiculo.findByTipoVehiculoIdTipo(id);
	}

}
