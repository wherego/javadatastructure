package LeftistHeap;

/**
* Java ����: �����
*
* @author skywang
* @date 2014/03/31
*/


public class LeftistHeap<T extends Comparable<T>> {

    private LeftistNode<T> mRoot;    // �����

    private class LeftistNode<T extends Comparable<T>> {
        T key;                    // �ؼ���(��ֵ)
        int npl;                // ��·������(Null Path Length)
        LeftistNode<T> left;    // ����
        LeftistNode<T> right;    // �Һ���

        public LeftistNode(T key, LeftistNode<T> left, LeftistNode<T> right) {
            this.key = key;
            this.npl = 0;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            return "key:"+key;
        }
    }

    public LeftistHeap() {
        mRoot = null;
    }

    /*
     * ǰ�����"�����"
     */
    private void preOrder(LeftistNode<T> heap) {
        if(heap != null) {
            System.out.print(heap.key+" ");
            preOrder(heap.left);
            preOrder(heap.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * �������"�����"
     */
    private void inOrder(LeftistNode<T> heap) {
        if(heap != null) {
            inOrder(heap.left);
            System.out.print(heap.key+" ");
            inOrder(heap.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    /*
     * �������"�����"
     */
    private void postOrder(LeftistNode<T> heap) {
        if(heap != null)
        {
            postOrder(heap.left);
            postOrder(heap.right);
            System.out.print(heap.key+" ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    /*
     * �ϲ�"�����x"��"�����y"
     */
    private LeftistNode<T> merge(LeftistNode<T> x, LeftistNode<T> y) {
        if(x == null) return y;
        if(y == null) return x;

        // �ϲ�x��yʱ����x��Ϊ�ϲ�������ĸ���
        // ����Ĳ����Ǳ�֤: x��key < y��key
        if(x.key.compareTo(y.key) > 0) {
            LeftistNode<T> tmp = x;
            x = y;
            y = tmp;
        }

        // ��x���Һ��Ӻ�y�ϲ���"�ϲ�������ĸ�"��x���Һ��ӡ�
        x.right = merge(x.right, y);

        // ���"x������Ϊ��" ���� "x�����ӵ�npl<�Һ��ӵ�npl"
         // �򣬽���x��y
         if (x.left == null || x.left.npl < x.right.npl) {
             LeftistNode<T> tmp = x.left;
             x.left = x.right;
             x.right = tmp;
         }
         if (x.right == null || x.left == null)
             x.npl = 0;
         else
             x.npl = (x.left.npl > x.right.npl) ? (x.right.npl + 1) : (x.left.npl + 1);
 
         return x;
     }
 
     public void merge(LeftistHeap<T> other) {
         this.mRoot = merge(this.mRoot, other.mRoot);
     }
 
     /* 
      * �½����(key)����������뵽�������
      *
      * ����˵����
      *     key ������ļ�ֵ
      */
     public void insert(T key) {
         LeftistNode<T> node = new LeftistNode<T>(key,null,null);
 
         // ����½����ʧ�ܣ��򷵻ء�
         if (node != null)
             this.mRoot = merge(this.mRoot, node);
     }
 
     /* 
      * ɾ�������
      * 
      * ����ֵ��
      *     ���ر�ɾ���Ľڵ�ļ�ֵ
      */
     public T remove() {
         if (this.mRoot == null)
             return null;
 
         T key = this.mRoot.key;
         LeftistNode<T> l = this.mRoot.left;
         LeftistNode<T> r = this.mRoot.right;
 
         this.mRoot = null;          // ɾ�����ڵ�
         this.mRoot = merge(l, r);   // �ϲ���������
 
         return key;
     }
 
     /*
      * ���������
      */
     private void destroy(LeftistNode<T> heap) {
         if (heap==null)
             return ;
 
         if (heap.left != null)
             destroy(heap.left);
         if (heap.right != null)
             destroy(heap.right);
 
         heap=null;
     }
 
     public void clear() {
         destroy(mRoot);
         mRoot = null;
     }
 
     /*
      * ��ӡ"�����"
      *
      * key        -- �ڵ�ļ�ֵ 
      * direction  --  0����ʾ�ýڵ��Ǹ��ڵ�;
      *               -1����ʾ�ýڵ������ĸ���������;
      *                1����ʾ�ýڵ������ĸ������Һ��ӡ�
      */
     private void print(LeftistNode<T> heap, T key, int direction) {
 
         if(heap != null) {
 
             if(direction==0)    // heap�Ǹ��ڵ�
                 System.out.printf("%2d(%d) is root\n", heap.key, heap.npl);
             else                // heap�Ƿ�֧�ڵ�
                 System.out.printf("%2d(%d) is %2d's %6s child\n", heap.key, heap.npl, key, direction==1?"right" : "left");
 
             print(heap.left, heap.key, -1);
             print(heap.right,heap.key,  1);
         }
     }
 
     public void print() {
         if (mRoot != null)
             print(mRoot, mRoot.key, 0);
     }
 }