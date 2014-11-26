package crossList;
import crossList.OLNode;
import java.util.Scanner;

public class CrossList {
	//十字行链表的头指针  
	   OLNode row_head[];  
	   //十字列链表的头指针  
	   OLNode col_head[];  
	   //稀疏矩阵的行数、列数和非零元素的个数  
	   int m, n, len;    
	   
	 //建立十字链表  
	   public CrossList()  
	   {  
	      int m, n, t;  
	      int i, j, e;  
	      OLNode p=new OLNode();  
	      OLNode q=new OLNode();  
	     
	      //采用十字链表存储结构，创建稀疏矩阵M  
	    Scanner sc=new Scanner(System.in);
	    m=sc.nextInt();
	    n=sc.nextInt();
	    t=sc.nextInt();
	      this.m = m;  
	      this.n = n;  
	      this.len = t;  
	        
	     
	      //初始化行头指针向量，各行链表为空的链表  
	      for(int h=0; h<m+1; h++)  
	      {  
	         this.row_head[h] = null;          
	      }  
	      //初始化列头指针向量，各列链表为空的链表  
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
	         
	         //生成节点  
	         p.row = i;  
	         p.col = j;  
	         p.value = e;  
	         if(this.row_head[i] == null)  
	         {  
	            this.row_head[i] = p;                 
	         }   
	         else   
	         {  
	            //寻找行表中的插入位置  
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
	            //寻找列表中的插入位置  
	            for(q=this.col_head[j]; q.down!=null&& q.down.row < i; q=q.down);    
	              
	            p.down = q.down;  
	            q.down = p;  
	         }  
	      }  
sc.close();
	   }
}
