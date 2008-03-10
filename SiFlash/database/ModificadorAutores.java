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
    * Inserta el autor cuyos datos se le pasan por parámetro en la base de datos.
    * 
    * Excepción si ya existe o no hay permisos.
    * @param nombre - Nombre del autor.
    * @param apellidos - Apellidos del autor.
    * @param web - Dirección de la página web del autor.
    * @throws database.BDException
    * @roseuid 47C5A3B1036B
    */
   public void insertaAutor(String nombre, String apellidos, String web) throws BDException 
   {
	   String consulta = "INSERT INTO Autores VALUES (" + nombre + ", " + apellidos + ", " + web + ");";
	   theBaseDatos.exeUpdate(consulta);
   }
   
   /**
    * Borra el autor cuyos datos se le pasan por parámetro de la base de datos, 
    * desvinculandolo previamente de todas sus operaciones.
    * 
    * Excepcion si no se tienen permisos.
    * @param nombre - Nombre del autor.
    * @param apellidos - Apellidos del autor.
    * @throws BDException 
    * @roseuid 47C5A4F702CE
    */
   public void borraAutor(String nombre, String apellidos) throws BDException  
   {
	   String consulta = "DELETE FROM Autores WHERE nombre = " + nombre + " and apellidos = " + apellidos + ";";
	   theBaseDatos.exeUpdate(consulta);   
   }
   
   /**
    * @throws BDException 
 * @roseuid 47C5A5EA034B
    */
   public void modificaAutor(String nombreActual, String apellidosActual, String nombreNuevo, String apellidosNuevos, String urlNueva) throws BDException 
   {
	   String consulta = "UPDATE Autores SET nombre = " + nombreNuevo + ", apellidos = " + apellidosNuevos + ", url = " + urlNueva +
	   		"WHERE nombre = " + nombreActual + " and apellidos = " + apellidosActual + ";" ;
	   theBaseDatos.exeUpdate(consulta);    
   }
}
