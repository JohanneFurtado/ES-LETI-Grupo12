package detector.rulles.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import read_write_Excel.Excel;

class ExcelTest {
	
	private static Excel ex = new Excel();

	@Before
	public static void setUp(){
		ex.readFile();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAllMethod() {
		assertTrue(ex.allMethod() != null);
	}

}
