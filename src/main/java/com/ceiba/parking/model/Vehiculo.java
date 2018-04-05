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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;


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
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "America/Bogota")
	@Column(name="fecha_entrada")
	private Date fechaEntrada;

	@Column(name="estado")
	private int estado;
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idTipoVehiculo")	
	private TipoVehiculo tipoVehiculo;
	
	

	
	public Vehiculo( String placa, int cilindraje, Date fechaEntrada,TipoVehiculo tipoVehiculo) {
		super();
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
		this.fechaEntrada = fechaEntrada;
	
	
	}
	public Vehiculo() {
		super();
		
	}

	public Long getIdvehiculo() {
		return idvehiculo;
	}
	public void setIdVehiculo(Long idvehiculo) {
		this.idvehiculo = idvehiculo;
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

	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}
	
	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	

	

	
	
	
	
}
