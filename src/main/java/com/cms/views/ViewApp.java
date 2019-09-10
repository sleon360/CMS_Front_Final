package com.cms.views;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.appcms.entity.ViewEntity;

@Component
public class ViewApp {

<<<<<<< HEAD
	private StringBuilder sb;
=======
	StringBuilder sb = new StringBuilder("");
	private String TOKENONE;
>>>>>>> refs/heads/master
	private String apiUrl;
	private RestTemplate restTemplate = new RestTemplate();

<<<<<<< HEAD
	public ViewApp(String apiUrl) {
		this.apiUrl = apiUrl;
		this.sb = new StringBuilder("");
=======
	
	public ViewApp(String xapiUrl,String xTOKENONE) {
		this.apiUrl = xapiUrl;
		this.TOKENONE=xTOKENONE;
	}
	
	public void setrestTemplate(RestTemplate xrestTemplate) {
		this.restTemplate = xrestTemplate;

>>>>>>> refs/heads/master
	}
	

	public ResponseEntity<ViewEntity> loadView(String view) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

<<<<<<< HEAD
=======
//        System.out.println(xrestAuthentication.getTOKENONE()+" 666666666666666666666666666666666666666");
		headers.set("Authorization", TOKENONE);
		System.out.println(TOKENONE);
>>>>>>> refs/heads/master
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
<<<<<<< HEAD
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<ViewEntity> response = restTemplate.exchange(apiUrl + "/get/view/getByName/" + view, HttpMethod.GET,
					httpEntity, ViewEntity.class);
			return response.getBody().getContent();
		} catch(Exception e) {
			System.out.println("Error obteniendo vistas: " + e.getMessage());
			return "Not Loaded: " + view;
		}
=======
		
		return restTemplate.exchange(apiUrl + "/view/getByName/" + view, HttpMethod.GET,httpEntity, ViewEntity.class);
	
>>>>>>> refs/heads/master
	}

	public void addView(String viewName) {
		ResponseEntity<ViewEntity> data = this.loadView(viewName);
		sb.append(data.getBody());
	}

	public String render() {
		String datos=sb.toString();
		sb= new StringBuilder("");
		return datos;
	}

}