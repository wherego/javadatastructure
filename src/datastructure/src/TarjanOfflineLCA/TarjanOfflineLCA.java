package TarjanOfflineLCA;

/** 
 *  
 * Tarjan's off-line LCA algorithm 
 *  
 * Copyright (c) 2011 ljs (http://blog.csdn.net/ljsspace/) 
 * Licensed under GPL (http://www.opensource.org/licenses/gpl-license.php)  
 *  
 *  
 * @author ljs 
 * 2011-08-16 
 * 
 */  
public class TarjanOfflineLCA {  
    // disjoint set's  array  
    private int[] B;  
    //the ancestor of the representative (i.e. root) of a disjoint set  
    private int[] ancestor;  
    //P is provided in advance (i.e. off-line)  
    private Query[] P;  
  
    public TarjanOfflineLCA(int n, Query[] queries) {  
        this.B = new int[n];  
        for(int i=0;i<B.length;i++)  
            makeSet(i);  
        ancestor = new int[n];  
          
        this.P = queries;  
    }  
  
    static class Node {  
        // index  
        int index;  
        Node[] children;  
  
        // for TarjanLCA DFS  
        boolean visited;  
  
        public Node(int index) {  
            this.index = index;  
        }  
  
        public String toString() {  
            return String.valueOf(index);  
        }  
    }  
  
    static class Query {  
        Node u;  
        Node v;  
        int lca;  
  
        public Query(Node u, Node v) {  
            this.u = u;  
            this.v = v;  
        }  
    }  
  
    public void offlineLCA(Node root) {       
        lca(root);  
    }  
  
    private void lca(Node u) {  
        if (u.children != null) {  
            for (Node v : u.children) {  
                lca(v);  
                //u is merged with its processed subtrees  
                union(u.index, v.index);      
                //the representative of equivalence class S  
                int rep = find(u.index);  
                //set the ancestor of S  
                ancestor[rep] = u.index;  
            }  
        }else{  
            ancestor[u.index] = u.index;  
        }  
        //visit u  
        u.visited = true;  
          
        //queried that include u should execute immediately after u is visited  
        for (Query q : P) {  
            Node v = null;   
            if (q.u == u && q.v.visited) {  
                v = q.v;  
            }else if(q.v ==u && q.u.visited){  
                v = q.u;  
            }  
            if(v != null) {  
                int rep = find(v.index);  
                q.lca = ancestor[rep];                
            }  
        }  
    }  
  
    public void makeSet(int x) {      
        B[x] = -1; // rank is 0  
    }  
  
    // union by rank  
    public void unionSets(int root1, int root2) {  
        if (B[root2] < B[root1]) {  
            // root2 is deeper  
            B[root1] = root2;  
        } else {  
            if (B[root1] == B[root2]) {  
                // the same depth, set root1 as the root  
                // increase depth  
                //Note: only when merging two trees with the same ranks can the merged rank be altered.  
                B[root1]--;  
            }  
            B[root2] = root1;  
        }  
    }  
  
    public void union(int x, int y) {  
        unionSets(find(x), find(y));  
    }  
  
    public int find(int x) {  
        if (B[x] < 0)  
            return x; // root  
        else  
            return B[x] = find(B[x]); // path compression  
    }  
  
    public static void main(String[] args) {  
        int[] A = new int[] { 3, 5, 4, 2, 1, 0, 7, 15, 9, 10, 12, 8, 20, 22,  
                25, 17,30,6,40,41 };  
  
        Node n0 = new Node(0);  
        Node n1 = new Node(1);  
        Node n2 = new Node(2);  
        Node n3 = new Node(3);  
        Node n4 = new Node(4);  
        Node n5 = new Node(5);  
        Node n6 = new Node(6);  
        Node n7 = new Node(7);  
        Node n8 = new Node(8);  
        Node n9 = new Node(9);  
        Node n10 = new Node(10);  
        Node n11 = new Node(11);  
        Node n12 = new Node(12);  
        Node n13 = new Node(13);  
        Node n14 = new Node(14);  
        Node n15 = new Node(15);  
        Node n16 = new Node(16);  
        Node n17 = new Node(17);  
        Node n18 = new Node(18);          
        Node n19 = new Node(19);  
        n0.children = new Node[] { n1, n2 };  
        n1.children = new Node[] { n3, n4, n5 };  
        n2.children = new Node[] { n6, n7 };  
        n4.children = new Node[] { n8, n9 };  
        n5.children = new Node[] { n10, n11 };  
        n8.children = new Node[] { n12, n13 };  
        n9.children = new Node[] { n14, n15 };  
        n10.children = new Node[] { n16, n17 };  
        n11.children = new Node[] { n18, n19 };  
  
        Query[] P = { new Query(n8, n5), new Query(n12, n14),  
                new Query(n14, n6), new Query(n6, n7),  
                new Query(n10, n9), new Query(n3, n10),  
                new Query(n14, n15), new Query(n15, n8),  
                new Query(n11, n10), new Query(n13, n12),  
                new Query(n5, n4),new Query(n18, n13),  
                new Query(n4, n17),new Query(n16, n19),  
                new Query(n9, n19),new Query(n10, n19),  
                new Query(n7, n17),new Query(n17, n16),  
                new Query(n18, n19)};         
  
        TarjanOfflineLCA tarjan = new TarjanOfflineLCA(A.length, P);  
        tarjan.offlineLCA(n0);  
  
        //report  
        for(Query q:P){  
            System.out.format("LCA of %d and %d is: %d%n", q.u.index,  
                q.v.index, q.lca);  
        }  
    }  
  
}  