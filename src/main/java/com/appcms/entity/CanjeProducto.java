package com.appcms.entity;


public class CanjeProducto {
	int idProducto;
	String nombreAsociado;
	String rutAsociado; 
	int cantidad;
	int monto;
	String csrf_token;
	String actionx;

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

	public String getCsrf_token() {
		return csrf_token;
	}

	public void setCsrf_token(String csrf_token) {
		this.csrf_token = csrf_token;
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

	@Override
	public String toString() {
		return "CanjeProducto [idProducto=" + idProducto + ", nombreAsociado=" + nombreAsociado + ", rutAsociado="
				+ rutAsociado + ", cantidad=" + cantidad + ", csrf_token=" + csrf_token + "]";
	}

}
