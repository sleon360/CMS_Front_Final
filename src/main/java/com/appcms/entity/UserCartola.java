package com.appcms.entity;

import java.util.ArrayList;
import java.util.List;

public class UserCartola {
	String nombre;
	String apellido;
	String strFecha;
	int puntosDisponibles;
	int puntosCanjeados;
	int ano;
	public List<UserCartolaMovimiento> movimientos = new ArrayList<>();
	
	public UserCartola() {
		super();
	}

	public UserCartola(String nombre, String apellido, String strFecha, int puntosDisponibles, int puntosCanjeados,
			int ano, List<UserCartolaMovimiento> movimientos) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.strFecha = strFecha;
		this.puntosDisponibles = puntosDisponibles;
		this.puntosCanjeados = puntosCanjeados;
		this.ano = ano;
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

	public int getPuntosCanjeados() {
		return puntosCanjeados;
	}

	public void setPuntosCanjeados(int puntosCanjeados) {
		this.puntosCanjeados = puntosCanjeados;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public List<UserCartolaMovimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<UserCartolaMovimiento> movimientos) {
		this.movimientos = movimientos;
	}

}
