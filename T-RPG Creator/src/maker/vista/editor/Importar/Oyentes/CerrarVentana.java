package maker.vista.editor.Importar.Oyentes;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import maker.vista.editor.Editor;

/**
 * @author  David García
 */
public class CerrarVentana extends WindowAdapter 
{
	private Editor ventana;

	public CerrarVentana(Editor ventana) 
	{
		this.ventana = ventana;
	}

	public void windowClosing(WindowEvent arg0) 
	{
		ventana.setEnabled(true);
	}
}
