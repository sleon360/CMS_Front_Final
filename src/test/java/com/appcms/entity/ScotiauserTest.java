package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class ScotiauserTest {
/*
	private Scotiauser scotiauser;
	
	@Before
	public void setUp() throws Exception {
		scotiauser=new Scotiauser(2, "177824577", "Fabian", "Gaete", "fgaete@afiniti.cl","1");
		scotiauser=new Scotiauser();
		
		scotiauser.setAddress_id("11");
		scotiauser.setEmail("EMAIL@CORREO.CL");
		scotiauser.setFirstname("PRIMERNOMBRE");
		scotiauser.setId_cliente(15);
		scotiauser.setid_grupo("25");
		scotiauser.setLastname("SEGUNDONOMBRE");
		scotiauser.setPoints(50245);
		scotiauser.setRut("1-9");
		scotiauser.setTelephone("+5698544551");
		scotiauser.setTokenScotia("dfgsdf8gsd76fg76es7fg89thkh45owerklgsedmrglk");
	}


	@Test
	public final void testGetPints() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getPints()).thenReturn(10);
		when(noesperado.getPints()).thenReturn(854654);
		assertEquals(scotiauser.getPints(), esperado.getPints());
		assertNotEquals(noesperado.getPints(), scotiauser.getPints());
	}

	@Test
	public final void testGetId_cliente() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getId_cliente()).thenReturn(15);
		when(noesperado.getId_cliente()).thenReturn(84);
		assertEquals(scotiauser.getId_cliente(), esperado.getId_cliente());
		assertNotEquals(noesperado.getId_cliente(), scotiauser.getId_cliente());
	}

	@Test
	public final void testGetFirstname() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getFirstname()).thenReturn("PRIMERNOMBRE");
		when(noesperado.getFirstname()).thenReturn("NO_PRIMERNOMBRE");
		assertEquals(scotiauser.getFirstname(), esperado.getFirstname());
		assertNotEquals(noesperado.getFirstname(), scotiauser.getFirstname());
	}

	@Test
	public final void testGetLastname() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getLastname()).thenReturn("SEGUNDONOMBRE");
		when(noesperado.getLastname()).thenReturn("NO_SEGUNDONOMBRE");
		assertEquals(scotiauser.getLastname(), esperado.getLastname());
		assertNotEquals(noesperado.getLastname(), scotiauser.getLastname());
	}

	@Test
	public final void testGetid_grupo() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getId_grupo()).thenReturn("25");
		when(noesperado.getId_grupo()).thenReturn("74857");
		assertEquals(scotiauser.getId_grupo(), esperado.getId_grupo());
		assertNotEquals(noesperado.getId_grupo(), scotiauser.getId_grupo());
	}

	@Test
	public final void testGetEmail() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getEmail()).thenReturn("EMAIL@CORREO.CL");
		when(noesperado.getEmail()).thenReturn("NO_EMAIL@CORREO.CL");
		assertEquals(scotiauser.getEmail(), esperado.getEmail());
		assertNotEquals(noesperado.getEmail(), scotiauser.getEmail());
	}

	@Test
	public final void testGetRut() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getRut()).thenReturn("1-9");
		when(noesperado.getRut()).thenReturn("11232456-9");
		assertEquals(scotiauser.getRut(), esperado.getRut());
		assertNotEquals(noesperado.getRut(), scotiauser.getRut());
	}

	@Test
	public final void testGetTelephone() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getTelephone()).thenReturn("+5698544551");
		when(noesperado.getTelephone()).thenReturn("+5698444651");
		assertEquals(scotiauser.getTelephone(), esperado.getTelephone());
		assertNotEquals(noesperado.getTelephone(), scotiauser.getTelephone());
	}

	@Test
	public final void testGetAddress_id() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getAddress_id()).thenReturn("11");
		when(noesperado.getAddress_id()).thenReturn("545");
		assertEquals(scotiauser.getAddress_id(), esperado.getAddress_id());
		assertNotEquals(noesperado.getAddress_id(), scotiauser.getAddress_id());
	}

	@Test
	public final void testGetId_grupo() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getid_grupo()).thenReturn("25");
		when(noesperado.getid_grupo()).thenReturn("75745");
		assertEquals(scotiauser.getid_grupo(), esperado.getid_grupo());
		assertNotEquals(noesperado.getid_grupo(), scotiauser.getid_grupo());
	}

	@Test
	public final void testGetTokenScotia() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getTokenScotia()).thenReturn("dfgsdf8gsd76fg76es7fg89thkh45owerklgsedmrglk");
		when(noesperado.getTokenScotia()).thenReturn("dfgsd76fg76es7fg89thkh45owerklgsedmrglk");
		assertEquals(scotiauser.getTokenScotia(), esperado.getTokenScotia());
		assertNotEquals(noesperado.getTokenScotia(), scotiauser.getTokenScotia());
	}

	@Test
	public final void testGetPoints() throws Exception {
		Scotiauser esperado = mock(Scotiauser.class);
		Scotiauser noesperado = mock(Scotiauser.class);
		when(esperado.getPoints()).thenReturn(50245);
		when(noesperado.getPoints()).thenReturn(68555);
		assertEquals(scotiauser.getPoints(), esperado.getPoints());
		assertNotEquals(noesperado.getPoints(), scotiauser.getPoints());
	}
*/
}
