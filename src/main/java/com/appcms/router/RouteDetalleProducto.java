package com.appcms.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.appcms.entity.Scmenu;
import com.appcms.entity.Scsubmenu;
import com.appcms.model.DataServer;
import com.cms.errors.ViewRendererException;
import com.cms.views.ViewApp;

@Controller
public class RouteDetalleProducto {

	@Autowired
	private ViewApp viewApp;

	@Autowired
	private DataServer dtserver;

	@Autowired
	private CategoryHeaderSetter categoryHeaderSetter;

	@GetMapping("/categoria/{menu}/{submenu}/detalle/{producto}")
	public ModelAndView menuDetalleProducto(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,
			@PathVariable("producto") int producto) throws ViewRendererException {
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
		String html = viewApp.loadViews("HEAD", "HEADER_CATEGORIAS");
		switch (scmenuurlsub.getTipo()) {
		case 30:
			html += viewApp.loadViews("DETALLE-PRODUCTO-ECOMMERCE2");
			break;
		case 31:
			html += viewApp.loadViews("DETALLE-PRODUCTO-ECOMMERCE3");
			break;
		case 32:
			html += viewApp.loadViews("DETALLE-PRODUCTO-ECOMMERCE4");
			break;
		case 33:
			html += viewApp.loadViews("DETALLE-PRODUCTO-ECOMMERCE5");
			break;
		case 34:
			html += viewApp.loadViews("DETALLE-PRODUCTO-ECOMMERCE6");
			break;
		case 35:
			html += viewApp.loadViews("DETALLE-PRODUCTO-CATALOGO1");
			break;
		case 36:
			html += viewApp.loadViews("DETALLE-PRODUCTO-CATALOGO2");
			break;
		case 37:
			html += viewApp.loadViews("DETALLE-PRODUCTO-CATALOGO3");
			break;
		case 38:
			html += viewApp.loadViews("DETALLE-PRODUCTO-CATALOGO4");
			break;
		case 39:
			html += viewApp.loadViews("DETALLE-PRODUCTO-CATALOGO5");
			break;
		default:
			html += viewApp.loadViews("CANJES");
			break;
		}
		html += viewApp.loadViews("FOOTER");
		ModelAndView mav = new ModelAndView(html);
		scmenuurlsub.setProductosLikeLista(dtserver.loadProductosDetalle(producto));
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		categoryHeaderSetter.setHeaders(mav);
		return mav;
	}
}
