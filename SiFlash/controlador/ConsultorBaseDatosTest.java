package controlador;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.BaseDatos;


public class ConsultorBaseDatosTest {
	String str1,str2,str3,at1,at2,at3;
	ConsultorBaseDatos consultor;
	Vector <String> v_str1,v_str2,v_str3;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		str1 = new String("str1");
		str2 = new String("str2");
		str3 = new String("str3");
		at1 = new String ("at1");
		at2 = new String ("at2");
		at3 = new String ("at3");
		consultor = new ConsultorBaseDatos(new BaseDatos());
		v_str1 = new Vector <String>();
		v_str2 = new Vector <String>();
		v_str3 = new Vector <String>();
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
		String cs2 = new String ("str1.idDoc IN (SELECT DISTINCT editadopor.idDoc FROM editadopor WHERE (editadopor.nombre LIKE ('%at1%') OR editadopor.apellidos LIKE ('%at1%')) UNION SELECT DISTINCT escritopor.idDoc FROM escritopor WHERE (escritopor.nombre LIKE ('%at1%') OR escritopor.apellidos LIKE ('%at1%')))");
		String cs3 = new String ("str1.idDoc IN (SELECT DISTINCT editadopor.idDoc FROM editadopor WHERE (editadopor.nombre LIKE ('%at1%') OR editadopor.apellidos LIKE ('%at1%')) UNION SELECT DISTINCT escritopor.idDoc FROM escritopor WHERE (escritopor.nombre LIKE ('%at1%') OR escritopor.apellidos LIKE ('%at1%'))) AND str1.idDoc IN (SELECT DISTINCT editadopor.idDoc FROM editadopor WHERE (editadopor.nombre LIKE ('%at2%') OR editadopor.apellidos LIKE ('%at2%')) UNION SELECT DISTINCT escritopor.idDoc FROM escritopor WHERE (escritopor.nombre LIKE ('%at2%') OR escritopor.apellidos LIKE ('%at2%')))");
		String cs4 = new String ("str1.idDoc IN (SELECT DISTINCT editadopor.idDoc FROM editadopor WHERE (editadopor.nombre LIKE ('at1') OR editadopor.apellidos LIKE ('at1')) UNION SELECT DISTINCT escritopor.idDoc FROM escritopor WHERE (escritopor.nombre LIKE ('at1') OR escritopor.apellidos LIKE ('at1'))) AND str1.idDoc IN (SELECT DISTINCT editadopor.idDoc FROM editadopor WHERE (editadopor.nombre LIKE ('at2') OR editadopor.apellidos LIKE ('at2')) UNION SELECT DISTINCT escritopor.idDoc FROM escritopor WHERE (escritopor.nombre LIKE ('at2') OR escritopor.apellidos LIKE ('at2'))) AND str1.idDoc IN (SELECT DISTINCT editadopor.idDoc FROM editadopor WHERE (editadopor.nombre LIKE ('at3') OR editadopor.apellidos LIKE ('at3')) UNION SELECT DISTINCT escritopor.idDoc FROM escritopor WHERE (escritopor.nombre LIKE ('at3') OR escritopor.apellidos LIKE ('at3')))");
		
		String res1 = consultor.creaConsultaAutores(str1, new Vector<String>(),true);
		String res2 = consultor.creaConsultaAutores(str1, v_str1, true);
		String res3 = consultor.creaConsultaAutores(str1, v_str2, true);
		String res4 = consultor.creaConsultaAutores(str1, v_str3, false);
		
		assertEquals(cs1, res1);
		assertEquals(cs2, res2);
		assertEquals(cs3, res3);
		assertEquals(cs4, res4);
	}

	@Test
	public final void testCreaConsultaSimple() {
		assertEquals("TRUE", consultor.creaConsultaSimple(str1, null, str3, true));
		assertEquals("TRUE", consultor.creaConsultaSimple(null, str2, str3, true));
		assertEquals("TRUE", consultor.creaConsultaSimple(str1, str2, null, true));
		assertEquals("str1.str2 = 'str3'", consultor.creaConsultaSimple(str1, str2, str3, false));
		assertEquals("str1.str2 LIKE ('%str3%')", consultor.creaConsultaSimple(str1,str2,str3,true));
	}

}
