//Source file: C:\\GENERADO\\publicaciones\\Book.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;

import org.jdom.Element;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;
import temporal.UnimplementedException;


/**
 * Clase que representa un libro
 */
public class Book extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;
	
	/**
	 * LinkedList que contiene a la/s persona/s que, sin ser autores propiamente dichos, ayudaron a editar la obra.
	 */
	private LinkedList<AutorEditor> editor;
	
   /**
    * Representa a la entidad que publica.
    */
   private String publisher;
   
   /**
    * Volumen en el que está contenido.
    */
   private String volume;
   
   /**
    * Serie en la que se encuadra la publicacion.
    */
   private String series;
   
   /**
    * Lugar de publicación.
    */
   private String address;
   
   /**
    * Edicion del mismo.
    */
   private String edition;
   
   /**
    * Crea un Book a partir de una lista de campos.
    * @param campos Campos a partir de los cuales se quiere crear el objeto.
    */
   public Book(LinkedList<CampoPublicacion> campos)
   {
	   CampoPublicacion campo;
	   Iterator<CampoPublicacion> it = campos.iterator();
	   while (it.hasNext())
	   {
		   campo = it.next();
		   String nombreCampo = campo.getNombre();
		   String valor = campo.getValor();
		   insertar(nombreCampo, valor);
	   }
   }
   
   public Book(Object[] objects) throws UnimplementedException {
	throw new UnimplementedException();
}

/**
    * Inserta el campo.
    * @param nombreCampo Nombre del campo que se quiere insertar.
    * @param valorString Valor del campo que se quiere insertar.
    */
   private void insertar(String nombreCampo, String valorString)
   {
	   if (nombreCampo.equals("author") && author == null)
		   author = extraerAutoresEditores(valorString);
	   else if (nombreCampo.equals("referencia") && referencia == null)
		   referencia = valorString;
	   else if (nombreCampo.equals("editor") && editor == null)
		   editor = extraerAutoresEditores(valorString);
	   else if (nombreCampo.equals("title") && title == null)
		   title = valorString;
	   else if (nombreCampo.equals("publisher") && publisher == null)
		   publisher = valorString;
	   else if (nombreCampo.equals("volume") && volume == null)
		   volume = valorString;
	   else if (nombreCampo.equals("series") && series == null)
		   series = valorString;
	   else if (nombreCampo.equals("address") && address == null)
		   address = valorString;
	   else if (nombreCampo.equals("edition") && edition == null)
		   edition = valorString;
	   else if (nombreCampo.equals("month") && month == null)
		   month = valorString;
	   else if (nombreCampo.equals("note") && note == null)
		   note = valorString;
	   else if (nombreCampo.equals("abstract") && _abstract == null)
		   _abstract = valorString;
	   else if (nombreCampo.equals("key") && key == null)
		   key = valorString;
	   else if (nombreCampo.equals("year") && year == null)
		   year = valorString;
   }
   
   /**
	 * Genera un elemento XML con la información del objeto.
	 * @return El elemento generado.
	 */
   public Element generarElementoXML()
   {
	   Element elemento = new Element("publication");
	   elemento.setAttribute ("tipo", "Book");
	   if (referencia != null)
		   elemento.setAttribute("referencia", referencia);

	   Element eTitle = new Element("title");
	   eTitle.addContent(title);
	   elemento.addContent(eTitle);

	   Element eAuthor = generarAutoresEditoresXML(true);
	   elemento.addContent(eAuthor);

	   Element eEditor = generarAutoresEditoresXML(false);
	   elemento.addContent(eEditor);

	   Element ePublisher = new Element("publisher");
	   ePublisher.addContent(publisher);
	   elemento.addContent(ePublisher);

	   Element eYear = new Element("year");
	   eYear.addContent(year);
	   elemento.addContent(eYear);

	   Element eVolume = new Element("volume");
	   eVolume.addContent(volume);
	   elemento.addContent(eVolume);

	   Element eSeries = new Element("series");
	   eSeries.addContent(series);
	   elemento.addContent(eSeries);

	   Element eAddress = new Element("address");
	   eAddress.addContent(address);
	   elemento.addContent(eAddress);

	   Element eEdition = new Element("edition");
	   eEdition.addContent(edition);
	   elemento.addContent(eEdition);

	   Element eMonth = new Element("month");
	   eMonth.addContent(month);
	   elemento.addContent(eMonth);

	   Element eNote = new Element("note");
	   eNote.addContent(note);
	   elemento.addContent(eNote);

	   Element eAbstract = new Element("abstract");
	   eAbstract.addContent(_abstract);
	   elemento.addContent(eAbstract);

	   Element eKey = new Element("key");
	   eKey.addContent(key);
	   elemento.addContent(eKey);

	   return elemento;
   }
   
   /**
	 * Genera un elemento XML con todos los autores/editores.
	 * @return El elemento generado.
	 */
   private Element generarAutoresEditoresXML(boolean b) 
   {
	   Element eAuthorEditor;
	   if (b) //eAuthor
	   {
		   eAuthorEditor = new Element("authors");
		   if (author != null)
		   {
			   Iterator<AutorEditor> it = author.iterator();
			   while (it.hasNext())
				   eAuthorEditor.addContent(it.next().generarAuthorXML());
		   }
	   }
	   else //eEditor
	   {
		   eAuthorEditor = new Element("editors");
		   if (editor != null)
		   {
			   Iterator<AutorEditor> it = editor.iterator();
			   while (it.hasNext())
				   eAuthorEditor.addContent(it.next().generarEditorXML());
		   }
	   }
	   return eAuthorEditor;
   }

   @Override
   public String getBibTeX() {
	   // TODO Auto-generated method stub
	   return null;
   }

   @Override
   public String getHTML() {
	   // TODO Auto-generated method stub
	   return null;
   }

   public LinkedList<AutorEditor> getAuthor() {
	   return author;
   }

   public LinkedList<AutorEditor> getEditor() {
	   return editor;
   }

   public String getPublisher() {
	   return publisher;
   }

   public String getVolume() {
	   return volume;
   }

   public String getSeries() {
	   return series;
   }

   public String getAddress() {
	   return address;
   }

   public String getEdition() {
	   return edition;
   }
}
