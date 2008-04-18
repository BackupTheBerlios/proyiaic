//Source file: C:\\GENERADO\\publicaciones\\Book.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import org.jdom.Element;

import parserFicherosBibtex.Campo;
import parserFicherosBibtex.CampoPublicacion;
import parserFicherosBibtex.CampoPublicacionAutorEditor;
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
    * Número del libro.
    */
   private String number;
   
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
   public Book(LinkedList<Campo> campos)
	{
		Campo campo;
		Iterator<Campo> it = campos.iterator();
		while (it.hasNext())
		{
			campo = it.next();
			String nombreCampo = campo.getNombre();
			if (campo instanceof CampoPublicacion)
			{
				String valor = ((CampoPublicacion)campo).getValor();
				insertar(nombreCampo, valor);
			}
			else
			{
				LinkedList<AutorEditor> valor = ((CampoPublicacionAutorEditor)campo).getValor();
				insertar(nombreCampo, valor);
			}
				
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
	   if (nombreCampo.equalsIgnoreCase("author") && author == null)
		   author = extraerAutoresEditores(valorString);
	   else if (nombreCampo.equalsIgnoreCase("referencia") && referencia == null)
		   referencia = valorString;
	   else if (nombreCampo.equalsIgnoreCase("editor") && editor == null)
		   editor = extraerAutoresEditores(valorString);
	   else if (nombreCampo.equalsIgnoreCase("title") && title == null)
		   title = valorString;
	   else if (nombreCampo.equalsIgnoreCase("publisher") && publisher == null)
		   publisher = valorString;
	   else if (nombreCampo.equalsIgnoreCase("volume") && volume == null)
		   volume = valorString;
	   else if (nombreCampo.equalsIgnoreCase("number") && number == null)
		   number = valorString;
	   else if (nombreCampo.equalsIgnoreCase("series") && series == null)
		   series = valorString;
	   else if (nombreCampo.equalsIgnoreCase("address") && address == null)
		   address = valorString;
	   else if (nombreCampo.equalsIgnoreCase("edition") && edition == null)
		   edition = valorString;
	   else if (nombreCampo.equalsIgnoreCase("month") && month == null)
		   month = valorString;
	   else if (nombreCampo.equalsIgnoreCase("note") && note == null)
		   note = valorString;
	   else if (nombreCampo.equalsIgnoreCase("abstract") && _abstract == null)
		   _abstract = valorString;
	   else if (nombreCampo.equalsIgnoreCase("key") && key == null)
		   key = separarKeys(valorString);
	   else if (nombreCampo.equalsIgnoreCase("year") && year == null)
		   year = valorString;
   }
   
   private void insertar(String nombreCampo, LinkedList<AutorEditor> valor) 
	{
		if (nombreCampo.equalsIgnoreCase("authors") && author == null)
			author = valor;
		else if (nombreCampo.equalsIgnoreCase("editors") && editor == null)
			editor = valor;
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

	   Element eNumber = new Element("number");
	   eVolume.addContent(number);
	   elemento.addContent(eNumber);
	   
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
	   eKey.addContent(convertirTextoBibtexKeys(key));
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
   public String getBibTeX() 
   {
		String bibtex = "@book{";
		if (referencia != null)
			bibtex += referencia;
		bibtex += "\n";
		if (title != null)
			bibtex += "\ttitle={" + convertirTextoBibtex(title) + "}\n";
		if (year != null)
			bibtex += "\tyear={" + convertirTextoBibtex(year) + "}\n";
		if (month != null)
			bibtex += "\tmonth={" + convertirTextoBibtex(month) + "}\n";
		if (author != null)
			bibtex += "\tauthor={" + convertirTextoBibtex(author) + "}\n";
		if (editor != null)
			bibtex += "\teditor={" + convertirTextoBibtex(editor) + "}\n";
		if (publisher != null)
			bibtex += "\tpublisher={" + convertirTextoBibtex(publisher) + "}\n";
		if (volume != null)
			bibtex += "\tvolume={" + convertirTextoBibtex(volume) + "}\n";
		if (number != null)
			bibtex += "\tnumber={" + convertirTextoBibtex(number) + "}\n";
		if (series != null)
			bibtex += "\tseries={" + convertirTextoBibtex(series) + "}\n";
		if (address != null)
			bibtex += "\taddress={" + convertirTextoBibtex(address) + "}\n";
		if (edition != null)
			bibtex += "\tedition={" + convertirTextoBibtex(edition) + "}\n";
		if (_abstract != null)
			bibtex += "\tabstract={" + convertirTextoBibtex(_abstract) + "}\n";
		if (note != null)
			bibtex += "\tnote={" + convertirTextoBibtex(note) + "}\n";
		if (key != null)
			bibtex += "\tkey={" + convertirTextoBibtexKeys(key) + "}\n";
		bibtex += "}";
		
		return bibtex;
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
   
   public String getNumber() {
	   return number;
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

@Override
public Vector<String> generaInserciones() {
	// TODO Auto-generated method stub
	return null;
}
}
