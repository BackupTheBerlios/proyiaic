package controlador;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import publicaciones.CodigosDatos;

import database.BDException;
import database.BaseDatos;


public class ConsultorBaseDatosTest {
	String book,str1,str2,str3;
	Integer at1,at2,at3;
	ConsultorBaseDatos consultor;
	Vector <Integer> v_str1,v_str2,v_str3;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		book = new String("book");
		str1 = new String("str1");
		str2 = new String("str2");
		str3 = new String("str3");
		at1 = new Integer (1);
		at2 = new Integer (2);
		at3 = new Integer (3);
		consultor = new ConsultorBaseDatos(new BaseDatos());
		v_str1 = new Vector <Integer>();
		v_str2 = new Vector <Integer>();
		v_str3 = new Vector <Integer>();
		v_str1.add(at1);
		v_str2.add(at1);
		v_str2.add(at2);
		v_str3.add(at1);
		v_str3.add(at2);
		v_str3.add(at3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testConsultorBaseDatos() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetDatabase() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetPublicaciones() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetPublicacionIddoc() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreaConsulta() {
		
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreaConsultaAutores() {
		String cs1 = new String ("TRUE");
		String cs2 = new String ("book.idDoc IN (SELECT DISTINCT escrito_editado_por.idDoc FROM escrito_editado_por WHERE (escrito_editado_por.idPer IN (1) AND escrito_editado_por.escrito_o_editado = FALSE))");
		String cs3 = new String ("book.idDoc IN (SELECT DISTINCT escrito_editado_por.idDoc FROM escrito_editado_por WHERE (escrito_editado_por.idPer IN (1,2) AND escrito_editado_por.escrito_o_editado = TRUE))");
		String cs4 = new String ("book.idDoc IN (SELECT DISTINCT escrito_editado_por.idDoc FROM escrito_editado_por WHERE (escrito_editado_por.idPer IN (1,2,3) AND escrito_editado_por.escrito_o_editado = FALSE))");		
		
		String res1 = consultor.creaConsultaAutores(book, new Vector<Integer>(),CodigosDatos.codAutor);
		String res2 = consultor.creaConsultaAutores(book, v_str1, CodigosDatos.codEditor);
		String res3 = consultor.creaConsultaAutores(book, v_str2, CodigosDatos.codAutor);
		String res4 = consultor.creaConsultaAutores(book, v_str3, CodigosDatos.codEditor);
		
		assertEquals(cs1, res1);
		assertEquals(cs2, res2);
		assertEquals(cs3, res3);
		assertEquals(cs4, res4);
	}

	@Test
	public final void testCreaConsultaSimple() {
		assertEquals("TRUE", consultor.creaConsultaSimple(book, null, str3, true));
		assertEquals("TRUE", consultor.creaConsultaSimple(null, str2, str3, true));
		assertEquals("TRUE", consultor.creaConsultaSimple(book, str2, null, true));
		assertEquals("book.str2 = 'str3'", consultor.creaConsultaSimple(book, str2, str3, false));
		assertEquals("book.str2 LIKE ('%str3%')", consultor.creaConsultaSimple(book,str2,str3,true));
	}

	@Test
	public final void testBuscaAutores(){
		Vector <String> vector = new Vector<String>();
		vector.add(book);
		vector.add(str1);
		vector.add(str2);
		try {
			consultor.buscaAutores(vector);
		} catch (BDException e) {
			fail ("Fallo en la consulta de autores");
		}
		assertTrue(true);
	}
}
