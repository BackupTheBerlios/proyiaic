//Source file: C:\\GENERADO\\publicaciones\\InBook.java

package publicaciones;

import java.util.Vector;


/**
 * Una parte de un libro, que puede ser un cap�tulo (o secci�n o lo que fuere) o un 
 * 
 * 
 * 
 * rango de p�ginas.
 */
public class InBook extends Publication 
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
    * Libro al que hace referencia.
    */
   private String booktitle;
   
   /**
    * Cap�tulo en el que est� contenido
    */
   private String chapter;
   
   /**
    * Paginas en las que est� contenido.
    */
   private String pages;
   
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
    * Tipo del contenido.
    */
   private String type;
   
   /**
    * Edicion del mismo.
    */
   private String edition;
   
   /**
    * Lugar de publicaci�n.
    */
   private String address;
   
   /**
    * Mes de publicaci�n.
    */
   private String month;
   
   /**
    * @roseuid 47C8A711031C
    */
   public InBook() 
   {
    
   }
}
