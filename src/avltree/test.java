package avltree;
import java.util.Iterator;  
public class test {

	 public static void main(String[] args) {  
	        AVLTree<Integer> tree = new AVLTree<Integer>();  
	        System.out.println("------���------");  
	        tree.add(50);  
	        System.out.print(50+" ");  
	        tree.add(66);  
	        System.out.print(66+" ");  
	        for(int i=0;i<10;i++){  
	            int ran = (int)(Math.random() * 100);  
	            System.out.print(ran+" ");  
	            tree.add(ran);  
	        }  
	        System.out.println("------ɾ��------");  
	        tree.remove(50);  
	        tree.remove(66);  
	          
	        System.out.println();  
	        Iterator<Integer> it = tree.itrator();  
	        while(it.hasNext()){  
	            System.out.print(it.next()+" ");  
	        }  
	    }  
	
}
