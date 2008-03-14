//Source file: C:\\GENERADO\\publicaciones\\Publication.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import parserFicherosBibtex.CampoString;
import personas.AutorEditor;

/**
 * Clase abstracta que agrupa los dos atributos comunes a todos las "publicaciones" 
 * 
 * 
 * 
 * existentes, su idDoc y su t�tulo.
 * Nos servir� para realizar las operaciones gen�ricas sobre ellas.
 */
public abstract class Publication 
{
   
   /**
    * Valor que le corresponde al Id del documento
    */
	protected int idDoc;
   
   /**
    * T�tulo que le corresponde a la publicaci�n.
    */
   protected String title;
   
   /**
    * A�o de publicaci�n.
    */
   protected String year;
   
   /**
    * Mes de publicaci�n.
    */
   protected String month;
   
   /**
    * URL en el que se puede localizar la publicaci�n.
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
    * Clave/s de la publicaci�n.
    */
   protected String key;
   
   /**
    * Usuario que ha a�adido la publicaci�n al sistema.
    */
   protected String user;
   
   /**
    * Contiene todos los proyectos a los que pertenece la aplicaci�n.
    */
   protected LinkedList proyectos;
   
   public PublicationException thePublicationException;
   
   /**
    * Devuelve el String correspondiente al c�digo XML de la publicaci�n 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C4463201C5
    */
   public abstract String getXML();
   
   /**
    * Devuelve el String correspondiente al c�digo HTML de la publicaci�n 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C5AA3D006D
    */
   public abstract String getHTML();
   
   /**
    * Devuelve el String correspondiente al c�digo BibTeX de la publicaci�n 
    * correspondiente.
    * @return java.lang.String
    * @roseuid 47C5AA3F0213
    */
   public abstract String getBibTeX();
   
   public abstract void imprimir();
   
   protected LinkedList<AutorEditor> extraerAutoresEditores(String autoresEditores)
   {
	   LinkedList<AutorEditor> lista = new LinkedList<AutorEditor>();
	   if (autoresEditores != null)
	   {
		   if (autoresEditores.charAt(0) == '{')
			   lista.add(new AutorEditor(autoresEditores));
		   else
			   if (autoresEditores.contains(" and "))
			   {
				   String[] separados = autoresEditores.split(" and ");
				   int numSeparados = separados.length;
				   LinkedList<AutorEditor> ae = new LinkedList<AutorEditor>();
				   for (int i = 0; i < numSeparados; i++)
				   {
					   ae = extraerAutoresEditores(separados[i]);
					   lista.addAll(ae);
				   }
			   }
			   else
			   {
				   AutorEditor nuevo = new AutorEditor(autoresEditores);
				   lista.add(nuevo);
			   }
		   return lista;
	   }
	   else
		   return null;
   }
   
   protected String sustituirStrings(LinkedList<CampoString> strings, String valorString)
	{
	   String nuevo = valorString;
		Iterator<CampoString> itStrings = strings.iterator();
		CampoString c;
		while (itStrings.hasNext())
		{
			c = itStrings.next();
			String abrev = c.getAbrev();
			String texto = c.getTexto();

			nuevo = nuevo.replaceAll(" " + abrev + " ", " " + texto + " ");
			nuevo = nuevo.replaceAll(" " + abrev + ",", " " + texto + ",");
			nuevo = nuevo.replaceAll("=" + abrev + " ", "=" + texto + " ");
			nuevo = nuevo.replaceAll("=" + abrev + ",", "=" + texto + ",");
		}
		return nuevo;
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

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}
}
