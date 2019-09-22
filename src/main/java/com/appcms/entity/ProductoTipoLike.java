package com.appcms.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductoTipoLike {

	@JsonProperty("id_producto")
	int id;
	String nombre;
	String titulo;
	String subtitulo;
	String imagen;
	@JsonProperty("porcentaje_info")
	String porcentajeInfo;
	@JsonProperty("condiciones_comercio")
	String condicionesComercio;
	@JsonProperty("direcciones")
	String direcciones;
	@JsonProperty("condiciones_legales")
	String condicionesLegales;
	int like;
	int precio;
	int equipesos;
	String fecha_creacion;
	String fecha_fin;
	@JsonProperty("titulo_tipo1")
	String titulo_detalles;
	@JsonProperty("titulo_tipo2")
	String tutulo_direcciones;
	@JsonProperty("formatos_detalles")
	List<FormatoDetalle> formatosDetalles;
	String codigo;
	int stock;
	int canjeados;
	
	@JsonProperty("tags_producto")
	public List<TagProducto> tagsProducto;
	
	@JsonProperty("genera_canje")
	public boolean generaCanje;
	
	@JsonProperty("cantidad_maxima_canje")
	int cantidadMaximaCanje;
	
	@JsonProperty("id_tipo_canje")
	int idTipoCanje;
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCanjeados() {
		return canjeados;
	}

	public void setCanjeados(int canjeados) {
		this.canjeados = canjeados;
	}
	
	public ProductoTipoLike() {
		super();
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

	public String getCondicionesComercio() {
		return condicionesComercio;
	}

	public void setCondicionesComercio(String condicionesComercio) {
		this.condicionesComercio = condicionesComercio;
	}

	public String getCondicionesLegales() {
		return condicionesLegales;
	}

	public void setCondicionesLegales(String condicionesLegales) {
		this.condicionesLegales = condicionesLegales;
	}
	
	public String getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(String direcciones) {
		this.direcciones = direcciones;
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

	public int getEquipesos() {
		return equipesos;
	}

	public void setEquipesos(int equipesos) {
		this.equipesos = equipesos;
	}
	
	public List<FormatoDetalle> getFormatosDetalles() {
		return this.formatosDetalles;
	}

	public void setFormatosDetalles(List<FormatoDetalle> formatosDetalles) {
		this.formatosDetalles = formatosDetalles;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public List<TagProducto> getTagsProducto() {
		return tagsProducto;
	}

	public void setTagsProducto(List<TagProducto> tagsProducto) {
		this.tagsProducto = tagsProducto;
	}

}
