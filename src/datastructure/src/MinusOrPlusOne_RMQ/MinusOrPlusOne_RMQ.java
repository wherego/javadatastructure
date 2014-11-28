package MinusOrPlusOne_RMQ;

public class MinusOrPlusOne_RMQ {

	private int blocksize;  
    private int blockcount;  
      
    //block type  
    private int[] T;  
    //block LookUp table  
    private int[][] P;  
      
    //out-of-block sparse table   
    //the first-dimension indices are block IDs  
    private int[][] M;  
      
    //check if A is valid +1/-1 RMQ   
    private void sanityCheck(int[] A) throws Exception{  
        for(int i=1;i<A.length;i++){  
            int diff = A[i] - A[i-1];  
            if(!(diff==1 || diff==-1))  
                throw new Exception("illegal +1/-1 RMQ problem!");  
        }  
    }  
      
    //output: blocksize, blockcount, T[], P[][], M  
    public void preprocess(int[] A) throws Exception {  
        sanityCheck(A);  
          
        int n = A.length;  
           
        int paddingsize = 0;      
  
        // grouping into logn/2 sized blocks  
        int b = (int) (Math.log(n) / Math.log(2)) >> 1;  
        if(b==0){  
            b = n;  //small problem  
        }  
        int count = (int) Math.ceil(n / (double)b);  
        this.blocksize = b;  
        this.blockcount = count;  
  
        // padding the last block  
        int endblocksize = n - b * (count - 1);  
        if (endblocksize > 0 && endblocksize < b) {  
            paddingsize = b - endblocksize;  
        }  
  
          
          
        //step 1: in-block preprocess  
        //the size of LU table (one-dimensional)  
        int size = b*(b+1)/2;  
          
        int start = 0; // j is the index of A  
        int end = -1;  
        T = new int[count];  
        int blockTypesCount = 1<<(b-1);  
        P = new int[blockTypesCount][size];  
        int[] B = new int[count]; //used in ST algorithm: the min-value array for each block  
        boolean[] pDone =new boolean[blockTypesCount];  
          
        int fullblockscnt = count;  
        if(paddingsize>0){  
            fullblockscnt = count - 1;  
        }         
        for (int i = 0; i < fullblockscnt; i++) {  
            start = end+1;  
            end = (i+1)*b-1;  
            //compute the type of the block   
            int type = computeBlockType(A,start,end);     
            T[i] = type;  
            if(!pDone[type]){  
                //if LU table is not done yet for this type of block  
                P[type] = makeLUTable(b,A,start,size);        
                pDone[type]=true;  
            }             
            B[i] = queryLUTable(b,start,start,end,type);              
        }  
        //the end block  
        if(paddingsize>0){  
            start = end+1;  
            end = count*b-1;  
              
            //extend the end block  
            int actualsize = n-start;  
            int[] D = new int[b];  
            System.arraycopy(A, start, D, 0, actualsize);   
            for (int k = actualsize; k < b; k++) {  
                D[k] = D[k - 1] + 1;  
            }             
              
            int type = computeBlockType(D,0,b-1);     
            T[count-1] = type;  
            if(!pDone[type]){  
                P[type] = makeLUTable(b,D,0,size);        
                pDone[type]=true;  
            }     
            //the min-index from start...n-1, not start...end  
            B[count-1] = queryLUTable(b,start,start,n-1,type);        
        }  
          
        //step 2: Sparse table algorithm applied to out-of-blocks         
        this.M = outBlockPreprocess(B,A,count);  
    }  
      
    //return the index  
    public int query(int[] A,int p,int q){  
        if(q<p){  
            //swap    
            int tmp=p;p=q;q=tmp;  
        }  
          
        int start = 0; // j is the index of A  
        int end = -1;  
        int s = 0,t=0; //i..j for ST algorithm  
        int startMin=-1,endMin=-1; //the start block and end block's min index  
        for (int i = 0; i < this.blockcount; i++) {  
            start = end+1;  
            end = (i+1)*this.blocksize-1;  
            if(p>=start && q<=end){  
                //within a block  
                return queryLUTable(blocksize,start,p,q,T[i]);  
            }else if(p>=start && p<=end){  
                startMin = queryLUTable(blocksize,start,p,end,T[i]);  
                s=i+1;  
            }else if(q<=end && q>=start){  
                endMin = queryLUTable(blocksize,start,start,q,T[i]);  
                t=i-1;  
                break;  
            }  
        }  
        int minIndex = startMin;  
          
        if(s<=t){  
            int outBlocksMin = outBlockQuery(A,s,t);  
            if(A[startMin]>A[outBlocksMin]){  
                minIndex = outBlocksMin;  
            }  
        }  
        if(A[minIndex]>A[endMin]){  
            minIndex = endMin;  
        }  
        return minIndex;  
    }  
      
    //ST: O(1) for querying  
    //precondition: s<=t  
    private int outBlockQuery(int[] A,int s,int t){  
        int k = (int)(Math.log(t-s+1)/Math.log(2));  
        //the first interval  
        int mina = M[s][k];  
        int minb = M[t-(1<<k)+1][k];  
        if(A[mina]<=A[minb])  
            return mina;  
        else  
            return minb;  
    }  
      
    private int[][] outBlockPreprocess(int[] B,int[] A,int count){  
        //floor value  
        int maxJ=(int)(Math.log(count)/Math.log(2));  
           
        int[][] M = new int[count][maxJ+1];  
          
        //initial condition for dynamic programming: the RMQ for interval length=1  
        for (int i = 0; i < count; i++)  
              M[i][0] = B[i];  
          
        //dynamic programming: compute values from smaller(j=1) to bigger intervals  
        for (int j = 1; j<=maxJ; j++){  
            for (int i = 0; i + (1 << j) - 1 < count; i++){  
                int nexti = i + (1 << (j - 1));  
                if (A[M[i][j - 1]] <= A[M[nexti][j - 1]])  
                    M[i][j] = M[i][j - 1];  
                else  
                    M[i][j] = M[nexti][j - 1];  
            }  
        }  
        return M;  
    }  
    private int computeBlockType(int[] D,int start,int end){      
        int sum = 0;  
        //use Horner's rule  
        for(int i=start+1;i<=end;i++){  
            int diff = D[i]-D[i-1];  
            if(diff==1){//0 when diff=+1  
                sum <<= 1;  
            }else{//1 when diff=-1  
                sum = (sum << 1) + 1;  
            }                 
        }  
        return sum;  
    }  
   
    //return the index    
    //precondition: i<=j  
    private int queryLUTable(int blocksize,int offset,int i,int j,int type){  
        i -= offset; j-=offset;  
        int[] L = P[type];  
        int index = blocksize*i - (i-1)*i/2 + (j-i);  
        return L[index]+offset;  
    }  
    //use naive method to compute the lookup table for a block  
    //the return index is relative to the block itself  
    private int[] makeLUTable(int blocksize, int[] D,int offset,int size) {  
        int[][] Q = new int[blocksize][blocksize];  
        for (int i = 0; i < blocksize; i++)  
            Q[i][i] = i;  
        for (int i = 0; i < blocksize; i++)  
            for (int j = i + 1; j < blocksize; j++)  
                if (D[Q[i][j - 1]+offset] <= D[j+offset])  
                    Q[i][j] = Q[i][j - 1];  
                else  
                    Q[i][j] = j;  
        //convert to one-dimension array          
        int[] L = new int[size];  
        int k=0;  
        for(int i=0;i<blocksize;i++){  
            for(int j=i;j<blocksize;j++,k++){  
                L[k] = Q[i][j];  
            }  
        }  
        return L;  
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
  
    public static void main(String[] args) throws Exception {  
  
        int[] A = new int[]{3,2,3};  
        MinusOrPlusOne_RMQ mpoRMQ = new MinusOrPlusOne_RMQ();  
        mpoRMQ.preprocess(A);  
        mpoRMQ.reportLUTable(A);  
          
          
        A = new int[]{1,0,1,2};  
        mpoRMQ = new MinusOrPlusOne_RMQ();  
        mpoRMQ.preprocess(A);  
        mpoRMQ.reportLUTable(A);  
          
        A = new int[]{1,0,1,0};  
        mpoRMQ = new MinusOrPlusOne_RMQ();  
        mpoRMQ.preprocess(A);  
        mpoRMQ.reportLUTable(A);  
          
          
        A = new int[] { 0, 1, 0, 1, 2, 3, 2, 3, 2, 1, 2, 3, 2, 3, 2, 1, 0 };  
        mpoRMQ = new MinusOrPlusOne_RMQ();  
        mpoRMQ.preprocess(A);  
          
        System.out.format("%n***********************%n");         
        int i=5,j=11;         
        int min = mpoRMQ.query(A,i,j);  
        System.out.format("RMQ for A[%d..%d]: A[%d]=%d", i,j,min,A[min]);  
          
        System.out.format("%n***********************%n");         
        j=5;  
        i=11;         
        min = mpoRMQ.query(A,i,j);  
        System.out.format("RMQ for A[%d..%d]: A[%d]=%d", i,j,min,A[min]);  
          
        System.out.format("%n***********************%n");         
        i=4;  
        j=16;         
        min = mpoRMQ.query(A,i,j);  
        System.out.format("RMQ for A[%d..%d]: A[%d]=%d", i,j,min,A[min]);  
          
        System.out.format("%n***********************%n");  
        mpoRMQ.reportLUTable(A);  
          
          
          
        A = new int[]{   
                10,11,12,13,12,11,10,9,10,11,  //0..9  
                12,13,14,15,14,15,14,15,16,17, //10..19  
                16,15,14,13,12,11,10,9,8,7,    //20..29  
                8,9,10,11,12,13,12,11,10,9,    //30..39  
                10,11,12,13,14,15,14,15,14,15, //40..49  
                16,17,16,15,14,13,12,11,10,9,  //50..59  
                8,7,8,9,10,11,12,13,12,11,     //60..69  
                10,9,10,11,12,13,14,15,14,15,  //70..79  
                14,15,16,17,16,15,14,13,12,11, //80..89  
                10,9,8,7,8,9,10,11,12,13,14};     //90..100  
        mpoRMQ = new MinusOrPlusOne_RMQ();  
        mpoRMQ.preprocess(A);  
        mpoRMQ.reportLUTable(A);  
    }  
	
}
