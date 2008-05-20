//Source file: C:\\GENERADO\\publicaciones\\TechReport.java

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
import controlador.DataBaseControler;
import database.BDException;


/**
 * Un reporte publicado por una escuela u otra institución, usualmente numerado 
 * dentro de una serie.
 */
public class TechReport extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;

	/**
	 * Institucion que lo realiza.
	 */
	private String institution;

	/**
	 * Tipo del contenido.
	 */
	private String type;

	/**
	 * Numero de volumen.
	 */
	private String number;

	/**
	 * Lugar de publicación.
	 */
	private String address;

	/**
	 * Crea un TechReport a partir de una lista de campos.
	 * @param campos Campos a partir de los cuales se quiere crear el objeto.
	 */
	public TechReport(LinkedList<Campo> campos)
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

	public TechReport(Object[] objects) throws UnimplementedException {
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
		else if (nombreCampo.equalsIgnoreCase("title") && title == null)
			title = valorString;
		else if (nombreCampo.equalsIgnoreCase("institution") && institution == null)
			institution = valorString;
		else if (nombreCampo.equalsIgnoreCase("type") && type == null)
			type = valorString;
		else if (nombreCampo.equalsIgnoreCase("address") && address == null)
			address = valorString;
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
		else if (nombreCampo.equalsIgnoreCase("number") && number == null)
			number = valorString;
		else if (nombreCampo.equalsIgnoreCase("user") && user == null)
			user = valorString;
		else if (nombreCampo.equalsIgnoreCase("url") && URL == null)
			URL = valorString;
		else if (nombreCampo.equalsIgnoreCase("proyecto") && proyecto == null)
			proyecto = valorString;
	}

	private void insertar(String nombreCampo, LinkedList<AutorEditor> valor) 
	{
		if (nombreCampo.equalsIgnoreCase("authors") && author == null)
			author = valor;
	}

	/**
	 * Genera un elemento XML con la información del objeto.
	 * @return El elemento generado.
	 */
	public Element generarElementoXML()
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "TechReport");
		if (idDoc != 0)
			elemento.setAttribute("idDoc", ""+idDoc);
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);

		Element eTitle = new Element("title");
		eTitle.addContent(title);
		elemento.addContent(eTitle);

		Element eAuthor = generarAutoresEditoresXML();
		elemento.addContent(eAuthor);

		Element eInstitution = new Element("institution");
		eInstitution.addContent(institution);
		elemento.addContent(eInstitution);

		Element eYear = new Element("year");
		eYear.addContent(year);
		elemento.addContent(eYear);

		Element eType = new Element("type");
		eType.addContent(type);
		elemento.addContent(eType);

		Element eAddress = new Element("address");
		eAddress.addContent(address);
		elemento.addContent(eAddress);

		Element eNumber = new Element("number");
		eNumber.addContent(number);
		elemento.addContent(eNumber);

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
	 * Genera un elemento XML con todos los autores.
	 * @return El elemento generado.
	 */
	private Element generarAutoresEditoresXML() 
	{	
		Element eAuthor = new Element("authors");
		if (author != null)
		{
			Iterator<AutorEditor> it = author.iterator();
			while (it.hasNext())
				eAuthor.addContent(it.next().generarAuthorXML());
		}
		return eAuthor;
	}

	@Override
	public String getBibTeX()
	{
		String bibtex = "@techreport{";
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
		if (institution != null)
			bibtex += "\tinstitution={" + convertirTextoBibtex(institution) + "}\n";
		if (type != null)
			bibtex += "\ttype={" + convertirTextoBibtex(type) + "}\n";
		if (number != null)
			bibtex += "\tnumber={" + convertirTextoBibtex(number) + "}\n";
		if (address != null)
			bibtex += "\taddress={" + convertirTextoBibtex(address) + "}\n";
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

	public String getInstitution() {
		return institution;
	}

	public String getType() {
		return type;
	}

	public String getNumber() {
		return number;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public Vector<String> generaInserciones() throws BDException {
		idDoc = 0;
		Vector <String> vector = new Vector <String>();
		String str1 = new String ("INSERT INTO techreport VALUES (");
		str1 += Integer.toString(getIdDoc());

		if (getTitle()!= null)
			str1 += ",'" + getTitle() + "'";
		else str1+= ",null";

		if (getInstitution()!= null)
			str1 += ",'" + getInstitution() + "'";
		else str1+= ",null";		

		if (getYear()!=null)
			str1 += ",'" + getYear() + "'";
		else str1+= ",null";
		
		if (getType()!=null)
			str1 += ",'" + getType() + "'";
		else str1+= ",null";		

		if(getNumber()!=null)
			str1 += ",'" + getNumber() + "'";
		else str1+= ",null";

		if(getAddress()!=null)
			str1 += ",'" + getAddress() + "'";
		else str1+= ",null";

		if(getMonth()!=null)
			str1 += ",'" + getMonth() + "'";
		else str1+= ",null";

		if(getNote()!=null)
			str1 += ",'" + getNote() + "'";
		else str1+= ",null";

		if(get_abstract()!=null)
			str1 += ",'" + get_abstract() + "'";
		else str1+= ",null";

		if(getURL()!=null)
			str1 += ",'" + getURL() + "'";
		else str1+= ",null";

		if(getUser()!=null)
			str1 += ",'" + getUser() + "'";
		else str1+= ",null";

		if(getReferencia()!=null)
			str1 += ",'" + getReferencia() + "'";
		else str1+= ",null";

		str1+=");";

		DataBaseControler dbc = new DataBaseControler();
		dbc.ejecutaString(str1);
		idDoc = dbc.consultaIdDoc();	

		str1 = new String ("INSERT INTO tipopublicacion VALUES (" + getIdDoc() + ",'techreport');");
		vector.add(str1);

		if (author != null)
			for (int i=0;i<this.author.size();i++){
				int idAutor = author.get(i).getId();
				String str;
				if (idAutor == 0) //Hay que insertarlo
				{
					idAutor = dbc.consultaIdAutor(author.get(i).getNombre(), author.get(i).getApellidos());
					if (idAutor == 0)
					{
						dbc.insertaAutorEditor(author.get(i));
						idAutor = dbc.consultaIdAutor(author.get(i).getNombre(), author.get(i).getApellidos());
					}
				}
				str = new String ("INSERT INTO escrito_editado_por VALUES(" + getIdDoc());
				str += "," + idAutor + ",TRUE);";
				vector.add(str);
			}

		vector.addAll(super.generaInserciones());

		return vector; 
	}


	public static Vector<TechReport> generaPub(Vector<Object[]> v) throws UnimplementedException {
		//"SELECT DISTINCT TEC1.idDoc, TEC1.title, TEC1.type, TEC1.institution, TEC1.address, TEC1.month, TEC1.year, TEC1.note, TEC1.abstract, TEC1.URL, TEC1.user, TEC1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, AUT1.web, EEX1.escrito_o_editado, KEY1.clave, TEC1.type FROM techreport AS TEC1, pertenecea AS PRY1, autoreseditores AS AUT1, escrito_editado_por AS EEX1, tienekey AS KEY1 WHERE PRY1.idDoc = TEC1.idDoc AND EEX1.idDoc = TEC1.idDoc AND AUT1.idAut = EEX1.idPer AND KEY1.idDoc = TEC1.idDoc"
		Vector <TechReport> vector = new Vector <TechReport>();
		if (v == null) return vector;
		for (int i=0; i< v.size();){
			Object[] array = v.get(i);
			int idDoc,id_aut;
			String title,address,type,year;
			String month,number,institution, note, abstracts, URL,user, referencia; 
			String proyecto,n_aut,ap_aut,web_aut,clave;
			LinkedList<AutorEditor> autores,editores;
			Vector<String> proyectos = new Vector<String>();
			Vector<String> claves = new Vector<String>();
			autores = new LinkedList<AutorEditor>();
			editores = new LinkedList<AutorEditor>();
			boolean cambio_pub,escrito_edit;
			cambio_pub = false;			
			idDoc = ((Long) array[0]).intValue();
			if (array[1] != null) title = (String) array[1]; else title = null;
			if (array[2] != null) type = (String) array[2]; else type = null;
			if (array[3] != null) institution = (String) array[3]; else institution = null;
			if (array[4] != null) address = (String) array[4]; else address = null;
			if (array[5] != null) month = (String) array[5]; else month = null;
			if (array[6] != null) year = ((String) array[6]); else year = null;						
			if (array[7] != null) note = (String) array[7]; else note = null;
			if (array[8] != null) abstracts = (String) array[8]; else abstracts = null;
			if (array[9] != null) URL = (String) array[9]; else URL = null;
			if (array[10] != null) user = (String) array[10]; else user = null;
			if (array[11] != null) referencia = (String) array[11]; else referencia = null;
			if (array[12] != null) proyecto = (String) array[12]; else proyecto = null;
			id_aut = ((Long) array[13]).intValue();			
			if (array[14] != null) n_aut = (String) array[14]; else n_aut = null;
			if (array[15] != null) ap_aut = (String) array[15]; else ap_aut = null;
			if (array[16] != null) web_aut = (String) array[16]; else web_aut = null;
			escrito_edit = ((Boolean) array[17]).booleanValue();
			if (array[18] != null) clave = (String) array[18]; else clave = null;
			if (array[19] != null) number = (String) array[19]; else number = null;
			AutorEditor autor1 = new AutorEditor(id_aut,n_aut,ap_aut,web_aut);
			if (escrito_edit == true) autores.add(autor1);
			else editores.add(autor1);	
			if (proyecto != null) proyectos.add(proyecto);
			if (clave != null) claves.add(clave);
			TechReport tec1 = new TechReport(idDoc,referencia,title,year,month,URL,abstracts,note,claves,user,proyectos,autores,address,institution,type,number);
			vector.add(tec1);

			// Evaluamos el cambio_pub
			i++;
			if (i>= v.size()) cambio_pub = true;
			else {
				array = v.get(i);		
				idDoc = ((Long) array[0]).intValue();
				if (idDoc != tec1.getIdDoc()) cambio_pub = true;
				else cambio_pub = false;
			}			
			while (!cambio_pub){
				if (array[12] != null) proyecto = (String) array[12]; else proyecto = null;
				id_aut = ((Long) array[13]).intValue();			
				if (array[14] != null) n_aut = (String) array[14]; else n_aut = null;
				if (array[15] != null) ap_aut = (String) array[15]; else ap_aut = null;
				if (array[16] != null) web_aut = (String) array[16]; else web_aut = null;
				escrito_edit = ((Boolean) array[17]).booleanValue();
				if (array[18] != null) clave = (String) array[18]; else clave = null;


				autor1 = new AutorEditor(id_aut,n_aut,ap_aut,web_aut);				
				if (escrito_edit == true) tec1.addAutor(autor1);				


				if (proyecto != null) tec1.addProyect(proyecto);
				if (clave != null) tec1.addKey(clave);

				// Evaluamos el cambio de publicacion
				i++;
				if (i>= v.size()) cambio_pub = true;
				else {
					array = v.get(i);		
					idDoc = ((Long) array[0]).intValue();
					if (idDoc != tec1.getIdDoc()) cambio_pub = true;
					else cambio_pub = false;
				}					
			}							
		}
		return vector;
	}

	public void addAutor(AutorEditor e){
		if (!author.contains(e)) author.add(e);
	}

	public TechReport(int idDoc, String referencia, String title,
			String year, String month, String url, String _abstract,
			String note, Vector<String> key, String user,
			Vector<String> proyectos,LinkedList<AutorEditor> author, 
			String address, String institution,	String type, String number) throws UnimplementedException {
		this.author = author;
		this.address = address;
		this.type = type;
		this.institution = institution;
		this.number = number;
		super.SetAll(idDoc, referencia, title, year, month, url, _abstract, note, key,
				user, proyectos);
	}	
}
