package maker.vista.editor.Oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import maker.modelo.recursos.Objeto;
import maker.modelo.recursos.Textura;
import maker.vista.editor.Editor;
import maker.vista.editor.PanelNuevoProyecto;



/**
 * @author  David García
 */
public class OyenteNuevo implements ActionListener 
{
	private Editor editor;
		
	public OyenteNuevo(Editor editor) 
	{
		this.editor = editor;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		PanelNuevoProyecto pnp = new PanelNuevoProyecto();
		JOptionPane.showMessageDialog(null, pnp, "Creando proyecto...", JOptionPane.PLAIN_MESSAGE);
		File ruta = pnp.getRuta();
		String nombre = pnp.getNombre();
		File directory = new File(ruta.getAbsolutePath() + "\\" + nombre); 
		editor.setDirectorio(directory);
		directory.mkdir();
		crearSistemaFicheros(editor.getDirectorio());
		editor.crearSistemaCarga();
		editor.activarBotonesEdición();
	}

	private void crearSistemaFicheros(File opened) 
	{
		File crear = new File(opened.getAbsolutePath() + "\\Imagenes");
		crear.mkdir();
		File obj = new File(crear.getAbsolutePath() + "\\Objetos");
		obj.mkdir();
		obj = new File(crear.getAbsolutePath() + "\\Personajes (Batalla)");
		obj.mkdir();
		obj = new File(crear.getAbsolutePath() + "\\Caras de Personaje");
		obj.mkdir();
		obj = new File(crear.getAbsolutePath() + "\\Texturas");
		obj.mkdir();
		
		File sound = new File(opened.getAbsolutePath() + "\\Sonidos");
		sound.mkdir();
		obj = new File(sound.getAbsolutePath() + "\\Sonidos MIDI");
		obj.mkdir();
		
		crear = new File(opened.getAbsolutePath() + "\\Base de datos");
		crear.mkdir();
		crear = new File(crear.getAbsolutePath() + "\\Objetos.srpgdata");
		LinkedList<Objeto> lista = new LinkedList<Objeto>();
		lista.add(new Objeto("", "", "1x1", false, false, 0));
		lista.add(new Objeto("", "", "1x1", false, false, 0));
		lista.add(new Objeto("", "", "1x1", false, false, 0));
		lista.add(new Objeto("", "", "1x1", false, false, 0));
		lista.add(new Objeto("", "", "1x1", false, false, 0));
		try 
		{
			FileOutputStream fos = new FileOutputStream(crear); 
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lista);
			oos.close();
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		crear = new File(opened.getAbsolutePath() + "\\Base de datos");
		crear = new File(crear.getAbsolutePath() + "\\Texturas.srpgdata");
		LinkedList<Textura> listaTex = new LinkedList<Textura>();
		listaTex.add(new Textura("", false, "", "", ""));
		listaTex.add(new Textura("", false, "", "", ""));
		listaTex.add(new Textura("", false, "", "", ""));
		listaTex.add(new Textura("", false, "", "", ""));
		listaTex.add(new Textura("", false, "", "", ""));
		try 
		{
			FileOutputStream fos = new FileOutputStream(crear); 
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listaTex);
			oos.close();
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		crear = new File(opened.getAbsolutePath() + "\\Mapas");
		crear.mkdir();
		crear = new File(opened.getAbsolutePath() + "\\Varios");
		crear.mkdir();
		File datos = new File(opened.getAbsolutePath() + "\\project_data.srpg");
		try 
		{
			FileWriter fw = new FileWriter(datos);
			fw.write(opened.getAbsolutePath() + "\n");
			// Aqui se escriben las cosas que haya que añadir. 
			fw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
}
