package com.appcms.entity;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductoTipoLikeTest {

	private ProductoTipoLike productotipolike;
	private ProductoTipoLike productotipolikeInt;

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
		// TODO
		throw new RuntimeException("not yet implemented");
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
	public final void testProductoTipoLike() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testProductoTipoLikeIntStringStringStringStringStringStringStringStringIntIntIntStringStringStringStringString()
			throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testProductoTipoLikeIntStringStringStringStringStringStringStringStringIntIntIntInt()
			throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testProductoTipoLikeIntStringStringStringStringStringStringStringStringInt() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testProductoTipoLikeIntStringStringStringStringStringStringStringStringIntInt() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testProductoTipoLikeIntStringStringStringString() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testProductoTipoLikeIntStringStringStringStringStringStringStringStringIntStringString()
			throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testProductoTipoLikeIntStringIntString() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
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
		formatosDetallesNoesperado.add(new FormatoDetalle("Condiciones", "Beneficio no acumulable con otras promociones y/o descuentos.", 1));
		formatosDetallesNoesperado.add(new FormatoDetalle("¿Qué es Mafe Pastelería?", "Beneficio no acumulable.", 1));
		formatosDetallesNoesperado.add(new FormatoDetalle("¿Cómo descuento?", "Beneficio no acumulable.", 1));

		when(esperado.getFormatosDetalles()).thenReturn(formatosDetalles);
		when(noesperado.getFormatosDetalles()).thenReturn(formatosDetallesNoesperado);

		assertTrue(equals(productotipolike.getFormatosDetalles(),esperado.getFormatosDetalles()));
		assertFalse(equals(productotipolike.getFormatosDetalles(),noesperado.getFormatosDetalles()));
	}
	
	
	private boolean equals(List<FormatoDetalle> f1,List<FormatoDetalle> f2) {
		ListIterator<FormatoDetalle> e1 = f2.listIterator();
		ListIterator<FormatoDetalle> e2 = f1.listIterator();
			while (e1.hasNext() && e2.hasNext()) {
				FormatoDetalle o1 = e1.next();
				FormatoDetalle o2 = e2.next();
			    if (!o1.getDetalle().equals(o2.getDetalle()) || !(o1.getTipo()==o2.getTipo()) || !o1.getTitulo().equals(o2.getTitulo()))
			    	 return false;
			  }
			  return true;
		}

	@Test
	public final void testGetDetalles() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testGetDirecciones() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testGetCodigo() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public final void testToString() throws Exception {
		// TODO
		throw new RuntimeException("not yet implemented");
	}

}
