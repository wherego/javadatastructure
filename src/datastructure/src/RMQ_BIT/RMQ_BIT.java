package RMQ_BIT;

public class RMQ_BIT {
	
	
	//each element is a min-val index in the range i-2^r +1 ...  i  
    private int[] T;   
  
    public RMQ_BIT(int n) {  
        //each element in T is initialized to 0  
        T = new int[n + 1];  
        //init T  
        for(int i=1;i<=n;i++){  
            T[i] = -1;  
        }         
    }  
    //preprocess method 1: create BIT - O(nlogn)  
    public void preprocess(int[] A){  
        int n = A.length;         
        for(int i=0;i<n;i++){  
            update(A,i);  
        }  
    }  
    //A better preprocess method 2: create BIT - O(n)  
    public void preprocess2(int[] A){  
        int n = A.length;         
        for(int i=1;i<=n;i++){  
            int k = lowbit(i);  
            T[i] = i-1; //initial value  
            //each subrange's index is left shifted by 1 (=divide by 2)  
            for(int j=1;j<k;j<<=1){  
                //Note: use == for canonical RMQ result  
                if(A[T[i]]>=A[T[i-j]]){  
                    T[i] = T[i-j];  
                }  
            }  
        }  
    }  
      
    //another form of preprocess2: similar to query in BIT   
    public void preprocess3(int[] A){  
        int n = A.length;         
        for(int i=1;i<=n;i++){  
            int j = i - lowbit(i);                    
            T[i] =  i - 1; //set the initial value of T[i]: i-1 is an index of A  
            int k = i - 1; //i-1 is the next index of T  
            while(k>j){  
                //Note: use == for canonical RMQ result  
                if(A[T[i]]>=A[T[k]]){  
                    T[i] = T[k];  
                }                 
                k -= lowbit(k);  
            }  
        }  
    }  
   
    private int lowbit(int x){  
        return x & (-x);  
    }  
    //idx is A's index, starting with 0   
    private void update(int[] A,int idx) {  
        int x = idx+1; //A's index starts with 0  
        int val = A[idx];  
        while (x < T.length) {  
            if(T[x] ==-1 || A[T[x]] > val){  
                T[x] = idx;  
            }  
            x += lowbit(x);  
        }  
    }  
    //i,j are A's index, starting with 0  
    public int query(int[] A,int i,int j){  
        if(i>j){  
            //swap    
            int tmp=i;i=j;j=tmp;  
        }  
        return query(A,i+1,j+1,j);  
    }  
    //precondition: p...q must be in the range: 1...n (inclusive)  
    private int query(int[] A,int p,int q,int minIndex){  
        if(p>q) return minIndex;  
          
        int j = q - lowbit(q);  
        if(p>j){  
            //p..q is equal to or less than T[q]'s coverage  
            if(A[q-1] <= A[minIndex])minIndex = q-1;  
            return query(A,p,q-1,minIndex);  
        }else{ // if(p<=j)  
            //p..q is greater than T[q]'s coverage  
            if(A[T[q]] <= A[minIndex])minIndex = T[q];  
            return query(A,p,j,minIndex);  
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
        int[] A = new int[]{2,4,3,1,6,7,8,9,1,7};  
        int n = A.length;  
        RMQ_BIT bitRMQ = new RMQ_BIT(n);  
          
        bitRMQ.preprocess3(A);  
        int i=0;  
        int j=3;  
        int min = bitRMQ.query(A, i,j);  
        System.out.format("RMQ for A[%d..%d]: A[%d]=%d", i,j,min,A[min]);  
          
        bitRMQ.reportLUTable(A);  
          
        ////////////////////  
        System.out.format("%n***********************%n");  
          
        A=new int[]{10,15,34,20,7,5,18,68,29,40, //0..9  
                24,3,45,26,7,23,43,12,68,34,  //10..19  
                26,34,33,12,80,57,24,42,77,27, //20..29  
                56,33,23,32,54,13,79,65,19,33,  //30..39  
                15,24,43,73,55,13,63,8,23,17};  //40..49  
        n = A.length;  
        bitRMQ = new RMQ_BIT(n);  
          
        bitRMQ.preprocess3(A);  
          
        i=0;  
        j=10;         
        min = bitRMQ.query(A,i,j);  
        System.out.format("RMQ for A[%d..%d]: A[%d]=%d%n", i,j,min,A[min]);  
          
        i=12;  
        j=49;         
        min = bitRMQ.query(A,i,j);  
        System.out.format("RMQ for A[%d..%d]: A[%d]=%d%n", i,j,min,A[min]);  
          
        i=20;  
        j=46;         
        min = bitRMQ.query(A,i,j);  
        System.out.format("RMQ for A[%d..%d]: A[%d]=%d%n", i,j,min,A[min]);  
          
        i=20;  
        j=49;         
        min = bitRMQ.query(A,i,j);  
        System.out.format("RMQ for A[%d..%d]: A[%d]=%d%n", i,j,min,A[min]);  
          
    }  

}
