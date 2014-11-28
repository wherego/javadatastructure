package LeftistMinHeap;

import java.util.LinkedList;  


/** 
 *  
 * Leftist Heap    
 *   
 * Copyright (c) 2011 ljs (http://blog.csdn.net/ljsspace/) 
 * Licensed under GPL (http://www.opensource.org/licenses/gpl-license.php)  
 *  
 * @author ljs 
 * 2011-08-20 
 * 
 */  
public class LeftistMinHeap {  
    static class LeftistHeapNode{  
        int key;  
        LeftistHeapNode left;  
        LeftistHeapNode right;  
        int npl; //null-path-length  
          
        public LeftistHeapNode(int key){  
            this.key = key;  
            this.npl = 0;  
        }  
          
        public String toString(){  
            return this.key + "(npl=" + this.npl + ")";  
        }  
          
        public LeftistHeapNode merge(LeftistHeapNode rhsRoot){  
            if(rhsRoot==this || rhsRoot == null) return this;  
                                          
            LeftistHeapNode root1 = null; //the root of the merged tree  
            LeftistHeapNode root2 = null;   
            if(rhsRoot.key<this.key){  
                //merge this with rhsRoot's right child  
                root1 = rhsRoot;  
                root2 = this;  
            }else{  
                //merge rhsRoot with this's right child  
                root1 = this;  
                root2 = rhsRoot;                  
            }  
              
            LeftistHeapNode tmpRoot = root2.merge(root1.right);  
            root1.right = tmpRoot;  
            if(root1.left == null){ //left can not be null unless right is null  
                root1.right = null;  
                root1.left = tmpRoot;  
                root1.npl = 0;  
            }else{  
                if(root1.right.npl>root1.left.npl){  
                    //swap left and right child                       
                    root1.right = root1.left;  
                    root1.left = tmpRoot;                     
                }  
                //at this time, the right child has the shortest null-path  
                root1.npl = root1.right.npl + 1;  
            }  
              
            return root1;  
        }  
    }  
      
    private LeftistHeapNode root;  
      
    public LeftistMinHeap(LeftistHeapNode root){  
        this.root = root;  
    }  
      
    private static LeftistHeapNode merge(LeftistHeapNode root1,LeftistHeapNode root2){  
        if(root1 == null) return root2;  
        if(root2 == null) return root1;  
        return root1.merge(root2);   
    }        
    public static LeftistMinHeap merge(LeftistMinHeap h1,LeftistMinHeap h2){  
        LeftistHeapNode rootNode = merge(h1.root,h2.root);  
        return new LeftistMinHeap(rootNode);  
    }        
      
    public static LeftistMinHeap buildHeap(int[] A){  
        LinkedList<LeftistHeapNode> queue = new LinkedList<LeftistHeapNode>();  
        int n = A.length;  
        //init: queue all elements as a single-node tree  
        for(int i=0;i<n;i++){  
            LeftistHeapNode node = new LeftistHeapNode(A[i]);  
            queue.add(node);  
        }  
        //merge adjacent heaps and enqueue the merged heap afterward  
        while(queue.size()>1){  
            LeftistHeapNode root1 = queue.remove(); //dequeue  
            LeftistHeapNode root2 = queue.remove();  
            LeftistHeapNode rootNode = merge(root1,root2);  
            queue.add(rootNode);  
        }  
        LeftistHeapNode rootNode = queue.remove();  
        return new LeftistMinHeap(rootNode);  
    }  
    public void insert(int x){  
        this.root = LeftistMinHeap.merge(new LeftistHeapNode(x), this.root);  
    }  
      
    public Integer extractMin(){  
        if(this.root == null) return null;  
          
        int min = this.root.key;  
        this.root = LeftistMinHeap.merge(this.root.left, this.root.right);  
        return min;  
    }  
       
    public static void main(String[] args) {  
         int[] A = new int[]{4,8,10,9,1,3,5,6,11};  
           
         LeftistMinHeap heap = LeftistMinHeap.buildHeap(A);  
         heap.insert(7);  
         Integer min = null;  
         while((min = heap.extractMin()) != null){  
             System.out.format(" %d", min);  
         }  
         System.out.println();  
           
         System.out.println("********************");  
         A = new int[]{3,10,8,21,14,17,23,26};  
           
           
         LeftistHeapNode a0 = new LeftistHeapNode(A[0]);  
         LeftistHeapNode a1 = new LeftistHeapNode(A[1]);  
         LeftistHeapNode a2 = new LeftistHeapNode(A[2]);  
         LeftistHeapNode a3 = new LeftistHeapNode(A[3]);  
         LeftistHeapNode a4 = new LeftistHeapNode(A[4]);  
         LeftistHeapNode a5 = new LeftistHeapNode(A[5]);  
         LeftistHeapNode a6 = new LeftistHeapNode(A[6]);  
         LeftistHeapNode a7 = new LeftistHeapNode(A[7]);           
         a0.left = a1;  a0.npl = 1;  
         a0.right = a2;   
         a1.left = a3;  a1.npl = 1;  
         a1.right = a4;  
         a4.left = a6;  
         a2.left = a5;  
         a5.left = a7;  
         LeftistMinHeap h1 = new LeftistMinHeap(a0);  
           
         int[] B = new int[]{6,12,7,18,24,37,18,33};  
         LeftistHeapNode b0 = new LeftistHeapNode(B[0]);  
         LeftistHeapNode b1 = new LeftistHeapNode(B[1]);  
         LeftistHeapNode b2 = new LeftistHeapNode(B[2]);  
         LeftistHeapNode b3 = new LeftistHeapNode(B[3]);  
         LeftistHeapNode b4 = new LeftistHeapNode(B[4]);  
         LeftistHeapNode b5 = new LeftistHeapNode(B[5]);  
         LeftistHeapNode b6 = new LeftistHeapNode(B[6]);  
         LeftistHeapNode b7 = new LeftistHeapNode(B[7]);  
         b0.left = b1;  b0.npl = 2;  
         b0.right = b2;  
         b1.left = b3;  b1.npl = 1;  
         b1.right = b4;   
         b4.left = b7;  
         b2.left = b5;  b2.npl = 1;  
         b2.right = b6;  
         LeftistMinHeap h2 = new LeftistMinHeap(b0);  
           
         heap = LeftistMinHeap.merge(h1,h2);  
         while((min = heap.extractMin()) != null){  
             System.out.format(" %d", min);  
         }  
         System.out.println();   
           
         System.out.println("********************");  
         A = new int[]{1,5,7,10,15,20,25,50,99};  
         a0 = new LeftistHeapNode(A[0]);  
         a1 = new LeftistHeapNode(A[1]);  
         a2 = new LeftistHeapNode(A[2]);  
         a3 = new LeftistHeapNode(A[3]);  
         a4 = new LeftistHeapNode(A[4]);  
         a5 = new LeftistHeapNode(A[5]);  
         a6 = new LeftistHeapNode(A[6]);  
         a7 = new LeftistHeapNode(A[7]);  
         LeftistHeapNode a8 = new LeftistHeapNode(A[8]);      
         a0.left = a1; a0.npl = 2;         
         a0.right = a2;  
         a1.left = a3;  a1.npl = 1;  
         a1.right = a4;  
         a2.left = a5;  a2.npl = 1;  
         a2.right = a6;  
         a5.left = a7;  a5.npl =  1;  
         a5.right = a8;  
         h1 = new LeftistMinHeap(a0);  
           
         B = new int[]{22,75};  
         b0 = new LeftistHeapNode(B[0]);  
         b1 = new LeftistHeapNode(B[1]);  
         b0.left = b1;  
         h2 = new LeftistMinHeap(b0);  
           
         heap = LeftistMinHeap.merge(h1,h2);   
         while((min = heap.extractMin()) != null){  
             System.out.format(" %d", min);  
         }  
         System.out.println();  
    }  
  
}  