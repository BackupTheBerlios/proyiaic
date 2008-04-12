package principal;

import java.io.FileInputStream;
import java.io.InputStream;

import parserFicherosBibtex.ParserBibtex;

public class Main 
{

	public static void main(String[] args) 
	{
		try
		{
			ParserBibtex pb = new ParserBibtex();
			//InputStream is = new FileInputStream("D:\\Rosa07.bib");
			InputStream is = new FileInputStream("parserFicherosBibtex\\JUnit\\ficherosDePrueba\\total-ordenado.bib");
			//pb.procesar("parserFicherosBibtex\\JUnit\\ficherosDePrueba\\Rosa07.bib");
			pb.procesar(is, "miXML.xml");
			
			/*ConversorBusquedas cb = new ConversorBusquedas();
			InputStream is = new FileInputStream("D:\\datos.xml");
			cb.procesar(is);*/
			
			/*CampoPublicacion c1 = new CampoPublicacion("title", "Hola Mundo", false);
			CampoPublicacion c2 = new CampoPublicacion("year", "2001", false);
			CampoPublicacion c3 = new CampoPublicacion("month", "may", false);
			CampoPublicacion c4 = new CampoPublicacion("volume", "tercero", false);
			CampoPublicacion c5 = new CampoPublicacion("author", "{Cruces Orb\\\'{\\i}s} and David García and Ortiz Carrillo, Luis", false);
			CampoPublicacion c6 = new CampoPublicacion("number", "54", true);
			
			LinkedList<CampoPublicacion> listaCampos = new LinkedList<CampoPublicacion>();
			listaCampos.add(c1);
			listaCampos.add(c2);
			listaCampos.add(c3);
			listaCampos.add(c4);
			listaCampos.add(c5);
			listaCampos.add(c6);
			
			Article art = new Article(listaCampos);
			
			System.out.println(art.getBibTeX());*/
			
			/*CampoPublicacion c1 = new CampoPublicacion("title", "Hola, me llamo Orb{\\\'{\\i}}s", false);
			c1.sustituirTildes();
			System.out.println(c1.getValor());*/
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
