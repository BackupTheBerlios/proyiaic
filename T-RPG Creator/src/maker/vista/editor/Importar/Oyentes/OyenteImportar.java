package maker.vista.editor.Importar.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;

import maker.modelo.ficheros.Copiar;
import maker.vista.editor.Importar.Filtros.ImageFilter;
import maker.vista.editor.Importar.Filtros.MidiFilter;
import maker.vista.editor.Importar.Filtros.PNGFilter;

public class OyenteImportar implements ActionListener
{
	private JList listaCat;
	private File dir;
	private DefaultListModel dlmArchivo;

	public OyenteImportar(JList listaCat, File dir, DefaultListModel dlmArchivo) 
	{
		this.listaCat = listaCat;
		this.dir = dir;
		this.dlmArchivo = dlmArchivo;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		String rutadef = (String) listaCat.getSelectedValue();
		String rutainter = "";
		JFileChooser jfc = new JFileChooser();
		if (rutadef.equals("Objetos"))
		{
			jfc.setFileFilter(new PNGFilter());
			rutainter = "Imagenes";
		}
		if (rutadef.equals("Texturas") || rutadef.equals("Caras de Personaje"))
		{
			jfc.setFileFilter(new ImageFilter());
			rutainter = "Imagenes";
		}
		if (rutadef.equals("Sonidos MIDI"))
		{
			jfc.setFileFilter(new MidiFilter());
			rutainter = "Sonidos";
		}
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setMultiSelectionEnabled(true);
		jfc.setAcceptAllFileFilterUsed(false);
		if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(null))
		{
			File[] fuef = jfc.getSelectedFiles();
			for (int i = 0; i < fuef.length; i++)
			{
				File desf = new File(dir.getAbsolutePath() + "\\" + rutainter + "\\" + rutadef + "\\" + fuef[i].getName());					
				Copiar cp = new Copiar();
				try 
				{
					cp.copy(fuef[i], desf);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				dlmArchivo.addElement(fuef[i].getName());
			}
		}
	}
}
