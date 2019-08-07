package com.appcms.entity;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import edu.emory.mathcs.backport.java.util.Arrays;

@RunWith(SpringRunner.class)
public class ResourceEntityTest {

	private ResourceEntity resourceEntity;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		resourceEntity=new ResourceEntity();
		resourceEntity.setId("123546545");
		resourceEntity.setChecksum_resource("F7C02E9EC46F3471D2671728B2DEF7");
		resourceEntity.setMime_resource("image/jpg");
		resourceEntity.setNombre_resource("F7C02E9EC46F3471D2671728B2DEF7.JPG");
		resourceEntity.setData(new byte[0101010101]);
		
	}

	@Test
	public final void testGetId() throws Exception {
		ResourceEntity esperado = mock(ResourceEntity.class);
		ResourceEntity noesperado = mock(ResourceEntity.class);
		when(esperado.getId()).thenReturn("123546545");
		when(noesperado.getId()).thenReturn("12300000000");
		assertEquals(resourceEntity.getId(), esperado.getId());
		assertNotEquals(noesperado.getId(), resourceEntity.getId());
	}

	@Test
	public final void testGetNombre_resource() throws Exception {
		ResourceEntity esperado = mock(ResourceEntity.class);
		ResourceEntity noesperado = mock(ResourceEntity.class);
		when(esperado.getNombre_resource()).thenReturn("F7C02E9EC46F3471D2671728B2DEF7.JPG");
		when(noesperado.getNombre_resource()).thenReturn("985492e9e900000000000028b2def7.jpg");
		assertEquals(esperado.getNombre_resource(),resourceEntity.getNombre_resource());
		assertNotEquals(noesperado.getNombre_resource(), resourceEntity.getNombre_resource());
	}

	@Test
	public final void testGetMime_resource() throws Exception {
		ResourceEntity esperado = mock(ResourceEntity.class);
		ResourceEntity noesperado = mock(ResourceEntity.class);
		when(esperado.getMime_resource()).thenReturn("image/jpg");
		when(noesperado.getMime_resource()).thenReturn("image/png");
		assertEquals(resourceEntity.getMime_resource(), esperado.getMime_resource());
		assertNotEquals(noesperado.getMime_resource(), resourceEntity.getMime_resource());
	}

	@Test
	public final void testGetChecksum_resource() throws Exception {
		ResourceEntity esperado = mock(ResourceEntity.class);
		ResourceEntity noesperado = mock(ResourceEntity.class);
		when(esperado.getChecksum_resource()).thenReturn("F7C02E9EC46F3471D2671728B2DEF7");
		when(noesperado.getChecksum_resource()).thenReturn("F7C02E9EC46F88888888728B2DEF7");
		assertEquals(resourceEntity.getChecksum_resource(), esperado.getChecksum_resource());
		assertNotEquals(noesperado.getChecksum_resource(), resourceEntity.getChecksum_resource());
	}

	@Test
	public final void testGetData() throws Exception {
		
		ResourceEntity esperado = mock(ResourceEntity.class);
		ResourceEntity noesperado = mock(ResourceEntity.class);
		when(esperado.getData()).thenReturn(new byte[0101010101]);
		when(noesperado.getData()).thenReturn(new byte[1010101]);
		assertArrayEquals(resourceEntity.getData(), esperado.getData());
		assertFalse(Arrays.equals(noesperado.getData(), resourceEntity.getData()));
	}

}
