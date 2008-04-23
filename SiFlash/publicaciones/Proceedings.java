//Source file: C:\\GENERADO\\publicaciones\\Proceedings.java

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
 * Las actas de sesiones de una conferencia.
 */
public class Proceedings extends Publication 
{
	/**
	 * Libro al que hace referencia.
	 */
	private String booktitle;

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
	 * Crea un Proceedings a partir de una lista de campos.
	 * @param campos Campos a partir de los cuales se quiere crear el objeto.
	 */
	public Proceedings(LinkedList<Campo> campos)
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

	public Proceedings(Object[] objects) throws UnimplementedException {
		throw new UnimplementedException();
	}

	/**
	 * Inserta el campo.
	 * @param nombreCampo Nombre del campo que se quiere insertar.
	 * @param valorString Valor del campo que se quiere insertar.
	 */
	private void insertar(String nombreCampo, String valorString)
	{
		if (nombreCampo.equalsIgnoreCase("title") && title == null)
			title = valorString;
		else if (nombreCampo.equalsIgnoreCase("referencia") && referencia == null)
			referencia = valorString;
		else if (nombreCampo.equalsIgnoreCase("booktitle") && booktitle == null)
			booktitle = valorString;
		else if (nombreCampo.equalsIgnoreCase("editor") && editor == null)
			editor = extraerAutoresEditores(valorString);
		else if (nombreCampo.equalsIgnoreCase("volume") && volume == null)
			volume = valorString;
		else if (nombreCampo.equalsIgnoreCase("series") && series == null)
			series = valorString;
		else if (nombreCampo.equalsIgnoreCase("address") && address == null)
			address = valorString;
		else if (nombreCampo.equalsIgnoreCase("month") && month == null)
			month = valorString;
		else if (nombreCampo.equalsIgnoreCase("organization") && organization == null)
			organization = valorString;
		else if (nombreCampo.equalsIgnoreCase("publisher") && publisher == null)
			publisher = valorString;
		else if (nombreCampo.equalsIgnoreCase("note") && note == null)
			note = valorString;
		else if (nombreCampo.equalsIgnoreCase("abstract") && _abstract == null)
			_abstract = valorString;
		else if (nombreCampo.equalsIgnoreCase("key") && key == null)
			key = separarKeys(valorString);
		else if (nombreCampo.equalsIgnoreCase("number") && number == null)
			number = valorString;
		else if (nombreCampo.equalsIgnoreCase("year") && year == null)
			year = valorString;
	}

	private void insertar(String nombreCampo, LinkedList<AutorEditor> valor) 
	{
		if (nombreCampo.equalsIgnoreCase("editors") && editor == null)
			editor = valor;
	}
	
	/**
	 * Genera un elemento XML con la información del objeto.
	 * @return El elemento generado.
	 */
	public Element generarElementoXML()
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "Proceedings");
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);

		Element eTitle = new Element("title");
		eTitle.addContent(title);
		elemento.addContent(eTitle);

		Element eYear = new Element("year");
		eYear.addContent(year);
		elemento.addContent(eYear);

		Element eBooktitle = new Element("booktitle");
		eBooktitle.addContent(booktitle);
		elemento.addContent(eBooktitle);

		Element eEditor = generarAutoresEditoresXML();
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

		Element eAbstract = new Element("abstract");
		eAbstract.addContent(_abstract);
		elemento.addContent(eAbstract);

		Element eKey = new Element("key");
		eKey.addContent(convertirTextoBibtexKeys(key));
		elemento.addContent(eKey);

		return elemento;
	}

	/**
	 * Genera un elemento XML con todos los editores.
	 * @return El elemento generado.
	 */
	private Element generarAutoresEditoresXML() 
	{	
		Element eEditor = new Element("editors");
		if (editor != null)
		{
			Iterator<AutorEditor> it = editor.iterator();
			while (it.hasNext())
				eEditor.addContent(it.next().generarAuthorXML());
		}
		return eEditor;
	}

	@Override
	public String getBibTeX() 
	{
		String bibtex = "@proceedings{";
		if (referencia != null)
			bibtex += referencia;
		bibtex += "\n";
		if (title != null)
			bibtex += "\ttitle={" + convertirTextoBibtex(title) + "}\n";
		if (year != null)
			bibtex += "\tyear={" + convertirTextoBibtex(year) + "}\n";
		if (month != null)
			bibtex += "\tmonth={" + convertirTextoBibtex(month) + "}\n";
		if (booktitle != null)
			bibtex += "\tbooktitle={" + convertirTextoBibtex(booktitle) + "}\n";
		if (editor != null)
			bibtex += "\teditor={" + convertirTextoBibtex(editor) + "}\n";
		if (volume != null)
			bibtex += "\tvolume={" + convertirTextoBibtex(volume) + "}\n";
		if (number != null)
			bibtex += "\tnumber={" + convertirTextoBibtex(number) + "}\n";
		if (series != null)
			bibtex += "\tseries={" + convertirTextoBibtex(series) + "}\n";
		if (address != null)
			bibtex += "\taddress={" + convertirTextoBibtex(address) + "}\n";
		if (organization != null)
			bibtex += "\torganization={" + convertirTextoBibtex(organization) + "}\n";
		if (publisher != null)
			bibtex += "\tpublisher={" + convertirTextoBibtex(publisher) + "}\n";
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

	public String getBooktitle() {
		return booktitle;
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

	public String getAddress() {
		return address;
	}

	public String getOrganization() {
		return organization;
	}

	public String getPublisher() {
		return publisher;
	}

	@Override
	public Vector<String> generaInserciones() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Vector<Proceedings> generaPub(Vector<Object[]> v) throws UnimplementedException {
		Vector <Proceedings> vector = new Vector <Proceedings>();
		if (v == null) return vector;
		for (int i=0; i< v.size();){
			Object[] array = v.get(i);
			int idDoc,year,number,id_aut;
			String title,booktitle,volume,series,address;
			String month,organization,publisher, note, abstracts, URL,user, referencia; 
			String proyecto,n_aut,ap_aut,web_aut,clave;
			LinkedList<AutorEditor> editores;
			Vector<String> proyectos = new Vector<String>();
			Vector<String> claves = new Vector<String>();
			editores = new LinkedList<AutorEditor>();
			boolean cambio_pub,escrito_edit;
			cambio_pub = false;			
			idDoc = ((Long) array[0]).intValue();
			if (array[1] != null) title = (String) array[1]; else title = null;
			if (array[2] != null) booktitle = (String) array[2]; else booktitle = null;
			if (array[3] != null) year = ((Long) array[3]).intValue(); else year = -1;	
			if (array[4] != null) volume = (String) array[4]; else volume = null;
			if (array[5] != null) number = ((Long) array[5]).intValue(); else number = -1;
			if (array[6] != null) series = (String) array[6]; else series = null;
			if (array[7] != null) address = (String) array[7]; else address = null;
			if (array[8] != null) month = (String) array[8]; else month = null;
			if (array[9] != null) organization = (String) array[9]; else organization = null;
			if (array[10] != null) publisher = (String) array[10]; else publisher = null;
			if (array[11] != null) note = (String) array[11]; else note = null;
			if (array[12] != null) abstracts = (String) array[12]; else abstracts = null;
			if (array[13] != null) URL = (String) array[13]; else URL = null;
			if (array[14] != null) user = (String) array[14]; else user = null;
			if (array[15] != null) referencia = (String) array[15]; else referencia = null;
			if (array[16] != null) proyecto = (String) array[16]; else proyecto = null;
			id_aut = ((Long) array[17]).intValue();			
			if (array[18] != null) n_aut = (String) array[18]; else n_aut = null;
			if (array[19] != null) ap_aut = (String) array[19]; else ap_aut = null;
			if (array[20] != null) web_aut = (String) array[20]; else web_aut = null;
			escrito_edit = ((Boolean) array[21]).booleanValue();
			if (array[22] != null) clave = (String) array[22]; else clave = null;
			AutorEditor autor1 = new AutorEditor(id_aut,n_aut,ap_aut,web_aut);
			if (escrito_edit == false) editores.add(autor1);	
			if (proyecto != null) proyectos.add(proyecto);
			if (clave != null) claves.add(clave);
			Proceedings pr1 = new Proceedings(idDoc,referencia,title,Integer.toString(year),month,URL,abstracts,note,claves,user,proyectos,booktitle,editores,volume,Integer.toString(number),series,address,organization,publisher);
			vector.add(pr1);

			// Evaluamos el cambio_pub
			i++;
			if (i>= v.size()) cambio_pub = true;
			else {
				array = v.get(i);		
				idDoc = ((Long) array[0]).intValue();
				if (idDoc != pr1.getIdDoc()) cambio_pub = true;
				else cambio_pub = false;
			}			
			while (!cambio_pub){
				if (array[18] != null) proyecto = (String) array[18]; else proyecto = null;
				id_aut = ((Long) array[19]).intValue();			
				if (array[20] != null) n_aut = (String) array[20]; else n_aut = null;
				if (array[21] != null) ap_aut = (String) array[21]; else ap_aut = null;
				if (array[22] != null) web_aut = (String) array[22]; else web_aut = null;
				escrito_edit = ((Boolean) array[23]).booleanValue();
				if (array[24] != null) clave = (String) array[24]; else clave = null;


				autor1 = new AutorEditor(id_aut,n_aut,ap_aut,web_aut);				
				if (escrito_edit == false) pr1.addEditor(autor1);


				if (proyecto != null) pr1.addProyect(proyecto);
				if (clave != null) pr1.addKey(clave);

				// Evaluamos el cambio de publicacion
				i++;
				if (i>= v.size()) cambio_pub = true;
				else {
					array = v.get(i);		
					idDoc = ((Long) array[0]).intValue();
					if (idDoc != pr1.getIdDoc()) cambio_pub = true;
					else cambio_pub = false;
				}					
			}							
		}
		return vector;
	}

	public void addEditor(AutorEditor e){
		if (!editor.contains(e)) editor.add(e);
	}
	
	public Proceedings(int idDoc, String referencia, String title,
			String year, String month, String url, String _abstract,
			String note, Vector<String> key, String user,
			Vector<String> proyectos, String booktitle,
			LinkedList<AutorEditor> editor, String volume,
			String number, String series, String address,
			String organization, String publisher) throws UnimplementedException {		
		this.booktitle = booktitle;
		this.editor = editor;
		this.volume = volume;
		this.number = number;
		this.series = series;
		this.address = address;
		this.organization = organization;
		this.publisher = publisher;
		super.SetAll(idDoc, referencia, title, year, month, url, _abstract, note, key,
				user, proyectos);
	}		
}
