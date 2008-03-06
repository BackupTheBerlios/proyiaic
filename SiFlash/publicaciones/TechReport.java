//Source file: C:\\GENERADO\\publicaciones\\TechReport.java

package publicaciones;

import java.util.Vector;


/**
 * Un reporte publicado por una escuela u otra institución, usualmente numerado 
 * dentro de una serie.
 */
public class TechReport extends Publication 
{
	/**
	 * Vector que contiene los autores que han colaborado en la creación de la misma.
	 */
	private Vector author;
   
   /**
    * Institucion que lo realiza.
    */
   private String institution;
   
   /**
    * Año de publicación.
    */
   private int year;
   
   /**
    * Tipo del contenido.
    */
   private String type;
   
   /**
    * Numero de volumen.
    */
   private int number;
   
   /**
    * Lugar de publicación.
    */
   private String address;
   
   /**
    * Mes de publicación.
    */
   private String month;
   
   /**
    * @roseuid 47C8A712034B
    */
   public TechReport() 
   {
    
   }
}
