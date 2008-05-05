package controlador;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.BaseDatos;

public class ModificadorProyectosTest {

	@Before
	public void setUp() throws Exception {
		BaseDatos db= new BaseDatos(); 
		ModificadorProyectos modif_proyectos = new ModificadorProyectos(db);
		ModificadorUsuarios modif_user = new ModificadorUsuarios(db);
		Random rnd = new Random(System.nanoTime()); 
		String proy1,proy2;
		String user1_1,user1_2,user2_1,user2_2;
		proy1 = Float.toString(rnd.nextFloat());
		proy2 = Float.toString(rnd.nextFloat());
		user1_1 = Float.toString(rnd.nextFloat());
		user1_2 = Float.toString(rnd.nextFloat());
		user2_1 = Float.toString(rnd.nextFloat());
		user2_2 =Float.toString(rnd.nextFloat());			
		
	}


	@After
	public void tearDown() throws Exception {
	}	
	
	@Test
	public void testInsertaProyecto() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testBorraProyecto() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSecuencia(){
		fail("Not yet implemented");
	}

}
