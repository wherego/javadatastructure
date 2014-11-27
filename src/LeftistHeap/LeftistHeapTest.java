package LeftistHeap;

public class LeftistHeapTest {

	 public static void main(String[] args) {
		          int a[]= {10,40,24,30,36,20,12,16};
		          int b[]= {17,13,11,15,19,21,23};
		          LeftistHeap<Integer> ha=new LeftistHeap<Integer>();
		          LeftistHeap<Integer> hb=new LeftistHeap<Integer>();
		  
		          System.out.printf("== �����(ha)���������: ");
		          for(int i=0; i<a.length; i++) {
		              System.out.printf("%d ", a[i]);
		              ha.insert(a[i]);
		          }
		          System.out.printf("\n== �����(ha)����ϸ��Ϣ: \n");
		          ha.print();
		  
		  
		          System.out.printf("\n== �����(hb)���������: ");
		          for(int i=0; i<b.length; i++) {
		              System.out.printf("%d ", b[i]);
		              hb.insert(b[i]);
		          }
		          System.out.printf("\n== �����(hb)����ϸ��Ϣ: \n");
		          hb.print();
		  
		          // ��"�����hb"�ϲ���"�����ha"�С�
		          ha.merge(hb);
		          System.out.printf("\n== �ϲ�ha��hb�����ϸ��Ϣ: \n");
		          ha.print();
		      }
	
}
