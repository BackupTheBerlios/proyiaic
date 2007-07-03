package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import juego.Ventana;
import maker.modelo.MapaEscenario;
import maker.vista.editor.Editor;

public class OyentePlay implements ActionListener 
{
	private Editor editor;

	public OyentePlay(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		MapaEscenario mapaEscenario = (MapaEscenario)editor.getController().getModelo().getMapa();
		Ventana ven = new Ventana(mapaEscenario.getMapa_logico(), mapaEscenario.getAlturas(), mapaEscenario.getFilas(), mapaEscenario.getColumnas());
		ven.setVisible(true);
	}

}
