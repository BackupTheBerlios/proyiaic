//Source file: C:\\GENERADO\\publicaciones\\BookLet.java

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
 * Clase que representa una obra que está impresa y encuadernada (bound), pero sin 
 * una editorial o institución patrocinadora (sponsoring).
 * Contiene todos sus posibles campos, así como los métodos necesarios
 * para su correcto manejo.
 */
public class Booklet extends Publication 
{
	/**
	 * Contiene los autores que han colaborado en la creación de la misma.
	 */
	private LinkedList<AutorEditor> author;

	/**
	 * Forma en la que ha sido publicado.
	 */
	private String howPublished;

	/**
	 * Lugar de publicación.
	 */
	private String address;

	/**
	 * Crea un Booklet a partir de una lista de campos.
	 * @param campos Campos a partir de los cuales se quiere crear el objeto.
	 */
	public Booklet(LinkedList<Campo> campos)
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
		if (nombreCampo.equalsIgnoreCase("title") && title == null)
			title = valorString;
		else if (nombreCampo.equalsIgnoreCase("idDoc") && idDoc == 0)
			idDoc = Integer.parseInt(valorString);
		else if (nombreCampo.equalsIgnoreCase("referencia") && referencia == null)
			referencia = valorString;
		else if (nombreCampo.equalsIgnoreCase("author") && author == null)
			author = extraerAutoresEditores(valorString);
		else if (nombreCampo.equalsIgnoreCase("howpublished") && howPublished == null)
			howPublished = valorString;
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
		else if (nombreCampo.equalsIgnoreCase("user") && user == null)
			user = valorString;
		else if (nombreCampo.equalsIgnoreCase("url") && URL == null)
			URL = valorString;
		else if (nombreCampo.equalsIgnoreCase("proyecto") && proyecto == null)
			proyecto = valorString;
	}

	/**
	 * Establece el valor de un atributo(lista) del documento.
	 * @param nombreCampo Nombre del campo que se quiere insertar.
	 * @param valor Lista con los valores del campo que se quiere insertar.
	 */		
	private void insertar(String nombreCampo, LinkedList<AutorEditor> valor) 
	{
		if (nombreCampo.equalsIgnoreCase("authors") && author == null)
			author = valor;
	}

	@Override
	public Element generarElementoXML(boolean quitarLlaves)
	{
		Element elemento = new Element("publication");
		elemento.setAttribute ("tipo", "Booklet");
		if (idDoc != 0)
			elemento.setAttribute("idDoc", ""+idDoc);
		if (referencia != null)
			elemento.setAttribute("referencia", quitarLlaves(referencia, quitarLlaves));

		Element eTitle = new Element("title");
		eTitle.addContent(quitarLlaves(title, quitarLlaves));
		elemento.addContent(eTitle);

		Element eAuthor = generarAutoresEditoresXML(quitarLlaves);
		elemento.addContent(eAuthor);

		Element eHowPublished = new Element("howPublished");
		eHowPublished.addContent(quitarLlaves(howPublished, quitarLlaves));
		elemento.addContent(eHowPublished);

		Element eAddress = new Element("address");
		eAddress.addContent(quitarLlaves(address, quitarLlaves));
		elemento.addContent(eAddress);

		Element eMonth = new Element("month");
		eMonth.addContent(quitarLlaves(month, quitarLlaves));
		elemento.addContent(eMonth);

		Element eYear = new Element("year");
		eYear.addContent(quitarLlaves(year, quitarLlaves));
		elemento.addContent(eYear);

		Element eNote = new Element("note");
		eNote.addContent(quitarLlaves(note, quitarLlaves));
		elemento.addContent(eNote);

		Element eAbstract = new Element("abstract");
		eAbstract.addContent(quitarLlaves(_abstract, quitarLlaves));
		elemento.addContent(eAbstract);

		Element eKey = new Element("key");
		eKey.addContent(quitarLlaves(convertirTextoBibtexKeys(key), quitarLlaves));
		elemento.addContent(eKey);
		
		Element eURL = new Element("URL");
		eURL.addContent(URL);
		elemento.addContent(eURL);

		return elemento;
	}

	/**
	 * Genera un elemento XML con todos los autores.
	 * @param quitarLlaves Indica si se quieren quitar las llaves que aparezcan en alguno de los campos de la publicación.
	 * @return El elemento generado.
	 */
	private Element generarAutoresEditoresXML(boolean quitarLlaves) 
	{	
		Element eAuthor = new Element("authors");
		if (author != null)
		{
			Iterator<AutorEditor> it = author.iterator();
			while (it.hasNext())
				eAuthor.addContent(it.next().generarAuthorXML(quitarLlaves));
		}
		return eAuthor;
	}

	@Override
	public String getBibTeX() 
	{
		String bibtex = "@booklet{";
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
		if (address != null)
			bibtex += "\taddress={" + convertirTextoBibtex(address) + "},\n";
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

	/**
	 * @return LinkedList con los autores del documento.
	 */
	public LinkedList<AutorEditor> getAuthor() {
		return author;
	}

	/**
	 * @return La forma en la que ha sido publicado.
	 */
	public String getHowPublished() {
		return howPublished;
	}

	/**
	 * @return El address del documento.
	 */
	public String getAddress() {
		return address;
	}

	@Override
	public Vector<String> generaInserciones(Connection conn) throws BDException, ExistingElementException 
	{
		Vector <String> vector = new Vector <String>();
		String str1 = new String ("INSERT INTO booklet VALUES (");
		str1 += Integer.toString(getIdDoc());
		
		if (getTitle()!= null)
		str1 += ",\"" + sustituirComillasSQL(getTitle()) + "\"";
		else str1+= ",null";
		
		if (getHowPublished()!=null)
		str1 += ",\"" + sustituirComillasSQL(getHowPublished()) + "\"";
		else str1+= ",null";
		
		if(getAddress()!=null)
			str1 += ",\"" + sustituirComillasSQL(getAddress()) + "\"";
			else str1+= ",null";		
		
		if(getMonth()!=null)
			str1 += ",\"" + sustituirComillasSQL(getMonth()) + "\"";
			else str1+= ",null";		
		
		if (getYear()!=null)
		str1 += ",\"" + sustituirComillasSQL(getYear()) + "\"";
		else str1+= ",null";		
		
		if(getNote()!=null)
		str1 += ",\"" + sustituirComillasSQL(getNote()) + "\"";
		else str1+= ",null";
		
		if(get_abstract()!=null)
		str1 += ",\"" + sustituirComillasSQL(get_abstract()) + "\"";
		else str1+= ",null";
		
		if(getURL()!=null)
		str1 += ",\"" + sustituirComillasSQL(getURL()) + "\"";
		else str1+= ",null";
		
		if(getUser()!=null)
		str1 += ",\"" + sustituirComillasSQL(getUser()) + "\"";
		else str1+= ",null";
		
		if(getReferencia()!=null)
		str1 += ",\"" + sustituirComillasSQL(getReferencia()) + "\"";
		else str1+= ",null";
		
		str1+=");";
			
		DataBaseControler dbc = new DataBaseControler();
		dbc.ejecutaString(str1, conn);
		if (idDoc == 0)
			idDoc = dbc.consultaIdDoc(conn);	

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

	/**
	 * Genera una conjunto de Booklet a partir del resultado obtenido al realizar una consulta 
	 * en la base de datos.
	 * @param v Resultado obtenido por una consulta a la base de datos, cada array de Object 
	 * contenido en  representa una fila del resultado obtenido al consultar la base de datos.
	 * @return Vector de documentos resultante.
	 */	
	public static Vector<Booklet> generaPub(Vector<Object[]> v) {
		//"SELECT DISTINCT BOOK1.idDoc, BOOK1.title, BOOK1.howpublished, BOOK1.address, BOOK1.month, BOOK1.year, BOOK1.note, BOOK1.abstract, BOOK1.URL, BOOK1.user, BOOK1.referencia, PRY1.proyecto, AUT1.idAut, AUT1.nombre, AUT1.apellidos, EEX1.escrito_o_editado, KEY1.clave FROM bookleet AS BOOK1, pertenecea AS PRY1, autoreseditores AS AUT1, escrito_editado_por AS EEX1, tienekey AS KEY1 WHERE PRY1.idDoc = BOOK1.idDoc AND EEX1.idDoc = BOOK1.idDoc AND AUT1.idAut = EEX1.idPer AND KEY1.idDoc = BOOK1.idDoc"
		Vector <Booklet> vector = new Vector <Booklet>();
		if (v == null) return vector;
		for (int i=0; i< v.size();){
			Object[] array = v.get(i);
			int idDoc,id_aut;
			String title,address,year;
			String month,howpublished,note, abstracts, URL,user, referencia; 
			String proyecto,n_aut,ap_aut,clave;
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
			if (array[3] != null) address = (String) array[3]; else address = null;
			if (array[4] != null) month = (String) array[4]; else month = null;
			if (array[5] != null) year = ((String) array[5]); else year = null;						
			if (array[6] != null) note = (String) array[6]; else note = null;
			if (array[7] != null) abstracts = (String) array[7]; else abstracts = null;
			if (array[8] != null) URL = (String) array[8]; else URL = null;
			if (array[9] != null) user = (String) array[9]; else user = null;
			if (array[10] != null) referencia = (String) array[10]; else referencia = null;
			if (array[11] != null) proyecto = (String) array[11]; else proyecto = null;
			id_aut = ((Long) array[12]).intValue();			
			if (array[13] != null) n_aut = (String) array[13]; else n_aut = null;
			if (array[14] != null) ap_aut = (String) array[14]; else ap_aut = null;
			escrito_edit = ((Boolean) array[15]).booleanValue();
			if (array[16] != null) clave = (String) array[16]; else clave = null;
			AutorEditor autor1 = new AutorEditor(id_aut,n_aut,ap_aut);
			if (escrito_edit == true) autores.add(autor1);
			else editores.add(autor1);	
//			if (proyecto != null) proyectos.add(proyecto);
			if (clave != null) claves.add(clave);
			if (autores.isEmpty()) autores = null;
			if (editores.isEmpty()) editores = null;
			Booklet bkl1 = new Booklet(idDoc,referencia,title,year,month,URL,abstracts,note,claves,user,proyecto,autores,address,howpublished);
			vector.add(bkl1);

			// Evaluamos el cambio_pub
			i++;
			if (i>= v.size()) cambio_pub = true;
			else {
				array = v.get(i);		
				idDoc = ((Long) array[0]).intValue();
				if (idDoc != bkl1.getIdDoc()) cambio_pub = true;
				else cambio_pub = false;
			}			
			while (!cambio_pub){
				if (array[11] != null) proyecto = (String) array[11]; else proyecto = null;
				id_aut = ((Long) array[12]).intValue();			
				if (array[13] != null) n_aut = (String) array[13]; else n_aut = null;
				if (array[14] != null) ap_aut = (String) array[14]; else ap_aut = null;
				escrito_edit = ((Boolean) array[15]).booleanValue();
				if (array[16] != null) clave = (String) array[16]; else clave = null;


				autor1 = new AutorEditor(id_aut,n_aut,ap_aut);				
				if (escrito_edit == true) bkl1.addAutor(autor1);				


//				if (proyecto != null) bkl1.addProyect(proyecto);
				if (clave != null) bkl1.addKey(clave);

				// Evaluamos el cambio de publicacion
				i++;
				if (i>= v.size()) cambio_pub = true;
				else {
					array = v.get(i);		
					idDoc = ((Long) array[0]).intValue();
					if (idDoc != bkl1.getIdDoc()) cambio_pub = true;
					else cambio_pub = false;
				}					
			}							
		}
		return vector;
	}

	/**
	 * Añade un autor al documento.
	 * @param e Autor a añadir.
	 */
	public void addAutor(AutorEditor e){
		if (author == null)
			author = new LinkedList<AutorEditor>();
		if (!author.contains(e)) author.add(e);
	}

	/**
	 * Constructor de la clase dados sus atributos.
	 * @param idDoc Identificador del documento.
	 * @param referencia Referencia del documento.
	 * @param title Título del documento.
	 * @param year Año del documento.
	 * @param month Mes del documento.
	 * @param url Dirección URL del documento.
	 * @param _abstract Abstract del documento.
	 * @param note Nota del documento.
	 * @param key Conjunto de keywords del documento.
	 * @param user Usuario que ha subido el documento.
	 * @param proyecto Proyectos a los que pertenece el documento.
	 * @param author Autores del documento.
	 * @param address Dirección que le corresponde al documento.
	 * @param howpublished Forma en la que ha sido publicado.		
	 */	
	public Booklet(int idDoc, String referencia, String title,
			String year, String month, String url, String _abstract,
			String note, Vector<String> key, String user,
			String proyecto,LinkedList<AutorEditor> author, 
			String address, String howpublished) {
		this.author = author;
		this.address = address;
		this.howPublished = howpublished;
		super.SetAll(idDoc, referencia, title, year, month, url, _abstract, note, key,
				user, proyecto);
	}	
	
	@Override
	public String obligatoriosRellenos()
	{
		if (title == null)
			return "Error: 'title' es un campo obligatorio.";
		else if (URL == null)
			return "Error: 'URL' es un campo obligatorio.";
		else 
			return null;
	}
}
