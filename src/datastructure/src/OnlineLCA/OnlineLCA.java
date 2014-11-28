package OnlineLCA;

import java.util.LinkedList;  


/** 
 *  
 * Online LCA algorithm 
 * complexity: <O(n),O(1)> 
 *  
 * see also: B. Schieber & U. Vishkin (1988) "On finding lowest common ancestors - Simplification and parallelization" 
 *  
 * Copyright (c) 2011 ljs (http://blog.csdn.net/ljsspace/) 
 * Licensed under GPL (http://www.opensource.org/licenses/gpl-license.php)  
 *  
 *  
 * @author ljs 
 * 2011-08-23 
 * 
 */  
public class OnlineLCA {  
    static class Node{  
        int key;  
        Node[] children;  
          
        Node parent;  
        int inlabel;  
        int ascendant;  
        int level;  
        public Node(int key){  
            this.key = key;  
        }  
        public String toString() {  
            return String.valueOf(key) + " parent:" +   
                (this.parent==null?"":this.parent.key) +   
                " inlabel:" + inlabel + " level:" + level +   
                " ascendant:" + ascendant;  
        }  
    }  
    //the leaders of each inlabel path  
    private Node[] HEAD;  
    //the bits of inlabel and ascendent  
    private int l;  
    private Node root;  
      
    public OnlineLCA(int n,Node root){  
        HEAD = new Node[n+1];  
        this.root = root;  
        l = (int)(Math.log(n) / Math.log(2));  
    }  
    //preprocess tree T  
    public void preprocess(){  
        this.root.level = 0;  
        doInOrderTraverse(this.root);  
          
        doLevelOrderTraverse();  
    }  
      
    //return the LCA node of x and y  
    public Node query(Node x,Node y){  
        Node lca = null;  
        if(x.inlabel==y.inlabel){  
            if(x.level<y.level)   
                lca = x;  
            else  
                lca = y;  
        }else{  
            //find i (lca of x.inlabel and y.inlabel in complete binary tree B)            
            int i = findLSBOne(x.inlabel,0);   
            int tmp = findLSBOne(y.inlabel,0);   
            if(tmp>i)  
                i=tmp;  
            int diff = x.inlabel ^ y.inlabel;  
            tmp = (int)(Math.log(diff) / Math.log(2));  
            if(tmp>i)  
                i=tmp;  
              
            //find j and z.inlabel (z is lca of x and y)  
            int ascand = x.ascendant & y.ascendant;  
            int j = findLSBOne(ascand,i);   
            int inlabelz = findInlabel(x,j);   
              
            //find x-hat and y-hat  
            Node x_hat = findHat(x,inlabelz,j);  
            Node y_hat = findHat(y,inlabelz,j);  
            if(x_hat.level<y_hat.level)  
                lca = x_hat;  
            else  
                lca = y_hat;  
        }  
        return lca;  
    }  
      
    private Node findHat(Node x,int inlabelz,int j){  
        Node x_hat = null;  
        if(x.inlabel == inlabelz){  
            x_hat = x;  
        }else{  
            int ascsub = x.ascendant & ((1<<j)-1); //000.A{j-1}...A{0}  
            int k = (int)(Math.log(ascsub) / Math.log(2));  
            //compute inlabel(w) path                 
            int inlabelw = findInlabel(x,k);  
            Node w = HEAD[inlabelw];  
            x_hat = w.parent;  
        }  
        return x_hat;  
    }  
      
    private int findInlabel(Node x,int j){  
        int mask = ((1<<(l-j)) - 1)<<(j+1); //e.g. 1110000  
        int inlabel = (x.inlabel & mask) + (1<<j); //1111000  
        return inlabel;  
    }  
   
    //find the index of rightmost "1" in num's binary form  
    private int findLSBOne(int num,int rightStart){       
        int i = rightStart;  
        num >>= rightStart;         
        while((num & 0x01) != 0x01){  
            num >>=1;  
            i++;                              
        }  
        return i;  
    }  
    //calculate ascendants and head based on inlabel numbers  
    //so this method must be called after inlabels are determined.  
    private void doLevelOrderTraverse(){  
        LinkedList<Node> queue = new LinkedList<Node>();  
        queue.add(this.root);   
        HEAD[this.root.inlabel] = this.root;  
        this.root.ascendant = (1<<l); //2^l  
        while(!queue.isEmpty()){  
            Node parent = queue.remove();             
            //enqueue children  
            if(parent.children != null){  
                for(Node child:parent.children){  
                    if(child.inlabel != parent.inlabel){  
                        HEAD[child.inlabel] = child;  
                          
                        //calculate ascendant   
                        int i = findLSBOne(child.inlabel,0);  
                        child.ascendant = parent.ascendant + (1<<i);  
                    }else{  
                        child.ascendant = parent.ascendant;  
                    }  
                    queue.add(child);  
                }  
            }  
        }  
    }  
       
    private int nr=1;  
    private int doInOrderTraverse(Node node){  
        //visit node   
        int preorder = nr;  
        nr++;  
          
        int size = 1;  
        if(node.children != null){  
            for(Node child:node.children){  
                child.parent = node; //update parent  
                child.level = node.level + 1; //calculate level values  
                size += doInOrderTraverse(child);  
            }  
        }  
        //calculate inlabel(node)  
        int intervalMax = preorder+size-1;  
        int diff = (preorder-1) ^ intervalMax;  
        int i = (int)(Math.log(diff) / Math.log(2));  
        int inlabel = intervalMax & ~((1<<i) - 1);  
        node.inlabel = inlabel;  
          
        return size;  
    }  
    public void lcaReport(Node x,Node y){  
        Node lca = this.query(x,y);  
        System.out.format("LCA of %d and %d is: %d%n",x.key,y.key,lca.key);  
    }  
    public static void main(String[] args) {  
        int[] A = new int[]{10,20,30,40,50,60,70,80,90,100};  
        Node a0 = new Node(A[0]);  
        Node a1 = new Node(A[1]);  
        Node a2 = new Node(A[2]);  
        Node a3 = new Node(A[3]);  
        Node a4 = new Node(A[4]);  
        Node a5 = new Node(A[5]);  
        Node a6 = new Node(A[6]);  
        Node a7 = new Node(A[7]);  
        Node a8 = new Node(A[8]);  
        Node a9 = new Node(A[9]);  
          
        a0.children = new Node[]{a1,a4};  
        a1.children = new Node[]{a2,a3};  
        a4.children = new Node[]{a5,a6,a7};  
        a7.children = new Node[]{a8,a9};  
          
        OnlineLCA onlineLCA = new OnlineLCA(A.length,a0);  
          
        onlineLCA.preprocess();  
          
        //queries  
        onlineLCA.lcaReport(a2,a3);  
        onlineLCA.lcaReport(a2,a9);  
        onlineLCA.lcaReport(a6,a9);  
        onlineLCA.lcaReport(a7,a8);  
        onlineLCA.lcaReport(a6,a7);  
        onlineLCA.lcaReport(a9,a8);  
        onlineLCA.lcaReport(a4,a9);  
          
        System.out.println("************************");  
        int[] B = new int[]{10,20,30,40,50,60,70,80,90,100,  
                110,120,130,140,150,160,170,180,190,200,210,220};  
        Node b0 = new Node(B[0]);  
        Node b1 = new Node(B[1]);  
        Node b2 = new Node(B[2]);  
        Node b3 = new Node(B[3]);  
        Node b4 = new Node(B[4]);  
        Node b5 = new Node(B[5]);  
        Node b6 = new Node(B[6]);  
        Node b7 = new Node(B[7]);  
        Node b8 = new Node(B[8]);  
        Node b9 = new Node(B[9]);  
        Node b10 = new Node(B[10]);  
        Node b11 = new Node(B[11]);  
        Node b12 = new Node(B[12]);  
        Node b13 = new Node(B[13]);  
        Node b14 = new Node(B[14]);  
        Node b15 = new Node(B[15]);  
        Node b16 = new Node(B[16]);  
        Node b17 = new Node(B[17]);  
        Node b18 = new Node(B[18]);  
        Node b19 = new Node(B[19]);  
        Node b20 = new Node(B[20]);  
        Node b21 = new Node(B[21]);  
          
        b0.children = new Node[]{b1,b10,b12};  
        b1.children = new Node[]{b2};  
        b2.children = new Node[]{b3,b6,b9};  
        b3.children = new Node[]{b4,b5};  
        b6.children = new Node[]{b7};  
        b7.children = new Node[]{b8};  
          
        b10.children = new Node[]{b11};  
        b12.children = new Node[]{b13};  
        b13.children = new Node[]{b14};  
        b14.children = new Node[]{b15,b16,b17,b21};  
        b17.children = new Node[]{b18};  
        b18.children = new Node[]{b19,b20};  
          
        onlineLCA = new OnlineLCA(B.length,b0);  
          
        onlineLCA.preprocess();  
          
        //queries  
        onlineLCA.lcaReport(b3,b6);  
        onlineLCA.lcaReport(b16,b17);  
        onlineLCA.lcaReport(b8,b21);  
        onlineLCA.lcaReport(b12,b19);         
        onlineLCA.lcaReport(b15,b21);  
        onlineLCA.lcaReport(b19,b20);  
        onlineLCA.lcaReport(b11,b18);  
        onlineLCA.lcaReport(b4,b7);  
        onlineLCA.lcaReport(b3,b10);  
        onlineLCA.lcaReport(b5,b9);  
        onlineLCA.lcaReport(b2,b19);  
        onlineLCA.lcaReport(b5,b18);  
        onlineLCA.lcaReport(b0,b4);  
        onlineLCA.lcaReport(b1,b4);  
        onlineLCA.lcaReport(b4,b8);  
        onlineLCA.lcaReport(b2,b14);  
    }  
  
}  
