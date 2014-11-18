package tree;

import tree.BinaryTree.TreeNode;
import tree.BinaryTree;

public class test {
	
	public static void main(String[] args) {  
		  
	    BinaryTree tree = new BinaryTree();  

	    TreeNode node = tree.init();  

	    // 先序遍历。。。。。。。  

	    System.out.println("**************************");  

	    System.out.println("先序遍历。。。。。。。");  

	    tree.xianIterator(node);  

	    System.out.println("");  

	    // 中序遍历。。。。。。。  

	    System.out.println("**************************");  

	    System.out.println("中序遍历。。。。。。。");  

	    tree.zhongIterator(node);  

	    System.out.println("");  

	    // 后续遍历。。。。。。。  

	    System.out.println("**************************");  

	    System.out.println("后序遍历。。。。。。。");  

	    tree.houIterator(node);  

	}  	

}
