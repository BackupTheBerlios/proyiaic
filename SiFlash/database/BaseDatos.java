//Source file: C:\\GENERADO\\database\\BaseDatos.java

package database;


/**
 * Clase que almacena los par�metros necesarios para conectarse a una base de 
 * datos, as� como los m�todos que se necesitan para ello.
 */
public class BaseDatos 
{
   
   /**
    * Representa la IP en la que se encuentra el servidor de bases de datos
    */
   private String ip = "80.25.136.115";
   
   /**
    * Nombre de la base de datos, dentro del sistema de gesti�n de bases de datos, en 
    * la que se encuentra la informaci�n.
    */
   private String bd = "ssii";
   
   /**
    * Nombre del usuario de la base de datos.
    */
   private String login = "Sistemas";
   
   /**
    * Contrase�a del usuario de la base de datos
    */
   private String password = "123456789";
   
   /**
    * @roseuid 47C8A7100196
    */
   public BaseDatos() 
   {
    
   }
   
   /**
    * M�todo que genera la URL que representa la base de datos.
    * @return java.lang.String
    * @roseuid 47C493BC0177
    */
   public String URL() 
   {
    return null;
   }
   
   /**
    * M�todo para probar si se puede conectar correctamente a la base de datos.
    * @return boolean que indica si se ha podido conectar con la base de datos en 
    * cuesti�n.
    * @roseuid 47C496CA0119
    */
   public boolean testConn() 
   {
    return true;
   }
   
   /**
    * M�todo para realizar una consulta sobre la base de datos.
    * @param query - String que representa la consulta a realizar.
    * @return vector con las publicaciones resultantes de aplicar la consulta.
    * @roseuid 47C496EF007D
    */
   public Object exeQuery(String query) 
   {
    return null;
   }
   
   /**
    * M�todo para realizar una modificaci�n sobre la base de datos.
    * @param sentence - String que la operaci�n a realizar.
    * @throws database.BDException
    * @roseuid 47C4988C006D
    */
   public void exeUpdate(String sentence) throws BDException 
   {
    
   }
}
