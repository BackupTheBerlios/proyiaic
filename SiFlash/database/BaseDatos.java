//Source file: C:\\GENERADO\\database\\BaseDatos.java

package database;


/**
 * Clase que almacena los parámetros necesarios para conectarse a una base de 
 * datos, así como los métodos que se necesitan para ello.
 */
public class BaseDatos 
{
   
   /**
    * Representa la IP en la que se encuentra el servidor de bases de datos
    */
   private String ip = "80.25.136.115";
   
   /**
    * Nombre de la base de datos, dentro del sistema de gestión de bases de datos, en 
    * la que se encuentra la información.
    */
   private String bd = "ssii";
   
   /**
    * Nombre del usuario de la base de datos.
    */
   private String login = "Sistemas";
   
   /**
    * Contraseña del usuario de la base de datos
    */
   private String password = "123456789";
   
   /**
    * @roseuid 47C8A7100196
    */
   public BaseDatos() 
   {
    
   }
   
   /**
    * Método que genera la URL que representa la base de datos.
    * @return java.lang.String
    * @roseuid 47C493BC0177
    */
   public String URL() 
   {
    return null;
   }
   
   /**
    * Método para probar si se puede conectar correctamente a la base de datos.
    * @return boolean que indica si se ha podido conectar con la base de datos en 
    * cuestión.
    * @roseuid 47C496CA0119
    */
   public boolean testConn() 
   {
    return true;
   }
   
   /**
    * Método para realizar una consulta sobre la base de datos.
    * @param query - String que representa la consulta a realizar.
    * @return vector con las publicaciones resultantes de aplicar la consulta.
    * @roseuid 47C496EF007D
    */
   public Object exeQuery(String query) 
   {
    return null;
   }
   
   /**
    * Método para realizar una modificación sobre la base de datos.
    * @param sentence - String que la operación a realizar.
    * @throws database.BDException
    * @roseuid 47C4988C006D
    */
   public void exeUpdate(String sentence) throws BDException 
   {
    
   }
}
