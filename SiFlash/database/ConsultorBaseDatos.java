//Source file: C:\\GENERADO\\database\\ConsultorBaseDatos.java

package database;

import java.util.Vector;

import publicaciones.Publication;

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
   public ConsultorBaseDatos() 
   {
    
   }
   
   /**
    * Metodo que realiza una consulta sobre la base de datos.
    * Se le pasan por par�metro los campos sobre los que se puede realizar el 
    * filtrado.
    * @param tipo_publicaciones - Representa la AND l�gica a nivel de bits de los 
    * c�digos correspondientes a cada tipo de publicaci�n que deseamos consultar.
    * @param authors - Vector con un conjunto de Strings que deben estar contenidos 
    * entre los autores. null para no filtrar por este campo.
    * @param title - Titulo o parte del t�tulo de la publicaci�n sobre la que queremos 
    * realizar la b�squeda. null para no filtrar por este campo.
    * @param publisher - Editorial o parte de la editorial de la publicaci�n sobre la 
    * que queremos realizar la b�squeda. null para no filtrar por este campo.
    * @param journal - Journal o parte del mismo en que se incluye. null para no 
    * filtrar por este campo.
    * @param yearInicial - Primer a�o en que puede estar incluido. -1 Indica que no 
    * hay l�mite.
    * @param yearFinal - Ultimo a�o en el que puede estar incluida. -1 Indica que no 
    * hay l�mite.
    * @param monthInicial - Primer mes en el que se desea realizar la b�squeda. null 
    * para no filtrar por este campo.
    * @param monthFinal - Mes final en que se puede contener. null para no filtrar por 
    * este campo.
    * @param volume - Volumen o parte de este en que se incluye. null para no filtrar 
    * por este campo.
    * @param series - Serie o parte de su nombre en que se incluye. null para no 
    * filtrar por este campo.
    * @param address - Lugar en que se realiza o parte de su nombre. null para no 
    * filtrar por este campo.
    * @param pagesMin - Longitud m�nima en p�ginas. -1 para no filtrar por este campo.
    * @param pagesMax - Maximo de p�ginas. -1 para no filtrar por este campo.
    * @param organization - Organizacion o parte del nombre de la misma a la que 
    * pertenece. null para no filtrar por este par�metro
    * @param school - Escuela o parte del nombre de la misma en la que se ha 
    * realizado. null para no filtrar por este campo.
    * @param note - Vector con un conjunto de String que deber�n estar contenidos en 
    * el campo note de la publicaci�n. null para no filtrar por este campo.
    * @param abstracts - Vector con un conjunto de String que deber�n estar contenidos 
    * en el campo abstract de la publicaci�n. null para no filtrar por este campo.
    * @param bookTitle - T�tulo o parte del t�tulo del libro en el que se contiene la 
    * publicaci�n. null para no filtrar por este campo.
    * @return vector construido con las publicaciones que cumplen los 
    * requisitos@throws database.BDException
    * @roseuid 47C49FBE0280
    */
   public Vector<Publication> getPublicaciones(int tipo_publicaciones, Vector<Integer> authors, String title, String publisher, String journal, int yearInicial, int yearFinal, String monthInicial, String monthFinal, String volume, String series, String address, int pagesMin, int pagesMax, String organization, String school, Vector<String> note, Vector<String> abstracts, String bookTitle) throws BDException 
   {
    return null;
   }
   
   /**
    * Devuelve la publicaci�n que corresponde a un idDoc determinado.
    * 
    * Excepcion si no corresponde con ninguna publicacion.
    * @param id_doc - IdDoc para el que se desea buscar su correspondiente 
    * publicaci�n.
    * @return publicacion
    * @throws database.BDException
    * @roseuid 47C5961C0148
    */
   public Publication getPublicacionIddoc(int id_doc) throws BDException 
   {
    return null;
   }
}
