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

import detector.rulles.FeatureEnvy;
import read_write_Excel.Excel;
import software.Method;

class FeatureEnvyTest {
	
	@Rule
	private  static List <Method> list = new ArrayList<Method>();
	
	private static FeatureEnvy lm;

	@BeforeAll
	public static void setUp() throws Exception {
		lm = new FeatureEnvy(3.0,0.2,"test1", "OR");
		Excel ex = new Excel();
		ex.readFile();
		list = ex.allMethod();
		lm.feature_envy(list);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetL_ATFD() {
		assertTrue("Não vazio",lm.getL_ATFD() != null);
		assertFalse("Funciona", lm.getL_ATFD() > 20);
		assertTrue(lm.getL_ATFD() < 20);
	}

	@Test
	void testGetL_LAA() {
		assertTrue("Não vazio",lm.getL_LAA() != null);
		assertFalse("Funciona", lm.getL_LAA() > 20);
		assertTrue(lm.getL_LAA() < 20);
	}

	@Test
	void testGetNome() {
		assertTrue("Não vazio",lm.getNome() != null);
		assertEquals("test1",lm.getNome());
	}
	
	@Test
	void testGetTipo() {
		assertTrue("Não vazio",lm.getTipo() != null);
		assertEquals("OR",lm.getTipo());
	}

	@Test
	void testIsfeatureEnvyDet() {
		assertNotNull(lm.getLocRes(), "Lista não vazio");
	}

	@Test
	void testIsIPlasma() {
		assertTrue("isIplasma",lm.getLocRes() != null);
	}

	@Test
	void testIsPMD() {
		assertTrue("isPMD",lm.getLocRes() != null);
	}

}
