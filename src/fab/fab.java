package fab;

public class fab {
	
	    public int m[];
	    public  int k;
	    public int n;
	    public fab(int k,int n)
	    {   this.n=n; 
	    	this.k=k;
	    	this.m=new int[k];
	    	
	    }
	    
		public int fabs(int n)
		{  
		    if(m[n]==1){  
		        return m[n];  
		    }  
		    else{  
		        m[n] = fabs(n - 1) + fabs(n - 2);  
		        return m[n];  
		    }  
		}  
		  
		public  int memFab()  
		{  
		    int i;  
		    m[0] = 1;  
		    m[1] = 1;  
		    for(i = 2; i<this.k; ++i){  
		        m[i] = 0;  
		    }  
		    return fabs(this.n);  
		}  	
}

