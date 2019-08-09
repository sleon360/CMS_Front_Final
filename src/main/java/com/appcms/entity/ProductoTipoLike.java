package com.appcms.entity;
import java.util.List;
import java.util.ListIterator;

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
	String direccionTxt1;
	String direccionTxt2;
	String direccionTxt3;
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
	public List<FormatoDetalle> formatosDetalles;
	public List<FormatoDetalle> detalles;
	public List<FormatoDetalle> direcciones;
	String codigo;

	int stock;
	int canjeados;
	
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

	public List<FormatoDetalle> getDetalles() {
		return this.detalles;
	}

	public void setDetalles(List<FormatoDetalle> detalles) {
		this.detalles = detalles;
	}

	public List<FormatoDetalle> getDirecciones() {
		return this.direcciones;
	}

	public void setDirecciones(List<FormatoDetalle> direcciones) {
		this.direcciones = direcciones;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public boolean equalsFormatosDetalles(List<FormatoDetalle> f1) {
		ListIterator<FormatoDetalle> e1 = f1.listIterator();
		ListIterator<FormatoDetalle> e2 = this.getFormatosDetalles().listIterator();
	
		if(f1.size()!=this.getFormatosDetalles().size())
		{
			return false;
		}
		
			while (e1.hasNext()) {
				FormatoDetalle o1 = e1.next();
				FormatoDetalle o2 = e2.next();
			    if (!o1.getDetalle().equals(o2.getDetalle()))
			    	 return false;
			  }
			  return true;
		}
	
	
	public boolean equalsDirecciones(List<FormatoDetalle> f1) {
		ListIterator<FormatoDetalle> e1 = f1.listIterator();
		ListIterator<FormatoDetalle> e2 = this.getDirecciones().listIterator();
	
		if(f1.size()!=this.getDirecciones().size())
		{
			return false;
		}
		
			while (e1.hasNext()) {
				FormatoDetalle o1 = e1.next();
				FormatoDetalle o2 = e2.next();
			    if (!o1.getDetalle().equals(o2.getDetalle()))
			    	 return false;
			  }
			  return true;
		}

	
	
	public boolean equalsgetDetalles(List<FormatoDetalle> f1) {
		ListIterator<FormatoDetalle> e1 = f1.listIterator();
		ListIterator<FormatoDetalle> e2 = this.getDetalles().listIterator();
	
		if(f1.size()!=this.getDetalles().size())
		{
			return false;
		}
		
			while (e1.hasNext()) {
				FormatoDetalle o1 = e1.next();
				FormatoDetalle o2 = e2.next();
			    if (!o1.getDetalle().equals(o2.getDetalle()))
			    	 return false;
			  }
			  return true;
		}

	
	
}
