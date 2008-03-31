//Source file: C:\\GENERADO\\database\\ConsultorBaseDatos.java

package database;

import java.util.Vector;

import publicaciones.Article;
import publicaciones.Book;
import publicaciones.Booklet;
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




/**
 * Clase que se encarga de realizar las consultas necesarias sobre la base de 
 * datos.
 */
public class ConsultorBaseDatos 
{
   public BaseDatos theBaseDatos;
   
   /**
    * @roseuid 47C8AB160261
    */
   public ConsultorBaseDatos(BaseDatos base) 
   {
	   this.theBaseDatos=base;
   }
   
   /**
    * Metodo que realiza una consulta sobre la base de datos.
    * Se le pasan por parámetro los campos sobre los que se puede realizar el 
    * filtrado.
    * @param tipo_publicaciones - Representa la AND lógica a nivel de bits de los 
    * códigos correspondientes a cada tipo de publicación que deseamos consultar.
    * @param authors - Vector con un conjunto de Strings que deben estar contenidos 
    * entre los autores. null para no filtrar por este campo.
    * @param parecido_authors - Booleano que indica si vale con que el autor/editor sea parecido
    * a los proporcionados o debe ser igual.
    * @param title - Titulo o parte del título de la publicación sobre la que queremos 
    * realizar la búsqueda. null para no filtrar por este campo.
    * @param publisher - Editorial o parte de la editorial de la publicación sobre la 
    * que queremos realizar la búsqueda. null para no filtrar por este campo.
    * @param journal - Journal o parte del mismo en que se incluye. null para no 
    * filtrar por este campo.
    * @param yearInicial - Primer año en que puede estar incluido. -1 Indica que no 
    * hay límite.
    * @param yearFinal - Ultimo año en el que puede estar incluida. -1 Indica que no 
    * hay límite.
    * @param monthInicial - Primer mes en el que se desea realizar la búsqueda. null 
    * para no filtrar por este campo.
    * @param monthFinal - Mes final en que se puede contener. null para no filtrar por 
    * este campo.
    * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
    * por este campo.
    * @param series - Serie o parte de su nombre en que se incluye. null para no 
    * filtrar por este campo.
    * @param address - Lugar en que se realiza o parte de su nombre. null para no 
    * filtrar por este campo.
    * @param pagesMin - Longitud mínima en páginas. -1 para no filtrar por este campo.
    * @param pagesMax - Maximo de páginas. -1 para no filtrar por este campo.
    * @param organization - Organizacion o parte del nombre de la misma a la que 
    * pertenece. null para no filtrar por este parámetro
    * @param school - Escuela o parte del nombre de la misma en la que se ha 
    * realizado. null para no filtrar por este campo.
    * @param note - Vector con un conjunto de String que deberán estar contenidos en 
    * el campo note de la publicación. null para no filtrar por este campo.
    * @param abstracts - Vector con un conjunto de String que deberán estar contenidos 
    * en el campo abstract de la publicación. null para no filtrar por este campo.
    * @param bookTitle - Título o parte del título del libro en el que se contiene la 
    * publicación. null para no filtrar por este campo.
    * @return vector construido con las publicaciones que cumplen los 
    * requisitos@throws database.BDException
    * @roseuid 47C49FBE0280
    */
   public Vector<Publication> getPublicaciones(int tipo_publicaciones, Vector<Integer> authors, boolean parecidoauthors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle) throws BDException 
   {
    return null;
   }
   
   /**
    * Devuelve la publicación que corresponde a un idDoc determinado.
    * 
    * Excepcion si no corresponde con ninguna publicacion.
    * @param id_doc - IdDoc para el que se desea buscar su correspondiente 
    * publicación.
    * @return publicacion
    * @throws database.BDException
    * @roseuid 47C5961C0148
    */
   public Publication getPublicacionIddoc(int id_doc) throws BDException 
   {
    return null;
   }
   
   
   private Vector<Article> getArticles(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){
	   String consulta = "SELECT * FROM article";
	   
	   consulta += ";";
   }
   
   private Vector<Book> getBooks(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }
    
   private Vector<Booklet> getBooklets(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }
   
   private Vector<Conference> getConferences(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }
   
   private Vector<InBook> getInbooks(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }   
   
   private Vector<InCollection> getIncollection(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }
   
   private Vector<InProceedings> getInproceedings(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }
   
   private Vector<Manual> getManuals(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }   
   
   private Vector<MastersThesis> getMasterThesis(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }   
   
   private Vector<Misc> getMiscs(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }
   
   private Vector<PhdThesis> getPhdThesis(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }
   
   private Vector<Proceedings> getProceedings(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }   
   
   private Vector<TechReport> getTechReports(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }
   
   private Vector<Unpublished> getUnpublisheds(Vector<String> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle){	   
   }   
   
   /* Ejemplo de Consulta con varios parametros en Books
    * 
    * SELECT * FROM books WHERE( 
    * 	book.idDoc IN (
    * 		(
    * 			SELECT idDoc FROM editadoPor WHERE( 
    * 				nombre LIKE (%palabra1%) OR apellido APELLIDO (%palabra1%)
    * 			)
    * 			UNION
    * 			SELECT idDocFROM escritoPor WHERE(
    * 				nombre LIKE (%palabra1%) OR apellido APELLIDO (%palabra1%)
    * 			)
    * 		)
    * 	)
    * 	AND
    * 	book.idDoc IN (
    * 		(
    * 			SELECT idDoc FROM editadoPor WHERE( 
    * 				nombre LIKE (%palabra2%) OR apellido APELLIDO (%palabra2%)
    * 			)
    * 			UNION
    * 			SELECT idDocFROM escritoPor WHERE(
    * 				nombre LIKE (%palabra2%) OR apellido APELLIDO (%palabra2%)
    * 			)
    * 		)
    * 	)
    * 
    * 	------------------------------- Realizado el filtrado por autores/editores
    * 	AND
    * 	
    * 
    */   
   private String creaConsulta (Vector<String> authors, final boolean parecido_authors,
		   final Vector<String> title, final boolean parecido_title,
		   final String publisher, final String journal, 
		   final int yearInicial, final int yearFinal, final String monthInicial, final String monthFinal, final String volume, final String series, final String address, final int pagesMin, final int pagesMax, final String organization, final String school, final Vector<String> note, final Vector<String> abstracts, final String bookTitle){
	   String str = null;
	   boolean iniciado = false;
	   if (authors!= null && !authors.isEmpty()){
		   if (!iniciado){
			   iniciado = true;
			   str = new String (" WHERE( ");
		   }				   
		   str += creaConsultaAutores(authors,parecido_authors);		  
	   }
	   
	   if (title != null){
		   if (!iniciado){
			   iniciado = true;
			   str = new String (" WHERE( ");
		   }
	   }
	   
	   if (iniciado) str += ")"; 
	   return str;
   }


   /**
    * Método que genera el String con la parte de consulta relativa a los autores/editores
    * de la publicaciones.
    * La cadena generada debe tener el siguiente aspecto
    * 
    * 
    * 	book.idDoc IN (
    * 		(
    * 			SELECT idDoc FROM editadoPor WHERE( 
    * 				nombre LIKE (%palabra1%) OR apellido APELLIDO (%palabra1%)
    * 			)
    * 			UNION
    * 			SELECT idDocFROM escritoPor WHERE(
    * 				nombre LIKE (%palabra1%) OR apellido APELLIDO (%palabra1%)
    * 			)
    * 		)
    * 	)
    * 
    *  AND
    *  
    * 	book.idDoc IN (
    * 		(
    * 			SELECT idDoc FROM editadoPor WHERE( 
    * 				nombre LIKE (%palabra2%) OR apellido APELLIDO (%palabra2%)
    * 			)
    * 			UNION
    * 			SELECT idDocFROM escritoPor WHERE(
    * 				nombre LIKE (%palabra2%) OR apellido APELLIDO (%palabra2%)
    * 			)
    * 		)
    * 	)
    * 
    * 
    * @param authors Vector con todas las palabras que deben aparecer en autores o editores.
    * @param parecido Booleano 
    * @return String con la parte de la consulta que genera el método.
    */
   private String creaConsultaAutores(Vector<String> authors,boolean parecido){
	   String cons = new String();	  
	   for (int i=0; i< authors.size();i++){
		   String palabra;
		   if (parecido){
			   palabra = new String ("%");
			   palabra += authors.get(i);
			   palabra += ("%");
		   }
		   else palabra = authors.get(i);
		   // La palabra de busqueda de patron de autor/editor ya está buscada.
		   
		   if (i > 0) cons += " AND ";	
		   
		   cons += "book.idDoc IN (";
		   		 
		   cons += "SELECT idDoc FROM editadoPor WHERE( editadoPor.nombre LIKE ('" + palabra;
		   cons += "') OR editadoPor.apellido LIKE('" + palabra +"'))";
		   
		   cons += " UNION ";
		   
		   cons += "SELECT idDoc FROM escritoPor WHERE( escritoPor.nombre LIKE ('" + palabra; 
		   cons += "') OR escritoPor.apellido LIKE('" + palabra +"'))";
		   
		   cons += ")";
	   }
	   
	   return cons;
   }
   
   /**
    * Método que genera la parte de una consulta correspondiente a una consulta simple dados
    * unos parámetros.
    * 
    * Ejemplos de llamadas:<br>
    * 	Llamada --> creaConsultaSimple ("publisher","Pearson",true);<br>
    * 	Return	--> publisher LIKE ('%Pearson%')<br>
    * 	Llamada	--> creaConsultaSimple ("publisher","Pearson",false);<br>
    * 	Return	--> publisher = 'Pearson'<br>
    * 
    * 
    * @param campo_de_busqueda Campo por el que se desea filtrar
    * @param token Palabra que se desea hacer coincidir con el campo en el que se busca
    * @param parecido indica si deseamos que sea exactamente igual o contenida la palabra.
    * @return
    */
   private String creaConsultaSimple(final String campo_de_busqueda, 
		   final String token, final boolean parecido){	   		  	   
	   if (parecido) return new String (campo_de_busqueda + " = '" + token + "'");
	   else return new String (campo_de_busqueda + " LIKE ('%" + token + "%')");
	   
   }     
}
