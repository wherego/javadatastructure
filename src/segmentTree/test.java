/** 
 * 线段树入门 
 * 问题：已知线段[2,5] [4,6] [0,7]；求点2,4,7分别出现了多少次 
 * 以下代码建立的线段树用链表来保存，且树的叶子结点类似[i,i] 
 */  


package segmentTree;
import segmentTree.SegmentTree;

public class test {
	
	public static void main(String[] args) {  
        SegmentTree tree = new SegmentTree(0, 7);  
        int[][] segments = {  
                {2, 5},   
                {4, 6},   
                {0, 7}  
        };  
        int[] targets = {2, 4, 7};  
        for (int i = 0, len = segments.length; i < len; i++) {  
            int[] segment = segments[i];  
            tree.insert(segment[0], segment[1]);  
        }  
        for(int target : targets) {  
            System.out.println(target + ":" + tree.caculateExistingTimes(target));  
        }  
    }  
	

}
