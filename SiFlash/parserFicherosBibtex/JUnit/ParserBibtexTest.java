package parserFicherosBibtex.JUnit;

import junit.framework.TestCase;
import parserFicherosBibtex.ParserBibtex;
import personas.Autor;
import publicaciones.Article;
import publicaciones.Book;

/**
 * Clase JUnit para probar la clase ParserBibtex
 */

public class ParserBibtexTest extends TestCase
{
	public void testPrueba1()
	{
		ParserBibtex pb = new ParserBibtex();
		pb.procesar("D:\\prueba.txt");

		boolean esLibro = pb.getUltDoc() instanceof Book;
		String autorEsperado = "Milton Abramowitz and Irene A. Stegun";
		String tituloEsperado = "Handbook of Mathematical Functions with Formulas, Graphs, and Mathematical Tables";
		String publisherEsperado = "Dover";
		int yearEsperado = 1964;
		String addressEsperado = "New York";
		String editionEsperado = "ninth Dover printing, tenth GPO printing";
		String keyEsperado = "abramowitz+stegun";
		
		String autor = ((Autor)((Book)pb.getUltDoc()).getAuthor().firstElement()).getNombre();
		String titulo = pb.getUltDoc().getTitle();
		String publisher = ((Book)pb.getUltDoc()).getPublisher();
		int year = ((Book)pb.getUltDoc()).getYear();
		String address = ((Book)pb.getUltDoc()).getAddress();
		String edition = ((Book)pb.getUltDoc()).getEdition();
		String key = pb.getUltDoc().getKey();
		
		assertTrue(esLibro);
		assertEquals(autorEsperado, autor);
		assertEquals(tituloEsperado, titulo);
		assertEquals(publisherEsperado, publisher);
		assertEquals(yearEsperado, year);
		assertEquals(addressEsperado, address);
		assertEquals(editionEsperado, edition);
		assertEquals(keyEsperado, key);
		
	}
	
	public void testPrueba2()
	{
		ParserBibtex pb = new ParserBibtex();
		pb.procesar("D:\\prueba2.txt");

		boolean esArticulo = pb.getUltDoc() instanceof Article;
		String autorEsperado = "Alfonsete";
		String tituloEsperado = "Mi vida {\"es\"} una full";
		String monthEsperado = "april";
		String keyEsperado = "perico+palotes";
		
		String autor = ((Autor)((Article)pb.getUltDoc()).getAuthor().firstElement()).getNombre();
		String titulo = pb.getUltDoc().getTitle();
		String month = ((Article)pb.getUltDoc()).getMonth();
		String key = pb.getUltDoc().getKey();
		
		assertTrue(esArticulo);
		assertEquals(autorEsperado, autor);
		assertEquals(tituloEsperado, titulo);
		assertEquals(monthEsperado, month);
		assertEquals(keyEsperado, key);
		
	}
}
