//Source file: C:\\GENERADO\\publicaciones\\Conference.java

package publicaciones;

import java.util.Vector;


/**
 * Lo mismo que inproceedings, incluido para compatibilidad con el lenguaje de 
 * markup Scribe en:Scribe (markup language).
 */
public class Conference extends Publication 
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
    * Longitud en páginas.
    */
   private int pages;
   
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
    * @roseuid 47C8A7110280
    */
   public Conference() 
   {
    
   }
}
