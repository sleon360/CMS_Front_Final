package com.appcms.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ProductoCategoriaTest {
/*
	private ProductoCategoria productoCategoria;
	@Before
	public void setUp() throws Exception {
		productoCategoria=new ProductoCategoria();
		productoCategoria.setId(111);
		productoCategoria.setDescripccion("DESCRIPCION");
		productoCategoria.setImagen("/resource/images/lg-tanta.png");
		productoCategoria.setColorHover("#CCCCCC");
		productoCategoria.setNombre("NOMBRE PRODUCTO");
		productoCategoria.setStrIndex("PROD001");
		productoCategoria.setTipo(5);
		
		List<ProductoTipoLike> productos =  new ArrayList<>();
		productos.add( new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		productos.add( new ProductoTipoLike(2, "Danieli v1", "Danieli 2", "Coffee Bar", "/resource/images/ver_img.png", "25%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 10) );
		productos.add( new ProductoTipoLike(3, "Danieli v1", "Test 3", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 30) );
		productos.add( new ProductoTipoLike(4, "Danieli v1", "Prod xn", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 100) );
		productoCategoria.setProductos(productos);
	}

	@Test
	public final void testGetId() throws Exception {
		ProductoCategoria esperado = mock(ProductoCategoria.class);
		ProductoCategoria noesperado = mock(ProductoCategoria.class);
		when(esperado.getId()).thenReturn(111);
		when(noesperado.getId()).thenReturn(5555);
		assertEquals(productoCategoria.getId(), esperado.getId());
		assertNotEquals(noesperado.getId(), productoCategoria.getId());
	}

	@Test
	public final void testGetColorHover() throws Exception {
		ProductoCategoria esperado = mock(ProductoCategoria.class);
		ProductoCategoria noesperado = mock(ProductoCategoria.class);
		when(esperado.getColorHover()).thenReturn("#CCCCCC");
		when(noesperado.getColorHover()).thenReturn("#000CCC");
		assertEquals(productoCategoria.getColorHover(), esperado.getColorHover());
		assertNotEquals(noesperado.getColorHover(), productoCategoria.getColorHover());
	}

	@Test
	public final void testGetStrIndex() throws Exception {
		ProductoCategoria esperado = mock(ProductoCategoria.class);
		ProductoCategoria noesperado = mock(ProductoCategoria.class);
		when(esperado.getStrIndex()).thenReturn("PROD001");
		when(noesperado.getStrIndex()).thenReturn("PROD000");
		assertEquals(productoCategoria.getStrIndex(), esperado.getStrIndex());
		assertNotEquals(noesperado.getStrIndex(), productoCategoria.getStrIndex());
	}

	@Test
	public final void testGetNombre() throws Exception {
		ProductoCategoria esperado = mock(ProductoCategoria.class);
		ProductoCategoria noesperado = mock(ProductoCategoria.class);
		when(esperado.getNombre()).thenReturn("NOMBRE PRODUCTO");
		when(noesperado.getNombre()).thenReturn("NO NOMBRE PRODUCTO");
		assertEquals(productoCategoria.getNombre(), esperado.getNombre());
		assertNotEquals(noesperado.getNombre(), productoCategoria.getNombre());
	}

	@Test
	public final void testGetDescripccion() throws Exception {
		ProductoCategoria esperado = mock(ProductoCategoria.class);
		ProductoCategoria noesperado = mock(ProductoCategoria.class);
		when(esperado.getDescripccion()).thenReturn("DESCRIPCION");
		when(noesperado.getDescripccion()).thenReturn("NO DESCRIPCION");
		assertEquals(productoCategoria.getDescripccion(), esperado.getDescripccion());
		assertNotEquals(noesperado.getDescripccion(), productoCategoria.getDescripccion());
	}

	@Test
	public final void testGetImagen() throws Exception {
		ProductoCategoria esperado = mock(ProductoCategoria.class);
		ProductoCategoria noesperado = mock(ProductoCategoria.class);
		when(esperado.getImagen()).thenReturn("/resource/images/lg-tanta.png");
		when(noesperado.getImagen()).thenReturn("/resource/images/logo.png");
		assertEquals(productoCategoria.getImagen(), esperado.getImagen());
		assertNotEquals(noesperado.getImagen(), productoCategoria.getImagen());
	}

	@Test
	public final void testGetTipo() throws Exception {
		ProductoCategoria esperado = mock(ProductoCategoria.class);
		ProductoCategoria noesperado = mock(ProductoCategoria.class);
		when(esperado.getTipo()).thenReturn(5);
		when(noesperado.getTipo()).thenReturn(1);
		assertEquals(productoCategoria.getTipo(), esperado.getTipo());
		assertNotEquals(noesperado.getTipo(), productoCategoria.getTipo());
	}

	@Test
	public final void testGetProductos() throws Exception {
		ProductoCategoria esperado = mock(ProductoCategoria.class);
		ProductoCategoria noesperado = mock(ProductoCategoria.class);
		
		List<ProductoTipoLike> productos =  new ArrayList<>();
		productos.add( new ProductoTipoLike(1, "Danieli v1", "Danieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		productos.add( new ProductoTipoLike(2, "Danieli v1", "Danieli 2", "Coffee Bar", "/resource/images/ver_img.png", "25%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 10) );
		productos.add( new ProductoTipoLike(3, "Danieli v1", "Test 3", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 30) );
		productos.add( new ProductoTipoLike(4, "Danieli v1", "Prod xn", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 100) );
		
		List<ProductoTipoLike> noproductos =  new ArrayList<>();
		noproductos.add( new ProductoTipoLike(1, "Danieli xv1", "xDanieli", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		
		when(esperado.getProductos()).thenReturn(productos);
		when(noesperado.getProductos()).thenReturn(noproductos);
	
		assertTrue(productoCategoria.equalsProductoTipoLike(esperado.getProductos()));
		assertFalse(productoCategoria.equalsProductoTipoLike(noproductos));
		
		
		productos =  new ArrayList<>();
		productos.add( new ProductoTipoLike(1, "Danieliss v1", "Danieliss", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		productos.add( new ProductoTipoLike(2, "Danieli v1", "Danieli 2", "Coffee Bar", "/resource/images/ver_img.png", "25%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 10) );
		productos.add( new ProductoTipoLike(3, "Danieli v1", "Test 3", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 30) );
		productos.add( new ProductoTipoLike(4, "Danieli v1", "Prod xn", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 100) );
		
		noproductos =  new ArrayList<>();
		noproductos.add( new ProductoTipoLike(1, "Danielixx v1", "Danielixx", "Coffee Bar", "/resource/images/ver_img.png", "40%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 3) );
		noproductos.add( new ProductoTipoLike(2, "Danieli v1", "Danieli 2", "Coffee Bar", "/resource/images/ver_img.png", "25%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 10) );
		noproductos.add( new ProductoTipoLike(3, "Danieli v1", "Test 3", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 30) );
		noproductos.add( new ProductoTipoLike(4, "Danieli v1", "Prod xn", "Coffee Bar", "/resource/images/ver_img.png", "35%", "Av. 4 Esquinas 1540, Local 1, Strip Center el Milagro, IV Región", "", "", 100) );
		
		when(esperado.getProductos()).thenReturn(productos);
		when(noesperado.getProductos()).thenReturn(noproductos);
		
		assertFalse(productoCategoria.equalsProductoTipoLike(esperado.getProductos()));
		assertFalse(productoCategoria.equalsProductoTipoLike(noproductos));
	}
*/
}
