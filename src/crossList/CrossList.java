package crossList;
import crossList.OLNode;
import java.util.Scanner;

public class CrossList {
	//ʮ���������ͷָ��  
	   OLNode row_head[];  
	   //ʮ���������ͷָ��  
	   OLNode col_head[];  
	   //ϡ�����������������ͷ���Ԫ�صĸ���  
	   int m, n, len;    
	   
	 //����ʮ������  
	   public CrossList()  
	   {  
	      int m, n, t;  
	      int i, j, e;  
	      OLNode p=new OLNode();  
	      OLNode q=new OLNode();  
	     
	      //����ʮ������洢�ṹ������ϡ�����M  
	    Scanner sc=new Scanner(System.in);
	    m=sc.nextInt();
	    n=sc.nextInt();
	    t=sc.nextInt();
	      this.m = m;  
	      this.n = n;  
	      this.len = t;  
	        
	     
	      //��ʼ����ͷָ����������������Ϊ�յ�����  
	      for(int h=0; h<m+1; h++)  
	      {  
	         this.row_head[h] = null;          
	      }  
	      //��ʼ����ͷָ����������������Ϊ�յ�����  
	      for(int k=0; k<n+1; k++)  
	      {  
	         this.col_head[k] = null;          
	      }  
	        
	      
	      
	      while(true)  
	      {  
	    	  
	    	i=sc.nextInt();
	  	    j=sc.nextInt();
	  	    e=sc.nextInt();
	  	    if(i==0)
	  	    	break;
	         
	         //���ɽڵ�  
	         p.row = i;  
	         p.col = j;  
	         p.value = e;  
	         if(this.row_head[i] == null)  
	         {  
	            this.row_head[i] = p;                 
	         }   
	         else   
	         {  
	            //Ѱ���б��еĲ���λ��  
	            for(q=this.row_head[i]; q.right!=null&& q.right.col < j; q=q.right);  
	              
	            p.right = q.right;  
	            q.right = p;  
	         }  
	           
	         if(this.col_head[j] == null)  
	         {  
	            this.col_head[j] = p;                    
	         }  
	         else   
	         {  
	            //Ѱ���б��еĲ���λ��  
	            for(q=this.col_head[j]; q.down!=null&& q.down.row < i; q=q.down);    
	              
	            p.down = q.down;  
	            q.down = p;  
	         }  
	      }  
sc.close();
	   }
}
