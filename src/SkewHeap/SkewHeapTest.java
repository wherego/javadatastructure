package SkewHeap;

public class SkewHeapTest {

	 public static void main(String[] args) {
		  
		          int a[]= {10,40,24,30,36,20,12,16};
		          int b[]= {17,13,11,15,19,21,23};
		          SkewHeap<Integer> ha=new SkewHeap<Integer>();
		          SkewHeap<Integer> hb=new SkewHeap<Integer>();
		  
		          System.out.printf("== б��(ha)���������: ");
		          for(int i=0; i<a.length; i++) {
		              System.out.printf("%d ", a[i]);
		              ha.insert(a[i]);
		          }
		          System.out.printf("\n== б��(ha)����ϸ��Ϣ: \n");
		          ha.print();
		  
		  
		          System.out.printf("\n== б��(hb)���������: ");
		          for(int i=0; i<b.length; i++) {
		              System.out.printf("%d ", b[i]);
		              hb.insert(b[i]);
		          }
		          System.out.printf("\n== б��(hb)����ϸ��Ϣ: \n");
		          hb.print();
		  
		          // ��"б��hb"�ϲ���"б��ha"�С�
		          ha.merge(hb);
		          System.out.printf("\n== �ϲ�ha��hb�����ϸ��Ϣ: \n");
		          ha.print();
		      }
		  }
