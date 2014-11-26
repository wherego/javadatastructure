package sort;
/** 
�鲢����������һ���ݹ����������֮�� 
*/  
public class MergeSort {
	
	 /**   
     * �ݹ黮������   
     * @param arr   
     * @param from   
     * @param end   
     * @param c void   
     */    
    public void partition(Integer[] arr, int from, int end) {     
        //���ֵ�����ֻ��һ��Ԫ��ʱ�Ų������ٻ���     
        if (from < end) {     
            //���м仮�ֳ���������     
            int mid = (from + end) / 2;     
            partition(arr, from, mid);     
            partition(arr, mid + 1, end);     
            //�ϲ����ֺ����������     
            merge(arr, from, end, mid);     
        }     
    }     
    
    /**   
     * ����ϲ����ϲ������ж������������������   
     * ǰ���������������������   
     * @param arr   
     * @param from   
     * @param end   
     * @param mid   
     * @param c void   
     */    
    public void merge(Integer[] arr, int from, int end, int mid) {     
        Integer[] tmpArr = new Integer[10];  
        int tmpArrIndex = 0;//ָ����ʱ����     
        int part1ArrIndex = from;//ָ���һ��������     
        int part2ArrIndex = mid + 1;//ָ��ڶ���������     
    
        //����������������������ģ�����ÿ���ֿ��Դӵ�һ��Ԫ������ȡ�����һ��Ԫ�أ��ٶ�������     
        //ȡ����Ԫ�ؽ��бȽϡ�ֻҪĳ��������Ԫ��ȡ����˳�ѭ��     
        while ((part1ArrIndex <= mid) && (part2ArrIndex <= end)) {     
            //���������������ȡһ�����бȽϣ�ȡ��Сһ����������ʱ������     
            if (arr[part1ArrIndex] - arr[part2ArrIndex] < 0) {     
                //�����һ��������Ԫ��С���򽫵�һ��������Ԫ�ط�����ʱ�����У�������ʱ����ָ��     
                //tmpArrIndex����һ���������´δ洢λ��׼����ǰ��������ָ��part1ArrIndex     
                //ҲҪ����һ���Ա��´�ȡ����һ��Ԫ����󲿷�����Ԫ�رȽ�     
                tmpArr[tmpArrIndex++] = arr[part1ArrIndex++];     
            } else {     
                //����ڶ���������Ԫ��С���򽫵ڶ���������Ԫ�ط�����ʱ������     
                tmpArr[tmpArrIndex++] = arr[part2ArrIndex++];     
            }     
        }     
        //�����˳�ѭ���������������п�����һ������Ԫ�ػ�δ�����꣬������Ҫ����Ĵ�����Ȼ����     
        //�����������鶼��δ�������Ԫ�أ�������������ѭ�����ֻ��һ����ִ�У����Ҷ��Ǵ����ѷ���     
        //��ʱ�����е�Ԫ��     
        while (part1ArrIndex <= mid) {     
            tmpArr[tmpArrIndex++] = arr[part1ArrIndex++];     
        }     
        while (part2ArrIndex <= end) {     
            tmpArr[tmpArrIndex++] = arr[part2ArrIndex++];     
        }     
    
        //������ʱ���鿽����Դ������ͬ��λ��     
        System.arraycopy(tmpArr, 0, arr, from, end - from + 1);     
    }     
    
    /**   
     * ����   
     * @param args   
     */    
    public static void main(String[] args) {     
        Integer[] intgArr = {5,9,1,4,2,6,3,8,0,7};     
        MergeSort insertSort = new MergeSort();     
        insertSort.partition(intgArr,0,intgArr.length-1);  
        for(Integer a:intgArr){  
            System.out.print(a + " ");  
        }  
    }     

}
