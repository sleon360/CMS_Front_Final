package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class LoginUserTest {

	private LoginUser loginUser;
	
	@Before
	public void setUp() throws Exception {
		loginUser=new LoginUser();
		loginUser.setRut("1-9");
		loginUser.setPass("123456789");
		loginUser.setToken("6846df54g65er46534t544654g6sdf4gh56dfgh56dfg");
	}

	@Test
	public final void testGetRut() throws Exception {
		LoginUser esperado = mock(LoginUser.class);
		LoginUser noesperado = mock(LoginUser.class);
		when(esperado.getRut()).thenReturn("1-9");
		when(noesperado.getRut()).thenReturn("1-1");
		assertEquals(loginUser.getRut(), esperado.getRut());
		assertNotEquals(noesperado.getRut(), loginUser.getRut());
	}

	@Test
	public final void testGetPass() throws Exception {
		LoginUser esperado = mock(LoginUser.class);
		LoginUser noesperado = mock(LoginUser.class);
		when(esperado.getPass()).thenReturn("123456789");
		when(noesperado.getPass()).thenReturn("1546523456789");
		assertEquals(loginUser.getPass(), esperado.getPass());
		assertNotEquals(noesperado.getPass(), loginUser.getPass());
	}

	@Test
	public final void testGetToken() throws Exception {
		LoginUser esperado = mock(LoginUser.class);
		LoginUser noesperado = mock(LoginUser.class);
		when(esperado.getToken()).thenReturn("6846df54g65er46534t544654g6sdf4gh56dfgh56dfg");
		when(noesperado.getToken()).thenReturn("y7867tyjfghj44654g6sdf4gh56dfgh56dfg");
		assertEquals(loginUser.getToken(), esperado.getToken());
		assertNotEquals(noesperado.getToken(), loginUser.getToken());
	}

}
