//Source file: C:\\GENERADO\\publicaciones\\Codigos.java

package publicaciones;



/**
 * Clase que almacena las equivalencias entre distintos enteros y el tipo
 * al que representan, se utilizan a la hora de realizar las búsquedas.
 */
public class CodigosDatos 
{
	/**
	 * Representa a los autores.
	 */
	public static final int codAutor = 1;
	
	/**
	 * Representa a los editores.
	 */
	public static final int codEditor = 2;
	
	/**
	 * Representa a los Article.
	 */
	public static final int codArticle = 1;
	
	/**
	 * Representa los Book.
	 */
	public static final int codBook = 2;
	
	/**
	 * Representa los Booklet.
	 */
	public static final int codBookLet = 4;
	
	/**
	 * Representa los Conference.
	 */
	public static final int codConference = 8;
	
	/**
	 * Representa los Inbook.
	 */
	public static final int codInbook = 16;
	
	/**
	 * Representa los Incollection.
	 */
	public static final int codIncollection = 32;
	
	/**
	 * Representa los Inproceedings.
	 */
	public static final int codInproceedings = 64;
	
	/**
	 * Representa los Manuals.
	 */
	public static final int codManual = 128;
	
	/**
	 * Representa los MasterThesis.
	 */
	public static final int codMasterThesis = 256;
	
	/**
	 * Representa los Miscs.
	 */
	public static final int codMisc = 512;
	
	/**
	 * Representa los PhdThesis.
	 */
	public static final int codphdThesis = 1024;
	
	/**
	 * Representa los Proceedings.
	 */
	public static final int codProceedings = 2048;
	
	/**
	 * Representa los TechReports.
	 */
	public static final int codTechReport = 4096;
	
	/**
	 * Representa los Unpublisheds.
	 */
	public static final int codUnpublished = 8192;
	
	/**
	 * Representa todos los tipos de documento.
	 */
	public static final int codTodas = 1|2|4|8|16|32|64|128|256|512|1024|2048|4096|8192;
	
	/**
	 * Suma de todos los códigos correspondientes a las publicaciones.
	 */
	public static final int codSumaTodasPublicaciones = codArticle + codBook + codBookLet + codConference + codInbook + codIncollection + codInproceedings + codManual + codMasterThesis + codMisc + codphdThesis + codProceedings + codTechReport + codUnpublished;
	
	/**
	 * Array con los nombres de los meses en castellano ordenados cronológicamente,
	 * comenzando por enero. 
	 */
	public static final String[] listaMeses = {"enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre"};   

}
