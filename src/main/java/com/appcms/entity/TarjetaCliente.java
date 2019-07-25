package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TarjetaCliente {
	
	@JsonProperty("card_number")
	String numero;
	
	@JsonProperty("card_key")
	String key;
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

}
