//Source file: C:\\GENERADO\\usuarios\\Usuario.java

package personas;


/**
 * Clase que representa un usuario del sistema.
 */
public class Usuario 
{
	/**
	 * Representa el usuario.
	 */
	public static final int USUARIO = 0;

	/**
	 * Representa el jefe.
	 */	
	public static final int JEFE = 1;

	/**
	 * Representa el administrador.
	 */	
	public static final int ADMINISTRADOR = 2; 

	/**
	 * Nombre del Usuario
	 */
	private String nombre;

	/**
	 * Contraseña del usuario.
	 */
	private String password;

	/**
	 * Tipo de Usuario
	 */
	private int tipo;     

	/**
	 * @param nombre nombre de Usuario
	 * @param password Contraseña del usuario.
	 * @param tipo Tipo de Usuario.
	 */
	public Usuario(String nombre, String password, int tipo) {
		this.nombre = nombre;
		this.password = password;
		this.tipo = tipo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the tipo
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
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
