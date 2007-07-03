package maker.controlador.herramientas.mapa_escenario;

import maker.controlador.IControlador;
import maker.controlador.IHerramienta;
import maker.modelo.MapaEscenario;

public class HerramientaPintarCasilla implements IHerramienta 
{	
	private int altura;
	private int pos_y;
	private int pos_x;
	private int old_altura;
	private int textura;

	public HerramientaPintarCasilla(int x, int y, int alt) 
	{
		pos_x = x;
		pos_y = y;
		altura = alt;
	}

	public void deshacer(IControlador controler) 
	{
		MapaEscenario mapaEscenario = (MapaEscenario)controler.getModelo().getMapa();
		mapaEscenario.setTextura(pos_x, pos_y, old_altura, textura);
	}

	public boolean hacer(IControlador controler) 
	{
		MapaEscenario mapaEscenario = (MapaEscenario)controler.getModelo().getMapa();
		if (pos_x >= 0 && pos_x < mapaEscenario.getFilas() && pos_y < mapaEscenario.getColumnas() && pos_y >= 0)
		{
			old_altura = mapaEscenario.getAltura(pos_x, pos_y);
			textura = mapaEscenario.getTextura(pos_x,pos_y);
			mapaEscenario.pintar(pos_x, pos_y, altura);
			return true;
		}
		return false;
	}

}
