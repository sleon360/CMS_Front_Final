package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockTicket {
	
	@JsonProperty("empresa")
	String empresa;
	
	@JsonProperty("ACTIVO")
	int activo;
	
	@JsonProperty("QUEMADO")
	int quemado;
	
	public StockTicket() {
		super();
	}
	public StockTicket(int ACTIVO, int QUEMADO) {
		super();
		this.activo = ACTIVO;
		this.quemado = QUEMADO;
	}
	public String getEmpresa() {
		return this.empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public int getActivo() {
		return this.activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	public int getQuemado() {
		return this.quemado;
	}
	public void setQuemado(int quemado) {
		this.quemado = quemado;
	}
	@Override
	public String toString() {
		return "StockTicket [empresa=" + empresa + ", activo=" + activo + ", quemado=" + quemado + "]";
	}
	
}
