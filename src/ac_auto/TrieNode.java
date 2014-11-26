package ac_auto;
import java.util.*;

public class TrieNode {
	

	             
	            public TrieNode[] childNodes;
	
	             
	             public int freq;
	 
	            
	             public char nodeChar;
	 
             
	             public TrieNode faliNode;
	
	            
	             public Set<Integer> hashSet = new HashSet<Integer>();
	 
	            
	            public TrieNode()
            {
	                childNodes = new TrieNode[26];
	                freq = 0;
	            }

}
