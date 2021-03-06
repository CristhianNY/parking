package com.ceiba.parking.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipovehiculo")
public class TipoVehiculo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idtipo",

	    columnDefinition = "INT(11)"
	)
	private Long idTipo;
	
	@Column(name="tipo")
	private String tipo;
	@OneToMany(mappedBy="tipoVehiculo")		
	private Collection<Vehiculo> vehiculos = new ArrayList<>();
	@Column(name="precioHora")
	private BigDecimal precioHora;
	@Column(name="precioDia")
	private BigDecimal precioDia;
	public TipoVehiculo() {
		super();
	
	}
	
	public TipoVehiculo(Long idTipo) {
		this.idTipo = idTipo;
	} 	

	public TipoVehiculo(Long idTipo ,String tipo) {
		super();
		this.tipo = tipo;
		this.idTipo= idTipo;
	}

	public Long getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Collection<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Collection<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public BigDecimal getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(BigDecimal precioHora) {
		this.precioHora = precioHora;
	}

	public BigDecimal getPrecioDia() {
		return precioDia;
	}

	public void setPrecioDia(BigDecimal precioDia) {
		this.precioDia = precioDia;
	}
	
	
}
