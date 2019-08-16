package com.appcms.router;


import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.util.Calendar;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import com.appcms.entity.CanjeProducto;
import com.appcms.entity.CredencialesEntity;
import com.appcms.entity.CustomerReward;
import com.appcms.entity.Information;
import com.appcms.entity.LoginUser;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.Scsubmenu;
import com.appcms.entity.StockTicket;
import com.appcms.entity.UserGusto;
import com.appcms.model.DataServer;
import com.appcms.model.Emudata;
import com.appcms.security.ErrorControllerExection;
import com.cms.views.ViewApp;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Controller
public class Routes {
///
	public final String csrf_token = "afxn123xnx360";

	private final DataServer dtserver;

	
	@Autowired
    public Routes(DataServer xdtserver) {
		this.dtserver = xdtserver;
    }
	
	

	public void setHeaderx(ModelAndView mav) {
		mav.addObject("menuesHeader", dtserver.loadAllScmenu());
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

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView notfound(HttpServletRequest rq) {
		dtserver.ViewApp().addView("head");
		dtserver.ViewApp().addView("404");
		dtserver.ViewApp().addView("footer");
		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());
		this.setHeaderx(mav);
		return mav;
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}

//	@ExceptionHandler(value = {Exception.class,MultipartException.class,NestedServletException.class,NestedServletException.class,ConnectException.class })
	@ExceptionHandler(value = { Exception.class, MultipartException.class, NestedServletException.class,
			NestedServletException.class, ConnectException.class, RequestRejectedException.class })
	@RequestMapping(value = "/errores", method = RequestMethod.GET)
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
			
			errorMsg = "Http Error Code: 500. Internal Server Error";

		}

		dtserver.ViewApp().addView("head");
		dtserver.ViewApp().addView("error");
		dtserver.ViewApp().addView("footer");
		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());

		mav.addObject("titulo_error", httpErrorCode);
		mav.addObject("descripcion_error", errorMsg);
		mav.addObject("clean", clean);

		this.setHeaderx(mav);
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

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(HttpServletRequest rq) {
		
		dtserver.ViewApp().addView("header");
		dtserver.ViewApp().addView("admin");
		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest rq) {
		// return new ModelAndView("redirect:/home");	
		dtserver.ViewApp().addView("head");
		dtserver.ViewApp().addView("index");
		dtserver.ViewApp().addView("footer");
		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());
		mav.addObject("banners", dtserver.loadBannerAll(0)); // Emudata.getBanners()
		mav.addObject("banners_resp", dtserver.loadBannerAll(1));

		mav.addObject("descuentos_destacados", dtserver.loadscmenuinformationFomScmenu(10));

		this.setHeaderx(mav);

		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}")
	public ModelAndView menuSubmenu(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu) throws UnsupportedEncodingException {
		// ModelAndView mav = new ModelAndView("categorias");
		Scmenu scmenu = dtserver.loadScmenuByName( menu);
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
			scmenuurlsub.informationsubmenu = dtserver.loadInformatioSub(scmenuurlsub.getId());// Emudata.getInformatiotest();
			break;
		case 2:
			System.out.println("Tipo 2:" + scmenuurlsub.getId()); // TIPO PRODUCTO CON LIKE
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLike(scmenuurlsub.getId());// Emudata.getProductosLikeTest();
			break;
		case 3:
			System.out.println("Tipo 3"); // TIPO CON CUPON
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLike(scmenuurlsub.getId());// Emudata.getProductosiNFOTest();
			break;
		case 4:
			System.out.println("Tipo 4"); // TIPO PRODUCTO E-COMERCE
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLike(scmenuurlsub.getId());// Emudata.getProductoseEcomerceTest();
			System.out.println("prodconstock:" + scmenuurlsub.productosLikeLista.toString());
			break;
		case 5:
			System.out.println("Tipo 5"); // TIPO CANJE CON CATEGORIAS
			scmenuurlsub.categoriaProductoLista = dtserver.loadCateProductosFromCategoria(scmenuurlsub.getId());// Emudata.getCategoriasProductosTest();//
			break;
		case 6:
			System.out.println("Tipo 6"); // TIPO CANJE CON CATEGORIAS PARA FORMULARIO
			scmenuurlsub.categoriaProductoLista = dtserver.loadCateProductosFromCategoria(scmenuurlsub.getId());// Emudata.getCategoriasProductosTestTipo6();
			break;
		case 7:
			System.out.println("Tipo 7"); // TIPO CANJE CASHBACK
			scmenuurlsub.tarjetasCliente = dtserver.loadUserTarjetas().getTarjetasCliente();
			break;
		case 8:
			System.out.println("Tipo 8"); // TIPO CANJE DESCUENTOS
			scmenuurlsub.productosLikeLista = dtserver.loadProductosLike(scmenuurlsub.getId());// Emudata.getProductosLikeTest();
			//scmenuurlsub.tagsProductos = dtserver.loadTagsProductos(rq);// Emudata.getProductosLikeTest();
			//System.out.println("<<<<< " + scmenuurlsub.tagsProductos + " >>>>>");
			break;
		case 9:
			System.out.println("Tipo 9"); // TIPO VISTA INFORMATION
			scmenuurlsub.informationHtml = dtserver.loadInformationScsubmenu(scmenuurlsub.getId());// Emudata.getInformationHtml();loadInformationScsubmenu
			break;
		}

		dtserver.ViewApp().addView("head");
		dtserver.ViewApp().addView("HEADER_CATEGORIAS");
		dtserver.ViewApp().addView("CATEGORIAS");
		dtserver.ViewApp().addView("footer");

		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav);

		return mav;
	}

	@RequestMapping(value = "/categoria/{menu}/{submenu}/productos/{categoria}", method = RequestMethod.GET)
	public ModelAndView menuProductoCategoria(@PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu, @PathVariable("categoria") String categoria, HttpServletRequest rq)
			throws UnsupportedEncodingException {
//		ModelAndView mav = new ModelAndView("categorias");
		
		dtserver.ViewApp().addView("head");
		dtserver.ViewApp().addView("HEADER_CATEGORIAS");
		dtserver.ViewApp().addView("CATEGORIAS");
		dtserver.ViewApp().addView("footer");
		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());

		
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

			scmenuurlsub.categoriaProductoLista = dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(),categoria);// Emudata.getCateProductosFromCategoria(categoria);
			System.out.println("Lista de productos de categoria: " + scmenuurlsub.categoriaProductoLista.toString());

			mav.addObject("verProductosCategoria", true);
			break;

		case 6:
			System.out.println("Tipo 6"); // TIPO CANJE CON CATEGORIAS FORMULARIO
			// se pasa la categoria para seleccionar el primer producto de ella, deberia
			// siempre tener 1 producto MAXIMO por categoria tipo formulario

			scmenuurlsub.productosLikeLista = dtserver.loadProductosLikeSubmenuCategoria(scmenuurlsub.getId(),categoria);// Emudata.getProductosLikeTest();
			mav.addObject("producto", new CanjeProducto());
			mav.addObject("verProductosCategoria", true);
			
			//Se agregan las tarjetas del cliente
			scmenuurlsub.tarjetasCliente = dtserver.loadUserTarjetas().getTarjetasCliente();
			break;
		case 8:
			System.out.println("Tipo 8"); // TIPO CANJE CON CATEGORIAS
			scmenuurlsub.categoriaProductoLista = dtserver.loadproductoCategoriaConProductos(scmenuurlsub.getId(),categoria);// Emudata.getCateProductosFromCategoria(categoria);
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

		this.setHeaderx(mav);

		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}/detalle/{producto}")
	public ModelAndView menuDetalleProducto(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,
			@PathVariable("producto") int producto, HttpServletRequest rq) throws UnsupportedEncodingException {
//		ModelAndView mav = new ModelAndView("canjes");
		dtserver.ViewApp().addView("HEAD");
		dtserver.ViewApp().addView("HEADER_CATEGORIAS");
		dtserver.ViewApp().addView("CANJES");
		dtserver.ViewApp().addView("FOOTER");
		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());

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
			return new ModelAndView("redirect:/404");

		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/404");

		}

		scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto); // Emudata.getProductoSearch(producto);
		
		mav.addObject("menuurl", scmenu);
		mav.addObject("submenuurl", scmenuurlsub);
		mav.addObject("csrf_token", csrf_token);

		this.setHeaderx(mav);

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
			return new ModelAndView("redirect:" + referer + "?login");
		}

		dtserver.ViewApp().addView("HEAD");
		dtserver.ViewApp().addView("HEADER_CATEGORIAS");
		dtserver.ViewApp().addView("CANJES");
		dtserver.ViewApp().addView("FOOTER");
		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());

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
				ProductoTipoLike detalleProducto = dtserver.loadProductoById(producto.getIdProducto());
				int idPruductoCanje = detalleProducto.getId();

				// PRODUCTO DISPONIBLE (NO FUNCIONA)
				if (idPruductoCanje == 0 || producto.getCantidad() < 1) {
					System.out.println("no producto");
					return new ModelAndView("redirect:/404");
//					return new ModelAndView("redirect:/");
				} else {

					//DESCRIPCION DEL CANJE
					String descipcionAbono = "Canje: " + detalleProducto.getTitulo();
					java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					
					//SETEO DE PUNTOS
					int totalPuntos = detalleProducto.getPrecio() * producto.getCantidad();
					Scotiauser usuario = credentialUser.getScotiauser();

					usuario.setPoints(dtserver.loadUserPoints().getAvailablePoints());


					StockTicket stockticket = dtserver.loadStockTicket(detalleProducto.getNombre());
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
	
						CustomerReward movimientoActual=new CustomerReward();
						movimientoActual.setCustomer_id(usuario.getId_cliente());
						movimientoActual.setOrder_id(producto.getIdProducto());
						movimientoActual.setDescription(descipcionAbono);
						movimientoActual.setPoints(totalPuntos);
						movimientoActual.setDate_added(date.toString());
						movimientoActual.setDate_vencimiento(date.toString());
						movimientoActual.setId_trx(0);			
						movimientoActual.setTipo_reward(1);
						
						String agregado = dtserver.setReward(movimientoActual, detalleProducto.getNombre(),usuario.getRut());
						System.out.println("RESULTSETREWARDS: " + agregado);
						if (agregado != null) {
							System.out.println("Movimiento agregado");
							mav.addObject("canjeExito", true);

							JsonArray jsonObjectAgregado = new JsonParser().parse(agregado).getAsJsonArray();
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
			scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto.getIdProducto());// Emudata.getProductoSearchById(producto.getIdProducto());//dtserver.loadProductosDetalle(scmenuurlsub.getId());//Emudata.getProductoSearchById(producto.getIdProducto());//
			mav.addObject("producto", producto);

			System.out.println(scmenuurlsub.productosLikeLista.get(0).getEquipesos());
			System.out.println(scmenuurlsub.productosLikeLista.get(0).getPrecio());
			
			break;
		case 6: // TIPO CANJE CON CATEGORIAS PARA FORMULARIO
			System.out.println("canje T6");
			if (producto.getActionx().equalsIgnoreCase("finish")) {
				return new ModelAndView("redirect:/404");
//				mav.addObject("canjeExito", true);
			} else {
//				return new ModelAndView("redirect:/404");
				producto.setActionx("finish");
			}

			scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto.getIdProducto());// Emudata.getProductoSearchById(producto.getIdProducto());
			System.out.println(scmenuurlsub.productosLikeLista.get(0).getEquipesos());
			System.out.println(scmenuurlsub.productosLikeLista.get(0).getPrecio());
			mav.addObject("producto", producto);
			break;
		case 7: // TIPO CANJE CASHBACK
			break;
		case 8: // TIPO CANJE DESCUENTOS
			scmenuurlsub.productosLikeLista = dtserver.loadProductosDetalle(producto.getIdProducto());// Emudata.getProductoSearchById(producto.getIdProducto());//dtserver.loadProductosDetalle(scmenuurlsub.getId());//Emudata.getProductoSearchById(producto.getIdProducto());
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

		this.setHeaderx(mav);

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
////			return new ModelAndView("redirect:" + referer + "?login");
////		}
//
//		ViewApp vi = new ViewApp(rq,dtserver.getApiUrl());
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
			return new ModelAndView("redirect:" + referer + "?login");
		}

		dtserver.ViewApp().addView("HEAD");
		dtserver.ViewApp().addView("HEADER_CATEGORIAS");
		
		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());

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
			return new ModelAndView("redirect:/404");

		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/404");

		}

		switch (scmenuurlsub.getTipo()) {
		case 20: // information
			System.out.println("Tipo 20"); // TIPO MI CARTOLA
			dtserver.ViewApp().addView("MI-CARTOLA");
			dtserver.ViewApp().addView("FOOTER");
			mav = new ModelAndView(dtserver.ViewApp().render());
			mav.addObject("cartola", dtserver.loadUserCartola());
			break;
		case 21: // information
			System.out.println("Tipo 21"); // TIPO INSCRIPCCION
			dtserver.ViewApp().addView("mis-inscripciones");
			dtserver.ViewApp().addView("FOOTER");
			mav = new ModelAndView(dtserver.ViewApp().render());
			mav.addObject("inscripciones", Emudata.getInscripciones());
			break;
		case 22: // information
			System.out.println("Tipo 22"); // TIPO MIS CUPONES
			dtserver.ViewApp().addView("mis-cupones");
			dtserver.ViewApp().addView("FOOTER");
			mav = new ModelAndView(dtserver.ViewApp().render());
			System.out.println("Mis cupones: usr: " + credentialUser.getScotiauser().getId_cliente());
			mav.addObject("usercupones", dtserver.loadCupones(credentialUser.getScotiauser().getId_cliente()));

//			scmenuurlsub.informationsubmenu = Emudata.getInformatiotest();
			break;
		case 23: // information
			System.out.println("Tipo 23"); // TIPO MIS GUSTOS
			dtserver.ViewApp().addView("mis-preferencias");
			dtserver.ViewApp().addView("FOOTER");
			mav = new ModelAndView(dtserver.ViewApp().render());
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
			dtserver.ViewApp().addView("transfiere-scotiapesos");
			dtserver.ViewApp().addView("FOOTER");
			mav = new ModelAndView(dtserver.ViewApp().render());
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

		this.setHeaderx(mav);

		return mav;

	}

	@GetMapping("/information/{nombreInformation}")
	public ModelAndView getinformation(@PathVariable("nombreInformation") String nombreInformation,
			HttpServletRequest rq) throws UnsupportedEncodingException {
//		ModelAndView mav = new ModelAndView("user");
		dtserver.ViewApp().addView("HEAD");
		dtserver.ViewApp().addView("INFORMATION");
		dtserver.ViewApp().addView("FOOTER");

		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());

		Information informationhtml = new Information();

		informationhtml = dtserver.loadInformationByName(nombreInformation);
		System.out.println("inforxn" + informationhtml);
		if (informationhtml == null) {
			return new ModelAndView("redirect:/404");
		}

		mav.addObject("informationhtml", informationhtml);

		this.setHeaderx(mav);

		return mav;

	}

	@PostMapping("/user/login")
	public ModelAndView loginuser(@ModelAttribute("loginForm") LoginUser loginForm, HttpServletRequest rq) {
//		ModelAndView mav = new ModelAndView("user");
		dtserver.ViewApp().addView("HEAD");
//		vi.addView("INFORMATION");
		dtserver.ViewApp().addView("FOOTER");

		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());

		System.out.println("infologin: " + loginForm);

		String resultlogin = dtserver.testLogin(loginForm.getRut(), loginForm.getPass());
		System.out.println("result_login:" + resultlogin);

		if (resultlogin != null) { // token de sesion devuelto

		} else {

		}

		this.setHeaderx(mav);

		return mav;

	}

	@GetMapping("/cupon/get/{id_reward}")
	public ModelAndView getCuponByRew(@PathVariable("id_reward") int id_reward, HttpServletRequest rq,
			@RequestHeader(value = "referer", required = false) final String referer)
			throws UnsupportedEncodingException {
//		ModelAndView mav = new ModelAndView("user");

		CredencialesEntity credentialUser = new CredencialesEntity();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
		if (!resolver.isAnonymous(auth)) {
			credentialUser = (CredencialesEntity) auth.getPrincipal();
		} else {
			System.out.println("User no login");
			return new ModelAndView("redirect:" + referer + "?login");
		}

		dtserver.ViewApp().addView("HEAD");
		dtserver.ViewApp().addView("INFORMATION");
		dtserver.ViewApp().addView("FOOTER");

		ModelAndView mav = new ModelAndView(dtserver.ViewApp().render());

		this.setHeaderx(mav);

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
			return new ModelAndView("redirect: /?login");
		}
		byte[] response = dtserver.loadCuponAsPdf(credentialUser.getScotiauser().getId_cliente(), id_rew);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new ByteArrayResource(response));
	}
	
	@PostMapping("/gustos/actualizar")
	public ModelAndView actualizarGustos(@RequestParam(value = "gusto", required = false) String[] gustos) {
		System.out.println("Se llega al front");
		if (gustos == null) {
			gustos = new String[0];
		}
		System.out.println("consultando al dtserver");
		String success = dtserver.saveCustomerGustos(gustos);
		return null;
	}

}
