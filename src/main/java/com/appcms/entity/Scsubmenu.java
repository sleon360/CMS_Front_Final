package com.appcms.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Scsubmenu {

	int id;
	String nombre;
	String strIndex = null;
	String link;
	String color;
	String color_hover;
	String color_borde;
	int tipo;
	String icono;
	String imagen;
	String titulo_descripcion; 
	String descripcion;
	String fecha_creacion;
	String fecha_modificacion;
	int estado;
	Scinformacionsubmenu informationsubmenu = null;
	List<ProductoTipoLike> productosLikeLista = new ArrayList<>();
	List<ProductoCategoria> categoriaProductoLista = new ArrayList<>();
	List<TarjetaCliente> tarjetasCliente = new ArrayList<>();
	Information informationHtml = null;
	boolean visibleTop = true;
	boolean visibleOnlyLogin = false;
<<<<<<< HEAD
	List<TagProducto> tagsProductos = new ArrayList<>();
	
=======

>>>>>>> refs/heads/master
	public Scsubmenu() {
		super();
	}


	public Scsubmenu(String strIndex, int id, String nombre, String link, String color, String color_hover,
			String color_borde, int tipo, String icono, String imagen, String titulo_descripcion, String descripcion,
			String fecha_creacion, String fecha_modificacion, int estado) {
		super();
		this.strIndex = strIndex;
		this.id = id;
		this.nombre = nombre;
		this.link = link;
		this.color = color;
		this.color_hover = color_hover;
		this.color_borde = color_borde;
		this.tipo = tipo;
		this.icono = icono;
		this.imagen = imagen;
		this.titulo_descripcion = titulo_descripcion;
		this.descripcion = descripcion;
		this.fecha_creacion = fecha_creacion;
		this.fecha_modificacion = fecha_modificacion;
		this.estado = estado;
	}
	
	
	

	
	public String getStrIndex() {
		return strIndex;
	}

	public void setStrIndex(String strIndex) {
		this.strIndex = strIndex;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor_hover() {
		return color_hover;
	}

	public void setColor_hover(String color_hover) {
		this.color_hover = color_hover;
	}

	public String getColor_borde() {
		return color_borde;
	}

	public void setColor_borde(String color_borde) {
		this.color_borde = color_borde;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTitulo_descripcion() {
		return titulo_descripcion;
	}

	public void setTitulo_descripcion(String titulo_descripcion) {
		this.titulo_descripcion = titulo_descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public boolean isVisibleTop() {
		return visibleTop;
	}

	public void setVisibleTop(boolean visibleTop) {
		this.visibleTop = visibleTop;
	}

	public boolean isVisibleOnlyLogin() {
		return visibleOnlyLogin;
	}

	public void setVisibleOnlyLogin(boolean visibleOnlyLogin) {
		this.visibleOnlyLogin = visibleOnlyLogin;
	}

<<<<<<< HEAD
	@Override
	public String toString() {
		return "Scsubmenu [id=" + id + ", nombre=" + nombre + ", strIndex=" + strIndex + ", link=" + link + ", color="
				+ color + ", color_hover=" + color_hover + ", color_borde=" + color_borde + ", tipo=" + tipo
				+ ", icono=" + icono + ", imagen=" + imagen + ", titulo_descripcion=" + titulo_descripcion
				+ ", descripcion=" + descripcion + ", fecha_creacion=" + fecha_creacion + ", fecha_modificacion="
				+ fecha_modificacion + ", estado=" + estado + ", informationsubmenu=" + informationsubmenu
				+ ", productosLikeLista=" + productosLikeLista + ", categoriaProductoLista=" + categoriaProductoLista
				+ ", TarjetaClienteLista=" + tarjetasCliente + ", informationHtml=" + informationHtml
				+ ", visibleTop=" + visibleTop + ", visibleOnlyLogin=" + visibleOnlyLogin + "]";
	}

	public Scinformacionsubmenu getInformationsubmenu() {
		return informationsubmenu;
	}

	public void setInformationsubmenu(Scinformacionsubmenu informationsubmenu) {
		this.informationsubmenu = informationsubmenu;
	}

	public List<ProductoTipoLike> getProductosLikeLista() {
		return productosLikeLista;
	}

	public void setProductosLikeLista(List<ProductoTipoLike> productosLikeLista) {
		this.productosLikeLista = productosLikeLista;
	}

	public List<ProductoCategoria> getCategoriaProductoLista() {
		return categoriaProductoLista;
	}

	public void setCategoriaProductoLista(List<ProductoCategoria> categoriaProductoLista) {
		this.categoriaProductoLista = categoriaProductoLista;
	}

	public List<TarjetaCliente> getTarjetasCliente() {
		return tarjetasCliente;
	}

	public void setTarjetasCliente(List<TarjetaCliente> tarjetasCliente) {
		this.tarjetasCliente = tarjetasCliente;
	}

	public Information getInformationHtml() {
		return informationHtml;
	}

	public void setInformationHtml(Information informationHtml) {
		this.informationHtml = informationHtml;
	}

	public List<TagProducto> getTagsProductos() {
		return tagsProductos;
	}

	public void setTagsProductos(List<TagProducto> tagsProductos) {
		this.tagsProductos = tagsProductos;
	}

=======
>>>>>>> refs/heads/master
}
