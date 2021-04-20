package simpleIr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

public class getSnippet {
	
	public getSnippet(String input) throws IOException, ClassNotFoundException {
		String F_input="";
		FileInputStream fileStream = new FileInputStream("C:\\Users\\Aqil Ikhwan\\OneDrive\\Documents\\SimpleIr\\input.txt");
		 F_input += fileStream.toString();
		 String [] output = F_input.split("\n");
		 String [][] input2 = new String [5][2]; 
		 for(int i = 0;i< output.length; i++) {
			 KeywordExtractor ke = new KeywordExtractor();
             KeywordList kl = ke.extractKeyword(output[i], true);
             for(int l = 0; l<kl.size(); l++) {
          	   String text;
          	   Keyword kwrd = kl.get(i);
          	   text = "#"+kwrd.getString()+":\t"+kwrd.getCnt()+"\t";
          	   input2[0][i] +=""+i;
          	   input2[1][i] +=text;
          	
             }
		 }
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		getSnippet dr = new getSnippet("abc");

	}

}
