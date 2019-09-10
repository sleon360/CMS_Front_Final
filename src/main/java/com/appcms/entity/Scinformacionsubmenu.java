package com.appcms.entity;

import java.util.ArrayList;
import java.util.List;

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
	String texto_link;
	String json_condiciones;
	String fecha_creacion;
	String fecha_modificacion;
	String imagen_logo;
	List<String> condicioneslista = new ArrayList<>();
	int estado;
	
	public String getImagen_logo() {
		return imagen_logo;
	}

	public void setImagen_logo(String imagen_logo) {
		this.imagen_logo = imagen_logo;
	}

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

	public String getTexto_link() {
		return texto_link;
	}

	public void setTexto_link(String texto_link) {
		this.texto_link = texto_link;
	}

	public String getJson_condiciones() {
		return json_condiciones;
	}

	public void setJson_condiciones(String json_condiciones) {
		this.json_condiciones = json_condiciones;
	}

	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(String fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<String> getCondicioneslista() {
		return condicioneslista;
	}

	public void setCondicioneslista(List<String> condicioneslista) {
		this.condicioneslista = condicioneslista;
	}

	public void addCondicioneslista(String condiciones) {
		this.condicioneslista.add(condiciones);
	}
<<<<<<< HEAD

=======
	
	
	
	
>>>>>>> refs/heads/master
}
