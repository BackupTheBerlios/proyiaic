package maker.vista;

import maker.controlador.IControlador;

public interface IVista 
{
	public void registrarControlador(IControlador controller);
	
	public IControlador getControlador();
	
	public void actualizarVista();
	
	public void mostrar();
}
