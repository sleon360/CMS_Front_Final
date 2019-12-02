package com.appcms.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.cms.errors.ViewRendererException;
import com.cms.views.ViewApp;

@Controller
public class RouteErrors {

	@Autowired
	private ViewApp viewApp;
	
	@Autowired
	private CategoryHeaderSetter categoryHeaderSetter;
	
	@GetMapping("/error/{err}")
	public ModelAndView errorprint(@PathVariable("err") int err) throws ViewRendererException {
		String errorMsg = "Error al procesar la solicitud.";
		int clean = 0;
		int httpErrorCode = err;
		try {
			switch (httpErrorCode) {
			case 400: {
				errorMsg = "Http Error Code: 400. Bad Request";
				break;
			}
			case 401: {
				errorMsg = "Http Error Code: 401. Unauthorized";
				break;
			}
			case 403: {
				errorMsg = "Http Error Code: 403. Forbidden";
				clean = 1;
				break;
			}
			case 404: {
				errorMsg = "Http Error Code: 404. Resource not found";
				break;
			}
			case 500: {
				errorMsg = "Http Error Code: 500. Internal Server Error";
				break;
			}
			}
		} catch (Exception ex) {
			errorMsg = "Http Error Code: 500. Internal Server Error";
		}
		ModelAndView mav = new ModelAndView(viewApp.loadViews("head", "error", "footer"));
		mav.addObject("titulo_error", httpErrorCode);
		mav.addObject("descripcion_error", errorMsg);
		mav.addObject("clean", clean);
		categoryHeaderSetter.setHeaders(mav);
		return mav;
	}
}
