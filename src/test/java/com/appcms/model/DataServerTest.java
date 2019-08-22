package com.appcms.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;

import com.appcms.entity.Banner;
import com.appcms.entity.CanjeProducto;
import com.appcms.entity.Categoria;
import com.appcms.entity.CredencialesEntity;
import com.appcms.entity.Information;
import com.appcms.entity.LoginUser;
import com.appcms.entity.ProductoCategoria;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.ResourceEntity;
import com.appcms.entity.Scinformacionsubmenu;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.Scsubmenu;
import com.appcms.entity.StockTicket;
import com.appcms.entity.TarjetaCliente;
import com.appcms.entity.Tarjetas;
import com.appcms.entity.UserGusto;
import com.appcms.entity.points.Points;
import com.appcms.front.FronendApplication;
import com.appcms.router.ResourceRoutes;
import com.appcms.router.Routes;
import com.appcms.services.GetRestService;
import com.cms.views.ViewApp;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FronendApplication.class)
public class DataServerTest {

	
	private MockMvc mvc;
	@Autowired
	private DataServer dataServer;
	@MockBean
	private RestTemplate restTemplate;
	private Routes routes;
	private ViewApp vi;
	private ModelAndView mav;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
    
	
	
	
	@Before
	public void setUp() throws Exception {
		  //mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
		  
		  request = new MockHttpServletRequest();
		  response = new MockHttpServletResponse();
		  dataServer = mock(DataServer.class);
		  restTemplate= mock(RestTemplate.class);
		  routes = new Routes(dataServer);
		  vi=mock(ViewApp.class);
		  vi.setrestTemplate(restTemplate);
		  dataServer.setRestemplate(restTemplate);
		  
		  routes.setViewApp(vi);
		  Mockito.when(vi.loadView("head")).thenReturn("TEST");
		  
	      mvc =MockMvcBuilders.standaloneSetup(routes).build();
		  
		  SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		  AuthenticationTrustResolver resolver=mock(AuthenticationTrustResolver.class);
		  Authentication authentication = Mockito.mock(Authentication.class);
		  SecurityContextHolder.setContext(securityContext);
		  CredencialesEntity credenciales =new CredencialesEntity();
			credenciales.setTOKENONE("AAAAAAAAAAAAAAAAAA");
			credenciales.setUserName("1-9");
			credenciales.setPassword("123");
			credenciales.setScotiauser(new Scotiauser(2, "177824577", "Fabian", "Gaete","fgaete@afiniti.cl","1"));
		
			
		  Mockito.when(authentication.getPrincipal()).thenReturn((CredencialesEntity)credenciales);
		  Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

		  ModelAndView httpResponsefalse = routes.notfound(request);
		Assert.assertNotNull(httpResponsefalse);
		
		
		
		
		//String Responsefalse = routes.logoutPage(request,response);
		//Assert.assertNotNull(Responsefalse);
		
	
		httpResponsefalse = routes.errorprint(400,request);
		Assert.assertNotNull(httpResponsefalse);
		
		httpResponsefalse = routes.errorprint(401,request);
		Assert.assertNotNull(httpResponsefalse);
		
		httpResponsefalse = routes.errorprint(403,request);
		Assert.assertNotNull(httpResponsefalse);
		
		httpResponsefalse = routes.errorprint(404,request);
		Assert.assertNotNull(httpResponsefalse);
		
		httpResponsefalse = routes.errorprint(000,request);
		Assert.assertNotNull(httpResponsefalse);
		
		httpResponsefalse = routes.errorprint(500,request);
		Assert.assertNotNull(httpResponsefalse);
		
		mvc = MockMvcBuilders.standaloneSetup(routes).build();
		  
	}


	@Test
	public final void testGetApiUrl() throws Exception {
		String apiURL="http://testapi";
		///Mockito.when(dataServer.getApiUrl()).thenReturn(apiURL);
    	///assertNotNull(dataServer.getApiUrl());
    	
    	//ModelAndView httpResponsefalse = routes.home(request);;
    	//assertNotNull((ModelAndView) httpResponsefalse);
		
		
		
    	
	}

	@Test
	public final void testLoadScmenuByName() throws Exception {
		
		
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
		
		Scmenu scmenu=new Scmenu();
		scmenu.setColor("#ffffff");
		scmenu.setEstado(1);
		scmenu.setFecha_creacion("01-01-2019");
		scmenu.setFecha_modificacion("01-02-2019");
		scmenu.setId(100);
		scmenu.setLink("http://google.cl");
		scmenu.setNombre("mundos");
		scmenu.setStrIndex("mundos");

		
		 Scinformacionsubmenu scinformacionsubmenu=new Scinformacionsubmenu();
			scinformacionsubmenu.setImagen_logo("/img/imagen.jpg");
			scinformacionsubmenu.setId(1);
			scinformacionsubmenu.setId_submenu(500);
			scinformacionsubmenu.setSubmenuStrindex("restorando");
			scinformacionsubmenu.setNombre("restorando");
			scinformacionsubmenu.setTipo(20);
			scinformacionsubmenu.setImagen("/img/imagen2.jpg");
			scinformacionsubmenu.setTitulo("TITULO");
			scinformacionsubmenu.setSubtitulo("SUBTITULO");
			scinformacionsubmenu.setDescripcion("DESCRIPCION");
			scinformacionsubmenu.setLink("http://google.cl");
			scinformacionsubmenu.setTexto_link("TEXTO_LINK");;
			scinformacionsubmenu.setJson_condiciones("[\"Paga todos los jueves con tus Tarjetas de Débito y Crédito Scotiabank en cualquiera de los restaurantes de la Ruta Gourmet Scotiabank\",\"Descuento aplica al momento de pagar la cuenta\r\n" + 
					"No es necesario reservar para hacer efectivo el descuento\"]");
			scinformacionsubmenu.setFecha_creacion("01-01-2019");
			scinformacionsubmenu.setFecha_modificacion("01-02-2019");
			scinformacionsubmenu.setEstado(1);
	        
			
		
		List<Scsubmenu> msubmenu1 = new ArrayList<>();
		msubmenu1.add(new Scsubmenu("restorando",1,"Restorando","/restorando","#008080","hover-green-bg","#008080",1,"/resource/images/dish.png","/resource/sections/gourmet.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("rutapub",2,"Ruta Gourmet","/rutapub","#d33195","hover-pink-bg","#d33195",2,"/resource/images/chef.png","/resource/sections/gourmet.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("ecomerce",3,"Ruta Pub","/ecomerce","#039fd3","hover-blue-bg","#039fd3",3,"/resource/images/beer.png","/resource/sections/pub.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("productos",4,"E-commerce Gastronomía","/productos","#533dc1","hover-purple-bg","#533dc1",4,"/resource/images/shopping-bag.png","/resource/sections/coffee.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("platos",5,"Productos","/platos","#ec121f","hover-red-bg","#ec121f",5,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("test6",6,"Productos","/platos","#ec121f","hover-red-bg","#ec121f",6,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("test7",7,"Productos","/platos","#ec121f","hover-red-bg","#ec121f",7,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("test8",8,"Productos","/platos","#ec121f","hover-red-bg","#ec121f",8,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("test9",9,"Productos","/platos","#ec121f","hover-red-bg","#ec121f",9,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		scmenu.setSubmenues(msubmenu1);
		
		///Mockito.when(dataServer.loadScmenuByName("restorando").getSubmenues()).thenReturn(msubmenu1);
	
		//List<TarjetaCliente> tarjetasCliente = new ArrayList<>();
		Tarjetas tj=new Tarjetas();
		
		
		ArrayList<TarjetaCliente> tarjetasCliente = new ArrayList<>();
		TarjetaCliente tarjeta=new TarjetaCliente();
		tarjeta.setKey("121321321");
		tarjeta.setNumero("12312123131321321321");
		tarjetasCliente.add(tarjeta);
		tj.setTarjetasCliente(tarjetasCliente);
		Mockito.when(dataServer.loadUserTarjetas()).thenReturn(tj);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", dataServer.getToken());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		URI uri = new URI("http://142.93.62.102:9080/cmsrest/get/scmenuByName/mundos");
        Mockito.when(restTemplate.exchange(uri, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<Scmenu>() {
				})).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));

        
        ProductoTipoLike productos = new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3);
		
		
       // ResponseEntity<Points> pointsResponseEntity = restTemplate.exchange(apiUrl + "/v1/customer/points",
		//		HttpMethod.GET, new HttpEntity<Object>(httpHeaders), Points.class);
        
        
        Mockito.when(dataServer.loadScmenuByName("mundos")).thenReturn(new ResponseEntity<Scmenu>(scmenu,HttpStatus.OK));
        Mockito.when(dataServer.loadProductoById(1)).thenReturn(new ResponseEntity<ProductoTipoLike>(productos,HttpStatus.OK));
        
        Points points = new Points();
		points.setAvailablePoints(10000000);

		
        Mockito.when(dataServer.loadUserPoints()).thenReturn(points);

        
        StockTicket stockticket =new StockTicket();
        stockticket.setActivo(1);
        stockticket.setEmpresa("giftcard");
        
        Mockito.when(dataServer.loadStockTicket("Danieli v1")).thenReturn(stockticket);
        
        
        List<ProductoTipoLike> productosList =  new ArrayList<>();
        productosList.add( new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
        Mockito.when(dataServer.loadProductosDetalle(1)).thenReturn(productosList);
        
        Mockito.when(dataServer.loadInformatioSub(1)).thenReturn(new ResponseEntity<Scinformacionsubmenu>(scinformacionsubmenu,HttpStatus.OK));
        Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/cmsrest//get/productosSubmenu/1", HttpMethod.GET, httpEntity,new ParameterizedTypeReference<List<ProductoTipoLike>>(){})).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosList, HttpStatus.OK));
        Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/cmsrest/get/informationsubmenu/1", HttpMethod.GET, httpEntity,Scinformacionsubmenu.class)).thenReturn(new ResponseEntity<Scinformacionsubmenu>(scinformacionsubmenu, HttpStatus.OK));
        
        Mockito.when(dataServer.loadProductosLike(1)).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosList,HttpStatus.OK));
        Mockito.when(dataServer.loadProductosLike(2)).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosList,HttpStatus.OK));
        Mockito.when(dataServer.loadProductosLike(3)).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosList,HttpStatus.OK));
        Mockito.when(dataServer.loadProductosLike(4)).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosList,HttpStatus.OK));
        Mockito.when(dataServer.loadProductosLike(5)).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosList,HttpStatus.OK));
        Mockito.when(dataServer.loadProductosLike(6)).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosList,HttpStatus.OK));
        Mockito.when(dataServer.loadProductosLike(7)).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosList,HttpStatus.OK));
        Mockito.when(dataServer.loadProductosLike(8)).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosList,HttpStatus.OK));
        Mockito.when(dataServer.loadProductosLike(9)).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosList,HttpStatus.OK));
        

       // dtserver.loadScmenuByName( menu).getBody();
		ModelAndView httpResponsefalse = routes.menuSubmenu("mundos", "restorando");
    	Assert.assertNotNull(httpResponsefalse);
    	
        
        
    	httpResponsefalse = routes.menuSubmenu("mundos", "rutapub");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "ecomerce");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "productos");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/productoCategoria/100", HttpMethod.GET, httpEntity,Scmenu.class)).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));
		Mockito.when(dataServer.loadCateProductosFromCategoria(5)).thenReturn(new ResponseEntity<List<ProductoCategoria>>(HttpStatus.OK));
		
    	httpResponsefalse = routes.menuSubmenu("mundos", "platos");
    	Assert.assertNotNull(httpResponsefalse);


    	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/productoCategoria/100", HttpMethod.GET, httpEntity,Scmenu.class)).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));
		Mockito.when(dataServer.loadCateProductosFromCategoria(6)).thenReturn(new ResponseEntity<List<ProductoCategoria>>(HttpStatus.OK));
		
		
    	httpResponsefalse = routes.menuSubmenu("mundos", "test6");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "test7");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "test8");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	
    	
    	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/informationScsubmenu/9", HttpMethod.GET, httpEntity,Information.class)).thenReturn(new ResponseEntity<Information>(information, HttpStatus.OK));
		Mockito.when(dataServer.loadInformationScsubmenu(9)).thenReturn(new ResponseEntity<Information>(information,HttpStatus.OK));
    	httpResponsefalse = routes.menuSubmenu("mundos", "test9");
    	Assert.assertNotNull(httpResponsefalse);
    	
     	Mockito.when(dataServer.loadInformationByName("mundos")).thenReturn(new ResponseEntity<Information>(information,HttpStatus.OK));
    	httpResponsefalse = routes.getinformation("mundos");
    	
    	httpResponsefalse = routes.getinformation("mundos");
    	Assert.assertNotNull(httpResponsefalse);
    	
    
   
    	Assert.assertNotNull(httpResponsefalse);
    	
    	//loadScmenuByName
    	
    	
    	CanjeProducto cp=new CanjeProducto(1, "TEST", "19", 1);
    	cp.setActionx("finish");
    	httpResponsefalse = routes.menuCanje(cp, "mundos","mundos", null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuCanje(cp, "mundos","restorando", null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuCanje(cp, "mundos","ecomerce", null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuCanje(cp, "mundos","productos", null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuCanje(cp, "mundos","rutapub", null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	httpResponsefalse = routes.menuCanje(cp, "mundos","platos", null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	cp.setActionx("finishx");
    	httpResponsefalse = routes.menuCanje(cp, "mundos","test6", null);
    	Assert.assertNotNull(httpResponsefalse);
    	cp.setActionx("finish");
    	httpResponsefalse = routes.menuCanje(cp, "mundos","test7", null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuCanje(cp, "mundos","test8", null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuCanje(cp, "mundos","test9", null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	List<Banner> banners = new ArrayList<>();
		banners.add(new Banner(1, "/resource/home-slider/comida2.jpg", "#", false));
		banners.add(new Banner(2, "/resource/home-slider/nino.jpg", "#", false));
		banners.add(new Banner(3, "/resource/home-slider/landscape.jpg", "#", false));
		Mockito.when(dataServer.loadBannerAll(0)).thenReturn(new ResponseEntity<List<Banner>>(banners,HttpStatus.OK));
		Mockito.when(dataServer.loadBannerAll(1)).thenReturn(new ResponseEntity<List<Banner>>(banners,HttpStatus.OK));
    	httpResponsefalse = routes.home();
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	httpResponsefalse = routes.menuDetalleProducto("mundos","restorando",1);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	msubmenu1 = new ArrayList<>();
		msubmenu1.add(new Scsubmenu("rutapub",20,"Ruta Gourmet","/rutapub","#d33195","hover-pink-bg","#d33195",20,"/resource/images/chef.png","/resource/sections/gourmet.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("ecomerce",21,"Ruta Pub","/ecomerce","#039fd3","hover-blue-bg","#039fd3",21,"/resource/images/beer.png","/resource/sections/pub.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("productos",22,"E-commerce Gastronomía","/productos","#533dc1","hover-purple-bg","#533dc1",22,"/resource/images/shopping-bag.png","/resource/sections/coffee.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("platos",23,"Productos","/platos","#ec121f","hover-red-bg","#ec121f",23,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("test6",24,"Productos","/platos","#ec121f","hover-red-bg","#ec121f",24,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("test100",100,"Productos","/platos","#ec121f","hover-red-bg","#ec121f",100,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		scmenu.setSubmenues(msubmenu1);
		Mockito.when(dataServer.loadScmenuByName("mundos")).thenReturn(new ResponseEntity<Scmenu>(scmenu,HttpStatus.OK));
		
		List<UserGusto> catelist =  new ArrayList<>();
		 catelist.add(new UserGusto(1,"Viajes","/resource/images/honeymoon.png") );
		 catelist.add(new UserGusto(2,"Fútbol","/resource/images/football2.png") );
		 catelist.add(new UserGusto(3,"Entretención","/resource/images/concert.png") );
		 catelist.add(new UserGusto(4,"Comida","/resource/images/dish2.png") );
		 catelist.add(new UserGusto(5,"Concursos","/resource/images/reward.png") );
		 
			Mockito.when(dataServer.loadGustos()).thenReturn(catelist);
			Mockito.when(dataServer.loadCustomerGustos()).thenReturn(catelist);
			
		httpResponsefalse = routes.menuUser("mundos", "rutapub",null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuUser("mundos", "ecomerce",null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuUser("mundos", "productos",null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuUser("mundos", "platos",null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuUser("mundos", "test6",null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuUser("mundos", "test100",null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	LoginUser loginForm=new LoginUser();
    	loginForm.setPass("123");
    	loginForm.setRut("19");
    	loginForm.setToken("Bearer TOKENOK");
    	
    	
    	MultiValueMap<String, String> xparam = new LinkedMultiValueMap<String, String>();
		xparam.add("userCostumer", loginForm.getRut());
		xparam.add("userPassword", loginForm.getPass());

    	ResponseEntity<String> responseLogin= new ResponseEntity<String>("TOKENOK", HttpStatus.OK);
    	responseLogin.ok().header("Authorization", "Bearer TOKENOK");
    	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/v1/login_customer", HttpMethod.GET, httpEntity,String.class)).thenReturn(responseLogin);
     	Mockito.when(dataServer.testLogin("19","123")).thenReturn(responseLogin);
    	httpResponsefalse = routes.loginuser(loginForm);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	Mockito.when(dataServer.testLogin(loginForm.getRut(), loginForm.getPass())).thenReturn(new ResponseEntity<String>("Bearer TOKENOK",HttpStatus.OK));
    	httpResponsefalse = routes.loginuser(loginForm);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	byte[] pdffile= { (byte)0xe0, 0x4f, (byte)0xd0,0x20, (byte)0xea, 0x3a, 0x69, 0x10, (byte)0xa2, (byte)0xd8, 0x08, 0x00, 0x2b,0x30, 0x30, (byte)0x9d };;
    	Mockito.when(dataServer.loadCuponAsPdf(2, 1)).thenReturn(pdffile);
    	Object httpResponsefalseObj = routes.getFile(1, "string");
    	Assert.assertNotNull(httpResponsefalseObj);
    	
    	//Mockito.when(dataServer).thenReturn("TOKEN");
    	httpResponsefalse = routes.getCuponByRew(1,null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	String[] gustos = new String[20];
    	gustos[0] = "Cheese";
    	gustos[1] = "Pepperoni";
    	gustos[2] = "Black Olives";
    	boolean httpResponsetrue = routes.actualizarGustos( gustos);
    	Assert.assertTrue(httpResponsetrue);
    	
    	request.setAttribute("javax.servlet.error.status_code", 500);
    	String retorno = routes.error(request);
    	Assert.assertNotNull(retorno);
	}

	
	@Test
	public final void testLoadproductoCategoriaConProductos() throws Exception {
		
		
		Scmenu scmenu=new Scmenu();
		scmenu.setColor("#ffffff");
		scmenu.setEstado(1);
		scmenu.setFecha_creacion("01-01-2019");
		scmenu.setFecha_modificacion("01-02-2019");
		scmenu.setId(100);
		scmenu.setLink("http://google.cl");
		scmenu.setNombre("mundos");
		scmenu.setStrIndex("mundos");
		
		
		Tarjetas tj=new Tarjetas();
		
		ArrayList<TarjetaCliente> tarjetasCliente = new ArrayList<>();
		TarjetaCliente tarjeta=new TarjetaCliente();
		tarjeta.setKey("121321321");
		tarjeta.setNumero("12312123131321321321");
		tarjetasCliente.add(tarjeta);
		tj.setTarjetasCliente(tarjetasCliente);
		Mockito.when(dataServer.loadUserTarjetas()).thenReturn(tj);
		
		List<Scsubmenu> msubmenu1 = new ArrayList<>();
		msubmenu1.add(new Scsubmenu("mundos",5,"mundos","/platos","#ec121f","hover-red-bg","#ec121f",5,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("mundos6",6,"mundos6","/platos","#ec121f","hover-red-bg","#ec121f",6,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("mundos7",7,"mundos7","/platos","#ec121f","hover-red-bg","#ec121f",7,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("mundos8",8,"mundos8","/platos","#ec121f","hover-red-bg","#ec121f",8,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));

		scmenu.setSubmenues(msubmenu1);
		
		
		
		
		
		URI uri = new URI("http://142.93.62.102:9080/cmsrest/get/scmenuByName/mundos");
		ResponseEntity<Scmenu> xrespo=restTemplate.exchange(uri,HttpMethod.GET, null,Scmenu.class);

		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/cmsrest/get/scmenuByName/mundos", HttpMethod.GET, 
				null, new ParameterizedTypeReference<Scmenu>() {})).thenReturn(xrespo);
		
		
		
		
		
		
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/productosSubmenuCategoria/mundos/1", HttpMethod.GET, null, Scmenu.class)).thenReturn(xrespo);
		when(dataServer.loadProductosLikeSubmenuCategoria(100,"mundos")).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(HttpStatus.OK));
		
		
		when(dataServer.loadScmenuByName("mundos")).thenReturn(new ResponseEntity<Scmenu>(scmenu,HttpStatus.OK));
		assertEquals(new ResponseEntity<Scmenu>(scmenu,HttpStatus.OK), dataServer.loadScmenuByName("mundos"));
        
		
		
		
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/productoCategoriaConProductos/categoria/5", HttpMethod.GET, null,Scmenu.class)).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));
		Mockito.when(dataServer.loadproductoCategoriaConProductos(5, "mundos")).thenReturn(new ResponseEntity<List<ProductoCategoria>>(HttpStatus.OK));

        Mockito.when(dataServer.loadScmenuByName("mundos")).thenReturn(new ResponseEntity<Scmenu>(scmenu,HttpStatus.OK));
		ModelAndView httpResponsefalse = routes.menuProductoCategoria("mundos", "mundos","mundos");
		Assert.assertNotNull(httpResponsefalse);
		
		
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/productosSubmenuCategoria/mundos6/6", HttpMethod.GET, null, Scmenu.class)).thenReturn(xrespo);
		when(dataServer.loadProductosLikeSubmenuCategoria(6,"mundos6")).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(HttpStatus.OK));
		Mockito.when(dataServer.loadScmenuByName("mundos")).thenReturn(new ResponseEntity<Scmenu>(scmenu,HttpStatus.OK));
		httpResponsefalse = routes.menuProductoCategoria("mundos", "mundos6","mundos6");
		Assert.assertNotNull(httpResponsefalse);
		
		
		
		Mockito.when(dataServer.loadScmenuByName("mundos7")).thenReturn(new ResponseEntity<Scmenu>(scmenu,HttpStatus.OK));
		httpResponsefalse = routes.menuProductoCategoria("mundos", "mundos7","mundos7");
		Assert.assertNotNull(httpResponsefalse);
		
		
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/productoCategoriaConProductos/categoria/8", HttpMethod.GET, null,Scmenu.class)).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));
		Mockito.when(dataServer.loadproductoCategoriaConProductos(8, "mundos8")).thenReturn(new ResponseEntity<List<ProductoCategoria>>(HttpStatus.OK));
		Mockito.when(dataServer.loadScmenuByName("mundos8")).thenReturn(new ResponseEntity<Scmenu>(scmenu,HttpStatus.OK));
		httpResponsefalse = routes.menuProductoCategoria("mundos", "mundos8","mundos8");
		Assert.assertNotNull(httpResponsefalse);
		
		//when(dataServer.loadScmenuByName("mundos")).thenReturn(scmenu);

		//mvc.perform(get("/categoria/mundos/mundos")).andExpect(status().isOk());

       // verify(dataServer).loadScmenuByName("mundos");
		
		
	}
	
	
	@Test
	public final void testLoadAllScmenu() throws Exception {
		Scmenu scmenu=new Scmenu();		
		scmenu.setColor("#ffffff");
		scmenu.setEstado(1);
		scmenu.setFecha_creacion("01-01-2019");
		scmenu.setFecha_modificacion("01-02-2019");
		scmenu.setId(100);
		scmenu.setLink("http://google.cl");
		scmenu.setNombre("NOMBRE");
		scmenu.setStrIndex("1111");
		List<Scmenu> scmenulist= new ArrayList<>();
		scmenulist.add(scmenu);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", dataServer.getToken());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/cmsrest/get/scmenu", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Scmenu>>() {})).thenReturn(new ResponseEntity<List<Scmenu>>(scmenulist,HttpStatus.OK));
		when(dataServer.loadAllScmenu()).thenReturn(new ResponseEntity<List<Scmenu>>(scmenulist,HttpStatus.OK));
		assertEquals(new ResponseEntity<List<Scmenu>>(scmenulist,HttpStatus.OK), dataServer.loadAllScmenu());
	}

	@Test
	public final void testLoadInformatioSub() throws Exception {
		Scinformacionsubmenu scinformacionsubmenu=new Scinformacionsubmenu();
		scinformacionsubmenu.setImagen_logo("/img/imagen.jpg");
		scinformacionsubmenu.setId(1);
		scinformacionsubmenu.setId_submenu(500);
		scinformacionsubmenu.setSubmenuStrindex("restorando");
		scinformacionsubmenu.setNombre("restorando");
		scinformacionsubmenu.setTipo(20);
		scinformacionsubmenu.setImagen("/img/imagen2.jpg");
		scinformacionsubmenu.setTitulo("TITULO");
		scinformacionsubmenu.setSubtitulo("SUBTITULO");
		scinformacionsubmenu.setDescripcion("DESCRIPCION");
		scinformacionsubmenu.setLink("http://google.cl");
		scinformacionsubmenu.setTexto_link("TEXTO_LINK");;
		scinformacionsubmenu.setJson_condiciones("[\"Paga todos los jueves con tus Tarjetas de Débito y Crédito Scotiabank en cualquiera de los restaurantes de la Ruta Gourmet Scotiabank\",\"Descuento aplica al momento de pagar la cuenta\r\n" + 
				"No es necesario reservar para hacer efectivo el descuento\"]");
		scinformacionsubmenu.setFecha_creacion("01-01-2019");
		scinformacionsubmenu.setFecha_modificacion("01-02-2019");
		scinformacionsubmenu.setEstado(1);
		
		Scmenu scmenu=new Scmenu();
		scmenu.setColor("#ffffff");
		scmenu.setEstado(1);
		scmenu.setFecha_creacion("01-01-2019");
		scmenu.setFecha_modificacion("01-02-2019");
		scmenu.setId(1);
		scmenu.setLink("http://google.cl");
		scmenu.setNombre("restorando");
		scmenu.setStrIndex("restorando");
		
		
		List<Scsubmenu> msubmenu1 = new ArrayList<>();
		msubmenu1.add(new Scsubmenu("restorando",1,"Restorando","/restorando","#008080","hover-green-bg","#008080",1,"/resource/images/dish.png","/resource/sections/gourmet.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("rutapub",2,"Ruta Gourmet","/rutapub","#d33195","hover-pink-bg","#d33195",2,"/resource/images/chef.png","/resource/sections/gourmet.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("ecomerce",3,"Ruta Pub","/ecomerce","#039fd3","hover-blue-bg","#039fd3",2,"/resource/images/beer.png","/resource/sections/pub.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("productos",4,"E-commerce Gastronomía","/productos","#533dc1","hover-purple-bg","#533dc1",3,"/resource/images/shopping-bag.png","/resource/sections/coffee.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("platos",5,"Productos","/platos","#ec121f","hover-red-bg","#ec121f",4,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		scmenu.setSubmenues(msubmenu1);
		
		Mockito.when(dataServer.loadScmenuByName("restorando")).thenReturn(new ResponseEntity<Scmenu>(scmenu,HttpStatus.OK));
		Mockito.when(dataServer.loadInformatioSub(1)).thenReturn(new ResponseEntity<Scinformacionsubmenu>(scinformacionsubmenu,HttpStatus.OK));
		ModelAndView httpResponsefalse = routes.menuSubmenu("restorando", "restorando");
    	Assert.assertNotNull(httpResponsefalse);
    	
	}

	

	@Test
	public final void testLoadProductosLike() throws Exception {
		List<ProductoTipoLike> productos =  new ArrayList<>();
		productos.add( new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		productos.add( new ProductoTipoLike(2, "Danieli v1", "Danieli 2", "Coffee Bar", "/resource/images/ver_img.png", "25%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 10) );
		productos.add( new ProductoTipoLike(3, "Danieli v1", "Test 3", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 30) );
		productos.add( new ProductoTipoLike(4, "Danieli v1", "Prod xn", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 100) );
		
		Mockito.when(dataServer.loadProductosLike(11)).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productos,HttpStatus.OK));
		assertEquals(new ResponseEntity<List<ProductoTipoLike>>(productos,HttpStatus.OK), dataServer.loadProductosLike(11));
	}

	@Test
	public final void testLoadProductosLikeSubmenuCategoria() throws Exception {
		List<ProductoTipoLike> productos =  new ArrayList<>();
		productos.add( new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		productos.add( new ProductoTipoLike(2, "Danieli v1", "Danieli 2", "Coffee Bar", "/resource/images/ver_img.png", "25%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 10) );
		productos.add( new ProductoTipoLike(3, "Danieli v1", "Test 3", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 30) );
		productos.add( new ProductoTipoLike(4, "Danieli v1", "Prod xn", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 100) );
		///Mockito.when(dataServer.loadProductosLikeSubmenuCategoria(11,"test")).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productos,HttpStatus.OK));
		///assertEquals(new ResponseEntity<List<ProductoTipoLike>>(productos,HttpStatus.OK), dataServer.loadProductosLikeSubmenuCategoria(11,"test"));
	}


	  
	@Test
	public final void testLoadProductosDetalle() throws Exception {
		List<ProductoTipoLike> productos =  new ArrayList<>();
		productos.add( new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		productos.add( new ProductoTipoLike(2, "Danieli v1", "Danieli 2", "Coffee Bar", "/resource/images/ver_img.png", "25%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 10) );
		productos.add( new ProductoTipoLike(3, "Danieli v1", "Test 3", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 30) );
		productos.add( new ProductoTipoLike(4, "Danieli v1", "Prod xn", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 100) );
		Mockito.when(dataServer.loadProductosDetalle(11)).thenReturn(productos);
		assertEquals(productos, dataServer.loadProductosDetalle(11));
	}
	
	@Test
	public final void testLoadscmenuinformationFomScmenu() throws Exception {
		List<ProductoTipoLike> productos =  new ArrayList<>();
		productos.add( new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		productos.add( new ProductoTipoLike(2, "Danieli v1", "Danieli 2", "Coffee Bar", "/resource/images/ver_img.png", "25%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 10) );
		productos.add( new ProductoTipoLike(3, "Danieli v1", "Test 3", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 30) );
		productos.add( new ProductoTipoLike(4, "Danieli v1", "Prod xn", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 100) );
		Mockito.when(dataServer.loadProductosDetalle(11)).thenReturn(productos);
		dataServer.loadProductosDetalle(11);
		Mockito.verify(dataServer).loadProductosDetalle(11);
		
		assertEquals(productos, dataServer.loadProductosDetalle(11));
		
		
	}

	/*

	@Test
	public final void testLoadCateProductosFromCategoria() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}



	@Test
	public final void testLoadBannerAll() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadInformationScsubmenu() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadInformationByName() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testSetReward() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadProductoById() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testTestLogin() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadUserCartola() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadUserPoints() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadCupones() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadCuponAsPdf() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadStockTicket() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadUserTarjetas() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadGustos() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadCustomerGustos() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testSaveCustomerGustos() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadTagsProductos() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}*/

}
