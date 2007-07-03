package maker.vista.editor;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import maker.modelo.MapaEscenario;
import maker.modelo.recursos.Textura;
import maker.recursos.SistemaCarga;



public class PanelVistaPreviaMapa extends JPanel
{
	private static final long serialVersionUID = 1L;

	private int geit = 0;

	private Editor editor;

	public PanelVistaPreviaMapa(Editor editor)
	{
		this.editor = editor;


	}

	public void update(Graphics g)
	{
		paint(g);
	}

	public void paint(Graphics g) 
	{

		MapaEscenario mapaEscenario = (MapaEscenario)editor.getController().getModelo().getMapa();
		
		BufferedImage buffer = new BufferedImage(100 * mapaEscenario.getColumnas(), 60 * mapaEscenario.getFilas() ,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = buffer.createGraphics();
		g2.setPaint(new GradientPaint(512,0, Color.blue, 512,768, Color.black));
		g2.fillRect(0,0,2000,1200);

		for (int i = 0; i < mapaEscenario.getFilas(); i++)
			for (int j = mapaEscenario.getColumnas() - 1; j >= 0; j--)
			{ 
				int textura = mapaEscenario.getTextura(i, j);
				if (textura >= 0)
				{
					Textura tex = (Textura)editor.getSistemacarga().getRecurso(SistemaCarga.TEXTURAS, textura);
					geit = mapaEscenario.getAltura(i, j);
					BufferedImage imagen = tex.getTexturas().getSup();
					
					// Pintamos la parte de arriba
					g2.setPaint(new TexturePaint(imagen, new Rectangle(imagen.getWidth(), imagen.getHeight())));

					Polygon p = polSuperior(i, j);
					g2.fillPolygon(p);
					//g2.setColor(Color.BLACK);
					//g2.drawPolygon(p);

					/*if (j == 0 || heights[i][j - 1] < heights[i][j])
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
					}*/
				}
			}
		g.drawImage(buffer, 0, 0, 50 * mapaEscenario.getColumnas(), 30 * mapaEscenario.getFilas(), null);
	}

	public Polygon polSuperior(int i, int j)
	{
		int[] x = {i * 50 + j * 50,
				50 + i * 50 + j * 50, 
				100 + i * 50 + j * 50, 
				50 + i * 50 + j * 50};
		int[] y = {(-25 * geit) + 512 + 25 * i - 25 * j,
				(-25 * geit) + 487 + 25 * i - 25 * j,
				(-25 * geit) + 512 + 25 * i - 25 * j,
				(-25 * geit) + 537 + 25 * i - 25 * j};
		return new Polygon(x, y, 4);
	}

	/*public Polygon polDerecho(int i, int j)
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
	}*/
}
