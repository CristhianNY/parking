package com.ceiba.parking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cupo")
public class Cupo implements Serializable{

	@Id
	@Column(name="idCupo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCupo;
	@Column(name="cantidad")
	private int cantidad;
	public Cupo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cupo(int cantidad) {
		super();
		this.cantidad = cantidad;
	}
	public Long getIdCupo() {
		return idCupo;
	}
	public void setIdCupo(Long idCupo) {
		this.idCupo = idCupo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
