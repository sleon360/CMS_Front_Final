package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class CustomerRewardTest {

	private CustomerReward customerReward;
	
	@Before
	public void setUp() throws Exception {
		customerReward=new CustomerReward();
		customerReward.setCustomer_id(100);
		customerReward.setCustomer_reward_id(500);
		customerReward.setDate_added("10-10-2019");
		customerReward.setDate_vencimiento("11-11-2019");
		customerReward.setDescription("CUSTOMER DESCRIPTION REWARD");
		customerReward.setId_campana(5);
		customerReward.setId_campana(60);
		customerReward.setId_jos_ticket(40);
		customerReward.setId_trx(22);
		customerReward.setOrder_id(2);
		customerReward.setPoints(5000);
		customerReward.setTipo_reward(24);
	}
	
	/*

	@Test
	public final void testCustomerReward() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testCustomerRewardIntIntIntStringIntStringStringIntIntIntInt() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testCustomerRewardIntIntStringIntStringStringIntIntIntInt() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}*/

	@Test
	public final void testGetCustomer_reward_id() throws Exception {
		CustomerReward esperado = mock(CustomerReward.class);
		CustomerReward noesperado = mock(CustomerReward.class);
		when(esperado.getCustomer_reward_id()).thenReturn(500);
		when(noesperado.getCustomer_reward_id()).thenReturn(300);
		assertEquals(customerReward.getCustomer_reward_id(), esperado.getCustomer_reward_id());
		assertNotEquals(noesperado.getCustomer_reward_id(), customerReward.getCustomer_reward_id());
	}

	@Test
	public final void testGetCustomer_id() throws Exception {
		CustomerReward esperado = mock(CustomerReward.class);
		CustomerReward noesperado = mock(CustomerReward.class);
		when(esperado.getCustomer_id()).thenReturn(100);
		when(noesperado.getCustomer_id()).thenReturn(300);
		assertEquals(customerReward.getCustomer_id(), esperado.getCustomer_id());
		assertNotEquals(noesperado.getCustomer_id(), customerReward.getCustomer_id());
	}

	@Test
	public final void testGetOrder_id() throws Exception {
		CustomerReward esperado = mock(CustomerReward.class);
		CustomerReward noesperado = mock(CustomerReward.class);
		when(esperado.getOrder_id()).thenReturn(2);
		when(noesperado.getOrder_id()).thenReturn(654);
		assertEquals(customerReward.getOrder_id(), esperado.getOrder_id());
		assertNotEquals(noesperado.getOrder_id(), customerReward.getOrder_id());
	}

	@Test
	public final void testGetDescription() throws Exception {
		CustomerReward esperado = mock(CustomerReward.class);
		CustomerReward noesperado = mock(CustomerReward.class);
		when(esperado.getDescription()).thenReturn("CUSTOMER DESCRIPTION REWARD");
		when(noesperado.getDescription()).thenReturn("NO_CUSTOMER DESCRIPTION REWARD");
		assertEquals(customerReward.getDescription(), esperado.getDescription());
		assertNotEquals(noesperado.getDescription(), customerReward.getDescription());
	}

	@Test
	public final void testGetPoints() throws Exception {
		CustomerReward esperado = mock(CustomerReward.class);
		CustomerReward noesperado = mock(CustomerReward.class);
		when(esperado.getPoints()).thenReturn(5000);
		when(noesperado.getPoints()).thenReturn(5001);
		assertEquals(customerReward.getPoints(), esperado.getPoints());
		assertNotEquals(noesperado.getPoints(), customerReward.getPoints());
	}

	@Test
	public final void testGetDate_added() throws Exception {
		CustomerReward esperado = mock(CustomerReward.class);
		CustomerReward noesperado = mock(CustomerReward.class);
		when(esperado.getDate_added()).thenReturn("10-10-2019");
		when(noesperado.getDate_added()).thenReturn("10-10-2022");
		assertEquals(customerReward.getDate_added(), esperado.getDate_added());
		assertNotEquals(noesperado.getDate_added(), customerReward.getDate_added());
	}

	@Test
	public final void testGetDate_vencimiento() throws Exception {
		CustomerReward esperado = mock(CustomerReward.class);
		CustomerReward noesperado = mock(CustomerReward.class);
		when(esperado.getDate_vencimiento()).thenReturn("11-11-2019");
		when(noesperado.getDate_vencimiento()).thenReturn("11-11-2018");
		assertEquals(customerReward.getDate_vencimiento(), esperado.getDate_vencimiento());
		assertNotEquals(noesperado.getDate_vencimiento(), customerReward.getDate_vencimiento());
	}

	@Test
	public final void testGetId_campana() throws Exception {
		CustomerReward esperado = mock(CustomerReward.class);
		CustomerReward noesperado = mock(CustomerReward.class);
		when(esperado.getId_campana()).thenReturn(60);
		when(noesperado.getId_campana()).thenReturn(88);
		assertEquals(customerReward.getId_campana(), esperado.getId_campana());
		assertNotEquals(noesperado.getId_campana(), customerReward.getId_campana());
	}

	@Test
	public final void testGetId_trx() throws Exception {
		CustomerReward esperado = mock(CustomerReward.class);
		CustomerReward noesperado = mock(CustomerReward.class);
		when(esperado.getId_trx()).thenReturn(22);
		when(noesperado.getId_trx()).thenReturn(88);
		assertEquals(customerReward.getId_trx(), esperado.getId_trx());
		assertNotEquals(noesperado.getId_trx(), customerReward.getId_trx());
	}

	@Test
	public final void testGetId_jos_ticket() throws Exception {
		CustomerReward esperado = mock(CustomerReward.class);
		CustomerReward noesperado = mock(CustomerReward.class);
		when(esperado.getId_jos_ticket()).thenReturn(40);
		when(noesperado.getId_jos_ticket()).thenReturn(74);
		assertEquals(customerReward.getId_jos_ticket(), esperado.getId_jos_ticket());
		assertNotEquals(noesperado.getId_jos_ticket(), customerReward.getId_jos_ticket());
	}

	@Test
	public final void testGetTipo_reward() throws Exception {
		CustomerReward esperado = mock(CustomerReward.class);
		CustomerReward noesperado = mock(CustomerReward.class);
		when(esperado.getTipo_reward()).thenReturn(24);
		when(noesperado.getTipo_reward()).thenReturn(74);
		assertEquals(customerReward.getTipo_reward(), esperado.getTipo_reward());
		assertNotEquals(noesperado.getTipo_reward(), customerReward.getTipo_reward());
	}

}
