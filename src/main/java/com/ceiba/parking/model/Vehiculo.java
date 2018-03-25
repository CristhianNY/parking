package com.ceiba.parking.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="vehiculo")
public class Vehiculo implements Serializable{
	@Id
	@Column(name="idvehiculo",columnDefinition = "INT(11)", updatable = false, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idvehiculo;	
	@Column(name="placa")
	private String placa;
	@Column(name="cilindraje")
	private int cilindraje;	
	
	@Column(name="fecha_entrada")
	private String fechaEntrada;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idTipoVehiculo")
	private TipoVehiculo tipoVehiculo;
	//@ManyToOne(optional= true)
	//@ManyToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="parqueadero_idparqueadero")
	

	
	public Vehiculo( String placa, int cilindraje, String fechaEntrada,TipoVehiculo tipoVehiculo) {
		super();
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
		this.fechaEntrada = fechaEntrada;
	
	
	}
	public Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdvehiculo() {
		return idvehiculo;
	}

	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
	
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	@JsonIgnore
	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}
	@JsonProperty
	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	

	

	
	
	
	
}
