package com.ceiba.parking.integracion;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.ceiba.parking.model.TipoVehiculo;
import com.ceiba.parking.model.Vehiculo;
import com.ceiba.parking.service.VehiculoService;
@RunWith(SpringRunner.class)
@SpringBootTest(classes=ParkingApplication.class)
public class VehiculoIntegrationTest {

	 /**	public static final LocalDateTime FECHA_SALIDA  =	LocalDateTime.of(2018, 4, 2, 11, 0);
		private Calendar fechaEntrada = Calendar.getInstance();
		private double horas;
		private Vehiculo vehiculo;
		private TipoVehiculo tipoVehiculo;
		private BigDecimal precioDia;
		private BigDecimal precioHora;
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
			
			vehiculo = new Vehiculo();
			vehiculo.setPlaca("123");
			vehiculo.setCilindraje(250);
			vehiculo.setFechaEntrada(fechaEntrada.getTime());
			vehiculo.setEstado(1);
			vehiculo.setTipoVehiculo(tipoVehiculo);
			
			
	    }
	 
	 	@Transactional
	 	@Test
		public void guardarVehiculoTest() {
			
	      Vehiculo v=    vehiculoservice.guardarVehiculo(vehiculo);  
	      
	      assertThat(v).hasFieldOrPropertyWithValue("placa", "123");
	      assertThat(v).hasFieldOrPropertyWithValue("cilindraje", 250);
	      assertThat(v).hasFieldOrPropertyWithValue("placa", "123");
	      assertThat(v).hasFieldOrPropertyWithValue("fechaEntrada", fechaEntrada.getTime());
	      assertThat(v).hasFieldOrPropertyWithValue("estado", 1);     
	      
		 	
		}**/
	@Test
	public void guardarVehiculoTest() {}
}
