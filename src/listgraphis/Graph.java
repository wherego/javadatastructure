package listgraphis;
import java.util.*;
public class Graph {

	int k;//ͼ�Ķ�����  
	   VNode[] V;//��������  
	   boolean[] visted;//��¼ĳ�������Ƿ������  
	  
	   public Graph(int k,VNode[] v){  
	      this.k=k;  
	      this.V=v;  
	      visted = new boolean[k];  
	   }  
	  
	   //��v0�������������ȱ���ͼ  
	  
	   public void BFS(int v0){  
	     Queue<Integer> que=new LinkedList<Integer>();  
	     que.add(v0);  
	     while(!que.isEmpty()){  
	       v0=que.poll();  
	       if(!visted[v0]){  
	          System.out.print(v0+"  ");  
	          visted[v0]=true;  
	       }  
	      Edge e=V[v0].first;  
	      while(e!=null){  
	          if(!visted[e.to]) que.add(e.to);  
	          e=e.next;  
	      }  
	  
	     }  
	   }
	   
	   
	   
	   public void DFS(int v0){  
		     visted[v0]=true;  
		     //���ʶ���v0  
		     System.out.print(v0+"  ");  
		     
		     Edge p=V[v0].first;  
		     while(p!=null){  
		       if(!visted[p.to]){  
		          DFS(p.to);  
		       }  
		       p=p.next;  
		    }  
		  }  
	   
	   
}
