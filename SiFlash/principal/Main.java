package principal;

import parserFicherosBibtex.ParserBibtex;

public class Main 
{

	public static void main(String[] args) 
	{
		ParserBibtex pb = new ParserBibtex();
		pb.procesar("D:\\prueba.txt");
	}
}
