package principal;

import java.io.FileInputStream;
import java.util.LinkedList;

import controlador.ConversorXML_BBDD;

import parserFicherosBibtex.Campo;
import parserFicherosBibtex.CampoPublicacion;
import publicaciones.Article;




public class Main 
{

	public static void main(String[] args) 
	{
		try
		{
			//-------------------------------------------------------------------------
//			ParserBibtex pb = new ParserBibtex();
//			InputStream is = new FileInputStream("ficherosDePrueba\\parserBibtex\\prueba.bib");
//			pb.procesar(is, "miXML.xml");
			
			//-------------------------------------------------------------------------
//			ConversorXML_BBDD cb = new ConversorXML_BBDD();
//			InputStream is = new FileInputStream("ficherosDePrueba\\consultas\\consulta1.xml");
//			String salida = cb.procesarConsulta(is);
//			System.out.println(salida);

			//-------------------------------------------------------------------------
//			CampoPublicacion c1 = new CampoPublicacion("title", "Hola Mundo", false);
//			CampoPublicacion c2 = new CampoPublicacion("year", "2001", false);
//			CampoPublicacion c3 = new CampoPublicacion("month", "may", false);
//			CampoPublicacion c4 = new CampoPublicacion("volume", "tercero", false);
//			CampoPublicacion c5 = new CampoPublicacion("author", "{Cruces Orb\\\'{\\i}s} and David Garc\\\'ia and Ortiz Carrillo, Luis", false);
//			CampoPublicacion c6 = new CampoPublicacion("abstract", "54", true);
//			CampoPublicacion c7 = new CampoPublicacion("key", "clave1  , clave2 , 	clave3", true);
//			
//			LinkedList<Campo> listaCampos = new LinkedList<Campo>();
//			listaCampos.add(c1);
//			listaCampos.add(c2);
//			listaCampos.add(c3);
//			listaCampos.add(c4);
//			listaCampos.add(c5);
//			listaCampos.add(c6);
//			listaCampos.add(c7);
//			
//			Article art = new Article(listaCampos);
//			
//			System.out.println(art.generarElementoXML(false));

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
//			
//			InputStream is = new FileInputStream(".\\ficherosDePrueba\\inserciones\\publicaciones\\inproceedings.xml");
//			ConversorXML_BBDD conv = new ConversorXML_BBDD();
//			System.out.println(conv.procesarInsercion(is));
			
			//-------------------------------------------------------------------------
			
//			DataBaseControler db_controler = new DataBaseControler();
////			System.out.println(db_controler.obtenerListaAutoresEditoresYProyectosParaInserciones("user1"));
//			System.out.println(db_controler.verificaUsuario("user3", "user3"));
			
			//-------------------------------------------------------------------------
			
//			ConversorXML_BBDD conv = new ConversorXML_BBDD();
//			System.out.println(conv.procesarNuevoUsuario(new FileInputStream(".\\ficherosDePrueba\\inserciones\\usuario.xml")));
		
			//-------------------------------------------------------------------------
		
//			ConversorXML_BBDD conv = new ConversorXML_BBDD();
//			System.out.println(conv.procesarEliminarUsuario(new FileInputStream(".\\ficherosDePrueba\\eliminaciones\\eliminaUsuario.xml")));
			//-------------------------------------------------------------------------
			
//			ConversorXML_BBDD conv = new ConversorXML_BBDD();
//			System.out.println(conv.procesarDesasociarUsuarioAProyecto(new FileInputStream(".\\ficherosDePrueba\\inserciones\\asociarUsuarioProyecto.xml")));
			//-------------------------------------------------------------------------
			
//			ConversorXML_BBDD conv = new ConversorXML_BBDD();
//			System.out.println(conv.procesarDesasociarPublicacionAProyecto(new FileInputStream(".\\ficherosDePrueba\\inserciones\\asociarPublicacionProyecto.xml")));
			//-------------------------------------------------------------------------
			
//			DataBaseControler db_controler = new DataBaseControler();
//			System.out.println(db_controler.obtenerListaAutoresEditoresYProyectosParaBusquedas());
			//-------------------------------------------------------------------------
			
//			DataBaseControler db_controler = new DataBaseControler();
//			System.out.println(db_controler.obtenerListaProyectosUsuariosYAutores());
//			//-------------------------------------------------------------------
			
			ConversorXML_BBDD conv = new ConversorXML_BBDD();
			System.out.println(conv.procesarModificarAutor(new FileInputStream(".\\ficherosDePrueba\\modificaciones\\autor.xml")));
			//-------------------------------------------------------------------
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
