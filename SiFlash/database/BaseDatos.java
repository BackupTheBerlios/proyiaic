//Source file: C:\\GENERADO\\database\\BaseDatos.java

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


/**
 * Clase que almacena los parámetros necesarios para conectarse a una base de 
 * datos, así como los métodos que se necesitan para operar facilmente sobre ella.
 */
public class BaseDatos 
{

	/**
	 * Representa la IP en la que se encuentra el servidor de bases de datos
	 */
	private String ip;

	/**
	 * Nombre de la base de datos(schema), dentro del sistema de gestión de bases de datos, 
	 * en la que se encuentra la información.
	 */
	private String bd;


	/**
	 * Nombre del usuario para conectar a la base de datos.
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
	 * Constructor por defecto de la base de datos.
	 * Contiene los parámetros del servidor actual en uso.
	 */
	public BaseDatos(){
		//Datos pc Luis.
//		ip = "80.25.136.115";
//		bd = "ssii";
//		login = "Sistemas";
//		password = "123456789";

		//Datos pc David.
//		ip = "81.34.73.17:3306";
//		bd = "ssii";
//		login = "Sistemas";
//		password = "123456789";

		//Datos pc Alfonso.
		ip = "localhost:3306";
		bd = "ssii";
		login = "Sistemas";
		password = "123456789";
	}

	/**
	 * Método que genera la URL para el conector jdbc que representa la base de datos.
	 * @return String - URL para realizar la conexión mediante jdbc.
	 */
	private String URL() 
	{
		return new String ("jdbc:mysql://"+ip+"/"+bd);
	}

	/**
	 * Método para probar si se puede conectar correctamente a la base de datos.
	 * @return boolean - Indica si se ha podido conectar con la base de datos en 
	 * cuestión.
	 * @throws BDException  - Posible excepción no prevista en la prueba.
	 */
	public boolean testConn() throws BDException 
	{
		Connection conn = abreConexion();

		if (conn != null)
		{
			System.out.println("Conexión a base de datos "+URL()+" ... Ok");
			cierraConexion(conn);
			return true;
		}
		return false;
	}

	/**
	 * Método para realizar una consulta sobre la base de datos.
	 * @param query - String que representa la consulta a realizar.
	 * @return Array de arrays de Objects, con los datos contenidos en la base de datos
	 * referentes a esta consulta.
	 * @throws BDException - Error en la operación.
	 */
	public Vector<Object[]> exeQuery(String query, Connection conn) throws BDException 
	{
		try
		{
			boolean conexionLocal = conn == null;
			if (conexionLocal)
				conn = abreConexion();

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
				if (conexionLocal)
					cierraConexion(conn);
				return vector;
			}
		}
		catch(SQLException ex)
		{
			throw new BDException("Fallo al ejecutar la consulta en la base de datos: " + ex.getMessage());
		}
		return null;					
	}

	/**
	 * Método para realizar una modificación sobre la base de datos.
	 * @param conn - Objeto de la clase Connection, debe ser una conexión ya establecida
	 * con la base de datos, de tal forma que se agiliza bastante el proceso.
	 * @param sentence - String que representa la operación a realizar.
	 * @throws database.BDException Si se produce un error al realizar la modificación.
	 */
	public void exeUpdates(Vector<String> sentences, Connection conn) throws BDException 
	{
		//Connection conn = null;
		try
		{
			boolean conexionLocal = conn == null;
			if (conexionLocal)
				conn = abreConexion();
			if (conn != null)
			{
				Statement stmt = conn.createStatement();
				if (sentences != null){
					for (int i=0;i<sentences.size();i++) 
						stmt.executeUpdate(sentences.get(i));
				}				
				stmt.close();
				if (conexionLocal)
					conn.close();
			}
		}
		catch(SQLException ex)
		{
			throw new BDException("Fallo al ejecutar la modificiación en la base de datos: " + ex.getMessage());
		}   
	}   

	/**
	 * Método para realizar una modificación sobre la base de datos.
	 * @param sentence - String que la operación a realizar.
	 * @param conn - Objeto de la clase Connection, debe ser una conexión ya establecida
	 * con la base de datos, de tal forma que se agiliza bastante el proceso.
	 * @throws BDException - Si se produce un error al realizar la modificación.
	 */
	public void exeUpdate(String sentence, Connection conn) throws BDException 
	{
		try
		{
			boolean conexionLocal = conn == null;
			if (conexionLocal)
				conn = abreConexion();

			if (conn != null)
			{
				Statement stmt = conn.createStatement();		
				stmt.executeUpdate(sentence);
				stmt.close();
				if (conexionLocal)
					conn.close();
			}
		}
		catch(SQLException ex)
		{
			throw new BDException("Fallo al ejecutar la modificiación en la base de datos."  + ex.getMessage());
		} 
	}

	/**
	 * Método para establecer una conexión con la base de datos.
	 * @return Objeto de la clase Connection, representa la conexión establecida.
	 * @throws BDException - Si se produce algún error en el intento de creación de la
	 * conexión.
	 */
	public Connection abreConexion() throws BDException 
	{
		Connection conn = null;	   
		try 
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			}
			catch (InstantiationException e) {
				throw new BDException("Fallo al instanciar el driver de la conexion.");
			} catch (IllegalAccessException e) {
				throw new BDException("Fallo al intentar acceder al driver de la conexión.");
			}
			conn = DriverManager.getConnection(URL(),login,password);
			return conn;
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


	/**
	 * Método para cerrar una la conexión establecida.
	 * @param conn - Conexion que se desea cerrar.
	 */
	public void cierraConexion(Connection conn) 
	{
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
