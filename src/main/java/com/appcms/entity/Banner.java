package com.appcms.entity;

public class Banner {
	
	private int orden;
	private String ruta;
	private String url;
	private boolean blank;
	private int responsive;
	private int tipo;
	private String html;
	
	
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
	
	public int getResponsive() {
		return responsive;
	}
	
	public void setResponsive(int responsive) {
		this.responsive = responsive;
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
