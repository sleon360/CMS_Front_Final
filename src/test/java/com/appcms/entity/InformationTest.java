package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class InformationTest {

	private Information information;
	
	@Before
	public void setUp() throws Exception {
		information=new Information(6, 8,"TEST", 6, "");
		information=new Information();
		information.setId(66);
		information.setId_submenu(30);
		information.setNombre("NOMBRE");
		information.setTipo(54);
		information.setHtml("<HTML>");

	}



	@Test
	public final void testGetId() throws Exception {
		Information esperado = mock(Information.class);
		Information noesperado = mock(Information.class);
		when(esperado.getId()).thenReturn(66);
		when(noesperado.getId()).thenReturn(300);
		assertEquals(information.getId(), esperado.getId());
		assertNotEquals(noesperado.getId(), information.getId());
	}

	@Test
	public final void testGetId_submenu() throws Exception {
		Information esperado = mock(Information.class);
		Information noesperado = mock(Information.class);
		when(esperado.getId_submenu()).thenReturn(30);
		when(noesperado.getId_submenu()).thenReturn(300);
		assertEquals(information.getId_submenu(), esperado.getId_submenu());
		assertNotEquals(noesperado.getId_submenu(), information.getId_submenu());
	}

	@Test
	public final void testGetNombre() throws Exception {
		Information esperado = mock(Information.class);
		Information noesperado = mock(Information.class);
		when(esperado.getNombre()).thenReturn("NOMBRE");
		when(noesperado.getNombre()).thenReturn("NO_NOMBRE");
		assertEquals(information.getNombre(), esperado.getNombre());
		assertNotEquals(noesperado.getNombre(), information.getNombre());
	}

	@Test
	public final void testGetTipo() throws Exception {
		Information esperado = mock(Information.class);
		Information noesperado = mock(Information.class);
		when(esperado.getTipo()).thenReturn(54);
		when(noesperado.getTipo()).thenReturn(47);
		assertEquals(information.getTipo(), esperado.getTipo());
		assertNotEquals(noesperado.getTipo(), information.getTipo());
	}

	@Test
	public final void testGetHtml() throws Exception {
		Information esperado = mock(Information.class);
		Information noesperado = mock(Information.class);
		when(esperado.getHtml()).thenReturn("<HTML>");
		when(noesperado.getHtml()).thenReturn("<NOHTML>");
		assertEquals(information.getHtml(), esperado.getHtml());
		assertNotEquals(noesperado.getHtml(), information.getHtml());
	}

}
