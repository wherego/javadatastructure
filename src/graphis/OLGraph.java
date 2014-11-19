package graphis;
import java.util.*;

public class OLGraph {
	
	  boolean visited[]=new boolean[100];
	    public  VexNode[] xlist=new VexNode[20]; //ָ�򶥵������ָ��  
	    private int vexnum;  //������  
	    private int arcnum;  //����  
	    private int maxnum; //�����������ֵ  
	  
	public  OLGraph(int num)  
	    {  
	       this.xlist=new VexNode[num];
	       for(int i=0;i<20;i++)
			{
	    	   xlist[i]=new VexNode();
			}
	        maxnum=num;  
	    }  
	      
	    int Locate_Vex(String v)  
	    {  
	        for(int i=0;i<vexnum;i++)  
	        {  
	            if(xlist[i].data==v) 	            	
	                return i;  
	        }  
	        return -1;  
	    }  
	  
	    void CreateDG_OLG()  
	    {  
	        //��������ͼ  
	        String v1,v2;  
	        int i,j,k;  
	        System.out.println("���붥�����ͱߵ���Ŀ��");  
	        Scanner input = new Scanner(System.in);
	        vexnum = input.nextInt();
	        arcnum =  input.nextInt(); 
	          
	        while(vexnum>maxnum)       
	        {  
	        	System.out.println("������Ŀ����������ƣ����������룺");  
	            vexnum=input.nextInt(); ;  
	        }  
	  
	        System.out.println("���������������ƣ�");  
	        for(i=0;i<vexnum;i++)  
	        {  
	            xlist[i].data=input.next();
	            xlist[i].firstin=null;  
	            xlist[i].firstout=null;  
	        }  
	          
	        for(k=0;k<arcnum;k++)  
	        {  
	        	System.out.println("�����"+k+1+"���ߵ���������(β��>ͷ��˳��)��");
	            v1=input.next();
	            v2=input.next();  
	            i=Locate_Vex(v1);  
	            j=Locate_Vex(v2);  
	              
	            while(i == -1 || j == -1)  
	            {  
	            	 System.out.println("���λ���������,��������: ");  
	            	v1=input.next();
	 	            v2=input.next();    
	                i=Locate_Vex(v1);  
	                j=Locate_Vex(v2);     
	            }         
	              
	            ArcBox p=new ArcBox();  
	            p.tailvex=i;  
	            p.headvex=j;  
	            p.hlink=xlist[j].firstin;  
	            p.tlink=xlist[i].firstout;  
	            xlist[i].firstout=xlist[j].firstin=p;  
	        }  
	  
	        System.out.println("����ͼ�������\n");  
	        input.close();
	    }  
	  
	    //ͳ�ƶ������  
	    int In_degree(String v)  
	    {  
	        int pos=Locate_Vex(v);  
	        if(pos == -1)  
	        {  
	        	System.out.println("��㲻��ͼ��\n");  
	            return -1;  
	        }  
	        ArcBox p=xlist[pos].firstin;  
	        int ins=0;  
	        while(p!=null)  
	        {  
	            ins++;  
	            p=p.hlink;  
	        }  
	        return ins;  
	    }  
	  
	    //ͳ�ƶ������  
	    int Out_degree(String v)  
	    {  
	        int pos=Locate_Vex(v);  
	        if(pos == -1)  
	        {  
	        	System.out.println("��㲻��ͼ��\n");  
	            return -1;  
	        }  
	        ArcBox p=xlist[pos].firstout;  
	        int out=0;  
	        while(p!=null)  
	        {  
	            out++;  
	            p=p.tlink;  
	        }  
	        return out;  
	    }  
	  
	    //������ȱ���  
	    void DFS(int v)  
	    {  
	        visited[v]=true;  
	        System.out.println(xlist[v].data+"  ");  
	        ArcBox p=xlist[v].firstout;  
	        while(p!=null)  
	        {  
	            if(!visited[p.headvex])  
	                DFS(p.headvex);  
	            p=p.tlink;  
	        }  
	    }  
	  
	    void DFS_Traverse()  
	    {  
	        for(int i=0;i<vexnum;i++)  
	            visited[i]=false;  
	        for(int i=0;i<vexnum;i++) 
	            if(!visited[i])  
	                DFS(i); 	 
	    }  
	  
	    //������ȱ���  
	    void BFS(int v)  
	    {  
	        visited[v]=true;  
	        System.out.println(xlist[v].data+"  ");  
	        Queue<Integer> qu= new LinkedList<Integer>();  
	        int vex;  
	        ArcBox p;  
	        qu.offer(v);  
	        while(qu.peek()!=null)  
	        {  
	            vex=qu.peek();  
	            qu.poll();  
	            p=xlist[vex].firstout;  
	            while(p!=null)  
	            {  
	                if(!visited[p.headvex])  
	                {  
	                    visited[p.headvex]=true;  
	                    System.out.println(xlist[p.headvex].data+"  ");  
	                    qu.offer(p.headvex);  
	                }  
	                p=p.tlink;  
	            }  
	        }  
	    }  
	  
	    void BFS_Traverse()  
	    {  
	        for(int i=0;i<vexnum;i++)  
	            visited[i]=false;  
	        for(int i=0;i<vexnum;i++)  
	            if(!visited[i])  
	                BFS(i);  
	    }  
	  
	    void DFS_2(int v)  
	    {  
	        visited[v]=true;  
	        System.out.println(xlist[v].data+"  ");  
	        Stack<Integer> s = new Stack<Integer>();   
	        ArcBox p;  
	        int pos;  
	        s.push(v);  
	        while(!s.empty())  
	        {  
	            pos=s.peek(); 
	            p=xlist[pos].firstout;  
	            while(p!=null && visited[p.headvex])  
	                p=p.tlink;  
	            if(p==null)  
	                s.pop();  
	            else  
	            {  
	                visited[p.headvex]=true;  
	                System.out.println(xlist[p.headvex].data+"  ");  
	                s.push(p.headvex);  
	            }  
	        }  
	    }  
	  
	    void DFS_Traverse_2()  
	    {  
	        for(int i=0;i<vexnum;i++)  
	            visited[i]=false;  
	        for(int i=0;i<vexnum;i++)  
	            if(!visited[i])  
	                DFS_2(i);  
	    }  
	    //����ͨ��֧��  
	    int Connect_Cpnt()  
	    {  
	        for(int i=0;i<vexnum;i++)  
	            visited[i]=false;  
	        System.out.println("�����ÿһ����ʾһ����ͨ��֧��\n");  
	        int num=1;  
	        DFS(0);  
	        System.out.println("\n");   
	        for(int i=0;i<vexnum;i++)  
	        {  
	            if(!visited[i])  
	            {  
	                num++;  
	                DFS(i);  
	                System.out.println("\n");  
	            }  
	        }  
	        return num;  
	    }  

}
