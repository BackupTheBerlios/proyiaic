//Source file: C:\\GENERADO\\publicaciones\\Conference.java

package publicaciones;


/**
 * Lo mismo que inproceedings, incluido para compatibilidad con el lenguaje de 
 * markup Scribe en:Scribe (markup language).
 */
public class Conference extends Publication 
{
   
   /**
    * Libro al que hace referencia.
    */
   private String bookTittle;
   
   /**
    * Año de publicación.
    */
   private int year;
   
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
