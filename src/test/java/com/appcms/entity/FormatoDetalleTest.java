package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class FormatoDetalleTest {

	private FormatoDetalle formatoDetalle;

	@Before
	public void setUp() throws Exception {
		formatoDetalle=new FormatoDetalle();
		
		formatoDetalle.setTitulo("TITULO");
		formatoDetalle.setTipo(5);
		formatoDetalle.setDetalle("DETALLE");
	}

	@Test
	public final void testFormatoDetalleStringStringInt() throws Exception {
		formatoDetalle=new FormatoDetalle("TITULO", "DETALLE", 5);
		FormatoDetalle esperado = mock(FormatoDetalle.class);
		FormatoDetalle noesperado = mock(FormatoDetalle.class);
		when(esperado.getTitulo()).thenReturn("TITULO");
		when(noesperado.getTitulo()).thenReturn("NO_TITULO");
		
		assertEquals(formatoDetalle.getTitulo(), esperado.getTitulo());
		assertNotEquals(noesperado.getTitulo(), formatoDetalle.getTitulo());
	}

	@Test
	public final void testGetTitulo() throws Exception {
		FormatoDetalle esperado = mock(FormatoDetalle.class);
		FormatoDetalle noesperado = mock(FormatoDetalle.class);
		when(esperado.getTitulo()).thenReturn("TITULO");
		when(noesperado.getTitulo()).thenReturn("NO_TITULO");
		assertEquals(formatoDetalle.getTitulo(), esperado.getTitulo());
		assertNotEquals(noesperado.getTitulo(), formatoDetalle.getTitulo());
	}

	@Test
	public final void testGetDetalle() throws Exception {
		FormatoDetalle esperado = mock(FormatoDetalle.class);
		FormatoDetalle noesperado = mock(FormatoDetalle.class);
		when(esperado.getDetalle()).thenReturn("DETALLE");
		when(noesperado.getDetalle()).thenReturn("NO_DETALLE");
		assertEquals(formatoDetalle.getDetalle(), esperado.getDetalle());
		assertNotEquals(noesperado.getDetalle(), formatoDetalle.getDetalle());
	}

	@Test
	public final void testGetTipo() throws Exception {
		FormatoDetalle esperado = mock(FormatoDetalle.class);
		FormatoDetalle noesperado = mock(FormatoDetalle.class);
		when(esperado.getTipo()).thenReturn(5);
		when(noesperado.getTipo()).thenReturn(2);
		assertEquals(formatoDetalle.getTipo(), esperado.getTipo());
		assertNotEquals(noesperado.getTipo(), formatoDetalle.getTipo());
	}

}
