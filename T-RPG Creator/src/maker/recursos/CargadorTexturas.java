package maker.recursos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import maker.modelo.recursos.FactoriaRecursosVacios;
import maker.modelo.recursos.Textura;
import maker.vista.editor.Editor;

public class CargadorTexturas implements Cargador
{
	private File rutaTex;
	
	private LinkedList<Textura> lista;
	
	public CargadorTexturas(Editor ed)
	{
		cargarTexturas(ed);
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<Textura> cargarTexturas(Editor editor) 
	{
		rutaTex = new File(editor.getDirectorio().getAbsolutePath() + "\\Base de datos\\Texturas.srpgdata");
		lista = null;
		try 
		{
			FileInputStream fis = new FileInputStream(rutaTex);
			ObjectInputStream ois = new ObjectInputStream(fis);
			lista = (LinkedList<Textura>)ois.readObject();
			Iterator<Textura> iter = lista.iterator();
			while (iter.hasNext())
			{
				iter.next().precargar();
			}
			return lista;
		} 
		catch (IOException e1) 
		{
			JOptionPane.showMessageDialog(editor, "Error al cargar el archivo. No se encuentra o han tenido lugar errores de lectura.", "Error al abrir los objetos.",JOptionPane.ERROR_MESSAGE);
		} 
		catch (ClassNotFoundException e1) 
		{
			JOptionPane.showMessageDialog(editor, "Datos inesperados en el archivo de objetos.", "Error al abrir los objetos.",JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}


	public Object getRecurso(int i) 
	{
		if (i < 0 || i >= lista.size())
			return null;
		return lista.get(i);
	}


	public void modificarTamaño(int n_tamaño) 
	{
		if (n_tamaño < lista.size())
		{}
	}

	public int getTamaño() 
	{
		return lista.size();
	}

	public void setRecurso(int selec, Object tex) 
	{
		if (selec >= 0 && selec < lista.size())
		{ 
			((Textura) tex).precargar();
			lista.add(selec, (Textura) tex);
			lista.remove(selec + 1);
		}
	}

	public void guardar() 
	{
		try 
		{
			Iterator<Textura> iter = lista.iterator();
			while (iter.hasNext())
			{
				iter.next().setTexturas(null);
			}
			FileOutputStream fos = new FileOutputStream(rutaTex);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lista);
			oos.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void setTamaño(int i) 
	{
		if (i < lista.size())
			disminuirTamaño(lista.size()- i);
		else if (i > lista.size())
			aumentarTamaño(i-lista.size());
	}

	private void aumentarTamaño(int i) 
	{
		for (int j = 0; j < i; j++)
			lista.add((Textura) FactoriaRecursosVacios.crearRecursoVacio(FactoriaRecursosVacios.TEXTURA));
	}

	private void disminuirTamaño(int i) 
	{
		for (int j = 0; j < i; j++)
			lista.removeLast();
	}

}
