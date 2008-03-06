//Source file: C:\\GENERADO\\publicaciones\\BookLet.java

package publicaciones;

import java.util.Vector;


/**
 * Una obra que est� impresa y encuadernada (bound), pero sin una editorial o 
 * instituci�n patrocinadora (sponsoring).
 */
public class Booklet extends Publication 
{
	/**
	 * Vector que contiene los autores que han colaborado en la creaci�n de la misma.
	 */
	private Vector author;
	
   /**
    * Forma en la que ha sido publicado.
    */
   private String howPublished;
   
   /**
    * A�o de publicaci�n.
    */
   private int year;
   
   /**
    * Lugar de publicaci�n.
    */
   private String address;
   
   /**
    * Mes de publicaci�n.
    */
   private String month;
   
   /**
    * @roseuid 47C8A71101F4
    */
   public Booklet() 
   {
    
   }
}
