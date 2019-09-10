package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class UserInscripcionTest {

	private UserInscripcion userInscripcion;
	
	@Before
	public void setUp() throws Exception {
		userInscripcion=new UserInscripcion("Grandes Tiendas", 19000, "XXXX-XXXX-XXXX-1460", 123, "20/02/2018", "13/06/2018");
		userInscripcion=new UserInscripcion();
		userInscripcion.setFechaEmision( "20/02/2018");
		userInscripcion.setFechaVencimiento("20/02/2019");
		userInscripcion.setIdTarjeta(100000);
		userInscripcion.setMonto(100);
		userInscripcion.setNombre("TEST");
		userInscripcion.setStrTarjeta("123456789");
	}

	@Test
	public final void testSetNombre() throws Exception {
		UserInscripcion esperado = mock(UserInscripcion.class);
		UserInscripcion noesperado = mock(UserInscripcion.class);
		when(esperado.getNombre()).thenReturn("TEST");
		when(noesperado.getNombre()).thenReturn("NO_TEST");
		assertEquals(userInscripcion.getNombre(), esperado.getNombre());
		assertNotEquals(noesperado.getNombre(), userInscripcion.getNombre());
	}

	@Test
	public final void testSetMonto() throws Exception {
		UserInscripcion esperado = mock(UserInscripcion.class);
		UserInscripcion noesperado = mock(UserInscripcion.class);
		when(esperado.getMonto()).thenReturn(100);
		when(noesperado.getMonto()).thenReturn(500);
		assertEquals(userInscripcion.getMonto(), esperado.getMonto());
		assertNotEquals(noesperado.getMonto(), userInscripcion.getMonto());
	}

	@Test
	public final void testSetStrTarjeta() throws Exception {
		UserInscripcion esperado = mock(UserInscripcion.class);
		UserInscripcion noesperado = mock(UserInscripcion.class);
		when(esperado.getStrTarjeta()).thenReturn("123456789");
		when(noesperado.getStrTarjeta()).thenReturn("00123456789");
		assertEquals(userInscripcion.getStrTarjeta(), esperado.getStrTarjeta());
		assertNotEquals(noesperado.getStrTarjeta(), userInscripcion.getStrTarjeta());
	}

	@Test
	public final void testSetIdTarjeta() throws Exception {
		UserInscripcion esperado = mock(UserInscripcion.class);
		UserInscripcion noesperado = mock(UserInscripcion.class);
		when(esperado.getIdTarjeta()).thenReturn(100000);
		when(noesperado.getIdTarjeta()).thenReturn(220000);
		assertEquals(userInscripcion.getIdTarjeta(), esperado.getIdTarjeta());
		assertNotEquals(noesperado.getIdTarjeta(), userInscripcion.getIdTarjeta());
	}

	@Test
	public final void testSetFechaEmision() throws Exception {
		UserInscripcion esperado = mock(UserInscripcion.class);
		UserInscripcion noesperado = mock(UserInscripcion.class);
		when(esperado.getFechaEmision()).thenReturn("20/02/2018");
		when(noesperado.getFechaEmision()).thenReturn("20/02/2019");
		assertEquals(userInscripcion.getFechaEmision(), esperado.getFechaEmision());
		assertNotEquals(noesperado.getFechaEmision(), userInscripcion.getFechaEmision());
	}

	@Test
	public final void testSetFechaVencimiento() throws Exception {
		UserInscripcion esperado = mock(UserInscripcion.class);
		UserInscripcion noesperado = mock(UserInscripcion.class);
		when(esperado.getFechaVencimiento()).thenReturn("20/02/2019");
		when(noesperado.getFechaVencimiento()).thenReturn("20/02/2018");
		assertEquals(userInscripcion.getFechaVencimiento(), esperado.getFechaVencimiento());
		assertNotEquals(noesperado.getFechaVencimiento(), userInscripcion.getFechaVencimiento());
	}

}
