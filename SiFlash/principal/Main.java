package principal;

import parserFicherosBibtex.ParserBibtex;

public class Main 
{

	public static void main(String[] args) 
	{
		ParserBibtex pb = new ParserBibtex();
		pb.procesar("../SiFlash/parserFicherosBibtex/ficherosDePrueba/prueba.txt");
	}
}
