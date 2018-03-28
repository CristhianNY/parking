package com.ceiba.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parking.dao.CupoDao;
import com.ceiba.parking.model.Cupo;

@Service("cupoService")
@Transactional
public class CupoServiceImpl implements CupoService {

	@Autowired
	private CupoDao _cupoDao;

	@Override
	public Cupo obtenerCupoPorId(Long id) {
		// TODO Auto-generated method stub
		return _cupoDao.obtenerCupoPorId(id);
	}

	@Override
	public void actualizarCantidadCupo(int cantidad) {
		// TODO Auto-generated method stub
		_cupoDao.actualizarCantidadCupo(cantidad);
	}

	@Override
	public List<Cupo> obtenerTodosLosCupos() {
		// TODO Auto-generated method stub
		return _cupoDao.obtenerTodosLosCupos();
	}
	

}
