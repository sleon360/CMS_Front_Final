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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;

import com.appcms.entity.Banner;
import com.appcms.entity.CanjeProducto;
import com.appcms.entity.Categoria;
import com.appcms.entity.CredencialesEntity;
import com.appcms.entity.Information;
import com.appcms.entity.LoginUser;
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
public class DataServerTest2 {
	
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

		 dataServer.setRestemplate(restTemplate);
		  
		 // routes.setViewApp(vi);
		 // Mockito.when(vi.loadView("head")).thenReturn("TEST");

		
		
		
		  
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
		
		
      
	}

	

}
