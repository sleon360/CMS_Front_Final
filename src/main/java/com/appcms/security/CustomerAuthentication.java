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

import com.appcms.entity.CustomerEntity;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.customer.Customer;
import com.appcms.model.ConfigJNDIModel;

@Component
public class CustomerAuthentication {

	static Customer CustomerAuth(String username, String password, String token) {
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
			ResponseEntity<CustomerEntity> response = restTemplate.exchange(
					ConfigJNDIModel.getVar("apiURL")+"/cmsrest/v1/login_customer", HttpMethod.POST, httpEntity,
					CustomerEntity.class);
			TOKENTWO = response.getHeaders().getFirst("Authorization");
			CustomerEntity customerEntity = response.getBody();
			Scotiauser scotiauser = new Scotiauser(customerEntity.getId(), customerEntity.getRut(),
					customerEntity.getPrimerNombre(), customerEntity.getPrimerApellido(), customerEntity.getEmail(),
					customerEntity.getIdCustomerGroupFK());
			return new Customer(TOKENTWO, scotiauser);
		} catch (HttpClientErrorException ex) {
			return null;
		} catch (Exception ex) {
			return null;
		}
	}
}
