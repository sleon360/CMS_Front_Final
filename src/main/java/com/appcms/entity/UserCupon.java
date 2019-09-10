package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCupon {
	int id_cupon;
	String codigo;
	String nombre;
	String valor;
	String imagen;
	@JsonProperty("tipo_cupon")
	int tipoCupon;
	String fecha_emitido;
	String fecha_vencimiento;	
	
	public UserCupon() {
		super();
	}


	


	public int getId_cupon() {
		return id_cupon;
	}

	public void setId_cupon(int id_cupon) {
		this.id_cupon = id_cupon;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public int getTipoCupon() {
		return tipoCupon;
	}

	public void setTipoCupon(int tipoCupon) {
		this.tipoCupon = tipoCupon;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getFecha_emitido() {
		return fecha_emitido;
	}

	public void setFecha_emitido(String fecha_emitido) {
		this.fecha_emitido = fecha_emitido;
	}

	public String getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(String fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	

}
