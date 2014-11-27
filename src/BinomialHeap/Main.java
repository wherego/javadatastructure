package BinomialHeap;

public class Main {
	
	
	private static final boolean DEBUG = false;
	 
	     // ��7�� = 1+2+4
	     private static int a[] = {12,  7, 25, 15, 28, 33, 41};
	     // ��13�� = 1+4+8
	     private static int b[] = {18, 35, 20, 42,  9, 
	                31, 23,  6, 48, 11, 
	                24, 52, 13 };
	 
	 
	     // ��֤"����ѵĲ������"
	     public static void testInsert() {
	         BinomialHeap<Integer> ha=new BinomialHeap<Integer>();
	 
	         // �����ha
	         System.out.printf("== �����(ha)���������: ");
	         for(int i=0; i<a.length; i++) {
	             System.out.printf("%d ", a[i]);
	             ha.insert(a[i]);
	         }
	         System.out.printf("\n");
	         System.out.printf("== �����(ha)����ϸ��Ϣ: \n");
	         ha.print();
	     }
	 
	     // ��֤"����ѵĺϲ�����"
	     public static void testUnion() {
	         BinomialHeap<Integer> ha=new BinomialHeap<Integer>();
	         BinomialHeap<Integer> hb=new BinomialHeap<Integer>();
	 
	         // �����ha
	         System.out.printf("== �����(ha)���������: ");
	         for(int i=0; i<a.length; i++) {
	             System.out.printf("%d ", a[i]);
	             ha.insert(a[i]);
	         }
	         System.out.printf("\n");
	         System.out.printf("== �����(ha)����ϸ��Ϣ: \n");
	         ha.print();
	 
	         // �����hb
	         System.out.printf("== �����(hb)���������: ");
	         for(int i=0; i<b.length; i++) {
	             System.out.printf("%d ", b[i]);
	             hb.insert(b[i]);
	         }
	         System.out.printf("\n");
	         // ��ӡ�����hb
	         System.out.printf("== �����(hb)����ϸ��Ϣ: \n");
	         hb.print();
	 
	         // ��"�����hb"�ϲ���"�����ha"�С�
	         ha.union(hb);
	         // ��ӡ�����ha����ϸ��Ϣ
	         System.out.printf("== �ϲ�ha��hb�����ϸ��Ϣ:\n");
	         ha.print();
	     }
	 
	     // ��֤"����ѵ�ɾ������"
	     public static void testDelete() {
	         BinomialHeap<Integer> hb=new BinomialHeap<Integer>();
	 
	         // �����hb
	         System.out.printf("== �����(hb)���������: ");
	         for(int i=0; i<b.length; i++) {
	             System.out.printf("%d ", b[i]);
	             hb.insert(b[i]);
	         }
	         System.out.printf("\n");
	         // ��ӡ�����hb
	         System.out.printf("== �����(hb)����ϸ��Ϣ: \n");
	         hb.print();
	 
	         // ��"�����hb"�ϲ���"�����ha"�С�
	         hb.remove(20);
	         System.out.printf("== ɾ���ڵ�20�����ϸ��Ϣ: \n");
	         hb.print();
	     }
	 
	     // ��֤"����ѵĸ���(����)����"
	     public static void testDecrease() {
	         BinomialHeap<Integer> hb=new BinomialHeap<Integer>();
	 
	         // �����hb
	         System.out.printf("== �����(hb)���������: ");
	         for(int i=0; i<b.length; i++) {
	             System.out.printf("%d ", b[i]);
	             hb.insert(b[i]);
	         }
	         System.out.printf("\n");
	         // ��ӡ�����hb
	         System.out.printf("== �����(hb)����ϸ��Ϣ: \n");
	         hb.print();
	 
	         // ���ڵ�20����Ϊ2
	         hb.update(20, 2);
	         System.out.printf("== ���½ڵ�20->2�����ϸ��Ϣ: \n");
	         hb.print();
	     }
	 
	     // ��֤"����ѵĸ���(����)����"
	     public static void testIncrease() {
	         BinomialHeap<Integer> hb=new BinomialHeap<Integer>();
	 
	         // �����hb
	         System.out.printf("== �����(hb)���������: ");
	         for(int i=0; i<b.length; i++) {
	             System.out.printf("%d ", b[i]);
	             hb.insert(b[i]);
	         }
	         System.out.printf("\n");
	         // ��ӡ�����hb
	         System.out.printf("== �����(hb)����ϸ��Ϣ: \n");
	         hb.print();
	 
	         // ���ڵ�6����Ϊ60
	         hb.update(6, 60);
	         System.out.printf("== ���½ڵ�6->60�����ϸ��Ϣ: \n");
	         hb.print();
	     }
	 
	     public static void main(String[] args) {
	         // 1. ��֤"����ѵĲ������"
	         testInsert();
	         // 2. ��֤"����ѵĺϲ�����"
	         //testUnion();
	         // 3. ��֤"����ѵ�ɾ������"
	         //testDelete();
	         // 4. ��֤"����ѵĸ���(����)����"
	         //testDecrease();
	         // 5. ��֤"����ѵĸ���(����)����"
	         //testIncrease();
	     }

}
