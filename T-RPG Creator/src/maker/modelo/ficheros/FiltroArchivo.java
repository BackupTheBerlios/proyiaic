package maker.modelo.ficheros;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FiltroArchivo extends FileFilter {

	public boolean accept(File f) 
	{
		if (f.isDirectory())
			return true;
		else 
		{
			String nombre = f.getName();
			return nombre.equals("project_data.srpg");
		}
	}

	public String getDescription() 
	{
		return "Datos de proyecto SIM RPG Maker 2007. (project_data.srpg)";
	}

}
