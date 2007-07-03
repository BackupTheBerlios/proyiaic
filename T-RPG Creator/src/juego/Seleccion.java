package juego;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

/**
 * @author  David García
 */
public class Seleccion extends Thread
{
	private int seleccion;

	private Ventana menu;
	
	private int diferencia;
	private int total;

	private boolean vivo; 

	public Seleccion(Ventana menú)
	{
		seleccion = 0;
		menu = menú;
		diferencia = 5;
		total = 0;
		vivo = true;
	}

	public void subir()
	{
		seleccion--;
		if (seleccion < 0)
			seleccion = 2;
	}

	public void bajar()
	{
		seleccion++;
		if (seleccion > 2)
			seleccion = 0;
	}

	public void run()
	{
		try 
		{
			while (vivo)
			{
				sleep(20);
				total += diferencia;
				if (total > 100)
				{
					diferencia = -5;
					total = 100;
				}
				if (total < 0)
				{
					diferencia = 5;
					total = 0;
				}
				menu.repaint();
			}
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	public void pintarSeleccion(Graphics2D g1) 
	{
		GradientPaint gp = new GradientPaint(0, 0, new Color(total, total, total), 800, 600, new Color(255, total, total));
		g1.setPaint(gp);
		g1.fillRect(332, 470 + 60 * seleccion, 350, 40);
		g1.setColor(Color.white);
		g1.drawRect(332, 470 + 60 * seleccion, 350, 40);
		g1.drawRect(333, 471 + 60 * seleccion, 348, 38);
	}

	public int getseleccion() 
	{
		return seleccion;
	}
	
	public void aniquilar()
	{
		vivo = false;
	}
}
