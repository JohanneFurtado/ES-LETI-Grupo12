package detector.rulles.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import detector.rulles.LongMethod;
import read_write.Excel;
import software.Method;


class LongMethodTest {
	
	@Rule
	private  static List <Method> list = new ArrayList<Method>();
	
	private static LongMethod lm;

	@BeforeAll
	public static void setUp() throws Exception {
		lm = new LongMethod(10.0, 10.0, "test1", "OR");
		Excel ex = new Excel();
		ex.readFile();
		list = ex.allMethod();
		lm.longMethod(list);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetTipo() {
		assertTrue("Não vazio",lm.getTipo() != null);
		assertEquals("OR",lm.getTipo());	
	}

	@Test
	void testGetName() {
		assertTrue("Não vazio",lm.getName() != null);
		assertEquals("test1",lm.getName());
	}

	@Test
	void testGetL_LOC() {
		assertTrue("Não vazio",lm.getL_LOC() != null);
		assertFalse("Funciona", lm.getL_LOC() > 20);
		assertTrue(lm.getL_LOC() < 20);
	}

	@Test
	void testGetL_CYCLO() {
		assertTrue("Não vazio",lm.getL_CYCLO() != null);
		assertEquals(10.0, lm.getL_CYCLO());
		assertTrue(lm.getL_LOC() < 20);
	}


	@Test
	void testIsIPlasma() {
		assertTrue("isIplasma",lm.getLocRes() != null);
	}

	@Test
	void testIsPMD() {
		assertTrue("isPMD",lm.getLocRes() != null);
	}

	@Test
	void testIslongMethod() {
		assertNotNull(lm.getLocRes(), "Lista não vazio");
	}

}
