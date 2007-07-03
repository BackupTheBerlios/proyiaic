package maker.controlador.herramientas.mapa_escenario;

import maker.controlador.IControlador;
import maker.controlador.IHerramienta;
import maker.modelo.MapaEscenario;

public class HerramientaBorrarCasilla implements IHerramienta 
{

	private int pos_y;
	private int pos_x;
	private int old_altura;
	private int textura;

	public HerramientaBorrarCasilla(int x, int y) 
	{
		pos_x = x;
		pos_y = y;
	}

	public void deshacer(IControlador controler) 
	{
		MapaEscenario mapaEscenario = (MapaEscenario)controler.getModelo().getMapa();
		mapaEscenario.setTextura(pos_x, pos_y, old_altura, textura);
	}

	public boolean hacer(IControlador controler) 
	{
		MapaEscenario mapaEscenario = (MapaEscenario)controler.getModelo().getMapa();
		old_altura = mapaEscenario.getAltura(pos_x, pos_y);
		textura = mapaEscenario.getTextura(pos_x,pos_y);
		mapaEscenario.setTextura(pos_x, pos_y, -1, -1);
		return true;
	}


}
