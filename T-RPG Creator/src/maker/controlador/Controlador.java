package maker.controlador;

import java.util.Stack;
import maker.modelo.IModelo;
import maker.vista.IVista;

/**
 * @author  David García
 */
public class Controlador implements IControlador
{
	private IVista vista;
	
	private IModelo modelo;

	private Stack<IHerramienta> pila;

	public Controlador() 
	{
		this.pila = new Stack<IHerramienta>();
	}

	public void derregistarModelo() 
	{
		modelo = null;
	}

	public void derregistarVista() 
	{
		vista = null;
	}

	public boolean hacer(IHerramienta herramienta) 
	{
		boolean exito = herramienta.hacer(this);
		if (exito)
			pila.push(herramienta);
		if (pila.size() > 0)
			return true;
		else
			return false;
	}

	public void registrarModelo(IModelo modelo) 
	{
		this.modelo = modelo;
	}

	public void registrarVista(IVista vista) 
	{
		this.vista = vista;
	}

	public boolean deshacer() 
	{
		if (pila.size() > 0)
			pila.pop().deshacer(this);
		if (pila.size() == 0)
			return false;
		else return true;
	}

	/**
	 * @return  the modelo
	 */
	public IModelo getModelo() {
		return modelo;
	}

	/**
	 * @return  the vista
	 */
	public IVista getVista() {
		return vista;
	}

	public void vaciarPila() 
	{
		pila.clear();
	}

}
