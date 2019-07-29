package com.appcms.entity;

public class UserGusto {
	int id;
	String nombre;
	String imagen;
	boolean gustado = false;

	public UserGusto() {
		super();
	}

	public UserGusto(int id, String nombre, String imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean isGustado() {
		return gustado;
	}

	public void setGustado(boolean gustado) {
		this.gustado = gustado;
	}

}
