package sort;

public class SelectSort {
	
	/**   
     * �����㷨��ʵ�֣���������ָ����Ԫ�ؽ�������   
     * @param array �����������   
     * @param from �����￪ʼ����   
     * @param end �ŵ�����   
     * @param c �Ƚ���   
     */    
    public void select(Integer[] array) {     
        int minIndex;//��С����     
        /*   
         * ѭ���������飨��ʵ������Ͻ�Ϊ array.length - 1 ���ɣ���Ϊ�� i= array.length-1   
         * ʱ�����һ��Ԫ�ؾ����������ˣ����Ϊarray.lengthʱ���ڲ�ѭ��������ѭ������ÿ�ּ���   
         * ��һ��Ԫ��Ϊ��СԪ�أ�����ӵ�һԪ�غ���ѡ���ȵ�һ��Ԫ�ظ�СԪ�أ���������СԪ�����һ   
         * ��Ԫ�ؽ���    
         */    
        for (int i=0; i<array.length; i++) {     
            minIndex = i;//����ÿ�ֵ�һ��Ԫ��Ϊ��СԪ��     
            //�Ӽ������СԪ�ص���һԪ�ؿ�ʼѭ��     
            for (int j=i+1;j<array.length; j++) {     
                //��������бȵ�ǰarray[smallIndex]��СԪ�أ�����¸�Ԫ�ص�������smallIndex��     
                if ((array[j].compareTo(array[minIndex])) < 0) {     
                    minIndex = j;     
                }     
            }     
    
            //��ǰֻ�Ǽ�¼��СԪ������������СԪ������ȷ��������ÿ�ֵĵ�һ��Ԫ�ؽ���     
            swap(array, i, minIndex);     
        }     
    }     
      
    public static void swap(Integer[] intgArr,int x,int y){  
        //Integer temp; //���Ҳ��  
        int temp;  
        temp=intgArr[x];  
        intgArr[x]=intgArr[y];  
        intgArr[y]=temp;  
    }  
      
    /**   
     * ����   
     * @param args   
     */    
    public static void main(String[] args) {     
        Integer[] intgArr = { 5, 9, 1, 4, 2, 6, 3, 8, 0, 7 };     
        SelectSort insertSort = new SelectSort();  
        insertSort.select(intgArr);  
        for(Integer intObj:intgArr){  
            System.out.print(intObj + " ");  
        }    
          
    }     

}
