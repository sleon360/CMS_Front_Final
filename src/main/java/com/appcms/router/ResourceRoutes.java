package com.appcms.router;

import javax.servlet.http.HttpServletRequest;
<<<<<<< HEAD

=======
import javax.servlet.http.HttpServletResponse;
>>>>>>> refs/heads/master
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
<<<<<<< HEAD
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
=======
>>>>>>> refs/heads/master
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import com.appcms.entity.ResourceEntity;
import com.appcms.services.GetRestService;

@Controller

public class ResourceRoutes {
	
    private final GetRestService greetingService;
	
	@Autowired
    public ResourceRoutes(GetRestService greetingService) {
        this.greetingService = greetingService;
    }

	@GetMapping("/resource/{folder}/{resourceid:.+}")
<<<<<<< HEAD
	public Object resource(@PathVariable("resourceid") String resourceName, @PathVariable("folder") String folder,
			HttpServletRequest request) {
=======
	public Object resource(@PathVariable("resourceid") String resourceName, @PathVariable("folder") String folder,HttpServletRequest request) {
>>>>>>> refs/heads/master
		try {
<<<<<<< HEAD
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ResourceEntity> xresponse = restTemplate
					.exchange(apiUrl + "/get/resource/" + folder + "?name={name}", HttpMethod.GET, HttpEntity.EMPTY, ResourceEntity.class, resourceName);
			if (xresponse.getStatusCode() == HttpStatus.OK) {
				ResourceEntity rEntity = xresponse.getBody();
				return ResponseEntity.ok().contentType(MediaType.parseMediaType(rEntity.getMime_resource()))
						.body(new ByteArrayResource(rEntity.getData()));
			} else {
				request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
				return new ModelAndView("redirect:/404");
			}
=======
			ResourceEntity rEntity=greetingService.getResouce(folder, resourceName);
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(rEntity.getMime_resource())).body(new ByteArrayResource(rEntity.getData()));
>>>>>>> refs/heads/master
		} catch (Exception ex) {
			request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
			return new ModelAndView("redirect:/404");
		}

	}
}
