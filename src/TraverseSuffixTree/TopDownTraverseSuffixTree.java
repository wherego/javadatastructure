package TraverseSuffixTree;

import java.util.ArrayList;  
import java.util.LinkedList;  
import java.util.List;   
   
/** 
 *  
 * Top-Down traversal of a suffix tree 
 * (The suffix tree is built with ukk algorithm) 
 *  
 *   
 * Copyright (c) 2011 ljs (http://blog.csdn.net/ljsspace/) 
 * Licensed under GPL (http://www.opensource.org/licenses/gpl-license.php)  
 *  
 * @author ljs 
 * 2011-07-25 
 * 
 */  
public class TopDownTraverseSuffixTree {  
    private class SuffixNode {        
        private StringBuilder sb;  
          
        private List<SuffixNode> children = new LinkedList<SuffixNode>();  
          
        private SuffixNode link;  
        private int start;  
        private int end;  
        private int pathlen;  
          
        private int lb;  
        private int rb;  
          
          
        public SuffixNode(StringBuilder sb,int start,int end,int pathlen){    
            this.sb = sb;  
            this.start = start;  
            this.end = end;  
            this.pathlen = pathlen;  
        }  
        public SuffixNode(StringBuilder sb){          
            this.sb = sb;  
            this.start = -1;  
            this.end = -1;        
            this.pathlen = 0;  
        }  
        public int getLength(){  
            if(start == -1) return 0;  
            else return end - start + 1;  
        }  
        public String getString(){  
            if(start != -1){  
                return this.sb.substring(start,end+1);  
            }else{  
                return "";  
            }  
        }  
        public boolean isRoot(){  
            return start == -1;  
        }  
        public String getCoordinate(){  
            return "[" + start+".." + end + "/" + this.pathlen + "]";  
        }  
        public String toString(){             
            return getString() + "(" + getCoordinate()   
                + ",link:" + ((this.link==null)?"N/A":this.link.getCoordinate())   
                + ",children:" + children.size() +")";  
        }        
    }  
    private class State{  
        private SuffixNode u; //parent(v)  
        //private SuffixNode w;    
        private SuffixNode v;    
        //private int k; //the global index of text starting from 0 to text.length()  
        //private boolean finished;    
    }  
      
    private SuffixNode root;  
    private StringBuilder sb = new StringBuilder();  
       
      
    //build a suffix-tree for a string of text  
    public void  buildSuffixTree(String text) throws Exception{   
        int m = text.length();  
          
        if(m==0)  
            return;  
          
        if(root==null){  
            root = new SuffixNode(sb);                
            root.link = root; //link to itself  
        }  
          
        List<SuffixNode> leaves =  new ArrayList<SuffixNode>();  
          
        //add first node  
        sb.append(text.charAt(0));  
        SuffixNode node = new SuffixNode(sb,0,0,1);  
        leaves.add(node);  
        root.children.add(node);      
        int j_star = 0; //j_{i-1}  
          
        SuffixNode u = root;  
        SuffixNode v = root;              
        for(int i=1;i<=m-1;i++){           
            //do phase i  
            sb.append(text.charAt(i));  
              
            //step 1: do implicit extensions   
            for(SuffixNode leafnode:leaves){  
                leafnode.end++;  
                leafnode.pathlen++;  
            }  
              
            //step 2: do explicit extensions until rule #3 is applied             
            State state = new State();    
              
            //for the first explicit extension, we reuse the last phase's u and do slowscan  
            //also note: suffix link doesn't span two phases.  
            int j=j_star+1;  
            SuffixNode s = u;          
            int k = s.pathlen + j;        
            state.u = s;              
            state.v = s;    
            SuffixNode newleaf = slowscan(state,s,k);  
            if(newleaf == null){  
                //if rule #3 is applied, then we can terminate this phase  
                j_star = j - 1;  
                //Note: no need to update state.v because it is not going to be used  
                //at the next phase  
                u = state.u;  
                continue;  
            }else{            
                  
                j_star = j;  
                leaves.add(newleaf);  
                  
                u = state.u;  
                v = state.v;  
            }         
            j++;  
              
            //for other explicit extensions, we start with fast scan.  
            for(;j<=i;j++){  
                s = u.link;  
                  
                int uvLen=v.pathlen - u.pathlen;          
                if(u.isRoot() && !v.isRoot()){  
                    uvLen--;  
                }  
                //starting with index k of the text  
                k = s.pathlen + j;        
                  
                  
                //init state  
                state.u = s;              
                state.v = s; //if uvLen = 0   
                  
                //execute fast scan  
                newleaf = fastscan(state,s,uvLen,k);                  
                //establish the suffix link with v        
                v.link = state.v;  
                  
                if(newleaf == null){  
                    //if rule #3 is applied, then we can terminate this phase  
                    j_star = j - 1;  
                    u = state.u;  
                    break;  
                }else{  
                      
                    j_star = j;  
                    leaves.add(newleaf);  
                      
                    u = state.u;  
                    v = state.v;  
                }             
            }  
        }  
    }  
    //slow scan from currNode until state.v is found  
    //return the new leaf if a new one is created right after v;  
    //return null otherwise (i.e. when rule #3 is applied)  
    private SuffixNode slowscan(State state,SuffixNode currNode,int k){  
        SuffixNode newleaf = null;  
          
        boolean done = false;         
        int keyLen = sb.length() - k;  
        for(int i=0;i<currNode.children.size();i++){  
            SuffixNode child = currNode.children.get(i);  
              
            //use min(child.key.length, key.length)           
            int childKeyLen = child.getLength();  
            int len = childKeyLen<keyLen?childKeyLen:keyLen;  
            int delta = 0;  
            for(;delta<len;delta++){  
                if(sb.charAt(k+delta) != sb.charAt(child.start+delta)){  
                    break;  
                }  
            }  
            if(delta==0){//this child doesn't match any character with the new key            
                //order keys by lexi-order  
                if(sb.charAt(k) < sb.charAt(child.start)){  
                    //e.g. child="e" (currNode="abc")  
                    //     abc                     abc  
                    //    /  \    =========>      / | \  
                    //   e    f   insert "c"     c  e  f  
                    int pathlen = sb.length() - k + currNode.pathlen;  
                    SuffixNode node = new SuffixNode(sb,k,sb.length()-1,pathlen);  
                    currNode.children.add(i,node);        
                    //state.u = currNode; //currNode is already registered as state.u, so commented out  
                    state.v = currNode;  
                    newleaf = node;  
                    done = true;  
                    break;                    
                }else{ //key.charAt(0)>child.key.charAt(0)  
                    //don't forget to add the largest new key after iterating all children  
                    continue;  
                }  
            }else{//current child's key partially matches with the new key    
                if(delta==len){  
                    if(keyLen==childKeyLen){                          
                        //e.g. child="ab"  
                        //     ab                    ab  
                        //    /  \    =========>    /  \  
                        //   e    f   insert "ab"  e    f  
                        //terminate this phase  (implicit tree with rule #3)          
                        state.u = child;  
                        state.v = currNode;  
                    }else if(keyLen>childKeyLen){   
                        //TODO: still need an example to test this condition  
                        //e.g. child="ab"  
                        //     ab                      ab  
                        //    /  \    ==========>     / | \                            
                        //   e    f   insert "abc"   c e  f       
                        //recursion  
                        state.u = child;  
                        state.v = child;  
                        k += childKeyLen;  
                        //state.k = k;  
                        newleaf = slowscan(state,child,k);  
                    }  
                    else{ //keyLen<childKeyLen  
                        //e.g. child="abc"  
                        //     abc                      abc  
                        //    /   \      =========>     /  \   
                        //   e     f     insert "ab"   e   f         
                        //                              
                        //terminate this phase  (implicit tree with rule #3)  
                        //state.u = currNode;  
                        state.v = currNode;  
                    }  
                }else{//0<delta<len   
              
                    //e.g. child="abc"  
                    //     abc                     ab  
                    //    /  \     ==========>     / \  
                    //   e    f   insert "abd"    c   d   
                    //                           /  \  
                    //                          e    f                    
                    //insert the new node: ab   
                    int nodepathlen = child.pathlen   
                            - (child.getLength()-delta);  
                    SuffixNode node = new SuffixNode(sb,  
                            child.start,child.start + delta - 1,nodepathlen);   
                    node.children = new LinkedList<SuffixNode>();  
                      
                    int leafpathlen = (sb.length() - (k + delta)) + nodepathlen;  
                    SuffixNode leaf = new SuffixNode(sb,  
                            k+delta,sb.length()-1,leafpathlen);  
                      
                    //update child node: c  
                    child.start += delta;  
                    if(sb.charAt(k+delta)<sb.charAt(child.start)){  
                        node.children.add(leaf);  
                        node.children.add(child);  
                    }else{  
                        node.children.add(child);  
                        node.children.add(leaf);                              
                    }  
                    //update parent  
                    currNode.children.set(i, node);  
                      
                    //state.u = currNode; //currNode is already registered as state.u, so commented out  
                    state.v = node;  
                    newleaf = leaf;           
                }  
                done = true;  
                break;  
            }  
        }  
        if(!done){  
            int pathlen = sb.length() - k + currNode.pathlen;  
            SuffixNode node = new SuffixNode(sb,k,sb.length()-1,pathlen);  
            currNode.children.add(node);  
            //state.u = currNode; //currNode is already registered as state.u, so commented out  
            state.v = currNode;   
            newleaf = node;  
        }  
          
        return newleaf;  
    }  
      
      
    //fast scan until state.v is found;  
    //return the new leaf if a new one is created right after v;  
    //return null otherwise (i.e. when rule #3 is applied)  
    private SuffixNode fastscan(State state,SuffixNode currNode,int uvLen,int k){                 
        if(uvLen==0){  
            //state.u = currNode; //currNode is already registered as state.u, so commented out  
            //continue with slow scan  
            return slowscan(state,currNode,k);    
        }  
          
        SuffixNode newleaf = null;  
        boolean done  = false;  
        for(int i=0;i<currNode.children.size();i++){  
            SuffixNode child = currNode.children.get(i);  
              
            if(sb.charAt(child.start) == sb.charAt(k)){               
                int len = child.getLength();  
                if(uvLen==len){  
                    //then we find v              
                    //uvLen = 0;                      
                    state.u = child;      
                    //state.v = child;  
                    k += len;  
                    //state.k = k;  
                      
                    //continue with slow scan  
                    newleaf = slowscan(state,child,k);                    
                }else if(uvLen<len){  
                    //we know v must be an internal node; branching and cut child short                               
                    //e.g. child="abc",uvLen = 2  
                    //     abc                          ab  
                    //    /  \    ================>     / \  
                    //   e    f   suffix part: "abd"   c   d   
                    //                                /  \  
                    //                               e    f               
                      
                    //insert the new node: ab; child is now c   
                    int nodepathlen = child.pathlen   
                            - (child.getLength()-uvLen);  
                    SuffixNode node = new SuffixNode(sb,  
                            child.start,child.start + uvLen - 1,nodepathlen);   
                    node.children = new LinkedList<SuffixNode>();  
                      
                    int leafpathlen = (sb.length() - (k + uvLen)) + nodepathlen;  
                    SuffixNode leaf = new SuffixNode(sb,  
                            k+uvLen,sb.length()-1,leafpathlen);  
                      
                    //update child node: c  
                    child.start += uvLen;  
                    if(sb.charAt(k+uvLen)<sb.charAt(child.start)){  
                        node.children.add(leaf);  
                        node.children.add(child);  
                    }else{  
                        node.children.add(child);  
                        node.children.add(leaf);                              
                    }  
              
                    //update parent  
                    currNode.children.set(i, node);  
                      
                    //uvLen = 0;  
                    //state.u = currNode; //currNode is already registered as state.u, so commented out  
                    state.v = node;               
                    newleaf = leaf;  
                }else{//uvLen>len  
                    //e.g. child="abc", uvLen = 4  
                    //     abc                            
                    //    /  \    ================>        
                    //   e    f   suffix part: "abcde"     
                    //                                  
                    //                    
                    //jump to next node  
                    uvLen -= len;  
                    state.u = child;  
                    //state.v = child;  
                    k += len;  
                    //state.k = k;  
                    newleaf = fastscan(state,child,uvLen,k);  
                }  
                done = true;  
                break;  
            }  
        }         
        if(!done){            
            //TODO: still need an example to test this condition  
            //add a leaf under the currNode  
            int pathlen = sb.length() - k + currNode.pathlen;  
            SuffixNode node = new SuffixNode(sb,k,sb.length()-1,pathlen);  
            currNode.children.add(node);  
            //state.u = currNode; //currNode is already registered as state.u, so commented out  
            state.v = currNode;   
            newleaf = node;  
        }  
          
        return newleaf;  
    }  
      
    
      
    private int maxk=0; //the suffix array index   
    private void prepare(SuffixNode currNode){  
        for(int i=0;i<currNode.children.size();i++){  
            SuffixNode child = currNode.children.get(i);  
              
            prepare(child);                               
        }   
        if(!currNode.children.isEmpty()){  
            currNode.lb = currNode.children.get(0).lb;  
            currNode.rb = currNode.children.get(currNode.children.size()-1).rb;  
        }else{  
            currNode.lb = currNode.rb = maxk;  
              
            maxk++;  
        }  
    }  
    public void topDownTraverse(){    
        //prepare lb and rb for each internal node  
        prepare(root);   
        topDownTraverse(root);        
    }  
      
    public void topDownTraverse(SuffixNode currNode){  
        if(!currNode.children.isEmpty())  
            visit(currNode);  
          
        for(int i=0;i<currNode.children.size();i++){  
            SuffixNode child = currNode.children.get(i);  
              
            if(!child.children.isEmpty()){                
                topDownTraverse(child);                   
            }             
        }   
    }  
    //visit internal node  
    private void visit(SuffixNode node){  
        String interval = String.format("%d-[%d..%d]",   
                node.pathlen,node.lb,node.rb);  
          
        if(node.children.size()>0){  
            StringBuilder sb = new StringBuilder();  
            int internalNodes = 0;  
            for(SuffixNode child:node.children){  
                if(!child.children.isEmpty()){  
                    internalNodes++;  
                    String childInterval = String.format("%d-[%d..%d]",   
                            child.pathlen,child.lb,child.rb);  
                    sb.append(childInterval);  
                    sb.append(",");  
                }  
            }  
            if(internalNodes>0){  
                sb.deleteCharAt(sb.length()-1);  
                System.out.format("%s, children={%s}%n",   
                        interval,sb.toString());              
            }else{  
                System.out.format("%s%n", interval);      
            }                 
        }else{  
            System.out.format("%s%n", interval);      
        }         
    }  
    //for test purpose only  
    public void printTree(){  
        System.out.format("The suffix tree for S = %s is: %n",this.sb);  
        this.print(0, this.root);  
    }  
    private void print(int level, SuffixNode node){  
        for (int i = 0; i < level; i++) {  
            System.out.format(" ");  
        }  
        System.out.format("|");  
        for (int i = 0; i < level; i++) {  
            System.out.format("-");  
        }  
        System.out.format("%s(%d..%d/%d)%n", node.getString(),node.start,node.end,node.pathlen);  
        //System.out.format("(%d,%d)%n", node.start,node.end);  
        for (SuffixNode child : node.children) {  
            print(level + 1, child);  
        }         
    }  
    public static void main(String[] args) throws Exception {  
        //test suffix-tree  
        System.out.println("****************************");       
        String text = "mississippi#"; //the last char must be unique!  
        TopDownTraverseSuffixTree stree = new TopDownTraverseSuffixTree();  
        stree.buildSuffixTree(text);  
        //stree.printTree();   
        System.out.format("%nInternal Nodes for text: %s %n",text);  
        stree.topDownTraverse();          
        System.out.println();  
           
        System.out.println("****************************");       
        text = "GACCCACCACC#"; //the last char must be unique!  
        stree = new TopDownTraverseSuffixTree();  
        stree.buildSuffixTree(text);  
        //stree.printTree();   
        System.out.format("Internal Nodes for text: %s %n",text);  
        stree.topDownTraverse();          
        System.out.println();  
           
        System.out.println("****************************");       
        text = "abcdefghijklmmnopqrstuvwxyz#"; //the last char must be unique!  
        stree = new TopDownTraverseSuffixTree();  
        stree.buildSuffixTree(text);  
        //stree.printTree();   
        System.out.format("Internal Nodes for text: %s %n",text);  
        stree.topDownTraverse();  
          
        System.out.println("****************************");       
        text = "yabbadabbado#"; //the last char must be unique!  
        stree = new TopDownTraverseSuffixTree();  
        stree.buildSuffixTree(text);  
        //stree.printTree();   
        System.out.format("Internal Nodes for text: %s %n",text);  
        stree.topDownTraverse();  
          
        System.out.println("****************************");       
        text = "AAAAAAAAAAAAAAAAAAAAAAAAAA#"; //the last char must be unique!  
        stree = new TopDownTraverseSuffixTree();  
        stree.buildSuffixTree(text);  
        //stree.printTree();   
        System.out.format("Internal Nodes for text: %s %n",text);  
        stree.topDownTraverse();  
          
        System.out.println("****************************");       
        text = "GGGGGGGGGGGGCGCAAAAGCGAGCAGAGAGAAAAAAAAAAAAAAAAAAAAAA#"; //the last char must be unique!  
        stree = new TopDownTraverseSuffixTree();  
        stree.buildSuffixTree(text);  
        //stree.printTree();   
        System.out.format("Internal Nodes for text: %s %n",text);  
        stree.topDownTraverse();  
    }  
}  