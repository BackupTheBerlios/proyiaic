package controlador;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import personas.AutorEditor;
import publicaciones.Article;
import publicaciones.Book;
import publicaciones.Booklet;
import publicaciones.CodigosDatos;
import publicaciones.Conference;
import publicaciones.InBook;
import publicaciones.InCollection;
import publicaciones.InProceedings;
import publicaciones.Manual;
import publicaciones.MastersThesis;
import publicaciones.Misc;
import publicaciones.PhdThesis;
import publicaciones.Proceedings;
import publicaciones.Publication;
import publicaciones.TechReport;
import publicaciones.Unpublished;
import controlador.exceptions.ExistingElementException;
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

	//@Test
	public void testConsultaDocumentos() {
		Vector<Publication> v1 = new Vector<Publication>();
		Vector<String> keys = null; 
		keys = new Vector<String>();
		keys.add("key2");
		try {
			v1 = db_controler.consultaDocumentos(null, CodigosDatos.codArticle, null, null, "otro", true, null, "suplemento", null, null, null, null, null, null, null, keys, true, true, true, true, true, true, true, true, true, null);
		} catch (BDException e) {
			fail ("BDException");
		}
		if (v1.size()<1) fail ("ninguna publicacion");
		assertTrue(true);		
	}/**/

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
	public void testInsertaDocumento() throws ExistingElementException {		
		AutorEditor at1,at3,ed8;
		LinkedList<AutorEditor> autores,editores;
		Vector<String> proyectos = new Vector<String>();
		@SuppressWarnings("unused")
		Vector <String> keys1,keys2,keys3;
		keys1 = null;
		keys2 = new Vector<String>();
		keys2.add(new String(" "));
		keys3 = new Vector<String>();
		keys3.add("key832");
		keys3.add("key2");
		
		proyectos.add("pr1");
		proyectos.add("pr2");
		proyectos.add("prueba3");
		
		at1 = new AutorEditor(0,"str1","fdsa",null);
		at3 = new AutorEditor(0,"nombre",null,null);
		ed8 = new AutorEditor(0,null,"str1",null);
		
		autores = new LinkedList<AutorEditor>();
		editores = new LinkedList<AutorEditor>();
		autores.add(at1);
		autores.add(at3);
		editores.add(at1);
		editores.add(ed8);
		Random rnd = new Random (System.currentTimeMillis());
		int idDoc;
		
		
		// Insercion de articulo
		
		Article art1 = null;
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		art1 = new Article(idDoc,"referencia afirmativa","otro","1992","june","jsp://127.25.25.25","no resumindo","corto note",keys3,"user1",proyectos,autores,"suplemento","32 volumenes","no hay numero","43 paginas");
		try {
			db_controler.insertaDocumento(art1);
		} catch (BDException e) {
			fail ("fallo en la inserción del articulo: " + art1.getIdDoc());
		}
		assertTrue(true);

		// Insercion de book

		Book book1 = null;
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		book1 = new Book(idDoc,"bkk4156","titulo book","2003","4",null,null,null,keys2,"user1",proyectos,autores,editores,"publisher book","465","1",null,"madrid_madrid","5");

		try {
			db_controler.insertaDocumento(book1);
		} catch (BDException e) {
			fail ("fallo en la inserción del book: " + idDoc);
		}
		assertTrue(true);
		/**/

		// Insercion de booklet
		Booklet bkl1 = null;
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		bkl1 = new Booklet (idDoc,"bkl1254","booklet1","2008","junio","1546.156.156.1","abstract",null,null,"user1",proyectos,autores,"direccion145","s/c");

		try {
			db_controler.insertaDocumento(bkl1);
		} catch (BDException e) {
			fail ("fallo en la inserción del booklet: " + idDoc);
		}
		assertTrue(true);	
		/**/	
		// Insercion de conference
		Conference conf1 = null;		
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		conf1 = new Conference (idDoc,"referencia","conferencia48","1990",null,"ftp:/145.0.0.0","resumen prueba",null,new Vector<String>(),"user1",proyectos,autores,"booktitle","crg",editores,null,null,"seri45e1",null,null,null,null);
		try {
			db_controler.insertaDocumento(conf1);
		} catch (BDException e) {
			fail ("fallo en la inserción del conference: " + idDoc);
		}
		assertTrue(true);		/**/

		// Insercion de Inbook
		InBook inbk1 = null;
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		inbk1 = new InBook (idDoc,"INBK485","inbook fds","2004",null,null,null,null,null,"user1",proyectos,autores,"hui","tipi",editores,"789","976",null,null,"dsazfas","capitulo 12","yo");

		try {
			db_controler.insertaDocumento(inbk1);
		} catch (BDException e) {
			fail ("fallo en la inserción del inbook: " + idDoc);
		}
		assertTrue(true);/**/

		// Insercion de incollection
		InCollection inclc1 = null;
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		inclc1 = new InCollection (idDoc,"ICLC781","titulo de incollection","2003","456","7789","4865132651","11198",keys3,"user1",proyectos,autores,"booktitle conference",null,editores,"4","489","serie virtual","74","Gran Via 4","tipo standard","AMD publisher","Chp 5","7 edition");

		try {
			db_controler.insertaDocumento(inclc1);
		} catch (BDException e) {
			fail ("fallo en la inserción del incollection: " + idDoc);
		}
		assertTrue(true);		/**/


		// Insercion de inproceedings
		InProceedings inp1 = null;		
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		inp1 = new InProceedings (idDoc,"referencia","titulo48","1990",null,"ftp:/145.0.0.0","resumen prueba",null,new Vector<String>(),"user1",proyectos,autores,"booktitle","crg",editores,null,null,"serie1",null,null,null,null);
		try {
			db_controler.insertaDocumento(inp1);
		} catch (BDException e) {
			fail ("fallo en la inserción del inproceeding: " + idDoc);
		}
		assertTrue(true);		/**/	

		// Insercion de manual
		Manual man1 = null;		
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		man1 = new Manual(idDoc,null,"manual 1",null,null,null,null,null,null,"user1",proyectos,autores,null,null,null);
		try {
			db_controler.insertaDocumento(man1);

		} catch (BDException e) {
			fail ("fallo en la inserción del manual: " + idDoc);
		}
		assertTrue(true);	/**/


		// Insercion de masterthesis
		MastersThesis mas1 = null;		
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		mas1 = new MastersThesis (idDoc,"mas1891","masterthesis de pueba","2043","10","154.155.90.36","reshnuids nfdsanfj sdanfjoasdfopajsdfasdnfasnf nas","nota",keys2,"user1",proyectos,autores,null,"school fsadf",null);
		try {
			db_controler.insertaDocumento(mas1);

		} catch (BDException e) {
			fail ("fallo en la inserción del mastersthesis: " + idDoc);
		}
		assertTrue(true);		/**/	

		// Insercion de misc
		Misc misc1 = null;		
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		misc1 = new Misc (idDoc,"misc156","misc1","1999","3","localhost","misc de prueba para inserciones",null,null,"user1",proyectos,autores,"sin publicar");
		try {
			db_controler.insertaDocumento(misc1);

		} catch (BDException e) {
			fail ("fallo en la inserción del misc1: " + idDoc);
		}
		assertTrue(true);	/**/


		// Insercion de phdthesis
		PhdThesis phd1 = null;		
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		phd1 = new PhdThesis(idDoc,"refphd1","titulo phd","0984","marzo",null,null,null,keys2,"user1",proyectos,autores,"Castellana 145","De negocios","Instrucciones");
		try {
			db_controler.insertaDocumento(phd1);

		} catch (BDException e) {
			fail ("fallo en la inserción del phdThesis: " + idDoc);
		}
		assertTrue(true);	/**/



		// Insercion de Proceedings
		Proceedings prc1 = null;		
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		prc1 = new Proceedings(idDoc,null,"ds proceedings","15","4","45.1.1.1","resumen del proceeding","note de proceeding",keys3,"user1",proyectos,"libro que contiene el proceeding",editores,"volumen 3","0","serie tecnica","Bravo Murillo Proceedings","Proceedings creation organization","Publicante de Proceedings");
		try {
			db_controler.insertaDocumento(prc1);

		} catch (BDException e) {
			fail ("fallo en la inserción del proceedings: " + idDoc);
		}
		assertTrue(true);	/**/



		// Insercion de TechReport
		TechReport tec1 = null;		
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		tec1 = new TechReport(idDoc,"TEC15261","tech ratones","2008","abril 28","157.12.2.3","tech sobre ratones prueba insercion","note tech",keys3,"user1",proyectos,autores,"seneca 8","Elio","CMU techs","60");
		try {
			db_controler.insertaDocumento(tec1);

		} catch (BDException e) {
			fail ("fallo en la inserción del tech: " + idDoc);
		}
		assertTrue(true);	/**/


		// Insercion de Unpublished
		Unpublished unp1 = null;		
		idDoc = rnd.nextInt()%100000;
		if (idDoc < 0) idDoc = idDoc * (-1);
		unp1 = new Unpublished(idDoc,"Unp156","Unpublished no publicado","2009","01","127.0.0.1","resumen no publicado","note sin publicar",null,"user1",proyectos,autores);
		try {
			db_controler.insertaDocumento(unp1);

		} catch (BDException e) {
			fail ("fallo en la inserción del man1: " + idDoc);
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

