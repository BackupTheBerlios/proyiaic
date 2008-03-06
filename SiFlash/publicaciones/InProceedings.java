//Source file: C:\\GENERADO\\publicaciones\\InProceedings.java

package publicaciones;

import java.util.Vector;


/**
 * Un artículo en las actas de sesiones (proceedings) de una conferencia
 */
public class InProceedings extends Publication 
{
	/**
	 * Vector que contiene los autores que han colaborado en la creación de la misma.
	 */
	private Vector author;
   
   /**
    * Libro al que hace referencia.
    */
   private String booktitle;
   
   /**
    * Año de publicación.
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
    * Paginas en las que está contenido.
    */
   private String pages;
   
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
    * @roseuid 47C8A712006D
    */
   public InProceedings() 
   {
    
   }
}
