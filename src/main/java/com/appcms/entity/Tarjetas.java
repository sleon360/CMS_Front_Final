package com.appcms.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tarjetas {

	@JsonProperty("cards")
	ArrayList<TarjetaCliente> tarjetasCliente;
	
	@JsonProperty("customer_segment")
	String tipoCliente;

	public ArrayList<TarjetaCliente> getTarjetasCliente() {
		return tarjetasCliente;
	}

	public void setTarjetasCliente(ArrayList<TarjetaCliente> tarjetasCliente) {
		this.tarjetasCliente = tarjetasCliente;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
}
