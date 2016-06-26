package extrator;

import java.util.Locale;
import java.util.ArrayList;


import org.cogroo.analyzer.Analyzer;
import org.cogroo.analyzer.ComponentFactory;
import org.cogroo.text.Chunk;
import org.cogroo.text.Document;
import org.cogroo.text.Sentence;

import org.cogroo.text.Token;
import org.cogroo.text.impl.DocumentImpl;


public class AnalisaSentenca {

	public String [][] analisaSentenca(String sentencas) {
		
		ComponentFactory factory = ComponentFactory.create(new Locale("pt", "BR"));
    	Analyzer cogroo = factory.createPipe();

    	// Cria uma lista para cada elemento: palavras, classes gramaticais e chunkers
    	ArrayList<String> palavra = new ArrayList<String>();
    	ArrayList<String> classe = new ArrayList<String>();
    	ArrayList<String> ch = new ArrayList<String>();


    	Document document = new DocumentImpl();
//    	document.setText("O Ambulim foi um dos centros que contribuíram para um estudo apresentado na 5ª Conferência Internacional sobre Transtornos Alimentares, de 29 de abril a 1º de maio em Nova York.\nDados sobre	abuso sexual em bulímicas no Brasil, Áustria e Estados Unidos foram centralizados por Harrison Pope Harrison Pope, da Escola de Medicina e Harvard.\nEssa divisão gera algumas distorções terríveis.\nUm dos injustiçados é Alfredo Volpi, que recebe apenas um painel, com cinco telas que servem para ilustrar sua «evolução» de figurativo a abstrato.");
    	document.setText(sentencas);  //cria um documento com as setenças extraídas do arquivo txt
    	
    	cogroo.analyze(document);
    	
    	    	for (Sentence sentence : document.getSentences()) { // lista de sentenças
    //	    		int comeca = sentence.getStart();
   // 	    		int termina = sentence.getEnd();
    	    		
   // 	    		System.out.print(comeca+"-"+termina);
    	    		
//    	    		System.out.println(sentence.getText());
    	    		// Chunks
    	    		for (Chunk chunk : sentence.getChunks()) { // lista de chunks
    	    			// Tokens
    		    		for (Token token : chunk.getTokens()) { // lista de tokens
    		    			
    		    			palavra.add(token.getLexeme());   //palavra da sentença
    		    			classe.add(token.getPOSTag());    //classe gramatical da palavra
    		    			ch.add(token.getChunkTag());      // chunk da palavra
    		    			
    		    			int teste=0;
    		    			
    		    			teste=chunk.getStart();
    		    			
   // 		    			System.out.println(token.getLexeme()+"="+token.getPOSTag()+"="+token.getChunkTag());

    		    	//		System.out.println(token.getLexeme()+" |"+token.getPOSTag()+" |"+chunk.getStart()+" |"+chunk.getEnd());

    	//	    			System.out.println(token.getPOSTag());

        		    	}
    	    		}
    	    		palavra.add(".");
    	    		classe.add(".");
    	    		ch.add(".");
    	    	}
    	    	
    	    	String [][] vetor = new String [3][palavra.size()];   // cria um vetor e insere as 3 listas nele
	    		for (int j=0; j<classe.size();j++){
	    			
	    			if (palavra.get(j).equals(".null")){
	    				palavra.set(j, "");
	    			}
	    			
	    			if (palavra.get(j).equals("em") ){
	    				if (palavra.get(j+1).equals("o") ){
	    					vetor[0][j] = "no";
	    					vetor[1][j] = "prp";
	    					vetor[2][j] = "B-PP";
	    					palavra.set(j+1, "");
	    					j++;
	    				}
	    				else if (palavra.get(j+1).equals("os")){
	    					vetor[0][j] = "nos";
	    					vetor[1][j] = "prp";
	    					vetor[2][j] = "B-PP";
	    					palavra.set(j+1, "");
	    					j++;
	    				}
	    				else if (palavra.get(j+1).equals("a")){
	    					vetor[0][j] = "na";
	    					vetor[1][j] = "prp";
	    					vetor[2][j] = "B-PP";
	    					palavra.set(j+1, "");
	    					j++;
	    				}
	    				else if (palavra.get(j+1).equals("as")){
	    					vetor[0][j] = "nas";
	    					vetor[1][j] = "prp";
	    					vetor[2][j] = "B-PP";
	    					palavra.set(j+1, "");
	    					j++;
	    				}
	    				

	    			}
	    			
	    			if (palavra.get(j).equals("de")){
	    				if (palavra.get(j+1).equals("o")){
	    					vetor[0][j] = "do";
	    					vetor[1][j] = "prp";
	    					vetor[2][j] = "B-PP";
	    					palavra.set(j+1, "");
	    					j++;
	    				}
	    				else if (palavra.get(j+1).equals("os")){
	    					vetor[0][j] = "dos";
	    					vetor[1][j] = "prp";
	    					vetor[2][j] = "B-PP";
	    					palavra.set(j+1, "");
	    					j++;
	    				}
	    				else if (palavra.get(j+1).equals("a")){
	    					vetor[0][j] = "da";
	    					vetor[1][j] = "prp";
	    					vetor[2][j] = "B-PP";
	    					palavra.set(j+1, "");
	    					j++;
	    				}
	    				else if (palavra.get(j+1).equals("as")){
	    					vetor[0][j] = "das";
	    					vetor[1][j] = "prp";
	    					vetor[2][j] = "B-PP";
	    					palavra.set(j+1, "");
	    					j++;
	    				}
	    				
	    			}
	    			if (palavra.get(j).equals("a")){
	    				if (palavra.get(j+1).equals("o")){
	    					vetor[0][j] = "ao";
	    					vetor[1][j] = "prp";
	    					vetor[2][j] = "B-PP";
	    					palavra.set(j+1, "");
	    					j++;
	    				}
	    			} 
	    			
	    			vetor[0][j] = palavra.get(j);
	    			vetor[1][j] = classe.get(j);
	    			vetor[2][j] = ch.get(j);
	    		}
//    			System.out.println(palavra.size());
//    			System.out.println(classe.size());
//    			System.out.println(ch.size());


	    		return vetor;
    	    	
	}
}
