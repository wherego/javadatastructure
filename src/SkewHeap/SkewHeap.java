package SkewHeap;

public class SkewHeap<T extends Comparable<T>> {
	 
	     private SkewNode<T> mRoot;    // �����
	 
	     private class SkewNode<T extends Comparable<T>> {
	         T key;                // �ؼ���(��ֵ)
	         SkewNode<T> left;    // ����
	         SkewNode<T> right;    // �Һ���
	 
	         public SkewNode(T key, SkewNode<T> left, SkewNode<T> right) {
	             this.key = key;
	             this.left = left;
	             this.right = right;
	         }
	 
	         public String toString() {
	             return "key:"+key;
	         }
	     }
	 
	     public SkewHeap() {
	         mRoot = null;
	     }
	 
	     /*
	      * ǰ�����"б��"
	      */
	     private void preOrder(SkewNode<T> heap) {
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
	      * �������"б��"
	      */
	     private void inOrder(SkewNode<T> heap) {
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
	      * �������"б��"
	      */
	     private void postOrder(SkewNode<T> heap) {
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
	      * �ϲ�"б��x"��"б��y"
	      */
	     private SkewNode<T> merge(SkewNode<T> x, SkewNode<T> y) {
	         if(x == null) return y;
	         if(y == null) return x;
	 
	         // �ϲ�x��yʱ����x��Ϊ�ϲ�������ĸ���
	         // ����Ĳ����Ǳ�֤: x��key < y��key
	         if(x.key.compareTo(y.key) > 0) {
	             SkewNode<T> tmp = x;
	             x = y;
	             y = tmp;
	         }
	 
	         // ��x���Һ��Ӻ�y�ϲ���
	         // �ϲ���ֱ�ӽ���x�����Һ��ӣ�������Ҫ�������һ���������ǵ�npl��
	         SkewNode<T> tmp = merge(x.right, y);
	         x.right = x.left;
	         x.left = tmp;
	 
	         return x;
	     }
	 
	     public void merge(SkewHeap<T> other) {
	         this.mRoot = merge(this.mRoot, other.mRoot);
	     }
	 
	     /* 
	      * �½����(key)����������뵽б����
	      *
	      * ����˵����
	      *     key ������ļ�ֵ
	      */
	     public void insert(T key) {
	         SkewNode<T> node = new SkewNode<T>(key,null,null);
	 
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
	         SkewNode<T> l = this.mRoot.left;
	         SkewNode<T> r = this.mRoot.right;
	 
	         this.mRoot = null;          // ɾ�����ڵ�
	         this.mRoot = merge(l, r);   // �ϲ���������
	 
	         return key;
	     }
	 
	     /*
	      * ����б��
	      */
	     private void destroy(SkewNode<T> heap) {
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
	      * ��ӡ"б��"
	      *
	      * key        -- �ڵ�ļ�ֵ 
	      * direction  --  0����ʾ�ýڵ��Ǹ��ڵ�;
	      *               -1����ʾ�ýڵ������ĸ���������;
	      *                1����ʾ�ýڵ������ĸ������Һ��ӡ�
	      */
	     private void print(SkewNode<T> heap, T key, int direction) {
	 
	         if(heap != null) {
	 
	             if(direction==0)    // heap�Ǹ��ڵ�
	                 System.out.printf("%2d is root\n", heap.key);
	             else                // heap�Ƿ�֧�ڵ�
	                 System.out.printf("%2d is %2d's %6s child\n", heap.key, key, direction==1?"right" : "left");
	 
	             print(heap.left, heap.key, -1);
	             print(heap.right,heap.key,  1);
	         }
	     }
	 
	     public void print() {
	         if (mRoot != null)
	             print(mRoot, mRoot.key, 0);
	     }
	 }
