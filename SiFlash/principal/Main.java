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
			//-------------------------------------------------------------------------
			ParserBibtex pb = new ParserBibtex();
			InputStream is = new FileInputStream("D:\\pruebaKeys.bib");
			//InputStream is = new FileInputStream("parserFicherosBibtex\\JUnit\\ficherosDePrueba\\total-ordenado.bib");
			//pb.procesar("parserFicherosBibtex\\JUnit\\ficherosDePrueba\\Rosa07.bib");
			pb.procesar(is, "miXML.xml");
			
			//-------------------------------------------------------------------------
			/*ConversorXML_BBDD cb = new ConversorXML_BBDD();
			InputStream is = new FileInputStream("D:\\datos.xml");
			String salida = cb.procesarConsulta(is);
			System.out.println(salida);*/

			//-------------------------------------------------------------------------
			/*CampoPublicacion c1 = new CampoPublicacion("title", "Hola Mundo", false);
			CampoPublicacion c2 = new CampoPublicacion("year", "2001", false);
			CampoPublicacion c3 = new CampoPublicacion("month", "may", false);
			CampoPublicacion c4 = new CampoPublicacion("volume", "tercero", false);
			CampoPublicacion c5 = new CampoPublicacion("author", "{Cruces Orb\\\'{\\i}s} and David García and Ortiz Carrillo, Luis", false);
			CampoPublicacion c6 = new CampoPublicacion("abstract", "54", true);
			
			LinkedList<CampoPublicacion> listaCampos = new LinkedList<CampoPublicacion>();
			listaCampos.add(c1);
			listaCampos.add(c2);
			listaCampos.add(c3);
			listaCampos.add(c4);
			listaCampos.add(c5);
			listaCampos.add(c6);
			
			Conference conf = new Conference(listaCampos);
			
			System.out.println(conf.getBibTeX());*/

			//-------------------------------------------------------------------------
			/*CampoPublicacion c1 = new CampoPublicacion("title", "Hola, me llamo Orb{\\\'{\\i}}s", false);
			c1.sustituirTildes();
			System.out.println(c1.getValor());*/

			//-------------------------------------------------------------------------
			/*DataBaseControler db_controler = new DataBaseControler(new BaseDatos());
			Vector<Publication> vector = db_controler.consultaDocumentos(CodigosDatos.codInproceedings, null, null, null, true, null, null, 1990, 1990, null, null, null, null, null, -1, -1, null, null, null, null, null, true, true, true, true, true, true, true, true);

			int numPublic = vector.size();
			for (int i = 0; i < numPublic; i++)
				System.out.println(vector.get(i).getTitle());*/

			//-------------------------------------------------------------------------
			/*ConversorXML_Publication cp = new ConversorXML_Publication();
			InputStream is = new FileInputStream("D:\\pruebasConversorXML_Publication\\unpublished.xml");
			Publication p = cp.convertir(is);
			System.out.println(p.getBibTeX());*/
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
