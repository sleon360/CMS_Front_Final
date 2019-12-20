package com.appcms.model;


import static org.mockito.Mockito.mock;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.HtmlUtils;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import com.appcms.entity.Banner;
import com.appcms.entity.CredencialesEntity;
import com.appcms.entity.CustomerReward;
import com.appcms.entity.Information;
import com.appcms.entity.ProductoCategoria;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.Scinformacionsubmenu;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scotiauser;
import com.appcms.entity.Scsubmenu;
import com.appcms.entity.StockTicket;
import com.appcms.entity.TagProducto;
import com.appcms.entity.UserCartola;
import com.appcms.entity.UserCupon;
import com.appcms.entity.UserGusto;
import com.appcms.entity.user.points.Points;
import com.appcms.entity.user.transactions.Transaction;
import com.appcms.front.FronendApplication;
import com.appcms.router.Routes;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FronendApplication.class)
public class ServiceServerTest {
	
	/*
	
	@Autowired
	private DataServer dataServer;
	
	@MockBean
	private RestTemplate restTemplate;
	private Routes routes;

	
	@Autowired
    private WebApplicationContext webApplicationContext;
    
	
	
	
	@Before
	public void setUp() throws Exception {
		  //mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
		  
		
		 // dataServer = mock(DataServer.class);
		//  restTemplate= mock(RestTemplate.class);
		//  routes = new Routes(dataServer);

		  
		 // routes.setViewApp(vi);
		 // Mockito.when(vi.loadView("head")).thenReturn("TEST");

		 SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		  Authentication authentication = Mockito.mock(Authentication.class);
		  SecurityContextHolder.setContext(securityContext);
		  CredencialesEntity credenciales =new CredencialesEntity();
			credenciales.setTOKENONE("AAAAAAAAAAAAAAAAAA");
			credenciales.setUserName("19");
			credenciales.setPassword("123");
			credenciales.setScotiauser(new Scotiauser(2, "177824577", "Fabian", "Gaete","fgaete@afiniti.cl","1"));
		
			
		  Mockito.when(authentication.getPrincipal()).thenReturn((CredencialesEntity)credenciales);
		  Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		
		
		  
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
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", dataServer.getToken());
		HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
        Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/cmsrest/get/scmenuByName/mundos", HttpMethod.GET, httpEntity,Scmenu.class)).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));

        
        ProductoTipoLike productos = new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3);
        Mockito.when(dataServer.loadScmenuByName("mundos")).thenReturn(new ResponseEntity<Scmenu>(scmenu,HttpStatus.OK));
        Assert.assertNotNull(dataServer.loadScmenuByName("mundos"));
        
        
  
		List<Scmenu> scmenulist= new ArrayList<>();
		scmenulist.add(scmenu);
		
        Mockito.when(dataServer.loadAllScmenu()).thenReturn(new ResponseEntity<List<Scmenu>>(scmenulist,HttpStatus.OK));
        Assert.assertNotNull(dataServer.loadAllScmenu());
        
        
        
        Scinformacionsubmenu scinformacionsubmenu=mock(Scinformacionsubmenu.class);
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
		scinformacionsubmenu.setJson_condiciones("['Paga todos los jueves con tus Tarjetas de Débito y Crédito Scotiabank en cualquiera de los restaurantes de la Ruta Gourmet Scotiabank']");
		scinformacionsubmenu.setFecha_creacion("01-01-2019");
		scinformacionsubmenu.setFecha_modificacion("01-02-2019");
		scinformacionsubmenu.setEstado(1);
        
		//scinformacionsubmenu.get
		
		//Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/cmsrest/get/informationsubmenu/1", HttpMethod.GET, httpEntity,Scinformacionsubmenu.class)).thenReturn(new ResponseEntity<Scinformacionsubmenu>(scinformacionsubmenu, HttpStatus.OK));
		//Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/cmsrest/get/informationsubmenu/2", HttpMethod.GET, httpEntity,Scinformacionsubmenu.class)).thenReturn(new ResponseEntity<Scinformacionsubmenu>(scinformacionsubmenu, HttpStatus.OK));
    	//Mockito.when(dataServer.loadInformatioSub(1)).thenReturn(new ResponseEntity<Scinformacionsubmenu>(scinformacionsubmenu,HttpStatus.OK));
    	//Mockito.when(dataServer.loadAllScmenu()).thenReturn(new ResponseEntity<List<Scmenu>>(scmenulist,HttpStatus.OK));
        //Assert.assertNotNull(dataServer.loadInformatioSub(1).getBody());
		
		
		List<ProductoTipoLike> productosLike =  new ArrayList<>();
		productosLike.add( new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		productosLike.add( new ProductoTipoLike(2, "Danieli v1", "Danieli 2", "Coffee Bar", "/resource/images/ver_img.png", "25%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 10) );
		productosLike.add( new ProductoTipoLike(3, "Danieli v1", "Test 3", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 30) );
		productosLike.add( new ProductoTipoLike(4, "Danieli v1", "Prod xn", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 100) );
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/cmsrest/get/productosSubmenu/1", HttpMethod.GET, httpEntity,Scmenu.class)).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));
		Mockito.when(dataServer.loadProductosLike(1)).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosLike,HttpStatus.OK));
		
		
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/productosSubmenuCategoria/categoria/1", HttpMethod.GET, httpEntity,Scmenu.class)).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));
		Mockito.when(dataServer.loadProductosLikeSubmenuCategoria(1, "categoria")).thenReturn(new ResponseEntity<List<ProductoTipoLike>>(productosLike,HttpStatus.OK));
		
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/informationsubmenuList/1", HttpMethod.GET, httpEntity,Scmenu.class)).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));
		Mockito.when(dataServer.loadscmenuinformationFomScmenu(1)).thenReturn(new ResponseEntity<List<Scinformacionsubmenu>>(HttpStatus.OK));

		
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/productoCategoria/1", HttpMethod.GET, httpEntity,Scmenu.class)).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));
		Mockito.when(dataServer.loadCateProductosFromCategoria(1)).thenReturn(new ResponseEntity<List<ProductoCategoria>>(HttpStatus.OK));


		
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/productoCategoriaConProductos/categoria/1", HttpMethod.GET, httpEntity,Scmenu.class)).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));
		Mockito.when(dataServer.loadproductoCategoriaConProductos(1, "categoria")).thenReturn(new ResponseEntity<List<ProductoCategoria>>(HttpStatus.OK));

		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/bannerAll/1", HttpMethod.GET, httpEntity,Scmenu.class)).thenReturn(new ResponseEntity<Scmenu>(scmenu, HttpStatus.OK));
		Mockito.when(dataServer.loadBannerAll(1)).thenReturn(new ResponseEntity<List<Banner>>(HttpStatus.OK));

		
		
		
		
		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/informationScsubmenu/1", HttpMethod.GET, httpEntity,Information.class)).thenReturn(new ResponseEntity<Information>(information, HttpStatus.OK));
		Mockito.when(dataServer.loadInformationScsubmenu(1)).thenReturn(new ResponseEntity<Information>(information,HttpStatus.OK));

		Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/informationByName/Ayuda", HttpMethod.GET, httpEntity,Information.class)).thenReturn(new ResponseEntity<Information>(information, HttpStatus.OK));
     	Mockito.when(dataServer.loadInformationByName("Ayuda")).thenReturn(new ResponseEntity<Information>(information,HttpStatus.OK));
     	Assert.assertNotNull(dataServer.loadInformationByName("Ayuda"));
     	
     	
     	CustomerReward reward=new CustomerReward();
     	reward.setCustomer_id(123);
     	reward.setCustomer_reward_id(11);
     	reward.setDate_added("01-01-2019");
     	reward.setDate_vencimiento("01-01-2020");
     	reward.setDescription("description");
     	reward.setId_campana(1);
     	reward.setOrder_id(11);
     	
     	MultiValueMap<String, String> xparam = new LinkedMultiValueMap<String, String>();
		xparam.add("customer_id", Integer.toString(reward.getCustomer_id()));
		xparam.add("order_id", Integer.toString(reward.getOrder_id()));
		xparam.add("description", reward.getDescription());
		xparam.add("points", Integer.toString(reward.getPoints()));
		xparam.add("date_added", reward.getDate_added());
		xparam.add("date_vencimiento", reward.getDate_vencimiento());
		xparam.add("id_campana", Integer.toString(reward.getId_campana()));
		xparam.add("id_trx", Integer.toString(reward.getId_trx()));
		xparam.add("id_jos_ticket", Integer.toString(reward.getId_jos_ticket()));
		xparam.add("tipo_reward", Integer.toString(reward.getTipo_reward()));
		xparam.add("nombre_ticket", "555");
		xparam.add("rut_cliente", "19");
		
		HttpEntity<?> httpEntitycustomerreward = new HttpEntity<Object>(xparam, headers);
    	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/customerreward/set", HttpMethod.POST, httpEntitycustomerreward,Information.class)).thenReturn(new ResponseEntity<Information>(information, HttpStatus.OK));
     	Mockito.when(dataServer.setReward(reward, "555", "19")).thenReturn(new ResponseEntity<String>("STRING_REWARD",HttpStatus.OK));
     	Assert.assertNotNull(dataServer.setReward(reward, "555", "19"));
     	
     	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/detalleProducto/1", HttpMethod.GET, httpEntity,Information.class)).thenReturn(new ResponseEntity<Information>(information, HttpStatus.OK));
     	Mockito.when(dataServer.loadProductoById(1)).thenReturn(new ResponseEntity<ProductoTipoLike>(productos,HttpStatus.OK));
     	Assert.assertNotNull(dataServer.loadInformationByName("Ayuda"));
     	
     	
     	
     	ResponseEntity<String> responseLogin= new ResponseEntity<String>("TOKENOK", HttpStatus.OK);
     	HttpEntity<?> httpEntityLogin = new HttpEntity<Object>(xparam, headers);
     	
     	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/v1/login_customer", HttpMethod.POST, httpEntityLogin,String.class)).thenReturn(responseLogin);
     	Mockito.when(dataServer.testLogin("19","123")).thenReturn(new ResponseEntity<String>("Bearer TOKENOK",HttpStatus.OK));
     	Assert.assertNotNull(dataServer.testLogin("19","123"));
     	
     	List<UserCartolaMovimiento> movimientos = new ArrayList<>();
     	UserCartola miCartola = new UserCartola("Fabian", "Gaete", "al 20 de diciembre 2018", 100, 50, "01-01-2020", movimientos);
     	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/v1/customer/points", HttpMethod.GET, httpEntity,Information.class)).thenReturn(new ResponseEntity<Information>(information, HttpStatus.OK));
     	Mockito.when(dataServer.loadUserCartola()).thenReturn(new ResponseEntity<UserCartola>(miCartola,HttpStatus.OK));
     	Assert.assertNotNull(dataServer.loadUserCartola());
     	
     	
     	
     	Points points = new Points();
		points.setAvailablePoints(-1);
		
     	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/v1/customer/points", HttpMethod.POST, httpEntityLogin,Points.class)).thenReturn(new ResponseEntity<Points>(points,HttpStatus.OK));
     	Mockito.when(dataServer.loadUserPoints()).thenReturn(new ResponseEntity<Points>(points,HttpStatus.OK));
     	Assert.assertNotNull(dataServer.testLogin("19","123"));
     	
     	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080v1/customer/1/cupones", HttpMethod.POST, httpEntityLogin,new ParameterizedTypeReference<List<UserCupon>>() {})).thenReturn(new ResponseEntity<List<UserCupon>>(HttpStatus.OK));
     	Mockito.when(dataServer.loadCupones(1)).thenReturn(new ResponseEntity<List<UserCupon>>(HttpStatus.OK));
     	Assert.assertNotNull(dataServer.loadCupones(1));
     	
     	byte[] pdffile= { (byte)0xe0, 0x4f, (byte)0xd0,0x20, (byte)0xea, 0x3a, 0x69, 0x10, (byte)0xa2, (byte)0xd8, 0x08, 0x00, 0x2b,0x30, 0x30, (byte)0x9d };;
     	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/v1/customer/1/cupones/1/getAsPdf", HttpMethod.POST, httpEntityLogin,byte[].class)).thenReturn(new ResponseEntity<byte[]>(pdffile,HttpStatus.OK));
     	Mockito.when(dataServer.loadCuponAsPdf(1, 1)).thenReturn(new ResponseEntity<byte[]>(HttpStatus.OK));
     	Assert.assertNotNull(dataServer.loadCupones(1));
     	
     	
     	StockTicket stockticket =new StockTicket();
        stockticket.setActivo(1);
        stockticket.setEmpresa("CENCOSUD");
        
     	
        Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/get/stockticket/CENCOSUD", HttpMethod.POST, httpEntityLogin,Points.class)).thenReturn(new ResponseEntity<Points>(points,HttpStatus.OK));
     	Mockito.when(dataServer.loadStockTicket("CENCOSUD")).thenReturn(new ResponseEntity<StockTicket>(stockticket,HttpStatus.OK));
     	Assert.assertNotNull(dataServer.loadStockTicket("CENCOSUD"));
     	
     	
     	List<UserGusto> catelist =  new ArrayList<>();
		 catelist.add(new UserGusto(1,"Viajes","/resource/images/honeymoon.png") );
		 catelist.add(new UserGusto(2,"Fútbol","/resource/images/football2.png") );
		 catelist.add(new UserGusto(3,"Entretención","/resource/images/concert.png") );
     	
     	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/active_customer_gustos", HttpMethod.POST, httpEntityLogin,Points.class)).thenReturn(new ResponseEntity<Points>(points,HttpStatus.OK));
     	Mockito.when(dataServer.loadGustos()).thenReturn(new ResponseEntity<List<UserGusto>>(catelist,HttpStatus.OK));
     	Assert.assertNotNull(dataServer.loadStockTicket("CENCOSUD"));
     	
     	
     	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/v1/customer/1/customer_gustos", HttpMethod.POST, httpEntityLogin, new ParameterizedTypeReference<List<UserGusto>>() {})).thenReturn(new ResponseEntity<List<UserGusto>>(catelist,HttpStatus.OK));
     	Mockito.when(dataServer.loadCustomerGustos()).thenReturn(new ResponseEntity<List<UserGusto>>(catelist,HttpStatus.OK));
     	Assert.assertNotNull(dataServer.loadCustomerGustos());
     	
     	
     	String[] gustos = new String[20];
    	gustos[0] = "Cheese";
    	gustos[1] = "Pepperoni";
    	gustos[2] = "Black Olives";
    	
     	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/v1/customer/1/customer_gustos", HttpMethod.POST, httpEntityLogin, new ParameterizedTypeReference<List<UserGusto>>() {})).thenReturn(new ResponseEntity<List<UserGusto>>(catelist,HttpStatus.OK));
     	Mockito.when(dataServer.saveCustomerGustos(gustos)).thenReturn(new ResponseEntity<String>(HttpStatus.OK));
     	Assert.assertNotNull(dataServer.saveCustomerGustos(gustos));
     	
     	
     	Mockito.when(restTemplate.exchange("http://142.93.62.102:9080/tags_productos/getTop10", HttpMethod.POST, httpEntityLogin, new ParameterizedTypeReference<List<UserGusto>>() {})).thenReturn(new ResponseEntity<List<UserGusto>>(catelist,HttpStatus.OK));
     	Mockito.when(dataServer.loadTagsProductos()).thenReturn(new ResponseEntity<List<TagProducto>>(HttpStatus.OK));
     	Assert.assertNotNull(dataServer.loadTagsProductos());
     	
 
		//ResponseEntity<List<Scinformacionsubmenu>> loadscmenuinformationFomScmenu(int idscbmenu)
		//String url = apiUrl + "/get/informationsubmenuList/" + idscbmenu;
		
		//ResponseEntity<List<ProductoTipoLike>>
		//String url = apiUrl + "/get/productosSubmenuCategoria/" + categoria + "/" + idsubmenu;
		//return restTemplate.exchange(url, HttpMethod.GET, httpEntity,new ParameterizedTypeReference<List<ProductoTipoLike>>() {});
      
	}

	*/

}
