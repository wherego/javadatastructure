package huffmanTree;
import huffmanTree.HuffmanTree;
import huffmanTree.HuffmanTree.Node;
import java.util.List;
import java.util.ArrayList;


public class test {
	
	 public static void main(String[] args) {  
		  
	        List<Node> list = new ArrayList<Node>();  
	        Node<String> node1 = new Node<String>("东方不败", 8);  
	        Node<String> node2 = new Node<String>("风清扬", 5);  
	        Node<String> node3 = new Node<String>("岳不群", 4);  
	        Node<String> node4 = new Node<String>("左冷禅", 1);  
	        list.add(node1);  
	        list.add(node2);  
	        list.add(node3);  
	        list.add(node4);  
	        
	        HuffmanTree hfmtree=new HuffmanTree();
	        
	        
	        Node root =hfmtree.createHuffmanTree(list);  
	        System.out.println(hfmtree.deepFirst(root));  
	    }  

}
