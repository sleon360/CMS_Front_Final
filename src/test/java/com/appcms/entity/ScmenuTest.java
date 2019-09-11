package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ScmenuTest {
/*
	
	private Scmenu scmenu;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		List<Scsubmenu> submenues= new ArrayList<Scsubmenu>();;
		submenues.add(new Scsubmenu("restorando",1,"Restorando","/restorando","#008080","hover-green-bg","#008080",1,"/resource/images/dish.png","/resource/sections/gourmet.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		submenues.add(new Scsubmenu("rutapub",2,"Ruta Gourmet","/rutapub","#d33195","hover-pink-bg","#d33195",2,"/resource/images/chef.png","/resource/sections/gourmet.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		submenues.add(new Scsubmenu("ecomerce",3,"Ruta Pub","/ecomerce","#039fd3","hover-blue-bg","#039fd3",2,"/resource/images/beer.png","/resource/sections/pub.jpg","","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","2018-12-11 18:15:04","2019-02-13 20:39:54",1));
		
		scmenu=new Scmenu("ayuda","Ayuda", "/categoria/ayuda", submenues,true);
		scmenu=new Scmenu("home",1, "Home", "#fff", 1, "/index", "2019-01-01", "2019-01-01", 1,null);
		
		scmenu=new Scmenu();
		
		scmenu.setColor("#ffffff");
		scmenu.setEstado(1);
		scmenu.setFecha_creacion("01-01-2019");
		scmenu.setFecha_modificacion("01-02-2019");
		scmenu.setId(100);
		scmenu.setLink("http://google.cl");
		scmenu.setNombre("NOMBRE");
		scmenu.setStrIndex("1111");
		
		
		scmenu.setSubmenues(submenues);
		scmenu.setTipo(1);
		scmenu.setUserElement(true);
		scmenu.setVisibleOnlyLogin(true);
		scmenu.setVisibleTop(true);
		//scmenu.set
		
	}

	@Test
	public final void testGetNombre() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.getNombre()).thenReturn("NOMBRE");
		when(noesperado.getNombre()).thenReturn("NO_NOMBRE");
		assertEquals(scmenu.getNombre(), esperado.getNombre());
		assertNotEquals(noesperado.getNombre(), scmenu.getNombre());
	}
	
	
	@Test
	public final void testGetColor() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.getColor()).thenReturn("#ffffff");
		when(noesperado.getColor()).thenReturn("#000000");
		assertEquals(scmenu.getColor(), esperado.getColor());
		assertNotEquals(noesperado.getColor(), scmenu.getColor());
	}
	
	
	@Test
	public final void testGetTipo() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.getTipo()).thenReturn(1);
		when(noesperado.getTipo()).thenReturn(0);
		assertEquals(scmenu.getTipo(), esperado.getTipo());
		assertNotEquals(noesperado.getTipo(), scmenu.getTipo());
	}
	
	@Test
	public final void testGetLink() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.getLink()).thenReturn("http://google.cl");
		when(noesperado.getLink()).thenReturn("http://123.cl");
		assertEquals(scmenu.getLink(), esperado.getLink());
		assertNotEquals(noesperado.getLink(), scmenu.getLink());
	}
	
	
	@Test
	public final void testGetisVisibleTop() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.isVisibleTop()).thenReturn(true);
		when(noesperado.isVisibleTop()).thenReturn(false);
		assertEquals(scmenu.isVisibleTop(), esperado.isVisibleTop());
		assertNotEquals(noesperado.isVisibleTop(), scmenu.isVisibleTop());
	}
	
	
	@Test
	public final void testGetisUserElement() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.isUserElement()).thenReturn(true);
		when(noesperado.isUserElement()).thenReturn(false);
		assertEquals(scmenu.isUserElement(), esperado.isUserElement());
		assertNotEquals(noesperado.isUserElement(), scmenu.isUserElement());
	}
	
	
	
	@Test
	public final void testGetStrIndex() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.getStrIndex()).thenReturn("1111");
		when(noesperado.getStrIndex()).thenReturn("0000");
		assertEquals(scmenu.getStrIndex(), esperado.getStrIndex());
		assertNotEquals(noesperado.getStrIndex(), scmenu.getStrIndex());
	}

	@Test
	public final void testGetId() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.getId()).thenReturn(100);
		when(noesperado.getId()).thenReturn(666);
		assertEquals(scmenu.getId(), esperado.getId());
		assertNotEquals(noesperado.getId(), scmenu.getId());
	}


	@Test
	public final void testGetFecha_creacion() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.getFecha_creacion()).thenReturn("01-01-2019");
		when(noesperado.getFecha_creacion()).thenReturn("01-01-2011");
		assertEquals(scmenu.getFecha_creacion(), esperado.getFecha_creacion());
		assertNotEquals(noesperado.getFecha_creacion(), scmenu.getFecha_creacion());
	}

	@Test
	public final void testGetFecha_modificacion() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.getFecha_modificacion()).thenReturn("01-02-2019");
		when(noesperado.getFecha_modificacion()).thenReturn("01-02-2015");
		assertEquals(scmenu.getFecha_modificacion(), esperado.getFecha_modificacion());
		assertNotEquals(noesperado.getFecha_modificacion(), scmenu.getFecha_modificacion());
	}

	@Test
	public final void testGetEstado() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.getEstado()).thenReturn(1);
		when(noesperado.getEstado()).thenReturn(0);
		assertEquals(scmenu.getEstado(), esperado.getEstado());
		assertNotEquals(noesperado.getEstado(), scmenu.getEstado());
	}

	@Test
	public final void testIsVisibleTop() throws Exception {
		Scmenu esperado = mock(Scmenu.class);
		Scmenu noesperado = mock(Scmenu.class);
		when(esperado.isVisibleOnlyLogin()).thenReturn(true);
		when(noesperado.isVisibleOnlyLogin()).thenReturn(false);
		assertEquals(scmenu.isVisibleOnlyLogin(), esperado.isVisibleOnlyLogin());
		assertNotEquals(noesperado.isVisibleOnlyLogin(), scmenu.isVisibleOnlyLogin());
	}
*/
}
