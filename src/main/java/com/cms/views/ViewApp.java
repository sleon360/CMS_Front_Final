package com.cms.views;


import org.springframework.beans.factory.annotation.Qualifier;
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

	StringBuilder sb = new StringBuilder("");
	private String TOKENONE;
	private String apiUrl;

	public ViewApp(String xapiUrl,String xTOKENONE) {
		this.apiUrl = xapiUrl;
		this.TOKENONE=xTOKENONE;
	}

	public String loadView(String view) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

//        System.out.println(xrestAuthentication.getTOKENONE()+" 666666666666666666666666666666666666666");
		headers.set("Authorization", TOKENONE);
		System.out.println(TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ViewEntity> response = restTemplate.exchange(apiUrl + "/view/getByName/" + view, HttpMethod.GET,
				httpEntity, ViewEntity.class);
		if (response.getStatusCodeValue() == 200) {
			ViewEntity vV = response.getBody();
//        	System.out.println(vV.getContent()+"--------------------------------------------------");
			return vV.getContent();
		} else {
			return "Not Load " + view;
		}
	}

	public void addView(String viewName) {
		String data = this.loadView(viewName);
		sb.append(data);
	}

	public String render() {
		String datos=sb.toString();
		sb= new StringBuilder("");
		return datos;
	}

}