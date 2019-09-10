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
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductoTipoLikeTest {

	private ProductoTipoLike productotipolike;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		productotipolike = new ProductoTipoLike();
		productotipolike.setCanjeados(1111111111);
		productotipolike.setId(123);
		productotipolike.setNombre("LOKI");
		productotipolike.setTitulo("TITULO DE PRODUCTO");
		productotipolike.setSubtitulo("SUBTITULO DE PRODUCTO");
		productotipolike.setImagen("IMAGEN PRODUCTOLIKE");
		productotipolike.setPorcentajeInfo("100%");
		productotipolike.setDireccionTxt1("LAS CONDES 123");
		productotipolike.setDireccionTxt2("LAS CONDES 321");
		productotipolike.setDireccionTxt3("LAS CONDES 456");
		productotipolike.setLike(1);
		productotipolike.setPrecio(5000);
		productotipolike.setFecha_creacion("2019-06-06");
		productotipolike.setFecha_fin("2020-06-06");
		productotipolike.setTitulo_detalles("DETALLE DETALLE DETALLE DETALLE");
		productotipolike.setTutulo_direcciones("TITULO DIRECCIONES");
		productotipolike.setEquipesos(5);
		productotipolike.setStock(100);
		productotipolike.setCodigo("SD465465765465");

		List<FormatoDetalle> detalles = new ArrayList<FormatoDetalle>();
		detalles.add(
				new FormatoDetalle("Condiciones", "Beneficio no acumulable con otras promociones y/o descuentos.", 1));
		detalles.add(new FormatoDetalle("¿Qué es Mafe Pastelería?",
				"Beneficio no acumulable con otras promociones y/o descuentos.", 1));
		detalles.add(new FormatoDetalle("¿Cómo accedo a este descuento?",
				"Beneficio no acumulable con otras promociones y/o descuentos.", 1));
		productotipolike.setDetalles(detalles);

		List<FormatoDetalle> formatosDetalles = new ArrayList<FormatoDetalle>();
		formatosDetalles.add(
				new FormatoDetalle("Condiciones", "Beneficio no acumulable con otras promociones y/o descuentos.", 1));
		formatosDetalles.add(new FormatoDetalle("¿Qué es Mafe Pastelería?",
				"Beneficio no acumulable con otras promociones y/o descuentos.", 1));
		formatosDetalles.add(new FormatoDetalle("¿Cómo accedo a este descuento?",
				"Beneficio no acumulable con otras promociones y/o descuentos.", 1));
		productotipolike.setFormatosDetalles(formatosDetalles);

		List<FormatoDetalle> direcciones = new ArrayList<FormatoDetalle>();
		direcciones.add(new FormatoDetalle("Dirección Web:", "http://google.com/MafePasteleria/", 2));
		direcciones.add(new FormatoDetalle("Dirección y Teléfono:", "Av Central, Región Metropolitana", 1));
		productotipolike.setDirecciones(direcciones);

	}

	@Test
	public final void testGetStock() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);
		when(esperado.getStock()).thenReturn(100);
		when(noesperado.getStock()).thenReturn(10000);
		assertEquals(productotipolike.getStock(), esperado.getStock());
		assertNotEquals(noesperado.getStock(), productotipolike.getStock());
	}

	@Test
	public final void testGetCanjeados() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getCanjeados()).thenReturn(1111111111);
		when(noesperado.getCanjeados()).thenReturn(12345689);

		assertEquals(productotipolike.getCanjeados(), esperado.getCanjeados());
		assertNotEquals(noesperado.getCanjeados(), productotipolike.getCanjeados());
	}


	@Test
	public final void testGetId() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getId()).thenReturn(123);
		when(noesperado.getId()).thenReturn(12345689);
		assertEquals(productotipolike.getId(), esperado.getId());
		assertNotEquals(noesperado.getId(), productotipolike.getId());
	}

	@Test
	public final void testGetNombre() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getNombre()).thenReturn("LOKI");
		when(noesperado.getNombre()).thenReturn("LOKI123");
		assertEquals(productotipolike.getNombre(), esperado.getNombre());
		assertNotEquals(noesperado.getNombre(), productotipolike.getNombre());
	}

	@Test
	public final void testGetTitulo() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getTitulo()).thenReturn("TITULO DE PRODUCTO");
		when(noesperado.getTitulo()).thenReturn("TITULO DE PRODUCTO NO ESPERADO");
		assertEquals(productotipolike.getTitulo(), esperado.getTitulo());
		assertNotEquals(noesperado.getTitulo(), productotipolike.getTitulo());
	}

	@Test
	public final void testGetSubtitulo() throws Exception {

		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getSubtitulo()).thenReturn("SUBTITULO DE PRODUCTO");
		when(noesperado.getSubtitulo()).thenReturn("SUBTITULO DE PRODUCTO NO ESPERADO");
		assertEquals(productotipolike.getSubtitulo(), esperado.getSubtitulo());
		assertNotEquals(noesperado.getSubtitulo(), productotipolike.getSubtitulo());
	}

	@Test
	public final void testGetImagen() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getImagen()).thenReturn("IMAGEN PRODUCTOLIKE");
		when(noesperado.getImagen()).thenReturn("IMAGEN PRODUCTOLIKE NO ESPERADO");
		assertEquals(productotipolike.getImagen(), esperado.getImagen());
		assertNotEquals(noesperado.getImagen(), productotipolike.getImagen());
	}

	@Test
	public final void testGetPorcentajeInfo() throws Exception {
		// TODO
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getPorcentajeInfo()).thenReturn("100%");
		when(noesperado.getPorcentajeInfo()).thenReturn("10%");
		assertEquals(productotipolike.getPorcentajeInfo(), esperado.getPorcentajeInfo());
		assertNotEquals(noesperado.getPorcentajeInfo(), productotipolike.getPorcentajeInfo());

	}

	@Test
	public final void testGetDireccionTxt1() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getDireccionTxt1()).thenReturn("LAS CONDES 123");
		when(noesperado.getDireccionTxt1()).thenReturn("SANTIAGO 123");
		assertEquals(productotipolike.getDireccionTxt1(), esperado.getDireccionTxt1());
		assertNotEquals(noesperado.getDireccionTxt1(), productotipolike.getDireccionTxt1());
	}

	@Test
	public final void testGetDireccionTxt2() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getDireccionTxt2()).thenReturn("LAS CONDES 321");
		when(noesperado.getDireccionTxt2()).thenReturn("SANTIAGO 321");
		assertEquals(productotipolike.getDireccionTxt2(), esperado.getDireccionTxt2());
		assertNotEquals(noesperado.getDireccionTxt2(), productotipolike.getDireccionTxt2());
	}

	@Test
	public final void testGetDireccionTxt3() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getDireccionTxt3()).thenReturn("LAS CONDES 456");
		when(noesperado.getDireccionTxt3()).thenReturn("SANTIAGO 321");
		assertEquals(productotipolike.getDireccionTxt3(), esperado.getDireccionTxt3());
		assertNotEquals(noesperado.getDireccionTxt3(), productotipolike.getDireccionTxt3());
	}

	@Test
	public final void testGetLike() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getLike()).thenReturn(1);
		when(noesperado.getLike()).thenReturn(11);
		assertEquals(productotipolike.getLike(), esperado.getLike());
		assertNotEquals(noesperado.getLike(), productotipolike.getLike());
	}

	@Test
	public final void testGetPrecio() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getPrecio()).thenReturn(5000);
		when(noesperado.getPrecio()).thenReturn(10000);
		assertEquals(productotipolike.getPrecio(), esperado.getPrecio());
		assertNotEquals(noesperado.getPrecio(), productotipolike.getPrecio());
	}

	@Test
	public final void testGetFecha_creacion() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getFecha_creacion()).thenReturn("2019-06-06");
		when(noesperado.getFecha_creacion()).thenReturn("2019-06-01");
		assertEquals(productotipolike.getFecha_creacion(), esperado.getFecha_creacion());
		assertNotEquals(noesperado.getFecha_creacion(), productotipolike.getFecha_creacion());
	}

	@Test
	public final void testGetFecha_fin() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getFecha_fin()).thenReturn("2020-06-06");
		when(noesperado.getFecha_fin()).thenReturn("2019-06-01");
		assertEquals(productotipolike.getFecha_fin(), esperado.getFecha_fin());
		assertNotEquals(noesperado.getFecha_fin(), productotipolike.getFecha_fin());
	}

	@Test
	public final void testGetTitulo_detalles() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getTitulo_detalles()).thenReturn("DETALLE DETALLE DETALLE DETALLE");
		when(noesperado.getTitulo_detalles()).thenReturn("DETALLE");
		assertEquals(productotipolike.getTitulo_detalles(), esperado.getTitulo_detalles());
		assertNotEquals(noesperado.getTitulo_detalles(), productotipolike.getTitulo_detalles());
	}

	@Test
	public final void testGetTutulo_direcciones() throws Exception {

		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getTutulo_direcciones()).thenReturn("TITULO DIRECCIONES");
		when(noesperado.getTutulo_direcciones()).thenReturn("TITULO");
		assertEquals(productotipolike.getTutulo_direcciones(), esperado.getTutulo_direcciones());
		assertNotEquals(noesperado.getTutulo_direcciones(), productotipolike.getTutulo_direcciones());
	}

	@Test
	public final void testGetEquipesos() throws Exception {

		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getEquipesos()).thenReturn(5);
		when(noesperado.getEquipesos()).thenReturn(1);
		assertEquals(productotipolike.getEquipesos(), esperado.getEquipesos());
		assertNotEquals(noesperado.getEquipesos(), productotipolike.getEquipesos());
	}

	@Test
	public final void testGetFormatosDetalles() throws Exception {

		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		List<FormatoDetalle> formatosDetalles = new ArrayList<FormatoDetalle>();
		formatosDetalles.add(new FormatoDetalle("Condiciones", "Beneficio no acumulable con otras promociones y/o descuentos.", 1));
		formatosDetalles.add(new FormatoDetalle("¿Qué es Mafe Pastelería?","Beneficio no acumulable con otras promociones y/o descuentos.", 1));
		formatosDetalles.add(new FormatoDetalle("¿Cómo accedo a este descuento?","Beneficio no acumulable con otras promociones y/o descuentos.", 1));

		List<FormatoDetalle> formatosDetallesNoesperado = new ArrayList<FormatoDetalle>();
		formatosDetallesNoesperado.add(new FormatoDetalle("Condiciones", "Beneficio.", 1));
		formatosDetallesNoesperado.add(new FormatoDetalle("¿Qué es Mafe Pastelería?", "Beneficio no acumulable.", 1));
		formatosDetallesNoesperado.add(new FormatoDetalle("¿Cómo descuento?", "Beneficio no acumulable.", 1));

		when(esperado.getFormatosDetalles()).thenReturn(formatosDetalles);
		when(noesperado.getFormatosDetalles()).thenReturn(formatosDetallesNoesperado);
		assertTrue( productotipolike.equalsFormatosDetalles(esperado.getFormatosDetalles()));
		assertFalse(productotipolike.equalsFormatosDetalles(noesperado.getFormatosDetalles()));
		
		
		formatosDetalles = new ArrayList<FormatoDetalle>();
		formatosDetallesNoesperado = new ArrayList<FormatoDetalle>();
		
		when(esperado.getFormatosDetalles()).thenReturn(formatosDetalles);
		when(noesperado.getFormatosDetalles()).thenReturn(formatosDetallesNoesperado);
		
		assertFalse(productotipolike.equalsFormatosDetalles(esperado.getFormatosDetalles()));
		assertFalse(productotipolike.equalsFormatosDetalles(noesperado.getFormatosDetalles()));
		
		formatosDetalles = new ArrayList<FormatoDetalle>();
		formatosDetalles.add(null);
		formatosDetalles.add(null);
		
		formatosDetallesNoesperado = new ArrayList<FormatoDetalle>();
		when(esperado.getFormatosDetalles()).thenReturn(formatosDetalles);
		when(noesperado.getFormatosDetalles()).thenReturn(formatosDetallesNoesperado);
		
		assertFalse(productotipolike.equalsFormatosDetalles(esperado.getFormatosDetalles()));
		assertFalse(productotipolike.equalsFormatosDetalles(noesperado.getFormatosDetalles()));
		
		
		
		
	}
	
	

	@Test
	public final void testGetDetalles() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		List<FormatoDetalle> detalles = new ArrayList<FormatoDetalle>();
		detalles.add(new FormatoDetalle("Condiciones","Beneficio no acumulable con otras promociones y/o descuentos.",1));
		 detalles.add(new FormatoDetalle("¿Qué es Mafe Pastelería?","Beneficio no acumulable con otras promociones y/o descuentos.",1));
		 detalles.add(new FormatoDetalle("¿Cómo accedo a este descuento?","Beneficio no acumulable con otras promociones y/o descuentos.",1));
		 
		
		List<FormatoDetalle> detallesNoesperado = new ArrayList<FormatoDetalle>();
		detallesNoesperado.add(new FormatoDetalle("Condiciones","Beneficio no acumulable con otras promociones y/o descuentos.",1));
		detallesNoesperado.add(new FormatoDetalle("¿Qué es Mafe Pastelería?","Beneficio no acumulable.",1));
		detallesNoesperado.add(new FormatoDetalle("¿Cómo accedo a este descuento?","Beneficio.",1));
		 

		when(esperado.getDetalles()).thenReturn(detalles);
		when(noesperado.getDetalles()).thenReturn(detallesNoesperado);
		assertTrue(productotipolike.equalsgetDetalles(esperado.getDetalles()));
		assertFalse(productotipolike.equalsgetDetalles(noesperado.getDetalles()));
		
		detalles = new ArrayList<FormatoDetalle>();
		detallesNoesperado = new ArrayList<FormatoDetalle>();
		
		when(esperado.getDetalles()).thenReturn(detalles);
		when(noesperado.getDetalles()).thenReturn(detallesNoesperado);
		
		assertFalse(productotipolike.equalsgetDetalles(esperado.getDetalles()));
		assertFalse(productotipolike.equalsgetDetalles(noesperado.getDetalles()));
	}

	
	
	
	
	
	
	
	
	
	@Test
	public final void testGetDirecciones() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		List<FormatoDetalle> direcciones = new ArrayList<FormatoDetalle>();
		direcciones.add(new FormatoDetalle("Dirección Web:", "http://google.com/MafePasteleria/", 2));
		direcciones.add(new FormatoDetalle("Dirección y Teléfono:", "Av Central, Región Metropolitana", 1));
		
		List<FormatoDetalle> direccionesNoesperado = new ArrayList<FormatoDetalle>();
		direccionesNoesperado.add(new FormatoDetalle("Dirección Web:","http://",1));
		direccionesNoesperado.add(new FormatoDetalle("Dirección:","Macul, Región Metropolitana",1));

		when(esperado.getDirecciones()).thenReturn(direcciones);
		when(noesperado.getDirecciones()).thenReturn(direccionesNoesperado);
		assertTrue(productotipolike.equalsDirecciones(esperado.getDirecciones()));
		assertFalse(productotipolike.equalsDirecciones(noesperado.getDirecciones()));
		
		direcciones = new ArrayList<FormatoDetalle>();
		direccionesNoesperado = new ArrayList<FormatoDetalle>();
		
		when(esperado.getDirecciones()).thenReturn(direcciones);
		when(noesperado.getDirecciones()).thenReturn(direccionesNoesperado);
		
		assertFalse(productotipolike.equalsDirecciones(esperado.getDirecciones()));
		assertFalse(productotipolike.equalsDirecciones(noesperado.getDirecciones()));
		
		
	}

	@Test
	public final void testGetCodigo() throws Exception {
		ProductoTipoLike esperado = mock(ProductoTipoLike.class);
		ProductoTipoLike noesperado = mock(ProductoTipoLike.class);

		when(esperado.getCodigo()).thenReturn("SD465465765465");
		when(noesperado.getCodigo()).thenReturn("00000000000000");
		assertEquals(productotipolike.getCodigo(), esperado.getCodigo());
		assertNotEquals(noesperado.getCodigo(), productotipolike.getCodigo());
	}

}
