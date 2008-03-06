//Source file: C:\\GENERADO\\publicaciones\\BookLet.java

package publicaciones;

import java.util.Vector;


/**
 * Una obra que está impresa y encuadernada (bound), pero sin una editorial o 
 * institución patrocinadora (sponsoring).
 */
public class Booklet extends Publication 
{
	/**
	 * Vector que contiene los autores que han colaborado en la creación de la misma.
	 */
	private Vector author;
	
   /**
    * Forma en la que ha sido publicado.
    */
   private String howPublished;
   
   /**
    * Año de publicación.
    */
   private int year;
   
   /**
    * Lugar de publicación.
    */
   private String address;
   
   /**
    * Mes de publicación.
    */
   private String month;
   
   /**
    * @roseuid 47C8A71101F4
    */
   public Booklet() 
   {
    
   }
}
