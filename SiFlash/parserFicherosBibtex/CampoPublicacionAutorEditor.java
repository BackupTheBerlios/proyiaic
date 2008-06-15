package parserFicherosBibtex;

import java.util.LinkedList;

import personas.AutorEditor;

/**
 * Almacena el nombre y el valor de un campo, cuando el valor es una lista enlazada.
 */
public class CampoPublicacionAutorEditor extends Campo 
{
	/**
	 * Valor del campo.
	 */
	private LinkedList<AutorEditor> valor;
	
	/**
	 * Constructor especificando los parámetros.
	 * @param nombre Nombre del campo.
	 * @param valor Valor del campo.
	 */
	public CampoPublicacionAutorEditor(String nombre, LinkedList<AutorEditor> valor)
	{
		this.nombre = nombre;
		this.valor = valor;
	}
	
	public LinkedList<AutorEditor> getValor() {
		return valor;
	}
	
	/**
	 * Convierte a String el nombre del campo.
	 */
	public String toString()
	{
		return nombre;
	}
}
