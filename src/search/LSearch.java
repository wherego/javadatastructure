package search;
//���Բ��� 
public class LSearch {
	
	public static int[] Data = { 12, 76, 29, 22, 15, 62, 29, 58, 35, 67, 58,  
		   33, 28, 89, 90, 28, 64, 48, 20, 77 }; // ������������  
		  
		 public static int Counter = 1;    // ���Ҵ�����������  
		  
		 public static void main(String args[])  
		 {  
		  
		  int KeyValue = 22;  
		  // �������Բ���  
		  if (Linear_Search((int) KeyValue))  
		  {  
		   // ������Ҵ���  
		   System.out.println("");  
		   System.out.println("Search Time = " + (int) Counter);  
		  }  
		  else  
		  {  
		   // ���û���ҵ�����  
		   System.out.println("");  
		   System.out.println("No Found!!");  
		  }  
		 }
		  
		  public static boolean Linear_Search(int Key)  
		  {  
		   int i; // ����������������  
		   
		   for (i = 0; i < 20; i++)  
		   {  
		    // �������  
		    System.out.print("[" + (int) Data[i] + "]");  
		    // ���ҵ�����ʱ  
		    if ((int) Key == (int) Data[i])  
		     return true; // ����true  
		    Counter++; // ����������  
		   }  
		   return false; // ����false  
		  }  
		  
}
