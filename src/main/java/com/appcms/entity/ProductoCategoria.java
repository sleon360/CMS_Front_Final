package com.appcms.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductoCategoria {

	@JsonProperty("id_categoria_producto")
	int id;
	String nombre;
	String strIndex;
	String descripccion;
	String imagen;
	String colorHover;
	int tipo; // 1:Productos  2:formulario
	public List<ProductoTipoLike> productos = new ArrayList<>();

	public ProductoCategoria() {
		super();
	}

	public ProductoCategoria(int id, String strIndex, String nombre, String descripcion, String imagen, int tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.strIndex = strIndex;
		this.descripccion = descripcion;
		this.imagen = imagen;
		this.tipo = tipo;
	}
	public ProductoCategoria(int id, String strIndex, String nombre, String descripcion, String imagen, int tipo,String colorHover) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.strIndex = strIndex;
		this.descripccion = descripcion;
		this.imagen = imagen;
		this.tipo = tipo;
		this.colorHover = colorHover;
	}
	public int getId() {
		return id;
	}

	public String getColorHover() {
		return colorHover;
	}

	public void setColorHover(String colorHover) {
		this.colorHover = colorHover;
	}

	public String getStrIndex() {
		return strIndex;
	}

	public void setStrIndex(String strIndex) {
		this.strIndex = strIndex;
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

	public String getDescripccion() {
		return descripccion;
	}

	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public List<ProductoTipoLike> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoTipoLike> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "ProductoCategoria [id=" + id + ", nombre=" + nombre + ", strIndex=" + strIndex + ", descripccion="
				+ descripccion + ", imagen=" + imagen + ", colorHover=" + colorHover + ", tipo=" + tipo
				+ ", productosList=" + productos + "]";
	}

}
