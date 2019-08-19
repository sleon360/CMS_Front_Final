package com.appcms.entity;

import java.util.ArrayList;
import java.util.List;

public class UserCartola {
	String nombre;
	String apellido;
	String strFecha;
	int puntosDisponibles;
	int puntosPorVencer;
	String fechaVencimiento;
	int puntosInscritos;
	List<UserCartolaMovimiento> movimientos = new ArrayList<>();
	
	public UserCartola() {
		super();
	}

	public UserCartola(String nombre, String apellido, String strFecha, int puntosDisponibles, int puntosCanjeados,
			String fechaVencimiento, List<UserCartolaMovimiento> movimientos) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.strFecha = strFecha;
		this.puntosDisponibles = puntosDisponibles;
		this.puntosPorVencer = puntosCanjeados;
		this.fechaVencimiento = fechaVencimiento;
		this.movimientos = movimientos;
	}

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

	public int getPuntosDisponibles() {
		return puntosDisponibles;
	}

	public void setPuntosDisponibles(int puntosDisponibles) {
		this.puntosDisponibles = puntosDisponibles;
	}

	public int getPuntosPorVencer() {
		return puntosPorVencer;
	}

	public void setPuntosPorVencer(int puntosCanjeados) {
		this.puntosPorVencer = puntosCanjeados;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public int getPuntosInscritos() {
		return puntosInscritos;
	}

	public void setPuntosInscritos(int puntosInscritos) {
		this.puntosInscritos = puntosInscritos;
	}

	public List<UserCartolaMovimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<UserCartolaMovimiento> movimientos) {
		this.movimientos = movimientos;
	}

}
