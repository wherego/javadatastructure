package sort;

public class HeapSort {
	
	/**   
     * �����㷨��ʵ�֣���������ָ����Ԫ�ؽ�������   
     * @param array �����������   
     * @param from �����￪ʼ����   
     * @param end �ŵ�����   
     * @param c �Ƚ���   
     */    
    public void sort(Integer[] array, int from, int end) {     
        //������ʼ��     
        initialHeap(array, from, end);     
    
        /*   
         * �Գ�ʼ�ѽ���ѭ�����Ҵ����һ���ڵ㿪ʼ��ֱ����ֻ�������ڵ�ֹ   
         * ÿ��ѭ���������һ��Ҷ�ӽڵ㣬�ٿ���һ���µ���   
         */    
        for (int i = end - from + 1; i >= 2; i--) {     
            //���ڵ������һ��Ҷ�ӽڵ㽻��λ�ã��������еĵ�һ��Ԫ�������һ��Ԫ�ػ���     
            swap(array, from, i - 1);     
            //��������Ҫ���µ�����     
            adjustNote(array, 1, i - 1);     
        }     
    
    }     
    
    /**   
     * ��ʼ����   
     * ����ԭ����Ϊ��7,2,4,3,12,1,9,6,8,5,10,11   
     * ���ʼ��Ϊ��1,2,4,3,5,7,9,6,8,12,10,11   
     * @param arr ��������   
     * @param from ����   
     * @param end ����   
     * @param c �Ƚ���   
     */    
    private void initialHeap(Integer[] arr, int from, int end) {     
        int lastBranchIndex = (end - from + 1) / 2;//���һ����Ҷ�ӽڵ�     
        //�����еķ�Ҷ�ӽڵ����ѭ�� ���Ҵ���һ����Ҷ�ӽڵ㿪ʼ     
        for (int i = lastBranchIndex; i >= 1; i--) {     
            adjustNote(arr, i, end - from + 1);     
        }     
    }     
    
    /**   
     * �����ڵ�˳�򣬴Ӹ��������ӽڵ������ڵ���ѡ��һ�����ڵ��븸�ڵ�ת��   
     * @param arr ����������   
     * @param parentNodeIndex Ҫ�����Ľڵ㣬�������ӽڵ�һ����е���   
     * @param len ���Ľڵ���   
     * @param c �Ƚ���   
     */    
    private void adjustNote(Integer[] arr, int parentNodeIndex, int len) {     
        int minNodeIndex = parentNodeIndex;     
        //�������������i * 2Ϊ���ӽڵ�����      
        if (parentNodeIndex * 2 <= len) {     
            //������ڵ�С��������ʱ      
            if ((arr[parentNodeIndex - 1].compareTo(arr[parentNodeIndex * 2 - 1])) < 0) {     
                minNodeIndex = parentNodeIndex * 2;//��¼�������Ϊ���ӽڵ�����      
            }     
    
            // ֻ�����л�������ǰ���²ſ��������������ٽ�һ�������Ƿ���������      
            if (parentNodeIndex * 2 + 1 <= len) {     
                //��������������ڵ����      
                if ((arr[minNodeIndex - 1].compareTo(arr[(parentNodeIndex * 2 + 1) - 1])) < 0) {     
                    minNodeIndex = parentNodeIndex * 2 + 1;//��¼�������Ϊ���ӽڵ�����      
                }     
            }     
        }     
    
        //����ڸ��ڵ㡢�����ӽڵ������У����ڵ㲻�Ǹ��ڵ�ʱ�轻�����������븸�ڵ㽻���������󶥶�     
        if (minNodeIndex != parentNodeIndex) {     
            swap(arr, parentNodeIndex - 1, minNodeIndex - 1);     
            //�����������Ҫ�ؽ��ѣ�ԭ���ڵ������Ҫ�����³�     
            if (minNodeIndex * 2 <= len) {//�Ƿ����ӽڵ㣬ע��ֻ���ж��Ƿ�������������֪��     
                adjustNote(arr, minNodeIndex, len);     
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
        Integer[] intgArr = { 5, 9, 1, 4, 2, 6, 3, 8, 0, 7 };    
        HeapSort heapsort = new HeapSort();     
        heapsort.sort(intgArr,0,intgArr.length-1);  
        for(Integer intObj:intgArr){  
            System.out.print(intObj + " ");  
        }  
    }     

}
