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
	
		
		getSession().persist(vehiculo);
	}

	@Override
	public List<Vehiculo> obtenerTodosLosVehiculos() {
	
		

		
		return 	getSession().createQuery("from Vehiculo where estado=1").list();
		
	}

	
	


	@Override
	public void retirarVehiculo(Vehiculo vehiculo) {
	
		getSession().createQuery("update Vehiculo c set c.estado = 2 where c.estado = :estado and c.placa = :placa")
		.setParameter("estado", vehiculo.getEstado())
		.setParameter("placa", vehiculo.getPlaca()).executeUpdate();
		
	}

	@Override
	public void actualizarVehiculoAParqueado(Vehiculo vehiculo) {
	
		getSession().update(vehiculo);
		
	}


	@Override
	public Vehiculo obtenerVehiculoPorCilindraje(String cilindraje) {
	
		return (Vehiculo)getSession().createQuery("from Vehiculo where cilindraje = :cilindraje")
				.setParameter("placa", cilindraje).uniqueResult();
	}
	@Override
	public Vehiculo obtenerVehiculoPorPlaca(String placa) {
		return (Vehiculo)getSession().createQuery("from Vehiculo where placa = :placa ")
				.setParameter("placa", placa).uniqueResult();
	}
	@Override
	public Vehiculo obtenerVehiculoPorPlacaParqueado(String placa) {
		
		return (Vehiculo)getSession().createQuery("from Vehiculo where placa = :placa and estado = 1")
				.setParameter("placa", placa).uniqueResult();
	}

	@Override
	public Vehiculo obtenerVehiculoGuardadoSinParquear(String placa) {
		
		return (Vehiculo)getSession().createQuery("from Vehiculo where placa = :placa and estado = 2")
				.setParameter("placa", placa).uniqueResult();
	}

	@Override
	public int obtenerCantidadDeVehiculos() {
		
		return  (int) getSession().createQuery("select count(*) from Vehiculo where estado = 1").uniqueResult();
	}

	@Override
	public int obtenerCantidadDeVehiculosCarros() {
		
		int resultado =  ((Number) getSession().createQuery("select count(*) from Vehiculo ve where ve.estado = 1 and ve.tipoVehiculo.idTipo = 2").uniqueResult()).intValue();
		return resultado;
	}

	@Override
	public int obtenerCantidadDeVehiculosMotos() {
		
		int resultado =  ((Number) getSession().createQuery("select count(*) from Vehiculo ve where ve.estado = 1 and ve.tipoVehiculo.idTipo = 1").uniqueResult()).intValue();
		
		return resultado;
	}



	

	

}
