package maker.controlador.herramientas.mapa_escenario;

import maker.controlador.IControlador;
import maker.controlador.IHerramienta;

public class HerramientaDeshacerLinea implements IHerramienta {

	private int pintados;

	public HerramientaDeshacerLinea(int pintados) 
	{
		this.pintados = pintados;
	}

	public void deshacer(IControlador controler) 
	{
		for (int i = 0; i < pintados; i++)
			controler.deshacer();
	}

	public boolean hacer(IControlador controler) 
	{
		//No hace nada, ya que la herramienta solo sirve para deshacer
		return true;
	}

}
