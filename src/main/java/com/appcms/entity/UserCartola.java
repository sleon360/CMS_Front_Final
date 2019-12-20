package com.appcms.entity;

public class UserCartola {
	
	private String nombre;
	private String apellido;
	private String strFecha;
	private String puntosDisponibles;
	private String puntosPorVencer;
	private String fechaVencimiento;
	private String puntosInscritos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getStrFecha() {
		return strFecha;
	}

	public void setStrFecha(String strFecha) {
		this.strFecha = strFecha;
	}

	public String getPuntosDisponibles() {
		return puntosDisponibles;
	}

	public void setPuntosDisponibles(String puntosDisponibles) {
		this.puntosDisponibles = puntosDisponibles;
	}

	public String getPuntosPorVencer() {
		return puntosPorVencer;
	}

	public void setPuntosPorVencer(String puntosCanjeados) {
		this.puntosPorVencer = puntosCanjeados;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public String getPuntosInscritos() {
		return puntosInscritos;
	}

	public void setPuntosInscritos(String puntosInscritos) {
		this.puntosInscritos = puntosInscritos;
	}

}
