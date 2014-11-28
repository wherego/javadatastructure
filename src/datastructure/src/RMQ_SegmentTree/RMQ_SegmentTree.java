package RMQ_SegmentTree;

public class RMQ_SegmentTree {

	  public int[] buildSegmentTree(int[] A){  
	        int n = A.length;  
	        int heapHeight = (int)(Math.log(n)/Math.log(2))+1;//h=floor(logN)+1; h starts with 0.  
	        int mSize = (1<<(heapHeight+1))-1; //total nodes = 2^(h+1)-1  
	          
	        int[] M = new int[mSize];  
	          
	        buildSegmentTree(M, 0, A, 0, n-1);  
	        return M;  
	    }  
	      
	    private void buildSegmentTree(int[] M,int node,int[] A,int i,int j){          
	        if(i==j){  
	            M[node] = i;  
	        }else{  
	            int leftnode=2*node+1;  
	            int rightnode=leftnode+1;  
	              
	            buildSegmentTree(M,leftnode,A,i,(i+j)/2);  
	            buildSegmentTree(M,rightnode,A,(i+j)/2+1,j);  
	              
	            if(A[M[leftnode]]<=A[M[rightnode]]){  
	                M[node] = M[leftnode];  
	            }else{  
	                M[node] = M[rightnode];  
	            }  
	        }  
	    }  
	      
	    //x..y is the query interval of RMQ  
	    public int query(int[] M,int[] A,int x,int y){  
	        int n=A.length;  
	        return query(M,0,A,0,n-1,x,y);  
	    }  
	      
	    //x..y is the query interval of RMQ  
	    //i..j is the current interval of a segment sub-tree's root  
	    private int query(int[] M,int node,int[] A,int i,int j,int x,int y){          
	        //if the query interval doesn't intersect the current interval   
	        //return -1  
	        if (x>j || y<i)  
	            return -1;  
	             
	        //if the query interval entirely covers the current interval  
	        //return the current node's minimum index  
	        if(x<=i && y>=j){  
	            return M[node];  
	        }  
	          
	        //split query interval  
	        int leftnode=2*node+1;  
	        int rightnode=leftnode+1;  
	          
	        int mid = (i+j)/2;  
	        if(x>mid){  
	            //right branch  
	            return query(M,rightnode,A,mid+1,j,x,y);  
	        }else if(y<=mid){  
	            //left branch  
	            return query(M,leftnode,A,i,mid,x,y);  
	        }else{  
	            //mixed  
	            int p1 = query(M,leftnode,A,i,mid,x,y);  
	            int p2 = query(M,rightnode,A,mid+1,j,x,y);  
	              
	            if(p1==-1)return p2;  
	            if(p2==-1)return p1;  
	              
	            if(A[p1]<=A[p2])  
	                return p1;  
	            else  
	                return p2;  
	        }  
	    }  
	  
	    public static void main(String[] args) {  
	        RMQ_SegmentTree rmqSegTree = new RMQ_SegmentTree();  
	      
	        int[] A = new int[]{0,1,2,3,7,1,9,2,8,6};             
	        int[] M = rmqSegTree.buildSegmentTree(A);  
	          
	              
	        //print RMQ table RMQ[i,j], each cell has the form: value/index  
	        System.out.println("RMQ table:");  
	        for(int x=0;x<A.length;x++){  
	            for(int y=0;y<x;y++){  
	                System.out.format("    ");  
	            }  
	            for(int y=x;y<A.length;y++){  
	                int p = rmqSegTree.query(M,A,x,y);                
	                System.out.format(" %d/%d",A[p],p);  
	            }  
	            System.out.println();  
	        }         
	    }  
	
}
