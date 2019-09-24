package com.appcms.router;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;
import org.thymeleaf.util.StringUtils;

import com.appcms.entity.CanjeProducto;
import com.appcms.entity.CustomerInscripcion;
import com.appcms.entity.CustomerRewardResponse;
import com.appcms.entity.Information;
import com.appcms.entity.ProductoCategoria;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.Scsubmenu;
import com.appcms.entity.UserCupon;
import com.appcms.entity.UserGusto;
import com.appcms.entity.customer.Customer;
import com.appcms.model.DataServer;
import com.appcms.services.CustomerService;
import com.cms.views.ViewApp;

@Controller
public class Routes {

	@Autowired
	private ViewApp viewApp;

	@Autowired
	private DataServer dtserver;

	@Autowired
	private CustomerService customerModel;

	public void setHeaderx(ModelAndView mav) {
		mav.addObject("menuesHeader", dtserver.loadAllScmenu());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			Customer customer = (Customer) auth.getPrincipal();
			mav.addObject("usuario", customer.getScotiauser());
			mav.addObject("points", customerModel.loadUserPoints());
			// Se agregan los gustos del usuario
			List<UserGusto> gustos = dtserver.loadGustos();
			List<UserGusto> gustosCliente = customerModel.loadCustomerGustos();
			for (int i = 0; i < gustos.size(); i++) {
				UserGusto gusto = gustos.get(i);
				for (int j = 0; j < gustosCliente.size(); j++) {
					UserGusto gustoCliente = gustosCliente.get(j);
					if (gusto.getId() == gustoCliente.getId()) {
						gusto.setGustado(true);
						break;
					}
				}
			}
			mav.addObject("gustos", gustos);
		} else {
			mav.addObject("usuario", new Scotiauser());
		}
	}

	@ExceptionHandler(value = { Exception.class, MultipartException.class, NestedServletException.class,
			NestedServletException.class, RequestRejectedException.class })
	@GetMapping("/errores")
	public String error(HttpServletRequest rq) {
		try {
			int code = (Integer) rq.getAttribute("javax.servlet.error.status_code");
			return "redirect:/error/" + code;
		} catch (Exception ex) {
			return "redirect:/error/500";
		}

	}

	@GetMapping("/error/{err}")
	public ModelAndView errorprint(@PathVariable("err") int err) {
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
		this.setHeaderx(mav);
		return mav;
	}

	@GetMapping("/")
	public ModelAndView home(HttpServletRequest rq) {
		ModelAndView mav = new ModelAndView(viewApp.loadViews("head", "index", "footer"));
		mav.addObject("banners", dtserver.loadBannerAll(0));
		mav.addObject("banners_resp", dtserver.loadBannerAll(1));
		mav.addObject("descuentos_destacados", dtserver.loadscmenuinformationFomScmenu(10));
		this.setHeaderx(mav);
		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}")
	public ModelAndView menuSubmenu(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu) {
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
			scmenuurlsub.setInformationsubmenu(dtserver.loadInformatioSub(scmenuurlsub.getId()));
			break;
		case 2:
			// TIPO PRODUCTOS CON LIKE
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosLike(scmenuurlsub.getId()));
			break;
		case 3:
			// TIPO CON CUPON
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosLike(scmenuurlsub.getId()));
			break;
		case 4:
			// TIPO PRODUCTO E-COMERCE
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosLike(scmenuurlsub.getId()));
			break;
		case 5:
			// TIPO CANJE CON CATEGORIAS
			scmenuurlsub.setCategoriaProductoLista(dtserver.loadCateProductosFromCategoria(scmenuurlsub.getId()));
			break;
		case 6:
			// TIPO CANJE CON CATEGORIAS PARA FORMULARIO (TIPO INSCRIPCION)
			scmenuurlsub.setCategoriaProductoLista(dtserver.loadScsubmenuRubros(scmenuurlsub.getId()));
			break;
		case 7:
			// TIPO CANJE CASHBACK
			scmenuurlsub.setTarjetasCliente(customerModel.loadUserTarjetas().getTarjetasCliente());
			break;
		case 8:
			// TIPO CANJE DESCUENTOS
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosLike(scmenuurlsub.getId()));
			scmenuurlsub.setTagsProductos(dtserver.loadTagsProductos());
			break;
		case 9:
			// TIPO VISTA INFORMATION
			scmenuurlsub.setInformationHtml(dtserver.loadInformationScsubmenu(scmenuurlsub.getId()));
			break;
		default:
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		String html = viewApp.loadViews("head", "HEADER_CATEGORIAS", "CATEGORIAS", "footer");
		ModelAndView mav = new ModelAndView(html);
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		this.setHeaderx(mav);
		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}/productos/{categoria}")
	public ModelAndView menuProductoCategoria(@PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu, @PathVariable("categoria") String categoria,
			@RequestHeader(value = "referer", required = false) final String referer) {
		ModelAndView mav = new ModelAndView(viewApp.loadViews("head", "HEADER_CATEGORIAS", "CATEGORIAS", "footer"));
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

		switch (scmenuurlsub.getTipo()) { // SI O SI TIENE QUE SER SUB CATEGORIA TIPO 5 o 6 PARA TENER PRODUCTOS A LA
											// CATEGORIAPRODUCTOS ASOCIADA
		case 5:
			// TIPO CANJE CON CATEGORIAS
			scmenuurlsub.setCategoriaProductoLista(
					dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(), categoria));
			mav.addObject("verProductosCategoria", true);
			break;

		case 6:
			// TIPO INSCRIPCIÓN DE PUNTOS
			// se pasa la categoria para seleccionar el primer producto de ella, deberia
			// siempre tener 1 producto MAXIMO por categoria tipo formulario

			// Si el usuario no se ha autenticado, no debería avanzarse ya que se requieren
			// los puntos y las tarjetas del cliente
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
			if (resolver.isAnonymous(auth)) {
				// Para evitar que una página quede como ?login?login se hace el replace
				return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
			}

			scmenuurlsub
					.setProductosLikeLista(dtserver.loadProductosLikeSubmenuCategoria(scmenuurlsub.getId(), categoria));
			mav.addObject("producto", new CanjeProducto());
			mav.addObject("verProductosCategoria", true);

			// Se agregan los puntos de cliente
			mav.addObject("puntosDisponibles", customerModel.loadUserPoints().getAvailablePoints());
			// Se agregan las tarjetas del cliente
			scmenuurlsub.setTarjetasCliente(customerModel.loadUserTarjetas().getTarjetasCliente());
			break;
		case 8:
			// TIPO CANJE CON CATEGORIAS
			scmenuurlsub.setCategoriaProductoLista(
					dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(), categoria));
			mav.addObject("verProductosCategoria", true);
			break;
		default:
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		this.setHeaderx(mav);

		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}/detalle/{producto}")
	public ModelAndView menuDetalleProducto(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,
			@PathVariable("producto") int producto) {
		ModelAndView mav = new ModelAndView(viewApp.loadViews("HEAD", "HEADER_CATEGORIAS", "CANJES", "FOOTER"));
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

		scmenuurlsub.setProductosLikeLista(dtserver.loadProductosDetalle(producto));
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		this.setHeaderx(mav);
		return mav;
	}

	@PostMapping("/categoria/{menu}/{submenu}/canje")
	public ModelAndView menuCanje(@ModelAttribute("producto") CanjeProducto producto, @PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu,
			@RequestHeader(value = "referer", required = false) final String referer, HttpServletRequest request)
			throws ServletException {
		producto.setCantidad(1);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (resolver.isAnonymous(auth)) {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}

		ModelAndView mav = new ModelAndView(viewApp.loadViews("HEAD", "HEADER_CATEGORIAS", "CANJES", "FOOTER"));
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
		switch (scmenuurlsub.getTipo()) {
		case 1:
			// TIPO INFORMACION
			scmenuurlsub.setTipo(0);
			break;
		case 2: // TIPO PRODUCTO CON LIKE
			scmenuurlsub.setTipo(0);
			break;
		case 3: // TIPO CON CUPON
			// efectuar canje, datos en objeto "producto"
			mav.addObject("canjeExito", true);
			break;
		case 4: // TIPO PRODUCTO E-COMERCE
			CustomerRewardResponse exchangeResponse = customerModel.realizarCanje(producto.getIdProducto());
			if (exchangeResponse.getStatus().equals("OK")) {
				mav.addObject("canjeExito", true);
			} else {
				mav.addObject("canjeExito", false);
				mav.addObject("errorMessage", exchangeResponse.getMensaje());
			}
			break;
		case 5: // TIPO CANJE CON CATEGORIAS
			// Se valida el nombre del beneficiario
			String nombreBeneficiario = producto.getNombreAsociado();
			if (nombreBeneficiario == null) {
				mav.addObject("canjeExito", false);
				mav.addObject("errorMessage", "El nombre del beneficiario no puede estar en blanco");
				break;
			} else {
				if (StringUtils.isEmptyOrWhitespace(nombreBeneficiario)) {
					mav.addObject("canjeExito", false);
					mav.addObject("errorMessage", "El nombre del beneficiario no puede estar en blanco");
					break;
				}
			}
			// Se valida el RUT del beneficiario
			String rutBeneficiario = producto.getRutAsociado();
			if (rutBeneficiario == null) {
				mav.addObject("canjeExito", false);
				mav.addObject("errorMessage", "El RUT del beneficiario no puede estar en blanco");
				break;
			} else {
				if (StringUtils.isEmptyOrWhitespace(rutBeneficiario)) {
					mav.addObject("canjeExito", false);
					mav.addObject("errorMessage", "El RUT del beneficiario no puede estar en blanco");
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
			break;
		case 6:
			// TIPO INCRIPCIÓN DE PUNTOS
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
			System.out.println("TIPO 8");
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
		default:
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		this.setHeaderx(mav);
		return mav;
	}

	@GetMapping("/user/{menu}/{submenu}")
	public ModelAndView menuUser(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,
			@RequestHeader(value = "referer", required = false) final String referer) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (resolver.isAnonymous(auth)) {
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}
		String html = viewApp.loadViews("HEAD", "HEADER_CATEGORIAS");
		Scmenu scmenu = dtserver.loadScmenuByName(menu);
		Scsubmenu scmenuurlsub = new Scsubmenu();

		ModelAndView mav = null;
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

		switch (scmenuurlsub.getTipo()) {
		case 20: // information
			// TIPO MI CARTOLA
			html += viewApp.loadViews("MI-CARTOLA", "FOOTER");
			mav = new ModelAndView(html);
			mav.addObject("cartola", customerModel.loadUserCartola());
			break;
		case 21: // information
			// TIPO INSCRIPCCION
			html += viewApp.loadViews("mis-inscripciones", "FOOTER");
			mav = new ModelAndView(html);
			List<CustomerInscripcion> inscripciones = customerModel.loadUserInscripciones();
			Date fechaActual = new Date();
			for (CustomerInscripcion customerInscripcion : inscripciones) {
				Date fechaVencimiento = customerInscripcion.getFechaVencimiento();
				if (fechaVencimiento == null) {
					customerInscripcion.setVencido(false);
				} else {
					if (fechaVencimiento.before(fechaActual)) {
						customerInscripcion.setVencido(true);
					} else {
						customerInscripcion.setVencido(false);
					}
				}
			}
			mav.addObject("inscripciones", inscripciones);
			break;
		case 22: // information
			// TIPO MIS CUPONES
			html += viewApp.loadViews("mis-cupones", "FOOTER");
			mav = new ModelAndView(html);
			List<UserCupon> cupones = customerModel.loadCupones();
			List<UserCupon> giftCards = new ArrayList<UserCupon>();
			List<UserCupon> entradasCine = new ArrayList<UserCupon>();
			List<UserCupon> panoramas = new ArrayList<UserCupon>();
			SimpleDateFormat currentDateFormatCupones = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat newDateFormatCupones = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < cupones.size(); i++) {
				UserCupon cupon = cupones.get(i);
				String fechaEmitido = cupon.getFecha_emitido();
				String fechaVencimiento = cupon.getFecha_vencimiento();
				try {
					Date dateEmitido = currentDateFormatCupones.parse(fechaEmitido);
					cupon.setFecha_emitido(newDateFormatCupones.format(dateEmitido));
				} catch (Exception e) {
					cupon.setFecha_emitido("N/A");
				}
				try {
					Date dateVencimiento = currentDateFormatCupones.parse(fechaVencimiento);
					cupon.setFecha_vencimiento(newDateFormatCupones.format(dateVencimiento));
				} catch (Exception e) {
					cupon.setFecha_vencimiento("N/A");
				}
				switch (cupon.getTipoCupon()) {
				case 2:
					giftCards.add(cupon);
					break;
				case 3:
					entradasCine.add(cupon);
					break;
				case 4:
					panoramas.add(cupon);
					break;
				default:
					break;
				}
			}
			mav.addObject("giftCards", giftCards);
			mav.addObject("entradasCine", entradasCine);
			mav.addObject("panoramas", panoramas);
			break;
		case 23:
			// TIPO MIS GUSTOS
			html += viewApp.loadViews("mis-preferencias", "FOOTER");
			mav = new ModelAndView(html);
			List<UserGusto> gustos = dtserver.loadGustos();
			List<UserGusto> gustosCliente = customerModel.loadCustomerGustos();
			for (int i = 0; i < gustos.size(); i++) {
				UserGusto gusto = gustos.get(i);
				for (int j = 0; j < gustosCliente.size(); j++) {
					UserGusto gustoCliente = gustosCliente.get(j);
					if (gusto.getId() == gustoCliente.getId()) {
						gusto.setGustado(true);
						break;
					}
				}
			}
			mav.addObject("gustos", gustos);
			break;
		case 24:
			// TIPO TRANSFERIR
			html += viewApp.loadViews("transfiere-scotiapesos", "FOOTER");
			mav = new ModelAndView(html);
			break;
		default:
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		this.setHeaderx(mav);
		return mav;
	}

	@GetMapping("/information/{nombreInformation}")
	public ModelAndView getinformation(@PathVariable("nombreInformation") String nombreInformation) {
		ModelAndView mav = new ModelAndView(viewApp.loadViews("HEAD", "INFORMATION", "FOOTER"));
		Information informationhtml = new Information();
		informationhtml = dtserver.loadInformationByName(nombreInformation);
		if (informationhtml == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		mav.addObject("informationhtml", informationhtml);
		this.setHeaderx(mav);
		return mav;
	}

	@GetMapping("/cupon/get/{id_reward}")
	public ModelAndView getCuponByRew(@PathVariable("id_reward") int id_reward,
			@RequestHeader(value = "referer", required = false) final String referer) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (resolver.isAnonymous(auth)) {
			return new ModelAndView("redirect:" + referer + "?login");
		}

		ModelAndView mav = new ModelAndView(viewApp.loadViews("HEAD", "INFORMATION", "FOOTER"));
		this.setHeaderx(mav);
		return mav;
	}

	@GetMapping("/getcupon/{id_rew}")
	public Object getFile(@PathVariable("id_rew") int idReward, HttpServletRequest rq,
			@RequestHeader(value = "referer", required = false) final String referer) {
		Customer customer = new Customer();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			customer = (Customer) auth.getPrincipal();
		} else {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}
		byte[] response = customerModel.loadCuponAsPdf(customer.getScotiauser().getIdCliente(), idReward);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new ByteArrayResource(response));
	}

	@PostMapping("/gustos/actualizar")
	public void actualizarGustos(@RequestParam(value = "gusto", required = false) String[] gustos) {
		if (gustos == null) {
			gustos = new String[0];
		}
		customerModel.saveCustomerGustos(gustos);
	}

	@GetMapping("/despegar")
	public ModelAndView getDespegarLink(@RequestHeader(value = "referer", required = false) final String referer) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			String despegarLink = customerModel.getDespegarLink().replace("\n", "").replace("\r", "");
			;
			return new ModelAndView("redirect:" + despegarLink);
		} else {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}
	}

}
