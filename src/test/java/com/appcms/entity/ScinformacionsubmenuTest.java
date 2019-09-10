package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ScinformacionsubmenuTest {

	private Scinformacionsubmenu scinformacionsubmenu;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		scinformacionsubmenu=new Scinformacionsubmenu();
		scinformacionsubmenu = new Scinformacionsubmenu(1,1,"Restorado8",1,"/resource/images/woman-computer.jpg","Obtén desde un","15% dcto. en restaurantes","Reserva ahora y obtén desde un 15% de dcto. en el total de tu cuenta","#","Reserva acá","[\"Restorando les ofrece a miles de comensales la posibilidad de descubrir miles de lugares para salir a comer, acceder a ofertas y beneficios en tiempo real y asegurar su mesa sin tener que esperar para sentarse.\",\"Restorando trabaja junto con los restaurantes para mejorar las experiencias gastron\\u00f3micas de los comensales en latinoam\\u00e9rica.\",\"XXC\",\"t2\"]","2018-12-11 18:15:04","2019-02-13 18:03:57",1);
		
		scinformacionsubmenu.setImagen_logo("/img/imagen.jpg");
		scinformacionsubmenu.setId(100);
		scinformacionsubmenu.setId_submenu(500);
		scinformacionsubmenu.setSubmenuStrindex("STR_INDEX");
		scinformacionsubmenu.setNombre("NOMBRE");
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
		
		List<String> condicioneslista = new ArrayList<>();
		condicioneslista.add("LISTA1");
		condicioneslista.add("LISTA2");
		condicioneslista.add("LISTA3");
		condicioneslista.add("LISTA4");
		scinformacionsubmenu.setCondicioneslista(condicioneslista);	
		
	}



	@Test
	public final void testGetImagen_logo() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getImagen_logo()).thenReturn("/img/imagen.jpg");
		when(noesperado.getImagen_logo()).thenReturn("/img/noesperado.jpg");
		assertEquals(scinformacionsubmenu.getImagen_logo(), esperado.getImagen_logo());
		assertNotEquals(noesperado.getImagen_logo(), scinformacionsubmenu.getImagen_logo());
	}

	@Test
	public final void testGetId() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getId()).thenReturn(100);
		when(noesperado.getId()).thenReturn(111);
		assertEquals(scinformacionsubmenu.getId(), esperado.getId());
		assertNotEquals(noesperado.getId(), scinformacionsubmenu.getId());
	}

	@Test
	public final void testGetId_submenu() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getId_submenu()).thenReturn(500);
		when(noesperado.getId_submenu()).thenReturn(111);
		assertEquals(scinformacionsubmenu.getId_submenu(), esperado.getId_submenu());
		assertNotEquals(noesperado.getId_submenu(), scinformacionsubmenu.getId_submenu());
	}

	@Test
	public final void testGetSubmenuStrindex() throws Exception {
		
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getSubmenuStrindex()).thenReturn("STR_INDEX");
		when(noesperado.getSubmenuStrindex()).thenReturn("NO_STR_INDEX");
		assertEquals(scinformacionsubmenu.getSubmenuStrindex(), esperado.getSubmenuStrindex());
		assertNotEquals(noesperado.getSubmenuStrindex(), scinformacionsubmenu.getSubmenuStrindex());
	}

	@Test
	public final void testGetNombre() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getNombre()).thenReturn("NOMBRE");
		when(noesperado.getNombre()).thenReturn("NO_NOMBRE");
		assertEquals(scinformacionsubmenu.getNombre(), esperado.getNombre());
		assertNotEquals(noesperado.getNombre(), scinformacionsubmenu.getNombre());
	}

	@Test
	public final void testGetTipo() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getTipo()).thenReturn(20);
		when(noesperado.getTipo()).thenReturn(10);
		assertEquals(scinformacionsubmenu.getTipo(), esperado.getTipo());
		assertNotEquals(noesperado.getTipo(), scinformacionsubmenu.getTipo());
	}

	@Test
	public final void testGetImagen() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getImagen()).thenReturn("/img/imagen2.jpg");
		when(noesperado.getImagen()).thenReturn("/img/no_imagen2.jpg");
		assertEquals(scinformacionsubmenu.getImagen(), esperado.getImagen());
		assertNotEquals(noesperado.getImagen(), scinformacionsubmenu.getImagen());
	}

	@Test
	public final void testGetTitulo() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getTitulo()).thenReturn("TITULO");
		when(noesperado.getTitulo()).thenReturn("NO_TITULO");
		assertEquals(scinformacionsubmenu.getTitulo(), esperado.getTitulo());
		assertNotEquals(noesperado.getTitulo(), scinformacionsubmenu.getTitulo());
	}

	@Test
	public final void testGetSubtitulo() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getSubtitulo()).thenReturn("SUBTITULO");
		when(noesperado.getSubtitulo()).thenReturn("NO_SUBTITULO");
		assertEquals(scinformacionsubmenu.getSubtitulo(), esperado.getSubtitulo());
		assertNotEquals(noesperado.getSubtitulo(), scinformacionsubmenu.getSubtitulo());
	}

	@Test
	public final void testGetDescripcion() throws Exception {
		
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getDescripcion()).thenReturn("DESCRIPCION");
		when(noesperado.getDescripcion()).thenReturn("NO_DESCRIPCION");
		assertEquals(scinformacionsubmenu.getDescripcion(), esperado.getDescripcion());
		assertNotEquals(noesperado.getDescripcion(), scinformacionsubmenu.getDescripcion());
	}

	@Test
	public final void testGetLink() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getLink()).thenReturn("http://google.cl");
		when(noesperado.getLink()).thenReturn("http://no-google.cl");
		assertEquals(scinformacionsubmenu.getLink(), esperado.getLink());
		assertNotEquals(noesperado.getLink(), scinformacionsubmenu.getLink());
	}

	@Test
	public final void testGetTexto_link() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getTexto_link()).thenReturn("TEXTO_LINK");
		when(noesperado.getTexto_link()).thenReturn("NO_TEXTO_LINK");
		assertEquals(scinformacionsubmenu.getTexto_link(), esperado.getTexto_link());
		assertNotEquals(noesperado.getTexto_link(), scinformacionsubmenu.getTexto_link());
	}

	@Test
	public final void testGetJson_condiciones() throws Exception {
		
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		
		when(esperado.getJson_condiciones()).thenReturn("[\"Paga todos los jueves con tus Tarjetas de Débito y Crédito Scotiabank en cualquiera de los restaurantes de la Ruta Gourmet Scotiabank\",\"Descuento aplica al momento de pagar la cuenta\r\n" + 
				"No es necesario reservar para hacer efectivo el descuento\"]");
		when(noesperado.getJson_condiciones()).thenReturn("NO_JSON");
		
		assertEquals(scinformacionsubmenu.getJson_condiciones(), esperado.getJson_condiciones());
		assertNotEquals(noesperado.getJson_condiciones(), scinformacionsubmenu.getJson_condiciones());
	}

	@Test
	public final void testGetFecha_creacion() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getFecha_creacion()).thenReturn("01-01-2019");
		when(noesperado.getFecha_creacion()).thenReturn("01-01-2018");
		assertEquals(scinformacionsubmenu.getFecha_creacion(), esperado.getFecha_creacion());
		assertNotEquals(noesperado.getFecha_creacion(), scinformacionsubmenu.getFecha_creacion());
	}

	@Test
	public final void testGetFecha_modificacion() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getFecha_modificacion()).thenReturn("01-02-2019");
		when(noesperado.getFecha_modificacion()).thenReturn("01-02-2018");
		assertEquals(scinformacionsubmenu.getFecha_modificacion(), esperado.getFecha_modificacion());
		assertNotEquals(noesperado.getFecha_modificacion(), scinformacionsubmenu.getFecha_modificacion());
	}

	@Test
	public final void testGetEstado() throws Exception {
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getEstado()).thenReturn(1);
		when(noesperado.getEstado()).thenReturn(0);
		assertEquals(scinformacionsubmenu.getEstado(), esperado.getEstado());
		assertNotEquals(noesperado.getEstado(), scinformacionsubmenu.getEstado());
	}

	@Test
	public final void testGetCondicioneslista() throws Exception {
		List<String> condicioneslista = new ArrayList<>();
		condicioneslista.add("LISTA1");
		condicioneslista.add("LISTA2");
		condicioneslista.add("LISTA3");
		condicioneslista.add("LISTA4");
		
		List<String> condicioneslista_noesperado = new ArrayList<>();
		condicioneslista.add("LISTA1");
		condicioneslista.add("LISTA2");
		
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getCondicioneslista()).thenReturn(condicioneslista);
		when(noesperado.getCondicioneslista()).thenReturn(condicioneslista_noesperado);
		assertTrue(scinformacionsubmenu.getCondicioneslista().containsAll(esperado.getCondicioneslista()));
		assertNotEquals(noesperado.getCondicioneslista(), scinformacionsubmenu.getCondicioneslista());
	}

	@Test
	public final void testAddCondicioneslista() throws Exception {
		List<String> condicioneslista = new ArrayList<>();
		condicioneslista.add("LISTA1");
		condicioneslista.add("LISTA2");
		condicioneslista.add("LISTA3");
		condicioneslista.add("LISTA4");
		condicioneslista.add("LISTA5");
		
		scinformacionsubmenu.addCondicioneslista("LISTA5");
		
		List<String> condicioneslista_noesperado = new ArrayList<>();
		condicioneslista.add("LISTA1");
		condicioneslista.add("LISTA2");
		
		Scinformacionsubmenu esperado = mock(Scinformacionsubmenu.class);
		Scinformacionsubmenu noesperado = mock(Scinformacionsubmenu.class);
		when(esperado.getCondicioneslista()).thenReturn(condicioneslista);
		when(noesperado.getCondicioneslista()).thenReturn(condicioneslista_noesperado);
		assertTrue(scinformacionsubmenu.getCondicioneslista().containsAll(esperado.getCondicioneslista()));
		assertNotEquals(noesperado.getCondicioneslista(), scinformacionsubmenu.getCondicioneslista());
	}

}
