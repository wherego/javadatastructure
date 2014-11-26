package sort;
/** 
* ð������ִ����һ����forѭ������С��һ�����ŵ����������ǰ�棨����һ�������㷨* ��һ����������λ��֮�佻�� 
*/  
  
public class BubbleSort {
	
	 /**   
     * �����㷨��ʵ�֣���������ָ����Ԫ�ؽ�������   
     * @param array �����������   
     * @param from �����￪ʼ����   
     * @param end �ŵ�����   
     * @param c �Ƚ���   
     */    
    public void bubble(Integer[] array, int from, int end) {     
        //��array.length - 1�ֱȽ�     
        for (int k = 1; k < end - from + 1; k++) {     
            //ÿ��ѭ���д����һ��Ԫ�ؿ�ʼ��ǰ���ݣ�ֱ��i=kֹ����i�����ִ�ֹ     
            for (int i = end - from; i >= k; i--) {     
                //����һ�ֹ��򣨺���Ԫ�ز���С��ǰ��Ԫ�أ�����     
                if ((array[i].compareTo(array[i - 1])) < 0) {     
                    //�������Ԫ��С���ˣ���Ȼ�Ǵ��ڻ���С��Ҫ���Ƚ���ʵ���ˣ�ǰ���Ԫ�أ���ǰ�󽻻�     
                    swap(array, i, i - 1);     
                }     
            }     
        }     
    }     
      
    /**   
     * ���������е�����Ԫ�ص�λ��   
     * @param array ������������   
     * @param i ��һ��Ԫ��   
     * @param j �ڶ���Ԫ��   
     */    
    public void swap(Integer[] array, int i, int j) {     
        if (i != j) {//ֻ�в���ͬһλ��ʱ���轻��     
            Integer tmp = array[i];     
            array[i] = array[j];     
            array[j] = tmp;     
        }     
    }     
  
      
    /**    
    * ����    
    * @param args    
    */    
    public static void main(String[] args) {     
        Integer[] intgArr = { 7, 2, 4, 3, 12, 1, 9, 6, 8, 5, 11, 10 };     
        BubbleSort bubblesort = new BubbleSort();     
        bubblesort.bubble(intgArr,0,intgArr.length-1);  
        for(Integer intObj:intgArr){  
            System.out.print(intObj + " ");  
        }  
    }     
	

}
