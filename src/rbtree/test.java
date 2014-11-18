package rbtree;

public class test {
	
	 /**  
     * @param args  
     */  
    public static void main(String[] args) {  
          
        RBTree rbTree = new RBTree();  
          
        rbTree.rbInsert(new Node(41));  
        rbTree.rbInsert(new Node(38));  
        rbTree.rbInsert(new Node(31));  
        rbTree.rbInsert(new Node(12));  
        rbTree.rbInsert(new Node(19));  
        rbTree.rbInsert(new Node(8));  
          
        //rbTree.printTree();  
          
          
        rbTree.rbDelete(19);  
          
        rbTree.printTree();  
          
  
    }  

}
