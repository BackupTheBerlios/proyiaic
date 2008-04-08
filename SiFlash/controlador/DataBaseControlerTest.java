package controlador;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import publicaciones.CodigosDatos;
import temporal.UnimplementedException;

import database.BDException;
import database.BaseDatos;

public class DataBaseControlerTest {
	private DataBaseControler db_controler;
	@Before
	public void setUp() throws Exception {
		db_controler = new DataBaseControler(new BaseDatos());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConsultaDocumentos() {
		Vector <String> v1 = new Vector<String>();
		v1.add("str1");
		try {
			db_controler.consultaDocumentos(CodigosDatos.codMisc, v1, CodigosDatos.escritoOEditado, null, true, null, null, -1, -1, null, null, null, null, null, -1, -1, null, null, null, null, null, true, true, true, true, true, true, true, true);
		} catch (BDException e) {
			fail ("BDException");
		} catch (UnimplementedException e) {
			// TODO Auto-generated catch block
			fail ("UnimplmentedException");
		}
		assertTrue(true);		
	}

	@Test
	public void testConsultaAutores() {
		fail("Not yet implemented");
	}

	@Test
	public void testConsultaDocumentosProyecto() {
		fail("Not yet implemented");
	}

	@Test
	public void testConsultaUsuariosProyecto() {
		fail("Not yet implemented");
	}

	@Test
	public void testConsultaProyectosUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyProyects() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyDocuments() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyUploadDocuments() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertaDocumento() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertaUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testModificaDocumento() {
		fail("Not yet implemented");
	}

	@Test
	public void testModificaAutor() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminaDocumento() {
		fail("Not yet implemented");
	}

	@Test
	public void testAsociaUsuarioProyecto() {
		fail("Not yet implemented");
	}

	@Test
	public void testDesasociaUsuarioProyecto() {
		fail("Not yet implemented");
	}

	@Test
	public void testDesasociaDocumentoProyecto() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminaUsuario() {
		fail("Not yet implemented");
	}

}
