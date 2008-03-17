package parserFicherosBibtex.JUnit;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Clase que ejecuta las pruebas.
 */
public class ParserBibtexTestSuite 
{
	public static Test suite() 
	{
		TestSuite suite = new TestSuite();
	    
	    suite.addTestSuite(ParserBibtexTest.class);
	    return suite;
	}
	
	public static void main(String[] args) 
	{
		junit.textui.TestRunner.run(suite());
	}
}
