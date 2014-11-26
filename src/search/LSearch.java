package search;
//线性查找 
public class LSearch {
	
	public static int[] Data = { 12, 76, 29, 22, 15, 62, 29, 58, 35, 67, 58,  
		   33, 28, 89, 90, 28, 64, 48, 20, 77 }; // 输入数据数组  
		  
		 public static int Counter = 1;    // 查找次数计数变量  
		  
		 public static void main(String args[])  
		 {  
		  
		  int KeyValue = 22;  
		  // 调用线性查找  
		  if (Linear_Search((int) KeyValue))  
		  {  
		   // 输出查找次数  
		   System.out.println("");  
		   System.out.println("Search Time = " + (int) Counter);  
		  }  
		  else  
		  {  
		   // 输出没有找到数据  
		   System.out.println("");  
		   System.out.println("No Found!!");  
		  }  
		 }
		  
		  public static boolean Linear_Search(int Key)  
		  {  
		   int i; // 数据索引计数变量  
		   
		   for (i = 0; i < 20; i++)  
		   {  
		    // 输出数据  
		    System.out.print("[" + (int) Data[i] + "]");  
		    // 查找到数据时  
		    if ((int) Key == (int) Data[i])  
		     return true; // 传回true  
		    Counter++; // 计数器递增  
		   }  
		   return false; // 传回false  
		  }  
		  
}
