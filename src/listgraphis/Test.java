package listgraphis;
import java.util.*;
public class Test {

	 public static void main(String[] args){  
	     Scanner sc=new Scanner(System.in);  
	     int k=sc.nextInt();//图的顶点数  
	     int m=sc.nextInt();//图的边数  
	  
	      VNode[] V=new VNode[k];  
	      for(int i=0;i<k;i++)  
	          V[i]=new VNode(i);  
	      Edge e=null;Edge e1=null;  
	      int u=0;int v=0;  
	      for(int i=0;i<m;i++){  
	         u=sc.nextInt();//起点  
	         v=sc.nextInt();//终点  
	        e=new Edge(v);  
	        e.next=V[u].first;//插入到链表开头  
	        V[u].first=e;  
	  
	       //对于无向图作对称处理  
	         e1=new Edge(u);  
	         e1.next=V[v].first;  
	         V[v].first=e1;  
	       }  
	  
	       Graph gra=new Graph(k,V);  
	      // gra.BFS(0);  
	       gra.DFS(0);  
	     }  
	
}
