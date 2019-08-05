package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CanjeProductoTest {
	
	private CanjeProducto canjeProducto;
	private CanjeProducto canjeProductoInit;

    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	

	@Before
	public void setUp() throws Exception {
		canjeProducto=new CanjeProducto(50,"Nombre123","Nombre123",10000);
		canjeProducto.setCsrf_token("9565bea199c52e8d1e1f7a41c88d2565");
		canjeProducto.setActionx("Actionx");
		canjeProducto.setRutAsociado("Nombre123");
		canjeProducto.setIdProducto(50);
		canjeProducto.setMonto(10000);
		canjeProducto.setNombreAsociado("Nombre123");
		canjeProducto.setCantidad(10000);
		canjeProductoInit=new CanjeProducto();
	}

	@Test
	public final void testCanjeProducto() throws Exception {   
		
		CanjeProducto esperado = mock(CanjeProducto.class);     
		CanjeProducto noesperado = mock(CanjeProducto.class); 
		
        when(esperado.getMonto()).thenReturn(0);
        when(noesperado.getMonto()).thenReturn(111);
        
        assertEquals(canjeProductoInit.getMonto(), esperado.getMonto());
        assertNotEquals(noesperado.getMonto(),canjeProductoInit.getMonto());
	}


	@Test
	public final void testGetCsrf_token() throws Exception {
		CanjeProducto esperado = mock(CanjeProducto.class);     
		CanjeProducto noesperado = mock(CanjeProducto.class);     
		
        when(esperado.getCsrf_token()).thenReturn("9565bea199c52e8d1e1f7a41c88d2565");
        when(noesperado.getCsrf_token()).thenReturn("000000000000000000000000000000000");
        
        assertEquals(canjeProducto.getCsrf_token(), esperado.getCsrf_token());
   
        assertNotEquals(noesperado.getCsrf_token(),canjeProducto.getCsrf_token());
	}


	@Test
	public final void testGetIdProducto() throws Exception {
		CanjeProducto esperado = mock(CanjeProducto.class);     
		CanjeProducto noesperado = mock(CanjeProducto.class);     
		
        when(esperado.getIdProducto()).thenReturn(50);
        when(noesperado.getIdProducto()).thenReturn(100);
        
        assertEquals(canjeProducto.getIdProducto(), esperado.getIdProducto());
   
        assertNotEquals(noesperado.getIdProducto(),canjeProducto.getIdProducto());
	}



	@Test
	public final void testGetNombreAsociado() throws Exception {
		CanjeProducto esperado = mock(CanjeProducto.class);     
		CanjeProducto noesperado = mock(CanjeProducto.class);     
		
        when(esperado.getNombreAsociado()).thenReturn("Nombre123");
        when(noesperado.getNombreAsociado()).thenReturn("NombreNoesperadp");
        
        assertEquals(canjeProducto.getNombreAsociado(), esperado.getNombreAsociado());
   
        assertNotEquals(noesperado.getNombreAsociado(),canjeProducto.getNombreAsociado());
	}



	@Test
	public final void testGetRutAsociado() throws Exception {
		CanjeProducto esperado = mock(CanjeProducto.class);     
		CanjeProducto noesperado = mock(CanjeProducto.class);     
		
        when(esperado.getRutAsociado()).thenReturn("Nombre123");
        when(noesperado.getRutAsociado()).thenReturn("NombreNoesperado");
        assertEquals(canjeProducto.getRutAsociado(), esperado.getRutAsociado());
        assertNotEquals(noesperado.getRutAsociado(),canjeProducto.getRutAsociado());
	}



	@Test
	public final void testGetCantidad() throws Exception {
		CanjeProducto esperado = mock(CanjeProducto.class);     
		CanjeProducto noesperado = mock(CanjeProducto.class);     
		
        when(esperado.getCantidad()).thenReturn(10000);
        when(noesperado.getCantidad()).thenReturn(0);
        assertEquals(canjeProducto.getCantidad(), esperado.getCantidad());
        assertNotEquals(noesperado.getCantidad(),canjeProducto.getCantidad());
	}

	@Test
	public final void testGetActionx() throws Exception {
		CanjeProducto esperado = mock(CanjeProducto.class);     
		CanjeProducto noesperado = mock(CanjeProducto.class);     
		
        when(esperado.getActionx()).thenReturn("Actionx");
        when(noesperado.getActionx()).thenReturn("Actionx_ERROR");
        assertEquals(canjeProducto.getActionx(), esperado.getActionx());
        assertNotEquals(noesperado.getActionx(),canjeProducto.getActionx());
	}

	@Test
	public final void testGetMonto() throws Exception {
		CanjeProducto esperado = mock(CanjeProducto.class);     
		CanjeProducto noesperado = mock(CanjeProducto.class);     
		
        when(esperado.getMonto()).thenReturn(10000);
        when(noesperado.getMonto()).thenReturn(555555);
        assertEquals(canjeProducto.getMonto(), esperado.getMonto());
        assertNotEquals(noesperado.getMonto(),canjeProducto.getMonto());
	}


	@Test
	public final void testToString() throws Exception {
		CanjeProducto esperado = mock(CanjeProducto.class);     
		CanjeProducto noesperado = mock(CanjeProducto.class);     
		
        when(esperado.toString()).thenReturn("CanjeProducto [idProducto=50, nombreAsociado=Nombre123, rutAsociado=Nombre123, cantidad=10000, csrf_token=9565bea199c52e8d1e1f7a41c88d2565]");
        when(noesperado.toString()).thenReturn("toString_NO");
        assertEquals(canjeProducto.toString(), esperado.toString());
        assertNotEquals(noesperado.toString(),canjeProducto.toString());
	}

}
