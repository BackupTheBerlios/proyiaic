//Source file: C:\\GENERADO\\publicaciones\\Publication.java

package publicaciones;

import java.util.Vector;

import personas.Autor;
import personas.Editor;

/**
 * Clase abstracta que agrupa los dos atributos comunes a todos las "publicaciones" 
 * 
 * 
 * 
 * existentes, su idDoc y su título.
 * Nos servirá para realizar las operaciones genéricas sobre ellas.
 */
public abstract class Publication 
{
   
   /**
    * Valor que le corresponde al Id del documento
    */
	protected int idDoc;
   
   /**
    * Título que le corresponde a la publicación.
    */
   protected String title;
   
   /**
    * URL en el que se puede localizar la publicación.
    */
   protected String URL = null;
   
   /**
    * Resumen de la publicacion.
    */
   protected String _abstract;
   
   /**
    * Comentarios al respecto.
    */
   protected String note;
   
   /**
    * Clave/s de la publicación.
    */
   protected String key;
   
   /**
    * Usuario que ha añadido la publicación al sistema.
    */
   protected String user;
   
   /**
    * Contiene todos los proyectos a los que pertenece la aplicación.
    */
   protected Vector proyectos;
   
   public PublicationException thePublicationException;
   
   /**
    * Devuelve el String correspondiente al código XML de la publicación 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C4463201C5
    */
   public abstract String getXML();
   
   /**
    * Devuelve el String correspondiente al código HTML de la publicación 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C5AA3D006D
    */
   public abstract String getHTML();
   
   /**
    * Devuelve el String correspondiente al código BibTeX de la publicación 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C5AA3F0213
    */
   public abstract String getBibTeX();
   
   public abstract void sustituir(String abrev, String texto);
   
   public abstract void imprimir();
   
   public Vector<Autor> extraerAutores(String autores)
   {
	   String nombre = autores;
	   String apellidos = null;
	   String web = null;
	   Autor autor = new Autor(nombre, apellidos, web);
	   Vector<Autor> v = new Vector<Autor>();
	   v.add(autor);
	   return v;
   }
   
   public Vector<Editor> extraerEditores(String editores)
   {
	   String nombre = editores;
	   String apellidos = null;
	   String web = null;
	   Editor editor = new Editor(nombre, apellidos, web);
	   Vector<Editor> v = new Vector<Editor>();
	   v.add(editor);
	   return v;
   }

public String getTitle() {
	return title;
}

public String get_abstract() {
	return _abstract;
}

public String getNote() {
	return note;
}

public String getKey() {
	return key;
}
}
