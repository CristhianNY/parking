package com.ceiba.parking.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ceiba.parking.model.Cupo;
@Repository
@Transactional
public class CupoDaoImpl extends AbstractSession implements CupoDao {

	@Override
	public List<Cupo> obtenerTodosLosCupos() {
		// TODO Auto-generated method stub
		return 	getSession().createQuery("from Cupo").list();
	}

	@Override
	public Cupo obtenerCupoPorId(Long id) {
		// TODO Auto-generated method stub
		return (Cupo)getSession().get(Cupo.class, id);
	}

	@Override
	public void actualizarCantidadCupo(int cantidad) {
		// TODO Auto-generated method stub
		
	}

}
