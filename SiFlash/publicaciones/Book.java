//Source file: C:\\GENERADO\\publicaciones\\Book.java

package publicaciones;

import java.util.Vector;


/**
 * Clase que representa un libro
 */
public class Book extends Publication 
{
	/**
	 * Vector que contiene los autores que han colaborado en la creaci�n de la misma.
	 */
	private Vector author;
	
	/**
	 * Vector que contiene a la/s persona/s que, sin ser autores propiamente dichos, ayudaron a editar la obra.
	 */
	private Vector editor;
	
   /**
    * Representa a la entidad que publica.
    */
   private String publisher;
   
   /**
    * A�o de publicaci�n.
    */
   private int year;
   
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
    * Edicion del mismo.
    */
   private String edition;
   
   /**
    * Mes de publicaci�n.
    */
   private String month;
   
   /**
    * @roseuid 47C8A7110177
    */
   public Book() 
   {
    
   }
}
