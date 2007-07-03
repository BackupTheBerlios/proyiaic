package maker.vista;

import java.io.File;

import maker.controlador.Controlador;
import maker.controlador.IControlador;
import maker.vista.editor.Editor;

/**
 * @author  David García
 */
public class Vista implements IVista 
{
	Editor ed;
	
	private IControlador controller;

	public Vista(IControlador controller)
	{
		this.controller = controller;
		ed = new Editor(controller, null);
	}
	
	public Vista(Controlador con, File path) 
	{
		this.controller = con;
		ed = new Editor(con, path);
	}

	public IControlador getControlador() 
	{
		return controller;
	}

	public void registrarControlador(IControlador controller) 
	{
		this.controller = controller;
	}

	public void actualizarVista() 
	{
		
	}

	public void mostrar() 
	{
		ed.mostrar();
	}
}
