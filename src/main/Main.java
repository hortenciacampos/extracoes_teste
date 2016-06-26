package main;

import java.io.IOException;
import extrator.*;
import sentencas.CarregaSentencas;;


public class Main {
	public static void main(String[] args) throws IOException {
		CarregaSentencas load = new CarregaSentencas();
    	String sentencas = new String();
		
		AnalisaSentenca ext = new AnalisaSentenca();
  	
    	sentencas =	load.leitor("acessorios/sentencasExemplo.txt");
    	
    	String[][] vetor = ext.analisaSentenca(sentencas);
    			
		ExtraiRelacoes analisador = new ExtraiRelacoes();

		analisador.parse(vetor);


	}

}
