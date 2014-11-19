package graphis;

import java.util.Scanner;

public class Test {
	public static void main(String args[]){
		
		OLGraph G=new OLGraph(20);  
	    String v;  
	    int ins,out;  
	    G.CreateDG_OLG();  
	      
	    System.out.println("����Ҫͳ���ĸ�������ȣ�"); 
	    Scanner input = new Scanner(System.in);
	    v=input.next();  
	    ins=G.In_degree(v);  
	    if(ins != -1)  
	    	 System.out.println("����"+v+"�����Ϊ��"+ins+'\n');  
	  
	    	 System.out.println("����Ҫͳ���ĸ����ĳ��ȣ�");  
	    v=input.next(); 
	    out=G.Out_degree(v);  
	    if(out != -1)  
	    	System.out.println("����"+v+"�ĳ���Ϊ��"+out+'\n');  
	  
	    System.out.println("������ȱ�����");  
	    G.DFS_Traverse();  
	    System.out.println('\n');  
	  
	    System.out.println("������ȱ�����");  
	    G.BFS_Traverse();  
	    System.out.println('\n');  
	  
	    System.out.println("����ͼ����ͨ��֧"+'\n');  
	    int num=G.Connect_Cpnt();  
	    System.out.println("ͼ����ͨ��֧��ĿΪ��"+num+'\n');  
	  
	    System.out.println("������ȱ����ǵݹ飺");  
	    G.DFS_Traverse_2();  
	    System.out.println('\n');  
	    input.close();
	    
	}

}
