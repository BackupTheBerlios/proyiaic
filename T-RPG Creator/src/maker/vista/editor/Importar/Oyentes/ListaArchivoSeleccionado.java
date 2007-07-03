package maker.vista.editor.Importar.Oyentes;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import maker.vista.editor.Importar.PanelImportar;

/**
 * @author  David García
 */
public class ListaArchivoSeleccionado implements ListSelectionListener 
{
	private JList listaArchivo;
	private PanelImportar importar;

	public ListaArchivoSeleccionado(JList listaArchivo, PanelImportar importar) 
	{
		this.listaArchivo = listaArchivo;
		this.importar = importar;
	}

	public void valueChanged(ListSelectionEvent arg0) 
	{
		if (listaArchivo.isSelectionEmpty())
		{
			importar.enableAll(false);
		}
		else
		{
			importar.enableAll(true);
		}
	}
}
