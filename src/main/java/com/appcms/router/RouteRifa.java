package com.appcms.router;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.appcms.entity.CustomerRewardResponse;
import com.appcms.entity.Rifa;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scsubmenu;
import com.appcms.model.DataServer;
import com.appcms.services.CustomerService;
import com.cms.errors.ViewRendererException;
import com.cms.views.ViewApp;

@Controller
public class RouteRifa {

	@Autowired
	private ViewApp viewApp;

	@Autowired
	private DataServer dtserver;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CategoryHeaderSetter categoryHeaderSetter;

	@GetMapping("/categoria/{menu}/{submenu}/rifa/seleccion")
	public ModelAndView seleccionarNumerosRifa(
			@PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu) throws ViewRendererException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (resolver.isAnonymous(auth)) {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + "/categoria/" + menu + "/" + submenu + "?login");
		}
		Scmenu scmenu = dtserver.loadScmenuByName(menu);
		Scsubmenu scmenuurlsub = new Scsubmenu();
		try {
			if (scmenu != null) {
				for (Scsubmenu scmenuurlsubtemp : scmenu.getSubmenues()) // buscamos el submenu que seleccionó
				{
					if (scmenuurlsubtemp.getStrIndex().equalsIgnoreCase(submenu)) {
						scmenuurlsub = scmenuurlsubtemp;
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		if (scmenuurlsub.getId() == 0) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		/* Se carga el HTML de la página */
		ModelAndView mav = new ModelAndView();
		String html = viewApp.loadViews("HEAD", "HEADER_CATEGORIAS");
		int tipoSubmenu = scmenuurlsub.getTipo();
		switch (tipoSubmenu) {
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
			// TIPO RIFA
			int tipoRifa = tipoSubmenu - 39;
			html += viewApp.loadViews("DETALLE-RIFA" + tipoRifa);
			mav.addObject("rifa", dtserver.loadSubmenuRifa(scmenuurlsub.getId()));
			break;
		default:
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		html += viewApp.loadViews("FOOTER");
		mav.setViewName(html);
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		categoryHeaderSetter.setHeaders(mav);
		return mav;
	}
	
	@PostMapping("/categoria/{menu}/{submenu}/rifa/condiciones")
	public ModelAndView aceptarCondicionesRifa(
			@PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu,
			@RequestParam int[] numeros) throws ViewRendererException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (resolver.isAnonymous(auth)) {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + "/categoria/" + menu + "/" + submenu + "?login");
		}
		Scmenu scmenu = dtserver.loadScmenuByName(menu);
		Scsubmenu scmenuurlsub = new Scsubmenu();
		try {
			if (scmenu != null) {
				for (Scsubmenu scmenuurlsubtemp : scmenu.getSubmenues()) // buscamos el submenu que seleccionó
				{
					if (scmenuurlsubtemp.getStrIndex().equalsIgnoreCase(submenu)) {
						scmenuurlsub = scmenuurlsubtemp;
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		if (scmenuurlsub.getId() == 0) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		/* Se carga el HTML de la página */
		ModelAndView mav = new ModelAndView();
		String html = viewApp.loadViews("HEAD", "HEADER_CATEGORIAS");
		int tipoSubmenu = scmenuurlsub.getTipo();
		switch (tipoSubmenu) {
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
			// TIPO RIFA
			int tipoRifa = tipoSubmenu - 39;
			html += viewApp.loadViews("CONDICIONES-RIFA" + tipoRifa);
			mav.addObject("rifa", dtserver.loadSubmenuRifa(scmenuurlsub.getId()));
			break;
		default:
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		html += viewApp.loadViews("FOOTER");
		mav.setViewName(html);
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		mav.addObject("numeros", numeros);
		categoryHeaderSetter.setHeaders(mav);
		return mav;
	}
	
	@PostMapping("/categoria/{menu}/{submenu}/rifa/confirmar")
	public ModelAndView confirmarRifa(
			@PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu,
			@RequestParam int[] numeros) throws ViewRendererException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (resolver.isAnonymous(auth)) {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + "/categoria/" + menu + "/" + submenu + "?login");
		}
		Scmenu scmenu = dtserver.loadScmenuByName(menu);
		Scsubmenu scmenuurlsub = new Scsubmenu();
		try {
			if (scmenu != null) {
				for (Scsubmenu scmenuurlsubtemp : scmenu.getSubmenues()) // buscamos el submenu que seleccionó
				{
					if (scmenuurlsubtemp.getStrIndex().equalsIgnoreCase(submenu)) {
						scmenuurlsub = scmenuurlsubtemp;
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		if (scmenuurlsub.getId() == 0) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		/* Se carga el HTML de la página */
		ModelAndView mav = new ModelAndView();
		String html = viewApp.loadViews("HEAD", "HEADER_CATEGORIAS");
		int tipoSubmenu = scmenuurlsub.getTipo();
		switch (tipoSubmenu) {
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
			// TIPO RIFA
			int tipoRifa = tipoSubmenu - 39;
			html += viewApp.loadViews("CONFIRMAR-RIFA" + tipoRifa);
			mav.addObject("rifa", dtserver.loadSubmenuRifa(scmenuurlsub.getId()));
			break;
		default:
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		html += viewApp.loadViews("FOOTER");
		mav.setViewName(html);
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		mav.addObject("numeros", numeros);
		categoryHeaderSetter.setHeaders(mav);
		return mav;
	}
	
	@PostMapping("/categoria/{menu}/{submenu}/rifa/canjear")
	public ModelAndView canjearRifa(
			@PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu,
			@RequestParam int[] numeros) throws ViewRendererException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (resolver.isAnonymous(auth)) {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + "/categoria/" + menu + "/" + submenu + "?login");
		}
		Scmenu scmenu = dtserver.loadScmenuByName(menu);
		Scsubmenu scmenuurlsub = new Scsubmenu();
		try {
			if (scmenu != null) {
				for (Scsubmenu scmenuurlsubtemp : scmenu.getSubmenues()) // buscamos el submenu que seleccionó
				{
					if (scmenuurlsubtemp.getStrIndex().equalsIgnoreCase(submenu)) {
						scmenuurlsub = scmenuurlsubtemp;
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		if (scmenuurlsub.getId() == 0) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		/* Se carga el HTML de la página */
		ModelAndView mav = new ModelAndView();
		String html = viewApp.loadViews("HEAD", "HEADER_CATEGORIAS");
		int tipoSubmenu = scmenuurlsub.getTipo();
		switch (tipoSubmenu) {
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
			// TIPO RIFA
			int tipoRifa = tipoSubmenu - 39;
			html += viewApp.loadViews("CANJE-RIFA" + tipoRifa);
	        List<Integer> numerosAsArray = Arrays.stream(numeros).boxed().collect(Collectors.toList());
	        CustomerRewardResponse customerRewardResponse = new CustomerRewardResponse();
	        if (numerosAsArray.contains(0)) {
	        	customerRewardResponse.setStatus("FAIL");
	        	customerRewardResponse.setMensaje("Al menos uno de los números seleccionados no es válido");
	        } else {
	        	Rifa rifa = dtserver.loadSubmenuRifa(scmenuurlsub.getId());
	        	customerRewardResponse = customerService.realizarCanjeTipoRifa(rifa.getIdRifa(), numeros);
	        }
	        mav.addObject("customerRewardResponse", customerRewardResponse);
			break;
		default:
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		html += viewApp.loadViews("FOOTER");
		mav.setViewName(html);
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		categoryHeaderSetter.setHeaders(mav);
		return mav;
	}
}
