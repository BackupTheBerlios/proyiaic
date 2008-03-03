//Source file: C:\\GENERADO\\database\\ModificadorProyectos.java

package database;


/**
 * Clase que se encarga de realizar las modificaciones necesarias en la base 
 * relativas a proyectos.
 */
public class ModificadorProyectos 
{
   public BaseDatos theBaseDatos;
   
   /**
    * @roseuid 47C8A711002E
    */
   public ModificadorProyectos() 
   {
    
   }
   
   /**
    * M�todo para a�adir un proyecto a la aplicaci�n.
    * 
    * Excepcion si no se cuentan con permisos.
    * @param nombre_proyecto - Nombre del proyecto a a�adir.
    * @param nombre_administrador - Nombre del administrador del proyecto.
    * @throws database.BDException
    * @roseuid 47C5A0FC005D
    */
   public void insetaProyecto(String nombre_proyecto, String nombre_administrador) throws BDException 
   {
    
   }
   
   /**
    * Cambia el administrador del proyecto al nuevo pasado por par�metro.
    * 
    * @param nombre_proyecto - Nombre del proyecto a modificar.
    * @param nuevo_administrador - Nuevo administrador del proyecto.
    * @return nombre del antiguo administrador.@throws database.BDException
    * @roseuid 47C5A19C03D8
    */
   public String setAdmin(String nombre_proyecto, String nuevo_administrador) throws BDException 
   {
    return null;
   }
   
   /**
    * Borra el proyecto de la aplicaci�n.
    * @param proyecto - Nombre del proyecto a borrar
    * @roseuid 47C5A25B0290
    */
   public void borraProyecto(String proyecto) 
   {
    
   }
}
