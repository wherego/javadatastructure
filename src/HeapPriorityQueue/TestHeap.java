package HeapPriorityQueue;

public class TestHeap {

	public static void main(String[] args) {  
        Heap heap = new Heap(new Integer[] { 8, 9, 2, 4, 5, 6, 7, 5, 3, 0 });  
        while (heap.getSize() > 0) {  
            System.out.println(heap.remove() + " ");  
        }  
    }  
	
}
