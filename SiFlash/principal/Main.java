package principal;

import parserFicherosBibtex.ParserBibtex;

public class Main 
{

	public static void main(String[] args) 
	{
		ParserBibtex pb = new ParserBibtex();
		pb.procesar("parserFicherosBibtex\\JUnit\\ficherosDePrueba\\Rosa07.bib");
	}
}
