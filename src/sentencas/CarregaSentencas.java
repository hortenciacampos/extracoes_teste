package sentencas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CarregaSentencas {
	
	private BufferedReader buffRead;

	public ArrayList<String> leitor(String path) throws IOException{
		ArrayList<String> sentences = new ArrayList<>();
//		sentences = "";
	//	StringBuffer text = new StringBuffer();  // cria um buffer de string para adicionar as linhas do arquivo txt
		
		buffRead = new BufferedReader(new FileReader(path));
		String line = "";
		while(line != null){
			line = buffRead.readLine();
			sentences.add(line);
			
		}
	//	sentences = text.toString();   //transforma o buffer em uma string única
		return sentences;
	}

}