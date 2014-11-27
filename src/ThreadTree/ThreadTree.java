package ThreadTree;

public class ThreadTree {
	
	  private Node root;         // ���ڵ�  
	    private int size;          // ��С  
	    private Node pre = null;   // ��������ʱ�򱣴�ǰ��  
	  
	    public ThreadTree()  
	    {  
	        this.root = null;  
	        this.size = 0;  
	        this.pre = null;  
	    }  
	  
	    public ThreadTree(int[] data)  
	    {  
	        this.pre = null;  
	        this.size = data.length;  
	        this.root = createTree(data, 1);   // ����������  
	    }  
	  
	    /** 
	     * ���������� 
	     *  
	     */  
	    public Node createTree(int[] data, int index)  
	    {  
	        if (index > data.length)  
	        {  
	            return null;  
	        }  
	        Node node = new Node(data[index - 1]);  
	        Node left = createTree(data, 2 * index);  
	        Node right = createTree(data, 2 * index + 1);  
	        node.setLeft(left);  
	        node.setRight(right);  
	        return node;  
	    }  
	  
	    /** 
	     * ����rootΪ���ڵ�Ķ����������� 
	     *  
	     */  
	    public void inThread(Node root)  
	    {  
	        if (root != null)  
	        {  
	            inThread(root.getLeft());     // ����������  
	            if (null == root.getLeft())   // ����Ϊ��  
	            {  
	                root.setLeftIsThread(true);    // ����������Ϊ����  
	                root.setLeft(pre);  
	            }  
	            if (pre != null && null == pre.getRight())  // �Һ���Ϊ��  
	            {  
	                pre.setRightIsThread(true);  
	                pre.setRight(root);  
	            }  
	            pre = root;  
	            inThread(root.getRight());       // �������Һ���  
	        }  
	    }  
	  
	    /** 
	     * ����������������� 
	     *  
	     */  
	    public void inThreadList(Node root)  
	    {  
	        if (root != null)  
	        {  
	            while (root != null && !root.isLeftIsThread())    // ������Ӳ�������  
	            {       
	                root = root.getLeft();  
	            }  
	  
	            do  
	            {  
	                System.out.print(root.getData() + ",");  
	                if (root.isRightIsThread())   // ����Һ���������  
	                {          
	                    root = root.getRight();  
	                }   
	                else         // ���Һ���   
	                {        
	                    root = root.getRight();  
	                    while (root != null && !root.isLeftIsThread())  
	                    {  
	                        root = root.getLeft();  
	                    }  
	                }  
	            } while (root != null);  
	        }  
	    }  
	  
	    /** 
	     * ǰ������ݹ��㷨 
	     *  
	     */  
	    public void preList(Node root)  
	    {  
	        if (root != null)  
	        {  
	            System.out.print(root.getData() + ",");  
	            preList(root.getLeft());  
	            preList(root.getRight());  
	        }  
	    }  
	  
	    /** 
	     * ������� 
	     *  
	     */  
	    public void inList(Node root)  
	    {  
	        if (root != null)  
	        {  
	            inList(root.getLeft());  
	            System.out.print(root.getData() + ",");  
	            inList(root.getRight());  
	        }  
	    }  
	  
	    public Node getRoot()  
	    {  
	        return root;  
	    }  
	  
	    public void setRoot(Node root)  
	    {  
	        this.root = root;  
	    }  
	  
	    public int getSize()  
	    {  
	        return size;  
	    }  
	  
	    public void setSize(int size)  
	    {  
	        this.size = size;  
	    }  

}
