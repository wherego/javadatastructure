package search;
import java.util.*;
import java.lang.*;
import java.util.Hashtable;
import java.util.Enumeration;

public class HashSearch {
	
	  public static void main(String args[]){
	        Hashtable has=new Hashtable();
	        has.put("one",new Integer(1));
	        has.put("two",new Integer(2));
	        has.put("three",new Integer(3));
	        has.put("four",new Double(12.3));
	        Set s=has.keySet();
	        for(Iterator<String> i=s.iterator();i.hasNext();){
	            System.out.println(has.get(i.next()));
	        }
	        
	        
	        
	      Hashtable hash=new Hashtable(2,(float)0.8);
	 
	   hash.put("Jiangsu","Nanjing");
	  
	    hash.put("Beijing","Beijing");
	    hash.put("Zhejiang","Hangzhou");

	    System.out.println("The hashtable hash1 is: "+hash);
	    System.out.println("The size of this hash table is "+hash.size());
	    

	    Enumeration enum1=hash.elements();
	    System.out.print("The element of hash is: ");
	    while(enum1.hasMoreElements())
	    System.out.print(enum1.nextElement()+" ");
	    System.out.println();
	    
	    if(hash.containsKey("Jiangsu"))
	    System.out.println("The capatial of Jiangsu is "+hash.get("Jiangsu"));
	    hash.remove("Beijing");
	    
	    System.out.println("The hashtable hash2 is: "+hash);
	    System.out.println("The size of this hash table is "+hash.size());

	        
	    }

}
