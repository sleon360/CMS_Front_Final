package com.appcms.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.appcms.entity.Categoria;
import com.appcms.entity.ProductoTipoLike;
import com.appcms.entity.ResourceEntity;
import com.appcms.entity.Scinformacionsubmenu;
import com.appcms.entity.Scmenu;
import com.appcms.entity.Scsubmenu;
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
	private WebApplicationContext webApplicationContext;
	
	//@Mock
	private DataServer dataServer;
	private Routes routes;
	private ViewApp vi;
	private ModelAndView mav;
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@Mock
    private RestTemplate restTemplate;
	
	
	@Before
	public void setUp() throws Exception {
		  mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
		  request = new MockHttpServletRequest();
		  response = new MockHttpServletResponse();
		  dataServer = mock(DataServer.class);
		  routes = new Routes(dataServer);
		  
	}


	@Test
	public final void testGetApiUrl() throws Exception {
		String apiURL="http://testapi";
		Mockito.when(dataServer.getApiUrl()).thenReturn(apiURL);
    	assertNotNull(dataServer.getApiUrl());
    	
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
		scmenu.setStrIndex("100");
		
		
		List<Scsubmenu> msubmenu1 = new ArrayList<>();
		msubmenu1.add(new Scsubmenu("restorando",1,"Restorando","/restorando","#008080","hover-green-bg","#008080",1,"/resource/images/dish.png","/resource/sections/gourmet.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("rutapub",2,"Ruta Gourmet","/rutapub","#d33195","hover-pink-bg","#d33195",2,"/resource/images/chef.png","/resource/sections/gourmet.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("ecomerce",3,"Ruta Pub","/ecomerce","#039fd3","hover-blue-bg","#039fd3",2,"/resource/images/beer.png","/resource/sections/pub.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("productos",4,"E-commerce Gastronomía","/productos","#533dc1","hover-purple-bg","#533dc1",3,"/resource/images/shopping-bag.png","/resource/sections/coffee.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		msubmenu1.add(new Scsubmenu("platos",5,"Productos","/platos","#ec121f","hover-red-bg","#ec121f",4,"/resource/images/market-place.png","/resource/sections/cheese.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		scmenu.setSubmenues(msubmenu1);
		
		//Mockito.when(dataServer.loadScmenuByName("restorando").getSubmenues()).thenReturn(msubmenu1);
		Mockito.when(dataServer.loadScmenuByName("restorando")).thenReturn(scmenu);
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
		
		//when(dataServer.loadAllScmenu()).thenReturn(scmenulist);
		when(dataServer.loadAllScmenu()).thenReturn(scmenulist);
		
		assertEquals(scmenulist, dataServer.loadAllScmenu());

		//Object httpResponsefalse = routes.home(request);
    	//assertNotNull((ModelAndView) httpResponsefalse);
    	//assertEquals(302, dataServer.loadAllScmenu());
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
		
		//Mockito.when(dataServer.loadScmenuByName("restorando").getSubmenues()).thenReturn(msubmenu1);
		Mockito.when(dataServer.loadScmenuByName("restorando")).thenReturn(scmenu);
		Mockito.when(dataServer.loadInformatioSub(1)).thenReturn(scinformacionsubmenu);
		
		
		
		assertEquals(scinformacionsubmenu, dataServer.loadInformatioSub(1));
		
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

	/*
	@Test
	public final void testLoadscmenuinformationFomScmenu() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadProductosDetalle() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadCateProductosFromCategoria() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testLoadproductoCategoriaConProductos() throws Exception {
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
