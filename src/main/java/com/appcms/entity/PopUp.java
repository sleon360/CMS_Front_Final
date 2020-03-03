package com.appcms.entity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PopUp {

	@JsonProperty("id_pop_up")
	int idPopUp;
	
	boolean activo;

	String imagen;

	String link;

	public int getIdPopUp() {
		return idPopUp;
	}

	public void setIdPopUp(int idPopUp) {
		this.idPopUp = idPopUp;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
