package SplayTree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class SplayTree {
    static class Node{
        int val;
        Node left;
        Node right;
        public Node(int val){
            this.val = val;
        }
        public String toString(){
            return String.valueOf(val);
        }
    }
    private Node root;
    public SplayTree(Node root){
        this.root = root;
    }
          
    public Node getRoot() {
        return root;
    }
    //find a node in the tree 
    //return true if found; false otherwise
    public boolean find(int x) throws Exception{
        if(root == null) throw new Exception("tree is empty.");
        splay(x);
        if(root.val == x){
            return true;
        }else{
            return false;
        }
    }
    private Node findMax(Node rootNode) throws Exception{
        if(rootNode == null) throw new Exception("tree or subtree is empty.");
                  
        rootNode = splay(rootNode,Integer.MAX_VALUE);
        return rootNode; //rootNode.val=max
    }
    public int findMax() throws Exception{
        root = findMax(this.root);
        return root.val;
    }
      
    private Node findMin(Node rootNode) throws Exception{
        if(rootNode == null) throw new Exception("tree or subtree is empty.");
          
          
        rootNode = splay(rootNode,Integer.MIN_VALUE);
        return rootNode; //rootNode.val=min
    }
      
    public int findMin() throws Exception{
        root = findMin(this.root);
        return root.val;
    }
    public int deleteMax() throws Exception{
        int max = findMax();
        this.root = root.left;
        return max;
    }
    public int deleteMin() throws Exception{
        int min = findMin();
        this.root = root.right;
        return min;
    }
      
    public void insert(int x) throws Exception {
        if(root == null){
            //set the new node as root
            this.root = new Node(x);
        }else{
            splay(x);
            if(root.val == x){
                throw new Exception("duplicate value!");
            }else if(x<root.val){
                //split the splayed tree with right subtree including root, and set the new node as root
                Node tmp = new Node(x);
                tmp.left = this.root.left;
                tmp.right = this.root;
                root.left = null;
                this.root = tmp;
            }else {//ie. x>root.val
                //split the splayed tree with left subtree including root, and set the new node as root
                Node tmp = new Node(x);
                tmp.left = this.root;
                tmp.right = this.root.right;
                root.right = null;
                this.root = tmp;
            }
        }
    }
    public void remove(int x) throws Exception {
        if(root == null) throw new Exception("tree is empty.");
          
        splay(x);
        if(x != root.val){
            throw new Exception("value not found.");
        }
          
        if(root.left == null){
            //root(root.val==x) is the min node
            root = root.right;
        }else{
            //find the max value from left subtree, and
            //then remove root and join the right subtree with the left splayed subtree
            Node leftSubTreeRoot = this.findMax(this.root.left);
            leftSubTreeRoot.right = this.root.right;
            root = leftSubTreeRoot;
        }
          
    }
    private void rotateLeftChild(Node grandparent,Node parent){
        grandparent.left = parent.right;
          
        parent.right = grandparent; 
        //split the parent with middle tree     
        parent.left = null;     
    }
      
    private void rotateRightChild(Node grandparent,Node parent){
        grandparent.right = parent.left;
          
        parent.left = grandparent; 
        //split the parent with middle tree
        parent.right = null;        
    }
    //x: the target value to be found for splaying
    public void splay(int x){
        this.root = splay(this.root,x);     
    }
    //x: the target value to be found for splaying
    //rootNode: the root node of the tree to be splayed
    //return the new root of the splayed tree or subtree
    private Node splay(Node rootNode,int x){
        if(rootNode == null) return null;
          
        Node pseudoNode = new Node(Integer.MAX_VALUE);
        //left tree root (no left child)
        Node leftMax = pseudoNode;
        //right tree root (no right child)
        Node rightMin = pseudoNode;
          
        Node t =  rootNode;
        while(true){
            if(x == t.val){
                break;
            }else if(x<t.val){
                //Note: the variable parent is target's parent, the variable t is target's grandparent
                Node parent = t.left;
                if(parent == null){
                    break;
                }else{                  
                    if(x < parent.val){
                        if(parent.left == null){
                            //zig
                            t.left = null;
                            rightMin.left = t;
                            rightMin = t;
                            t = parent;
                        }else{
                            //zig-zig                           
                            Node tmp = parent.left;                         
                              
                            rotateLeftChild(t,parent);
                              
                            //update right tree and its min node
                            rightMin.left = parent;
                            rightMin = parent;              
                              
                            //update the middle tree's root
                            t = tmp;
                        }                       
                    }else{ //ie. x >= t.left.val
                        //zig or zig-zag(simplified to zig)
                        t.left = null;
                        rightMin.left = t;
                        rightMin = t;
                        t = parent;
                    }
                }               
            }else{ //ie. x>t.val
                Node parent = t.right;
                if(parent == null){
                    break;
                }else{                  
                    if(x > parent.val){
                        if(parent.right == null){
                            //zag
                            t.right = null;
                            leftMax.right = t;
                            leftMax = t;
                            t = parent;
                        }else{
                            //zag-zag
                            Node tmp = parent.right;
                              
                            rotateRightChild(t,parent);
                              
                            //update left tree and its max node
                            leftMax.right = parent;
                            leftMax = parent;
                              
                            //update the middle tree's root
                            t = tmp;
                        }                       
                    }else{ //ie. x <= t.right.val
                        //zag or zag-zig (simplified to zag)
                        t.right = null;
                        leftMax.right = t;
                        leftMax = t;
                        t = parent;
                    }
                }
            }
        }
        //re-assemble (note: even if the above while is not executed, the following code works as expected.)
        leftMax.right = t.left;
        rightMin.left = t.right;
          
        t.left = pseudoNode.right;  //pseudoNode.right is the root of left tree
        t.right = pseudoNode.left;  //pseudoNode.left is the root of right tree 
                  
        return t;
    }
      
    //utility method for test purpose
    public static int getNumberOfNodes(Node root){
        int tmp = NODES;
        NODES = 0;
        return tmp;
    }
    public static int NODES=0;
    public static void recursiveInOrderTraverse(Node root){
        if(root == null)return;
          
        recursiveInOrderTraverse(root.left);
        System.out.format(" %d", root.val);
        NODES++;
        recursiveInOrderTraverse(root.right);
    }
    //utility method for test purpose
    //n: the nodes number of the tree
    public static void displayBinaryTree(Node root,int n){      
        if(root == null) return;
          
        LinkedList<Node> queue = new LinkedList<Node>();
          
        //all nodes in each level
        List<List<Node>> nodesList = new ArrayList<List<Node>>();
          
        //the positions in a displayable tree for each level's nodes
        List<List<Integer>> nextPosList = new ArrayList<List<Integer>>();
          
        queue.add(root);
        //int level=0;
        int levelNodes = 1;     
          
        int nextLevelNodes = 0;     
        List<Node> levelNodesList = new ArrayList<Node>();  
        List<Integer> nextLevelNodesPosList = new ArrayList<Integer>();
          
        int pos = 0;  //the position of the current node
        List<Integer> levelNodesPosList = new ArrayList<Integer>();
        levelNodesPosList.add(0); //root position
        nextPosList.add(levelNodesPosList);
        int levelNodesTotal = 1;
        while(!queue.isEmpty()) {
            Node node = queue.remove();
              
            if(levelNodes==0){       
                nodesList.add(levelNodesList);
                nextPosList.add(nextLevelNodesPosList);
                levelNodesPosList = nextLevelNodesPosList;
                  
                levelNodesList = new ArrayList<Node>();       
                nextLevelNodesPosList = new ArrayList<Integer>();
                  
                //level++; 
                levelNodes = nextLevelNodes;
                levelNodesTotal = nextLevelNodes;
                  
                nextLevelNodes = 0;             
            }       
            levelNodesList.add(node);           
              
            pos = levelNodesPosList.get(levelNodesTotal - levelNodes);          
            if(node.left != null){
                queue.add(node.left);
                nextLevelNodes++;       
                nextLevelNodesPosList.add(2*pos);
            }
                      
            if(node.right != null) {
                queue.add(node.right);
                nextLevelNodes++;       
                  
                nextLevelNodesPosList.add(2*pos+1);
            }
                          
            levelNodes--;           
        }
        //save the last level's nodes list
        nodesList.add(levelNodesList);
          
        int maxLevel = nodesList.size()-1;  //==level
           
        //use both nodesList and nextPosList to set the positions for each node
          
        //Note: expected max columns: 2^(level+1) - 1
        int cols = 1;
        for(int i=0;i<=maxLevel;i++){
            cols <<= 1;           
        }
        cols--;
        Node[][] tree = new Node[maxLevel+1][cols];
          
        //load the tree into an array for later display 
        for(int currLevel=0;currLevel<=maxLevel;currLevel++){
            levelNodesList = nodesList.get(currLevel);
            levelNodesPosList = nextPosList.get(currLevel);
            //Note: the column for this level's j-th element: 2^(maxLevel-level)*(2*j+1) - 1
            int tmp = maxLevel-currLevel;
            int coeff = 1;
            for(int i=0;i<tmp;i++){
                coeff <<= 1;          
            }
            for(int k=0;k<levelNodesList.size();k++){
                int j = levelNodesPosList.get(k);
                int col = coeff*(2*j + 1) - 1;              
                tree[currLevel][col] = levelNodesList.get(k);
            }
        }
          
        //display the binary search tree
        System.out.format("%n");
        for(int i=0;i<=maxLevel;i++){
            for(int j=0;j<cols;j++){
                Node node = tree[i][j];
                if(node == null)
                    System.out.format("  ");
                else
                    System.out.format("-",node.val);
            }
            System.out.format("%n");
        }
    }
      
    public static void printAfterSplayed(SplayTree splayTree){
        Node root = splayTree.getRoot();
        System.out.format("%nAfter being splayed, in-order BST:%n");        
        SplayTree.recursiveInOrderTraverse(root);
          
        System.out.format("%n%n%nAfter being splayed, the tree is:");
        SplayTree.displayBinaryTree(root,SplayTree.getNumberOfNodes(root));
      
    }     
}
