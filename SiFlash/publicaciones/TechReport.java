//Source file: C:\\GENERADO\\publicaciones\\TechReport.java

package publicaciones;

import java.util.Vector;


/**
 * Un reporte publicado por una escuela u otra instituci�n, usualmente numerado 
 * dentro de una serie.
 */
public class TechReport extends Publication 
{
	/**
	 * Vector que contiene los autores que han colaborado en la creaci�n de la misma.
	 */
	private Vector author;
   
   /**
    * Institucion que lo realiza.
    */
   private String institution;
   
   /**
    * A�o de publicaci�n.
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
    * Lugar de publicaci�n.
    */
   private String address;
   
   /**
    * Mes de publicaci�n.
    */
   private String month;
   
   /**
    * @roseuid 47C8A712034B
    */
   public TechReport() 
   {
    
   }
}
