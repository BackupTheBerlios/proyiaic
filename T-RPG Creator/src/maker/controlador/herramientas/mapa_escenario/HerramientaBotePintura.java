package maker.controlador.herramientas.mapa_escenario;

import java.util.Iterator;
import java.util.LinkedList;

import maker.controlador.IControlador;
import maker.controlador.IHerramienta;
import maker.modelo.MapaEscenario;

public class HerramientaBotePintura implements IHerramienta 
{
	class Casilla
	{
		int x;
		int y;
		int altura;
		int textura;

		public Casilla(int x, int y, int textura, int altura) {
			super();
			this.x = x;
			this.y = y;
			this.altura = altura;
			this.textura = textura;
		}
	}
	
	private int x;
	private int altura;
	private int y;
	private LinkedList<Casilla> cambios;
	
	public HerramientaBotePintura(int x, int y, int altura) 
	{
		this.x = x;
		this.y = y;
		this.altura = altura;
	}

	public void deshacer(IControlador controler) 
	{
		Iterator<Casilla> iter = cambios.iterator();
		while (iter.hasNext())
		{
			Casilla cas = iter.next();
			((MapaEscenario)controler.getModelo().getMapa()).setTextura(cas.x, cas.y, cas.altura, cas.textura);
		}
	}

	public boolean hacer(IControlador controler) 
	{
		cambios = new LinkedList<Casilla>();
		MapaEscenario mapaEscenario = (MapaEscenario)controler.getModelo().getMapa();
		
		int filas = mapaEscenario.getFilas();
		int columnas = mapaEscenario.getColumnas();
		
		boolean visitado[][] = new boolean[filas][];
		for (int i = 0; i < visitado.length; i++) 
		{
			visitado[i] = new boolean[columnas];
			for (int j = 0; j < visitado[i].length; j++) 
			{
				visitado[i][j] = false;
			}
		}
		
		int textura = mapaEscenario.getTextura(x, y);
		int altura = mapaEscenario.getAltura(x, y);
		
		expandir(textura, altura, visitado, x, y, mapaEscenario);
		return true;
	}

	private void expandir(int textura2, int altura2, boolean[][] visitado, int x2, int y2, MapaEscenario mapaEscenario) 
	{
		if ((mapaEscenario.getTextura(x2, y2) == textura2) && (mapaEscenario.getAltura(x2, y2) == altura2) && !visitado[x2][y2])
		{
			Casilla cas = new Casilla(x2,y2,mapaEscenario.getTextura(x2, y2),mapaEscenario.getAltura(x2, y2));
			cambios.add(cas);
			mapaEscenario.pintar(x2, y2, altura);
			visitado[x2][y2] = true;
			if (x2 > 0)
				expandir(textura2, altura2, visitado, x2 - 1, y2, mapaEscenario);
			if (x2 < mapaEscenario.getFilas() - 1)
				expandir(textura2, altura2, visitado, x2 + 1, y2, mapaEscenario);
			if (y2 > 0)
				expandir(textura2, altura2, visitado, x2, y2 - 1, mapaEscenario);
			if (y2 < mapaEscenario.getColumnas() - 1)
				expandir(textura2, altura2, visitado, x2, y2 + 1, mapaEscenario);
		}
	}
}














