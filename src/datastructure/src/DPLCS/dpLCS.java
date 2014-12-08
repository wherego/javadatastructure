package DPLCS;
import java.util.Stack;
import java.util.Random;

public class dpLCS {

 
	   private char[] x;
	   private char[] y;
	   int b[][];
	   int c[][];//C[i,j]记录X与Y的LCS的长度

	   public dpLCS(String s1,String s2){
	       x=new String(" "+s1).toCharArray();
	       y=new String(" "+s2).toCharArray();
	       b=new int[x.length][y.length];
	       c=new int[x.length][y.length];
	   }


	    public static void main(String[] args)   
	    {   
	    	dpLCS lcs=new dpLCS("pjwrbilcerzypsedamgk","kqeddkogpazssnnr");
	        lcs.getLength();   
	           
	        lcs.Display0();   
	        System.out.println();
	        lcs.Display1();
	        System.out.println();

	      // 随机生成字符串
	        String ss = GetRandomStrings(9);
	        String ss1 = GetRandomStrings(6);
	        
	        System.out.println("ss="+ss);
	        System.out.println("ss1="+ss1);
	        lcs=new dpLCS(ss,ss1);
	        lcs.getLength();   
	         lcs.Display0();   
	        System.out.println();
	        lcs.Display1();

	    }   
	       
	    private void getLength()   //计算c[i][j],从前往后计算
	    {   
	        
	        for(int i=1; i< x.length; i++)   
	        {   
	            for(int j=1; j< y.length; j++)   
	            {   
	                if( x[i] == y[j])   
	                {   
	                    c[i][j] = c[i-1][j-1] + 1;   
	                    b[i][j] = 1;   
	                }   
	                else if(c[i-1][j] >= c[i][j-1])   
	                {   
	                    c[i][j] = c[i-1][j];   
	                    b[i][j] = 0;   
	                }   
	                else  
	                {   
	                    c[i][j] = c[i][j-1];   
	                    b[i][j] = -1;   
	                }   
	            }   
	        }      
	           
	     
	    }   
	       
	    public  void Display0(){//输出最长公共子序列
	       int i = x.length-1, j = y.length-1;
	       Stack< Character> sta=new Stack< Character>();
	     
	        while (i>=1&& j >=1){
	            if (b[i][j]==1) {
	                sta.push(x[i]);
	                i--;
	                j--;
	            } else if (b[i][j]==0)
	                i--;
	            else
	                j--;
	        }
	       while(!sta.empty())
	        System.out.print(sta.pop()+" ");
	    } 
	   
	    public void Display1(){////输出最长公共子序列
	      Display2(x.length-1,y.length-1);
	    }
	       
	    public  void Display2(int i, int j)   
	    {   
	        if(i == 0 || j == 0)   
	            return;   
	           
	        if(b[i][j] == 1)   
	        {   
	            Display2(i-1, j-1);   
	            System.out.print(x[i] + " ");   
	        }   
	        else if(b[i][j] == 0)   
	        {   
	            Display2(i-1, j);   
	        }   
	        else if(b[i][j] == -1)   
	        {   
	            Display2(i, j-1);   
	        }   
	    }   

	   //取得定长随机字符串
	    public static String GetRandomStrings(int length){
	        StringBuffer buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
	        StringBuffer sb = new StringBuffer();
	        Random r = new Random();
	        int range = buffer.length();
	        for (int i = 0; i < length; i++) {
	            sb.append(buffer.charAt(r.nextInt(range)));
	        }
	        return sb.toString();
	    }



	}  
	
	

