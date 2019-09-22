package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Scinformacionsubmenu {

	@JsonProperty("id_scsubmenu_informacion")
	int id;
	int id_submenu;
	@JsonProperty("submenu_strindex")
	String submenuStrindex;
	String imagen;
	String titulo;
	String subtitulo;
	String descripcion;
	String link;
	
	@JsonProperty("texto_link")
	String textoLink;
	
	String condiciones;
	
	@JsonProperty("fecha_creacion")
	String fechaCreacion;
	
	@JsonProperty("fecha_modificacion")
	String fechaModificacion;
	
	@JsonProperty("imagen_logo")
	String imagenLogo;
	
	int estado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_submenu() {
		return id_submenu;
	}
	public void setId_submenu(int id_submenu) {
		this.id_submenu = id_submenu;
	}
	public String getSubmenuStrindex() {
		return submenuStrindex;
	}
	public void setSubmenuStrindex(String submenuStrindex) {
		this.submenuStrindex = submenuStrindex;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTextoLink() {
		return textoLink;
	}
	public void setTextoLink(String textoLink) {
		this.textoLink = textoLink;
	}
	public String getCondiciones() {
		return condiciones;
	}
	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getImagenLogo() {
		return imagenLogo;
	}
	public void setImagenLogo(String imagenLogo) {
		this.imagenLogo = imagenLogo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}

}
