
package com.appcms.entity;

//import com.scotiajava.backendscotia.controller.Emudata;

/**
 * Scotiauser main
 */
public class Scotiauser {

	/** Property id_cliente */
	int id_cliente;
	public int points;
	/** Property firstname */
	String firstname;

	/** Property lastname */
	String lastname;

	/** Property id_grupo */
	String id_grupo;

	/** Property email */
	String email;

	/** Property rut */
	String rut;

	/** Property telephone */
	String telephone;

	/** Property address_id */
	String address_id;

	String tokenScotia;

	/**
	 * Constructor
	 */
	public Scotiauser() {
		this.id_cliente = 0;
		this.points = this.getPints();
	}

	public Scotiauser(int id_cliente, String rut, String firstname, String lastname, String email, String id_grupo) {
		super();
		this.id_cliente = id_cliente;
		this.rut = rut;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.id_grupo = id_grupo;
		this.points = this.getPints();
	}

	public int getPints() {
		// return Emudata.getPoints();
		return 10;
	}

	/**
	 * Gets the id_cliente
	 */
	public int getId_cliente() {
		return this.id_cliente;
	}

	/**
	 * Sets the id_cliente
	 */
	public void setId_cliente(int value) {
		this.id_cliente = value;
	}

	/**
	 * Gets the firstname
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * Sets the firstname
	 */
	public void setFirstname(String value) {
		this.firstname = value;
	}

	/**
	 * Gets the lastname
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * Sets the lastname
	 */
	public void setLastname(String value) {
		this.lastname = value;
	}

	/**
	 * Gets the id_grupo
	 */
	public String getid_grupo() {
		return this.id_grupo;
	}

	/**
	 * Sets the id_grupo
	 */
	public void setid_grupo(String value) {
		this.id_grupo = value;
	}

	/**
	 * Gets the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email
	 */
	public void setEmail(String value) {
		this.email = value;
	}

	/**
	 * Gets the rut
	 */
	public String getRut() {
		return this.rut;
	}

	/**
	 * Sets the rut
	 */
	public void setRut(String value) {
		this.rut = value;
	}

	/**
	 * Gets the telephone
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * Sets the telephone
	 */
	public void setTelephone(String value) {
		this.telephone = value;
	}

	/**
	 * Gets the address_id
	 */
	public String getAddress_id() {
		return this.address_id;
	}

	/**
	 * Sets the address_id
	 */
	public void setAddress_id(String value) {
		this.address_id = value;
	}

	public String getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(String id_grupo) {
		this.id_grupo = id_grupo;
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

	@Override
	public String toString() {
		return "Scotiauser [id_cliente=" + id_cliente + ", points=" + points + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", id_grupo=" + id_grupo + ", email=" + email + ", rut=" + rut
				+ ", telephone=" + telephone + ", address_id=" + address_id + ", tokenScotia=" + tokenScotia + "]";
	}

}