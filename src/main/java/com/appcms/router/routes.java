package com.appcms.router;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import com.appcms.entity.CanjeProducto;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scsubmenu;
import com.appcms.model.DataServer;
import com.appcms.model.Emudata;
import com.cms.views.ViewApp;





@Controller
public class routes {
	
	public final String csrf_token = "afxn123xnx360";
	
	@RequestMapping("/home")
	public ModelAndView index(HttpServletRequest rq)
	{
		ViewApp vi=new ViewApp(rq);
		vi.addView("head");
		vi.addView("home");
		vi.addView("footer");
		
		ModelAndView mav = new ModelAndView(vi.render());
		this.setHeaderx(mav,rq);
		return mav;
	}

//	public void setHeaderx(ModelAndView mav) {
//
//		mav.addObject("menuesHeader", Emudata.getmenuCategorias());
//
//		mav.addObject("usuario", Emudata.getUsusario());
//
//	}
	public void setHeaderx(ModelAndView mav,HttpServletRequest rq) {
	
		
		DataServer dtserver = new DataServer(rq);
		mav.addObject("menuesHeader", dtserver.loadScmenu());
//		mav.addObject("menuesHeader", Emudata.getmenuCategorias());
	
		mav.addObject("usuario", Emudata.getUsusario());
//		 mav.addObject("usuario",Emudata.getUsusarioOff());
	}
	
	
	@RequestMapping("/test")
	public String groovy()
	{
		return "index";
	}
	
	@RequestMapping("/404")
	public ModelAndView notfound(HttpServletRequest rq)
	{
		
		ViewApp vi=new ViewApp(rq);
		vi.addView("header");
		vi.addView("404");
		ModelAndView mav = new ModelAndView(vi.render());
		return mav;
	}
	
	
	
	
	

	@ExceptionHandler(value = {Exception.class,MultipartException.class,NestedServletException.class,NestedServletException.class,ConnectException.class })
	@RequestMapping("/errores")
	public String error()
	{
		return "error";
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest rq)
	{
		ViewApp vi=new ViewApp(rq);
		vi.addView("header");
		vi.addView("login");
		ModelAndView mav = new ModelAndView(vi.render());
		return mav;
	}
	
	@RequestMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){   
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";
    }

	@RequestMapping("/admin")
	public ModelAndView admin(HttpServletRequest rq)
	{
		ViewApp vi=new ViewApp(rq);
		vi.addView("header");
		vi.addView("admin");
		ModelAndView mav = new ModelAndView(vi.render());
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/")
	public ModelAndView home(HttpServletRequest rq)
	{
		//return new ModelAndView("redirect:/home");
		ViewApp vi=new ViewApp(rq);
		vi.addView("head");
		//vi.addView("home");
		vi.addView("index");
		vi.addView("footer");
		
		ModelAndView mav = new ModelAndView(vi.render());
		mav.addObject("banners", Emudata.getBanners());
		this.setHeaderx(mav,rq);

		return mav;
	}
	@GetMapping("/categoria/{menu}/{submenu}")
	public ModelAndView menuSubmenu(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,HttpServletRequest rq)
			throws UnsupportedEncodingException {
		//ModelAndView mav = new ModelAndView("categorias");
		ViewApp vi=new ViewApp(rq);
		
		DataServer dtserver = new DataServer(rq);
		
		Scmenu scmenuurl = new Scmenu();
		Scsubmenu scmenuurlsub = new Scsubmenu();
		

		List<Scmenu> categiriasmenu = new ArrayList<>();
		categiriasmenu = dtserver.loadScmenu();// Emudata.getmenuCategorias();

		for (Scmenu menusel : categiriasmenu) // buscamos el menu que seleccionó
		{
			if (menusel.getStrIndex().equalsIgnoreCase(menu)) {
				scmenuurl = menusel;
				break;
			}
		}
		if (scmenuurl != null) {
			for (Scsubmenu scmenuurlsubtemp : scmenuurl.getSubmenues()) // buscamos el submenu que seleccionó
			{
				if (scmenuurlsubtemp.getStrIndex().equalsIgnoreCase(submenu)) {
					scmenuurlsub = scmenuurlsubtemp;
					break;
				}
			}
		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/");
		}

		switch (scmenuurlsub.getTipo()) {
		case 1: // information
			System.out.println("Tipo 1"); // TIPO INFORMACION
			scmenuurlsub.informationsubmenu = dtserver.loadInformatioSub(scmenuurlsub.getId());// Emudata.getInformatiotest();
			break;
		case 2:
			System.out.println("Tipo 2"); // TIPO PRODUCTO CON LIKE
			scmenuurlsub.productosLikeLista = Emudata.getProductosLikeTest();
			break;
		case 3:
			System.out.println("Tipo 3"); // TIPO CON CUPON
			scmenuurlsub.productosLikeLista = Emudata.getProductosiNFOTest();
			break;
		case 4:
			System.out.println("Tipo 4"); // TIPO PRODUCTO E-COMERCE
			scmenuurlsub.productosLikeLista = Emudata.getProductoseEcomerceTest();
			break;
		case 5:
			System.out.println("Tipo 5"); // TIPO CANJE CON CATEGORIAS
			scmenuurlsub.categoriaProductoLista = Emudata.getCategoriasProductosTest();
			break;
		case 6:
			System.out.println("Tipo 6"); // TIPO CANJE CON CATEGORIAS PARA FORMULARIO
			scmenuurlsub.categoriaProductoLista = Emudata.getCategoriasProductosTestTipo6();
			break;
		case 7:
			System.out.println("Tipo 7"); // TIPO CANJE CASHBACK
			scmenuurlsub.TarjetaClienteLista = Emudata.getTarjetasCliente();
			break;
		case 8:
			System.out.println("Tipo 8"); // TIPO CANJE DESCUENTOS
			scmenuurlsub.productosLikeLista = Emudata.getProductosLikeTest();
			break;
		case 9:
			System.out.println("Tipo 9"); // TIPO VISTA INFORMATION
			scmenuurlsub.informationHtml = Emudata.getInformationHtml();
			break;
		}

		vi.addView("head");
		vi.addView("HEADER_CATEGORIAS");
		vi.addView("CATEGORIAS");
		vi.addView("footer");
		
		ModelAndView mav = new ModelAndView(vi.render());
		mav.addObject("menuurl", scmenuurl);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav,rq);

		return mav;
	}

	@GetMapping("/categoria/{menu}/{submenu}/productos/{categoria}")
	public ModelAndView menuProductoCategoria(@PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu, @PathVariable("categoria") String categoria,HttpServletRequest rq)
			throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("categorias");

		Scmenu scmenuurl = new Scmenu();
		Scsubmenu scmenuurlsub = new Scsubmenu();

		List<Scmenu> categiriasmenu = new ArrayList<>();
		categiriasmenu = Emudata.getmenuCategorias();

		for (Scmenu menusel : categiriasmenu) // buscamos el menu que seleccionó
		{
			if (menusel.getStrIndex().equalsIgnoreCase(menu)) {
				scmenuurl = menusel;
				break;
			}
		}
		if (scmenuurl != null) {
			for (Scsubmenu scmenuurlsubtemp : scmenuurl.getSubmenues()) // buscamos el submenu que seleccionó
			{
				if (scmenuurlsubtemp.getStrIndex().equalsIgnoreCase(submenu)) {
					scmenuurlsub = scmenuurlsubtemp;
					break;
				}
			} 
		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/");
		}

		switch (scmenuurlsub.getTipo()) { // SI O SI TIENE QUE SER SUB CATEGORIA TIPO 5 o 6 PARA TENER PRODUCTOS A LA
											// CATEGORIAPRODUCTOS ASOCIADA
		case 5:
			System.out.println("Tipo 5"); // TIPO CANJE CON CATEGORIAS
			scmenuurlsub.categoriaProductoLista = Emudata.getCateProductosFromCategoria(categoria);
			mav.addObject("verProductosCategoria", true);
			break;

		case 6:
			System.out.println("Tipo 6"); // TIPO CANJE CON CATEGORIAS FORMULARIO
			//se pasa la categoria para seleccionar el primer producto de ella, deberia siempre tener 1 producto MAXIMO por categoria tipo fomrulario
			
			scmenuurlsub.productosLikeLista = Emudata.getProductosLikeTest();
			mav.addObject("producto", new CanjeProducto());
			mav.addObject("verProductosCategoria", true);
			break;
		case 8:
			System.out.println("Tipo 8"); // TIPO CANJE CON CATEGORIAS
			scmenuurlsub.categoriaProductoLista = Emudata.getCateProductosFromCategoria(categoria);
			mav.addObject("verProductosCategoria", true);
			break;
		default:
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/");
		}

		mav.addObject("menuurl", scmenuurl);
		mav.addObject("submenuurl", scmenuurlsub);
		mav.addObject("csrf_token", csrf_token);
		this.setHeaderx(mav,rq);

		return mav;
	} 

	@GetMapping("/categoria/{menu}/{submenu}/detalle/{producto}")
	public ModelAndView menuDetalleProducto(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,
			@PathVariable("producto") String producto,HttpServletRequest rq) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("canjes");

		Scmenu scmenuurl = new Scmenu();
		Scsubmenu scmenuurlsub = new Scsubmenu();

		List<Scmenu> categiriasmenu = new ArrayList<>();
		categiriasmenu = Emudata.getmenuCategorias();

		for (Scmenu menusel : categiriasmenu) // buscamos el menu que seleccionó
		{
			if (menusel.getStrIndex().equalsIgnoreCase(menu)) {
				scmenuurl = menusel;
				break;
			}
		}
		if (scmenuurl != null) {
			for (Scsubmenu scmenuurlsubtemp : scmenuurl.getSubmenues()) // buscamos el submenu que seleccionó
			{
				if (scmenuurlsubtemp.getStrIndex().equalsIgnoreCase(submenu)) {
					scmenuurlsub = scmenuurlsubtemp;
					break;
				}
			}
		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/");
		}

		scmenuurlsub.productosLikeLista = Emudata.getProductoSearch(producto);

		mav.addObject("csrf_token", csrf_token);
		mav.addObject("menuurl", scmenuurl);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav,rq);

		return mav;
	}

	@PostMapping("/categoria/{menu}/{submenu}/canje/")
	public ModelAndView menuCanje(@ModelAttribute("producto") CanjeProducto producto, @PathVariable("menu") String menu,
			@PathVariable("submenu") String submenu,HttpServletRequest rq) {
		ModelAndView mav = new ModelAndView("canjes");

		Scmenu scmenuurl = new Scmenu();
		Scsubmenu scmenuurlsub = new Scsubmenu();

		List<Scmenu> categiriasmenu = new ArrayList<>();
		categiriasmenu = Emudata.getmenuCategorias();

		for (Scmenu menusel : categiriasmenu) // buscamos el menu que seleccionó
		{
			if (menusel.getStrIndex().equalsIgnoreCase(menu)) {
				scmenuurl = menusel;
				break;
			}
		}
		if (scmenuurl != null) {
			for (Scsubmenu scmenuurlsubtemp : scmenuurl.getSubmenues()) // buscamos el submenu que seleccionó
			{
				if (scmenuurlsubtemp.getStrIndex().equalsIgnoreCase(submenu)) {
					scmenuurlsub = scmenuurlsubtemp;
					break;
				}
			}
		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/");
		}

		System.out.println(producto.toString());

		if (!producto.getCsrf_token().equalsIgnoreCase(csrf_token)) {
			System.out.println("Error csrf_token");
			scmenuurlsub.setTipo(0);
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
			// efectuar canje, datos en objeto "producto"
			mav.addObject("canjeExito", true);
			break;
		case 5: // TIPO CANJE CON CATEGORIAS
			if (producto.getActionx().equalsIgnoreCase("finish")) {
				mav.addObject("canjeExito", true);
			} else {
				producto.setActionx("finish");
			}
			scmenuurlsub.productosLikeLista = Emudata.getProductoSearchById(producto.getIdProducto());
			mav.addObject("producto", producto);

			break;
		case 6: // TIPO CANJE CON CATEGORIAS PARA FORMULARIO
			if (producto.getActionx().equalsIgnoreCase("finish")) {
				mav.addObject("canjeExito", true);
			} else {
				producto.setActionx("finish");
			}
			
			scmenuurlsub.productosLikeLista = Emudata.getProductoSearchById(producto.getIdProducto());
			mav.addObject("producto", producto);
			break;
		case 7: // TIPO CANJE CASHBACK
			break;
		case 8: // TIPO CANJE DESCUENTOS
			scmenuurlsub.productosLikeLista = Emudata.getProductoSearchById(producto.getIdProducto());
			mav.addObject("producto", producto);
			mav.addObject("canjeExito", true);
			break;
		default:
			System.out.println("Seccion fuera de menu");
			return new ModelAndView("redirect:/");
		}
		mav.addObject("csrf_token", csrf_token);
		mav.addObject("menuurl", scmenuurl);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav,rq);

		return mav;
	}

	@GetMapping("/user/{menu}/{submenu}")
	public ModelAndView menuUser(@PathVariable("menu") String menu, @PathVariable("submenu") String submenu,HttpServletRequest rq)
			throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("user");

		Scmenu scmenuurl = new Scmenu();
		Scsubmenu scmenuurlsub = new Scsubmenu();

		List<Scmenu> categiriasmenu = new ArrayList<>();
		categiriasmenu = Emudata.getmenuCategorias();

		for (Scmenu menusel : categiriasmenu) // buscamos el menu que seleccionó
		{
			if (menusel.getStrIndex().equalsIgnoreCase(menu)) {
				scmenuurl = menusel;
				break;
			}
		}
		if (scmenuurl != null) {
			for (Scsubmenu scmenuurlsubtemp : scmenuurl.getSubmenues()) // buscamos el submenu que seleccionó
			{
				if (scmenuurlsubtemp.getStrIndex().equalsIgnoreCase(submenu)) {
					scmenuurlsub = scmenuurlsubtemp;
					break;
				}
			}
		}

		if (scmenuurlsub.getId() == 0) {
			System.out.println("Seccion no encontrada");
			return new ModelAndView("redirect:/");
		}

		switch (scmenuurlsub.getTipo()) {
		case 20: // information
			System.out.println("Tipo 20"); // TIPO MI CARTOLA
			mav.addObject("cartola", Emudata.getUserCartola());
			break;
		case 21: // information
			System.out.println("Tipo 21"); // TIPO INSCRIPCCION
			mav.addObject("inscripciones", Emudata.getInscripciones());
			break;
		case 22: // information
			System.out.println("Tipo 22"); // TIPO MIS CUPONES
			scmenuurlsub.informationsubmenu = Emudata.getInformatiotest();
			break;
		case 23: // information
			System.out.println("Tipo 23"); // TIPO MIS GUSTOS
			mav.addObject("gustos", Emudata.getGustos());
			mav.addObject("gustosUser", Emudata.getGustos());
			break;
		case 24: // information
			System.out.println("Tipo 24"); // TIPO TRANSFERIR
			scmenuurlsub.informationsubmenu = Emudata.getInformatiotest();
			break;
		default:
			System.out.println("Seccion fuera de menu");
			return new ModelAndView("redirect:/");
		}

		mav.addObject("menuurl", scmenuurl);
		mav.addObject("submenuurl", scmenuurlsub);

		this.setHeaderx(mav,rq);

		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
