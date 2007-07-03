package maker.vista.baseDatos.OyenteListaTexturas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import maker.vista.baseDatos.Lista;

/**
 * @author  David García
 */
public class ListaSuprimir extends KeyAdapter 
{
	private Lista lista;

	public ListaSuprimir(Lista lista) 
	{
		this.lista = lista;
	}

	public void keyPressed(KeyEvent evento) 
	{
		if (evento.getKeyCode() == KeyEvent.VK_DELETE)
		{
			lista.borrarSeleccionado();
		}
	}
}
