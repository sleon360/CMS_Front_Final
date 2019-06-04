package com.appcms.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerAuthentication {

	static String CustomerAuth(String username, String password, String token) {
		try {
			String TOKENTWO;
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.set("Authorization", token);
			MultiValueMap<String, String> xlogin = new LinkedMultiValueMap<String, String>();
			xlogin.add("userCostumer", username);
			xlogin.add("userPassword", password);

			HttpEntity<?> httpEntity = new HttpEntity<Object>(xlogin, headers);
			RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response = restTemplate.exchange("http://localhost:9080/cmsrest/v1/login_customer",HttpMethod.POST, httpEntity, String.class);
//			ResponseEntity<String> response = restTemplate.exchange(
//					"http://localhost:9080/cmsrest/v1/login_customer_local", HttpMethod.POST, httpEntity, String.class);

			if (response.getStatusCodeValue() == 200) {
				TOKENTWO = response.getHeaders().getFirst("Authorization");
				return TOKENTWO;
			} else {
				return null;
			}
		} catch (HttpClientErrorException ex) {
			return null;
		} catch (Exception ex) {
			return null;
		}
	}
}
