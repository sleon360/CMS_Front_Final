package com.appcms.entity;


public class CanjeProducto {
	int idProducto;
	String nombreAsociado;
	String rutAsociado; 
	int cantidad = 1;
	int monto = 0;
	String cardKey;
	String cardNumber;
	String actionx;
	String region;
	String comuna;
	String direccion;
	String nroCalle;
	String apartamento;
	String telefono;
	String correo;

	public CanjeProducto() {
		super();
		this.cantidad = 1;
		this.monto = 0;
		this.actionx = "";
	}

	public CanjeProducto(int idProducto, String nombreAsociado, String rutAsociado, int cantidad) {
		super();
		this.idProducto = idProducto;
		this.nombreAsociado = nombreAsociado;
		this.rutAsociado = rutAsociado;
		this.cantidad = cantidad;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreAsociado() {
		return nombreAsociado;
	}

	public void setNombreAsociado(String nombreAsociado) {
		this.nombreAsociado = nombreAsociado;
	}

	public String getRutAsociado() {
		return rutAsociado;
	}

	public void setRutAsociado(String rutAsociado) {
		this.rutAsociado = rutAsociado;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getActionx() {
		return actionx;
	}

	public void setActionx(String action) {
		this.actionx = action;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public String getCardKey() {
		return cardKey;
	}

	public void setCardKey(String cardKey) {
		this.cardKey = cardKey;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getNroCalle() {
		return nroCalle;
	}

	public void setNroCalle(String nroCalle) {
		this.nroCalle = nroCalle;
	}

	public String getApartamento() {
		return apartamento;
	}

	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
