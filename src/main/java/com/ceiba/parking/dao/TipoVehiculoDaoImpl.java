package com.ceiba.parking.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ceiba.parking.model.TipoVehiculo;

@Repository
@Transactional
public class TipoVehiculoDaoImpl extends AbstractSession implements TipoVehiculoDao {

	@Override
	public TipoVehiculo findByTipoVehiculoIdTipo(Long id) {
		return (TipoVehiculo) getSession().get(TipoVehiculo.class, id);
	}

}
