package com.appcms.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.appcms.entity.Scmenu;
import com.appcms.entity.Scsubmenu;
import com.appcms.model.DataServer;
import com.appcms.services.CustomerService;
import com.cms.errors.ViewRendererException;
import com.cms.views.ViewApp;

@Controller
public class RouteCategoria {

	private final static Logger logger = LoggerFactory.getLogger(RouteCategoria.class);
	
	@Autowired
	private ViewApp viewApp;

	@Autowired
	private DataServer dtserver;
	
	@Autowired
	private CustomerService customerModel;
	
	@Autowired
	private CategoryHeaderSetter categoryHeaderSetter;
	
	@ExceptionHandler(ViewRendererException.class)
	public ResponseEntity<String> error(ViewRendererException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/categoria/{menu}/{submenu}")
	public ModelAndView menuSubmenu(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu) throws ViewRendererException {
		String html = viewApp.loadViews("head", "HEADER_CATEGORIAS");
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
		System.out.println("Tipo de submenú:" + scmenuurlsub.getTipo());
		switch (scmenuurlsub.getTipo()) {
		case 1:
			// TIPO INFORMACION
			html += viewApp.loadViews("CATEGORIAS");
			scmenuurlsub.setInformationsubmenu(dtserver.loadInformatioSub(scmenuurlsub.getId()));
			break;
		case 2:
			// TIPO PRODUCTOS CON LIKE
			html += viewApp.loadViews("CATEGORIAS");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosWithoutStock(scmenuurlsub.getId()));
			break;
		case 3:
			// TIPO REDIRECCION
			return new ModelAndView("redirect:" + scmenuurlsub.getLink());
		case 4:
			// TIPO PRODUCTO E-COMMERCE 1
			html += viewApp.loadViews("CATEGORIAS");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductos(scmenuurlsub.getId()));
			break;
		case 5:
			// TIPO CANJE CON CATEGORIAS
			html += viewApp.loadViews("CATEGORIAS");
			scmenuurlsub.setCategoriaProductoLista(dtserver.loadCateProductosFromCategoria(scmenuurlsub.getId()));
			break;
		case 6:
			// TIPO CANJE CON CATEGORIAS PARA FORMULARIO (TIPO INSCRIPCION)
			html += viewApp.loadViews("CATEGORIAS");
			scmenuurlsub.setCategoriaProductoLista(dtserver.loadScsubmenuRubros(scmenuurlsub.getId()));
			break;
		case 7:
			// TIPO CANJE CASHBACK
			html += viewApp.loadViews("CATEGORIAS");
			scmenuurlsub.setTarjetasCliente(customerModel.loadUserTarjetas().getTarjetasCliente());
			break;
		case 8:
			// TIPO CANJE DESCUENTOS
			html += viewApp.loadViews("CATEGORIAS");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosWithoutStock(scmenuurlsub.getId()));
			scmenuurlsub.setTagsProductos(dtserver.loadTagsProductos());
			break;
		case 9:
			// TIPO VISTA INFORMATION
			html += viewApp.loadViews("CATEGORIAS");
			scmenuurlsub.setInformationHtml(dtserver.loadInformationScsubmenu(scmenuurlsub.getId()));
			break;
		case 30:
			// TIPO PRODUCTO E-COMERCE 2
			html += viewApp.loadViews("CATEGORIA-ECOMMERCE2");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductos(scmenuurlsub.getId()));
			break;
		case 31:
			// TIPO PRODUCTO E-COMERCE 3
			html += viewApp.loadViews("CATEGORIA-ECOMMERCE3");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductos(scmenuurlsub.getId()));
			break;
		case 32:
			// TIPO PRODUCTO E-COMERCE 4
			html += viewApp.loadViews("CATEGORIA-ECOMMERCE4");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductos(scmenuurlsub.getId()));
			break;
		case 33:
			// TIPO PRODUCTO E-COMERCE 5
			html += viewApp.loadViews("CATEGORIA-ECOMMERCE5");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductos(scmenuurlsub.getId()));
			break;
		case 34:
			// TIPO PRODUCTO E-COMERCE 6
			html += viewApp.loadViews("CATEGORIA-ECOMMERCE6");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductos(scmenuurlsub.getId()));
			break;
		case 35:
			// TIPO CANJE POR CATÁLOGO 1
			html += viewApp.loadViews("CATEGORIA-CATALOGO1");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductos(scmenuurlsub.getId()));
			break;
		case 36:
			// TIPO CANJE POR CATÁLOGO 2
			html += viewApp.loadViews("CATEGORIA-CATALOGO2");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductos(scmenuurlsub.getId()));
			break;
		case 37:
			// TIPO CANJE POR CATÁLOGO 3
			html += viewApp.loadViews("CATEGORIA-CATALOGO3");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductos(scmenuurlsub.getId()));
			break;
		case 38:
			// TIPO CANJE POR CATÁLOGO 4
			html += viewApp.loadViews("CATEGORIA-CATALOGO4");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductos(scmenuurlsub.getId()));
			break;
		case 39:
			// TIPO CANJE POR CATÁLOGO 5
			html += viewApp.loadViews("CATEGORIA-CATALOGO5");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductos(scmenuurlsub.getId()));
			break;
		default:
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		
		html +=  viewApp.loadViews("FOOTER");
		ModelAndView mav = new ModelAndView(html);
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		categoryHeaderSetter.setHeaders(mav);
		return mav;
	}
}
