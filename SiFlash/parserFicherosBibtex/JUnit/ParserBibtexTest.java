package parserFicherosBibtex.JUnit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import junit.framework.TestCase;
import parserFicherosBibtex.ParserBibtex;
import personas.AutorEditor;
import publicaciones.Article;
import publicaciones.Book;

/**
 * Clase JUnit para probar la clase ParserBibtex
 */
public class ParserBibtexTest extends TestCase
{
	/**
	 * Prueba el fichero "prueba1.txt".
	 * @throws FileNotFoundException 
	 */
	public void testPrueba1() throws FileNotFoundException
	{
		ParserBibtex pb = new ParserBibtex();
		pb.procesar(new FileInputStream("parserFicherosBibtex\\JUnit\\ficherosDePrueba\\prueba1.txt"), "miXML.xml");

		boolean esLibro = pb.getUltDoc() instanceof Book;
		String autorEsperado = "Milton Abramowitz and Irene A. Stegun";
		String tituloEsperado = "Handbook of Mathematical Functions with Formulas, Graphs, and Mathematical Tables";
		String publisherEsperado = "Dover";
		String yearEsperado = "1964";
		String addressEsperado = "New York";
		String editionEsperado = "ninth Dover printing, tenth GPO printing";
		
		String autor = ((AutorEditor)((Book)pb.getUltDoc()).getAuthor().getFirst()).getNombre();
		String titulo = pb.getUltDoc().getTitle();
		String publisher = ((Book)pb.getUltDoc()).getPublisher();
		String year = ((Book)pb.getUltDoc()).getYear();
		String address = ((Book)pb.getUltDoc()).getAddress();
		String edition = ((Book)pb.getUltDoc()).getEdition();
		
		assertTrue(esLibro);
		assertEquals(autorEsperado, autor);
		assertEquals(tituloEsperado, titulo);
		assertEquals(publisherEsperado, publisher);
		assertEquals(yearEsperado, year);
		assertEquals(addressEsperado, address);
		assertEquals(editionEsperado, edition);
		
	}
	
	/**
	 * Prueba el fichero "prueba2.txt".
	 * @throws FileNotFoundException 
	 */
	public void testPrueba2() throws FileNotFoundException
	{
		ParserBibtex pb = new ParserBibtex();
		pb.procesar(new FileInputStream("parserFicherosBibtex\\JUnit\\ficherosDePrueba\\prueba2.txt"), "miXML.xml");
		

		boolean esArticulo = pb.getUltDoc() instanceof Article;
		String autorEsperado = "Alfonso";
		String tituloEsperado = "El título del artículo.";
		String monthEsperado = "april";
		
		String autor = ((AutorEditor)((Article)pb.getUltDoc()).getAuthor().getFirst()).getNombre();
		String titulo = pb.getUltDoc().getTitle();
		String month = ((Article)pb.getUltDoc()).getMonth();
		
		assertTrue(esArticulo);
		assertEquals(autorEsperado, autor);
		assertEquals(tituloEsperado, titulo);
		assertEquals(monthEsperado, month);
		
	}
}
