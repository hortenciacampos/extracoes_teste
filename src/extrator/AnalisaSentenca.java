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
    	String teste = "";

    	Document document = new DocumentImpl();
    	document.setText(sentencas);  //cria um documento com as seten�as extra�das do arquivo txt
    	
    	cogroo.analyze(document);
    	
    	    	for (Sentence sentence : document.getSentences()) { // lista de senten�as
    //	    		int comeca = sentence.getStart();
   // 	    		int termina = sentence.getEnd();
    	    		
//    	    		System.out.print(comeca+"-"+termina);
    	    		
    	    	//	System.out.println("Sentenca: "+sentence.getText());
    	    		// Chunks
    	    		
    	    		for (Chunk chunk : sentence.getChunks()) { // lista de chunks
    	    			// Tokens
    	    			
    		    		for (Token token : chunk.getTokens()) { // lista de tokens

    		    			palavra.add(token.getLexeme());   // palavra da senten�a
        		    		classe.add(token.getPOSTag());    // classe gramatical da palavra
        		    		ch.add(token.getChunkTag());      // chunk da palavra
        		    		
        		    		teste = teste+token.getLexeme()+"["+token.getPOSTag()+"] ";

    		    	//		System.out.println(token.getLexeme()+"="+token.getPOSTag()+"="+token.getChunkTag());
        		    		
        		    		

    		    	//		System.out.println(token.getLexeme()+" |"+token.getPOSTag()+" |"+chunk.getStart()+" |"+chunk.getEnd());

    	//	    			System.out.println(token.getPOSTag());

        		    	}
    		    		
    	    		}
    	    		palavra.add("null");
    	    		classe.add("adj");
    	    		ch.add("B-NP");

    	   // 		System.out.println(palavra.get(palavra.size()-1));
    	    	}
    	    	System.out.println("SENTEN�A: "+teste);
    	    	

	    		
    	    	String [][] vetor = new String [3][palavra.size()];   // cria um vetor e insere as 3 listas nele
	    		for (int j=0; j<classe.size();j++){
	    		/*	
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
	    			*/
	    			
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
