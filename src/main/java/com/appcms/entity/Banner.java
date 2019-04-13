package com.appcms.entity;

public class Banner {
	public int orden;
	public String ruta;
	public String url;
	public boolean blank;
	
	public Banner() {
	}
	public Banner(int orden, String ruta, String url, boolean blank) {
		super();
		this.orden = orden;
		this.ruta = ruta;
		this.url = url;
		this.blank = blank;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isBlank() {
		return blank;
	}
	public void setBlank(boolean blank) {
		this.blank = blank;
	}
	
}
