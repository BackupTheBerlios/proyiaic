package maker.vista.editor.Sonido.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;

import maker.vista.editor.Editor;

public class OyenteReproducir implements ActionListener {

	private Editor editor;
	private JComboBox list;

	public OyenteReproducir(Editor editor, JComboBox list) 
	{
		this.editor = editor;
		this.list = list;
	}

	public void actionPerformed(ActionEvent e) 
	{
		File f = new File(editor.getDirectorio().getAbsolutePath() + "\\Sonidos\\Sonidos MIDI\\" + list.getSelectedItem());
		editor.reproducir(f);
	}

}
