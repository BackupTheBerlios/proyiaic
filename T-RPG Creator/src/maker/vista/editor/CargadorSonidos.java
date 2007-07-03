package maker.vista.editor;

import java.io.File;

import javax.swing.JComboBox;

import maker.vista.editor.Importar.Filtros.MidiFilter;

public class CargadorSonidos 
{
	private Editor editor;
	private JComboBox list;

	public CargadorSonidos(Editor editor, JComboBox sonidos) 
	{
		this.editor = editor;
		this.list = sonidos;
	}

	public void cargarSonidos() 
	{
		File f = new File(editor.getDirectorio().getAbsolutePath() + "\\Sonidos\\Sonidos MIDI");
		File []array = f.listFiles();
		MidiFilter filtro = new MidiFilter();
		int i = 0;
		int fin;
		if (array == null)
			fin = 0;
		else
			fin = array.length;
		try
		{
			while (i < fin)				
			{
				if (filtro.accept(array[i]))
				{
					list.addItem(array[i].getName());
				}
				i++;
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{}
	}
}
