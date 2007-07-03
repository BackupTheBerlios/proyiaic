package maker.modelo.mapa_caminos;



public class Tramo 
{	
	private Nodo nodo_inicial;
	
	private Nodo nodo_final;
	
	public Tramo(Nodo inicial, Nodo fin)
	{
		if (inicial != fin)
		{
			nodo_inicial = inicial;
			nodo_final = fin;
		}
	}
}
