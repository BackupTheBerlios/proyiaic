package maker.vista.baseDatos.OyenteListaTexturas;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import maker.vista.baseDatos.Lista;

/**
 * @author  David García
 */
public class SeleccionLista implements ListSelectionListener 
{
	private Lista lista;
	
	public SeleccionLista(Lista lista) 
	{
		this.lista = lista;
	}

	public void valueChanged(ListSelectionEvent arg0) 
	{
		lista.cambiarSeleccionado();
	}

}
