//Source file: C:\\GENERADO\\usuarios\\Usuario.java

package personas;


/**
 * Representa un usuario registrado del sistema.
 */
public class Usuario 
{
	/**
	 * Representa el usuario b�sico.
	 */
	public static final int USUARIO = 0;

	/**
	 * Representa el jefe de proyecto.
	 */	
	public static final int JEFE = 1;

	/**
	 * Representa el administrador de la aplicaci�n.
	 */	
	public static final int ADMINISTRADOR = 2; 

	/**
	 * Nombre del Usuario
	 */
	private String nombre;

	/**
	 * Contrase�a del usuario.
	 */
	private String password;

	/**
	 * Tipo de Usuario.
	 */
	private int tipo;     

	/**
	 * Constructor de la clase dados sus atributos.
	 * @param nombre nombre de Usuario
	 * @param password Contrase�a del usuario.
	 * @param tipo Tipo de Usuario.
	 */
	public Usuario(String nombre, String password, int tipo) {
		this.nombre = nombre;
		this.password = password;
		this.tipo = tipo;
	}

	/**
	 * Devuelve el nombre de usuario.
	 * @return Nombre de usuario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el actual nombre de usuario al que se le pasa por par�metro.
	 * @param Nombre que se le dar� al usuario.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el password del usuario.
	 * @return Password del usuario
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Cambia el password del usuario al que se pasa por par�metro.
	 * @param El password que se pondr� al usuario.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Devuelve el tipo de usuario.
	 * @return Indica el tipo de usuario mediante un string, que
	 * puede ser "user" para el usuario b�sico, "jefe" para el jefe de
	 * proyecto, "admin" para el administrador de la aplicaci�n y null
	 * en caso de incoherencias.
	 */
	public String getTipo() 
	{
		if (tipo == USUARIO)
			return "user";
		else if (tipo == JEFE)
			return "jefe";
		else if (tipo == ADMINISTRADOR)
			return "admin";
		return null;
	}

	/**
	 * Cambia el tipo de usuario al que se le pasa por par�metro. El entero
	 * que se le pasa est� identificado con los atributos USUARIO/JEFE/ADMINISTRADOR.
	 * @param tipo El nuevo tipo de usuario.
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
