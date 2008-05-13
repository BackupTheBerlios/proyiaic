//Source file: C:\\GENERADO\\database\\BaseDatos.java

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import controlador.exceptions.ConnectionException;


/**
 * Clase que almacena los parámetros necesarios para conectarse a una base de 
 * datos, así como los métodos que se necesitan para ello.
 */
public class BaseDatos 
{
   
   /**
    * Representa la IP en la que se encuentra el servidor de bases de datos
    */
   private String ip;
   
   /**
    * Nombre de la base de datos, dentro del sistema de gestión de bases de datos, en 
    * la que se encuentra la información.
    */
   private String bd;
   
   /**
    * Nombre del usuario de la base de datos.
    */
   private String login;
   
   /**
    * Contraseña del usuario de la base de datos
    */
   private String password;
   
   
   /**
    * Constructor de la base de datos, proporcionandole los parametros.
 * @param ip - IP del servidor de la base de datos.
 * @param bd - Nombre del schema de la base de datos.
 * @param login - Nombre de usuario de la base de datos. 
 * @param password - COntraseña del usuario.
 */
   public BaseDatos(String ip, String bd, String login, String password) {
	this.ip = ip;
	this.bd = bd;
	this.login = login;
	this.password = password;
   }

/**
    * @roseuid 47C8A7100196
    * Constructor por defecto de la base de datos.
    * Contiene los parámetros del servidor actual en uso.
    */
   public BaseDatos(){
	   //Datos pc Luis.
	   ip = "80.25.136.115";
	   bd = "ssii";
	   login = "Sistemas";
	   password = "123456789";
	   
	   //Datos pc David.
//	   ip = "83.45.30.174:3306";
//	   bd = "ssii";
//	   login = "Sistemas";
//	   password = "123456789";
	   
	   //Datos pc Alfonso.
//	   ip = "192.168.1.165:3306";
//	   bd = "ssii";
//	   login = "Sistemas";
//	   password = "123456789";
   }
   
   /**
    * Método que genera la URL que representa la base de datos.
    * @return java.lang.String
    * @roseuid 47C493BC0177
    */
   private String URL() 
   {
    return new String ("jdbc:mysql://"+ip+"/"+bd);
   }
   
   /**
    * Método para probar si se puede conectar correctamente a la base de datos.
    * @return boolean que indica si se ha podido conectar con la base de datos en 
    * cuestión.
    * @roseuid 47C496CA0119
    */
   public boolean testConn() 
   {
		Connection conn = null;
		try
		{	   
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				return false;
			} catch (IllegalAccessException e) {
				return false;
			}
			
			conn = DriverManager.getConnection(URL(),login,password);
			
			if (conn != null)
			{
				System.out.println("Conexión a base de datos "+URL()+" ... Ok");
				conn.close();
				return true;
			}
		}
		catch(SQLException ex)
		{
			return false;
		}
		catch(ClassNotFoundException ex)
		{
			return false;
		}
		return false;   
   }
   
   /**
    * Método para realizar una consulta sobre la base de datos.
    * @param query - String que representa la consulta a realizar.
    * @return matriz de Objects con los datos contenidos en la base de datos.
    * @throws BDException 
    * @roseuid 47C496EF007D
    */
   public Vector<Object[]> exeQuery(String query) throws BDException 
   {
	   Connection conn = null;
		try
		{
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				throw new BDException("Fallo al instanciar el driver de la conexion.");
			} catch (IllegalAccessException e) {
				throw new BDException("Fallo al intentar acceder al driver de la conexión.");
			}
			try{
				conn = DriverManager.getConnection(URL(),login,password);
			} catch (Exception e){
				throw new ConnectionException();
			}
			
			
			
			if (conn != null)
			{
				Statement stmt = conn.createStatement();			
				ResultSet res  = stmt.executeQuery(query);
				Vector<Object[]> vector = new Vector<Object[]>();
				while(res.next())
				{
					Object[] fila = new Object[res.getMetaData().getColumnCount()];
					for (int i = 1; i<= res.getMetaData().getColumnCount(); i++){
						fila[i-1] = res.getObject(i);
					}								
					vector.add(fila);					
				}
				res.close();
				stmt.close();
				conn.close();	
				return vector;
			}
		}
		catch(SQLException ex)
		{
			throw new BDException("Fallo al ejecutar la consulta en la base de datos: " + ex.getMessage());
		}
		catch(ClassNotFoundException ex)
		{
			throw new BDException("Fallo al buscar el driver. ");
		}
		return null;					
   }
   
   /**
    * Método para realizar una modificación sobre la base de datos.
    * @param sentence - String que la operación a realizar.
    * @throws database.BDException Si se produce un error al realizar la modificación.
    * @roseuid 47C4988C006D
    */
   public void exeUpdates(Vector<String> sentences) throws BDException 
   {
	   Connection conn = null;
		try
		{
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				throw new BDException("Fallo al instanciar el driver de la conexion.");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				throw new BDException("Fallo al intentar acceder al driver de la conexión.");
			}
			conn = DriverManager.getConnection(URL(),login,password);
			
			
			if (conn != null)
			{
				Statement stmt = conn.createStatement();
				if (sentences != null){
					for (int i=0;i<sentences.size();i++) 
						stmt.executeUpdate(sentences.get(i));
				}				
				stmt.close();
				conn.close();
			}
		}
		catch(SQLException ex)
		{
			throw new BDException("Fallo al ejecutar la modificiación en la base de datos: " + ex.getMessage());
		}
		catch(ClassNotFoundException ex)
		{
			throw new BDException("Fallo al buscar el driver. ");
		}	    
   }   
   
   /**
    * Método para realizar una modificación sobre la base de datos.
    * @param sentence - String que la operación a realizar.
    * @throws database.BDException Si se produce un error al realizar la modificación.
    * @roseuid 47C4988C006D
    */
   public void exeUpdate(String sentence) throws BDException 
   {
	   Connection conn = null;
		try
		{
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				throw new BDException("Fallo al instanciar el driver de la conexion.");
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				throw new BDException("Fallo al intentar acceder al driver de la conexión.");
			}
			conn = DriverManager.getConnection(URL(),login,password);
			
			
			if (conn != null)
			{
				Statement stmt = conn.createStatement();		
				stmt.executeUpdate(sentence);
				stmt.close();
				conn.close();
			}
		}
		catch(SQLException ex)
		{
			throw new BDException("Fallo al ejecutar la modificiación en la base de datos."  + ex.getMessage());
		}
		catch(ClassNotFoundException ex)
		{
			throw new BDException("Fallo al buscar el driver. ");
		}	    
   }
}
