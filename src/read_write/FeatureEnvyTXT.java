package read_write;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import detector.rulles.FeatureEnvy;

public class FeatureEnvyTXT {

	private File path = new File("featureEnvy.txt");

	public  List<FeatureEnvy> leitor() {
		
		List <FeatureEnvy> list = new ArrayList<FeatureEnvy>();
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(path));
			String linha = buffRead.readLine();
			while (true) {
				if (linha != null) {
					
					String[] li = linha.split(" ");
					String n = li[0];
					String at = li[1];
					String la = li[2];
					String t = li[3];
					
					list.add(new FeatureEnvy(Double.parseDouble(at), Double.parseDouble(la), n, t));

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

	public  void escritor(List<FeatureEnvy> list) {
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
			
			for(FeatureEnvy lm : list) {
				String linha = lm.toString();
				buffWrite.append(linha + "\n");
			}
			buffWrite.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
}
