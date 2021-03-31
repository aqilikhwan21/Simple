package simpleIr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.IntPredicate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Frequency {
	
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException {
 		HashMap<String, String> Post = new HashMap<String, String>();
		HashMap[] Final = new HashMap[5];
		HashMap<String, Integer> Combine = new HashMap<String,Integer>();
		String [] input = new String [5];
		int k;
		 File inputFile = new File("C:\\Users\\Aqil Ikhwan\\OneDrive\\Documents\\SimpleIr\\index.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         
         Document newdoc = dBuilder.newDocument();
         Element docs = newdoc.createElement("docs");
         newdoc.appendChild(docs);
         doc.getDocumentElement().normalize();
         NodeList nList = doc.getElementsByTagName("code");
         for (int temp = 0; temp < nList.getLength(); temp++) {
        	Node nNode = nList.item(temp);
            Element body = newdoc.createElement("body");
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	              Element eElement = (Element) nNode;
	               
	              Element code = newdoc.createElement("code");
	  	     	  docs.appendChild(code);
	               
	              code.setAttribute("docid", ""+ eElement.getAttribute("docid"));
	         
	              Element title = newdoc.createElement("title");
	              title.appendChild(newdoc.createTextNode(eElement.getElementsByTagName("title").item(0).getTextContent()));
	              code.appendChild(title);
	              input[temp] =eElement.getElementsByTagName("body").item(0).getTextContent();
	              input[temp] = input[temp].replaceAll("\\p{P}","");
	              String [] output = input[temp].split("\\s");
	              HashMap<String, Integer> Input = new HashMap<String, Integer>();
	             for(int i = 0; i<output.length;i++) {
	              Input.put(output[i], k=Integer.parseInt(output[i+1]));
	              i++;
	            }
	             Final[temp] = Input;
            }
         }
         for(int i = 0; i<Final.length; i++) {
        	 Combine.putAll(Final[i]);
         }
         for (String m : Combine.keySet()) {
        	 String txt ="";
             //System.out.print("keyword : "+m + " ");
             double doc_count = 0.0;
             int [] doc1 = new int[5];
             int j=0;
              for (HashMap<String, Integer> i : Final) {
                int p=0;
                  for (String x : i.keySet()) {
                      if(m.equals(x)) {
                          doc_count += 1;
                          p=1;
                      }

                  }
                   if(p==1) {
                   doc1[0+j]=1;
                   j++;
                   }else {
                      doc1[0+j]=0;
                      j++;
                   }

              }
              int z = 0;
              for (HashMap<String, Integer> i : Final) {
                  for (String x : i.keySet()) {
                      if(m.equals(x)) {
                    	  while(true) {
                    	  if(doc1[z]==1) {
                    	  
//                          System.out.print((z+1)+" "+i.get(x) * Math.log10((5/doc_count)));
//                          System.out.print(",");
                          txt +="["+(z+1)+","+i.get(x) * Math.log10((5/doc_count))+", ";
                          txt +="]";
                          z++;
                    	  break;
                    	  }
                    	  else {
                    		  z++;
                    	  }
                    	  }
                             
                     }
                   
                          }
                  }
            	 Post.put(m, txt);
            	 
         }
   		  FileOutputStream Filestream = new FileOutputStream("C:\\Users\\Aqil Ikhwan\\OneDrive\\Documents\\SimpleIr\\Index.post");
   		  ObjectOutputStream ObjOs = new ObjectOutputStream(Filestream);
   		  
   		  ObjOs.writeObject(Post);
   		  ObjOs.close();
   		  
   		  FileInputStream fileStream = new FileInputStream("C:\\Users\\Aqil Ikhwan\\OneDrive\\Documents\\SimpleIr\\Index.post");
   		  ObjectInputStream ObjIs = new ObjectInputStream(fileStream);
   		  
   		  Object object = ObjIs.readObject();
   		  ObjIs.close();
   		  
   		  System.out.println("읽어온 객체의 type = "+object.getClass());
   		  
   		  HashMap hashmap = (HashMap)object;
   		  Iterator<String> it = hashmap.keySet().iterator();
   		  
   		  while(it.hasNext()) {
   			  String key = it.next();
   			  String value = (String)hashmap.get(key);
   			  System.out.println(key+ "="+ value);
   		  }


        

	}

}
