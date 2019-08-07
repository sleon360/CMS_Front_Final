package com.appcms.entity;

import java.util.List;

public class Scmenu {

	int id;
	String nombre;
	String strIndex;
	String color;
	int tipo;
	String link;
	String fecha_creacion;
	String fecha_modificacion;
	int estado;
	boolean visibleTop = true;
	boolean visibleOnlyLogin = false;
	boolean userElement = false;

	private List<Scsubmenu> submenues;

	public Scmenu() {
		super();
		this.id = 0;
	}



	public String getStrIndex() {
		return strIndex;
	}

	public void setStrIndex(String strIndex) {
		this.strIndex = strIndex;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(String fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public boolean isVisibleTop() {
		return visibleTop;
	}

	public void setVisibleTop(boolean visibleTop) {
		this.visibleTop = visibleTop;
	}

	public boolean isVisibleOnlyLogin() {
		return visibleOnlyLogin;
	}

	public void setVisibleOnlyLogin(boolean visibleOnlyLogin) {
		this.visibleOnlyLogin = visibleOnlyLogin;
	}

	public boolean isUserElement() {
		return userElement;
	}

	public void setUserElement(boolean userElement) {
		this.userElement = userElement;
	}

	public List<Scsubmenu> getSubmenues() {
		return this.submenues;
	}

	public void setSubmenues(List<Scsubmenu> submenues) {
		this.submenues = submenues;
	}
	

}
