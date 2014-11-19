package graphis;

import java.util.Scanner;

public class Test {
	public static void main(String args[]){
		
		OLGraph G=new OLGraph(20);  
	    String v;  
	    int ins,out;  
	    G.CreateDG_OLG();  
	      
	    System.out.println("输入要统计哪个结点的入度："); 
	    Scanner input = new Scanner(System.in);
	    v=input.next();  
	    ins=G.In_degree(v);  
	    if(ins != -1)  
	    	 System.out.println("顶点"+v+"的入度为："+ins+'\n');  
	  
	    	 System.out.println("输入要统计哪个结点的出度：");  
	    v=input.next(); 
	    out=G.Out_degree(v);  
	    if(out != -1)  
	    	System.out.println("顶点"+v+"的出度为："+out+'\n');  
	  
	    System.out.println("深度优先遍历：");  
	    G.DFS_Traverse();  
	    System.out.println('\n');  
	  
	    System.out.println("广度优先遍历：");  
	    G.BFS_Traverse();  
	    System.out.println('\n');  
	  
	    System.out.println("计算图的连通分支"+'\n');  
	    int num=G.Connect_Cpnt();  
	    System.out.println("图的连通分支数目为："+num+'\n');  
	  
	    System.out.println("深度优先遍历非递归：");  
	    G.DFS_Traverse_2();  
	    System.out.println('\n');  
	    input.close();
	    
	}

}
