package com.appcms.security;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.NestedServletException;

@Component
public class RestAuthentication {

	private static String TOKENONE;
	private static String TOKENTWO;

	public RestAuthentication() {

	}

	public String getTOKENONE() {
		return TOKENONE;
	}

	public static void setTOKENONE(String tOKENONE) {
		TOKENONE = tOKENONE;
	}

	public String getTOKENTWO() {
		return TOKENTWO;
	}

	public static void setTOKENTWO(String tOKENTWO) {
		TOKENTWO = tOKENTWO;
	}

	static boolean RestAutenticationLayerOne(HttpServletRequest rq, HttpServletResponse response, String apiUrl)
			throws NestedServletException, IOException {
		// RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		try {
			if (rq.getSession().getAttribute("TOKENONE") == null) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<String>("{\"username\":\"spring\",\"password\":\"secret\"}",headers);
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> xresponse = restTemplate.postForEntity(apiUrl + "/login", entity, String.class);

				System.out.println(xresponse.getHeaders());
				String result = xresponse.getBody();
				System.out.println(" XXXXXBBBBB " + xresponse.getHeaders().getFirst("Authorization") + " - " + result);
				if (xresponse.getStatusCodeValue() == 200) {
					setTOKENONE(xresponse.getHeaders().getFirst("Authorization").replace("Bearer ", ""));
					rq.getSession().setAttribute("TOKENONE",xresponse.getHeaders().getFirst("Authorization").replace("Bearer ", ""));
					return true;
				} else {

					// redirectStrategy.sendRedirect(rq, response, "/errores");
					// return false;
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println("Excepción: " + ex.getMessage());
			// redirectStrategy.sendRedirect(rq, response, "/errores");
			// return false;
			return true;
		}

		// redirectStrategy.sendRedirect(rq, response, "/errores");
		// return true;
		return true;

	}
	
	
	
	public String RestAutenticationLayerOne2(String apiUrl)
			throws NestedServletException, IOException {
		// RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		try {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> entity = new HttpEntity<String>("{\"username\":\"spring\",\"password\":\"secret\"}",headers);
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> xresponse = restTemplate.postForEntity(apiUrl + "/login", entity, String.class);
				System.out.println(xresponse.getHeaders());
				if (xresponse.getStatusCodeValue() == 200) {
					setTOKENONE(xresponse.getHeaders().getFirst("Authorization").replace("Bearer ", ""));
					return xresponse.getHeaders().getFirst("Authorization").replace("Bearer ", "");
				} else {
					return "0";
				}
		} catch (Exception ex) {
			return "0";
		}

	}

}
