package com.appcms.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.appcms.entity.ResourceEntity;
import com.appcms.services.ResourceService;

@Controller
public class ResourceRoutes {
	
	@Autowired
    private ResourceService resourceService;

	@GetMapping("/resource/{folder}/{resourceid:.+}")
	public ResponseEntity<ByteArrayResource> resource(@PathVariable("resourceid") String resourceName, @PathVariable("folder") String folder) {
		ResourceEntity resourceEntity =  resourceService.getResouce(folder, resourceName);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(resourceEntity.getMime_resource()))
				.body(new ByteArrayResource(resourceEntity.getData()));
	}
	
}
