package com.appcms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceEntity {

	@JsonProperty("id_resource")
	private String id;
	private String nombre_resource;
	private String mime_resource;
	private String checksum_resource;
    private byte[] data;
	public String getId() {
		return id;
	}

	public void setId(String xid) {
		this.id = xid;
	}

	public String getNombre_resource() {
		return nombre_resource;
	}

	public void setNombre_resource(String xnombre_resource) {
		this.nombre_resource = xnombre_resource.toUpperCase();
	}
	
	public String getMime_resource() {
		return mime_resource;
	}

	public void setMime_resource(String xmime_resource) {
		this.mime_resource = xmime_resource;
	}
	
	public String getChecksum_resource() {
		return checksum_resource;
	}

	public void setChecksum_resource(String xchecksum_resource) {
		this.checksum_resource = xchecksum_resource;
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] xdata) {
		this.data = xdata;
	}
}