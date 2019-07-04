package com.appcms.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.HtmlUtils;

import com.appcms.entity.Banner;
import com.appcms.entity.CredencialesEntity;
import com.appcms.entity.FormatoDetalle;
import com.appcms.entity.Information;
import com.appcms.entity.Menutop;
import com.appcms.entity.ProductoCategoria;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.Scinformacionsubmenu;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.Scsubmenu;
import com.appcms.entity.TarjetaCliente;
import com.appcms.entity.UserCartola;
import com.appcms.entity.UserCartolaMovimiento;
import com.appcms.entity.UserGusto;
import com.appcms.entity.UserInscripcion;
import com.appcms.entity.points.ExpiringPoints;
import com.appcms.entity.points.Points;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Component
public class Emudata {
	
	private static String apiUrl;
	
	@Autowired
	public Emudata(@Qualifier("apiUrl") String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	public static List<Menutop> getmenuHeader() {

		List<Menutop> menuscotia = new ArrayList<>();

		menuscotia.add(new Menutop("Home", "index", 1, null));

		List<Menutop> msubmenu1 = new ArrayList<>();
		msubmenu1.add(new Menutop("Restorando", "index", 1, null));
		msubmenu1.add(new Menutop("Ruta Gourmet", "index", 1, null));
		msubmenu1.add(new Menutop("Ruta Pub", "index", 1, null));
		msubmenu1.add(new Menutop("E-commerce", "index", 1, null));
		msubmenu1.add(new Menutop("Productos", "index", 1, null));
		menuscotia.add(new Menutop("Platos y copas", "index", 1, msubmenu1));

		List<Menutop> msubmenu2 = new ArrayList<>();
		msubmenu2.add(new Menutop("Scotiapesos", "index", 1, null));
		msubmenu2.add(new Menutop("Giftcard Restobares", "index", 1, null));
		msubmenu2.add(new Menutop("Giftcard Retail", "index", 1, null));
		msubmenu2.add(new Menutop("Giftcard Comercio", "index", 1, null));
		msubmenu2.add(new Menutop("Cambia tus ScotiaPesos", "index", 1, null));
		menuscotia.add(new Menutop("Mis Canjes", "index", 1, msubmenu2));

		menuscotia.add(new Menutop("Viajes", "index", 1, null));

		List<Menutop> msubmenu3 = new ArrayList<>();
		msubmenu3.add(new Menutop("Sorteos", "index", 1, null));
		msubmenu3.add(new Menutop("Subastas", "index", 1, null));
		menuscotia.add(new Menutop("Concursos", "index", 1, msubmenu3));

		List<Menutop> msubmenu4 = new ArrayList<>();
		msubmenu4.add(new Menutop("Descuentos", "index", 1, null));
		msubmenu4.add(new Menutop("ClubKids", "index", 1, null));
		msubmenu4.add(new Menutop("ClubPets", "index", 1, null));
		msubmenu4.add(new Menutop("ClubHincha", "index", 1, null));
		msubmenu4.add(new Menutop("ClubGamer", "index", 1, null));
		msubmenu4.add(new Menutop("Emprededor", "index", 1, null));
		menuscotia.add(new Menutop("Mundos", "index", 1, msubmenu4));

		List<Menutop> msubmenu5 = new ArrayList<>();
		msubmenu5.add(new Menutop("Acerca de Scoticlub", "index", 1, null));
		msubmenu5.add(new Menutop("Preguntas Frecuentes", "index", 1, null));
		menuscotia.add(new Menutop("Ayuda", "index", 1, msubmenu5));

		return menuscotia;
	}
	
	

	
	public static List<Scmenu> getmenuCategorias() {

		List<Scmenu> menuscotia = new ArrayList<>();

		menuscotia.add(new Scmenu("home",1, "Home", "#fff", 1, "/index", "2019-01-01", "2019-01-01", 1,null));
		
		List<Scsubmenu> msubmenu1 = new ArrayList<>();
		msubmenu1.add(new Scsubmenu("restorando",1,"Restorando","/restorando","#008080","hover-green-bg","#008080",1,"/resource/images/dish.png","/resource/sections/gourmet.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("rutapub",2,"Ruta Gourmet","/rutapub","#d33195","hover-pink-bg","#d33195",2,"/resource/images/chef.png","/resource/sections/gourmet.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("ecomerce",3,"Ruta Pub","/ecomerce","#039fd3","hover-blue-bg","#039fd3",2,"/resource/images/beer.png","/resource/sections/pub.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("productos",4,"E-commerce Gastronomía","/productos","#533dc1","hover-purple-bg","#533dc1",3,"/resource/images/shopping-bag.png","/resource/sections/coffee.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("platos",5,"Productos","/platos","#ec121f","hover-red-bg","#ec121f",4,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		menuscotia.add(new Scmenu("platosycomida",2,"Platos y copas", "#ec121f", 1, "/categoria/platosycomida", "2019-01-01", "2019-01-01", 1, msubmenu1));

		List<Scsubmenu> msubmenu2 = new ArrayList<>();
		msubmenu2.add(new Scsubmenu("scotiapesos",1,"Scotiapesos","/scotiapesos","#039fd3","hover-blue-bg","#039fd3",6,"/resource/images/value.png","/resource/sections/scotiapesos.jpg","Canjea tus Puntos donde y como quieras.","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras feugiat urna elit. Mauris vel feugiat urna. Suspendisse ","2018-12-11 18:15:04","2019-02-13 20:39:54",1));		
		msubmenu2.add(new Scsubmenu("gfrestobares",1,"Giftcard Restobares","/gfrestobares","#d33195","hover-pink-bg","#d33195",5,"/resource/images/value.png","/resource/sections/giftcard.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu2.add(new Scsubmenu("gfretail",1,"Giftcard Retail","/gfretail","#fa6400","hover-orange-bg","#fa6400",5,"/resource/images/retail.png","/resource/sections/giftcard.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu2.add(new Scsubmenu("gfcomercio",1,"Giftcard Comercio","/gfcomercio","#533dc1","hover-purple-bg","#533dc1",5,"/resource/images/shop.png","/resource/sections/giftcard.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu2.add(new Scsubmenu("cambiascotiapesos",1,"Cambia tus Scotiapesos a Puntos Cencosud","/cambiascotiapesos","#ec121f","hover-red-bg","#ec121f",7,"/resource/images/transfer.png","/resource/sections/cencosud.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		menuscotia.add(new Scmenu("miscanjes","Mis Canjes", "/categoria/miscanjes", msubmenu2));

		menuscotia.add(new Scmenu("scotiapesos","Viajes", "index", null));

		List<Scsubmenu> msubmenu3 = new ArrayList<>();
		msubmenu3.add(new Scsubmenu("sorteos","Sorteos", "index"));
		msubmenu3.add(new Scsubmenu("subasta","Subastas", "index"));
		menuscotia.add(new Scmenu("concursos","Concursos", "index",  msubmenu3));

		List<Scsubmenu> msubmenu4 = new ArrayList<>();
		msubmenu4.add(new Scsubmenu("descuentos",1,"Descuentos","/descuentos","#fa6400","hover-orange-bg","#fa6400",8,"/resource/images/percentage.png","/resource/sections/descuentos.jpg","Canjea tus Puntos donde y como quieras.","Mauris vel feugiat urna. Suspendisse ","2018-12-11 18:15:04","2019-02-13 20:39:54",1));	
		msubmenu4.add(new Scsubmenu("clubkids",1,"ClubKids","/clubkids","#d33195","hover-pink-bg","#d33195",8,"/resource/images/abc-block.png","/resource/sections/kids.jpg","Canjea tus Puntos donde y como quieras.","Mauris vel feugiat urna. Suspendisse ","2018-12-11 18:15:04","2019-02-13 20:39:54",1));	
		msubmenu4.add(new Scsubmenu("clubPets",1,"ClubPets","/clubPets","#039fd3","hover-blue-bg","#039fd3",8,"/resource/images/track.png","/resource/sections/pets.jpg","Canjea tus Puntos donde y como quieras.","Mauris vel feugiat urna. Suspendisse ","2018-12-11 18:15:04","2019-02-13 20:39:54",1));	
		msubmenu4.add(new Scsubmenu("clubHincha",1,"ClubHincha","/clubHincha","#533dc1","hover-purple-bg","#533dc1",8,"/resource/images/footballe.png","/resource/sections/hincha.jpg","Canjea tus Puntos donde y como quieras.","Mauris vel feugiat urna. Suspendisse ","2018-12-11 18:15:04","2019-02-13 20:39:54",1));	
		msubmenu4.add(new Scsubmenu("clubGamer",1,"ClubGamer","/clubGamer","#ec121f","hover-red-bg","#ec121f",8,"/resource/images/joystick.png","/resource/sections/gamer.jpg","Canjea tus Puntos donde y como quieras.","Mauris vel feugiat urna. Suspendisse ","2018-12-11 18:15:04","2019-02-13 20:39:54",1));	
		msubmenu4.add(new Scsubmenu("emprededor",1,"Emprendedores","/emprededor","#008080","hover-green-bg","#008080",8,"/resource/images/woman.png","/resource/sections/emprendedor.jpg","Canjea tus Puntos donde y como quieras.","Mauris vel feugiat urna. Suspendisse ","2018-12-11 18:15:04","2019-02-13 20:39:54",1));	
		menuscotia.add(new Scmenu("mundos","Mundos", "/categoria/mundos",  msubmenu4));

		List<Scsubmenu> msubmenu5 = new ArrayList<>();		
		msubmenu5.add(new Scsubmenu("acercascotia",1,"Acerca de Scoticlub","/acercascotia","#ec121f","hover-red-bg","#ec121f",9,"/resource/images/info.png","/resource/sections/ayuda.jpg","","","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu5.add(new Scsubmenu("preguntasfrecuentes",1,"Preguntas Frecuentes","/preguntasfrecuentes","#039fd3","hover-blue-bg","#039fd3",9,"/resource/images/question.png",	"/resource/sections/FAQ.jpg","","","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		menuscotia.add(new Scmenu("ayuda","Ayuda", "/categoria/ayuda", msubmenu5));

		
		List<Scsubmenu> msubmenu6 = new ArrayList<>();		
		msubmenu6.add(new Scsubmenu("micartola",1,"Mi cartola","/micartola","#ec121f","hover-red-bg","#ec121f",20,"/resource/images/profiles.png","/resource/sections/cartola.jpg","","","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu6.add(new Scsubmenu("misinscripciones",1,"Mis Inscripciones","/misinscripciones","#039fd3","hover-blue-bg","#039fd3",21,"/resource/images/profiles.png","/resource/sections/cartola.jpg","","","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		
		msubmenu6.add(new Scsubmenu("miscupones",1,"Mis cupones","/miscupones","#fa6400","hover-orange-bg","#fa6400",22,"/resource/images/checklist.png","/resource/sections/cartola.jpg","","","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu6.add(new Scsubmenu("misgustos",1,"Mis gustos","/misgustos","#d33195","hover-pink-bg","#d33195",23,"/resource/images/configuration.png","/resource/sections/cartola.jpg","","","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu6.add(new Scsubmenu("transferir",1,"Transferir","/transferir","#008080","hover-green-bg","#008080",24,"/resource/images/givemoney.png","/resource/sections/cartola.jpg","","","2018-12-11 18:15:04","2019-02-13 20:39:54",1,true));
		
		
//		StringBuilder sb = new StringBuilder("");
//		HttpServletRequest rqx = null;
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//		RestAuthentication xrestAuthentication = new RestAuthentication();
//        System.out.println(xrestAuthentication.getTOKENONE()+" 666666666666666666666666666666666666666");
//		headers.set("Authorization", rqx.getSession().getAttribute("TOKENONE").toString());
//
//		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<Scmenu> response = restTemplate.exchange(Emudata.serverIp+"/cmsrest/get/scmenu", HttpMethod.GET, httpEntity, Scmenu.class);
//		if (response.getStatusCodeValue() == 200) {			
//			System.out.println(response.getBody() +"--------------------------------------------------");
//		} else {
//			System.out.println("no resp");
//		}
		
		
		
		
		
		
		Scmenu cuenta = new Scmenu("account","Cuenta", "/user/account", msubmenu6);
		cuenta.setUserElement(true);
		menuscotia.add(cuenta);
		
		return menuscotia;
	}
	
	
	
	public static List<Banner> getBanners() {
		List<Banner> banners = new ArrayList<>();
		banners.add(new Banner(1, "/resource/home-slider/comida2.jpg", "#", false));
		banners.add(new Banner(2, "/resource/home-slider/nino.jpg", "#", false));
		banners.add(new Banner(3, "/resource/home-slider/landscape.jpg", "#", false));
		return banners;
	}
	
	public static Scotiauser getUsusario() {
		Scotiauser usuario = new Scotiauser(2, "177824577", "Fabian", "Gaete", "fgaete@afiniti.cl","1");		
		return usuario;
	}
	public static Scotiauser getUsusarioOff() {
		Scotiauser usuario = new Scotiauser();		
		return usuario;
	}
	public static int getPoints() {		
		return 10000;
	}
	
	public static int getCategoria() {		
		return 10000;
	}
	
	public static Scinformacionsubmenu getInformatiotest() {
		Scinformacionsubmenu information = new Scinformacionsubmenu(1,1,"Restorado8",1,"/resource/images/woman-computer.jpg","Obtén desde un","15% dcto. en restaurantes","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","#","Reserva acá","[\"Restorando les ofrece a miles de comensales la posibilidad de descubrir miles de lugares para salir a comer, acceder a ofertas y beneficios en tiempo real y asegurar su mesa sin tener que esperar para sentarse.\",\"Restorando trabaja junto con los restaurantes para mejorar las experiencias gastron\\u00f3micas de los comensales en latinoam\\u00e9rica.\",\"XXC\",\"t2\"]","2018-12-11 18:15:04","2019-02-13 18:03:57",1);
		
		String json = information.getJson_condiciones();
		JsonArray jsonObject = new JsonParser().parse(json).getAsJsonArray();

	        JsonArray arr = jsonObject.getAsJsonArray();
	        for (int i = 0; i < arr.size(); i++) {
	            String post_id = arr.get(i).getAsString();
	            information.condicioneslista.add(post_id);
	            //System.out.println(post_id);
	        }
		
		return information;
	}
	
	public static List<ProductoTipoLike> getProductosLikeTest() {
		 List<ProductoTipoLike> prodlist =  new ArrayList<>();
		 prodlist.add( new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		 prodlist.add( new ProductoTipoLike(2, "Danieli v1", "Danieli 2", "Coffee Bar", "/resource/images/ver_img.png", "25%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 10) );
		 prodlist.add( new ProductoTipoLike(3, "Danieli v1", "Test 3", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 30) );
		 prodlist.add( new ProductoTipoLike(4, "Danieli v1", "Prod xn", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 100) );
	 
		 return prodlist;
	}
	
	public static List<ProductoTipoLike> getProductosiNFOTest() {
		 List<ProductoTipoLike> prodlist =  new ArrayList<>();
		 prodlist.add( new ProductoTipoLike(5,"Pastelería Mafe","Pastelería Mafe","15% de descuento","/resource/images/mafe.jpg") );
		 prodlist.add( new ProductoTipoLike(6,"Pastelería Mafe","Pastelería Mafe2","25% de descuento","/resource/images/mafe.jpg") );
		 prodlist.add( new ProductoTipoLike(7,"Pastelería Mafe","Pastelería Mafe3","45% de descuento","/resource/images/mafe.jpg") );
		 return prodlist;
	}
	
	public static List<ProductoTipoLike> getProductoseEcomerceTest() {
		 List<ProductoTipoLike> prodlist =  new ArrayList<>();
		 prodlist.add( new ProductoTipoLike(8,"Aderezo Machitún",5900,"/resource/images/olive-oil-salad-dressing-cooking-olive.jpg") );
		 prodlist.add( new ProductoTipoLike(9,"Aderezo Machitún2",900,"/resource/images/olive-oil-salad-dressing-cooking-olive.jpg") );
		 prodlist.add( new ProductoTipoLike(10,"Aderezo Machitún3",2500,"/resource/images/olive-oil-salad-dressing-cooking-olive.jpg") );
		 prodlist.add( new ProductoTipoLike(11,"Aderezo Machitún3",2500,"/resource/images/olive-oil-salad-dressing-cooking-olive.jpg") );
		 prodlist.add( new ProductoTipoLike(12,"Aderezo Machitún3",1500,"/resource/images/olive-oil-salad-dressing-cooking-olive.jpg") );
		 return prodlist;
	}
	
	public static List<ProductoTipoLike> getProductoSearch(String prod) {
		 List<ProductoTipoLike> prodlist =  new ArrayList<>();
		 
		 List<FormatoDetalle> detalles = new ArrayList<>();
		 List<FormatoDetalle> direcciones = new ArrayList<>();
		 detalles.add(new FormatoDetalle("Condiciones","Beneficio no acumulable con otras promociones y/o descuentos.",1));
		 detalles.add(new FormatoDetalle("¿Qué es Mafe Pastelería?","Beneficio no acumulable con otras promociones y/o descuentos.",1));
		 detalles.add(new FormatoDetalle("¿Cómo accedo a este descuento?","Beneficio no acumulable con otras promociones y/o descuentos.",1));
		 
		 direcciones.add(new FormatoDetalle("Dirección Web:","http://facebook.com/MafePasteleria/",2));
		 direcciones.add(new FormatoDetalle("Dirección y Teléfono:","Av Macul 3226, Macul, Región Metropolitana",1));
		 
		 prodlist.add( new ProductoTipoLike(8, "Aderezo Machitún", "Aderezo Machitún", "En accesorios y en ropa interior (excluye calzado).","/resource/images/wados.jpg",
					"25", 9999, 10000, "Lorem ipsum dolor sit amet","Vivamus vulputate dui",detalles,direcciones) );
		 return prodlist;
	}
	public static List<ProductoTipoLike> getProductoSearchById(int prod) {
		 List<ProductoTipoLike> prodlist =  new ArrayList<>();
		 
		 List<FormatoDetalle> detalles = new ArrayList<>();
		 List<FormatoDetalle> direcciones = new ArrayList<>();
		 detalles.add(new FormatoDetalle("Condiciones","Beneficio no acumulable con otras promociones y/o descuentos.",1));
		 detalles.add(new FormatoDetalle("¿Qué es Mafe Pastelería?","Beneficio no acumulable con otras promociones y/o descuentos.",1));
		 detalles.add(new FormatoDetalle("¿Cómo accedo a este descuento?","Beneficio no acumulable con otras promociones y/o descuentos.",1));
		 
		 direcciones.add(new FormatoDetalle("Dirección Web:","http://facebook.com/MafePasteleria/",2));
		 direcciones.add(new FormatoDetalle("Dirección y Teléfono:","Av Macul 3226, Macul, Región Metropolitana",1));
		 
		 prodlist.add( new ProductoTipoLike(8, "Aderezo Machitún", "Aderezo Machitún", "En accesorios y en ropa interior (excluye calzado).","/resource/images/wados.jpg",
					"25", 9999, 10000, "Lorem ipsum dolor sit amet","Vivamus vulputate dui",detalles,direcciones) );
		 return prodlist;
	}
	
	public static List<ProductoCategoria> getCategoriasProductosTest() {
		 List<ProductoCategoria> catelist =  new ArrayList<>();
		 String descripTest = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras feugiat urna elit. Mauris vel feugiat urna. Suspendisse finibus varius tortor in fermentum. Pellentesque laoreet commodo eleifend. Quisque blandit ex nec lobortis iaculis. Sed aliquet tempus augue, nec egestas urna semper quis. Aliquam lacus ante, rhoncus nec dictum vel, tincidunt quis quam";
		 catelist.add( new ProductoCategoria(1,"cav","Giftcard cav",descripTest,"/resource/images/lg-cav.png",1) );
		 catelist.add( new ProductoCategoria(2,"tanta","Giftcard tanta",descripTest,"/resource/images/lg-tanta.png",1) );
		 catelist.add( new ProductoCategoria(3,"emporiolarosa","Giftcard emporio la rosa",descripTest,"/resource/images/emporiolarosa.png",1) );

		 return catelist;
	}
	
	public static List<ProductoCategoria> getCategoriasProductosTestTipo6() {
		 List<ProductoCategoria> catelist =  new ArrayList<>();
		 String descripTest = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras feugiat urna elit. Mauris vel feugiat urna. Suspendisse finibus varius tortor in fermentum. Pellentesque laoreet commodo eleifend. Quisque blandit ex nec lobortis iaculis. Sed aliquet tempus augue, nec egestas urna semper quis. Aliquam lacus ante, rhoncus nec dictum vel, tincidunt quis quam";
		 catelist.add( new ProductoCategoria(1,"cav","Giftcard cav",descripTest,"/resource/images/lg-cav.png",1,"soy-orange") );
		 catelist.add( new ProductoCategoria(2,"tanta","Giftcard tanta",descripTest,"/resource/images/lg-tanta.png",1,"soy-red") );
		 catelist.add( new ProductoCategoria(3,"emporiolarosa","Giftcard emporio la rosa",descripTest,"/resource/images/emporiolarosa.png",1,"soy-green") );

		 return catelist;
	}
	
	public static List<ProductoCategoria> getCateProductosFromCategoria(String strIndexCategoria) {
		 List<ProductoCategoria> catelist =  new ArrayList<>();
		 catelist = Emudata.getCategoriasProductosTest();
		 
		 List<ProductoCategoria> catelistResult =  new ArrayList<>();
		 
		 for (ProductoCategoria catesel : catelist) //buscamos el menu que seleccionó
			{ 
				if(catesel.getStrIndex().equalsIgnoreCase(strIndexCategoria) ) {			
					
					catesel.productosList = Emudata.getProductoseEcomerceTest();
					
					catelistResult.add(catesel); // se encuentra la categoria y se inserta					
					
					break;
				}
			}
		 
		 return catelistResult;
	} 
	
	public static List<TarjetaCliente> getTarjetasCliente() {
		 List<TarjetaCliente> catelist =  new ArrayList<>();
		 catelist.add( new TarjetaCliente(1, "Master-Card", "Tarjeta xxxx 1234") );
		 catelist.add( new TarjetaCliente(2, "Master-Card", "Tarjeta xxxx 5678") );
		 return catelist;
	}
	
	public static Information getInformationHtml() {
		Information information = new Information(1, 1, "Ayuda", 1, "&lt;div class=&quot;container pt-5 pb-5&quot;&gt;\r\n" + 
				"			&lt;div class=&quot;row&quot;&gt;\r\n" + 
				"        &lt;div class=&quot;col-12 mb-4&quot;&gt;\r\n" + 
				"          &lt;h5 class=&quot;font-weight-bold mb-1&quot;&gt;Bienvenido a ScotiaClub&lt;/h5&gt;\r\n" + 
				"          &lt;p&gt;Si eres parte de Scotiabank, &iexcl;ya eres parte de Scotiaclub!&lt;/p&gt;\r\n" + 
				"        &lt;/div&gt;\r\n" + 
				"      &lt;/div&gt;\r\n" + 
				"      &lt;div class=&quot;row&quot;&gt;\r\n" + 
				"        &lt;div class=&quot;col-2 col-md-5&quot;&gt;&lt;/div&gt;\r\n" + 
				"        &lt;div class=&quot;col-8 col-md-3 mb-4&quot;&gt;&lt;img class=&quot;fit-img&quot; src=&quot;/resource/images/scotiaclub-logo.png&quot;&gt;&lt;/div&gt;\r\n" + 
				"        &lt;div class=&quot;col-2 col-md-5&quot;&gt;&lt;/div&gt;\r\n" + 
				"      &lt;/div&gt;\r\n" + 
				"      &lt;div class=&quot;row&quot;&gt;\r\n" + 
				"        &lt;div class=&quot;col-md-2&quot;&gt;&lt;/div&gt;\r\n" + 
				"        &lt;div class=&quot;col-12 col-md-8&quot;&gt;\r\n" + 
				"          &lt;h6 class=&quot;font-weight-bold&quot;&gt;Ya puedes acceder a un mundo de descuentos y productos exclusivos para ti.&lt;/h6&gt; \r\n" + 
				"          &lt;h6&gt;Acumula ScotiaPesos y canjealos por lo que m&aacute;s te guste, s&iacute; &iexcl;t&uacute; eres qui&eacute;n decide en que gastar&aacute;s tus ScotiaPesos!. S&oacute;lo debes utilizar tus Tarjetas de Cr&eacute;dito y D&eacute;bito y ya estar&aacute;s acumulando.&lt;/h6&gt;\r\n" + 
				"        &lt;/div&gt;\r\n" + 
				"        &lt;div class=&quot;col-md-2&quot;&gt;&lt;/div&gt;\r\n" + 
				"      &lt;/div&gt;\r\n" + 
				"      &lt;div class=&quot;row&quot;&gt;\r\n" + 
				"        &lt;div class=&quot;col-md-2&quot;&gt;&lt;/div&gt;\r\n" + 
				"        &lt;div class=&quot;col-12 col-md-8 cr-grey-bg border rounded p-5 mt-4&quot;&gt;\r\n" + 
				"          &lt;div class=&quot;row&quot;&gt;\r\n" + 
				"            &lt;div class=&quot;col-md-2&quot;&gt;&lt;/div&gt;\r\n" + 
				"            &lt;div class=&quot;col-md-2 text-center&quot;&gt;&lt;img src=&quot;/resource/images/money.png&quot;&gt;&lt;/div&gt;\r\n" + 
				"            &lt;div class=&quot;col-md-6&quot;&gt;\r\n" + 
				"              &lt;h4 class=&quot;font-weight-bold&quot;&gt;1 Scotiapeso = 1 peso&lt;/h4&gt;\r\n" + 
				"            &lt;/div&gt;\r\n" + 
				"            &lt;div class=&quot;col-md-2&quot;&gt;&lt;/div&gt;\r\n" + 
				"          &lt;/div&gt;\r\n" + 
				"          &lt;div class=&quot;row mt-3&quot;&gt;\r\n" + 
				"            &lt;div class=&quot;col-md-2&quot;&gt;&lt;/div&gt;\r\n" + 
				"            &lt;div class=&quot;col-md-8 text-center&quot;&gt;\r\n" + 
				"              &lt;h6&gt;Acumula &lt;span class=&quot;font-weight-bold&quot;&gt;1 ScotiaPeso&lt;/span&gt; por cada &lt;span class=&quot;font-weight-bold&quot;&gt;$100 en compras&lt;/span&gt; con tu Tarjeta de Cr&eacute;dito&lt;/h6&gt;\r\n" + 
				"            &lt;/div&gt;\r\n" + 
				"            &lt;div class=&quot;col-md-2&quot;&gt;&lt;/div&gt;\r\n" + 
				"          &lt;/div&gt;\r\n" + 
				"          &lt;div class=&quot;row mt-4&quot;&gt;\r\n" + 
				"            &lt;div class=&quot;col-12 col-md-6&quot;&gt;\r\n" + 
				"              &lt;p&gt;Acumula 1 ScotiaPeso por cada $500 en compras con tu Tarjeta de D&eacute;bito&lt;/p&gt;\r\n" + 
				"            &lt;/div&gt;\r\n" + 
				"            &lt;div class=&quot;col-12 col-md-6&quot;&gt;\r\n" + 
				"              &lt;p&gt;Acumula 5 ScotiaPeso por cada USD1 en compras con tu Tarjeta de Cr&eacute;dito&lt;/p&gt;\r\n" + 
				"            &lt;/div&gt;\r\n" + 
				"          &lt;/div&gt;\r\n" + 
				"        &lt;/div&gt;\r\n" + 
				"        &lt;div class=&quot;col-md-2&quot;&gt;&lt;/div&gt;\r\n" + 
				"      &lt;/div&gt;\r\n" + 
				"		&lt;/div&gt;");
		information.setHtml(HtmlUtils.htmlUnescape(information.getHtml()));
		

		return information;
	}
	
	public static UserCartola getUserCartola() {
		
		List<UserCartolaMovimiento> movimientos = new ArrayList<>();
		movimientos.add(new UserCartolaMovimiento("13 - 06 - 2018", "REDCOMPRA", "Abono", "+ $1.158", "$40.158"));
		movimientos.add(new UserCartolaMovimiento("13 - 06 - 2018", "MASTERCARD NACIONAL PLATINIUM	", "Abono", "+ $3.189", "$40.158"));
		movimientos.add(new UserCartolaMovimiento("13 - 06 - 2018", "SCOTIACLUB GRANDES TIENDAS Y ZAPATERIAS	", "Cargo", "- $11.330", "$40.158"));
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CredencialesEntity credencialesEntity = (CredencialesEntity) auth.getPrincipal();
		Scotiauser scotiauser = credencialesEntity.getScotiauser();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("AuthorizationCustomer", credencialesEntity.getTOKENTWO());
		try {
			ResponseEntity<Points> pointsResponseEntity = restTemplate.exchange(apiUrl + "/v1/customer/points", HttpMethod.GET, new HttpEntity<Object>(httpHeaders), Points.class);
			Points points = pointsResponseEntity.getBody();
			UserCartola miCartola = new UserCartola(scotiauser.getFirstname(), scotiauser.getLastname(), "al 20 de diciembre 2018", points.getAvailablePoints(), points.getExpiringPoints().getPoints(), points.getExpiringPoints().getExpirationDate(), movimientos);				 
			return miCartola;
		} catch(Exception e) {
			Points points = new Points();
			points.setAvailablePoints(-1);
			ExpiringPoints expiringPoints = new ExpiringPoints();
			expiringPoints.setPoints(-1);
			expiringPoints.setExpirationDate("N/A");
			UserCartola miCartola = new UserCartola(scotiauser.getFirstname(), scotiauser.getLastname(), "al 20 de diciembre 2018", points.getAvailablePoints(), points.getExpiringPoints().getPoints(), points.getExpiringPoints().getExpirationDate(), movimientos);				 
			return miCartola;
		}
		
		
				
	}
	
	public static List<UserInscripcion> getInscripciones() {
		 List<UserInscripcion> catelist =  new ArrayList<>();
		 catelist.add( new UserInscripcion("Grandes Tiendas", 19000, "XXXX-XXXX-XXXX-1460", 123, "20/02/2018", "13/06/2018") );
		 catelist.add( new UserInscripcion("Samsung Galaxy S8+ Black.", 6000, "XXXX-XXXX-XXXX-1460", 123, "20/02/2018", "13/06/2018") );
		 catelist.add( new UserInscripcion("Grandes Tiendas", 19000, "XXXX-XXXX-XXXX-1460", 123, "20/02/2018", "13/06/2018") );
		 catelist.add( new UserInscripcion("Grandes Tiendas", 19000, "XXXX-XXXX-XXXX-1460", 123, "20/02/2018", "13/06/2018") );
//		 catelist.add( new UserInscripcion("Grandes Tiendas", 19000, "XXXX-XXXX-XXXX-1460", 123, "20/02/2018", "13/06/2018") );
//		 catelist.add( new UserInscripcion("Grandes Tiendas", 19000, "XXXX-XXXX-XXXX-1460", 123, "20/02/2018", "13/06/2018") );
//		 catelist.add( new UserInscripcion("Grandes Tiendas", 19000, "XXXX-XXXX-XXXX-1460", 123, "20/02/2018", "13/06/2018") );

		 return catelist;
	}
	
	public static List<UserGusto> getGustos() {
		 List<UserGusto> catelist =  new ArrayList<>();
		 catelist.add(new UserGusto(1,"Viajes","/resource/images/honeymoon.png") );
		 catelist.add(new UserGusto(2,"Fútbol","/resource/images/football2.png") );
		 catelist.add(new UserGusto(3,"Entretención","/resource/images/concert.png") );
		 catelist.add(new UserGusto(4,"Comida","/resource/images/dish2.png") );
		 catelist.add(new UserGusto(5,"Concursos","/resource/images/reward.png") );
//		 catelist.add(new UserGusto(6,"Descuentos","/resource/images/discount.png") );
		 return catelist;
	}
	
}
