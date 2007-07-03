package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import maker.vista.editor.Editor;
import maker.vista.editor.PanelCreacionMapa;

/**
 * @author  David García
 */
public class OyenteNuevoMapa implements ActionListener 
{
	private Editor editor;

	public OyenteNuevoMapa(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent evento) 
	{	
		PanelCreacionMapa sel = new PanelCreacionMapa(editor);
		int a = JOptionPane.showConfirmDialog(editor, sel, "Nuevo Mapa", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (a == JOptionPane.OK_OPTION) //Opción Ok
		{
			if (sel.getTipoMapa().equals("Escenario"))
			{
				editor.getController().getModelo().crearMapa(sel.getFilas(), sel.getColumnas(), sel.getNombre());
				editor.getController().getModelo().guardar(editor.getDirectorio());
				editor.activarBotonesPintura();
				editor.mostrarPanelTexturas();
				editor.repaint();
			}
			else
				if (sel.getTipoMapa().equals("Caminos"))
				{
					editor.getController().getModelo().crearMapaCaminos(sel.getNombre());
				}
		}
	}
}
