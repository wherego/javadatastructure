package RMQ_FH;

public class RMQ_FH {
	
	
	 private int blocksize;  
	    private int blockcount;  
	      
	      
	    //block type  
	    private int[] T;  
	    //block LookUp table  
	    private int[][] P;  
	      
	    //out-of-block sparse table   
	    //the first-dimension indices are block IDs (from 0...blockcount-1)  
	    private int[][] M;  
	      
	   
	       
	    //Note: this method is not quite different from the one in class MinusOrPlusOne_RMQ:  
	    //Except that blockTypesCount and computeBlockType are different, this method is   
	    //almost the same as the one in class MinusOrPlusOne_RMQ.  
	    public void preprocess(int[] A) throws Exception {  
	        int n = A.length;  
	          
	        int paddingsize = 0;      
	                  
	        //block size: (logn)/4  
	        int s = (int) (Math.log(n) / Math.log(2)) >> 2;  
	        if(s==0){  
	            s = (n>4)?4:n;   //small problem  
	        }     
	        int count = (int) Math.ceil(n / (double)s);  
	        this.blocksize = s;  
	        this.blockcount = count;  
	          
	        //compute the ballot numbers for determining each block's cartesian trees or block type  
	        int[] ballotnums = RMQ_FH.makeBallotNumbersTable(s);  
	          
	        // padding the last block  
	        int endblocksize = n - s * (count - 1);  
	        if (endblocksize > 0 && endblocksize < s) {  
	            paddingsize = s - endblocksize;  
	        }  
	          
	        //step 1: in-block preprocess  
	        //the size of LU table (one-dimensional)  
	        int size = s*(s+1)/2;  
	          
	        int start = 0; // j is the index of A  
	        int end = -1;  
	        T = new int[count];  
	        int blockTypesCount = ballotnums[(s+2)*(s+1)/2 - 1]; //catalan number  
	        P = new int[blockTypesCount][size];  
	        int[] B = new int[count]; //used in ST algorithm: the min-value array for each block  
	        boolean[] pDone =new boolean[blockTypesCount];  
	          
	        int fullblockscnt = count;  
	        if(paddingsize>0){  
	            fullblockscnt = count - 1;  
	        }     
	          
	        for (int i = 0; i < fullblockscnt; i++) {  
	            start = end+1;  
	            end = (i+1)*s-1;  
	            //compute the type of the block   
	            int type = computeBlockType(ballotnums,A,start,end);      
	            T[i] = type;  
	            if(!pDone[type]){  
	                //if LU table is not done yet for this type of block  
	                P[type] = makeLUTable(s,A,start,size);        
	                pDone[type]=true;  
	            }             
	            B[i] = queryLUTable(s,start,start,end,type);              
	        }  
	          
	        //the end block  
	        if(paddingsize>0){  
	            start = end+1;  
	            end = count*s-1;  
	              
	            //extend the end block  
	            int actualsize = n-start;  
	            int[] D = new int[s];  
	            System.arraycopy(A, start, D, 0, actualsize);   
	            for (int k = actualsize; k < s; k++) {  
	                D[k] = D[k - 1] + 1;  
	            }             
	              
	            int type = computeBlockType(ballotnums,D,0,s-1);      
	            T[count-1] = type;  
	            if(!pDone[type]){  
	                P[type] = makeLUTable(s,D,0,size);        
	                pDone[type]=true;  
	            }     
	            //the min-index from start...n-1, not start...end  
	            B[count-1] = queryLUTable(s,start,start,n-1,type);        
	        }  
	          
	        //step 2: Sparse table algorithm applied to out-of-blocks         
	        this.M = outBlockPreprocess(B,A,count);  
	    }  
	      
	    //Note: this method is the same as the one in class MinusOrPlusOne_RMQ. It is copied from there.  
	    //return the index  
	    public int query(int[] A,int p,int q){  
	        if(q<p){  
	            //swap    
	            int tmp=p;p=q;q=tmp;  
	        }  
	          
	        int start = 0;   
	        int end = -1;  
	        int s = 0,t=0;    
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
	      
	    //Note: this method is the same as the one in class MinusOrPlusOne_RMQ. It is copied from there.  
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
	      
	    //Note: this method is the same as the one in class MinusOrPlusOne_RMQ. It is copied from there.  
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
	      
	    //Note: this method is the same as the one in class MinusOrPlusOne_RMQ. It is copied from there.  
	    //return the index    
	    //precondition: i<=j  
	    private int queryLUTable(int blocksize,int offset,int i,int j,int type){  
	        i -= offset; j-=offset;  
	        int[] L = P[type];  
	        int index = blocksize*i - (i-1)*i/2 + (j-i);  
	        return L[index]+offset;  
	    }  
	       
	    //Note: this method is the same as the one in class MinusOrPlusOne_RMQ. It is copied from there.  
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
	    /* 
	     Ballot numbers:  
	     see: Knuth's Art of Computer Programming Vol 4A section 7.2.1.6 "Generating all trees" 
	     The diagonal numbers are Catalan numbers. 
	      
	     Catalan numbers:(0..16)  
	     see: http://en.wikipedia.org/wiki/Catalan_number 
	      
	     1, 1, 2, 5, 14, 42, 132, 429, 1430,  
	     4862, 16796, 58786, 208012, 742900,  
	     2674440, 9694845, 35357670 .... 
	     */   
	    public static int[] makeBallotNumbersTable(int s){  
	        int k = (s+2)*(s+1)/2;   
	        int[] ballotnums = new int[k];  
	        ballotnums[0] = 1;  
	        int i = 1;  
	        for(int q=1;q<=s;q++){  
	            //p=0, only top element is used, left element is 0  
	            ballotnums[i] = ballotnums[i-q]; i++;         
	            for(int p=1;p<q;p++){                  
	                ballotnums[i] = ballotnums[i-q] + ballotnums[i-1];  
	                i++;  
	            }  
	            //p=q, only left element is used, top element is 0  
	            ballotnums[i] = ballotnums[i-1]; i++;             
	        }  
	        return ballotnums;  
	    }  
	  
	    private static void printBallotNumbersTriangle(int[] ballotnums,int s){  
	        //print ballot numbers triangle  
	        System.out.println("Ballot numbers: ");  
	        int start = 0, end = -1;  
	        for(int m=0;m<=s;m++){  
	            start = end + 1;  
	            end = (m+2)*(m+1)/2 - 1;   
	            for(int n=start;n<=end;n++)  
	                System.out.format(" %d", ballotnums[n]);  
	            System.out.println();  
	        }  
	    }  
	      
	    private static void printCatalanNumbers(int[] ballotnums,int s){  
	        //print catalan numbers:   
	        System.out.println("Catalan numbers: ");  
	        for(int m=0;m<=s;m++){  
	            int j = (m+2)*(m+1)/2 - 1;   
	            if(m % 5 == 0){               
	                System.out.format("(%d):",m);  
	            }  
	            System.out.format(" %d", ballotnums[j]);  
	            if((m+1) % 5 == 0)  
	                System.out.println();  
	        }  
	    }   
	    public static void testBallotNumberTable(){  
	        int s = 16;       
	        int[] ballotnums = RMQ_FH.makeBallotNumbersTable(s);  
	        RMQ_FH.printCatalanNumbers(ballotnums, s);  
	        System.out.println();  
	        RMQ_FH.printBallotNumbersTriangle(ballotnums, s);  
	    }  
	      
	    //compute the block type, ie. the sequence number of its cartesian tree  
	    private int computeBlockType(int[] ballotnums, int[] D,int start,int end){  
	        int s = end - start + 1;  
	        //the stack is the rightmost path  
	        int[] stack = new int[s+1];  
	        stack[0] = Integer.MIN_VALUE; //the first element is a sentinel  
	        int top = 0; //stack top  
	        int N=0; //the sequence number of this block's cartesian tree  
	        int q = s;  
	        for(int i=0;i<s;i++){  
	            int p = s-i-1;  
	            while(stack[top]>D[i+start]){              
	                //numbering: go up one level in the ballot number triangle                
	                N += ballotnums[q*(q+1)/2+p];  
	                top--; //remove the element  
	                q--; //up one level  
	            }  
	            //when stack[top]<= D[i+start], we append the current number to the rightmost path  
	            stack[++top] = D[i+start];  
	        }  
	        return N;  
	    }  
	    public void testComputeBlockType(int[] D){        
	        int s = D.length; //block size  
	        int[] ballotnums = RMQ_FH.makeBallotNumbersTable(s);  
	          
	        int nr = this.computeBlockType(ballotnums,D, 0, D.length-1);  
	        System.out.format("block type(=cartesian tree sequence number): %d%n",nr);  
	    }  
	      
	    private void reportLUTable(int[] A){  
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
	        //test ballot numbers  
	        RMQ_FH.testBallotNumberTable();  
	          
	        //test block types  
	        System.out.println("*************");  
	        int[] A = new int[]{1,2,3,4,5};  
	        RMQ_FH rmq = new RMQ_FH();  
	        rmq.testComputeBlockType(A);  
	          
	        System.out.println("*************");  
	        A = new int[]{5,4,3,2,1};  
	        rmq = new RMQ_FH();  
	        rmq.testComputeBlockType(A);  
	          
	        //RMQ operations              
	        System.out.format("***********************%n");  
	        A=new int[]{1,7,3};  
	        rmq = new RMQ_FH();  
	        rmq.preprocess(A);        
	        rmq.reportLUTable(A);  
	          
	        System.out.format("***********************%n");  
	        A=new int[]{2,4,3,1,6,7,8,9,1,7};  
	        rmq = new RMQ_FH();  
	        rmq.preprocess(A);        
	        rmq.reportLUTable(A);  
	          
	        System.out.format("***********************%n");  
	        A=new int[]{10,15,34,20,7,5,18,68,29,40, //0..9  
	                24,3,45,26,7,23,43,12,68,34,  //10..19  
	                26,34,33,12,80,57,24,42,77,27, //20..29  
	                56,33,23,32,54,13,79,65,19,33,  //30..39  
	                15,24,43,73,55,13,63,8,23,17};  //40..49  
	        rmq = new RMQ_FH();  
	        rmq.preprocess(A);  
	          
	          
	        int i=0;  
	        int j=49;         
	        int min = rmq.query(A,i,j);  
	        System.out.format("RMQ for A[%d..%d]: A[%d]=%d%n", i,j,min,A[min]);  
	          
	    }  

}
