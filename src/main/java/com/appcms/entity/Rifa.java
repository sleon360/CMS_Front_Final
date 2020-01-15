package com.appcms.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rifa {
	
	@JsonProperty("id_rifa")
	private int idRifa;
	
	private String nombre;
	
	private String titulo;
	
	private String subtitulo;
	
	private String imagen;
	
	@JsonProperty("icono_animado")
	private String iconoAnimado;
	
	private String fondo;
	
	@JsonProperty("fondo_modal")
	private String fondoModal;
	
	@JsonProperty("cantidad_numeros")
	private int cantidadNumeros;

	@JsonProperty("numeros_maximos")
	private int numerosMaximos;

	@JsonProperty("condiciones_legales")
	private String condicionesLegales;
	
	private boolean estado;
	
	@JsonProperty("fecha_creacion")
	private String fechaCreacion;

	@JsonProperty("id_scsubmenu")
	private int idScsubmenu;
	
	@JsonProperty("costos_rifa")
	private List<CostoRifa> costosRifa;

	public int getIdRifa() {
		return idRifa;
	}

	public void setIdRifa(int idRifa) {
		this.idRifa = idRifa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getIconoAnimado() {
		return iconoAnimado;
	}

	public void setIconoAnimado(String iconoAnimado) {
		this.iconoAnimado = iconoAnimado;
	}

	public String getFondo() {
		return fondo;
	}

	public void setFondo(String fondo) {
		this.fondo = fondo;
	}

	public String getFondoModal() {
		return fondoModal;
	}

	public void setFondoModal(String fondoModal) {
		this.fondoModal = fondoModal;
	}

	public int getCantidadNumeros() {
		return cantidadNumeros;
	}

	public void setCantidadNumeros(int cantidadNumeros) {
		this.cantidadNumeros = cantidadNumeros;
	}

	public int getNumerosMaximos() {
		return numerosMaximos;
	}

	public void setNumerosMaximos(int numerosMaximos) {
		this.numerosMaximos = numerosMaximos;
	}

	public String getCondicionesLegales() {
		return condicionesLegales;
	}

	public void setCondicionesLegales(String condicionesLegales) {
		this.condicionesLegales = condicionesLegales;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getIdScsubmenu() {
		return idScsubmenu;
	}

	public void setIdScsubmenu(int idScsubmenu) {
		this.idScsubmenu = idScsubmenu;
	}

	public List<CostoRifa> getCostosRifa() {
		return costosRifa;
	}

	public void setCostosRifa(List<CostoRifa> costosRifa) {
		this.costosRifa = costosRifa;
	}

}
