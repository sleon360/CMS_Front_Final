package com.appcms.router;

<<<<<<< HEAD
=======

>>>>>>> refs/heads/master
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.ArrayList;
=======
>>>>>>> refs/heads/master
import java.util.Calendar;
<<<<<<< HEAD
import java.util.Date;
=======
>>>>>>> refs/heads/master
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
<<<<<<< HEAD
=======
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
>>>>>>> refs/heads/master
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
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import com.appcms.entity.CanjeProducto;
import com.appcms.entity.CustomerInscripcion;
import com.appcms.entity.CustomerReward;
import com.appcms.entity.Information;
import com.appcms.entity.ProductoCategoria;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.Scinformacionsubmenu;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.Scsubmenu;
import com.appcms.entity.StockTicket;
<<<<<<< HEAD
import com.appcms.entity.UserCupon;
=======
>>>>>>> refs/heads/master
import com.appcms.entity.UserGusto;
<<<<<<< HEAD
import com.appcms.entity.customer.Customer;
=======
import com.appcms.entity.points.Points;
>>>>>>> refs/heads/master
import com.appcms.model.DataServer;
import com.cms.views.ViewApp;

@Controller
public class Routes {
<<<<<<< HEAD
=======
///
	public final String csrf_token = "afxn123xnx360";
	private ViewApp vi;
	private final DataServer dtserver;
>>>>>>> refs/heads/master

	
	@Autowired
    public Routes(DataServer xdtserver) {
		this.dtserver = xdtserver;
		vi=new ViewApp(dtserver.getApiUrl(),dtserver.getToken());
    }
	
	public void setViewApp(ViewApp xvi)
	{
		this.vi=xvi;
	}
	
	

<<<<<<< HEAD
	public void setHeaderx(ModelAndView mav, HttpServletRequest rq) {
		System.out.println("Setting headers");
		mav.addObject("menuesHeader", dtserver.loadAllScmenu(rq));
=======
	public void setHeaderx(ModelAndView mav) {
		mav.addObject("menuesHeader", dtserver.loadAllScmenu().getBody());
>>>>>>> refs/heads/master
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		System.out.println("esta login: " + resolver.isAnonymous(auth));
		if (!resolver.isAnonymous(auth)) {
			Customer customer = (Customer) auth.getPrincipal();

			mav.addObject("usuario", customer.getScotiauser());
			mav.addObject("points", dtserver.loadUserPoints());
			// Se agregan los gustos del usuario
			List<UserGusto> gustos = dtserver.loadGustos();
			List<UserGusto> gustosCliente = dtserver.loadCustomerGustos();
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
<<<<<<< HEAD
=======
		
		// mav.addObject("usuario",Emudata.getUsusarioOff());
>>>>>>> refs/heads/master
	}

	@GetMapping("/404")
	public ModelAndView notfound(HttpServletRequest rq) {
<<<<<<< HEAD

		ViewApp vi = new ViewApp(apiUrl);
		vi.addView("head");
		vi.addView("404");
		vi.addView("footer");
		ModelAndView mav = new ModelAndView(vi.render());
		this.setHeaderx(mav, rq);
=======
		this.vi.addView("head");
		this.vi.addView("404");
		this.vi.addView("footer");
		ModelAndView mav = new ModelAndView(this.vi.render());
		this.setHeaderx(mav);
>>>>>>> refs/heads/master
		return mav;
	}

	@ExceptionHandler(value = { Exception.class, MultipartException.class, NestedServletException.class,
			NestedServletException.class, ConnectException.class, RequestRejectedException.class })
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
	public ModelAndView errorprint(@PathVariable("err") int err, HttpServletRequest rq) {
		System.out.println("Controlador de errores");
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

<<<<<<< HEAD
		ViewApp vi = new ViewApp(apiUrl);
		vi.addView("head");
		vi.addView("error");
		vi.addView("footer");
		ModelAndView mav = new ModelAndView(vi.render());
=======
		this.vi.addView("head");
		this.vi.addView("error");
		this.vi.addView("footer");
		ModelAndView mav = new ModelAndView(this.vi.render());

>>>>>>> refs/heads/master
		mav.addObject("titulo_error", httpErrorCode);
		mav.addObject("descripcion_error", errorMsg);
		mav.addObject("clean", clean);
<<<<<<< HEAD
		return mav;
	}
=======

		this.setHeaderx(mav);
		return mav;
	}

	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		Cookie cookie = new Cookie("welcomex", null); // cookie que muestra detalle al inciar
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		return "redirect:/";
	}

>>>>>>> refs/heads/master

<<<<<<< HEAD
	@GetMapping("/")
	public ModelAndView home(HttpServletRequest rq) {
		ViewApp vi = new ViewApp(apiUrl);
		vi.addView("head");
		vi.addView("index");
		vi.addView("footer");
		ModelAndView mav = new ModelAndView(vi.render());
		System.out.println("Se creó el MAV");
		mav.addObject("banners", dtserver.loadBannerAll(0, rq));
		System.out.println("Se obtuvieron los banners 0");
		mav.addObject("banners_resp", dtserver.loadBannerAll(1, rq));
		System.out.println("Se obtuvieron los banners");
		mav.addObject("descuentos_destacados", dtserver.loadscmenuinformationFomScmenu(10, rq));
		System.out.println("Se obtuvieron los descuentos destacados");
		this.setHeaderx(mav, rq);
=======
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		// return new ModelAndView("redirect:/home");	
		this.vi.addView("head");
		this.vi.addView("index");
		this.vi.addView("footer");
		ModelAndView mav = new ModelAndView(this.vi.render());
		mav.addObject("banners", dtserver.loadBannerAll(0).getBody()); // Emudata.getBanners()
		mav.addObject("banners_resp", dtserver.loadBannerAll(1).getBody());

		mav.addObject("descuentos_destacados", dtserver.loadscmenuinformationFomScmenu(10).getBody());

		this.setHeaderx(mav);

>>>>>>> refs/heads/master
		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}")
	public ModelAndView menuSubmenu(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu) throws UnsupportedEncodingException {
		// ModelAndView mav = new ModelAndView("categorias");
<<<<<<< HEAD
		ViewApp vi = new ViewApp(apiUrl);

		Scmenu scmenu = dtserver.loadScmenuByName(rq, menu);
=======
		Scmenu scmenu = dtserver.loadScmenuByName( menu).getBody();
>>>>>>> refs/heads/master
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
			return new ModelAndView("redirect:/404");

		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/404");

		}

		switch (scmenuurlsub.getTipo()) {
		case 1: // information
			System.out.println("Tipo 1:" + scmenuurlsub.getId()); // TIPO INFORMACION
<<<<<<< HEAD
			// scmenuurlsub.informationsubmenu =
			// dtserver.loadInformatioSub(scmenuurlsub.getId(), rq);//
			// Emudata.getInformatiotest();
			scmenuurlsub.setInformationsubmenu(dtserver.loadInformatioSub(scmenuurlsub.getId(), rq));// Emudata.getInformatiotest();
=======
			scmenuurlsub.informationsubmenu = dtserver.loadInformatioSub(scmenuurlsub.getId()).getBody();// Emudata.getInformatiotest();
>>>>>>> refs/heads/master
			break;
		case 2:
			System.out.println("Tipo 2:" + scmenuurlsub.getId()); // TIPO PRODUCTO CON LIKE
<<<<<<< HEAD
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosLike(scmenuurlsub.getId(), rq));// Emudata.getProductosLikeTest();
=======
			
			ResponseEntity<List<ProductoTipoLike>> retorno=dtserver.loadProductosLike(scmenuurlsub.getId());
			scmenuurlsub.productosLikeLista = retorno.getBody();// Emudata.getProductosLikeTest();
>>>>>>> refs/heads/master
			break;
		case 3:
			System.out.println("Tipo 3"); // TIPO CON CUPON
<<<<<<< HEAD
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosLike(scmenuurlsub.getId(), rq));// Emudata.getProductosLikeTest();
=======
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLike(scmenuurlsub.getId()).getBody();// Emudata.getProductosiNFOTest();
>>>>>>> refs/heads/master
			break;
		case 4:
			System.out.println("Tipo 4"); // TIPO PRODUCTO E-COMERCE
<<<<<<< HEAD
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosLike(scmenuurlsub.getId(), rq));// Emudata.getProductosLikeTest();
=======
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLike(scmenuurlsub.getId()).getBody();// Emudata.getProductoseEcomerceTest();
			System.out.println("prodconstock:" + scmenuurlsub.productosLikeLista.toString());
>>>>>>> refs/heads/master
			break;
		case 5:
			System.out.println("Tipo 5"); // TIPO CANJE CON CATEGORIAS
<<<<<<< HEAD
			scmenuurlsub.setCategoriaProductoLista(dtserver.loadCateProductosFromCategoria(scmenuurlsub.getId(), rq));// Emudata.getCategoriasProductosTest();//
=======
			scmenuurlsub.categoriaProductoLista = dtserver.loadCateProductosFromCategoria(scmenuurlsub.getId()).getBody();// Emudata.getCategoriasProductosTest();//
>>>>>>> refs/heads/master
			break;
		case 6:
<<<<<<< HEAD
			System.out.println("Tipo 6"); // TIPO CANJE CON CATEGORIAS PARA FORMULARIO (TIPO INSCRIPCION)
			scmenuurlsub.setCategoriaProductoLista(dtserver.loadCateProductosFromCategoria(scmenuurlsub.getId(), rq));// Emudata.getCategoriasProductosTestTipo6();
=======
			System.out.println("Tipo 6"); // TIPO CANJE CON CATEGORIAS PARA FORMULARIO
			scmenuurlsub.categoriaProductoLista = dtserver.loadCateProductosFromCategoria(scmenuurlsub.getId()).getBody();// Emudata.getCategoriasProductosTestTipo6();
>>>>>>> refs/heads/master
			break;
		case 7:
			System.out.println("Tipo 7"); // TIPO CANJE CASHBACK
			scmenuurlsub.setTarjetasCliente(dtserver.loadUserTarjetas().getTarjetasCliente());
			break;
		case 8:
			System.out.println("Tipo 8"); // TIPO CANJE DESCUENTOS
<<<<<<< HEAD
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosLike(scmenuurlsub.getId(), rq));// Emudata.getProductosLikeTest();
			scmenuurlsub.setTagsProductos(dtserver.loadTagsProductos(rq));// Emudata.getProductosLikeTest();
=======
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLike(scmenuurlsub.getId()).getBody();// Emudata.getProductosLikeTest();
			//scmenuurlsub.tagsProductos = dtserver.loadTagsProductos(rq);// Emudata.getProductosLikeTest();
			//System.out.println("<<<<< " + scmenuurlsub.tagsProductos + " >>>>>");
>>>>>>> refs/heads/master
			break;
		case 9:
			System.out.println("Tipo 9"); // TIPO VISTA INFORMATION
<<<<<<< HEAD
			scmenuurlsub.setInformationHtml(dtserver.loadInformationScsubmenu(scmenuurlsub.getId(), rq));// Emudata.getInformationHtml();loadInformationScsubmenu
=======
			scmenuurlsub.informationHtml = dtserver.loadInformationScsubmenu(scmenuurlsub.getId()).getBody();// Emudata.getInformationHtml();loadInformationScsubmenu
>>>>>>> refs/heads/master
			break;
		}

		this.vi.addView("head");
		this.vi.addView("HEADER_CATEGORIAS");
		this.vi.addView("CATEGORIAS");
		this.vi.addView("footer");

		ModelAndView mav = new ModelAndView(this.vi.render());
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav);

		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}/productos/{categoria}")
	public ModelAndView menuProductoCategoria(@PathVariable("menu") String menu,
<<<<<<< HEAD
			@PathVariable("submenu") String submenu, @PathVariable("categoria") String categoria,
			@RequestHeader(value = "referer", required = false) final String referer, HttpServletRequest rq)
=======
			@PathVariable("submenu") String submenu, @PathVariable("categoria") String categoria)
>>>>>>> refs/heads/master
			throws UnsupportedEncodingException {
<<<<<<< HEAD
		ViewApp vi = new ViewApp(apiUrl);

		vi.addView("head");
		vi.addView("HEADER_CATEGORIAS");
		vi.addView("CATEGORIAS");
		vi.addView("footer");
		ModelAndView mav = new ModelAndView(vi.render());
=======
//		ModelAndView mav = new ModelAndView("categorias");
		
		this.vi.addView("head");
		this.vi.addView("HEADER_CATEGORIAS");
		this.vi.addView("CATEGORIAS");
		this.vi.addView("footer");
		ModelAndView mav = new ModelAndView(this.vi.render());
>>>>>>> refs/heads/master

<<<<<<< HEAD
		Scmenu scmenu = dtserver.loadScmenuByName(rq, menu);
=======
		
		Scmenu scmenu = dtserver.loadScmenuByName(menu).getBody();
>>>>>>> refs/heads/master
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
			return new ModelAndView("redirect:/404");

		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/404");

		}

		
		
		System.out.println("VVVVVVVVVVVVV:"+scmenuurlsub.getTipo());
		switch (scmenuurlsub.getTipo()) { // SI O SI TIENE QUE SER SUB CATEGORIA TIPO 5 o 6 PARA TENER PRODUCTOS A LA
											// CATEGORIAPRODUCTOS ASOCIADA
		case 5:
			System.out.println("Tipo 5"); // TIPO CANJE CON CATEGORIAS
<<<<<<< HEAD

			scmenuurlsub.setCategoriaProductoLista(
					dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(), categoria, rq));// Emudata.getCateProductosFromCategoria(categoria);

=======

			scmenuurlsub.categoriaProductoLista = dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(),categoria).getBody();// Emudata.getCateProductosFromCategoria(categoria);
>>>>>>> refs/heads/master
			mav.addObject("verProductosCategoria", true);
			break;

		case 6:
			System.out.println("Tipo 6"); // TIPO CANJE CON CATEGORIAS FORMULARIO
			// se pasa la categoria para seleccionar el primer producto de ella, deberia
			// siempre tener 1 producto MAXIMO por categoria tipo formulario

<<<<<<< HEAD
			// Si el usuario no se ha autenticado, no debería avanzarse ya que se requieren
			// los puntos y las tarjetas del cliente
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
			if (resolver.isAnonymous(auth)) {
				// Para evitar que una página quede como ?login?login se hace el replace
				return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
			}

			scmenuurlsub.setProductosLikeLista(
					dtserver.loadProductosLikeSubmenuCategoria(scmenuurlsub.getId(), categoria, rq));// Emudata.getProductosLikeTest();
=======
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLikeSubmenuCategoria(scmenuurlsub.getId(),categoria).getBody();// Emudata.getProductosLikeTest();
>>>>>>> refs/heads/master
			mav.addObject("producto", new CanjeProducto());
			mav.addObject("verProductosCategoria", true);

			// Se agregan los puntos de cliente
			mav.addObject("puntosDisponibles", dtserver.loadUserPoints().getAvailablePoints());
			// Se agregan las tarjetas del cliente
			scmenuurlsub.setTarjetasCliente(dtserver.loadUserTarjetas().getTarjetasCliente());
			break;
		case 8:
			System.out.println("Tipo 8"); // TIPO CANJE CON CATEGORIAS
<<<<<<< HEAD
			scmenuurlsub.setCategoriaProductoLista(
					dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(), categoria, rq));
=======
			scmenuurlsub.categoriaProductoLista = dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(),categoria).getBody();// Emudata.getCateProductosFromCategoria(categoria);
>>>>>>> refs/heads/master
			mav.addObject("verProductosCategoria", true);
			break;
		default:
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/404");

		}

		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav);

		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}/detalle/{producto}")
	public ModelAndView menuDetalleProducto(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,
			@PathVariable("producto") int producto) throws UnsupportedEncodingException {
//		ModelAndView mav = new ModelAndView("canjes");
<<<<<<< HEAD
		ViewApp vi = new ViewApp(apiUrl);
=======
		this.vi.addView("HEAD");
		this.vi.addView("HEADER_CATEGORIAS");
		this.vi.addView("CANJES");
		this.vi.addView("FOOTER");
		ModelAndView mav = new ModelAndView(this.vi.render());
>>>>>>> refs/heads/master

		
		Scmenu scmenu = dtserver.loadScmenuByName(menu).getBody();
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
			return new ModelAndView("redirect:/404");

		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/404");

		}

<<<<<<< HEAD
		scmenuurlsub.setProductosLikeLista(dtserver.loadProductosDetalle(producto, rq));

=======
		scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto); // Emudata.getProductoSearch(producto);
		
>>>>>>> refs/heads/master
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav);

		return mav;
	}

	@PostMapping("/categoria/{menu}/{submenu}/canje")
	public ModelAndView menuCanje(@ModelAttribute("producto") CanjeProducto producto, @PathVariable("menu") String menu,
<<<<<<< HEAD
			@PathVariable("submenu") String submenu, HttpServletRequest rq,
			@RequestHeader(value = "referer", required = false) final String referer) {
		System.out.println("Adentro de controlador");
=======
			@PathVariable("submenu") String submenu,@RequestHeader(value = "referer", required = false) final String referer)
			throws NamingException, ErrorControllerExection {
		// ModelAndView mav = new ModelAndView("canjes");

>>>>>>> refs/heads/master
		producto.setCantidad(1);

		Customer customer = new Customer();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			customer = (Customer) auth.getPrincipal();
		} else {
			System.out.println("User no login, redirect");
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}

<<<<<<< HEAD
		ViewApp vi = new ViewApp(apiUrl);
=======
		this.vi.addView("HEAD");
		this.vi.addView("HEADER_CATEGORIAS");
		this.vi.addView("CANJES");
		this.vi.addView("FOOTER");
		ModelAndView mav = new ModelAndView(this.vi.render());
>>>>>>> refs/heads/master

		Scmenu scmenu = dtserver.loadScmenuByName(menu).getBody();
		Scsubmenu scmenuurlsub = new Scsubmenu();
<<<<<<< HEAD

=======
		
		
		
>>>>>>> refs/heads/master
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
			return new ModelAndView("redirect:/404");

		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/404");

		}
		System.out.println("Tipo de submenú: " + scmenuurlsub.getTipo());
		switch (scmenuurlsub.getTipo()) {
		case 1: // TIPO INFORMACION
			System.out.println("Error tipo");
			scmenuurlsub.setTipo(0);
			break;
		case 2: // TIPO PRODUCTO CON LIKE
			System.out.println("Error tipo");
			scmenuurlsub.setTipo(0);
			break;
		case 3: // TIPO CON CUPON
			// efectuar canje, datos en objeto "producto"
			mav.addObject("canjeExito", true);
			break;
		case 4: // TIPO PRODUCTO E-COMERCE
			System.out.println("Tipo 4");
			// efectuar canje, datos en objeto "producto"

			try {
				// OBTENEMOS EL PRODUCTO
				ProductoTipoLike detalleProducto = dtserver.loadProductoById(producto.getIdProducto()).getBody();
				int idPruductoCanje = detalleProducto.getId();

				// PRODUCTO DISPONIBLE (NO FUNCIONA)
				if (idPruductoCanje == 0 || producto.getCantidad() < 1) {
					System.out.println("no producto");
					return new ModelAndView("redirect:/404");
				} else {

					// DESCRIPCION DEL CANJE
					String descipcionAbono = "Canje: " + detalleProducto.getTitulo();
					java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

					// SETEO DE PUNTOS
					int totalPuntos = detalleProducto.getPrecio() * producto.getCantidad();
<<<<<<< HEAD
					Scotiauser usuario = customer.getScotiauser();

					usuario.setPoints(dtserver.loadUserPoints().getAvailablePoints());
=======
					Scotiauser usuario = credentialUser.getScotiauser();
					
					Points puntos=dtserver.loadUserPoints().getBody();
					usuario.setPoints(puntos.getAvailablePoints());
>>>>>>> refs/heads/master

<<<<<<< HEAD
					StockTicket stockticket = dtserver.loadStockTicket(detalleProducto.getNombre(), rq);
					System.out.println("activosticket: " + stockticket.toString());
					System.out.println("puntos canje: " + totalPuntos + "puntos disponibles: " + usuario.getPoints());

=======

					StockTicket stockticket = dtserver.loadStockTicket(detalleProducto.getNombre()).getBody();
					System.out.println("activosticket: " + detalleProducto.getNombre());
					//System.out.println("puntos canje: " + totalPuntos + "puntos disponibles: " + usuario.getPoints());

>>>>>>> refs/heads/master
					int stockProducto = stockticket.getActivo();

					if (stockProducto < 1) {// sin stock
						mav.addObject("canjeExito", false);
						mav.addObject("error_code", 10);// Sin stock

					} else if (totalPuntos > usuario.getPoints()) {
						mav.addObject("canjeExito", false);
						mav.addObject("error_code", 11);// Puntos insuficientes
					} else {
	
						CustomerReward movimientoActual=new CustomerReward();
						movimientoActual.setCustomer_id(usuario.getId_cliente());
						movimientoActual.setOrder_id(producto.getIdProducto());
						movimientoActual.setDescription(descipcionAbono);
						movimientoActual.setPoints(totalPuntos);
						movimientoActual.setDate_added(date.toString());
						movimientoActual.setDate_vencimiento(date.toString());
						movimientoActual.setId_trx(0);			
						movimientoActual.setTipo_reward(1);
						
						String agregado = dtserver.setReward(movimientoActual, detalleProducto.getNombre(),usuario.getRut()).getBody();
						System.out.println("RESULTSETREWARDS: " + agregado);
						if (agregado != null) {
							System.out.println("Movimiento agregado");
							mav.addObject("canjeExito", true);

<<<<<<< HEAD
							// JsonArray jsonObjectAgregado = new
							// JsonParser().parse(agregado).getAsJsonArray();
//							mav.addObject("idrewards", jsonObjectAgregado.get);
							// agregado: RETORNA STATUS DEL CANJE ("customer_reward_id")
							// SI
							// CONSULTA VOUCHER TICKETERA
							// MUESTRA RESULTADO CANJE
							// NO
							// VALIDA ERROR (SESSION FINALIZADA, PUNTOS INSUFICIENTES, TOKEN INVALIDO, ETC)
							// MUESTRA MENSAJE A CLIENTE
						} else { // PROPBLEMA AL CONSULTAR
=======
							JsonArray jsonObjectAgregado = new JsonParser().parse(agregado).getAsJsonArray();

						} else {
>>>>>>> refs/heads/master
							System.out.println("Movimiento no agregado");
							mav.addObject("canjeExito", false);
						}

					}
				}

			} catch (Exception ex) {
				System.out.println("canjeex: " + ex.getMessage());
			}

			break;
		case 5: // TIPO CANJE CON CATEGORIAS
			if (producto.getActionx().equalsIgnoreCase("finish")) {

				mav.addObject("canjeExito", true);
			} else {
				producto.setActionx("finish");
			} // dtserver.loadProductosDetalle(scmenuurlsub.getId());//
<<<<<<< HEAD
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosDetalle(producto.getIdProducto(), rq));
=======
			scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto.getIdProducto());// Emudata.getProductoSearchById(producto.getIdProducto());//dtserver.loadProductosDetalle(scmenuurlsub.getId());//Emudata.getProductoSearchById(producto.getIdProducto());//
>>>>>>> refs/heads/master
			mav.addObject("producto", producto);
			break;
		case 6: // TIPO CANJE CON CATEGORIAS PARA FORMULARIO
			if (producto.getActionx().equalsIgnoreCase("finish")) {
				dtserver.inscribirPuntos(producto.getIdProducto(), producto.getCardKey(), producto.getCardNumber(),
						producto.getMonto());
				mav.addObject("canjeExito", true);
			} else {
//				return new ModelAndView("redirect:/404");
				producto.setActionx("finish");
			}
			
			System.out.println("CCCCCCCCCCCCC:"+producto.getIdProducto());

<<<<<<< HEAD
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosDetalle(producto.getIdProducto(), rq));
			ProductoCategoria categoriaProducto = dtserver.loadProductoCategoria(producto.getIdProducto(), rq);// Emudata.getProductoSearchById(producto.getIdProducto());
			if (categoriaProducto != null) {
				scmenuurlsub.getProductosLikeLista().get(0).setImagen(categoriaProducto.getImagen());
				scmenuurlsub.getProductosLikeLista().get(0).setNombre(categoriaProducto.getNombre());
			}
=======
			scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto.getIdProducto());// Emudata.getProductoSearchById(producto.getIdProducto());
>>>>>>> refs/heads/master
			mav.addObject("producto", producto);
			break;
		case 7: // TIPO CANJE CASHBACK
			break;
		case 8: // TIPO CANJE DESCUENTOS
<<<<<<< HEAD
			scmenuurlsub.setProductosLikeLista(dtserver.loadProductosDetalle(producto.getIdProducto(), rq));
=======
			scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto.getIdProducto());// Emudata.getProductoSearchById(producto.getIdProducto());//dtserver.loadProductosDetalle(scmenuurlsub.getId());//Emudata.getProductoSearchById(producto.getIdProducto());
>>>>>>> refs/heads/master
			mav.addObject("producto", producto);
			mav.addObject("canjeExito", true);
			break;
		default:
			System.out.println("Seccion fuera de menu");
			return new ModelAndView("redirect:/404");

		}
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav);

		return mav;
	}

<<<<<<< HEAD
=======

	
>>>>>>> refs/heads/master
	@GetMapping("/user/{menu}/{submenu}")
	public ModelAndView menuUser(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu, @RequestHeader(value = "referer", required = false) final String referer)
			throws UnsupportedEncodingException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (resolver.isAnonymous(auth)) {
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}

<<<<<<< HEAD
		ViewApp vi = new ViewApp(apiUrl);

		vi.addView("HEAD");
		vi.addView("HEADER_CATEGORIAS");

		ModelAndView mav = new ModelAndView(vi.render());
=======
		this.vi.addView("HEAD");
		this.vi.addView("HEADER_CATEGORIAS");
		
		ModelAndView mav = new ModelAndView(this.vi.render());
>>>>>>> refs/heads/master

		Scmenu scmenu = dtserver.loadScmenuByName(menu).getBody();
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
			return new ModelAndView("redirect:/404");

		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/404");

		}

		switch (scmenuurlsub.getTipo()) {
		case 20: // information
			System.out.println("Tipo 20"); // TIPO MI CARTOLA
			this.vi.addView("MI-CARTOLA");
			this.vi.addView("FOOTER");
			mav = new ModelAndView(this.vi.render());
			mav.addObject("cartola", dtserver.loadUserCartola().getBody());
			break;
		case 21: // information
			System.out.println("Tipo 21"); // TIPO INSCRIPCCION
<<<<<<< HEAD
			vi.addView("mis-inscripciones");
			vi.addView("FOOTER");
			mav = new ModelAndView(vi.render());
			List<CustomerInscripcion> inscripciones = dtserver.loadUserInscripciones();
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
=======
			this.vi.addView("mis-inscripciones");
			this.vi.addView("FOOTER");
			mav = new ModelAndView(this.vi.render());
			mav.addObject("inscripciones", Emudata.getInscripciones());
>>>>>>> refs/heads/master
			break;
		case 22: // information
			System.out.println("Tipo 22"); // TIPO MIS CUPONES
<<<<<<< HEAD
			vi.addView("mis-cupones");
			vi.addView("FOOTER");
			mav = new ModelAndView(vi.render());
			List<UserCupon> cupones = dtserver.loadCupones();
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
=======
			this.vi.addView("mis-cupones");
			this.vi.addView("FOOTER");
			mav = new ModelAndView(this.vi.render());
			System.out.println("Mis cupones: usr: " + credentialUser.getScotiauser().getId_cliente());
			mav.addObject("usercupones", dtserver.loadCupones(credentialUser.getScotiauser().getId_cliente()));
>>>>>>> refs/heads/master

//			scmenuurlsub.informationsubmenu = Emudata.getInformatiotest();
			break;
		case 23: // information
			System.out.println("Tipo 23"); // TIPO MIS GUSTOS
<<<<<<< HEAD
			vi.addView("mis-preferencias");
			vi.addView("FOOTER");
			mav = new ModelAndView(vi.render());
			// No se hace nada, los gustos ya se agregan en setHeaderx porque pueden
			// aparecer en caulquier parte
=======
			this.vi.addView("mis-preferencias");
			this.vi.addView("FOOTER");
			mav = new ModelAndView(this.vi.render());
			List<UserGusto> gustos = dtserver.loadGustos().getBody();
			List<UserGusto> gustosCliente = dtserver.loadCustomerGustos().getBody();
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
>>>>>>> refs/heads/master
			break;
		case 24: // information
			System.out.println("Tipo 24"); // TIPO TRANSFERIR
<<<<<<< HEAD
			// scmenuurlsub.informationsubmenu = Emudata.getInformatiotest();
			vi.addView("transfiere-scotiapesos");
			vi.addView("FOOTER");
			mav = new ModelAndView(vi.render());
=======
			
			Scinformacionsubmenu information = new Scinformacionsubmenu(1,1,"Restorado8",1,"/resource/images/woman-computer.jpg","Obtén desde un","15% dcto. en restaurantes","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","#","Reserva acá","[\"Restorando les ofrece a miles de comensales la posibilidad de descubrir miles de lugares para salir a comer, acceder a ofertas y beneficios en tiempo real y asegurar su mesa sin tener que esperar para sentarse.\",\"Restorando trabaja junto con los restaurantes para mejorar las experiencias gastron\\u00f3micas de los comensales en latinoam\\u00e9rica.\",\"XXC\",\"t2\"]","2018-12-11 18:15:04","2019-02-13 18:03:57",1);
			scmenuurlsub.informationsubmenu =information;
			
			//scmenuurlsub.informationsubmenu = Emudata.getInformatiotest();
			this.vi.addView("transfiere-scotiapesos");
			this.vi.addView("FOOTER");
			mav = new ModelAndView(this.vi.render());
>>>>>>> refs/heads/master
			break;
		default:
			System.out.println("Seccion fuera de menu");
			return new ModelAndView("redirect:/404");
		}
<<<<<<< HEAD

=======
>>>>>>> refs/heads/master
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav);

		return mav;

	}

	@GetMapping("/information/{nombreInformation}")
	public ModelAndView getinformation(@PathVariable("nombreInformation") String nombreInformation) throws UnsupportedEncodingException {
//		ModelAndView mav = new ModelAndView("user");
<<<<<<< HEAD
		ViewApp vi = new ViewApp(apiUrl);
=======
		this.vi.addView("HEAD");
		this.vi.addView("INFORMATION");
		this.vi.addView("FOOTER");
>>>>>>> refs/heads/master

		ModelAndView mav = new ModelAndView(this.vi.render());

		Information informationhtml = new Information();
		informationhtml = dtserver.loadInformationByName(nombreInformation).getBody();
		if (informationhtml == null) {
			return new ModelAndView("redirect:/404");
		}

		mav.addObject("informationhtml", informationhtml);

		this.setHeaderx(mav);

		return mav;

	}

<<<<<<< HEAD
=======
	@PostMapping("/user/login")
	public ModelAndView loginuser(@ModelAttribute("loginForm") LoginUser loginForm) {
		this.vi.addView("HEAD");
		this.vi.addView("FOOTER");
		ModelAndView mav = new ModelAndView(this.vi.render());
		ResponseEntity<String> resultlogin = dtserver.testLogin(loginForm.getRut(), loginForm.getPass());

		//if (resultlogin.getStatusCode().OK==HttpStatus.OK) { // token de sesion devuelto

		//} else {

		//}
		this.setHeaderx(mav);

		return mav;

	}

	@GetMapping("/cupon/get/{id_reward}")
	public ModelAndView getCuponByRew(@PathVariable("id_reward") int id_reward,@RequestHeader(value = "referer", required = false) final String referer)
			throws UnsupportedEncodingException {
		CredencialesEntity credentialUser = new CredencialesEntity();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			credentialUser = (CredencialesEntity) auth.getPrincipal();
		} else {
			System.out.println("User no login");
			return new ModelAndView("redirect:" + referer + "?login");
		}

		this.vi.addView("HEAD");
		this.vi.addView("INFORMATION");
		this.vi.addView("FOOTER");

		ModelAndView mav = new ModelAndView(this.vi.render());

		this.setHeaderx(mav);

		return mav;

	}

>>>>>>> refs/heads/master
	@GetMapping("/getcupon/{id_rew}")
<<<<<<< HEAD
	public Object getFile(@PathVariable("id_rew") int idReward, HttpServletRequest rq,
			@RequestHeader(value = "referer", required = false) final String referer) {
		Customer customer = new Customer();
=======
	public Object getFile(@PathVariable("id_rew") int id_rew,@RequestHeader(value = "referer", required = false) final String referer) {
		CredencialesEntity credentialUser = new CredencialesEntity();
>>>>>>> refs/heads/master
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			customer = (Customer) auth.getPrincipal();
		} else {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}
<<<<<<< HEAD
		byte[] response = dtserver.loadCuponAsPdf(customer.getScotiauser().getId_cliente(), idReward, rq);
=======
		
		System.out.println("DDDDDDDDDDDDDDDDD"+credentialUser.getScotiauser().getId_cliente());
		byte[] response = dtserver.loadCuponAsPdf(credentialUser.getScotiauser().getId_cliente(), id_rew).getBody();
>>>>>>> refs/heads/master
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new ByteArrayResource(response));
	}

	@PostMapping("/gustos/actualizar")
<<<<<<< HEAD
	public void actualizarGustos(@RequestParam(value = "gusto", required = false) String[] gustos) {
=======
	public boolean actualizarGustos(@RequestParam(value = "gusto", required = false) String[] gustos) {
		System.out.println("Se llega al front");
>>>>>>> refs/heads/master
		if (gustos == null) {
			gustos = new String[0];
		}
<<<<<<< HEAD
		dtserver.saveCustomerGustos(gustos);
	}

	@PostMapping("/cupones/descargar-cupon")
	public ModelAndView descargarCupon(@RequestParam(value = "id-producto", required = false) int idProducto,
			@RequestHeader(value = "referer", required = false) final String referer) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			CustomerReward customerReward = dtserver.realizarCanjeDirecto(idProducto);
			return new ModelAndView("redirect:/getcupon/" + customerReward.getCustomer_reward_id());
		} else {
			// Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}
=======
		System.out.println("consultando al dtserver");
		String success = dtserver.saveCustomerGustos(gustos).getBody();
		return true;
>>>>>>> refs/heads/master
	}

}
