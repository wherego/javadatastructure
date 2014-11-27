package HeapPriorityQueue;
import java.util.ArrayList;

public class Heap {
	
	private ArrayList list = new ArrayList();  
	  
    public Heap() {  
  
    }  
  
    public Heap(Object[] objects) {  
        for (int i = 0; i < objects.length; i++) {  
            add(objects[i]);  
        }  
    }  
  
    public void add(Object newObject) {  
        list.add(newObject);  
        //the index of the last node  
        int currentIndex = list.size() - 1;  
  
        while (currentIndex > 0) {  
            //���㸸�ڵ��index  
            int parentIndex = (currentIndex - 1) / 2;  
            //�����ǰ�ڵ�������ĸ��ڵ�ͽ���  
            if (((Comparable) (list.get(currentIndex))).compareTo(list  
                    .get(parentIndex)) > 0) {  
                Object temp = list.get(currentIndex);  
                list.set(currentIndex, list.get(parentIndex));  
                list.set(parentIndex, temp);  
            } else {  
                break;  
            }  
            currentIndex = parentIndex;  
        }  
    }  
  
    /** 
     * remove the root from the heap 
     *  
     * @return 
     */  
    public Object remove() {  
        if (list.size() == 0) {  
            return null;  
        }  
        //��ɾ���Ľڵ�---���ڵ�  
        Object removedObject = list.get(0);  
          
        //�����һ���ƶ������ڵ�  
        list.set(0,  list.get(list.size() - 1));  
        list.remove(list.size() - 1);  
          
        int currentIndex = 0;  
        while (currentIndex < list.size()) {  
            //���㵱ǰ�ڵ����ڵ���ҽڵ�  
            int leftChildIndex = 2 * currentIndex + 1;  
            int rightChileIndex = 2 * currentIndex + 2;  
              
            //�ҵ������ӽڵ������Ľڵ�  
            if (leftChildIndex >= list.size()) {  
                break;  
            }  
            int maxIndex = leftChildIndex;  
            if (rightChileIndex < list.size()) {  
                if (((Comparable) (list.get(maxIndex))).compareTo(list  
                        .get(rightChileIndex)) < 0) {  
                    maxIndex = rightChileIndex;  
                }  
            }  
  
            //�����ǰ�ڵ�С���ӽڵ�����ֵ�ͽ���  
            if (((Comparable) (list.get(currentIndex))).compareTo(list  
                    .get(maxIndex)) < 0) {  
                Object temp = list.get(maxIndex);  
                list.set(maxIndex, list.get(currentIndex));  
                list.set(currentIndex, temp);  
                currentIndex = maxIndex;  
            } else {  
                break;  
            }  
        }  
        return removedObject;  
    }  
  
    public int getSize() {  
        return list.size();  
    }  

}
