package maker.vista.editor.Importar.Filtros;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ImageFilter extends FileFilter 
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
				if (extension.equals("png") || extension.equals("gif") || extension.equals("jpg"))
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
		return "Archivos de imagen (.png, .jpg, .gif)";
	}
}
