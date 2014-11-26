package sort;

public class QuickSort {
	
	/**   
     * �����㷨��ʵ�֣���������ָ����Ԫ�ؽ�������   
     * @param array �����������   
     * @param from �����￪ʼ����   
     * @param end �ŵ�����   
     * @param c �Ƚ���   
     */    
    //������һ�������Ĺ��з���sort�����������������ִ��˽�з���quckSort�����ַ�ʽֵ�ý������  
    public void sort(Integer[] array, int from, int end) {     
        quickSort(array, from, end);     
    }     
    
    /**   
     * �ݹ��������ʵ��   
     * @param array ����������   
     * @param low ��ָ��   
     * @param high ��ָ��   
     * @param c �Ƚ���   
     */    
    private void quickSort(Integer[] array, int low, int high) {     
        /*   
         * ��������еĵ�ָ��С�ڸ�ָ��ʱѭ�������low=higth˵������ֻ��һ��Ԫ�أ������ٴ���   
         * ���low > higth����˵���ϴ���ŦԪ�ص�λ��pivot����low������higth���������   
         * �·������棬Ҳ���账��   
         */    
        if (low < high) {     
            //�Է���������������     
              
            //int pivot = partition1(array, low, high);  
            int pivot = partition2(array, low, high);     
            //int pivot = partition3(array, low, high);        
              
            /*   
             * ��pivotΪ�߽磬������ֳ�������[low, pivot - 1]��[pivot]��[pivot + 1, high]   
             * ����[pivot]Ϊ��ŦԪ�أ����账���ٶ�[low, pivot - 1]��[pivot + 1, high]   
             * ���Խ��з��������������һ������   
             */    
            quickSort(array, low, pivot - 1);     
            quickSort(array, pivot + 1, high);     
        }     
    
    }     
    
    /**   
     * ʵ��һ   
     *    
     * @param array ����������   
     * @param low ��ָ��   
     * @param high ��ָ��   
     * @param c �Ƚ���   
     * @return int ����������λ��   
     */    
    private int partition1(Integer[] array, int low, int high) {     
        Integer pivotElem = array[low];//�Ե�һ��Ԫ��Ϊ����Ԫ��     
        //��ǰ�������ָ�������Ԫ��С��Ԫ�أ��տ�ʼʱָ�����࣬Ҳ��С�����С�����Ԫ�صķֽ��     
        int border = low;     
    
        /*   
         * ������Ԫ�غ����Ԫ���в���С������Ԫ�ص�����Ԫ�أ������δӵڶ���λ�ô�ǰ������   
         * ע���������ʹ��i���ƶ������ֱ���ƶ�low�Ļ������֪������ı߽��ˣ���������Ҫ   
         * ֪������ı߽�   
         */    
        for (int i = low + 1; i <= high; i++) {     
            //����ҵ�һ��������Ԫ��С��Ԫ��     
            if ((array[i].compareTo(pivotElem)) < 0) {     
                swap(array, ++border, i);//borderǰ�ƣ���ʾ��С������Ԫ�ص�Ԫ��     
            }     
        }     
        /*   
         * ���borderû���ƶ�ʱ˵��˵�������Ԫ�ض�������Ԫ��Ҫ��border��low��ȣ���ʱ��   
         * ͬһλ�ý������Ƿ񽻻���û��ϵ����border�Ƶ���highʱ˵������Ԫ�ض�С������Ԫ�أ���   
         * ʱ������Ԫ�������һ��Ԫ�ؽ������ɣ���low��high���н������������Ԫ���Ƶ��� ������   
         * ����� low <minIndex< high���� ����������Ԫ��ǰ����С������Ԫ�أ����󲿷ִ���   
         * ����Ԫ�أ���ʱ����Ԫ����ǰ�������������һ��С������Ԫ�ؽ���λ�ã�ʹ������Ԫ�ط�����   
         * ��ȷ��λ��   
         */    
        swap(array, border, low);     
        return border;     
    }     
    
    /**   
     * ʵ�ֶ�   
     *    
     * @param array ����������   
     * @param low ����������ָ��   
     * @param high ����������ָ��   
     * @param c �Ƚ���   
     * @return int ����������λ��   
     */    
    private int partition2(Integer[] array, int low, int high) {     
        int pivot = low;//����Ԫ��λ�ã������Ե�һ��Ԫ��Ϊ����Ԫ��     
        //�˳���������ֻ������ low = high     
        while (true) {     
            if (pivot != high) {//�������Ԫ���ڵ�ָ��λ��ʱ�������ƶ���ָ��     
                //�����ָ��Ԫ��С������Ԫ��ʱ����������Ԫ�ؽ���     
                if ((array[high].compareTo(array[pivot])) < 0) {     
                    swap(array, high, pivot);     
                    //����������Ԫ���ڸ�ָ��λ����     
                    pivot = high;     
                } else {//���δ�ҵ�С������Ԫ�أ����ָ��ǰ�Ƽ�����     
                    high--;     
                }     
            } else {//��������Ԫ���ڸ�ָ��λ��     
                //�����ָ��Ԫ�ش�������Ԫ��ʱ����������Ԫ�ؽ���     
                if ((array[low].compareTo(array[pivot])) > 0) {     
                    swap(array, low, pivot);     
                    //����������Ԫ���ڵ�ָ��λ����     
                    pivot = low;     
                } else {//���δ�ҵ���������Ԫ�أ����ָ����Ƽ�����     
                    low++;     
                }     
            }     
            if (low == high) {     
                break;     
            }     
        }     
        //��������Ԫ������λ�ã��Ա��´η���     
        return pivot;     
    }     
    
    /**   
     * ʵ����   
     *    
     * @param array ����������   
     * @param low ����������ָ��   
     * @param high ����������ָ��   
     * @param c �Ƚ���   
     * @return int ����������λ��   
     */    
    private int partition3(Integer[] array, int low, int high) {     
        int pivot = low;//����Ԫ��λ�ã������Ե�һ��Ԫ��Ϊ����Ԫ��     
        low++;     
        //----�����ߵ�ָ����ָ���Ԫ��˳�򣬰�С������Ԫ�ص��Ƶ�ǰ���֣���������Ԫ�ص��Ƶ����沿��     
        //�˳���������ֻ������ low = high     
    
        while (true) {     
            //�����ָ��δ������ָ��     
            while (low < high) {     
                //�����ָ��ָ���Ԫ�ش��ڻ��������Ԫ��ʱ��ʾ�ҵ��ˣ��˳���ע������ʱҲҪ����     
                if ((array[low].compareTo(array[pivot])) >= 0) {     
                    break;     
                } else {//�����ָ��ָ���Ԫ��С������Ԫ��ʱ������     
                    low++;     
                }     
            }     
    
            while (high > low) {     
                //�����ָ��ָ���Ԫ��С������Ԫ��ʱ��ʾ�ҵ����˳�     
                if ((array[high].compareTo(array[pivot])) < 0) {     
                    break;     
                } else {//�����ָ��ָ���Ԫ�ش�������Ԫ��ʱ������     
                    high--;     
                }     
            }     
            //�˳�����ѭ��ʱ low = high     
            if (low == high) {     
                break;     
            }     
    
            swap(array, low, high);     
        }     
    
        //----�ߵ�ָ����ָ���Ԫ��������ɺ󣬻���Ҫ������Ԫ�طŵ��ʵ���λ��     
        if ((array[pivot].compareTo(array[low])) > 0) {     
            //����˳�ѭ��ʱ����Ԫ�ش����˵�ָ����ָ��Ԫ��ʱ������Ԫ������lowԪ�ؽ���     
            swap(array, low, pivot);     
            pivot = low;     
        } else if ((array[pivot].compareTo(array[low])) <= 0) {     
            swap(array, low - 1, pivot);     
            pivot = low - 1;     
        }     
    
        //��������Ԫ������λ�ã��Ա��´η���     
        return pivot;     
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
        QuickSort quicksort = new QuickSort();     
        quicksort.sort(intgArr,0,intgArr.length-1);  
        for(Integer intObj:intgArr){  
            System.out.print(intObj + " ");  
        }  
    }     

}
