//Source file: C:\\GENERADO\\publicaciones\\Publication.java

package publicaciones;

import java.util.Vector;

/**
 * Clase abstracta que agrupa los dos atributos comunes a todos las "publicaciones" 
 * 
 * 
 * 
 * existentes, su idDoc y su t�tulo.
 * Nos servir� para realizar las operaciones gen�ricas sobre ellas.
 */
public abstract class Publication 
{
   
   /**
    * Valor que le corresponde al Id del documento
    */
	protected int idDoc;
   
   /**
    * T�tulo que le corresponde a la publicaci�n.
    */
   protected String title;
   
   /**
    * URL en el que se puede localizar la publicaci�n.
    */
   protected String URL = null;
   
   /**
    * Resumen de la publicacion.
    */
   protected String _abstract;
   
   /**
    * Comentarios al respecto.
    */
   protected String note;
   
   /**
    * Clave/s de la publicaci�n.
    */
   protected String key;
   
   /**
    * Usuario que ha a�adido la publicaci�n al sistema.
    */
   protected String user;
   
   /**
    * Contiene todos los proyectos a los que pertenece la aplicaci�n.
    */
   protected Vector proyectos;
   
   public PublicationException thePublicationException;
   
   /**
    * Devuelve el String correspondiente al c�digo XML de la publicaci�n 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C4463201C5
    */
   public String getXML() 
   {
    return null;
   }
   
   /**
    * Devuelve el String correspondiente al c�digo HTML de la publicaci�n 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C5AA3D006D
    */
   public String getHTML() 
   {
    return null;
   }
   
   /**
    * Devuelve el String correspondiente al c�digo BibText de la publicaci�n 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C5AA3F0213
    */
   public String getBibTeX() 
   {
    return null;
   }
}
