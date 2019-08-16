package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class CredencialesEntityTest {
	
	private CredencialesEntity credencialesEntity;
	
	
	@Before
	public void setUp() throws Exception {
		credencialesEntity=new CredencialesEntity();
		credencialesEntity.setUserName("TESTUSER");
		credencialesEntity.setPassword("%$/KJNKDKJ");
		//credencialesEntity.setScotiauser("TESTSCOTIAUSER");
		credencialesEntity.setTOKENONE("546546524165%$/KJNKDKJDNKJD");
		credencialesEntity.setTOKENTWO("HJJJNHKKJU0546546524165%$/KJNKDKJDNKJD");
	}

	@Test
	public final void testGetUserName() throws Exception {
		CredencialesEntity esperado = mock(CredencialesEntity.class);
		CredencialesEntity noesperado = mock(CredencialesEntity.class);
		when(esperado.getUserName()).thenReturn("TESTUSER");
		when(noesperado.getUserName()).thenReturn("NO_TESTUSER");
		assertEquals(credencialesEntity.getUserName(), esperado.getUserName());
		assertNotEquals(noesperado.getUserName(), credencialesEntity.getUserName());
	}

	@Test
	public final void testGetPassword() throws Exception {
		CredencialesEntity esperado = mock(CredencialesEntity.class);
		CredencialesEntity noesperado = mock(CredencialesEntity.class);
		when(esperado.getPassword()).thenReturn("%$/KJNKDKJ");
		when(noesperado.getPassword()).thenReturn("NO_%$/KJNKDKJ");
		assertEquals(credencialesEntity.getPassword(), esperado.getPassword());
		assertNotEquals(noesperado.getPassword(), credencialesEntity.getPassword());
	}

	@Test
	public final void testGetTOKENONE() throws Exception {
		CredencialesEntity esperado = mock(CredencialesEntity.class);
		CredencialesEntity noesperado = mock(CredencialesEntity.class);
		when(esperado.getTOKENONE()).thenReturn("546546524165%$/KJNKDKJDNKJD");
		when(noesperado.getTOKENONE()).thenReturn("NO_546546524165%$/KJNKDKJDNKJD");
		assertEquals(credencialesEntity.getTOKENONE(), esperado.getTOKENONE());
		assertNotEquals(noesperado.getTOKENONE(), credencialesEntity.getTOKENONE());
	}

	@Test
	public final void testGetTOKENTWO() throws Exception {
		CredencialesEntity esperado = mock(CredencialesEntity.class);
		CredencialesEntity noesperado = mock(CredencialesEntity.class);
		when(esperado.getTOKENTWO()).thenReturn("HJJJNHKKJU0546546524165%$/KJNKDKJDNKJD");
		when(noesperado.getTOKENTWO()).thenReturn("NO_546546524165%$/KJNKDKJDNKJD");
		assertEquals(credencialesEntity.getTOKENTWO(), esperado.getTOKENTWO());
		assertNotEquals(noesperado.getTOKENTWO(), credencialesEntity.getTOKENTWO());
	}



}
