package search;

public class BSearch {
	
	 public static int Max  = 20;  
	 public static int[] Data = { 12, 16, 19, 22, 25, 32, 39, 48, 55, 57, 58,  
	   63, 68, 69, 70, 78, 84, 88, 90, 97 }; // ��������  
	 public static int Counter = 1;    // ������  
	  
	 public static void main(String args[])  
	 {  
	  int KeyValue = 22;  
	  // �����۰����  
	  if (BinarySearch((int) KeyValue))  
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
	  
	 // ---------------------------------------------------  
	 // �۰���ҷ�  
	 // ---------------------------------------------------  
	 public static boolean BinarySearch(int KeyValue)  
	 {  
	  int Left; // ��߽����  
	  int Right; // �ұ߽����  
	  int Middle; // ��λ������  
	  
	  Left = 0;  
	  Right = Max - 1;  
	  
	  while (Left <= Right)  
	  {  
	   Middle = (Left + Right) / 2;  
	   if (KeyValue < Data[Middle]) // ������ֵ��С  
	    Right = Middle - 1; // ����ǰ���  
	   // ������ֵ�ϴ�  
	   else if (KeyValue > Data[Middle])  
	    Left = Middle + 1; // ���Һ���  
	   // ���ҵ�����  
	   else if (KeyValue == Data[Middle])  
	   {  
	    System.out.println("Data[" + Middle + "] = " + Data[Middle]);  
	    return true;  
	   }  
	   Counter++;  
	  }  
	  return false;  
	 }  

}
