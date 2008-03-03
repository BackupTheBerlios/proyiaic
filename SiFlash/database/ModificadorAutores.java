//Source file: C:\\GENERADO\\database\\ModificadorAutores.java

package database;


/**
 * Clase que se encarga de realizar las modificaciones necesarias en la base 
 * relativas a autores/editores.
 */
public class ModificadorAutores 
{
   public BaseDatos theBaseDatos;
   
   /**
    * @roseuid 47C8A711009C
    */
   public ModificadorAutores() 
   {
    
   }
   
   /**
    * Inserta el autor cuyos datos se le pasan por par�metro en la base de datos.
    * 
    * Excepci�n si ya existe o no hay permisos.
    * @param nombre - Nombre del autor.
    * @param apellidos - Apellidos del autor.
    * @param web - Direcci�n de la p�gina web del autor.
    * @throws database.BDException
    * @roseuid 47C5A3B1036B
    */
   public void insertaAutor(String nombre, String apellidos, String web) throws BDException 
   {
    
   }
   
   /**
    * Borra el autor cuyos datos se le pasan por par�metro de la base de datos, 
    * desvinculandolo previamente de todas sus operaciones.
    * 
    * Excepcion si no se tienen permisos.
    * @param nombre - Nombre del autor.
    * @param apellidos - Apellidos del autor.
    * @roseuid 47C5A4F702CE
    */
   public void borraAutor(String nombre, String apellidos) 
   {
    
   }
   
   /**
    * @roseuid 47C5A5EA034B
    */
   public void modificaAutor() 
   {
    
   }
}
