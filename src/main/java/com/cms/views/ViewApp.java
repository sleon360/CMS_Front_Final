package com.cms.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.appcms.entity.ViewEntity;
import com.cms.errors.ViewRendererException;

@Service
public class ViewApp {

	private String apiUrl;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	public ViewApp(@Qualifier("apiUrl") String apiUrl) {
		this.apiUrl = apiUrl;
	}	

	public String loadViews(String... views) throws ViewRendererException {
		if (views == null) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String html = "";
		for (int i = 0; i < views.length; i++) {
			String view = views[i];
			try {
				ViewEntity viewEntity = restTemplate.getForObject(apiUrl + "/get/view/getByName/" + view, ViewEntity.class);
				html += viewEntity.getContent();
			} catch(HttpStatusCodeException e) {
				throw new ViewRendererException("Error HTTP " + e.getRawStatusCode() +" hacia BFF: " + e.getResponseBodyAsString());
			} catch(RestClientException e) {
				throw new ViewRendererException("Entidad no esperada: " + e.getMessage());
			} catch(Exception e) {
				throw new ViewRendererException("Error general: " + e.getMessage());
			}
		}
		return html;		
	}

}