package RMQ_ST;

public class RMQ_ST {
	
	//ST: O(nlogn) for preprocessing  
    public int[][] preprocess(int[] A){  
        int n = A.length;  
        //floor value  
        int maxJ=(int)(Math.log(n)/Math.log(2));  
          
        int[][] M = new int[n][maxJ+1];  
          
        //initial condition for dynamic programming: the RMQ for interval length=1  
        for (int i = 0; i < n; i++)  
              M[i][0] = i;  
          
        //dynamic programming: compute values from smaller(j=1) to bigger intervals  
        for (int j = 1; j<=maxJ; j++){  
            for (int i = 0; i + (1 << j) - 1 < n; i++){  
                int nexti = i + (1 << (j - 1));  
                if (A[M[i][j - 1]] <= A[M[nexti][j - 1]])  
                    M[i][j] = M[i][j - 1];  
                else  
                    M[i][j] = M[nexti][j - 1];  
            }  
        }  
        return M;  
    }  
      
    //ST: O(1) for querying  
    public int query(int[] A,int[][] M,int i,int j){  
        if(j<i){  
            //swap i and j  
            int tmp=i;i=j;j=tmp;  
        }  
        int k = (int)(Math.log(j-i+1)/Math.log(2));  
        //the first interval  
        int mina = M[i][k];  
        int minb = M[j-(1<<k)+1][k];  
        if(A[mina]<=A[minb])  
            return mina;  
        else  
            return minb;  
    }  
  
    public static void main(String[] args) {          
        int[] A=new int[]{0,1,2,3,7,1,9,2,8,6};  
        RMQ_ST st = new RMQ_ST();  
        int[][] M = st.preprocess(A);  
          
          
        System.out.format("%n***********************%n");         
        int i=3,j=7;          
        int min = st.query(A,M, i,j);  
        System.out.format("RMQ for A[%d..%d]: A[%d]=%d", i,j,min,A[min]);  
          
        System.out.format("%n***********************%n");  
        j=3;i=7;      
        min = st.query(A,M, i,j);  
        System.out.format("RMQ for A[%d..%d]: A[%d]=%d", i,j,min,A[min]);  
          
        System.out.format("%n***********************%n");  
        for(int x=0;x<A.length;x++){  
            for(int y=0;y<x;y++){  
                System.out.format("    ");  
            }  
            for(int y=x;y<A.length;y++){  
                int p = st.query(A,M,x,y);                
                System.out.format(" %d/%d",A[p],p);  
            }  
            System.out.println();  
        }     
    }  

}
