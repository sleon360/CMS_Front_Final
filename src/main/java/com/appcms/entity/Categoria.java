package com.appcms.entity;
 
public class Categoria {
 
   int id;
   
   String nombre;
 
   String descripcion;
 
 
   String color;
 
   String color_hover;
 
   int tipo;
 
   String icono;
 
   String titulo_descipccion;
 
   String fecha_creacion;
 
   String fecha_modificacion;
 
   int estado;
 
   public Categoria() {
   }
 
   public Categoria(int id, String nombre, String descripcion, String color, String color_hover, int tipo, String icono,
		String titulo_descipccion, String fecha_creacion, String fecha_modificacion, int estado) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.descripcion = descripcion;
	this.color = color;
	this.color_hover = color_hover;
	this.tipo = tipo;
	this.icono = icono;
	this.titulo_descipccion = titulo_descipccion;
	this.fecha_creacion = fecha_creacion;
	this.fecha_modificacion = fecha_modificacion;
	this.estado = estado;
}

public String getNombre() {
      return this.nombre;
   }
 
   public void setNombre(String value) {
      this.nombre = value;
   }
 
   public String getDescripcion() {
      return this.descripcion;
   }
 
   public void setDescripcion(String value) {
      this.descripcion = value;
   }
 
   public int getId() {
      return this.id;
   }
 
   public void setId(int value) {
      this.id = value;
   }
 
   public String getColor() {
      return this.color;
   }
 
   public void setColor(String value) {
      this.color = value;
   }
 
   public String getColor_hover() {
      return this.color_hover;
   }
 
   public void setColor_hover(String value) {
      this.color_hover = value;
   }
 
   public int getTipo() {
      return this.tipo;
   }
 
   public void setTipo(int value) {
      this.tipo = value;
   }
 
   public String getIcono() {
      return this.icono;
   }
 
   public void setIcono(String value) {
      this.icono = value;
   }
 
   public String getTitulo_descipccion() {
      return this.titulo_descipccion;
   }
 
   public void setTitulo_descipccion(String value) {
      this.titulo_descipccion = value;
   }
 
   public String getFecha_creacion() {
      return this.fecha_creacion;
   }
 
   public void setFecha_creacion(String value) {
      this.fecha_creacion = value;
   }
 
   public String getFecha_modificacion() {
      return this.fecha_modificacion;
   }
 
   public void setFecha_modificacion(String value) {
      this.fecha_modificacion = value;
   }
 
   public int getEstado() {
      return this.estado;
   }
 
   public void setEstado(int value) {
      this.estado = value;
   }
}