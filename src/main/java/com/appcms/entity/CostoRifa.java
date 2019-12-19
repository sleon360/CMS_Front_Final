package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CostoRifa {

	@JsonProperty("id_costo_rifa")
	private int idCostoRifa;
	
	private int cantidad;
	
	private int costo;
	
	@JsonProperty("fecha_creacion")
	private String fechaCreacion;

	public int getIdCostoRifa() {
		return idCostoRifa;
	}

	public void setIdCostoRifa(int idCostoRifa) {
		this.idCostoRifa = idCostoRifa;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
