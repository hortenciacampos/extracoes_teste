package main;

import java.io.IOException;
import extrator.*;
import sentencas.CarregaSentencas;



import java.util.ArrayList;

/**
 * @author george
 */
public class Main {
    public static void main(String[] args) {
    	
    	AnalisaSentenca ext = new AnalisaSentenca();
        
        ExtraiRelacoes analisador = new ExtraiRelacoes();
        
        CarregaSentencas load = new CarregaSentencas();
        ArrayList<String> sentences;
        try {
            sentences = load.leitor("acessorios/sentencasExemplo.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sentences = new ArrayList<>();
        }
        
        for (String line : sentences) {
            //System.out.print(n++ + " ");
            if (line != null) {
            	String[][] vetor = ext.analisaSentenca(line);
 
                analisador.parse(vetor);
            }
        }
    }
}
