//Source file: C:\\GENERADO\\publicaciones\\Publication.java

package publicaciones;

import java.util.LinkedList;

import org.jdom.Element;

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
	 * Referencia del documento BibTeX.
	 */
   protected String referencia;
	
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
   
   public abstract Element generarElementoXML();
   
   protected LinkedList<AutorEditor> extraerAutoresEditores(String autoresEditores)
   {
	   LinkedList<AutorEditor> lista = new LinkedList<AutorEditor>();
	   if (autoresEditores != null)
	   {
		   if (autoresEditores.charAt(0) == '{')
			   lista.add(new AutorEditor(autoresEditores));
		   else
		   {
			   String separador = dameSeparador(autoresEditores);
			   if (separador != null)
			   {
				   String[] separados = autoresEditores.split(separador);
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
		   }
		   return lista;
	   }
	   else
		   return null;
   }
   
   private String dameSeparador(String autoresEditores) 
   {
	   int posAnd;
	   if (autoresEditores.contains(" and "))
		   return " and ";
	   else if (autoresEditores.contains(" and\n"))
		   return " and\n";
	   else if (autoresEditores.contains(" and\t"))
		   return " and\t";
	   else if (autoresEditores.contains(" and\r"))
		   return " and\r";
	   else if (autoresEditores.contains("\nand "))
		   return "\nand ";
	   else if (autoresEditores.contains("\nand\n"))
		   return "\nand\n";
	   else if (autoresEditores.contains("\nand\t"))
		   return "\nand\t";
	   else if (autoresEditores.contains("\nand\r"))
		   return "\nand\r";
	   else if (autoresEditores.contains("\tand "))
		   return "\tand ";
	   else if (autoresEditores.contains("\tand\n"))
		   return "\tand\n";
	   else if (autoresEditores.contains("\tand\t"))
		   return "\tand\t";
	   else if (autoresEditores.contains("\tand\r"))
		   return "\tand\r";
	   else if (autoresEditores.contains("\rand "))
		   return "\rand ";
	   else if (autoresEditores.contains("\rand\n"))
		   return "\rand\n";
	   else if (autoresEditores.contains("\rand\t"))
		   return "\rand\t";
	   else if (autoresEditores.contains("\rand\r"))
		   return "\rand\r";
	   else 
		   return null;
	 
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

	public String getReferencia() {
		return referencia;
	}
}
