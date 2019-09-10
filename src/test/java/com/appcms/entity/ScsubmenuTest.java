package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class ScsubmenuTest {

	private Scsubmenu scsubmenu;
	
	@Before
	public void setUp() throws Exception {
		scsubmenu=new Scsubmenu();
		scsubmenu.setId(100);
		scsubmenu.setIcono("icono.ico");
		scsubmenu.setColor("#FFFFFF");
		scsubmenu.setColor_borde("#FFF000");
		scsubmenu.setColor_hover("#CCCCCC");
		scsubmenu.setDescripcion("descripcion");
		scsubmenu.setEstado(1);
		scsubmenu.setFecha_creacion("01-01-2019");
		scsubmenu.setFecha_modificacion("01-01-2020");
		scsubmenu.setImagen("/img/imagen.jpg");
		scsubmenu.setLink("https://www.google.cl");
		scsubmenu.setNombre("nombre");
		scsubmenu.setStrIndex("COD123");
		scsubmenu.setTipo(1);
		scsubmenu.setTitulo_descripcion("titulo_descripcion");
		scsubmenu.setVisibleOnlyLogin(true);
		scsubmenu.setVisibleTop(true);	
	}

	@Test
	public final void testScsubmenuStringIntStringStringStringStringStringIntStringStringStringStringStringStringInt()
			throws Exception {
		
	}

	@Test
	public final void testGetStrIndex() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getStrIndex()).thenReturn("COD123");
		when(noesperado.getStrIndex()).thenReturn("NO_COD123");
		assertEquals(scsubmenu.getStrIndex(), esperado.getStrIndex());
		assertNotEquals(noesperado.getStrIndex(), scsubmenu.getStrIndex());
	}

	@Test
	public final void testGetLink() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getLink()).thenReturn("https://www.google.cl");
		when(noesperado.getLink()).thenReturn("https://www.google.com");
		assertEquals(scsubmenu.getLink(), esperado.getLink());
		assertNotEquals(noesperado.getLink(), scsubmenu.getLink());
	}

	@Test
	public final void testGetId() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getId()).thenReturn(100);
		when(noesperado.getId()).thenReturn(55854);
		assertEquals(scsubmenu.getId(), esperado.getId());
		assertNotEquals(noesperado.getId(), scsubmenu.getId());
	}

	@Test
	public final void testGetNombre() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getNombre()).thenReturn("nombre");
		when(noesperado.getNombre()).thenReturn("no_nombre");
		assertEquals(scsubmenu.getNombre(), esperado.getNombre());
		assertNotEquals(noesperado.getNombre(), scsubmenu.getNombre());
	}

	@Test
	public final void testGetColor() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getColor()).thenReturn("#FFFFFF");
		when(noesperado.getColor()).thenReturn("#0FFFFF");
		assertEquals(scsubmenu.getColor(), esperado.getColor());
		assertNotEquals(noesperado.getColor(), scsubmenu.getColor());
	}

	@Test
	public final void testGetColor_hover() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getColor_hover()).thenReturn("#CCCCCC");
		when(noesperado.getColor_hover()).thenReturn("#0FFFFF");
		assertEquals(scsubmenu.getColor_hover(), esperado.getColor_hover());
		assertNotEquals(noesperado.getColor_hover(), scsubmenu.getColor_hover());
	}

	@Test
	public final void testGetColor_borde() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getColor_borde()).thenReturn("#FFF000");
		when(noesperado.getColor_borde()).thenReturn("#0FFFFF");
		assertEquals(scsubmenu.getColor_borde(), esperado.getColor_borde());
		assertNotEquals(noesperado.getColor_borde(), scsubmenu.getColor_borde());
	}

	@Test
	public final void testGetTipo() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getTipo()).thenReturn(1);
		when(noesperado.getTipo()).thenReturn(5);
		assertEquals(scsubmenu.getTipo(), esperado.getTipo());
		assertNotEquals(noesperado.getTipo(), scsubmenu.getTipo());
	}

	@Test
	public final void testGetIcono() throws Exception {
		
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getIcono()).thenReturn("icono.ico");
		when(noesperado.getIcono()).thenReturn("noicono.ico");
		assertEquals(scsubmenu.getIcono(), esperado.getIcono());
		assertNotEquals(noesperado.getIcono(), scsubmenu.getIcono());
	}

	@Test
	public final void testGetImagen() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getImagen()).thenReturn("/img/imagen.jpg");
		when(noesperado.getImagen()).thenReturn("/img/noimagen.jpg");
		assertEquals(scsubmenu.getImagen(), esperado.getImagen());
		assertNotEquals(noesperado.getImagen(), scsubmenu.getImagen());
	}

	@Test
	public final void testGetTitulo_descripcion() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getTitulo_descripcion()).thenReturn("titulo_descripcion");
		when(noesperado.getTitulo_descripcion()).thenReturn("no_titulo_descripcion");
		assertEquals(scsubmenu.getTitulo_descripcion(), esperado.getTitulo_descripcion());
		assertNotEquals(noesperado.getTitulo_descripcion(), scsubmenu.getTitulo_descripcion());
	}

	@Test
	public final void testGetDescripcion() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getDescripcion()).thenReturn("descripcion");
		when(noesperado.getDescripcion()).thenReturn("no_descripcion");
		assertEquals(scsubmenu.getDescripcion(), esperado.getDescripcion());
		assertNotEquals(noesperado.getDescripcion(), scsubmenu.getDescripcion());
	}

	@Test
	public final void testGetFecha_creacion() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getFecha_creacion()).thenReturn("01-01-2019");
		when(noesperado.getFecha_creacion()).thenReturn("02-01-2019");
		assertEquals(scsubmenu.getFecha_creacion(), esperado.getFecha_creacion());
		assertNotEquals(noesperado.getFecha_creacion(), scsubmenu.getFecha_creacion());
	}

	@Test
	public final void testGetFecha_modificacion() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getFecha_modificacion()).thenReturn("01-01-2020");
		when(noesperado.getFecha_modificacion()).thenReturn("01-05-2020");
		assertEquals(scsubmenu.getFecha_modificacion(), esperado.getFecha_modificacion());
		assertNotEquals(noesperado.getFecha_modificacion(), scsubmenu.getFecha_modificacion());
	}

	@Test
	public final void testGetEstado() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.getEstado()).thenReturn(1);
		when(noesperado.getEstado()).thenReturn(2);
		assertEquals(scsubmenu.getEstado(), esperado.getEstado());
		assertNotEquals(noesperado.getEstado(), scsubmenu.getEstado());
	}

	@Test
	public final void testIsVisibleTop() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.isVisibleTop()).thenReturn(true);
		when(noesperado.isVisibleTop()).thenReturn(false);
		assertEquals(scsubmenu.isVisibleTop(), esperado.isVisibleTop());
		assertNotEquals(noesperado.isVisibleTop(), scsubmenu.isVisibleTop());
	}

	@Test
	public final void testIsVisibleOnlyLogin() throws Exception {
		Scsubmenu esperado = mock(Scsubmenu.class);
		Scsubmenu noesperado = mock(Scsubmenu.class);
		when(esperado.isVisibleOnlyLogin()).thenReturn(true);
		when(noesperado.isVisibleOnlyLogin()).thenReturn(false);
		assertEquals(scsubmenu.isVisibleOnlyLogin(), esperado.isVisibleOnlyLogin());
		assertNotEquals(noesperado.isVisibleOnlyLogin(), scsubmenu.isVisibleOnlyLogin());
	}

}
