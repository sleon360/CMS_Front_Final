package com.appcms.entity;

public class FormatoDetalle {
	String titulo;
	String detalle;
	int tipo; // 1:Texto 2:Link
	int finalidad; // 1:Detalle 2:Dirección

	public FormatoDetalle() {
		super();
	}

	public FormatoDetalle(String titulo, String detalle, int tipo) {
		super();
		this.titulo = titulo;
		this.detalle = detalle;
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(int finalidad) {
		this.finalidad = finalidad;
	}

}
