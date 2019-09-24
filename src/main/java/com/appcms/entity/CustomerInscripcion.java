package com.appcms.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerInscripcion {

	@JsonProperty("id_inscripcion")
	int idInscripcion;
	
	String rubro;
	
	int monto;
	
	String tarjeta;
	
	@JsonProperty("fecha_emision")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	Date fechaEmision;
	
	@JsonProperty("fecha_vencimiento")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	Date fechaVencimiento;
	
	boolean vigente;
	
	public int getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(int idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public String getRubro() {
		return rubro;
	}
	
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}
	
	public int getMonto() {
		return monto;
	}
	
	public void setMonto(int monto) {
		this.monto = monto;
	}
	
	public String getTarjeta() {
		return tarjeta;
	}
	
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	public Date getFechaEmision() {
		return fechaEmision;
	}
	
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public boolean isVigente() {
		return vigente;
	}

	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}
	
}
