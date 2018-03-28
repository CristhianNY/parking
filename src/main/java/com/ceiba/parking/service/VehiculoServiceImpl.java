package com.ceiba.parking.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parking.dao.VehiculoDao;
import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.util.Constans;


@Service("vehiculoService")
@Transactional
public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	private VehiculoDao _vehiculoDao;
	
	private BigDecimal precioDeDia;
	private BigDecimal precioXHora;
	
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

	
	@Override
	public int obtenerCantidadDeVehiculos() {
		// TODO Auto-generated method stub
		return _vehiculoDao.obtenerCantidadDeVehiculos();
	}

	@Override
	public BigDecimal verCobroPorPlaca(String placa) {
		
		
		Vehiculo vehiculo = _vehiculoDao.obtenerVehiculoPorPlaca(placa);
		
	 		
		
		if(vehiculo.getCilindraje()>= Constans.Cilindraje) {
			BigDecimal precioMoto = calcularPrecioSegunTiempo(calcularHoraVehiculoParqueado(vehiculo.getFechaEntrada()),vehiculo);
		
		int precio  = 	precioMoto.intValue();
		
		int	result = precio+2000;
	
		    BigDecimal precioMoto500 = new BigDecimal(result);
			return precioMoto500;
		}
		double horas= calcularHoraVehiculoParqueado(vehiculo.getFechaEntrada());
		 
		 float dia = (float) (horas/24);
		 float h2 = dia%1;
		 
		 if(h2>= 0.375) {
			 dia = (int)dia+1;
			
			 return calcularPrecioPorDia(dia,vehiculo);
		 }
		
		return calcularPrecioSegunTiempo(calcularHoraVehiculoParqueado(vehiculo.getFechaEntrada()),vehiculo);
	}
	
	private BigDecimal calcularPrecioPorDia(float dia, Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		precioDeDia = vehiculo.getTipoVehiculo().getPrecioDia().multiply(BigDecimal.valueOf(dia));
		
		return precioDeDia;
	}

	//x = a valor de los decimales 
	//h = horas

	BigDecimal calcularPrecioSegunTiempo(double horas,Vehiculo vehiculo) {
		
		if((horas>=9)&&(horas<=24)) {
			
			return  vehiculo.getTipoVehiculo().getPrecioDia();
			
		}
		
		float days = (float) horas/24;
		int dias = (int)days;
		int h = (int)horas%Constans.valorDia;
		
		if(h == Constans.valorMedioDia) {
			
			return vehiculo.getTipoVehiculo().getPrecioDia().multiply(BigDecimal.valueOf(dias));
			
		}
		
	     precioDeDia = vehiculo.getTipoVehiculo().getPrecioDia().multiply(BigDecimal.valueOf(dias));
		 precioXHora = vehiculo.getTipoVehiculo().getPrecioHora().multiply(BigDecimal.valueOf(h));
			
		return precioDeDia.add(precioXHora);
	}
	
	
	
	   double calcularHoraVehiculoParqueado(Date fecha){
		   
	LocalDateTime fechEntrada = convertToLocalDateTimeViaInstant(fecha);
	
	LocalDateTime horaActual = LocalDateTime.now();
		Duration duration = Duration.between(fechEntrada, horaActual);
		
	long minutos = 	duration.getSeconds()/60;	
	
		float h= (float) minutos/60;
		   if(h % 1 != 0) {
				
				int horas = ((int)h+1);
				
				
				return horas;
			}
		return h;
	}
	   
	   public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		    return dateToConvert.toInstant()
		      .atZone(ZoneId.systemDefault())
		      .toLocalDateTime();
		}
}
