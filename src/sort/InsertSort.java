package sort;

public class InsertSort {
	
	
	  /**   
     * �����㷨��ʵ�֣���������ָ����Ԫ�ؽ�������   
     * @param array �����������   
     * @param from �����￪ʼ����   
     * @param end �ŵ�����   
     * @param c �Ƚ���   
     */    
    public void insert(Integer[] array, int from, int end) {     
    
        /*   
         * ��һ��ѭ�����Դ����루���򣩵�Ԫ�ؽ���ѭ��   
         * �Ӵ���������ϵĵڶ���Ԫ�ؿ�ʼѭ���������һ��Ԫ�أ�������ֹ   
         */    
        for (int i=from+1; i<=end; i++) {     
            /*   
             * �ڶ���ѭ�����������������ѭ�����Ҵ������������һ��Ԫ�ؿ�ʼ���ѭ��   
             * �ҵ���һ�����ڴ������Ԫ��   
             * ���������ʼԪ��ֻ��һ������ΪԴ����ĵ�һ��Ԫ�أ�һ��Ԫ���������������   
             */    
            for (int j = 0; j < i; j++) {     
                Integer insertedElem = array[i];//�����뵽���������Ԫ��     
                //��������������һ��Ԫ�ؿ�ʼ���ҵ�һ�����ڴ������Ԫ��     
                if ((array[j].compareTo(insertedElem)) > 0) {     
                    //�ҵ������󣬴Ӳ���㿪ʼ�������Ԫ�غ���һλ     
                    move(array, j, i - 1);     
                    //��������Ԫ�ز��뵽����������     
                    array[j] = insertedElem;     
                    break;     
                }     
            }     
        }  
          
        //=======������java.util.Arrays�Ĳ��������㷨��ʵ��     
        /*   
         * ���㷨�������Ƚϼ��һj�㣬�е���ð���㷨��   
         * �������߼��Ϸֳ�ǰ���������ϣ�ǰ��ļ������Ѿ���������Ԫ�أ������漯��Ϊ�������   
         * ���ϣ�ÿ���ڲ�ѭ�Ӻ��漯�����ó�һ��Ԫ�أ�ͨ��ð�ݵ���ʽ����ǰ�漯�����һ��Ԫ�ؿ�   
         * ʼ��ǰ�Ƚϣ��������ǰ��Ԫ�ش��ں���Ԫ�أ��򽻻�������ѭ���˳�   
         *    
         * �ܸо����������е�ֹ֣���Ȼ�ǲ�������Ӧ�������ҵ�����㣬�����ٽ��������Ԫ�ز�   
         * �뵽�Ĳ�����ϣ���ô����Ԫ�ؾͱ�Ȼ����ƣ��о��㷨���������Ʋ�ƥ����������������ʵ   
         * �ֱȣ���ʵ��һ���ģ�ֻ���������Ҳ���㣬���ҵ���һ���Խ����Ԫ������ƣ������㷨ȴ   
         * ����һ����һ����һ��һ����������Ԫ����ǰ��   
         */    
        /*   
        for (int i = from; i <= end; i++) {   
            for (int j = i; j > from && c.compare(array[j - 1], array[j]) > 0; j--) {   
                swap(array, j, j - 1);   
            }   
        }   
        */    
    }     
      
      
     /**   
     * ����Ԫ�غ���   
     * @param array ���ƶ�������   
     * @param startIndex ���ĸ���ʼ��   
     * @param endIndex ���ĸ�Ԫ��ֹ   
     */    
    public void move(Integer[] array, int startIndex, int endIndex) {     
        for (int i = endIndex; i >= startIndex; i--) {     
            array[i+1] = array[i];     
        }     
    }     
  
      
    /**   
     * ����   
     * @param args   
     */    
    public static void main(String[] args) {     
        Integer[] intgArr = { 5, 9, 1, 4, 2, 6, 3, 8, 0, 7 };     
        InsertSort insertSort = new InsertSort();     
        insertSort.insert(intgArr,0,intgArr.length-1);  
        for(Integer intObj:intgArr){  
            System.out.print(intObj + " ");  
        }  
    }     

}
