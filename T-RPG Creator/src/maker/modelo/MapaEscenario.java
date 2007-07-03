package maker.modelo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.io.Serializable;

import maker.modelo.recursos.Textura;
import maker.recursos.SistemaCarga;
import maker.vista.editor.PanelMapa.Selección;


/**
 * @author  David García
 */
public class MapaEscenario extends MapaAbstracto implements Serializable
{
	private static final long serialVersionUID = -5162045390208958577L;

	private int[][] alturas;

	private int columnas;

	private int filas;

	private int[][] mapaTexturas;

	private int tactual;

	public MapaEscenario(int fil, int column, String nombre)
	{
		identificador = id++;
		this.nombre = nombre;
		this.filas = fil;
		this.columnas = column;
		mapaTexturas = new int[filas][];
		for (int i = 0; i < filas; i++) 
		{
			mapaTexturas[i] = new int[columnas];
			for (int j = 0; j < columnas; j++) 
			{
				mapaTexturas[i][j] = -1;
			}
		}
		alturas = new int[filas][];
		for (int i = 0; i < filas; i++) 
		{
			alturas[i] = new int[columnas];
			for (int j = 0; j < columnas; j++) 
			{
				alturas[i][j] = -1;
			}
		}
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		MapaAbstracto.id = id;
	}

	public int getIdentificador() {
		return identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void dibujar(Graphics2D g2, Point seleccionada, SistemaCarga carga, Selección sel) {
		for (int i = 0; i < getFilas(); i++) 
			for (int j = 0; j < getColumnas(); j++)
			{
				int textura = getTextura(i, j);
				if (textura >= 0)
				{
					Textura tex = (Textura) carga.getRecurso(SistemaCarga.TEXTURAS, textura);
					if (tex != null && tex.getTexturas() != null)
						g2.setPaint(new TexturePaint(tex.getTexturas().getSup(), new Rectangle(tex.getTexturas().getSup().getWidth(), tex.getTexturas().getSup().getHeight())));
					else
						g2.setPaint(Color.WHITE);
				}
				else
					g2.setPaint(Color.WHITE);
				g2.fillRect(2 + i * 32, 2 + j * 32, 32, 32);
				g2.setPaint(Color.BLACK);
				g2.drawRect(2 + i * 32, 2 + j * 32, 32, 32);
				g2.setPaint(Color.YELLOW);
				g2.drawString("" + getAltura(i,j), 14 + i * 32, 14 + j * 32);
			}
		if (seleccionada.x >= 0 && seleccionada.x < getFilas() && seleccionada.y >= 0 && seleccionada.y < getColumnas())
		{
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke(3));
			g2.drawRect(seleccionada.x * 32 + 3, seleccionada.y * 32 + 3, 30, 30);
	
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(1));
			g2.drawRect(seleccionada.x * 32 + 3, seleccionada.y * 32 + 3, 30, 30);
		}
		if (!(sel.ancho == 0 || sel.largo == 0))
		{
			Rectangle s = new Rectangle(sel.init_x * 32 + 3, sel.init_y * 32 + 3, sel.ancho * 32, sel.largo * 32);
			Color colorTransparente = new Color((float) 0.1, (float) 0.8, (float) 0.05, (float) 0.2);
			g2.setColor(colorTransparente);
			g2.fill(s);
			g2.setColor(Color.GREEN);
			g2.draw(s);
		}
	}
	public int getAltura(int i, int j) 
	{
		return alturas[i][conversion(j)];
	}

	/**
	 * @return  the alturas
	 */
	public int[][] getAlturas() {
		return alturas;
	}

	/**
	 * @return  the columnas
	 */
	public int getColumnas() {
		return columnas;
	}

	/**
	 * @return  the filas
	 */
	public int getFilas() {
		return filas;
	}

	/**
	 * @return  the mapa_logico
	 */
	public int[][] getMapa_logico() {
		return mapaTexturas;
	}

	public int getTextura(int i, int j) 
	{
		return mapaTexturas[i][conversion(j)];
	}

	public void pintar(int x, int y, int altura) 
	{
		if (x >= 0 && x < filas && y < columnas && y >= 0)
		{	
			int nuevo_y = conversion(y);
			mapaTexturas[x][nuevo_y] = tactual;
			alturas[x][nuevo_y] = altura;
		}
	}

	/**
	 * @param alturas  the alturas to set
	 */
	public void setAlturas(int[][] alturas) {
		this.alturas = alturas;
	}

	/**
	 * @param columnas  the columnas to set
	 */
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	/**
	 * @param filas  the filas to set
	 */
	public void setFilas(int filas) {
		this.filas = filas;
	}

	/**
	 * @param mapa_logico  the mapa_logico to set
	 */
	public void setMapa_logico(int[][] mapa_logico) {
		this.mapaTexturas = mapa_logico;
	}

	public void setTextura(int text) 
	{
		tactual = text;
	}

	public void setTextura(int i, int j, int altura, int tex) 
	{
		if (i >= 0 && i < filas && j < columnas && j >= 0)
		{
			int nuevo_j = conversion(j);
			mapaTexturas[i][nuevo_j] = tex;
			alturas[i][nuevo_j] = altura;
		}
	}

	private int conversion(int y) 
	{
		return (columnas - y - 1);
	}
}
