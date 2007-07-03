package maker.vista.editor.Sonido.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import maker.vista.editor.Editor;
import maker.vista.editor.Sonido.PanelSonido;

public class OyenteSalir implements ActionListener {

	private PanelSonido sonido;
	private Editor editor;

	public OyenteSalir(PanelSonido sonido, Editor editor) 
	{
		this.sonido = sonido;
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent evento) 
	{
		editor.setEnabled(true);
		sonido.dispose();
	}

}
