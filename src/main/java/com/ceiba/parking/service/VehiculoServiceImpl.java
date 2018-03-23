package com.ceiba.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parking.dao.VehiculoDao;
import com.ceiba.parking.model.Vehiculo;


@Service("vehiculoService")
@Transactional
public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	private VehiculoDao _vehiculoDao;
	@Override
	public void guardarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		_vehiculoDao.guardarVehiculo(vehiculo);
	}

	@Override
	public List<Vehiculo> obtenerTodosLosVehiculos() {
		// TODO Auto-generated method stub
		return _vehiculoDao.obtenerTodosLosVehiculos();
	
	}

	@Override
	public void eliminarVehiculoPorId(Long idVehiculo) {
		// TODO Auto-generated method stub
		_vehiculoDao.eliminarVehiculoPorId(idVehiculo);
	}

	@Override
	public void eliminarVehiculoPorPlaca(String placa) {
		// TODO Auto-generated method stub
		_vehiculoDao.eliminarVehiculoPorPlaca(placa);
	}

	@Override
	public void actualizarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		_vehiculoDao.actualizarVehiculo(vehiculo);
	}

	@Override
	public Vehiculo obtenerVehiculoPorId(Long idVehiculo) {
		// TODO Auto-generated method stub
		return _vehiculoDao.obtenerVehiculoPorId(idVehiculo);
	}

	@Override
	public Vehiculo obtenerVehiculoPorCilindraje(String cilindraje) {
		// TODO Auto-generated method stub
		return _vehiculoDao.obtenerVehiculoPorCilindraje(cilindraje);
	}

	@Override
	public Vehiculo obtenerVehiculoPorPlaca(String placa) {
		// TODO Auto-generated method stub
		return _vehiculoDao.obtenerVehiculoPorPlaca(placa);
	}

	

	
	
}
