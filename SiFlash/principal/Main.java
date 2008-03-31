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
			InputStream is = new FileInputStream("D:\\Rosa07.bib");
			//InputStream is = new FileInputStream("parserFicherosBibtex\\JUnit\\ficherosDePrueba\\total-ordenado.bib");
			//pb.procesar("parserFicherosBibtex\\JUnit\\ficherosDePrueba\\Rosa07.bib");
			pb.procesar(is);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
