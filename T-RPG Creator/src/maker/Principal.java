package maker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

import maker.controlador.Controlador;
import maker.modelo.Modelo;
import maker.vista.Vista;

public class Principal {

	public static void main(String[] args) 
	{
		mostrarLogo();
		
		File path = null;
		StringBuffer acum = new StringBuffer();
		try
		{
			for (int i = 0;;i++)
			{
				acum.append(args[i] + " "); 
			}
			
		}
		catch (ArrayIndexOutOfBoundsException e) 
		{
			if (!acum.toString().equals(""))
			{
				System.out.println(acum);
				path = new File(acum.toString());
			}
		}
		//try 
		/*{
			/*new SubstanceLookAndFeel();
			SubstanceLookAndFeel.setCurrentTheme(new org.jvnet.substance.theme.SubstanceSteelBlueTheme());
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());*/
		//} 
		/*catch (UnsupportedLookAndFeelException e) 
		{
			e.printStackTrace();
		}*/
		
		Controlador con = new Controlador();
		Modelo mod = new Modelo(con);
		con.registrarModelo(mod);
		
		Vista vis;
		if (path == null)
			vis = new Vista(con);
		else
			vis = new Vista(con, path);
		
		con.registrarVista(vis);
		
		vis.mostrar();
		
	}

	private static void mostrarLogo() 
	{
		JWindow win = new JWindow();
		win.setBounds(262, 195, 500, 375);
		win.setLayout(new BorderLayout());
		win.setBackground(Color.gray);
		
		JLabel label = new JLabel();
		ClassLoader cl = Principal.class.getClassLoader();
		label.setIcon(new ImageIcon(cl.getResource("maker/Iconos/LogoTRPG.jpg")));
		
		win.add(label);
		win.setVisible(true);
		
		long milis = System.currentTimeMillis();
		while (System.currentTimeMillis() < (milis + 5000)){}
		
		win.dispose();
	}

}
