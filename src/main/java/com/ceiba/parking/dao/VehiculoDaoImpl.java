package com.ceiba.parking.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ceiba.parking.model.Vehiculo;

@Repository
@Transactional
public class VehiculoDaoImpl extends AbstractSession  implements VehiculoDao {


	@Override
	public void guardarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
	//	parqueaderoSession.getSession().persist(vehiculo);
	//	parqueaderoSession.getSession().getTransaction().commit();
		
		getSession().persist(vehiculo);
	}

	@Override
	public List<Vehiculo> obtenerTodosLosVehiculos() {
	
		
	//	return parqueaderoSession.getSession().createQuery("from Vehiculo").getResultList();
		
		return 	getSession().createQuery("from Vehiculo").list();
		
	}

	@Override
	public void eliminarVehiculoPorId(Long idVehiculo) {
	
		Vehiculo vehiculo = obtenerVehiculoPorId(idVehiculo);
		if(vehiculo!=null) {
			getSession().delete(vehiculo);
		}
	}
	
	@Override
	public void eliminarVehiculoPorPlaca(String placa) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo = obtenerVehiculoPorPlaca(placa);
		if(vehiculo!=null) {
			getSession().delete(vehiculo);
		}
	}

	@Override
	public void actualizarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		getSession().update(vehiculo);
	}

	@Override
	public Vehiculo obtenerVehiculoPorId(Long idVehiculo) {
		// TODO Auto-generated method stub
		
		return (Vehiculo)getSession().get(Vehiculo.class, idVehiculo);
	}

	@Override
	public Vehiculo obtenerVehiculoPorCilindraje(String cilindraje) {
		// TODO Auto-generated method stub
		return (Vehiculo)getSession().createQuery("from Vehiculo where cilindraje = :cilindraje")
				.setParameter("placa", cilindraje).uniqueResult();
	}
	@Override
	public Vehiculo obtenerVehiculoPorPlaca(String placa) {
		return (Vehiculo)getSession().createQuery("from Vehiculo where placa = :placa and estado = 1")
				.setParameter("placa", placa).uniqueResult();
	}
	@Override
	public Vehiculo obtenerVehiculoPorPlacaParqueado(String placa) {
		
		return (Vehiculo)getSession().createQuery("from Vehiculo where placa = :placa and estado = 1")
				.setParameter("placa", placa).uniqueResult();
	}

	@Override
	public int obtenerCantidadDeVehiculos() {
		// TODO Auto-generated method stub
		return  (int) getSession().createQuery("select count(*) from Vehiculo where estado = 1").uniqueResult();
	}

	@Override
	public int obtenerCantidadDeVehiculosCarros() {
		// TODO Auto-generated method stub
		int resultado =  ((Number) getSession().createQuery("select count(*) from Vehiculo ve where ve.estado = 1 and ve.tipoVehiculo.idTipo = 2").uniqueResult()).intValue();
		return resultado;
	}

	@Override
	public int obtenerCantidadDeVehiculosMotos() {
		// TODO Auto-generated method stub
		int resultado =  ((Number) getSession().createQuery("select count(*) from Vehiculo ve where ve.estado = 1 and ve.tipoVehiculo.idTipo = 1").uniqueResult()).intValue();
		
		return resultado;
	}

	

	

}
