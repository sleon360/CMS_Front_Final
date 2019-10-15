package com.appcms.router;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.appcms.entity.ResourceEntity;
import com.appcms.services.ResourceService;

@Controller
public class ResourceRoutes {

	@Autowired
	private ResourceService resourceService;

	@GetMapping("/resource/{folder}/{resourceid:.+}")
	public ResponseEntity<ByteArrayResource> resource(@PathVariable("resourceid") String resourceName,
			@PathVariable("folder") String folder,
			@RequestHeader(value = HttpHeaders.ACCEPT_ENCODING, required = false) String acceptEncoding) {
		ResourceEntity resourceEntity = resourceService.getResouce(folder, resourceName);
		if (acceptEncoding != null) {
			String[] values = acceptEncoding.replace(" ", "").split(",");
			List<String> acceptEncodingList = Arrays.asList(values);
			if (acceptEncodingList.contains("gzip")) {
				try {
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
					gzipOutputStream.write(resourceEntity.getData());
					gzipOutputStream.close();

					return ResponseEntity.ok().contentType(MediaType.parseMediaType(resourceEntity.getMime_resource()))
							.header(HttpHeaders.CONTENT_ENCODING, "gzip")
							.body(new ByteArrayResource(byteArrayOutputStream.toByteArray()));
				} catch (IOException e) {
					/* Si la compresión falla, se retorna el recurso sin comprimir */
				}
			}
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(resourceEntity.getMime_resource()))
				.body(new ByteArrayResource(resourceEntity.getData()));
	}

	@ExceptionHandler
	public ResponseEntity<String> error(Exception e) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
