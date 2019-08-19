package com.appcms.entity;

import java.util.List;

public class Menutop {

	String nombre;
	String link;
	int tipo;
	List<Menutop> submenutop;	
	
	public Menutop() {
	}
	
	public Menutop(String nombre, String link, int tipo, List<Menutop> submenutop) {
		super();
		this.nombre = nombre;
		this.link = link;
		this.tipo = tipo;
		this.submenutop = submenutop;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public List<Menutop> getSubmenutop() {
		return submenutop;
	}
	public void setSubmenutop(List<Menutop> submenutop) {
		this.submenutop = submenutop;
	}
	
}