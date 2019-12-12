package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rifa {
	
	@JsonProperty("id_rifa")
	int idRifa;

	@JsonProperty("max_cantidad_numeros")
	int maxCantidadNumeros;
	
	String condiciones;

	public int getIdRifa() {
		return idRifa;
	}

	public void setIdRifa(int idRifa) {
		this.idRifa = idRifa;
	}

	public int getMaxCantidadNumeros() {
		return maxCantidadNumeros;
	}

	public void setMaxCantidadNumeros(int maxCantidadNumeros) {
		this.maxCantidadNumeros = maxCantidadNumeros;
	}

	public String getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}
	
}
