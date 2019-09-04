package com.appcms.entity;


public class ViewEntity {

	private int id_view;
	
	private String name;
	
	private String content;
	
	private String estado;	

    public int getId_view() {
		return id_view;
	}
	
	public void setId_view(int id_view) {
		this.id_view = id_view;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}

