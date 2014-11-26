package sort;
/**   
 * Ͱʽ����:   
 * Ͱʽ�������ǻ��ڱȽϵ��ˣ����ͻ�������ͬ���ڷ����������   
 * ����������ص�������Ҫ֪������ ���е�һЩ������   
 * Ͱʽ��������Ҫ֪������ ������һ����Χ�ڣ����������ΧӦ�ò��Ǻܴ�ġ�   
 * ����֪������������[0,M���ڣ���ô���Է���M��Ͱ����I��Ͱ��¼I�ĳ��������   
 * ������ÿ��Ͱ�յ���λ����Ϣ������������������ʽ��   
 * ����������������ʱ�����飬һ�����ڼ�¼λ����Ϣ��һ�����ڷ���������ݳ�����ʽ��   
 * �������Ǽ�����������0��MAX,����������ݲ��Ǵ�0��ʼ������԰�ÿ������ȥ��С������   
 */  
public class BucketSort {
	
	 public void sort(int[] keys,int from,int len,int max)     
     {     
         int[] temp=new int[len];     
         int[] count=new int[max];     
              
              
         for(int i=0;i<len;i++)     
         {     
             count[keys[from+i]]++;     
         }     
         //calculate position info     
         for(int i=1;i<max;i++)     
         {     
             count[i]=count[i]+count[i-1];//����ζ���ж�����ĿС�ڻ����i�������Ҳ��position+ 1     
         }     
              
         System.arraycopy(keys, from, temp, 0, len);     
         for(int k=len-1;k>=0;k--)//����ĩ����ͷ�����ȶ���     
         {     
             keys[--count[temp[k]]]=temp[k];// position +1 =count     
         }     
     }     
     /**   
      * @param args   
      */    
     public static void main(String[] args) {     
 
         int[] a={1,4,8,3,2,9,5,0,7,6,9,10,9,13,14,15,11,12,17,16};     
         BucketSort bucketSort=new BucketSort();     
         bucketSort.sort(a,0,a.length,20);//actually is 18, but 20 will also work     
              
              
         for(int i=0;i<a.length;i++)     
         {     
             System.out.print(a[i]+",");     
         }     
 
     }     

}
