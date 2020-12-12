package read_write;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import software.Method;


public class Excel {

	private  final String fileName = "Defeitos.xlsx";
	private  List<Method> listMethod = new ArrayList<Method>();

	
	public  void readFile() {
		try {
			FileInputStream arquivo = new FileInputStream(new File(fileName));

			XSSFWorkbook workbook = new XSSFWorkbook(arquivo);

			XSSFSheet sheet = workbook.getSheetAt(0);


			for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){
				Row row = sheet.getRow(i);

				Method soft = new Method();
					
				for(int j=row.getFirstCellNum();j<=row.getLastCellNum();j++){
						Cell cell = row.getCell(j);
						switch (j) {
						case 0:
							soft.setMethodID(cell.getNumericCellValue());
							break;
						case 1:
							soft.setPackageName(cell.getStringCellValue());
							break;
						case 2:
							soft.setClassName(cell.getStringCellValue());
							break;
						case 3:
							soft.setMethodName(cell.getStringCellValue());
							break;
						case 4:
							soft.setN_LOC(cell.getNumericCellValue());
							break;
						case 5:
							soft.setN_CYCLO(cell.getNumericCellValue());
							break;
						case 6:
							soft.setN_ATFD(cell.getNumericCellValue());
							break;				
						case 7: 
							soft.setN_LAA(cell.getNumericCellValue());
							break;
						case 8:
							soft.setIs_long_method(cell.getBooleanCellValue());
							break;
						case 9:
							soft.setiPlasma(cell.getBooleanCellValue());
							break;
						case 10:
							soft.setPMD(cell.getBooleanCellValue());
							break;
						case 11:
							soft.setIs_feature_envy(cell.getBooleanCellValue());
							break;
						}
					}
				listMethod.add(soft);
			}
			workbook.close();
			arquivo.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Excel file not find!");
		} catch (RuntimeException e) {
			e.getMessage();
		} catch(IOException e) {
			e.getMessage();
		}
	}


	
	public void view() {
			if (listMethod.size() == 0) {
				System.out.println("software not find!");
			} else {
				for (Method f : listMethod) {
					System.out.println(f);
				}
			}
	}
	
	public List<Method> allMethod(){
		return listMethod;
	}
}
