//Source file: C:\\GENERADO\\publicaciones\\InProceedings.java

package publicaciones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import org.jdom.Element;

import parserFicherosBibtex.CampoPublicacion;
import personas.AutorEditor;
import temporal.UnimplementedException;


/**
 * Un artículo en las actas de sesiones (proceedings) de una conferencia
 */
public class InProceedings extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;

	/**
	 * Libro al que hace referencia.
	 */
	private String booktitle;

	/**
	 * Ayuda a generar referecias cruzadas internas.
	 */
	private String crossref;

	/**
	 * LinkedList que contiene a la/s persona/s que, sin ser autores propiamente dichos, ayudaron a editar la obra.
	 */
	private LinkedList<AutorEditor> editor;

	/**
	 * Volumen en el que está contenido.
	 */
	private String volume;

	/**
	 * Numero de volumen.
	 */
	private String number;

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
	 * Organización que se encarga de la gestión de la misma.
	 */
	private String organization;

	/**
	 * Representa a la entidad que publica.
	 */
	private String publisher;

	/**
	 * Crea un InProceedings a partir de una lista de campos.
	 * @param campos Campos a partir de los cuales se quiere crear el objeto.
	 */
	public InProceedings(LinkedList<CampoPublicacion> campos)
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

	public InProceedings(Object[] objects) throws UnimplementedException {
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
		else if (nombreCampo.equals("title") && title == null)
			title = valorString;
		else if (nombreCampo.equals("booktitle") && booktitle == null)
			booktitle = valorString;
		else if (nombreCampo.equals("crossref") && crossref == null)
			crossref = valorString;
		else if (nombreCampo.equals("editor") && editor == null)
			editor = extraerAutoresEditores(valorString);
		else if (nombreCampo.equals("volume") && volume == null)
			volume = valorString;
		else if (nombreCampo.equals("series") && series == null)
			series = valorString;
		else if (nombreCampo.equals("address") && address == null)
			address = valorString;
		else if (nombreCampo.equals("month") && month == null)
			month = valorString;
		else if (nombreCampo.equals("organization") && organization == null)
			organization = valorString;
		else if (nombreCampo.equals("publisher") && publisher == null)
			publisher = valorString;
		else if (nombreCampo.equals("note") && note == null)
			note = valorString;
		else if (nombreCampo.equals("abstract") && _abstract == null)
			_abstract = valorString;
		else if (nombreCampo.equals("key") && key == null)
			key = valorString;
		else if (nombreCampo.equals("number") && number == null)
			number = valorString;
		else if (nombreCampo.equals("pages") && pages == null)
			pages = valorString;
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
		elemento.setAttribute ("tipo", "InProceedings");
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);

		Element eTitle = new Element("title");
		eTitle.addContent(title);
		elemento.addContent(eTitle);

		Element eAuthor = generarAutoresEditoresXML(true);
		elemento.addContent(eAuthor);

		Element eBooktitle = new Element("booktitle");
		eBooktitle.addContent(booktitle);
		elemento.addContent(eBooktitle);

		Element eYear = new Element("year");
		eYear.addContent(year);
		elemento.addContent(eYear);

		Element eCrossref = new Element("crossref");
		eCrossref.addContent(crossref);
		elemento.addContent(eCrossref);

		Element eEditor = generarAutoresEditoresXML(false);
		elemento.addContent(eEditor);

		Element eVolume = new Element("volume");
		eVolume.addContent(volume);
		elemento.addContent(eVolume);

		Element eNumber = new Element("number");
		eNumber.addContent(number);
		elemento.addContent(eNumber);

		Element eSeries = new Element("series");
		eSeries.addContent(series);
		elemento.addContent(eSeries);

		Element ePages = new Element("pages");
		ePages.addContent(pages);
		elemento.addContent(ePages);

		Element eAddress = new Element("address");
		eAddress.addContent(address);
		elemento.addContent(eAddress);

		Element eMonth = new Element("month");
		eMonth.addContent(month);
		elemento.addContent(eMonth);

		Element eOrganization = new Element("organization");
		eOrganization.addContent(organization);
		elemento.addContent(eOrganization);

		Element ePublisher = new Element("publisher");
		ePublisher.addContent(publisher);
		elemento.addContent(ePublisher);

		Element eNote = new Element("note");
		eNote.addContent(note);
		elemento.addContent(eNote);

		Element eAbstract = new Element("_abstract");
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

	public String getBooktitle() {
		return booktitle;
	}

	public String getCrossref() {
		return crossref;
	}

	public LinkedList<AutorEditor> getEditor() {
		return editor;
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

	public String getPages() {
		return pages;
	}

	public String getAddress() {
		return address;
	}

	public String getOrganization() {
		return organization;
	}

	public String getPublisher() {
		return publisher;
	}

	public static Vector<InProceedings> generaPub(Vector<Object[]> v) throws UnimplementedException {
		Vector <InProceedings> vector = new Vector <InProceedings>();
		if (v == null) return vector;
		for (int i=0; i< v.size();){
			Object[] array = v.get(i);
			int idDoc,year,number,id_aut,escrito_edit;
			String title,booktitle,crossref,volume,series,pages,address;
			String month,organization,publisher, note, abstracts, URL,user, referencia; 
			String proyecto,n_aut,ap_aut,web_aut,clave;
			LinkedList<AutorEditor> autores,editores;
			Vector<String> proyectos = new Vector<String>();
			Vector<String> claves = new Vector<String>();
			autores = new LinkedList<AutorEditor>();
			editores = new LinkedList<AutorEditor>();
			boolean cambio_pub;
			cambio_pub = false;			
			idDoc = ((Long) array[0]).intValue();
			if (array[1] != null) title = (String) array[1]; else title = null;
			if (array[2] != null) booktitle = (String) array[2]; else booktitle = null;
			if (array[3] != null) year = ((Long) array[3]).intValue(); else year = -1;
			if (array[4] != null) crossref = (String) array[4]; else crossref = null;
			if (array[5] != null) volume = (String) array[5]; else volume = null;
			if (array[6] != null) number = ((Long) array[6]).intValue(); else number = -1;
			if (array[7] != null) series = (String) array[7]; else series = null;
			if (array[8] != null) pages = (String) array[8]; else pages = null;
			if (array[9] != null) address = (String) array[9]; else address = null;
			if (array[10] != null) month = (String) array[10]; else month = null;
			if (array[11] != null) organization = (String) array[11]; else organization = null;
			if (array[12] != null) publisher = (String) array[12]; else publisher = null;
			if (array[13] != null) note = (String) array[13]; else note = null;
			if (array[14] != null) abstracts = (String) array[14]; else abstracts = null;
			if (array[15] != null) URL = (String) array[15]; else URL = null;
			if (array[16] != null) user = (String) array[16]; else user = null;
			if (array[17] != null) referencia = (String) array[17]; else referencia = null;
			if (array[18] != null) proyecto = (String) array[18]; else proyecto = null;
			id_aut = ((Long) array[19]).intValue();			
			if (array[20] != null) n_aut = (String) array[20]; else n_aut = null;
			if (array[21] != null) ap_aut = (String) array[21]; else ap_aut = null;
			if (array[22] != null) web_aut = (String) array[22]; else web_aut = null;
			escrito_edit = ((Long) array[23]).intValue();
			if (array[24] != null) clave = (String) array[24]; else clave = null;
			AutorEditor autor = new AutorEditor(id_aut,n_aut,ap_aut,web_aut);
			if (escrito_edit == 1) autores.add(autor);
			if (escrito_edit == 0) editores.add(autor);
			if (proyecto != null) proyectos.add(proyecto);
			if (clave != null) claves.add(clave);
			InProceedings inp1 = new InProceedings(idDoc,referencia,title,Integer.toString(year),month,URL,abstracts,note,claves,user,proyectos,autores,booktitle,crossref,editores,volume,Integer.toString(number),series,pages,address,organization,publisher);
			vector.add(inp1);
			
			// Evaluamos el cambio_pub
			i++;
			if (i>= v.size()) cambio_pub = true;
			else {
				array = v.get(i);		
				idDoc = ((Long) array[0]).intValue();
				if (idDoc != inp1.getIdDoc()) cambio_pub = true;
				else cambio_pub = false;
			}			
			while (!cambio_pub){
				if (array[18] != null) proyecto = (String) array[18]; else proyecto = null;
				id_aut = ((Long) array[19]).intValue();			
				if (array[20] != null) n_aut = (String) array[20]; else n_aut = null;
				if (array[21] != null) ap_aut = (String) array[21]; else ap_aut = null;
				if (array[22] != null) web_aut = (String) array[22]; else web_aut = null;
				escrito_edit = ((Long) array[23]).intValue();
				if (array[24] != null) clave = (String) array[24]; else clave = null;
			
				
				autor = new AutorEditor(id_aut,n_aut,ap_aut,web_aut);				
				if (escrito_edit == 1) inp1.addAutor(autor);
				if (escrito_edit == 0) inp1.addEditor(autor);
				
				
				if (proyecto != null) inp1.addProyect(proyecto);
				if (clave != null) inp1.addKey(clave);
			}				
			
		}
		return vector;
	}

	/* (non-Javadoc)
	 * @see publicaciones.Publication#SetAll(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Vector, java.lang.String, java.util.Vector)
	 */
	public InProceedings(int idDoc, String referencia, String title,
			String year, String month, String url, String _abstract,
			String note, Vector<String> key, String user,
			Vector<String> proyectos,LinkedList<AutorEditor> author, String booktitle,
			String crossref, LinkedList<AutorEditor> editor, String volume,
			String number, String series, String pages, String address,
			String organization, String publisher) throws UnimplementedException {
		this.author = author;
		this.booktitle = booktitle;
		this.crossref = crossref;
		this.editor = editor;
		this.volume = volume;
		this.number = number;
		this.series = series;
		this.pages = pages;
		this.address = address;
		this.organization = organization;
		this.publisher = publisher;
		super.SetAll(idDoc, referencia, title, year, month, url, _abstract, note, key,
				user, proyectos);
	}
	
	public void addAutor(AutorEditor e){
		if (!author.contains(e)) author.add(e);
	}
	
	public void addEditor(AutorEditor e){
		if (!editor.contains(e)) author.add(e);
	}
		
}
