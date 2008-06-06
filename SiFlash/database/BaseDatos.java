//Source file: C:\\GENERADO\\database\\BaseDatos.java

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


/**
 * Clase que almacena los par�metros necesarios para conectarse a una base de 
 * datos, as� como los m�todos que se necesitan para operar facilmente sobre ella.
 */
public class BaseDatos 
{

	/**
	 * Representa la IP en la que se encuentra el servidor de bases de datos
	 */
	private String ip;

	/**
	 * Nombre de la base de datos(schema), dentro del sistema de gesti�n de bases de datos, 
	 * en la que se encuentra la informaci�n.
	 */
	private String bd;


	/**
	 * Nombre del usuario para conectar a la base de datos.
	 */
	private String login;

	/**
	 * Contrase�a del usuario de la base de datos
	 */
	private String password;


	/**
	 * Constructor de la base de datos, proporcionandole los parametros.
	 * @param ip - IP del servidor de la base de datos.
	 * @param bd - Nombre del schema de la base de datos.
	 * @param login - Nombre de usuario de la base de datos. 
	 * @param password - COntrase�a del usuario.
	 */
	public BaseDatos(String ip, String bd, String login, String password) {
		this.ip = ip;
		this.bd = bd;
		this.login = login;
		this.password = password;
	}

	/**
	 * Constructor por defecto de la base de datos.
	 * Contiene los par�metros del servidor actual en uso.
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
	 * M�todo que genera la URL para el conector jdbc que representa la base de datos.
	 * @return String - URL para realizar la conexi�n mediante jdbc.
	 */
	private String URL() 
	{
		return new String ("jdbc:mysql://"+ip+"/"+bd);
	}

	/**
	 * M�todo para probar si se puede conectar correctamente a la base de datos.
	 * @return boolean - Indica si se ha podido conectar con la base de datos en 
	 * cuesti�n.
	 * @throws BDException  - Posible excepci�n no prevista en la prueba.
	 */
	public boolean testConn() throws BDException 
	{
		Connection conn = abreConexion();

		if (conn != null)
		{
			System.out.println("Conexi�n a base de datos "+URL()+" ... Ok");
			cierraConexion(conn);
			return true;
		}
		return false;
	}

	/**
	 * M�todo para realizar una consulta sobre la base de datos.
	 * @param query - String que representa la consulta a realizar.
	 * @return Array de arrays de Objects, con los datos contenidos en la base de datos
	 * referentes a esta consulta.
	 * @throws BDException - Error en la operaci�n.
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
	 * M�todo para realizar una modificaci�n sobre la base de datos.
	 * @param conn - Objeto de la clase Connection, debe ser una conexi�n ya establecida
	 * con la base de datos, de tal forma que se agiliza bastante el proceso.
	 * @param sentence - String que representa la operaci�n a realizar.
	 * @throws database.BDException Si se produce un error al realizar la modificaci�n.
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
			throw new BDException("Fallo al ejecutar la modificiaci�n en la base de datos: " + ex.getMessage());
		}   
	}   

	/**
	 * M�todo para realizar una modificaci�n sobre la base de datos.
	 * @param sentence - String que la operaci�n a realizar.
	 * @param conn - Objeto de la clase Connection, debe ser una conexi�n ya establecida
	 * con la base de datos, de tal forma que se agiliza bastante el proceso.
	 * @throws BDException - Si se produce un error al realizar la modificaci�n.
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
			throw new BDException("Fallo al ejecutar la modificiaci�n en la base de datos."  + ex.getMessage());
		} 
	}

	/**
	 * M�todo para establecer una conexi�n con la base de datos.
	 * @return Objeto de la clase Connection, representa la conexi�n establecida.
	 * @throws BDException - Si se produce alg�n error en el intento de creaci�n de la
	 * conexi�n.
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
				throw new BDException("Fallo al intentar acceder al driver de la conexi�n.");
			}
			conn = DriverManager.getConnection(URL(),login,password);
			return conn;
		}
		catch(SQLException ex)
		{
			throw new BDException("Fallo al ejecutar la modificiaci�n en la base de datos."  + ex.getMessage());
		}
		catch(ClassNotFoundException ex)
		{
			throw new BDException("Fallo al buscar el driver. ");
		}
	}


	/**
	 * M�todo para cerrar una la conexi�n establecida.
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
