package maker.vista.editor;

import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import maker.modelo.MapaEscenario;

public class VentanaVistaPrevia extends JFrame
{
	private static final long serialVersionUID = 1L;

	public VentanaVistaPrevia(final Editor editor)
	{
		int ancho = ((MapaEscenario)editor.getController().getModelo().getMapa()).getColumnas() * 50;
		int alto = ((MapaEscenario)editor.getController().getModelo().getMapa()).getFilas() * 30 + 25;
		setBounds(512 - ancho/2, 768/2 - alto/2, ancho, alto);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Vista Previa - " + editor.getController().getModelo().getMapa().getNombre());
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent arg0) 
			{
				editor.setEnabled(true);		
			}
		});
		
		PanelVistaPreviaMapa pnvm = new PanelVistaPreviaMapa(editor);
		add(pnvm);
		
		setVisible(true);
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}
	
	public void paint(Graphics g)
	{
		paintComponents(g);
	}
}
