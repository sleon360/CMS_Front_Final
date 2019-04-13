package com.appcms.entity;

public class UserInscripcion {
	String nombre;
	int monto;
	String strTarjeta;
	int idTarjeta;
	String fechaEmision;
	String fechaVencimiento;

	public UserInscripcion() {
		super();
	}

	public UserInscripcion(String nombre, int monto, String strTarjeta, int idTarjeta, String fechaEmision,
			String fechaVencimiento) {
		super();
		this.nombre = nombre;
		this.monto = monto;
		this.strTarjeta = strTarjeta;
		this.idTarjeta = idTarjeta;
		this.fechaEmision = fechaEmision;
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public String getStrTarjeta() {
		return strTarjeta;
	}

	public void setStrTarjeta(String strTarjeta) {
		this.strTarjeta = strTarjeta;
	}

	public int getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

}
