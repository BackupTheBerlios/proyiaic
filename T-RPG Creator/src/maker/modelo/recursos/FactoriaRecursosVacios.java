package maker.modelo.recursos;

public class FactoriaRecursosVacios 
{	
	public static final int TEXTURA = 234;
	public static final int OBJETO = 235;
	
	/** Devuelve un recurso vacio del tipo que se pasa como parámetro. Los tipos
	 * permitidos son FactoriaRecursosVacios.TEXTURA y FactoriaRecursosVacios.OBJETO*/
	public static Recurso crearRecursoVacio(int tipo)
	{
		switch (tipo)
		{
			case TEXTURA: return new Textura("", false, "", "", "");
			case OBJETO: return new Objeto("", "", "", false, false, 0);
			default: return null;
		}
	}
}
