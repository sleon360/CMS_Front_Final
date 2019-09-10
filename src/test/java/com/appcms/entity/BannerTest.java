package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class BannerTest {

	
	private Banner banner;
	
	
	@Before
	public void setUp() throws Exception {
		banner=new Banner(1, "/url/route/lista","https://localhost", true);
		banner=new Banner();
		banner.setBlank(true);
		banner.setOrden(1);
		banner.setResponsive(1);
		banner.setRuta("/url/route/lista");
		banner.setUrl("https://localhost");
	}


	@Test
	public final void testGetOrden() throws Exception {
		Banner esperado = mock(Banner.class);
		Banner noesperado = mock(Banner.class);
		when(esperado.getOrden()).thenReturn(1);
		when(noesperado.getOrden()).thenReturn(5);
		assertEquals(banner.getOrden(), esperado.getOrden());
		assertNotEquals(noesperado.getOrden(), banner.getOrden());
	}

	@Test
	public final void testGetRuta() throws Exception {
		Banner esperado = mock(Banner.class);
		Banner noesperado = mock(Banner.class);
		when(esperado.getRuta()).thenReturn("/url/route/lista");
		when(noesperado.getRuta()).thenReturn("/xurl/route/lista");
		assertEquals(banner.getRuta(), esperado.getRuta());
		assertNotEquals(noesperado.getRuta(), banner.getRuta());
	}

	@Test
	public final void testGetUrl() throws Exception {
		Banner esperado = mock(Banner.class);
		Banner noesperado = mock(Banner.class);
		when(esperado.getUrl()).thenReturn("https://localhost");
		when(noesperado.getUrl()).thenReturn("https://nolocalhost");
		assertEquals(banner.getUrl(), esperado.getUrl());
		assertNotEquals(noesperado.getUrl(), banner.getUrl());
	}

	@Test
	public final void testIsBlank() throws Exception {
		Banner esperado = mock(Banner.class);
		Banner noesperado = mock(Banner.class);
		when(esperado.isBlank()).thenReturn(true);
		when(noesperado.isBlank()).thenReturn(false);
		assertEquals(banner.isBlank(), esperado.isBlank());
		assertNotEquals(noesperado.isBlank(), banner.isBlank());
	}

	@Test
	public final void testGetResponsive() throws Exception {
		Banner esperado = mock(Banner.class);
		Banner noesperado = mock(Banner.class);
		when(esperado.getResponsive()).thenReturn(1);
		when(noesperado.getResponsive()).thenReturn(5);
		assertEquals(banner.getResponsive(), esperado.getResponsive());
		assertNotEquals(noesperado.getResponsive(), banner.getResponsive());
	}

}
