package read_write;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import detector.rulles.LongMethod;

public class LongMethodTXT {

	private File path = new File("longMethod.txt");

	public  List<LongMethod> leitor() {
		
		List <LongMethod> list = new ArrayList<LongMethod>();
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(path));
			String linha = buffRead.readLine();
			while (true) {
				if (linha != null) {
					
					String[] li = linha.split(" ");
					String n = li[0];
					String lc = li[1];
					String cy = li[2];
					String t = li[3];
					
					list.add(new LongMethod(Double.parseDouble(lc), Double.parseDouble(cy), n, t));

				} else
					break;
				linha = buffRead.readLine();
			}
			buffRead.close();
		} catch (IOException e) {
			e.getMessage();
		}
		return list;
	}

	public  void escritor(List<LongMethod> list) {
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
			
			for(LongMethod lm : list) {
				String linha = lm.toString();
				buffWrite.append(linha + "\n");
			}
			buffWrite.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
}
