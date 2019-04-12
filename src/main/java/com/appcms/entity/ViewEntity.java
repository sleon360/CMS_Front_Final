package com.appcms.entity;


public class ViewEntity {

	private int id_view;

	private String name;
	

	private String content;
	

	private String status;
	

	private String idviewfk;
	

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

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getIdviewfk() {
		return idviewfk;
	}


	public void setIdviewfk(String idviewfk) {
		this.idviewfk = idviewfk;
	}

    
}

