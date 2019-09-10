package com.appcms.entity;

public class Scotiauser {

	int id_cliente;

	String firstname;

	String lastname;

	String id_grupo;

	String email;

	String rut;

	String telephone;

	String address_id;

	String tokenScotia;

	int points;

	public Scotiauser() {
		this.id_cliente = 0;
	}

	public Scotiauser(int id_cliente, String rut, String firstname, String lastname, String email, String id_grupo) {
		super();
		this.id_cliente = id_cliente;
		this.rut = rut;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.id_grupo = id_grupo;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getId_grupo() {
		return id_grupo;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress_id() {
		return address_id;
	}

	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}

	public String getTokenScotia() {
		return tokenScotia;
	}

	public void setTokenScotia(String tokenScotia) {
		this.tokenScotia = tokenScotia;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}	

}