package sentencas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CarregaSentencas {
	
	private BufferedReader buffRead;

	public String leitor(String path) throws IOException{
		String sentences = new String();
		sentences = "";
		StringBuffer text = new StringBuffer();  // cria um buffer de string para adicionar as linhas do arquivo txt
		
		buffRead = new BufferedReader(new FileReader(path));
		String line = "";
		while(line != null){
			line = buffRead.readLine();
			text.append(line);
			
		}
		sentences = text.toString();   //transforma o buffer em uma string única
		return sentences;
	}

}
