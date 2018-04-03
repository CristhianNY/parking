package com.ceiba.parking;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
		String example = "Ariel";
	    String firstLetter  = "";

	  
	    firstLetter = String.valueOf(example.charAt(0));
	    System.out.println("La primera letra "+firstLetter);

	 
	
	}
	
	

}




