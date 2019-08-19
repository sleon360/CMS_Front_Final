package com.appcms.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.appcms.entity.CredencialesEntity;
import com.appcms.entity.customer.Customer;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	public CustomAuthenticationProvider() {
		super();
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

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
			
			if (customer != null) {
				System.out.println("PASOLOGIN");
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
		}

	}

	@Override
	public boolean supports(final Class authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
