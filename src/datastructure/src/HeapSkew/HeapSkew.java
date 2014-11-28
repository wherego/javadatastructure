package HeapSkew;

import java.util.*;

public class HeapSkew {
	
	
	 static class Node{  
	        int key;  
	        Node left;  
	        Node right;  
	          
	        public Node(int key){  
	            this.key = key;   
	        }  
	          
	        public String toString(){  
	            return String.valueOf(this.key);  
	        }  
	          
	        public Node recursiveMerge(Node rhsRoot){  
	            if(rhsRoot==this || rhsRoot == null) {  
	                //Note: no need to swap the left and right children of this tree  
	                //Node tmp = this.left;  
	                //this.left = this.right;  
	                //this.right = tmp;  
	                return this;  
	            }  
	              
	            Node root1 = null; //the root of the merged tree  
	            Node root2 = null;   
	            if(rhsRoot.key<this.key){  
	                //merge this with rhsRoot's right child  
	                root1 = rhsRoot;  
	                root2 = this;  
	            }else{  
	                //merge rhsRoot with this's right child  
	                root1 = this;  
	                root2 = rhsRoot;                  
	            }  
	              
	            Node tmpRoot = root2.recursiveMerge(root1.right);  
	            root1.right = root1.left;  
	            root1.left = tmpRoot;  
	            //or equivalently: we may first merge recursively, then swap left and right children.  
	            //Node right = root1.right;  
	            //root1.right = root1.left;            
	            //root1.left = root2.merge(right);            
	                          
	            return root1;  
	        }  
	    }  
	    private Node root;  
	      
	    public HeapSkew(Node root){  
	        this.root = root;  
	    }  
	      
	    //version 1: recursive merge: not recommended because skew heap is   
	    //unlike leftist heap to be deterministically leftist, so it can cause   
	    //stack overflow in extreme cases.  
	    private static Node recursiveMerge(Node root1,Node root2){  
	        if(root1 == null) return root2;  
	        if(root2 == null) return root1;  
	        return root1.recursiveMerge(root2);   
	    }     
	  
	    //version 2: non-recursive merge  
	    private static Node iterativeMerge(Node root1,Node root2){  
	        if(root1 == null) return root2;  
	        if(root2 == null) return root1;  
	          
	        Stack<Node> stack = new Stack<Node>();  
	        Node r1 = root1;  
	        Node r2 = root2;  
	        while(r1 != null && r2 != null){  
	            if(r1.key<r2.key){  
	                stack.push(r1);  
	                r1 = r1.right;  
	            }else{  
	                stack.push(r2);  
	                r2 = r2.right;  
	            }             
	        }  
	        //at this point, exactly one of r1 and r2 is null  
	        //Again we don't need to swap the left and right children of r  
	        Node r = (r1 != null)?r1:r2;  
	          
	        //merge  
	        while(!stack.isEmpty()){  
	            Node node = stack.pop();              
	            node.right = node.left;  
	            node.left = r;  
	            r = node;  
	        }  
	        return r;  
	    }  
	      
	    public static HeapSkew merge(HeapSkew h1,HeapSkew h2){  
	        Node rootNode = iterativeMerge(h1.root,h2.root);  
	        return new HeapSkew(rootNode);  
	    }        
	      
	    public static HeapSkew buildHeap(int[] A){  
	        LinkedList<Node> queue = new LinkedList<Node>();  
	        int n = A.length;  
	        //init: queue all elements as a single-node tree  
	        for(int i=0;i<n;i++){  
	            Node node = new Node(A[i]);  
	            queue.add(node);  
	        }  
	        //merge adjacent heaps and enqueue the merged heap afterward  
	        while(queue.size()>1){  
	            Node root1 = queue.remove(); //dequeue  
	            Node root2 = queue.remove();  
	            Node rootNode = iterativeMerge(root1,root2);  
	            queue.add(rootNode);  
	        }  
	        Node rootNode = queue.remove();  
	        return new HeapSkew(rootNode);  
	    }  
	    public void insert(int x){  
	        this.root = HeapSkew.iterativeMerge(new Node(x), this.root);  
	    }  
	      
	    public Integer extractMin(){  
	        if(this.root == null) return null;  
	          
	        int min = this.root.key;  
	        this.root = HeapSkew.iterativeMerge(this.root.left, this.root.right);  
	        return min;  
	    }  
	       
	      
	    public static void main(String[] args) {  
	        int[] A = new int[]{4,8,10,9,1,3,5,6,11};  
	           
	        HeapSkew heap = HeapSkew.buildHeap(A);  
	         heap.insert(7);  
	         Integer min = null;  
	         while((min = heap.extractMin()) != null){  
	             System.out.format(" %d", min);  
	         }  
	         System.out.println();  
	           
	         System.out.println("********************");  
	         A = new int[]{3,10,8,21,14,17,23,26};  
	           
	           
	         Node a0 = new Node(A[0]);  
	         Node a1 = new Node(A[1]);  
	         Node a2 = new Node(A[2]);  
	         Node a3 = new Node(A[3]);  
	         Node a4 = new Node(A[4]);  
	         Node a5 = new Node(A[5]);  
	         Node a6 = new Node(A[6]);  
	         Node a7 = new Node(A[7]);         
	         a0.left = a1;     
	         a0.right = a2;   
	         a1.left = a3;     
	         a1.right = a4;  
	         a4.left = a6;  
	         a2.left = a5;  
	         a5.left = a7;  
	         HeapSkew h1 = new HeapSkew(a0);  
	           
	         int[] B = new int[]{6,12,7,18,24,37,18,33};  
	         Node b0 = new Node(B[0]);  
	         Node b1 = new Node(B[1]);  
	         Node b2 = new Node(B[2]);  
	         Node b3 = new Node(B[3]);  
	         Node b4 = new Node(B[4]);  
	         Node b5 = new Node(B[5]);  
	         Node b6 = new Node(B[6]);  
	         Node b7 = new Node(B[7]);  
	         b0.left = b1;    
	         b0.right = b2;  
	         b1.left = b3;    
	         b1.right = b4;   
	         b4.left = b7;  
	         b2.left = b5;    
	         b2.right = b6;  
	         HeapSkew h2 = new HeapSkew(b0);  
	           
	         heap =HeapSkew.merge(h1,h2);  
	         while((min = heap.extractMin()) != null){  
	             System.out.format(" %d", min);  
	         }  
	         System.out.println();   
	           
	         System.out.println("********************");  
	         A = new int[]{1,4,23,63,24,44,28};  
	         a0 = new Node(A[0]);  
	         a1 = new Node(A[1]);  
	         a2 = new Node(A[2]);  
	         a3 = new Node(A[3]);  
	         a4 = new Node(A[4]);  
	         a5 = new Node(A[5]);  
	         a6 = new Node(A[6]);   
	         a0.left = a1;     
	         a0.right = a2;  
	         a1.left = a3;     
	         a1.right = a4;  
	         a2.left = a5;    
	         a2.right = a6;  
	         h1 = new HeapSkew(a0);  
	           
	         B = new int[]{13,17,99,57,49,105,201};  
	         b0 = new Node(B[0]);  
	         b1 = new Node(B[1]);  
	         b2 = new Node(B[2]);  
	         b3 = new Node(B[3]);  
	         b4 = new Node(B[4]);  
	         b5 = new Node(B[5]);  
	         b6 = new Node(B[6]);  
	         b0.left = b1;  
	         b0.right = b2;  
	         b1.left = b3;  
	         b1.right = b4;  
	         b2.left = b5;  
	         b2.right = b6;  
	         h2 = new HeapSkew(b0);  
	           
	         heap = HeapSkew.merge(h1,h2);   
	         while((min = heap.extractMin()) != null){  
	             System.out.format(" %d", min);  
	         }  
	         System.out.println();  
	    }  

}
