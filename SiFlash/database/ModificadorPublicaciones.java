//Source file: C:\\GENERADO\\database\\ModificadorPublicaciones.java

package database;

import publicaciones.Publication;

/**
 * Clase que se encarga de realizar las modificaciones necesarias en la base 
 * relativas a usuarios
 */
public class ModificadorPublicaciones 
{
   public BaseDatos theBaseDatos;
   
   /**
    * @roseuid 47C8A710038A
    */
   public ModificadorPublicaciones() 
   {
    
   }
   
   /**
    * M�todo que se encarga de insertar la publicaci�n pasada por par�metro en la base 
    * de datos.
    * 
    * Excepcion si ya existe o no hay permisos.
    * @param publicacion - Publicaci�n a insertar.
    * @roseuid 47C5927F006D
    */
   public void insertaPublicaci�n(Publication publicacion) 
   {
    
   }
   
   /**
    * M�todo que se encarga de modificar la publicaci�n pasada por par�metro en la 
    * base de datos, cambia los antiguos datos que conten�a al respecto de la misma 
    * por los que contiene el objeto. Para ello se basa en el idDoc, que no puede 
    * cambiar.
    * 
    * Excepcion si no existe o no hay permisos.
    * @param publicacion - Nuevos datos de la publicaci�n.
    * @throws database.BDException
    * @roseuid 47C59446001F
    */
   public void modificaPublicaci�n(Publication publicacion) throws BDException 
   {
    
   }
   
   /**
    * Elimina la publicaci�n cuyo id se le pasa por par�metro.
    * Para ello la desvincula previamente de todos los proyectos a los que est� 
    * asociada.
    * 
    * Excepcion si no hay permisos.
    * @param id_doc - IdDoc de la publicaci�n que se desea borrar.
    * @throws database.BDException
    * @roseuid 47C59A7002BF
    */
   public void borraPublicaci�n(int id_doc) throws BDException 
   {
    
   }
   
   /**
    * Desasocia la publicacion del proyecto.
    * @param publicacion - Publicaci�n a desasociar.
    * @param proyecto - Proyecto del que desasociar.
    * @throws database.BDException
    * @roseuid 47C59B0E0213
    */
   public void desasociaPublicacion(int publicacion, String proyecto) throws BDException 
   {
    
   }
}
