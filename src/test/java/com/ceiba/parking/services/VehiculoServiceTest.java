package com.ceiba.parking.services;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parking.ParkingApplication;
import com.ceiba.parking.dao.VehiculoDao;
import com.ceiba.parking.model.TipoVehiculo;
import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.service.VehiculoService;
import com.ceiba.parking.service.VehiculoServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ParkingApplication.class)
public class VehiculoServiceTest {
	
	public static final LocalDateTime FECHA_SALIDA  =	LocalDateTime.of(2018, 4, 2, 11, 0);
		private Calendar fechaEntrada = Calendar.getInstance();
		private double horas;
		private Vehiculo vehiculo,vehiculoMoto;
		private TipoVehiculo tipoVehiculo,tipoVehiculoMoto;
		private BigDecimal precioDia;
		private BigDecimal precioHora;
		private  VehiculoDao vehiculoDao;
		private  VehiculoService vhiculoServiceImpl;
	 @Autowired
	 VehiculoService vehiculoservice;
	 
	
	 
	 
	
	
	


	 @Before
	 public void initialize() {
	    
			
		 precioDia = new BigDecimal(4000);
		 precioHora = new BigDecimal(1000);
		
			fechaEntrada.set(Calendar.YEAR, 2018);
			fechaEntrada.set(Calendar.DAY_OF_MONTH, 2);
			fechaEntrada.set(Calendar.MONTH, Calendar.APRIL);
			fechaEntrada.set(Calendar.AM_PM, Calendar.AM);		
			fechaEntrada.set(Calendar.HOUR, 10);
			fechaEntrada.set(Calendar.MINUTE, 00);
			fechaEntrada.set(Calendar.SECOND, 00);
			fechaEntrada.set(Calendar.MILLISECOND, 00);
			
			tipoVehiculo = new TipoVehiculo();
			tipoVehiculo.setIdTipo(2L);
			tipoVehiculo.setPrecioDia(precioDia);
			tipoVehiculo.setPrecioHora(precioHora);
			tipoVehiculo.setTipo("Carro");
			
			tipoVehiculoMoto = new TipoVehiculo();
			tipoVehiculoMoto.setIdTipo(1L);
			tipoVehiculoMoto.setPrecioDia(precioDia);
			tipoVehiculoMoto.setPrecioHora(precioHora);
			tipoVehiculoMoto.setTipo("Moto");
			
			vehiculo = new Vehiculo();
			vehiculo.setPlaca("123");
			vehiculo.setCilindraje(250);
			vehiculo.setFechaEntrada(fechaEntrada.getTime());
			vehiculo.setEstado(1);
			vehiculo.setTipoVehiculo(tipoVehiculo);
			
			vehiculoMoto = new Vehiculo();
			vehiculoMoto.setPlaca("123");
			vehiculoMoto.setCilindraje(250);
			vehiculoMoto.setFechaEntrada(fechaEntrada.getTime());
			vehiculoMoto.setEstado(1);
			vehiculoMoto.setTipoVehiculo(tipoVehiculoMoto);
			vehiculoDao = mock(VehiculoDao.class);
			
			
		    when(vehiculoDao.obtenerVehiculoPorPlaca("1234")).thenReturn(vehiculo);
			vhiculoServiceImpl = new VehiculoServiceImpl(vehiculoDao);
			
	    }
	
	
	@Test
    public void calcularHoraVehiculoParqueadoTest() throws Exception {
     
		
		
		
	   horas = vehiculoservice.calcularHoraVehiculoParqueado(fechaEntrada.getTime(), FECHA_SALIDA);
		
		assertEquals(1,horas,0);
	
    }
	
	
	@Test
	public void calcularPrecioSegunTiempoTest() {
		
		 horas = vehiculoservice.calcularHoraVehiculoParqueado(fechaEntrada.getTime(), FECHA_SALIDA);

		 BigDecimal  precio = vehiculoservice.calcularPrecioSegunTiempo(horas, vehiculo);
		BigDecimal valorEsperado = new BigDecimal(1000);
		 assertEquals(valorEsperado, precio);
		 
		
		
	}
	
	@Test
	public void verificarPlacaQueIniciaConATest() {
		
		
		boolean verificar = vehiculoservice.verificarPlacaQueIniciaConA("ABC");
		
		assertEquals(true, verificar);
	}
	
	@Test
	public void verificarSiEsDomingoOLunesTest() {
		
		boolean verificarDia = vehiculoservice.verificarSiEsDomingoOLunes(fechaEntrada.getTime());
		
		assertEquals(true, verificarDia);
	}


	@Test
	public void obtenerCantidadDeVehiculosCarrosTest(){
	
		VehiculoServiceImpl vehiculoServiceImpl = mock(VehiculoServiceImpl.class);
		when(vehiculoServiceImpl.obtenerCantidadDeVehiculosCarros()).thenReturn(1);
		int cantidad = vehiculoServiceImpl.obtenerCantidadDeVehiculosCarros();

		assertEquals(1,cantidad);
		}
	
	@Test
	public void obtenerCantidadDeVehiculosMotosTest(){
	
		VehiculoServiceImpl vehiculoServiceImpl = mock(VehiculoServiceImpl.class);
		when(vehiculoServiceImpl.obtenerCantidadDeVehiculosMotos()).thenReturn(1);
		int cantidad = vehiculoServiceImpl.obtenerCantidadDeVehiculosMotos();

		assertEquals(1,cantidad);
	}
	@Transactional
	@Test
	public void actualizarVehiculoAParqueadoTest() {
		
		
	}
	
	@Test
	public void obtenerVehiculoPorPlacaTest() {
		
	
		

		Vehiculo v= vhiculoServiceImpl.obtenerVehiculoPorPlaca("1234");
		
	//	Boolean parqueado = vhiculoServiceImpl.verificarExistenciaVehiculoParqueado("123");
		
	//	System.out.println(parqueado);
		
		assertEquals(v, vehiculo);
//		assertTrue(parqueado);
		
	}
	
	@Test
	public void verificarCupoCarrosSiHay() {
		
		 when(vehiculoDao.obtenerCantidadDeVehiculosCarros()).thenReturn(1);
		 
		 
		 
	boolean cupos=	 vhiculoServiceImpl.verificarCupo(vehiculo);
	
	assertTrue(cupos);
		 
		
		
	}

	
	@Test
	public void verificarCupoCarrosNoHay() {
		
		 when(vehiculoDao.obtenerCantidadDeVehiculosCarros()).thenReturn(5);
		 
		 
		 
	boolean cupos=	 vhiculoServiceImpl.verificarCupo(vehiculo);
	
	assertFalse(cupos);
		 
		
		
	}
	
	@Test
	public void verificarCupoMotosSiHay() {
		
		 when(vehiculoDao.obtenerCantidadDeVehiculosMotos()).thenReturn(1);
		 
		 
		 
	boolean cupos=	 vhiculoServiceImpl.verificarCupo(vehiculoMoto);
	
	assertTrue(cupos);
		 
		
		
	}
	@Test
	public void verificarCupoMotosNoHay() {
		
		 when(vehiculoDao.obtenerCantidadDeVehiculosMotos()).thenReturn(5);
		 
		 
		 
	boolean cupos=	 vhiculoServiceImpl.verificarCupo(vehiculoMoto);
	
	assertFalse(cupos);
		 
		
		
	}
	
	
	
	
	
	

}
