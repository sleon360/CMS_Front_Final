package com.appcms.entity;

public class ResourceEntity {


	private String id;
	private String nombre_resource;
	private String mime_resource;
	private String checksum_resource;
    private byte[] data;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre_resource() {
		return nombre_resource;
	}

	public void setNombre_resource(String nombre_resource) {
		this.nombre_resource = nombre_resource.toUpperCase();
	}
	
	public String getMime_resource() {
		return mime_resource;
	}

	public void setMime_resource(String mime_resource) {
		this.mime_resource = mime_resource;
	}
	
	public String getChecksum_resource() {
		return checksum_resource;
	}

	public void setChecksum_resource(String checksum_resource) {
		this.checksum_resource = checksum_resource;
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}