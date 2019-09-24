package com.appcms.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductoCategoria {

	@JsonProperty("id_categoria_producto")
	int id;
	String nombre;
	String strIndex;
	@JsonProperty("condiciones_legales")
	private String condicionesLegales;
	String imagen;
	@JsonProperty("color_hover")
	String colorHover;
	int tipo; // 1:Productos  2:formulario
	List<ProductoTipoLike> productos = new ArrayList<>();

	public ProductoCategoria() {
		super();
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

	public String getCondicionesLegales() {
		return condicionesLegales;
	}

	public void setCondicionesLegales(String condicionesLegales) {
		this.condicionesLegales = condicionesLegales;
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
		return this.productos;
	}

	public void setProductos(List<ProductoTipoLike> productos) {
		this.productos = productos;
	}
	
	
	public boolean equalsProductoTipoLike(List<ProductoTipoLike> f1) {
		ListIterator<ProductoTipoLike> e1 = f1.listIterator();
		ListIterator<ProductoTipoLike> e2 = this.getProductos().listIterator();
	
		if(f1.size()!=this.getProductos().size())
		{
			return false;
		}
			while (e1.hasNext()) {
				ProductoTipoLike o1 = e1.next();
				ProductoTipoLike o2 = e2.next();
			    if (!o1.getNombre().equals(o2.getNombre()))
			    	 return false;
			  }
			  return true;
		}


}
