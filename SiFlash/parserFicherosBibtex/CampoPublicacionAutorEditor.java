package parserFicherosBibtex;

import java.util.LinkedList;

import personas.AutorEditor;

public class CampoPublicacionAutorEditor extends Campo 
{
	/**
	 * Valor del campo.
	 */
	private LinkedList<AutorEditor> valor;
	
	public CampoPublicacionAutorEditor(String nombre, LinkedList<AutorEditor> valor)
	{
		this.nombre = nombre;
		this.valor = valor;
	}
	
	public LinkedList<AutorEditor> getValor() {
		return valor;
	}
}
