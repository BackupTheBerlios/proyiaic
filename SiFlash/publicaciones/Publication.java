//Source file: C:\\GENERADO\\publicaciones\\Publication.java

package publicaciones;

import java.util.Vector;

/**
 * Clase abstracta que agrupa los dos atributos comunes a todos las "publicaciones" 
 * 
 * 
 * 
 * existentes, su idDoc y su título.
 * Nos servirá para realizar las operaciones genéricas sobre ellas.
 */
public abstract class Publication 
{
   
   /**
    * Valor que le corresponde al Id del documento
    */
   private int idDoc;
   
   /**
    * Título que le corresponde a la publicación.
    */
   private String title;
   
   /**
    * URL en el que se puede localizar la publicación.
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
    * Usuario que ha añadido la publicación al sistema.
    */
   private String user;
   
   /**
    * Contiene todos los proyectos a los que pertenece la aplicación.
    */
   private Vector proyectos;
   
   public PublicationException thePublicationException;
   
   /**
    * Devuelve el String correspondiente al código XML de la publicación 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C4463201C5
    */
   public String getXML() 
   {
    return null;
   }
   
   /**
    * Devuelve el String correspondiente al código HTML de la publicación 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C5AA3D006D
    */
   public String getHTML() 
   {
    return null;
   }
   
   /**
    * Devuelve el String correspondiente al código BibText de la publicación 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C5AA3F0213
    */
   public String getBibTeX() 
   {
    return null;
   }
}
