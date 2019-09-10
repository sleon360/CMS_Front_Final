package com.appcms.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.appcms.entity.CustomerEntity;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.customer.Customer;
import com.cms.errors.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private String apiUrl;

	@Autowired
	public CustomAuthenticationProvider(@Qualifier("apiUrl") String apiUrl) {
		this.apiUrl = apiUrl;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
<<<<<<< HEAD
		String user = authentication.getName();
		String password = authentication.getCredentials().toString();
		if (!user.matches("^\\d{8}-(\\d|k|K)$")) {
			throw new BadCredentialsException("RUT no válido, debe ser de la forma XXXXXXXX-X");
=======

		try {
			// Map<String, Object> info = (Map<String,
			// Object>)SecurityContextHolder.getContext().getAuthentication().getDetails();
			/// String tok = (String) info.get("TOKENONE");

			System.out.println(
					authentication.getDetails() + " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBB");

			/// String name = authentication.getName();
			// String password = authentication.getCredentials().toString();
			System.out.println(authentication.getName() + " - " + authentication.getCredentials() + " TESTTTTTT!!! "
					+ authentication.getName().equals("admin") + " - "
					+ authentication.getCredentials().equals("admin"));

			CredencialesEntity credenciales = (CredencialesEntity) authentication.getCredentials();
			// System.out.println(CustomerAuthentication.CustomerAuth(authentication.getName(),
			// authentication.getCredentials().toString(), ""));
			Customer customer = CustomerAuthentication
					.CustomerAuth(credenciales.getUserName(), credenciales.getPassword(), credenciales.getTOKENONE());
			
			System.out.println("TokenTwo: " + customer.getJwt().replace("Bearer ", ""));
			if (customer != null) {
				System.out.println("PASOLOGIN+++++++++++++++++++++++++++++++");
				List<SimpleGrantedAuthority> grantedAuths = new ArrayList<>();
				// grantedAuths.add(new SimpleGrantedAuthority("USER"));
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				// return createSuccessAuthentication(authentication);

				// UsernamePasswordAuthenticationToken token = new
				// UsernamePasswordAuthenticationToken(authentication.getName(),
				// authentication.getCredentials());
				// Authentication auth = new UsernamePasswordAuthenticationToken
				// (authentication.getName(), authentication.getCredentials(), grantedAuths);
				// auth.setAuthenticated(true);
				// return auth;
				// return new UsernamePasswordAuthenticationToken(authentication.getName(),
				// authentication.getCredentials(), grantedAuths);
				credenciales.setTOKENTWO(customer.getJwt());
				System.out.println("Usuario autenticado, scotiauser: " + customer.getScotiauser().toString());
				credenciales.setScotiauser(customer.getScotiauser());
				final Authentication auth = new UsernamePasswordAuthenticationToken(credenciales,
						credenciales.getPassword(), grantedAuths);

				// System.out.println("Authentication: " +
				// SecurityContextHolder.getContext().getAuthentication());
				// SecurityContextHolder.getContext().setAuthentication(auth);
				// System.out.println("Authentication: " +
				// SecurityContextHolder.getContext().getAuthentication());
				return auth;

			} else {
				System.out.println("No pasó login");
				return null;
			}
		} catch (Exception ex) {
			return null;
>>>>>>> refs/heads/master
		}
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
		multiValueMap.add("userCostumer", user);
		multiValueMap.add("userPassword", password);
		
		HttpEntity<?> httpEntity = new HttpEntity<Object>(multiValueMap, headers);
		try {
			ResponseEntity<CustomerEntity> responseEntity = restTemplate.exchange(apiUrl + "/v1/login_customer", HttpMethod.POST,
					httpEntity, CustomerEntity.class);
			CustomerEntity customerEntity = responseEntity.getBody();
			String jwt = responseEntity.getHeaders().getFirst("Authorization").replace("Bearer ", "");
			
			Scotiauser scotiauser = new Scotiauser(customerEntity.getId(), customerEntity.getRut(),
					customerEntity.getPrimerNombre(), customerEntity.getPrimerApellido(), customerEntity.getEmail(),
					customerEntity.getIdCustomerGroupFK());
			Customer customer = new Customer(jwt, scotiauser);
			return new UsernamePasswordAuthenticationToken(customer, password, AuthorityUtils.createAuthorityList("ROLE_CUSTOMER"));
		} catch(HttpClientErrorException e) {
			try {
				ApiError apiError = new ObjectMapper().readValue(e.getResponseBodyAsString(), ApiError.class);
				throw new BadCredentialsException(apiError.getMessage());
			} catch (IOException e1) {
				throw new AuthenticationServiceException("Error de inicio de sesión");
			}			
		} catch(Exception e) {
			throw new AuthenticationServiceException("Error de inicio de sesión");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
