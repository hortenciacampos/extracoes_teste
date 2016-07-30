package extrator;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Point;


public class ExtraiRelacoes {
	String VERB = "(v-fin |v-inf |v-pcp |v-ger )";
	String ADV = "(adv )";
	String NOUN = "(n |prop )";
	String ADJ = "(adj |n-adj |num )";
	String PRON = "(pron-pers |pron-det |pron-indp )";
	
	String DET = "(det |art | spec )";
	String PARTICLE = "(conj-s |conj-c |intj )";
	String PREP = "(prp )";
	String INF_MARKER = "(EC |- )";
	
	String V = "("+VERB+PARTICLE+"?"+ADV+"?)";
	String W = "("+NOUN+"|"+ADJ+"|"+ADV+"|"+PRON+"|"+DET+")";
	String P = "("+PREP+"|"+PARTICLE+"|"+INF_MARKER+")";
	
	String VP = "("+V+P+")";
	String VW_P = "("+V+W+"*"+P+")";

	String PATTERN = "("+VW_P+"|"+VP+"|"+V+")";

	String CHUNK = "B-NP (I-NP )*(B-PP B-NP (I-NP )*)*";
	
	public void parse(String[][] vetor) {
		// SET ITERATORS
		Vector<Integer> posTag = new Vector<>();
		Vector<Integer> chunkTag = new Vector<>();
		// FILL ITERATORS
		posTag.add(0);
		chunkTag.add(0);
		for(int i=0; i < vetor[0].length; i++){
			posTag.add(posTag.get(i)+vetor[1][i].length()+1);
			chunkTag.add(chunkTag.get(i)+vetor[2][i].length()+1);
		}
		// FILL A STRING WITH ALL POS TAGS
		String tags = "";
		String chunks = "";
		for(int i=0; i<vetor[1].length;i++){
			tags = tags + vetor[1][i]+' ';
			chunks = chunks + vetor[2][i]+' ';
		}
		
//		Pattern p = Pattern.compile(this.PATTERN);
//		Matcher m = p.matcher(tags); // get a matcher object

		Point relationIndexes=null;
		Point chunkIndexes=null;

		Vector<Point>extractions = new Vector<Point>();
		Vector<Point>np_extractions = new Vector<Point>();
		
		Pattern p = Pattern.compile(this.CHUNK);
		Matcher m = p.matcher(chunks); // get a matcher object
		while (m.find()) {
			//for(int i=m.start();i<m.end(); i++)
			//	System.out.print(chunks.charAt(i)); 
			chunkIndexes = getTagIndexes(m.start(), m.end(), chunkTag);
			np_extractions.add(chunkIndexes);
			/*
			for(int j=relationIndexes.x; j <= relationIndexes.y ; j++){
				System.out.print(pos.get(0)[j]+" ");
			}
			System.out.println(" ");
			*/
		}
		
		p = Pattern.compile(this.PATTERN);
		m = p.matcher(tags);
		
				while (m.find()) {


					relationIndexes = getTagIndexes(m.start(), m.end(), posTag);
				//	System.out.println(getTagIndexes(m.start(), m.end(), posTag));
					if(extractions.isEmpty()){
						extractions.add(relationIndexes);
					} else {
						if((Integer)extractions.lastElement().y+1 == relationIndexes.x)
							extractions.lastElement().y = relationIndexes.y;
						else
							extractions.add(relationIndexes);

					}
				}

				for(Point i: extractions){
					
					String arg1 = ""; 
					String rel = ""; 
					String arg2 = ""; 
					
					// FIND THE NEAREST LEFT CHUNK (IF EXISTS)
					for(Point l: np_extractions){
						if(l.y < i.x){
							chunkIndexes = l;
						} else {
							break;
						}
					}
					// PRINT THE NEAREST LEFT CHUNK
		//			System.out.print("arg1: ");
					for(int j=chunkIndexes.x; j <= chunkIndexes.y ; j++){
		//				System.out.print(vetor[0][j]+" ");
			//			if (!(vetor[0][j].equals(".null")) && !(vetor[0][j].equals("null")))
							arg1 = arg1+" "+vetor[0][j];
			//			System.out.println(arg1);

					}
					
		//			System.out.print(" | "+"rel: ");
					// PRINT THE RELATION
					for(int j=i.x; j <= i.y ; j++){				
		//				System.out.print(vetor[0][j]+" ");
						rel = rel+" "+vetor[0][j];

					}
			//		System.out.print(" | ");
					// FIND THE NEAREST RIGHT CHUNK (IF EXISTS)
					for(Point r: np_extractions){
						if(r.x>i.y){
							chunkIndexes = r;
							break;
						}
						// ARMENGUE MASTER BLASTER
						if(r.y>i.y){
							for(int j=r.x+1; j <= r.y ; j++){
								if(vetor[2][j].equals("B-NP")){
									r.x = j;
									chunkIndexes = r;
									break;
								}
							}
							break;
						}
					}
					// PRINT THE NEAREST RIGHT CHUNK
			//		System.out.print("arg2: ");
					for(int j=chunkIndexes.x; j <= chunkIndexes.y ; j++){
			//			System.out.print(vetor[0][j]+" ");
						if (!(vetor[0][j].equals(".null")))
							arg2 = arg2+" "+vetor[0][j];
			//			System.out.println(arg2);
						
					}
					if (!(arg1.equals(" null")) && !(arg2.equals(" null")) && !(arg1.equals(" .null")) && !(arg2.equals(" .null") )){
						System.out.println("Arg1: "+arg1+" | Rel: "+rel+" | Arg2: "+arg2);
					}

			//		System.out.println();
				}
			}
	
	
	private Point getTagIndexes(int start, int end, Vector<Integer> posTag){
		Point indexes = new Point(-1,-1);
		indexes.x = posTag.indexOf(start);
		
		if(posTag.indexOf(end) == -1)
			indexes.y = posTag.lastElement();
		else
			indexes.y = posTag.indexOf(end)-1;
		
		return indexes;	
	}
				
}
