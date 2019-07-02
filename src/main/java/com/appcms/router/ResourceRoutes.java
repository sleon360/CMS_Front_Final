package com.appcms.router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
	public Object resource(@PathVariable("resourceid") String resourceid, @PathVariable("folder") String folder,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.set("Authorization", request.getSession().getAttribute("TOKENONE").toString());
			MultiValueMap<String, String> xparam = new LinkedMultiValueMap<String, String>();
			xparam.add("idresource", resourceid);
			HttpEntity<?> httpEntity = new HttpEntity<Object>(xparam, headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ResourceEntity> xresponse = restTemplate
					.postForEntity(apiUrl + "/resource/" + folder + "/get", httpEntity, ResourceEntity.class);

			if (xresponse.getStatusCode() == HttpStatus.OK) {
				ResourceEntity rEntity = xresponse.getBody();
				return ResponseEntity.ok().contentType(MediaType.parseMediaType(rEntity.getMime_resource()))
						// .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
						// rEntity.getNombre_resource() + "\"")
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
