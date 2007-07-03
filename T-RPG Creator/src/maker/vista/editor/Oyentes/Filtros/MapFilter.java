package maker.vista.editor.Oyentes.Filtros;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class MapFilter extends FileFilter 
{
	public boolean accept(File archivo) 
	{
		if (archivo.isDirectory()) //El archivo es un directorio.
			return true;
		else //No es un directorio.
		{
			// Cogemos el nombre del archivo.
			String cadena = archivo.getAbsolutePath();
			int ext = cadena.lastIndexOf('.'); //Última posición de un punto en el nombre.
			if (ext < cadena.length() || ext >= 1) //Hay algún punto
			{
				String extension = cadena.substring(ext+1); //Cogemos la extensión del archivo.
				if (extension.equals("map")) //La extensión es "map".
					return true;
			}
		}
		return false;
	}

	/** Método que devuelve una descripción acerca de los archivos que acepta el método
	 * accept(File archivo)
	 * @return La descripción de los archivos aceptados.*/
	public String getDescription() 
	{
		return "Archivos de mapa de SIM RPG Maker 2007 (.map)";
	}

}

