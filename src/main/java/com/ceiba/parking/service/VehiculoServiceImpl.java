package com.ceiba.parking.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parking.dao.VehiculoDao;
import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.util.Constans;


@Service
@Transactional
public class VehiculoServiceImpl implements VehiculoService {

	@Autowired
	private VehiculoDao _vehiculoDao;
	
	private BigDecimal precioDeDia;
	private BigDecimal precioXHora;
	
	@Override
	public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
		
		
		if(verificarCupo(vehiculo)) {	
		
		
		if(verificarExistenciaVehiculoParqueado(vehiculo.getPlaca())) {
			return null;
		}
		
			
		if(verificarPlacaQueIniciaConA(vehiculo.getPlaca())) {
			
			
			if(verificarSiEsDomingoOLunes(vehiculo.getFechaEntrada())) {
				
				_vehiculoDao.guardarVehiculo(vehiculo);
				return vehiculo;
				}else {
					return null;
				}
			
			
		} else {
			_vehiculoDao.guardarVehiculo(vehiculo);
			
			
			return vehiculo;
		}
		
	}
		return null;
	
	}

	@Override
	public List<Vehiculo> obtenerTodosLosVehiculos() {
		
		return _vehiculoDao.obtenerTodosLosVehiculos();
	
	}

	@Override
	public void eliminarVehiculoPorId(Long idVehiculo) {
	
		_vehiculoDao.eliminarVehiculoPorId(idVehiculo);
	}

	@Override
	public void eliminarVehiculoPorPlaca(String placa) {
	
		_vehiculoDao.eliminarVehiculoPorPlaca(placa);
	}

	@Override
	public void retirarVehiculo(Vehiculo vehiculo) {
	
		_vehiculoDao.retirarVehiculo(vehiculo);
	}
	@Override
	public void actualizarVehiculoAParqueado(Vehiculo vehiculo) {
	
		vehiculo.setEstado(1);
		vehiculo.setFechaEntrada(new Date());
		_vehiculoDao.actualizarVehiculoAParqueado(vehiculo);
	}
	@Override
	public Vehiculo obtenerVehiculoPorId(Long idVehiculo) {
		
		return _vehiculoDao.obtenerVehiculoPorId(idVehiculo);
	}

	@Override
	public Vehiculo obtenerVehiculoPorCilindraje(String cilindraje) {
		
		return _vehiculoDao.obtenerVehiculoPorCilindraje(cilindraje);
	}
	@Override
	public Vehiculo obtenerVehiculoPorPlacaParqueado(String placa) {
		
		return _vehiculoDao.obtenerVehiculoPorPlacaParqueado(placa);
	}

	@Override
	public Vehiculo obtenerVehiculoPorPlaca(String placa) {
		
		return _vehiculoDao.obtenerVehiculoPorPlaca(placa);
	}
	
	@Override
	public Vehiculo obtenerVehiculoGuardadoSinParquear(String placa) {
		
		return _vehiculoDao.obtenerVehiculoGuardadoSinParquear(placa);
	}


	
	@Override
	public int obtenerCantidadDeVehiculosMotos() {
		
		return _vehiculoDao.obtenerCantidadDeVehiculosMotos();
	}
	@Override
	public int obtenerCantidadDeVehiculosCarros() {
	
		return _vehiculoDao.obtenerCantidadDeVehiculosCarros();
	}


	@Override
	public HashMap<String, BigDecimal> verCobroPorPlaca(String placa) {
			
		Vehiculo vehiculo = _vehiculoDao.obtenerVehiculoPorPlacaParqueado(placa);
		
		if(vehiculo == null) {
			return null;
		}
			
		HashMap<String, BigDecimal> bd_map = new HashMap<>();
		
		if(vehiculo.getCilindraje()>= Constans.Cilindraje) {
			return calcularPrecioMotosDeMasDe500(vehiculo, bd_map);
		}
		double horas= calcularHoraVehiculoParqueado(vehiculo.getFechaEntrada(),LocalDateTime.now());
		 
		 float dia = (float) (horas/24);
		 float h2 = dia%1;
		 
		 if(h2>= 0.375) {
			 dia = (float)dia+1;
			 bd_map.put("precio", calcularPrecioPorDia(dia,vehiculo));
			 return bd_map;
		 }
		 bd_map.put("precio", calcularPrecioSegunTiempo(calcularHoraVehiculoParqueado(vehiculo.getFechaEntrada(),LocalDateTime.now()),vehiculo));
		
		 return bd_map;
	}

	
	//No se si probarlo o no
	private HashMap<String, BigDecimal> calcularPrecioMotosDeMasDe500(Vehiculo vehiculo,
			HashMap<String, BigDecimal> bd_map) {
		BigDecimal precioMoto = calcularPrecioSegunTiempo(calcularHoraVehiculoParqueado(vehiculo.getFechaEntrada(),LocalDateTime.now()),vehiculo);
		int precio  = 	precioMoto.intValue();
		int	result = precio+2000;

		BigDecimal precioMoto500 = new BigDecimal(result);
		bd_map.put("precio", precioMoto500);
		return bd_map;
	}
	
	private BigDecimal calcularPrecioPorDia(float dia, Vehiculo vehiculo) {
	
		precioDeDia = vehiculo.getTipoVehiculo().getPrecioDia().multiply(BigDecimal.valueOf(dia));
		
		return precioDeDia;
	}



	public BigDecimal calcularPrecioSegunTiempo(double horas,Vehiculo vehiculo) {
		
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
	
	
	
	  public double calcularHoraVehiculoParqueado(Date fecha, LocalDateTime horaActual){
		   
	    LocalDateTime fechEntrada = convertToLocalDateTimeViaInstant(fecha);	
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
	   
	   
	   public boolean verificarPlacaQueIniciaConA(String placa) {
			  
		 

			  
		   String firstLetter = String.valueOf(placa.charAt(0));
		    
		    if(firstLetter.equals("A")||(firstLetter.equals("a"))) {
		    	 return true;
		    }
			  return false;
		  }
	   
	   
	   public boolean verificarSiEsDomingoOLunes(Date fechaEntrada) {
		   
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(fechaEntrada);
		   if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)||(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)) {
		      return true;
		    }
		   return false;
		   
	   }
	   
	   
	   public boolean verificarExistenciaVehiculoParqueado(String placa) {
		   
		   Vehiculo v =  obtenerVehiculoPorPlacaParqueado(placa);
		   
		   if(v != null) {
			   return true;
		   }
		   return false;
	   }
	   
	   
	   
	   public boolean verificarCupo(Vehiculo vehiculo) {
		   
		   Long tipo = vehiculo.getTipoVehiculo().getIdTipo();
		   int cuposMotos = obtenerCantidadDeVehiculosMotos();
		   int cuposCarros = obtenerCantidadDeVehiculosCarros();
		   
		   if(tipo.equals(1L) && (cuposMotos<Constans.CUPOS_MOTOS))  {
			   
			   return true;
			   
		   }
		   
		   if(tipo.equals(2L) && (cuposCarros<Constans.CUPOS_CARROS)){
			   
			   return true;
			   
		   }
		   
		   		   
		   
		   return false;
	   }





	   
	   
	   
	   
	   
}
