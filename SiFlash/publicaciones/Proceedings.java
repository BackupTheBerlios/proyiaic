//Source file: C:\\GENERADO\\publicaciones\\Proceedings.java

package publicaciones;

import java.util.Vector;


/**
 * Las actas de sesiones de una conferencia.
 */
public class Proceedings extends Publication 
{
   /**
    * A�o de publicaci�n.
    */
   private int year;

   /**
    * Libro al que hace referencia.
    */
   private String booktitle;
   
   /**
	 * Vector que contiene a la/s persona/s que, sin ser autores propiamente dichos, ayudaron a editar la obra.
	 */
	private Vector editor;
	
   /**
    * Volumen en el que est� contenido.
    */
   private String volume;
   
   /**
    * Numero de volumen.
    */
   private int number;
   
   /**
    * Serie en la que se encuadra la publicacion.
    */
   private String series;
   
   /**
    * Lugar de publicaci�n.
    */
   private String address;
   
   /**
    * Mes de publicaci�n.
    */
   private String month;
   
   /**
    * Organizaci�n que se encarga de la gesti�n de la misma.
    */
   private String organization;
   
   /**
    * Representa a la entidad que publica.
    */
   private String publisher;
   
   /**
    * @roseuid 47C8A71202BF
    */
   public Proceedings() 
   {
    
   }
}
