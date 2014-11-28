package BlockLinkList;
import java.util.*;

public class BlockLinkList {

	
	public BlockLinkNode blockLinkNode = null;
	private int total;
	
	        public BlockLinkList()
	        {
	            //��ʼ���ڵ�
	        	
	        	// blockLinkNode = new BlockLinkNode(null,null,list);
	      
	        }
	
	        
	        
	
	       
	
	        
	        public boolean IsExist(int num)
	        {
	            boolean isExist = false;
	
	            BlockLinkNode temp = blockLinkNode;
	
	            while (temp != null)
	            {
	                //�ж��Ƿ��ڸ�������
	                if (temp.list.size()-1 > 0 && num >= temp.list.get(0) && num <= temp.list.get(temp.list.size() - 1))
	                {
	                    isExist = temp.list.indexOf(num) > 0 ? true : false;
	
	                    return isExist;
	                }
	
	                temp = temp.next;
	            }
	
	            return isExist;
	        }
	
	        public String Get(int num)
	        {
	        	int blockIndex = 0;
	            int arrIndex = 0;
	
	            BlockLinkNode temp = blockLinkNode;
	
	            while (temp != null)
	            {
	                //�ж��Ƿ��ڸ�������
	                if (temp.list.size()-1 > 0 && num >= temp.list.get(0) && num <= temp.list.get(temp.list.size()- 1))
	                {
	                    arrIndex = temp.list.indexOf(num);
	
	                    return String.format("��ǰ�����ڵ�{0}���е�{1}��λ��", blockIndex, arrIndex);
	                }
	
	                blockIndex = blockIndex + 1;
	                temp = temp.next;
	            }
	
	             String str=null;
	             str="";
	             return str;
	        }
	
	       
	        
	        
	        public BlockLinkNode Add(int num)
	        {
	            return Add(blockLinkNode, num);
	        }
	
	       
	        
	        private BlockLinkNode Add(BlockLinkNode node, int num)
	        {
	            if (node == null)
	            {
	                return node;
	            }
	            else
	            {
	                /*
	                 *  ��һ�����ҵ�ָ���Ľڵ�
	                 */
	                if (node.list.size()== 0)
	                {
	                    node.list.add(num);
	
	                    total = total + 1;
	
	                    return node;
	                }
	
	                //��һ�����ٱȽ��Ƿ�Ӧ�÷��ѿ�
	                int blockLen = (int)Math.ceil(Math.sqrt(total)) * 2;
	
	                //����ýڵ����������λ��ֵ���ڲ���ֵ�����ʱ�����ҵ�������Ĳ���ڵ㣬
	                //���߸ýڵ��next=null��˵�������һ���ڵ㣬��ʱҲҪ�ж��Ƿ�Ҫ�ѿ�
	                if (node.list.get(node.list.size() - 1) > num || node.next == null)
	                {
	                    node.list.add(num);
	
	                    //�����������£���Ȼ�����ò�����������O(N)�㶨
	                    Collections.sort(node.list);
	
	                    //�������������ĸ�������2*blockLen��˵���Ѿ������ˣ���ʱ��Ҫ�԰����
	                    if (node.list.size() > blockLen)
	                    {
	                        //�Ƚ����ݲ��뵽���ݿ�
	                        int mid = node.list.size()/2-1;
	
	                        //���Ѵ���ǰ�β���
	                        ArrayList<Integer> firstList = new ArrayList<Integer>();
	
	                        //���Ѻ�ĺ�β���
	                        ArrayList<Integer> lastList = new ArrayList<Integer>();
	
	                        //�����ڲ���㴦���ѣ�Ҳ���Զ԰����(����԰����)
	                        
	                        
	                        for(int i=0;i<mid;i++)
	                        {
	                        	firstList.add(i,node.list.get(i));
	                        }
	                       
	                        for(int i=mid;i<node.list.size()-1;i++)
	                        {
	                        	lastList.add(i,node.list.get(i));
	                        }
	                        
	
	
	                        //��ʼ���ѽڵ㣬��Ҫ�¿���һ���½ڵ�
	                        ArrayList<Integer> tlist = new ArrayList<Integer>();
	                        BlockLinkNode nNode = new BlockLinkNode(null,null,tlist);
	
	                        nNode.list = lastList;
	                        nNode.next = node.next;
	                        nNode.prev = node;
	
	                        //�ı䵱ǰ�ڵ��next��list
	                        node.list = firstList;
	                        node.next = nNode;
	                    }
	
	                    total = total + 1;
	
	                    return node;
	                }
	
	                return Add(node.next, num);
	            }
	        }
	
	       
	        
	        public BlockLinkNode Remove(int num)
	        {
	            return Remove(blockLinkNode, num);
	        }
	
	        
	        
	        private BlockLinkNode Remove(BlockLinkNode node, int num)
	        {
	            if (node == null)
	            {
	                return node;
	            }
	            else
	            {
	                //��һ���� �ж�ɾ��Ԫ���Ƿ��ڸýڵ���
	             if (node.list.size()-1> 0 && num >= node.list.get(num) && num <= node.list.get(node.list.size() - 1))
	                {
	                    //����Ľڵ��Ŀ�����ڷ�ֹremove������ɾ�����������
	                    int prevcount = node.list.size();
	
	                    node.list.remove(num);
	
	                    total = total - (prevcount - node.list.size());
	
	                    //��һ���� �ж��Ƿ���Ҫ�ϲ��ڵ�
	                    int blockLen = (int)Math.ceil(Math.sqrt(total) / 2);
	
	                    //�����ǰ�ڵ���������С�� blocklen�Ļ�����ô��ʱ�Ľڵ���Ҫ�ͺ�һ���ڵ���кϲ�
	                    //����ýڵ�ʱβ�ڵ㣬������ϲ�
	                    if (node.list.size()< blockLen)
	                    {
	                        if (node.next != null)
	                        {
	                            node.list.addAll(node.next.list);
	
	                            //�����һ���ڵ����һ���ڵ㲻Ϊnull�������¸��ڵ��prev��ֵ
	                            if (node.next.next != null)
	                                node.next.next.prev = node;
	
	                            node.next = node.next.next;
	                        }
	                        else
	                        {
	                            //���һ���ڵ㲻��Ҫ�ϲ������list=0����ֱ���޳��ýڵ�
	                            if (node.list.size()== 0)
	                            {
	                                if (node.prev != null)
	                                    node.prev.next = null;
	
	                                node = null;
	                            }
	                        }
	                    }
	
	                    return node;
	                }
	
	                return Remove(node.next, num);
	            }
	        }
	
	       
	        public int GetCount()
	        {
	            int count = 0;
	
	            BlockLinkNode temp = blockLinkNode;
	
	            System.out.println("���ڵ����ݸ���Ϊ��");
	
	            while (temp != null)
	            {
	                count += temp.list.size();
	
	                System.out.println(temp.list.size() + ",");
	
	                temp = temp.next;
	            }
	
	            System.out.println("�ܹ���:{0} ��Ԫ��"+count);
	
	            return count;
	        }
	    }
	

