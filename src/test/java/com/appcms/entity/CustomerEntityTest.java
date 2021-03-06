package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class CustomerEntityTest {

	private CustomerEntity customerEntity;
	
	@Before
	public void setUp() throws Exception {
		customerEntity=new CustomerEntity();
		customerEntity.setIdCustomer(11111);
		customerEntity.setEstado(1);
		customerEntity.setEmail("email@gmail.com");
		customerEntity.setFechaRegistro("01-01-2019");
		customerEntity.setPrimerApellido("PRIMER APELLIDO");
		customerEntity.setPrimerNombre("PRIMER NOMBRE");
		customerEntity.setRut("12345678-9");
		customerEntity.setSegundoNombre("SEGUNDO NOMBRE");
		customerEntity.setSegundoApellido("SEGUNDO APELLIDO");
		customerEntity.setTelefono("+56987456288");
	}

	@Test
	public final void testGetId() throws Exception {
		CustomerEntity esperado = mock(CustomerEntity.class);
		CustomerEntity noesperado = mock(CustomerEntity.class);
		when(esperado.getIdCustomer()).thenReturn(11111);
		when(noesperado.getIdCustomer()).thenReturn(555);
		assertEquals(customerEntity.getIdCustomer(), esperado.getIdCustomer());
		assertNotEquals(noesperado.getIdCustomer(), customerEntity.getIdCustomer());
	}

	@Test
	public final void testGetRut() throws Exception {
		CustomerEntity esperado = mock(CustomerEntity.class);
		CustomerEntity noesperado = mock(CustomerEntity.class);
		when(esperado.getRut()).thenReturn("12345678-9");
		when(noesperado.getRut()).thenReturn("8525678-9");
		assertEquals(customerEntity.getRut(), esperado.getRut());
		assertNotEquals(noesperado.getRut(), customerEntity.getRut());
	}

	@Test
	public final void testGetPrimerNombre() throws Exception {
		CustomerEntity esperado = mock(CustomerEntity.class);
		CustomerEntity noesperado = mock(CustomerEntity.class);
		when(esperado.getPrimerNombre()).thenReturn("PRIMER NOMBRE");
		when(noesperado.getPrimerNombre()).thenReturn("NO_PRIMER NOMBRE");
		assertEquals(customerEntity.getPrimerNombre(), esperado.getPrimerNombre());
		assertNotEquals(noesperado.getPrimerNombre(), customerEntity.getPrimerNombre());
	}

	@Test
	public final void testGetSegundoNombre() throws Exception {
		CustomerEntity esperado = mock(CustomerEntity.class);
		CustomerEntity noesperado = mock(CustomerEntity.class);
		when(esperado.getSegundoNombre()).thenReturn("SEGUNDO NOMBRE");
		when(noesperado.getSegundoNombre()).thenReturn("NO_SEGUNDO NOMBRE");
		assertEquals(customerEntity.getSegundoNombre(), esperado.getSegundoNombre());
		assertNotEquals(noesperado.getSegundoNombre(), customerEntity.getSegundoNombre());
	}

	@Test
	public final void testGetPrimerApellido() throws Exception {
		CustomerEntity esperado = mock(CustomerEntity.class);
		CustomerEntity noesperado = mock(CustomerEntity.class);
		when(esperado.getPrimerApellido()).thenReturn("PRIMER APELLIDO");
		when(noesperado.getPrimerApellido()).thenReturn("NO_PRIMER APELLIDO");
		assertEquals(customerEntity.getPrimerApellido(), esperado.getPrimerApellido());
		assertNotEquals(noesperado.getPrimerApellido(), customerEntity.getPrimerApellido());
	}

	@Test
	public final void testGetSegundoApellido() throws Exception {
		CustomerEntity esperado = mock(CustomerEntity.class);
		CustomerEntity noesperado = mock(CustomerEntity.class);
		when(esperado.getSegundoApellido()).thenReturn("SEGUNDO APELLIDO");
		when(noesperado.getSegundoApellido()).thenReturn("NO_SEGUNDO APELLIDO");
		assertEquals(customerEntity.getSegundoApellido(), esperado.getSegundoApellido());
		assertNotEquals(noesperado.getSegundoApellido(), customerEntity.getSegundoApellido());
	}

	@Test
	public final void testGetEmail() throws Exception {
		CustomerEntity esperado = mock(CustomerEntity.class);
		CustomerEntity noesperado = mock(CustomerEntity.class);
		when(esperado.getEmail()).thenReturn("email@gmail.com");
		when(noesperado.getEmail()).thenReturn("NO_email@gmail.com");
		assertEquals(customerEntity.getEmail(), esperado.getEmail());
		assertNotEquals(noesperado.getEmail(), customerEntity.getEmail());
	}

	@Test
	public final void testGetEstado() throws Exception {
		CustomerEntity esperado = mock(CustomerEntity.class);
		CustomerEntity noesperado = mock(CustomerEntity.class);
		when(esperado.getEstado()).thenReturn(1);
		when(noesperado.getEstado()).thenReturn(6);
		assertEquals(customerEntity.getEstado(), esperado.getEstado());
		assertNotEquals(noesperado.getEstado(), customerEntity.getEstado());
	}

	@Test
	public final void testGetTelefono() throws Exception {
		CustomerEntity esperado = mock(CustomerEntity.class);
		CustomerEntity noesperado = mock(CustomerEntity.class);
		when(esperado.getTelefono()).thenReturn("+56987456288");
		when(noesperado.getTelefono()).thenReturn("55941256288");
		assertEquals(customerEntity.getTelefono(), esperado.getTelefono());
		assertNotEquals(noesperado.getTelefono(), customerEntity.getTelefono());
	}

	@Test
	public final void testGetFechaRegistro() throws Exception {
		CustomerEntity esperado = mock(CustomerEntity.class);
		CustomerEntity noesperado = mock(CustomerEntity.class);
		when(esperado.getFechaRegistro()).thenReturn("01-01-2019");
		when(noesperado.getFechaRegistro()).thenReturn("05-01-2019");
		assertEquals(customerEntity.getFechaRegistro(), esperado.getFechaRegistro());
		assertNotEquals(noesperado.getFechaRegistro(), customerEntity.getFechaRegistro());
	}

}
