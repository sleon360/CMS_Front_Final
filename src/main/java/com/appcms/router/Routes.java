package com.appcms.router;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import com.appcms.entity.CredencialesEntity;
import com.appcms.entity.CustomerReward;
import com.appcms.entity.Information;
import com.appcms.entity.LoginUser;
import com.appcms.entity.ProductoCategoria;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.Scsubmenu;
import com.appcms.entity.StockTicket;
import com.appcms.entity.UserCupon;
import com.appcms.entity.UserGusto;
import com.appcms.model.DataServer;
import com.appcms.model.Emudata;
import com.appcms.security.ErrorControllerExection;
import com.cms.views.ViewApp;

@Controller
public class Routes {
///
	public final String csrf_token = "afxn123xnx360";

	private String apiUrl;

	@Autowired
	DataServer dtserver;

	@Autowired
	public Routes(@Qualifier("apiUrl") String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public void setHeaderx(ModelAndView mav, HttpServletRequest rq) {
		mav.addObject("menuesHeader", dtserver.loadAllScmenu(rq));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		System.out.println("esta login: " + resolver.isAnonymous(auth));
		if (!resolver.isAnonymous(auth)) {
			CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();
			System.out.println("El usuario se encuentra autenticado con el token: " + credencialesEntity.getTOKENTWO());

			// Se recuperan los datos del ususario a partir de la entidad de usuario
			/*
			 * mav.addObject("usuario", new Scotiauser(2, "177824577", "Fabian", "Gaete",
			 * "fgaete@afiniti.cl","1"));
			 */
			mav.addObject("usuario", credencialesEntity.getScotiauser());
			mav.addObject("points", dtserver.loadUserPoints());
		} else {
			mav.addObject("usuario", Emudata.getUsusarioOff());
		}
		// mav.addObject("usuario",Emudata.getUsusarioOff());
	}

	@GetMapping("/404")
	public ModelAndView notfound(HttpServletRequest rq) {

		ViewApp vi = new ViewApp(rq, apiUrl);
		vi.addView("head");
		vi.addView("404");
		vi.addView("footer");
		ModelAndView mav = new ModelAndView(vi.render());
		this.setHeaderx(mav, rq);
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

		}

		ViewApp vi = new ViewApp(rq, apiUrl);
		vi.addView("head");
		vi.addView("error");
		vi.addView("footer");
		ModelAndView mav = new ModelAndView(vi.render());

		mav.addObject("titulo_error", httpErrorCode);
		mav.addObject("descripcion_error", errorMsg);
		mav.addObject("clean", clean);

		this.setHeaderx(mav, rq);
		return mav;

//		return "error";
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

	@GetMapping("/admin")
	public ModelAndView admin(HttpServletRequest rq) {
		ViewApp vi = new ViewApp(rq, apiUrl);
		vi.addView("header");
		vi.addView("admin");
		ModelAndView mav = new ModelAndView(vi.render());
		return mav;
	}

	@GetMapping("/")
	public ModelAndView home(HttpServletRequest rq) {
		// return new ModelAndView("redirect:/home");
		ViewApp vi = new ViewApp(rq, apiUrl);
		vi.addView("head");
		vi.addView("index");
		vi.addView("footer");
		ModelAndView mav = new ModelAndView(vi.render());
		mav.addObject("banners", dtserver.loadBannerAll(0, rq)); // Emudata.getBanners()
		mav.addObject("banners_resp", dtserver.loadBannerAll(1, rq));

		mav.addObject("descuentos_destacados", dtserver.loadscmenuinformationFomScmenu(10, rq));

		this.setHeaderx(mav, rq);

		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}")
	public ModelAndView menuSubmenu(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,
			HttpServletRequest rq) throws UnsupportedEncodingException {
		// ModelAndView mav = new ModelAndView("categorias");
		ViewApp vi = new ViewApp(rq, apiUrl);

		Scmenu scmenu = dtserver.loadScmenuByName(rq, menu);
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
			scmenuurlsub.informationsubmenu = dtserver.loadInformatioSub(scmenuurlsub.getId(), rq);// Emudata.getInformatiotest();
			break;
		case 2:
			System.out.println("Tipo 2:" + scmenuurlsub.getId()); // TIPO PRODUCTO CON LIKE
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLike(scmenuurlsub.getId(), rq);// Emudata.getProductosLikeTest();
			break;
		case 3:
			System.out.println("Tipo 3"); // TIPO CON CUPON
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLike(scmenuurlsub.getId(), rq);// Emudata.getProductosiNFOTest();
			break;
		case 4:
			System.out.println("Tipo 4"); // TIPO PRODUCTO E-COMERCE
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLike(scmenuurlsub.getId(), rq);// Emudata.getProductoseEcomerceTest();
			System.out.println("prodconstock:" + scmenuurlsub.productosLikeLista.toString());
			break;
		case 5:
			System.out.println("Tipo 5"); // TIPO CANJE CON CATEGORIAS
			scmenuurlsub.categoriaProductoLista = dtserver.loadCateProductosFromCategoria(scmenuurlsub.getId(), rq);// Emudata.getCategoriasProductosTest();//
			break;
		case 6:
			System.out.println("Tipo 6"); // TIPO CANJE CON CATEGORIAS PARA FORMULARIO (TIPO INSCRIPCION)
			scmenuurlsub.categoriaProductoLista = dtserver.loadCateProductosFromCategoria(scmenuurlsub.getId(), rq);// Emudata.getCategoriasProductosTestTipo6();
			break;
		case 7:
			System.out.println("Tipo 7"); // TIPO CANJE CASHBACK
			scmenuurlsub.tarjetasCliente = dtserver.loadUserTarjetas().getTarjetasCliente();
			break;
		case 8:
			System.out.println("Tipo 8"); // TIPO CANJE DESCUENTOS
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLike(scmenuurlsub.getId(), rq);// Emudata.getProductosLikeTest();
			System.out.println(scmenuurlsub.productosLikeLista);
			scmenuurlsub.tagsProductos = dtserver.loadTagsProductos(rq);// Emudata.getProductosLikeTest();
			break;
		case 9:
			System.out.println("Tipo 9"); // TIPO VISTA INFORMATION
			scmenuurlsub.informationHtml = dtserver.loadInformationScsubmenu(scmenuurlsub.getId(), rq);// Emudata.getInformationHtml();loadInformationScsubmenu
			break;
		}

		vi.addView("head");
		vi.addView("HEADER_CATEGORIAS");
		vi.addView("CATEGORIAS");
		vi.addView("footer");

		ModelAndView mav = new ModelAndView(vi.render());
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav, rq);

		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}/productos/{categoria}")
	public ModelAndView menuProductoCategoria(@PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu,
			@PathVariable("categoria") String categoria,
			@RequestHeader(value = "referer", required = false) final String referer,
			HttpServletRequest rq)
			throws UnsupportedEncodingException {
//		ModelAndView mav = new ModelAndView("categorias");
		ViewApp vi = new ViewApp(rq, apiUrl);

		vi.addView("head");
		vi.addView("HEADER_CATEGORIAS");
		vi.addView("CATEGORIAS");
		vi.addView("footer");
		ModelAndView mav = new ModelAndView(vi.render());

		Scmenu scmenu = dtserver.loadScmenuByName(rq, menu);
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

		switch (scmenuurlsub.getTipo()) { // SI O SI TIENE QUE SER SUB CATEGORIA TIPO 5 o 6 PARA TENER PRODUCTOS A LA
											// CATEGORIAPRODUCTOS ASOCIADA
		case 5:
			System.out.println("Tipo 5"); // TIPO CANJE CON CATEGORIAS

			scmenuurlsub.categoriaProductoLista = dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(),
					categoria, rq);// Emudata.getCateProductosFromCategoria(categoria);
			System.out.println("Lista de productos de categoria: " + scmenuurlsub.categoriaProductoLista.toString());

			mav.addObject("verProductosCategoria", true);
			break;

		case 6:
			System.out.println("Tipo 6"); // TIPO CANJE CON CATEGORIAS FORMULARIO
			// se pasa la categoria para seleccionar el primer producto de ella, deberia
			// siempre tener 1 producto MAXIMO por categoria tipo formulario

			//Si el usuario no se ha autenticado, no debería avanzarse ya que se requieren los puntos y las tarjetas del cliente
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
			if (resolver.isAnonymous(auth)) {
				//Para evitar que una página quede como ?login?login se hace el replace
				return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
			}
			
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLikeSubmenuCategoria(scmenuurlsub.getId(),
					categoria, rq);// Emudata.getProductosLikeTest();
			mav.addObject("producto", new CanjeProducto());
			mav.addObject("verProductosCategoria", true);

			
			// Se agregan los puntos de cliente
			mav.addObject("puntosDisponibles", dtserver.loadUserPoints().getAvailablePoints());
			// Se agregan las tarjetas del cliente
			scmenuurlsub.tarjetasCliente = dtserver.loadUserTarjetas().getTarjetasCliente();
			break;
		case 8:
			System.out.println("Tipo 8"); // TIPO CANJE CON CATEGORIAS
			scmenuurlsub.categoriaProductoLista = dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(),
					categoria, rq);// Emudata.getCateProductosFromCategoria(categoria);
			mav.addObject("verProductosCategoria", true);
			break;
		default:
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/404");

		}

//		mav.addObject("menuurl", scmenuurl);
//		mav.addObject("submenuurl", scmenuurlsub);
//		mav.addObject("csrf_token", csrf_token);
//		this.setHeaderx(mav,rq);

		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		mav.addObject("csrf_token", csrf_token);

		this.setHeaderx(mav, rq);

		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}/detalle/{producto}")
	public ModelAndView menuDetalleProducto(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,
			@PathVariable("producto") int producto, HttpServletRequest rq) throws UnsupportedEncodingException {
//		ModelAndView mav = new ModelAndView("canjes");
		ViewApp vi = new ViewApp(rq, apiUrl);

		vi.addView("HEAD");
		vi.addView("HEADER_CATEGORIAS");
		vi.addView("CANJES");
		vi.addView("FOOTER");
		ModelAndView mav = new ModelAndView(vi.render());

		Scmenu scmenu = dtserver.loadScmenuByName(rq, menu);
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

		scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto, rq); // Emudata.getProductoSearch(producto);

		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		mav.addObject("csrf_token", csrf_token);

		this.setHeaderx(mav, rq);

		return mav;
	}

	@PostMapping("/categoria/{menu}/{submenu}/canje")
	public ModelAndView menuCanje(@ModelAttribute("producto") CanjeProducto producto, @PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu, HttpServletRequest rq,
			@RequestHeader(value = "referer", required = false) final String referer)
			throws NamingException, ErrorControllerExection {
		// ModelAndView mav = new ModelAndView("canjes");

		producto.setCantidad(1);

		CredencialesEntity credentialUser = new CredencialesEntity();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			credentialUser = (CredencialesEntity) auth.getPrincipal();
		} else {
			System.out.println("User no login, redirect");
			//Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}

		ViewApp vi = new ViewApp(rq, apiUrl);

		vi.addView("HEAD");
		vi.addView("HEADER_CATEGORIAS");
		vi.addView("CANJES");
		vi.addView("FOOTER");
		ModelAndView mav = new ModelAndView(vi.render());

		Scmenu scmenu = dtserver.loadScmenuByName(rq, menu);
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
				ProductoTipoLike detalleProducto = dtserver.loadProductoById(producto.getIdProducto(), rq);
				int idPruductoCanje = detalleProducto.getId();

				// PRODUCTO DISPONIBLE (NO FUNCIONA)
				if (idPruductoCanje == 0 || producto.getCantidad() < 1) {
					System.out.println("no producto");
					return new ModelAndView("redirect:/404");
//					return new ModelAndView("redirect:/");
				} else {

					// DESCRIPCION DEL CANJE
					String descipcionAbono = "Canje: " + detalleProducto.getTitulo();
					java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

					// SETEO DE PUNTOS
					int totalPuntos = detalleProducto.getPrecio() * producto.getCantidad();
					Scotiauser usuario = credentialUser.getScotiauser();

					usuario.setPoints(dtserver.loadUserPoints().getAvailablePoints());

					StockTicket stockticket = dtserver.loadStockTicket(detalleProducto.getNombre(), rq);
					System.out.println("activosticket: " + stockticket.toString());
					System.out.println("puntos canje: " + totalPuntos + "puntos disponibles: " + usuario.getPoints());

					int stockProducto = stockticket.getActivo();

					if (stockProducto < 1) {// sin stock
						mav.addObject("canjeExito", false);
						mav.addObject("error_code", 10);// Sin stock

					} else if (totalPuntos > usuario.getPoints()) {
						mav.addObject("canjeExito", false);
						mav.addObject("error_code", 11);// Puntos insuficientes
					} else {
						CustomerReward movimientoActual = new CustomerReward(usuario.getId_cliente(),
								producto.getIdProducto(), descipcionAbono, totalPuntos, date.toString(),
								date.toString(), 0, 0, 1, 1);
						String agregado = dtserver.setReward(movimientoActual, detalleProducto.getNombre(),
								usuario.getRut(), rq);
						System.out.println("RESULTSETREWARDS: " + agregado);
						if (agregado != null) {
							System.out.println("Movimiento agregado");
							mav.addObject("canjeExito", true);

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
							System.out.println("Movimiento no agregado");
							mav.addObject("canjeExito", false);
						}

					}

//					JSONObject myjson = new JSONObject(the_json);
//					JSONArray the_json_array = myjson.getJSONArray("profiles");

				}

			} catch (Exception ex) {
				System.out.println("canjeex: " + ex.getMessage());
			}

//			System.out.println("canje: "+producto.getIdProducto());

//			mav.addObject("canjeExito", true);

			break;
		case 5: // TIPO CANJE CON CATEGORIAS
			if (producto.getActionx().equalsIgnoreCase("finish")) {

				mav.addObject("canjeExito", true);
			} else {
				producto.setActionx("finish");
			} // dtserver.loadProductosDetalle(scmenuurlsub.getId());//
			scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto.getIdProducto(), rq);// Emudata.getProductoSearchById(producto.getIdProducto());//dtserver.loadProductosDetalle(scmenuurlsub.getId());//Emudata.getProductoSearchById(producto.getIdProducto());//
			mav.addObject("producto", producto);

			System.out.println(scmenuurlsub.productosLikeLista.get(0).getEquipesos());
			System.out.println(scmenuurlsub.productosLikeLista.get(0).getPrecio());

			break;
		case 6: // TIPO CANJE CON CATEGORIAS PARA FORMULARIO
			System.out.println("canje T6");
			if (producto.getActionx().equalsIgnoreCase("finish")) {
				dtserver.inscribirPuntos(producto.getIdProducto(),
						producto.getCardKey(),
						producto.getCardNumber(),
						producto.getMonto());
				mav.addObject("canjeExito", true);
			} else {
//				return new ModelAndView("redirect:/404");
				producto.setActionx("finish");
			}

			scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto.getIdProducto(), rq);// Emudata.getProductoSearchById(producto.getIdProducto());
			ProductoCategoria categoriaProducto = dtserver.loadProductoCategoria(producto.getIdProducto(), rq);// Emudata.getProductoSearchById(producto.getIdProducto());
			if (categoriaProducto != null) {
				scmenuurlsub.productosLikeLista.get(0).setImagen(categoriaProducto.getImagen());
				scmenuurlsub.productosLikeLista.get(0).setNombre(categoriaProducto.getNombre());
			}
			mav.addObject("producto", producto);
			break;
		case 7: // TIPO CANJE CASHBACK
			break;
		case 8: // TIPO CANJE DESCUENTOS
			scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto.getIdProducto(), rq);// Emudata.getProductoSearchById(producto.getIdProducto());//dtserver.loadProductosDetalle(scmenuurlsub.getId());//Emudata.getProductoSearchById(producto.getIdProducto());
			mav.addObject("producto", producto);
			mav.addObject("canjeExito", true);
			break;
		default:
			System.out.println("Seccion fuera de menu");
			return new ModelAndView("redirect:/404");

		}
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		mav.addObject("csrf_token", csrf_token);

		this.setHeaderx(mav, rq);

		return mav;
	}

//	
//	@PostMapping("/categoria/canje")
//	public ModelAndView canjeDirecto(@ModelAttribute("producto") CanjeProducto producto, HttpServletRequest rq,
//			@RequestHeader(value = "referer", required = false) final String referer)
//			throws NamingException, ErrorControllerExection {
//		// ModelAndView mav = new ModelAndView("canjes");
//
//		producto.setCantidad(1);
//
//		CredencialesEntity credentialUser = new CredencialesEntity();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
////		if (!resolver.isAnonymous(auth)) {
////			credentialUser = (CredencialesEntity) auth.getPrincipal();
////		} else {
////			System.out.println("User no login");
////			Para evitar que una página quede como ?login?login se hace el replace
////			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
////		}
//
//		ViewApp vi = new ViewApp(rq,apiUrl);
//
//
//		vi.addView("HEAD");
//		vi.addView("CANJEDIRECTO");
//		vi.addView("FOOTER");
//		ModelAndView mav = new ModelAndView(vi.render());
//
//		Scmenu scmenuurl = new Scmenu();
//		Scsubmenu scmenuurlsub = new Scsubmenu();
//
//		List<Scmenu> categiriasmenu = new ArrayList<>();
//		categiriasmenu = dtserver.loadScmenu();// Emudata.getmenuCategorias();
//
//
//		
//		System.out.println(producto.toString());
//		
//		
//
//		mav.addObject("menuurl", scmenuurl);
//		mav.addObject("submenuurl", scmenuurlsub);
//
//		this.setHeaderx(mav, rq);
//
//		return mav;
//	}

	@GetMapping("/user/{menu}/{submenu}")
	public ModelAndView menuUser(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,
			HttpServletRequest rq, @RequestHeader(value = "referer", required = false) final String referer)
			throws UnsupportedEncodingException {

		CredencialesEntity credentialUser = new CredencialesEntity();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			credentialUser = (CredencialesEntity) auth.getPrincipal();
		} else {
			System.out.println("User no login");
			//Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}

		ViewApp vi = new ViewApp(rq, apiUrl);

		vi.addView("HEAD");
		vi.addView("HEADER_CATEGORIAS");

		ModelAndView mav = new ModelAndView(vi.render());

		Scmenu scmenu = dtserver.loadScmenuByName(rq, menu);
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
			vi.addView("MI-CARTOLA");
			vi.addView("FOOTER");
			mav = new ModelAndView(vi.render());
			mav.addObject("cartola", dtserver.loadUserCartola());
			break;
		case 21: // information
			System.out.println("Tipo 21"); // TIPO INSCRIPCCION
			vi.addView("mis-inscripciones");
			vi.addView("FOOTER");
			mav = new ModelAndView(vi.render());
			mav.addObject("inscripciones", Emudata.getInscripciones());
			break;
		case 22: // information
			System.out.println("Tipo 22"); // TIPO MIS CUPONES
			vi.addView("mis-cupones");
			vi.addView("FOOTER");
			mav = new ModelAndView(vi.render());
			System.out.println("Mis cupones: usr: " + credentialUser.getScotiauser().getId_cliente());
			List<UserCupon> cupones = dtserver.loadCupones(credentialUser.getScotiauser().getId_cliente(), rq);
			List<UserCupon> giftCards = new ArrayList<UserCupon>();
			List<UserCupon> entradasCine = new ArrayList<UserCupon>();
			List<UserCupon> panoramas = new ArrayList<UserCupon>();
			for (int i = 0; i < cupones.size(); i++) {
				UserCupon cupon = cupones.get(i);
				String fechaEmitido = cupon.getFecha_emitido();
				String fechaVencimiento = cupon.getFecha_vencimiento();
				SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date dateEmitido = currentDateFormat.parse(fechaEmitido);
					cupon.setFecha_emitido(newDateFormat.format(dateEmitido));
				} catch (Exception e) {
					cupon.setFecha_emitido("N/A");
				}
				try {
					Date dateVencimiento = currentDateFormat.parse(fechaVencimiento);
					cupon.setFecha_vencimiento(newDateFormat.format(dateVencimiento));
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
			System.out.println(giftCards);
			System.out.println(entradasCine);
			System.out.println("Panoramas: " + panoramas);
			mav.addObject("giftCards", giftCards);
			mav.addObject("entradasCine", entradasCine);
			mav.addObject("panoramas", panoramas);

//			scmenuurlsub.informationsubmenu = Emudata.getInformatiotest();
			break;
		case 23: // information
			System.out.println("Tipo 23"); // TIPO MIS GUSTOS
			vi.addView("mis-preferencias");
			vi.addView("FOOTER");
			mav = new ModelAndView(vi.render());
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
			break;
		case 24: // information
			System.out.println("Tipo 24"); // TIPO TRANSFERIR
			scmenuurlsub.informationsubmenu = Emudata.getInformatiotest();
			vi.addView("transfiere-scotiapesos");
			vi.addView("FOOTER");
			mav = new ModelAndView(vi.render());
			break;
		default:
			System.out.println("Seccion fuera de menu");
			return new ModelAndView("redirect:/404");
		}

//		mav.addObject("menuurl", scmenuurl);
//		mav.addObject("submenuurl", scmenuurlsub);
//
//		this.setHeaderx(mav,rq);
//
//		return mav;

		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav, rq);

		return mav;

	}

	@GetMapping("/information/{nombreInformation}")
	public ModelAndView getinformation(@PathVariable("nombreInformation") String nombreInformation,
			HttpServletRequest rq) throws UnsupportedEncodingException {
//		ModelAndView mav = new ModelAndView("user");
		ViewApp vi = new ViewApp(rq, apiUrl);

		vi.addView("HEAD");
		vi.addView("INFORMATION");
		vi.addView("FOOTER");

		ModelAndView mav = new ModelAndView(vi.render());

		Information informationhtml = new Information();

		informationhtml = dtserver.loadInformationByName(nombreInformation, rq);
		System.out.println("inforxn" + informationhtml);
		if (informationhtml == null) {
			return new ModelAndView("redirect:/404");
		}

		mav.addObject("informationhtml", informationhtml);

		this.setHeaderx(mav, rq);

		return mav;

	}

	@PostMapping("/user/login")
	public ModelAndView loginuser(@ModelAttribute("loginForm") LoginUser loginForm, HttpServletRequest rq) {
//		ModelAndView mav = new ModelAndView("user");
		ViewApp vi = new ViewApp(rq, apiUrl);

		vi.addView("HEAD");
//		vi.addView("INFORMATION");
		vi.addView("FOOTER");

		ModelAndView mav = new ModelAndView(vi.render());

		System.out.println("infologin: " + loginForm);

		String resultlogin = dtserver.testLogin(loginForm.getRut(), loginForm.getPass(), rq);
		System.out.println("result_login:" + resultlogin);

		if (resultlogin != null) { // token de sesion devuelto

		} else {

		}

		this.setHeaderx(mav, rq);

		return mav;

	}

	@GetMapping("/getcupon/{id_rew}")
	public Object getFile(@PathVariable("id_rew") int id_rew, HttpServletRequest rq,
			@RequestHeader(value = "referer", required = false) final String referer) {
		CredencialesEntity credentialUser = new CredencialesEntity();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			credentialUser = (CredencialesEntity) auth.getPrincipal();
		} else {
			//Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}
		byte[] response = dtserver.loadCuponAsPdf(credentialUser.getScotiauser().getId_cliente(), id_rew, rq);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new ByteArrayResource(response));
	}

	@PostMapping("/gustos/actualizar")
	public void actualizarGustos(@RequestParam(value = "gusto", required = false) String[] gustos) {
		if (gustos == null) {
			gustos = new String[0];
		}
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
			//Para evitar que una página quede como ?login?login se hace el replace
			return new ModelAndView("redirect:" + referer.replace("?login", "") + "?login");
		}
	}

}
