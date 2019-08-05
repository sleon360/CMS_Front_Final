package com.appcms.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.appcms.entity.ResourceEntity;

@Component
public class GetRestService {

	private String apiUrl;
	
	public GetRestService(@Qualifier("apiUrl") String xapiUrl)
	{
		this.apiUrl=xapiUrl;
	}
	
	public ResourceEntity getResouce(String folder,String resourceName,String TOKENONE)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", TOKENONE);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ResourceEntity> xresponse = restTemplate.exchange(apiUrl + "/resource/" + folder + "/get?name={name}", HttpMethod.GET, httpEntity, ResourceEntity.class, resourceName);
		
		if (xresponse.getStatusCode() == HttpStatus.OK) {
			ResourceEntity rEntity = xresponse.getBody();
			return rEntity;
		}  else {
			return new ResourceEntity();
		}
	}
}
