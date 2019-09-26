package com.appcms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.appcms.entity.ResourceEntity;

@Service
public class ResourceService {

	private final static Logger logger = LoggerFactory.getLogger(ResourceService.class);
	
	private String apiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	public ResourceService(@Qualifier("apiUrl") String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	public ResourceEntity getResouce(String folder, String resourceName) {
		String url = apiUrl + "/get/resource/" + folder + "?name={name}";
		try {
			return restTemplate
					.getForObject(url, ResourceEntity.class, resourceName);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
