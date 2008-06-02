//Source file: C:\\GENERADO\\publicaciones\\Misc.java

package publicaciones;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import org.jdom.Element;

import parserFicherosBibtex.Campo;
import parserFicherosBibtex.CampoPublicacion;
import parserFicherosBibtex.CampoPublicacionAutorEditor;
import personas.AutorEditor;
import controlador.DataBaseControler;
import controlador.exceptions.ExistingElementException;
import database.BDException;


/**
 * Para uso cuando los demás tipos no corresponden.
 */
public class Misc extends Publication 
{
	/**
	 * LinkedList que contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;

	/**
	 * Forma en la que ha sido publicado.
	 */
	private String howPublished;

	/**
	 * Crea un Misc a partir de una lista de campos.
	 * @param campos Campos a partir de los cuales se quiere crear el objeto.
	 */
	public Misc(LinkedList<Campo> campos)
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
		else if (nombreCampo.equalsIgnoreCase("howpublished") && howPublished == null)
			howPublished = valorString;
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
		elemento.setAttribute ("tipo", "Misc");
		if (idDoc != 0)
			elemento.setAttribute("idDoc", ""+idDoc);
		if (referencia != null)
			elemento.setAttribute("referencia", referencia);

		Element eTitle = new Element("title");
		eTitle.addContent(title);
		elemento.addContent(eTitle);

		Element eAuthor = generarAutoresEditoresXML();
		elemento.addContent(eAuthor);

		Element eHowPublished = new Element("howPublished");
		eHowPublished.addContent(howPublished);
		elemento.addContent(eHowPublished);

		Element eMonth = new Element("month");
		eMonth.addContent(month);
		elemento.addContent(eMonth);

		Element eYear = new Element("year");
		eYear.addContent(year);
		elemento.addContent(eYear);

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
		String bibtex = "@misc{";
		if (referencia != null)
			bibtex += referencia;
		bibtex += "\n";
		if (title != null)
			bibtex += "\ttitle={" + convertirTextoBibtex(title) + "},\n";
		if (year != null)
			bibtex += "\tyear={" + convertirTextoBibtex(year) + "},\n";
		if (month != null)
			bibtex += "\tmonth={" + convertirTextoBibtex(month) + "},\n";
		if (author != null)
			bibtex += "\tauthor={" + convertirTextoBibtex(author) + "},\n";
		if (howPublished != null)
			bibtex += "\thowPublished={" + convertirTextoBibtex(howPublished) + "},\n";
		if (_abstract != null)
			bibtex += "\tabstract={" + convertirTextoBibtex(_abstract) + "},\n";
		if (note != null)
			bibtex += "\tnote={" + convertirTextoBibtex(note) + "},\n";
		if (key != null)
			bibtex += "\tkey={" + convertirTextoBibtexKeys(key) + "},\n";
		if (bibtex.charAt(bibtex.length()-2) == ',') //Sobra la última coma.
			bibtex = bibtex.substring(0, bibtex.length()-2);
		bibtex += "}";
		
		return bibtex;
	}

	public LinkedList<AutorEditor> getAuthor() {
		return author;
	}

	public String getHowPublished() {
		return howPublished;
	}

	@Override
	public Vector<String> generaInserciones(Connection conn) throws BDException, ExistingElementException {
		idDoc = 0;
		Vector <String> vector = new Vector <String>();
		String str1 = new String ("INSERT INTO misc VALUES (");
		str1 += Integer.toString(getIdDoc());
		
		if (getTitle()!= null)
		str1 += ",'" + getTitle() + "'";
		else str1+= ",null";
		
		if (getHowPublished()!= null)
		str1 += ",'" + getHowPublished() + "'";
		else str1+= ",null";

		if(getMonth()!=null)
		str1 += ",'" + getMonth() + "'";
		else str1+= ",null";
				
		if (getYear()!=null)
		str1 += ",'" + getYear() + "'";
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
		dbc.ejecutaString("BEGIN;", conn); //Comenzar transacción.
		dbc.ejecutaString(str1, conn);
		idDoc = dbc.consultaIdDoc(conn);	
		
//		str1 = new String ("INSERT INTO tipopublicacion VALUES (" + getIdDoc() + ",'misc');");
//		vector.add(str1);

		if (author != null)	
			for (int i=0;i<this.author.size();i++){
				int idAutor = author.get(i).getId();
				String str;
				if (idAutor == 0) //Hay que insertarlo
				{
					idAutor = dbc.consultaIdAutor(author.get(i).getNombre(), author.get(i).getApellidos(), conn);
					if (idAutor == 0)
					{
						dbc.insertaAutorEditor(author.get(i), conn);
						idAutor = dbc.consultaIdAutor(author.get(i).getNombre(), author.get(i).getApellidos(), conn);
					}
				}
				str = new String ("INSERT INTO escrito_editado_por VALUES(" + getIdDoc());
				str += "," + idAutor + ",TRUE);";
				vector.add(str);
			}

		vector.addAll(super.generaInserciones(conn));
			
		return vector; 
	}

	public static Vector<Misc> generaPub(Vector<Object[]> v) {
		//"SELECT DISTINCT MISC1.idDoc, MISC1.title, MISC1.howpublished, MISC1.month, MISC1.year, MISC1.note, MISC1.abstract, MISC1.URL, MISC1.user, MISC1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM misc AS MISC1, pertenecea AS PRY1, autoreseditores AS AUT1, escrito_editado_por AS EEX1, tienekey AS KEY1 WHERE PRY1.idDoc = MISC1.idDoc AND EEX1.idDoc = MISC1.idDoc AND AUT1.idAut = EEX1.idPer AND KEY1.idDoc = MISC1.idDoc"
		Vector <Misc> vector = new Vector <Misc>();
		if (v == null) return vector;
		for (int i=0; i< v.size();){
			Object[] array = v.get(i);
			int idDoc,id_aut;
			String title, month,howpublished,note, abstracts, URL,user, referencia; 
			String proyecto,n_aut,ap_aut,clave,year;
			LinkedList<AutorEditor> autores,editores;
//			Vector<String> proyectos = new Vector<String>();
			Vector<String> claves = new Vector<String>();
			autores = new LinkedList<AutorEditor>();
			editores = new LinkedList<AutorEditor>();
			boolean cambio_pub,escrito_edit;
			cambio_pub = false;			
			idDoc = ((Long) array[0]).intValue();
			if (array[1] != null) title = (String) array[1]; else title = null;
			if (array[2] != null) howpublished = (String) array[2]; else howpublished = null;			
			if (array[3] != null) month = (String) array[3]; else month = null;
			if (array[4] != null) year = ((String) array[4]); else year = null;						
			if (array[5] != null) note = (String) array[5]; else note = null;
			if (array[6] != null) abstracts = (String) array[6]; else abstracts = null;
			if (array[7] != null) URL = (String) array[7]; else URL = null;
			if (array[8] != null) user = (String) array[8]; else user = null;
			if (array[9] != null) referencia = (String) array[9]; else referencia = null;
			if (array[10] != null) proyecto = (String) array[10]; else proyecto = null;
			id_aut = ((Long) array[11]).intValue();			
			if (array[12] != null) n_aut = (String) array[12]; else n_aut = null;
			if (array[13] != null) ap_aut = (String) array[13]; else ap_aut = null;
			escrito_edit = ((Boolean) array[14]).booleanValue();
			if (array[15] != null) clave = (String) array[15]; else clave = null;
			AutorEditor autor1 = new AutorEditor(id_aut,n_aut,ap_aut);
			if (escrito_edit == true) autores.add(autor1);
			else editores.add(autor1);	
//			if (proyecto != null) proyectos.add(proyecto);
			if (clave != null) claves.add(clave);
			Misc misc1 = new Misc(idDoc,referencia,title,year,month,URL,abstracts,note,claves,user,proyecto,autores,howpublished);
			vector.add(misc1);

			// Evaluamos el cambio_pub
			i++;
			if (i>= v.size()) cambio_pub = true;
			else {
				array = v.get(i);		
				idDoc = ((Long) array[0]).intValue();
				if (idDoc != misc1.getIdDoc()) cambio_pub = true;
				else cambio_pub = false;
			}			
			while (!cambio_pub){
				if (array[10] != null) proyecto = (String) array[10]; else proyecto = null;
				id_aut = ((Long) array[11]).intValue();			
				if (array[12] != null) n_aut = (String) array[12]; else n_aut = null;
				if (array[13] != null) ap_aut = (String) array[13]; else ap_aut = null;
				escrito_edit = ((Boolean) array[14]).booleanValue();
				if (array[15] != null) clave = (String) array[15]; else clave = null;


				autor1 = new AutorEditor(id_aut,n_aut,ap_aut);				
				if (escrito_edit == true) misc1.addAutor(autor1);				


//				if (proyecto != null) misc1.addProyect(proyecto);
				if (clave != null) misc1.addKey(clave);

				// Evaluamos el cambio de publicacion
				i++;
				if (i>= v.size()) cambio_pub = true;
				else {
					array = v.get(i);		
					idDoc = ((Long) array[0]).intValue();
					if (idDoc != misc1.getIdDoc()) cambio_pub = true;
					else cambio_pub = false;
				}					
			}							
		}
		return vector;
	}

	public void addAutor(AutorEditor e){
		if (!author.contains(e)) author.add(e);
	}

	public Misc(int idDoc, String referencia, String title,
			String year, String month, String url, String _abstract,
			String note, Vector<String> key, String user,
			String proyecto,LinkedList<AutorEditor> author, 
			String howpublished) {
		this.author = author;
		this.howPublished = howpublished;
		super.SetAll(idDoc, referencia, title, year, month, url, _abstract, note, key,
				user, proyecto);
	}	
}
