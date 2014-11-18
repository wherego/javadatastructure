package trieTree;

public class TrieTreeNode {
	final int MAX_SIZE=26; 
	int nCount;//记录该字符出现次数  
    char ch; //记录该字符  
    TrieTreeNode[] child;  
      
    public TrieTreeNode() {  
        nCount=1;  
        child=new TrieTreeNode[MAX_SIZE];  
    }  

}
