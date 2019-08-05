package com.appcms.router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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
	public Object resource(@PathVariable("resourceid") String resourceName, @PathVariable("folder") String folder,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			ResourceEntity rEntity=greetingService.getResouce(folder, resourceName, request.getSession().getAttribute("TOKENONE").toString());
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(rEntity.getMime_resource())).body(new ByteArrayResource(rEntity.getData()));
		} catch (Exception ex) {
			request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
			return new ModelAndView("redirect:/404");
		}

	}
}
