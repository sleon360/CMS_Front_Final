package com.appcms.entity;

import java.util.List;

public class ProductoTipoLike {

	int id;
	String nombre;
	String titulo;
	String subtitulo;
	String imagen;
	String porcentajeInfo;
	String direccionTxt1;
	String direccionTxt2;
	String direccionTxt3;
	int like;
	int precio;
	int equiPesos;
	String fecha_creacion;
	String fecha_fin;
	String titulo_detalles;
	String tutulo_direcciones;
	public List<FormatoDetalle> detalles = null;
	public List<FormatoDetalle> direcciones = null;

	public ProductoTipoLike() {
		super();
	}

	public ProductoTipoLike(int id, String nombre, String titulo, String subtitulo, String imagen,
			String porcentajeInfo, String direccionTxt1, String direccionTxt2, String direccionTxt3, int like) { // CONSTRUCTOR TIPO 2
		super();
		this.id = id;
		this.nombre = nombre;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.imagen = imagen;
		this.porcentajeInfo = porcentajeInfo;
		this.direccionTxt1 = direccionTxt1;
		this.direccionTxt2 = direccionTxt2;
		this.direccionTxt3 = direccionTxt3;
		this.like = like;
	}
	public ProductoTipoLike(int id, String nombre, String titulo, String subtitulo, String imagen,
			String porcentajeInfo, String direccionTxt1, String direccionTxt2, String direccionTxt3, int like, int precio) { // CONSTRUCTOR TIPO 2
		super();
		this.id = id;
		this.nombre = nombre;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.imagen = imagen;
		this.porcentajeInfo = porcentajeInfo;
		this.direccionTxt1 = direccionTxt1;
		this.direccionTxt2 = direccionTxt2;
		this.direccionTxt3 = direccionTxt3;
		this.like = like;
		this.precio = precio;
	}
	

	public ProductoTipoLike(int id, String nombre, String titulo, String subtitulo, String imagen) { //CONSTRUCTOR TIPO 3
		super();
		this.id = id;
		this.nombre = nombre;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.imagen = imagen;
	}

	public ProductoTipoLike(int id, String nombre, String titulo, String subtitulo, String imagen,
			String porcentajeInfo, String direccionTxt1, String direccionTxt2, String direccionTxt3, int like,
			String fecha_creacion, String fecha_fin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.imagen = imagen;
		this.porcentajeInfo = porcentajeInfo;
		this.direccionTxt1 = direccionTxt1;
		this.direccionTxt2 = direccionTxt2;
		this.direccionTxt3 = direccionTxt3;
		this.like = like;
		this.fecha_creacion = fecha_creacion;
		this.fecha_fin = fecha_fin;
	}
	
	

	public ProductoTipoLike(int id, String titulo, int precio, String imagen) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.precio = precio;
		this.imagen = imagen;
	}
	


	public ProductoTipoLike(int id, String nombre, String titulo, String subtitulo, String imagen,
			String porcentajeInfo, int precio, int equiPesos, String titulo_detalles, String tutulo_direcciones,List<FormatoDetalle> detalles,List<FormatoDetalle> direcciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.imagen = imagen;
		this.porcentajeInfo = porcentajeInfo;
		this.precio = precio;
		this.equiPesos = equiPesos;
		this.titulo_detalles = titulo_detalles;
		this.tutulo_direcciones = tutulo_direcciones;
		this.detalles = detalles;
		this.direcciones = direcciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPorcentajeInfo() {
		return porcentajeInfo;
	}

	public void setPorcentajeInfo(String porcentajeInfo) {
		this.porcentajeInfo = porcentajeInfo;
	}

	public String getDireccionTxt1() {
		return direccionTxt1;
	}

	public void setDireccionTxt1(String direccionTxt1) {
		this.direccionTxt1 = direccionTxt1;
	}

	public String getDireccionTxt2() {
		return direccionTxt2;
	}

	public void setDireccionTxt2(String direccionTxt2) {
		this.direccionTxt2 = direccionTxt2;
	}

	public String getDireccionTxt3() {
		return direccionTxt3;
	}

	public void setDireccionTxt3(String direccionTxt3) {
		this.direccionTxt3 = direccionTxt3;
	}

	public int getLike() {
		return like;
	}
	
	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}	

	public String getTitulo_detalles() {
		return titulo_detalles;
	}

	public void setTitulo_detalles(String titulo_detalles) {
		this.titulo_detalles = titulo_detalles;
	}

	public String getTutulo_direcciones() {
		return tutulo_direcciones;
	}

	public void setTutulo_direcciones(String tutulo_direcciones) {
		this.tutulo_direcciones = tutulo_direcciones;
	}

	public int getEquiPesos() {
		return equiPesos;
	}

	public void setEquiPesos(int equiPesos) {
		this.equiPesos = equiPesos;
	}
	

}
