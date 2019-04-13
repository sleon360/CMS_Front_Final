package com.appcms.entity;

public class UserCartolaMovimiento {
	String fecha;
	String transaccion;
	String movimiento;
	String monto;
	String saldo;

	
	
	public UserCartolaMovimiento() {
		super();
	}

	public UserCartolaMovimiento(String fecha, String transaccion, String movimiento, String monto, String saldo) {
		super();
		this.fecha = fecha;
		this.transaccion = transaccion;
		this.movimiento = movimiento;
		this.monto = monto;
		this.saldo = saldo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

}
