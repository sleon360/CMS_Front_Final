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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;

import com.appcms.entity.CanjeProducto;
import com.appcms.entity.Categoria;
import com.appcms.entity.CredencialesEntity;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.ResourceEntity;
import com.appcms.entity.Scinformacionsubmenu;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.Scsubmenu;
import com.appcms.entity.TarjetaCliente;
import com.appcms.entity.Tarjetas;
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
	
	private DataServer dataServer;
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
		Scmenu scmenu=new Scmenu();
		scmenu.setColor("#ffffff");
		scmenu.setEstado(1);
		scmenu.setFecha_creacion("01-01-2019");
		scmenu.setFecha_modificacion("01-02-2019");
		scmenu.setId(100);
		scmenu.setLink("http://google.cl");
		scmenu.setNombre("mundos");
		scmenu.setStrIndex("mundos");

		
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


        Mockito.when(dataServer.loadScmenuByName("mundos")).thenReturn(scmenu);
		
		ModelAndView httpResponsefalse = routes.menuSubmenu("mundos", "restorando");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "rutapub");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "ecomerce");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "productos");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "platos");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "test6");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "test7");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "test8");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	httpResponsefalse = routes.menuSubmenu("mundos", "test9");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	httpResponsefalse = routes.getinformation("mundos");
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	CanjeProducto cp=new CanjeProducto(1, "TEST", "19", 1);
    	httpResponsefalse = routes.menuCanje(cp, "mundo","mundo", null);
    	Assert.assertNotNull(httpResponsefalse);
    	
    	
    	


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
		
		when(dataServer.loadScmenuByName("mundos")).thenReturn(scmenu);
		assertEquals(scmenu, dataServer.loadScmenuByName("mundos"));
        
        Mockito.when(dataServer.loadScmenuByName("mundos")).thenReturn(scmenu);
		ModelAndView httpResponsefalse = routes.menuProductoCategoria("mundos", "mundos","mundos");
		Assert.assertNotNull(httpResponsefalse);
		Mockito.when(dataServer.loadScmenuByName("mundos6")).thenReturn(scmenu);
		httpResponsefalse = routes.menuProductoCategoria("mundos", "mundos6","mundos6");
		Assert.assertNotNull(httpResponsefalse);
		Mockito.when(dataServer.loadScmenuByName("mundos7")).thenReturn(scmenu);
		httpResponsefalse = routes.menuProductoCategoria("mundos", "mundos7","mundos7");
		Assert.assertNotNull(httpResponsefalse);
		Mockito.when(dataServer.loadScmenuByName("mundos8")).thenReturn(scmenu);
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
	
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/cmsrest/get/scmenu", HttpMethod.GET, null, new ParameterizedTypeReference<List<Scmenu>>() {})).thenReturn(new ResponseEntity<List<Scmenu>>(scmenulist,HttpStatus.OK));
		when(dataServer.loadAllScmenu()).thenReturn(scmenulist);
		assertEquals(scmenulist, dataServer.loadAllScmenu());
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
		
		Mockito.when(dataServer.loadScmenuByName("restorando")).thenReturn(scmenu);
		Mockito.when(dataServer.loadInformatioSub(1)).thenReturn(scinformacionsubmenu);
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
		
		Mockito.when(dataServer.loadProductosLike(11)).thenReturn(productos);
		assertEquals(productos, dataServer.loadProductosLike(11));
	}

	@Test
	public final void testLoadProductosLikeSubmenuCategoria() throws Exception {
		List<ProductoTipoLike> productos =  new ArrayList<>();
		productos.add( new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		productos.add( new ProductoTipoLike(2, "Danieli v1", "Danieli 2", "Coffee Bar", "/resource/images/ver_img.png", "25%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 10) );
		productos.add( new ProductoTipoLike(3, "Danieli v1", "Test 3", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 30) );
		productos.add( new ProductoTipoLike(4, "Danieli v1", "Prod xn", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 100) );
		Mockito.when(dataServer.loadProductosLikeSubmenuCategoria(11,"test")).thenReturn(productos);
		assertEquals(productos, dataServer.loadProductosLikeSubmenuCategoria(11,"test"));
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
	/*
	@Test
	public final void testLoadscmenuinformationFomScmenu() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	

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
