package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TagProducto {
	
	@JsonProperty("id_tag_producto")
	int idTagProducto;

	String nombre;
	
	String descripcion;
	
	String icono;
	
	boolean estado;
	
	public int getIdTagProducto() {
		return idTagProducto;
	}

	public void setIdTagProducto(int idTagProducto) {
		this.idTagProducto = idTagProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
