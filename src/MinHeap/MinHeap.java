 package MinHeap;
/**
  * �����(��С��)
  *
 * @author judyge
  * @date 2014/03/07
  */

 import java.util.ArrayList;
 import java.util.List;

public class MinHeap<T extends Comparable<T>> {

     private List<T> mHeap;        // ��Ŷѵ�����

    public MinHeap() {
        this.mHeap = new ArrayList<T>();
     }

     /*
      * ��С�ѵ����µ����㷨
     *
      * ע������ʵ�ֵĶ��У���N���ڵ�����ӵ�����ֵ��(2N+1)���Һ��ӵ�������(2N+2)��
      *
      * ����˵����
     *     start -- ���µ��ڵ����ʼλ��(һ��Ϊ0����ʾ�ӵ�1����ʼ)
      *     end   -- ������Χ(һ��Ϊ���������һ��Ԫ�ص�����)
     */
     protected void filterdown(int start, int end) {
        int c = start;          // ��ǰ(current)�ڵ��λ��
         int l = 2*c + 1;     // ��(left)���ӵ�λ��
         T tmp = mHeap.get(c);    // ��ǰ(current)�ڵ�Ĵ�С

       while(l <= end) {
             int cmp = mHeap.get(l).compareTo(mHeap.get(l+1));
            // 'l'�����ӣ�'l+1'���Һ���
            if(l < end && cmp>0)
                 l++;        // ������������ѡ���С�ߣ���mHeap[l+1]

            cmp = tmp.compareTo(mHeap.get(l));
             if(cmp <= 0)
                 break;        //��������
             else {
                mHeap.set(c, mHeap.get(l));
                 c = l;
                 l = 2*l + 1;  
            }      
         }  
        mHeap.set(c, tmp);
     }
     
     /*
      * ��С�ѵ�ɾ��
      *
      * ����ֵ��
     *     �ɹ������ر�ɾ����ֵ
      *     ʧ�ܣ�����null
     */
    public int remove(T data) {
         // ���'��'�ѿգ��򷵻�-1
         if(mHeap.isEmpty() == true)
            return -1;

         // ��ȡdata�������е�����
         int index = mHeap.indexOf(data);
         if (index==-1)
            return -1;

         int size = mHeap.size();
         mHeap.set(index, mHeap.get(size-1));// �����Ԫ���
         mHeap.remove(size - 1);                // ɾ������Ԫ��

         if (mHeap.size() > 1)
            filterdown(index, mHeap.size()-1);    // ��index��λ�ÿ�ʼ�������µ���Ϊ��С��

        return 0;
    }

     /*
     * ��С�ѵ����ϵ����㷨(��start��ʼ����ֱ��0��������)
      *
      * ע������ʵ�ֵĶ��У���N���ڵ�����ӵ�����ֵ��(2N+1)���Һ��ӵ�������(2N+2)��
     *
      * ����˵����
      *     start -- ���ϵ��ڵ����ʼλ��(һ��Ϊ���������һ��Ԫ�ص�����)
      */
     protected void filterup(int start) {
         int c = start;            // ��ǰ�ڵ�(current)��λ��
         int p = (c-1)/2;        // ��(parent)����λ��
        T tmp = mHeap.get(c);        // ��ǰ�ڵ�(current)�Ĵ�С

         while(c > 0) {
             int cmp = mHeap.get(p).compareTo(tmp);
             if(cmp <= 0)
                 break;
             else {
                mHeap.set(c, mHeap.get(p));
                c = p;
                 p = (p-1)/2;  
             }      
         }

         mHeap.set(c, tmp);
    }
 
     /*
     * ��data���뵽�������
      */
     public void insert(T data) {
         int size = mHeap.size();

        mHeap.add(data);    // ��'����'���ڱ�β
         filterup(size);        // ���ϵ�����
    }
      
     public String toString() {
         StringBuilder sb = new StringBuilder();
         for (int i=0; i<mHeap.size(); i++)
            sb.append(mHeap.get(i).toString() +' ');

         return sb.toString();
     }

     public static void main(String[] args) {
         int i;
         int a[] = {80, 40, 30, 60, 90, 70, 10, 50, 20};
         MinHeap<Integer> tree=new MinHeap<Integer>();

        System.out.println("==�������: ");
        for(i=0; i<a.length; i++) {
             System.out.println(a[i]);
             tree.insert(a[i]);
         }

         System.out.println("== �� С ��: "+tree);

         i=15;
        tree.insert(i);
        System.out.printf("== ���Ԫ��: "+i);
        System.out.printf("== �� С ��: "+tree);

        i=10;
         tree.remove(i);
         System.out.printf(" ɾ��Ԫ��: "+i);
        System.out.printf("== �� С ��: "+tree);
        System.out.printf("");
    }
}
