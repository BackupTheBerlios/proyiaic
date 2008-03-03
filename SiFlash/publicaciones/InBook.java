//Source file: C:\\GENERADO\\publicaciones\\InBook.java

package publicaciones;


/**
 * Una parte de un libro, que puede ser un capítulo (o sección o lo que fuere) o un 
 * 
 * 
 * 
 * rango de páginas.
 */
public class InBook extends Publication 
{
   
   /**
    * Libro al que hace referencia.
    */
   private String book_tittle;
   
   /**
    * Capítulo en el que está contenido
    */
   private String chapter;
   
   /**
    * Paginas en las que está contenido.
    */
   private String pages;
   
   /**
    * Representa a la entidad que publica.
    */
   private String publisher;
   
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
    * Tipo del contenido.
    */
   private String type;
   
   /**
    * Edicion del mismo.
    */
   private String edition;
   
   /**
    * Lugar de publicación.
    */
   private String address;
   
   /**
    * Mes de publicación.
    */
   private String month;
   
   /**
    * @roseuid 47C8A711031C
    */
   public InBook() 
   {
    
   }
}
