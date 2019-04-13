package com.appcms.entity;

public class Information {
	int id;
	int id_submenu;
	String nombre;
	int tipo;
	String html;

	public Information() {
		super();
	}

	public Information(int id, int id_submenu, String nombre, int tipo, String html) {
		super();
		this.id = id;
		this.id_submenu = id_submenu;
		this.nombre = nombre;
		this.tipo = tipo;
		this.html = html;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_submenu() {
		return id_submenu;
	}

	public void setId_submenu(int id_submenu) {
		this.id_submenu = id_submenu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
