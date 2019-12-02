package com.appcms.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.ModelAndView;

import com.appcms.services.CustomerService;

public class RouteDespegar {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/despegar")
	public ModelAndView getDespegarLink(@RequestHeader(value = "referer", required = false) final String referer) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			String despegarLink = customerService.getDespegarLink().replace("\n", "").replace("\r", "");
			;
			return new ModelAndView("redirect:" + despegarLink);
		} else {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}
	}
	
}
