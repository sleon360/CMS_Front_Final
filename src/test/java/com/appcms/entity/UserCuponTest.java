package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class UserCuponTest {

	
	private UserCupon userCupon;
	
	@Before
	public void setUp() throws Exception {
		userCupon=new UserCupon();
		userCupon.setCodigo("123456");
		userCupon.setId_cupon(66);
		userCupon.setImagen("/imagen.jpg");
		userCupon.setNombre("NOMBRE");
		userCupon.setValor(5000);
		userCupon.setFecha_emitido("01-01-2019");
		userCupon.setFecha_vencimiento("01-05-2019");
	}

	@Test
	public final void testGetId_cupon() throws Exception {
		UserCupon esperado = mock(UserCupon.class);
		UserCupon noesperado = mock(UserCupon.class);
		when(esperado.getId_cupon()).thenReturn(66);
		when(noesperado.getId_cupon()).thenReturn(45645);
		assertEquals(userCupon.getId_cupon(), esperado.getId_cupon());
		assertNotEquals(noesperado.getId_cupon(), userCupon.getId_cupon());
	}

	@Test
	public final void testGetNombre() throws Exception {
		UserCupon esperado = mock(UserCupon.class);
		UserCupon noesperado = mock(UserCupon.class);
		when(esperado.getNombre()).thenReturn("NOMBRE");
		when(noesperado.getNombre()).thenReturn("NO_NOMBRE");
		assertEquals(userCupon.getNombre(), esperado.getNombre());
		assertNotEquals(noesperado.getNombre(), userCupon.getNombre());
	}

	@Test
	public final void testGetValor() throws Exception {
		UserCupon esperado = mock(UserCupon.class);
		UserCupon noesperado = mock(UserCupon.class);
		when(esperado.getValor()).thenReturn(5000);
		when(noesperado.getValor()).thenReturn(3000);
		assertEquals(userCupon.getValor(), esperado.getValor());
		assertNotEquals(noesperado.getValor(), userCupon.getValor());
	}

	@Test
	public final void testGetImagen() throws Exception {
		UserCupon esperado = mock(UserCupon.class);
		UserCupon noesperado = mock(UserCupon.class);
		when(esperado.getImagen()).thenReturn("/imagen.jpg");
		when(noesperado.getImagen()).thenReturn("/imagen2.jpg");
		assertEquals(userCupon.getImagen(), esperado.getImagen());
		assertNotEquals(noesperado.getImagen(), userCupon.getImagen());
	}

	@Test
	public final void testGetFecha_emitido() throws Exception {
		UserCupon esperado = mock(UserCupon.class);
		UserCupon noesperado = mock(UserCupon.class);
		when(esperado.getFecha_emitido()).thenReturn("01-01-2019");
		when(noesperado.getFecha_emitido()).thenReturn("01-06-2019");
		assertEquals(userCupon.getFecha_emitido(), esperado.getFecha_emitido());
		assertNotEquals(noesperado.getFecha_emitido(), userCupon.getFecha_emitido());
	}

	@Test
	public final void testGetFecha_vencimiento() throws Exception {
		UserCupon esperado = mock(UserCupon.class);
		UserCupon noesperado = mock(UserCupon.class);
		when(esperado.getFecha_vencimiento()).thenReturn("01-05-2019");
		when(noesperado.getFecha_vencimiento()).thenReturn("01-08-2019");
		assertEquals(userCupon.getFecha_vencimiento(), esperado.getFecha_vencimiento());
		assertNotEquals(noesperado.getFecha_vencimiento(), userCupon.getFecha_vencimiento());
	}

	@Test
	public final void testGetCodigo() throws Exception {
		UserCupon esperado = mock(UserCupon.class);
		UserCupon noesperado = mock(UserCupon.class);
		when(esperado.getCodigo()).thenReturn("123456");
		when(noesperado.getCodigo()).thenReturn("100056");
		assertEquals(userCupon.getCodigo(), esperado.getCodigo());
		assertNotEquals(noesperado.getCodigo(), userCupon.getCodigo());
	}

}
