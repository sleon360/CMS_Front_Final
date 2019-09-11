package com.cms.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.appcms.entity.ViewEntity;

@Service
public class ViewApp {

	private String apiUrl;

	@Autowired
	RestTemplate restTemplate;

	public ViewApp(@Qualifier("apiUrl") String apiUrl) {
		this.apiUrl = apiUrl;
	}	

	public String loadView(String... views) {
		if (views == null) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String html = "";
		for (int i = 0; i < views.length; i++) {
			String view = views[i];
			try {
				ViewEntity viewEntity = restTemplate.getForObject(apiUrl + "/get/view/getByName/" + view, ViewEntity.class);
				html += viewEntity.getContent();
			} catch(Exception e) {
				throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return html;		
	}

}