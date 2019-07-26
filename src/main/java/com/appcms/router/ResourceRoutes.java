package com.appcms.router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.appcms.entity.ResourceEntity;

@Controller
public class ResourceRoutes {

	private String apiUrl;

	@Autowired
	public ResourceRoutes(@Qualifier("apiUrl") String apiUrl) {
		this.apiUrl = apiUrl;
	}

	@GetMapping("/resource/{folder}/{resourceid:.+}")
	public Object resource(@PathVariable("resourceid") String resourceName, @PathVariable("folder") String folder,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", request.getSession().getAttribute("TOKENONE").toString());
			
			HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ResourceEntity> xresponse = restTemplate
					.exchange(apiUrl + "/resource/" + folder + "/get?name={name}", HttpMethod.GET, httpEntity, ResourceEntity.class, resourceName);
			if (xresponse.getStatusCode() == HttpStatus.OK) {
				ResourceEntity rEntity = xresponse.getBody();
				return ResponseEntity.ok().contentType(MediaType.parseMediaType(rEntity.getMime_resource()))
						.body(new ByteArrayResource(rEntity.getData()));
			} else {
				request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
				return new ModelAndView("redirect:/404");
			}
		} catch (Exception ex) {
			request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
			return new ModelAndView("redirect:/404");
		}

	}
}
