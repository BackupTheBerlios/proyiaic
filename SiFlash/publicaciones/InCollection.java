//Source file: C:\\GENERADO\\publicaciones\\InCollection.java

package publicaciones;

import java.util.Vector;


/**
 * Una parte de un libro que tiene su propio t�tulo.
 */
public class InCollection extends Publication 
{
	/**
	 * Vector que contiene los autores que han colaborado en la creaci�n de la misma.
	 */
	private Vector author;
   
   /**
    * Libro al que hace referencia.
    */
   private String booktitle;
   
   /**
    * Representa a la entidad que publica.
    */
   private String publisher;
   
   /**
    * A�o de publicaci�n.
    */
   private int year;

   /**
    * Ayuda a generar referecias cruzadas internas.
    */
   private String crossref;
   
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
    * Tipo del contenido.
    */
   private String type;
   
   /**
    * Cap�tulo en el que est� contenido
    */
   private String chapter;
   
   /**
    * Paginas en las que est� contenido.
    */
   private String pages;
   
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
    * @roseuid 47C8A71103B9
    */
   public InCollection() 
   {
    
   }
}
