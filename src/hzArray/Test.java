package hzArray;

import hzArray.HzArray.Suffix;

public class Test {
	
	public static void main(String[] args){  
	    /* 
	    //plain counting sort test: 
	     
	    int[] A= {2,5,3,0,2,3,0,3}; 
	    PrefixDoubling pd = new PrefixDoubling(); 
	    int[] B = new int[A.length]; 
	    pd.countingSort(A,B,5); 
	    for(int i=0;i<B.length;i++) 
	        System.out.format(" %d", B[i]); 
	    System.out.println(); 
	    */        
	      
	    String text = "GACCCACCACC#";  
	    HzArray pd = new HzArray();  
	    Suffix suffix = pd.solve(text);  
	    System.out.format("Text: %s%n",text);  
	    pd.report(suffix);  
	      
	    System.out.println("********************************");  
	    text = "mississippi#";  
	    pd = new HzArray();  
	    suffix = pd.solve(text);  
	    System.out.format("Text: %s%n",text);  
	    pd.report(suffix);  
	      
	    System.out.println("********************************");  
	    text = "abcdefghijklmmnopqrstuvwxyz#";  
	    pd = new HzArray();  
	    suffix = pd.solve(text);  
	    System.out.format("Text: %s%n",text);  
	    pd.report(suffix);  
	      
	    System.out.println("********************************");  
	    text = "yabbadabbado#";  
	    pd = new HzArray();  
	    suffix = pd.solve(text);  
	    System.out.format("Text: %s%n",text);  
	    pd.report(suffix);  
	      
	    System.out.println("********************************");  
	    text = "DFDLKJLJldfasdlfjasdfkldjasfldafjdajfdsfjalkdsfaewefsdafdsfa#";  
	    pd = new HzArray();  
	    suffix = pd.solve(text);  
	    System.out.format("Text: %s%n",text);  
	    pd.report(suffix);  
	      
	}  

}
