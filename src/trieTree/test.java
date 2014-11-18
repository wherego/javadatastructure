package trieTree;
import trieTree.TrieTree;
import trieTree.TrieTreeNode;

public class test {
	
	 public static void main(String[] args)
	 {
	
	String[] strs={     
            "banana",     
            "band",     
            "bee",     
            "absolute",     
            "acm",     
    };     
    String[] prefix={     
        "ba",     
        "b",     
        "band",     
        "abc",     
    }; 
    
    TrieTree tree = new TrieTree();  
    TrieTreeNode root=new TrieTreeNode();
    
 for (String s : strs) {    
        tree.createTrie(root, s);    
    }     
//    tree.printAllWords();     
    for(String pre:prefix){     
        int num=tree.findCount(root,pre);     
        System.out.println(pre+" "+num);     
    }  
	
}
}