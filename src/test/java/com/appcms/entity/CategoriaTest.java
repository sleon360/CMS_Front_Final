package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class CategoriaTest {

	private Categoria categoria;
	
	@Before
	public void setUp() throws Exception {
		categoria=new Categoria();
		categoria.setColor("#CCFF00");
		categoria.setColor_hover("#0CFF00");
		categoria.setDescripcion("DESCRIPCION");
		categoria.setEstado(1);
		categoria.setFecha_creacion("10-10-2019");
		categoria.setFecha_modificacion("10-09-2020");
		categoria.setIcono("/icono.jpg");
		categoria.setId(45);
		categoria.setNombre("NOMBRE");
		categoria.setTipo(7);
		categoria.setTitulo_descipccion("TITULO_DESCRIPCION");
	}
	

	@Test
	public final void testGetNombre() throws Exception {
		Categoria esperado = mock(Categoria.class);
		Categoria noesperado = mock(Categoria.class);
		when(esperado.getNombre()).thenReturn("NOMBRE");
		when(noesperado.getNombre()).thenReturn("NO_NOMBRE");
		assertEquals(categoria.getNombre(), esperado.getNombre());
		assertNotEquals(noesperado.getNombre(), categoria.getNombre());
	}

	@Test
	public final void testGetDescripcion() throws Exception {
		Categoria esperado = mock(Categoria.class);
		Categoria noesperado = mock(Categoria.class);
		when(esperado.getDescripcion()).thenReturn("DESCRIPCION");
		when(noesperado.getDescripcion()).thenReturn("NO_DESCRIPCION");
		assertEquals(categoria.getDescripcion(), esperado.getDescripcion());
		assertNotEquals(noesperado.getDescripcion(), categoria.getDescripcion());
	}

	@Test
	public final void testGetId() throws Exception {
		Categoria esperado = mock(Categoria.class);
		Categoria noesperado = mock(Categoria.class);
		when(esperado.getId()).thenReturn(45);
		when(noesperado.getId()).thenReturn(87);
		assertEquals(categoria.getId(), esperado.getId());
		assertNotEquals(noesperado.getId(), categoria.getId());
	}

	@Test
	public final void testGetColor() throws Exception {
		Categoria esperado = mock(Categoria.class);
		Categoria noesperado = mock(Categoria.class);
		when(esperado.getColor()).thenReturn("#CCFF00");
		when(noesperado.getColor()).thenReturn("#00CFF00");
		assertEquals(categoria.getColor(), esperado.getColor());
		assertNotEquals(noesperado.getColor(), categoria.getColor());

	}

	@Test
	public final void testGetColor_hover() throws Exception {
		Categoria esperado = mock(Categoria.class);
		Categoria noesperado = mock(Categoria.class);
		when(esperado.getColor_hover()).thenReturn("#0CFF00");
		when(noesperado.getColor_hover()).thenReturn("#000FF00");
		assertEquals(categoria.getColor_hover(), esperado.getColor_hover());
		assertNotEquals(noesperado.getColor_hover(), categoria.getColor_hover());
	}

	@Test
	public final void testGetTipo() throws Exception {
		Categoria esperado = mock(Categoria.class);
		Categoria noesperado = mock(Categoria.class);
		when(esperado.getTipo()).thenReturn(7);
		when(noesperado.getTipo()).thenReturn(8);
		assertEquals(categoria.getTipo(), esperado.getTipo());
		assertNotEquals(noesperado.getTipo(), categoria.getTipo());
	}

	@Test
	public final void testGetIcono() throws Exception {
		Categoria esperado = mock(Categoria.class);
		Categoria noesperado = mock(Categoria.class);
		when(esperado.getIcono()).thenReturn("/icono.jpg");
		when(noesperado.getIcono()).thenReturn("/noicono.jpg");
		assertEquals(categoria.getIcono(), esperado.getIcono());
		assertNotEquals(noesperado.getIcono(), categoria.getIcono());
	}

	@Test
	public final void testGetTitulo_descipccion() throws Exception {
		Categoria esperado = mock(Categoria.class);
		Categoria noesperado = mock(Categoria.class);
		when(esperado.getTitulo_descipccion()).thenReturn("TITULO_DESCRIPCION");
		when(noesperado.getTitulo_descipccion()).thenReturn("NO_TITULO_DESCRIPCION");
		assertEquals(categoria.getTitulo_descipccion(), esperado.getTitulo_descipccion());
		assertNotEquals(noesperado.getTitulo_descipccion(), categoria.getTitulo_descipccion());
	}

	@Test
	public final void testGetFecha_creacion() throws Exception {
		Categoria esperado = mock(Categoria.class);
		Categoria noesperado = mock(Categoria.class);
		when(esperado.getFecha_creacion()).thenReturn("10-10-2019");
		when(noesperado.getFecha_creacion()).thenReturn("10-11-2019");
		assertEquals(categoria.getFecha_creacion(), esperado.getFecha_creacion());
		assertNotEquals(noesperado.getFecha_creacion(), categoria.getFecha_creacion());
	}

	@Test
	public final void testGetFecha_modificacion() throws Exception {
		Categoria esperado = mock(Categoria.class);
		Categoria noesperado = mock(Categoria.class);
		when(esperado.getFecha_modificacion()).thenReturn("10-09-2020");
		when(noesperado.getFecha_modificacion()).thenReturn("10-10-2020");
		assertEquals(categoria.getFecha_modificacion(), esperado.getFecha_modificacion());
		assertNotEquals(noesperado.getFecha_modificacion(), categoria.getFecha_modificacion());
	}

	@Test
	public final void testGetEstado() throws Exception {
		Categoria esperado = mock(Categoria.class);
		Categoria noesperado = mock(Categoria.class);
		when(esperado.getEstado()).thenReturn(1);
		when(noesperado.getEstado()).thenReturn(2);
		assertEquals(categoria.getEstado(), esperado.getEstado());
		assertNotEquals(noesperado.getEstado(), categoria.getEstado());
	}

}
