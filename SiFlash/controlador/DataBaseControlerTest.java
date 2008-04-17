package controlador;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import personas.AutorEditor;
import publicaciones.CodigosDatos;
import publicaciones.InProceedings;
import temporal.UnimplementedException;

import database.BDException;
import database.BaseDatos;

import java.util.Random;

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
			db_controler.consultaDocumentos(CodigosDatos.codInproceedings, null, null, null, true, null, null, null, null, null, null, null, null, null, null, true, true, true, true, true, true, true, true, true);
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
		AutorEditor at1,at3,ed8;
		LinkedList<AutorEditor> autores,editores;
		Vector<String> proyectos = new Vector<String>();
		proyectos.add("pr1");
		proyectos.add("pr2");
		proyectos.add("prueba3");
		at1 = new AutorEditor(1,"str1","fdsa",null);
		at3 = new AutorEditor(3,"nombre",null,null);
		ed8 = new AutorEditor(8,null,"str1",null);
		autores = new LinkedList<AutorEditor>();
		editores = new LinkedList<AutorEditor>();
		autores.add(at1);
		autores.add(at3);
		editores.add(at1);
		editores.add(ed8);
		InProceedings inp1 = null;
		Random rnd = new Random (System.currentTimeMillis());
		int idDoc = rnd.nextInt()%10000;
		try {
			inp1 = new InProceedings (idDoc,"referencia","titulo48","1990",null,"ftp:/145.0.0.0","resumen prueba",null,new Vector<String>(),"user1",proyectos,autores,"booktitle","crg",editores,null,null,"serie1",null,null,null,null);
		} catch (UnimplementedException e) {
			fail ("fallo en la creación del documento");
		}
		try {
			db_controler.insertaDocumento(inp1);
		} catch (BDException e) {
			fail ("fallo en la inserción del documento: " + idDoc);
		}
		assertTrue(true);
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

