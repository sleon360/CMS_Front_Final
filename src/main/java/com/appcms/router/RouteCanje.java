package com.appcms.router;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.appcms.entity.CanjeProducto;
import com.appcms.entity.CustomerRewardResponse;
import com.appcms.entity.ProductoCategoria;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scsubmenu;
import com.appcms.model.DataServer;
import com.appcms.services.CustomerService;
import com.cms.errors.ViewRendererException;
import com.cms.views.ViewApp;

@Controller
public class RouteCanje {

	@Autowired
	private ViewApp viewApp;

	@Autowired
	private DataServer dtserver;

	@Autowired
	private CustomerService customerModel;

	@Autowired
	private CategoryHeaderSetter categoryHeaderSetter;
	
	@PostMapping("/categoria/{menu}/{submenu}/canje")
	public ModelAndView menuCanje(@ModelAttribute("producto") CanjeProducto producto, @PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu,
			@RequestHeader(value = "referer", required = false) final String referer, HttpServletRequest request)
			throws ViewRendererException {
		producto.setCantidad(1);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (resolver.isAnonymous(auth)) {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}
		ModelAndView mav = new ModelAndView();
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
		String html = viewApp.loadViews("HEAD", "HEADER_CATEGORIAS");
		switch (scmenuurlsub.getTipo()) {
		case 1:
			// TIPO INFORMACION
			html += viewApp.loadViews("CANJES");
			scmenuurlsub.setTipo(0);			
			break;
		case 2: // TIPO PRODUCTO CON LIKE
			html += viewApp.loadViews("CANJES");
			scmenuurlsub.setTipo(0);
			break;
		case 3: // TIPO CON CUPON
			// efectuar canje, datos en objeto "producto"
			html += viewApp.loadViews("CANJES");
			mav.addObject("canjeExito", true);
			break;
		case 4: // TIPO PRODUCTO E-COMERCE
			html += viewApp.loadViews("CANJES");
			CustomerRewardResponse exchangeResponse = customerModel.realizarCanje(producto.getIdProducto());
			if (exchangeResponse.getStatus().equals("OK")) {
				mav.addObject("canjeExito", true);
			} else {
				mav.addObject("canjeExito", false);
				mav.addObject("errorMessage", exchangeResponse.getMensaje());
			}
			break;
		case 5: // TIPO CANJE CON CATEGORIAS
			html += viewApp.loadViews("CANJES");
			// Se valida el nombre del beneficiario
			String nombreBeneficiario = producto.getNombreAsociado();
			String rutBeneficiario = producto.getRutAsociado();
			if (nombreBeneficiario == null && rutBeneficiario == null) {
				/* Si el nombre del beneficiario y su RUT son nulos, el canje es no nominativo*/
				CustomerRewardResponse exchangeCategoryResponse = customerModel.realizarCanje(producto.getIdProducto());
				if (exchangeCategoryResponse.getStatus().equals("OK")) {
					mav.addObject("canjeExito", true);
				} else {
					mav.addObject("canjeExito", false);
					mav.addObject("errorMessage", exchangeCategoryResponse.getMensaje());
				}				
			} else {
				/* Si el nombre del beneficiario y su RUT no son nulos, el canje es nominativo y hay
				 * que validar los datos del beneficiario */
				// Se valida el nombre del beneficiario
				if (StringUtils.isEmptyOrWhitespace(nombreBeneficiario)) {
					mav.addObject("canjeExito", false);
					mav.addObject("errorMessage", "El nombre del beneficiario no puede estar en blanco");
					break;
				}
				// Se valida el RUT del beneficiario
				if (StringUtils.isEmptyOrWhitespace(rutBeneficiario)) {
					mav.addObject("canjeExito", false);
					mav.addObject("errorMessage", "El RUT del beneficiario no puede estar en blanco");
					break;
				} else {
					if (!rutBeneficiario.matches("^\\d{8}-(\\d|k|K)$")) {
						mav.addObject("canjeExito", false);
						mav.addObject("errorMessage", "El RUT del beneficiario no es válido. Debe ser de la forma XXXXXXXX-X (sin puntos, con guión");
						break;
					}
				}
				CustomerRewardResponse exchangeCategoryResponse = customerModel.realizarCanje(producto.getIdProducto(),
						nombreBeneficiario, rutBeneficiario);
				if (exchangeCategoryResponse.getStatus().equals("OK")) {
					mav.addObject("canjeExito", true);
				} else {
					mav.addObject("canjeExito", false);
					mav.addObject("errorMessage", exchangeCategoryResponse.getMensaje());
				}
			}
			break;
		case 6:
			// TIPO INCRIPCIÓN DE PUNTOS
			html += viewApp.loadViews("CANJES");
			if (producto.getActionx().equalsIgnoreCase("finish")) {
				// Se valida el monto a inscribir y la tarjeta de cliente
				int monto = producto.getMonto();
				String cardKey = producto.getCardKey();
				String cardNumber = producto.getCardNumber();
				if (monto <= 0) {
					mav.addObject("canjeExito", false);
					mav.addObject("errorMessage", "La cantidad de puntos a inscribir no es válida");
				} else if (StringUtils.isEmptyOrWhitespace(cardKey)) {
					mav.addObject("canjeExito", false);
					mav.addObject("errorMessage", "La tarjeta seleccionada no es válida");
				} else {
					boolean exito = customerModel.inscribirPuntos(producto.getIdProducto(), cardKey, cardNumber, monto);
					if (exito) {
						mav.addObject("canjeExito", true);
					} else {
						mav.addObject("canjeExito", false);
						mav.addObject("errorMessage", "Hubo un error inscribiendo puntos");
					}
				}
			} else {
				producto.setActionx("finish");
			}
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosDetalle(producto.getIdProducto()));
			ProductoCategoria categoriaProducto = dtserver.loadProductoCategoria(producto.getIdProducto());
			if (categoriaProducto != null) {
				scmenuurlsub.getProductosLikeLista().get(0).setImagen(categoriaProducto.getImagen());
				scmenuurlsub.getProductosLikeLista().get(0).setNombre(categoriaProducto.getNombre());
			}
			mav.addObject("producto", producto);
			break;
		case 7: // TIPO CANJE CASHBACK
			break;
		case 8:
			// TIPO CANJE DESCUENTOS
			html += viewApp.loadViews("CANJES");
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosDetalle(producto.getIdProducto()));
			CustomerRewardResponse exchangeDirectlyResponse = customerModel
					.realizarCanjeDirecto(producto.getIdProducto());
			if (exchangeDirectlyResponse.getStatus().equals("OK")) {
				mav.addObject("producto", producto);
				mav.addObject("canjeExito", true);
			} else {
				mav.addObject("canjeExito", false);
				mav.addObject("errorMessage", exchangeDirectlyResponse.getMensaje());
			}
			break;
		case 30:
			// TIPO CANJE ECOMMERCE2
			html += viewApp.loadViews("CANJE-ECOMMERCE2");
			CustomerRewardResponse exchangeResponseEcommerce2 = customerModel.realizarCanje(producto.getIdProducto());
			if (exchangeResponseEcommerce2.getStatus().equals("OK")) {
				mav.addObject("canjeExito", true);
			} else {
				mav.addObject("canjeExito", false);
				mav.addObject("errorMessage", exchangeResponseEcommerce2.getMensaje());
			}
			break;
		case 31:
			// TIPO CANJE ECOMMERCE3
			html += viewApp.loadViews("CANJE-ECOMMERCE3");
			CustomerRewardResponse exchangeResponseEcommerce3 = customerModel.realizarCanje(producto.getIdProducto());
			if (exchangeResponseEcommerce3.getStatus().equals("OK")) {
				mav.addObject("canjeExito", true);
			} else {
				mav.addObject("canjeExito", false);
				mav.addObject("errorMessage", exchangeResponseEcommerce3.getMensaje());
			}
			break;
		case 32:
			// TIPO CANJE ECOMMERC4
			html += viewApp.loadViews("CANJE-ECOMMERCE4");
			CustomerRewardResponse exchangeResponseEcommerce4 = customerModel.realizarCanje(producto.getIdProducto());
			if (exchangeResponseEcommerce4.getStatus().equals("OK")) {
				mav.addObject("canjeExito", true);
			} else {
				mav.addObject("canjeExito", false);
				mav.addObject("errorMessage", exchangeResponseEcommerce4.getMensaje());
			}
			break;
		case 33:
			// TIPO CANJE ECOMMERCE5
			html += viewApp.loadViews("CANJE-ECOMMERCE5");
			CustomerRewardResponse exchangeResponseEcommerce5 = customerModel.realizarCanje(producto.getIdProducto());
			if (exchangeResponseEcommerce5.getStatus().equals("OK")) {
				mav.addObject("canjeExito", true);
			} else {
				mav.addObject("canjeExito", false);
				mav.addObject("errorMessage", exchangeResponseEcommerce5.getMensaje());
			}
			break;
		case 34:
			// TIPO CANJE ECOMMERCE6
			html += viewApp.loadViews("CANJE-ECOMMERCE6");
			CustomerRewardResponse exchangeResponseEcommerce6 = customerModel.realizarCanje(producto.getIdProducto());
			if (exchangeResponseEcommerce6.getStatus().equals("OK")) {
				mav.addObject("canjeExito", true);
			} else {
				mav.addObject("canjeExito", false);
				mav.addObject("errorMessage", exchangeResponseEcommerce6.getMensaje());
			}
			break;
		case 35:
		case 36:
		case 37:
		case 38:
		case 39:
			// TIPO CANJE POR CATÁLOGO
			int tipoCatalogo = scmenuurlsub.getTipo() - 34;
			html += viewApp.loadViews("CANJE-CATALOGO" + tipoCatalogo);
			CustomerRewardResponse exchangeByCatalogProduct = customerModel
					.realizarCanjePorCatalogo(producto);
			if (exchangeByCatalogProduct.getStatus().equals("OK")) {
				mav.addObject("producto", producto);
				mav.addObject("canjeExito", true);
			} else {
				mav.addObject("canjeExito", false);
				mav.addObject("errorMessage", exchangeByCatalogProduct.getMensaje());
			}
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
