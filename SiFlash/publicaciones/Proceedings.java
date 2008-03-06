//Source file: C:\\GENERADO\\publicaciones\\Proceedings.java

package publicaciones;

import java.util.Vector;


/**
 * Las actas de sesiones de una conferencia.
 */
public class Proceedings extends Publication 
{
   /**
    * Año de publicación.
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
    * Volumen en el que está contenido.
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
    * Lugar de publicación.
    */
   private String address;
   
   /**
    * Mes de publicación.
    */
   private String month;
   
   /**
    * Organización que se encarga de la gestión de la misma.
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
