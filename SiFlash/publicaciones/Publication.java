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
   private int idDoc;
   
   /**
    * T�tulo que le corresponde a la publicaci�n.
    */
   private String title;
   
   /**
    * URL en el que se puede localizar la publicaci�n.
    */
   private String URL = null;
   
   /**
    * Resumen de la publicacion.
    */
   private String _abstract;
   
   /**
    * Comentarios al respecto.
    */
   private String notes;
   
   /**
    * Usuario que ha a�adido la publicaci�n al sistema.
    */
   private String user;
   
   /**
    * Contiene todos los proyectos a los que pertenece la aplicaci�n.
    */
   private Vector proyectos;
   
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
