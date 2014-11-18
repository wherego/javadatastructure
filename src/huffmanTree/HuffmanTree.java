package huffmanTree;
import java.util.ArrayDeque;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Queue;  

public class HuffmanTree {
	
	 /** 
     * 节点实体 
     */  
    public static class Node<T> {  
  
        // 数据  
        T data;  
  
        // 权重  
        int power;  
  
        Node<T> leftNode;  
  
        Node<T> rightNode;  
  
        public Node(T data, int power) {  
            this.data = data;  
            this.power = power;  
        }  
  
        @Override  
        public String toString() {  
            // TODO Auto-generated method stub  
            return "[data:" + data + "   power:" + power + "]";  
        }  
  
        @SuppressWarnings("unchecked")  
        public boolean compareTo(Node node) {  
  
            if (this.power < node.power) {  
                return true;  
            }  
  
            return false;  
        }  
  
    }  
  
    /** 
     * 将集合将序排序 
     *  
     * @param <T> 
     * @param <E> 
     *  
     * @param list 
     */  

    public static void sort(List<Node> list) {  
  
        for (int i = 0; i < list.size() - 1; i++) {  
  
            for (int j = i + 1; j < list.size(); j++) {  
  
                if (list.get(i).compareTo(list.get(j))) {  
                    // 交换数组中的元素位置  
                    Node node = list.get(i);  
  
                    list.set(i, list.get(j));  
  
                    list.set(j, node);  
                }  
  
            }  
  
        }  
  
    }  
  
    /** 
     * 创建哈夫曼树 
     *  
     * @param list 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static Node createHuffmanTree(List<Node> list) {  
  
        while (list.size() > 1) {  
            sort(list);  
  
            Node left = list.get(list.size() - 1);  
  
            Node right = list.get(list.size() - 2);  
  
            Node parent = new Node("父节点", left.power + right.power);  
  
            parent.leftNode = left;  
            parent.rightNode = right;  
  
            list.remove(list.size() - 1);  
            list.remove(list.size() - 1);  
  
            list.add(parent);  
        }  
        return list.get(0);  
    }  
  

    public static List<Node> deepFirst(Node root) {  
  
        List<Node> list = new ArrayList<Node>();  
        Queue<Node> queue = new ArrayDeque<Node>();  
        queue.add(root);  
  
        while (!queue.isEmpty()) {  
  
            list.add(queue.peek());  
  
            Node twoLinkNode = queue.poll();  
  
            if (twoLinkNode.leftNode != null) {  
                queue.add(twoLinkNode.leftNode);  
            }  
  
            if (twoLinkNode.rightNode != null) {  
                queue.add(twoLinkNode.rightNode);  
            }  
        }  
  
        return list;  
    }  
  
	
	
}
