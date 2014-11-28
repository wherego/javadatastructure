package GeneralRMQ_ViaTree;
import java.util.Stack;  
import MinusOrPlusOne_RMQ.MinusOrPlusOne_RMQ;
/** 
 *  
 * An algorithm to solve general RMQ problem 
 * by converting it into a RMQ+1/-1 problem 
 *  
 * time complexity: <O(n),O(1)> 
 *  
 *   
 * Copyright (c) 2011 ljs (http://blog.csdn.net/ljsspace/) 
 * Licensed under GPL (http://www.opensource.org/licenses/gpl-license.php)  
 *  
 * @author ljs 
 * 2011-08-06 
 * 
 */   

public class GeneralRMQ_ViaTree {

	  static class Node {  
	        //index  
	        int index;                
	        Node left;  
	        Node right;  
	        public Node(int index) {  
	            this.index = index;  
	        }  
	        public String toString(){  
	            return String.valueOf(index);  
	        }  
	    }  
	      
	    //input array  
	    private int[] A;  
	    //euler tour  
	    private int[] E;  
	    //levels during euler tour  
	    private int[] L;  
	    //each node's representation position (first occurence) in E  
	    private int[] R;  
	      
	    private MinusOrPlusOne_RMQ mpoRMQ; //collaborator for RMQ+1/-1 problem  
	  
	    public void preprocess(int[] A){  
	        //prepare  
	        int n = A.length;  
	        this.A = A;  
	        E = new int[2*n-1];  
	        L = new int[2*n-1];  
	        R = new int[n];  
	          
	        //build a cartesian tree to convert general RMQ to LCA  
	        //and then make an euler tour to create arrays E,L,R for later RMQ+1/-1 query  
	        Node root = this.buildCartesianTree();  
	        this.eulertour(root);   
	        //once E,L,R are created, cartesian tree can be garbage collected!  
	          
	        //create array R by iterating E backward  
	        for(int i=E.length-1;i>=0;i--){  
	            R[E[i]] = i;  
	        }         
	        this.reportELR();  
	          
	        //preprocess RMQ+1/-1 for L  
	        mpoRMQ = new MinusOrPlusOne_RMQ();  
	        try {  
	            mpoRMQ.preprocess(L);  
	        } catch (Exception e) {  
	            //never get here!  
	        }  
	    }  
	  
	      
	    public int query(int[] A,int i,int j){  
	        int p = R[i];  
	        int q = R[j];  
	        //delegate to RMQ+1/-1 query  
	        int min = mpoRMQ.query(L, p, q);  
	        return E[min];  
	    }  
	    
	    //O(n) algorithm for building cartesian tree  
	    //result: the root of a cartesian tree   
	    private Node buildCartesianTree(){  
	        Stack<Node> rightBranch =  new Stack<Node>();  
	        Node root = new Node(0);          
	        rightBranch.push(root);  
	        for(int i=1;i<A.length;i++){  
	            Node node = new Node(i);  
	            int k = A[i];  
	            Node rightmost= rightBranch.peek();  
	            //use >= for building canonical cartesian tree   
	            if(k>=A[rightmost.index]){   
	                rightmost.right = node;  
	                rightBranch.push(node);  
	            }else{  
	                rightBranch.pop();  
	                while(!rightBranch.isEmpty()){  
	                    rightmost = rightBranch.peek();  
	                    if(k>=A[rightmost.index]){                         
	                        Node tmp = rightmost.right;  
	                        rightmost.right = node;  
	                        node.left = tmp;  
	                        rightBranch.push(node);  
	                        break;  
	                    }else{  
	                        rightBranch.pop();  
	                    }  
	                }  
	                if(rightBranch.isEmpty()){                    
	                    node.left = root;  
	                    root = node;  
	                    rightBranch.push(node);  
	                }  
	            }  
	        }  
	        return root;  
	    }  
	    //make an euler tour to create arrays: E[0..2n-2],L[0..2n-1],R[0..n-1]  
	    private void eulertour(Node root) {  
	        eulertour(root,0);  
	    }  
	      
	    private void eulertour(Node node,int level) {  
	        visit(node,level);  
	        if (node.left != null) {  
	            eulertour(node.left, level + 1);  
	            visit(node, level);  
	        }  
	        if (node.right != null) {  
	            eulertour(node.right, level + 1);  
	            visit(node, level);  
	        }  
	    }  
	    private int i=0; //the position in E  
	    private void visit(Node node, int level) {  
	        this.E[i] = node.index;  
	        this.L[i] = level;        
	        i++;          
	    }  
	      
	    private void reportELR(){  
	        System.out.format("E:");  
	        for(int i=0;i<E.length;i++){  
	            System.out.format(" %d",E[i]);  
	        }  
	        System.out.println();  
	        System.out.format("L:");  
	        for(int i=0;i<L.length;i++){  
	            System.out.format(" %d",L[i]);  
	        }  
	        System.out.println();  
	        System.out.format("R:");  
	        for(int i=0;i<R.length;i++){  
	            System.out.format(" %d",R[i]);  
	        }  
	    }  
	      
	    private void reportLUTable(int[] A){  
	        System.out.format("%n***********************%n");  
	        for(int x=0;x<A.length;x++){  
	            System.out.format("%d..[%d-%d]",x,x,A.length-1);  
	            for(int y=x;y<A.length;y++){           
	                int p = query(A,x,y);                 
	                System.out.format(" %d/%d",A[p],p);               
	            }  
	            System.out.println();  
	        }     
	          
	    }  
	    public static void main(String[] args) {  
	        int[] A=new int[]{1,3,4,5,6,2};  
	        GeneralRMQ_ViaTree gRMQTree = new GeneralRMQ_ViaTree();  
	        gRMQTree.preprocess(A);  
	        gRMQTree.reportLUTable(A);  
	          
	        System.out.format("%n***********************%n");  
	        A=new int[]{2,4,3,1,6,7,8,9,1,7};  
	        gRMQTree = new GeneralRMQ_ViaTree();  
	        gRMQTree.preprocess(A);  
	          
	        int i=0,j=6;  
	        gRMQTree.query(A, i, j);  
	        int min = gRMQTree.query(A,i,j);  
	        System.out.format("%n%nRMQ for A[%d..%d]: A[%d]=%d", i,j,min,A[min]);  
	          
	          
	        gRMQTree.reportLUTable(A);  
	          
	        System.out.format("%n***********************%n");  
	        A=new int[]{10,15,34,20,7,5,18,68,29,40, //0..9  
	                    24,3,45,26,7,23,43,12,68,34,  //10..19  
	                    26,34,33,12,80,57,24,42,77,27, //20..29  
	                    56,33,23,32,54,13,79,65,19,33,  //30..39  
	                    15,24,43,73,55,13,63,8,23,17};  //40..49  
	        gRMQTree = new GeneralRMQ_ViaTree();  
	        gRMQTree.preprocess(A);  
	        gRMQTree.reportLUTable(A);  
	    }  
	
}
