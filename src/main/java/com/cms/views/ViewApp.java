package com.cms.views;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.appcms.entity.ViewEntity;

public class ViewApp {

	private StringBuilder sb;
	private String apiUrl;

	public ViewApp(String apiUrl) {
		this.apiUrl = apiUrl;
		this.sb = new StringBuilder("");
	}

	private String loadView(String view) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<ViewEntity> response = restTemplate.exchange(apiUrl + "/get/view/getByName/" + view, HttpMethod.GET,
					httpEntity, ViewEntity.class);
			return response.getBody().getContent();
		} catch(Exception e) {
			System.out.println("Error obteniendo vistas: " + e.getMessage());
			return "Not Loaded: " + view;
		}
	}

	public void addView(String viewName) {
		String data = this.loadView(viewName);
		sb.append(data);
	}

	public String render() {
		return sb.toString();
	}

}