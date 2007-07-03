package juego;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import maker.modelo.recursos.Textura;



public class Escenario implements Painter 
{
	private int longit;
	private int anchura;

	private int[][] escenario;

	private Point centrada;

	private int geit = 0;

	private int[][] heights;

	public Escenario(int lon, int anch, int[][] esc, int[][] alturas)
	{
		longit = lon;
		anchura = anch;
		centrada = new Point(lon/2, anch/2);
		escenario = esc;
		heights = alturas; 
	}

	public void dibujar(Graphics g) 
	{
		/*BufferedImage buffer = new BufferedImage(1024,768,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = buffer.createGraphics();
		g2.setPaint(new GradientPaint(512,0, Color.blue, 512,768, Color.black));
		g2.fillRect(0,0,1024,768);
		for (int i = 0; i < longit; i++)
			for (int j = anchura - 1; j >= 0; j--)
			{ 
				if (!escenario[i][j].getPath_superficie().equals(""))
				{
					escenario[i][j].precargar();
					geit = heights[i][j];
					int anch = escenario[i][j].getTexturas().getSup().getWidth(null);
					int altura = escenario[i][j].getTexturas().getSup().getHeight(null);
					BufferedImage imagen = escenario[i][j].getTexturas().getSup();
					// Pintamos la parte de arriba
					
					if (centrada.x == i && centrada.y == j)
						g2.setPaint(new GradientPaint(0,0, Color.RED, 10, 15, Color.white, true));
					else
						g2.setPaint(new TexturePaint(imagen, new Rectangle(anch,altura)));

					Polygon p = polSuperior(i, j);
					g2.fillPolygon(p);
					//g2.setColor(Color.BLACK);
					//g2.drawPolygon(p);
					
					if (j == 0 || heights[i][j - 1] < heights[i][j])
					{
						int anch2 = escenario[i][j].getTexturas().getIzq().getWidth(null);
						int altura2 = escenario[i][j].getTexturas().getIzq().getHeight(null);
						BufferedImage imagen2 = escenario[i][j].getTexturas().getIzq();
						g2.setPaint(new TexturePaint(imagen2, new Rectangle(anch2,altura2)));

						p = polDerecho(i, j);
						g2.fillPolygon(p);
						//g2.setColor(Color.BLACK);
						//g2.drawPolygon(p);
					}
					
					if (i == (longit - 1) || (heights[i][j] > heights[i + 1][j]))
					{
						int anch3 = escenario[i][j].getTexturas().getDer().getWidth(null);
						int altura3 = escenario[i][j].getTexturas().getDer().getHeight(null);
						BufferedImage imagen3 = escenario[i][j].getTexturas().getDer();
						g2.setPaint(new TexturePaint(imagen3, new Rectangle(anch3,altura3)));

						p = polIzquierdo(i, j);
						g2.fillPolygon(p);
						//g2.setColor(Color.BLACK);
						//g2.drawPolygon(p);
					}
				}
			}
		g.drawImage(buffer, 0, 0, null);*/
	}

	public void moverArriba()
	{
		if (centrada.x > 0)
			centrada.x--;
	}

	public void moverAbajo()
	{
		if (centrada.x < (longit - 1))
			centrada.x++;
	}

	public void moverIzquierda()
	{
		if (centrada.y > 0)
			centrada.y--;
	}

	public void moverDerecha()
	{
		if (centrada.y < (anchura - 1))
			centrada.y++;
	}

	public Polygon polSuperior(int i, int j)
	{
		int[] x = {462  + (i - centrada.x) * 50 + (j - centrada.y) * 50,
				512 + (i - centrada.x) * 50 + (j - centrada.y) * 50, 
				562 + (i - centrada.x) * 50 + (j - centrada.y) * 50, 
				512 + (i - centrada.x) * 50 + (j - centrada.y) * 50};
		int[] y = {(-25 * geit) + 359 + 25 * (i - centrada.x) - 25 * (j - centrada.y),
				(-25 * geit) + 334 + 25 * (i - centrada.x) - 25 * (j - centrada.y),
				(-25 * geit) + 359 + 25 * (i - centrada.x) - 25 * (j - centrada.y),
				(-25 * geit) + 384 + 25 * (i - centrada.x) - 25 * (j - centrada.y)};
		return new Polygon(x, y, 4);
	}

	public Polygon polDerecho(int i, int j)
	{
		int[] x2 = {462 + (i - centrada.x) * 50 + (j - centrada.y) * 50,
				512  + (i - centrada.x) * 50 + (j - centrada.y) * 50,
				512  + (i - centrada.x) * 50 + (j - centrada.y) * 50,
				462  + (i - centrada.x) * 50 + (j - centrada.y) * 50};
		int[] y2= {(-25 * geit) + 359 + 25 * (i - centrada.x) - 25 * (j - centrada.y), 
				(-25 * geit) + 384 + 25 * (i - centrada.x) - 25 * (j - centrada.y),
				434 + 25 * (i - centrada.x) - 25* (j - centrada.y),
				409 + 25 * (i - centrada.x) - 25* (j - centrada.y)};
		return new Polygon(x2, y2, 4);
	}

	public Polygon polIzquierdo(int i, int j)
	{
		int[] x3 = {562 + (i - centrada.x) * 50 + (j - centrada.y) * 50,
				512 + (i - centrada.x) * 50 + (j - centrada.y) * 50, 
				512 + (i - centrada.x) * 50 + (j - centrada.y) * 50, 
				562 + (i - centrada.x) * 50 + (j - centrada.y) * 50};
		int[] y3= {(-25 * geit) + 359 + 25 * (i - centrada.x) - 25 * (j - centrada.y),
				(-25 * geit) + 384 + 25 * (i - centrada.x) - 25 * (j - centrada.y),
				434 + 25 * (i - centrada.x) - 25 * (j - centrada.y), 
				409 + 25 * (i - centrada.x) - 25 * (j - centrada.y)};
		return new Polygon(x3, y3, 4);
	}
}
