package maker.controlador;

import maker.modelo.IModelo;
import maker.vista.IVista;

public interface IControlador 
{
	public void registrarVista(IVista vista);
	
	public void derregistarVista();
	
	public void registrarModelo(IModelo modelo);
	
	public void derregistarModelo();
	
	public boolean hacer(IHerramienta herramienta);
	
	public boolean deshacer();
	
	public IModelo getModelo();
	
	public IVista getVista();

	public void vaciarPila();
}
