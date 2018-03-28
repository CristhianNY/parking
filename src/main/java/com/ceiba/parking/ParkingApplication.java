package com.ceiba.parking;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
		
		//System.out.println(new Data().toString()+"mifecha");
		/**double valor = 30.9;
		double x = valor - Math.floor(valor);
				if(valor % 1 != 0) {
					System.out.println("SI");
					System.out.println(x);
					int horas = (int)valor+1;
					System.out.println((int)valor);
					System.out.println(horas);
					
				}
				int test = 29%24;
				System.out.println(test);
				System.out.println("---------");
				
				float days = (float)31/24;

				BigDecimal precioDeDia = new BigDecimal(8000);
				BigDecimal precioXHora = new BigDecimal(1000);
							
					int dias = (int)days;
					int h = 31%24;
				
					BigDecimal precioDia = precioDeDia.multiply(BigDecimal.valueOf(dias).movePointLeft(3));
					BigDecimal precioHora = precioXHora.multiply(BigDecimal.valueOf(h).movePointLeft(3));
					
					System.out.println("por aca entra");
					System.out.println(precioDia.add(precioHora));
				
				
	//	System.out.println("valor devuelto "+TimeUnit.DAYS.convert(valor, TimeUnit.HOURS));
	 * **/
	 
	}
	
	

}




