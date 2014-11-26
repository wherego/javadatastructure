package sort;

public class BubbleSort2 {
	
	 public static void main(String[] args){  
	        int[] a = {3,5,9,4,7,8,6,1,2};  
	        BubbleSort2 bubble = new BubbleSort2();  
	        bubble.bubble(a);  
	        for(int num:a){  
	            System.out.print(num + " ");  
	        }  
	    }  
	      
	    public void bubble(int[] a){  
	        for(int i=a.length-1;i>0;i--){  
	            for(int j=0;j<i;j++){  
	                if(new Integer(a[j]).compareTo(new Integer(a[j+1]))>0){  
	                    swap(a,j,j+1);  
	                }  
	            }  
	        }  
	    }  
	      
	    public void swap(int[] a,int x,int y){  
	        int temp;  
	        temp=a[x];  
	        a[x]=a[y];  
	        a[y]=temp;  
	    }  

}
