package com.cms.views;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.appcms.entity.ViewEntity;
import com.appcms.security.RestAuthentication;

public class ViewApp {

	StringBuilder sb = new StringBuilder("");
	private HttpServletRequest rqx;
	private String apiUrl;

	public ViewApp(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public ViewApp(HttpServletRequest rq, String apiUrl) {
		this.rqx = rq;
		this.apiUrl = apiUrl;
	}

	private String loadView(String view) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		RestAuthentication xrestAuthentication = new RestAuthentication();
//        System.out.println(xrestAuthentication.getTOKENONE()+" 666666666666666666666666666666666666666");
		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());

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
		return sb.toString();
	}

}