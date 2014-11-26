package ac_auto;
import ac_auto.TrieNode;

import java.util.*;

public class Trie {

	        public TrieNode trieNode = new TrieNode();
	 
	        public Queue<TrieNode> queue = new LinkedList<TrieNode>();
	 
	    
	        
	         public void AddTrieNode(char[] word, int id)
	         {
	              AddTrieNode(trieNode, word, id);
	          }
	  
	       
	         
	        public void AddTrieNode(TrieNode root, char[] word, int id)
	         {
	             
	
          //���ַ���ַ�����㽫���ַ����뵽26�����е���һ����
	             int k = word[0]-'a';
	             
	 
	             //����ò���Ϊ�գ����ʼ��
	             if (root.childNodes[k] == null)
	             {
	                 root.childNodes[k] = new TrieNode();
	 
	                //��¼���ַ�
	                root.childNodes[k].nodeChar = word[0];
	            }
	             char[] tmp=new char[26];
	             for(int i=1;i<=word.length;i++){
	            	 if (i==word.length)
			                return;
	            	tmp[i-1]=word[i];
	            	word[i-1]=tmp[i-1];
	             }
	             
	             
	             char[] nextWord=tmp;
	             
	             //˵�������һ���ַ���ͳ�Ƹôʳ��ֵĴ���
	             if (nextWord.length == 0)
	             {
	                 root.childNodes[k].freq++;
	                 root.childNodes[k].hashSet.add(id);
	             }
	
	             AddTrieNode(root.childNodes[k], nextWord, id);
	         }
	       
	 
	       
         
	         public void BuildFailNodeBFS()
	         {
	            BuildFailNodeBFS(trieNode);
	         }
	 
	        
	         
	         public void BuildFailNodeBFS(TrieNode root)
	         {
	             //���ڵ����
	             queue.offer(root);
	 
	            while (queue.peek() != null)
	             {
	                 //����
	            	TrieNode temp = queue.poll();
	 
	                 //ʧ�ܽڵ�
	                TrieNode failNode = null;
	 
	                //26����
	                 for (int i = 0; i < 26; i++)
	                {
                    //���뼼�ɣ���BFS��ʽ���ӵ�ǰ�ڵ����亢�ӽڵ㣬��ʱ���ӽڵ�
	                     //         �ĸ������ǵ�ǰ�ڵ㣬��������parent�ڵ�Ĵ��ڣ�
	                    if (temp.childNodes[i] == null)
	                        continue;
	
	                  //�����ǰ�Ǹ��ڵ㣬����ڵ��ʧ��ָ��ָ��root
	                     if (temp == root)
	                    {
	                        temp.childNodes[i].faliNode = root;
	                    }
	                     else
	                    {
	                       //��ȡ���ӽڵ��ʧ��ָ��
	                        failNode = temp.faliNode;
	
	                         //���������ڵ��ʧ��ָ���ߣ�һֱҪ�ҵ�һ���ڵ㣬ֱ�����Ķ���Ҳ�����ýڵ㡣
	                         while (failNode != null)
	                        {
	                             //�����Ϊ�գ����ڸ���ʧ�ܽڵ������ӽڵ������롣
	                            if (failNode.childNodes[i] != null)
	                            {
	                                temp.childNodes[i].faliNode = failNode.childNodes[i];
	                                break;
	                            }
	                             //����޷������ӽڵ㣬���˻ص�����ʧ�ܽڵ㲢��root�ڵ����������죬ֱ��null
	                           //��һ������������Ĺ��̣��ǳ�����˼��
	                             failNode = failNode.faliNode;
	                        }
	 
	                        //����null�Ļ���ָ��root�ڵ�
	                        if (failNode == null)
	                            temp.childNodes[i].faliNode = root;
	                     }
	                    queue.offer(temp.childNodes[i]);
	                }
	            }
	        }
	     
	        
	        
	        public Set<Integer> SearchAC(char[] s)
	        {
	        	Set<Integer> hash = new HashSet<Integer>();
	 
	            SearchAC(trieNode, s, hash);
	
	            return hash;
	        }
	 
	   
	        
	         public void SearchAC(TrieNode root, char[] s, Set<Integer> hashSet)
	        {
	            int freq = 0;
	
	            TrieNode head = root;
	              int i;
	             for(i=0;i<s.length-1;i++);
	             {
	                 //����λ��
	                 int index =s[i]-'a';
	 
	                 //�����ǰƥ����ַ���trie�������ӽڵ㲢�Ҳ���root����Ҫ��ʧ��ָ��
	                //���ݵ�ȥ�����ĵ�ǰ�ڵ���ӽڵ�
	                while ((head.childNodes[index] == null) && (head != root))
	                     head = head.faliNode;
	 
	                 //��ȡ�ò���
	                 head = head.childNodes[index];
	 
	                 //���Ϊ�գ�ֱ�Ӹ�root,��ʾ���ַ��Ѿ��������
	                 if (head == null)
	                     head = root;
	 
	                 TrieNode temp = head;
	 
	                 //��trie����ƥ�䵽���ַ�����ǵ�ǰ�ڵ�Ϊ�ѷ��ʣ�������Ѱ�Ҹýڵ��ʧ�ܽڵ㡣
	                 //ֱ��root�������൱������һ��������(ע�⣺������ǻ����һ��freq=-1��ʧ��ָ����)
	                 while (temp!= root && temp.freq != -1)
	                 {
	                    freq+= temp.freq;
	 
	                    //���ҵ���id׷�ӵ�������
	                    for(Integer item:temp.hashSet)
	                        hashSet.add(item);
	
	                    temp.freq = -1;
	 
	                    temp = temp.faliNode;
	                }
	            }
	           
	             
	        }
	        
	
	         public static void main(String args[])
	                 {
	        	 
	                      Trie trie = new Trie();
	         
	                     trie.AddTrieNode("say".toCharArray(),1);
	                     trie.AddTrieNode("she".toCharArray(),2);
	                     trie.AddTrieNode("shr".toCharArray(),3);
	                     trie.AddTrieNode("her".toCharArray(),4);
	                     trie.AddTrieNode("he".toCharArray(),5);
	         
	                     
	                     Set<Integer> hashSet = new HashSet<Integer>();
	                     trie.BuildFailNodeBFS();
	          
	                      char[] s = "yasherhs".toCharArray();
	                      
	                      
	                   
	                      hashSet= trie.SearchAC(s);
	                      String  tmps=String.valueOf(s);
	                    
	                    
	                     System.out.println("������{0}�д���ģʽ���ı��Ϊ:{1}");
	                     System.out.println(tmps);
	                    // Iterator<Integer> setIntIt =hashSet.iterator();
	                     
	                  //   while(setIntIt.hasNext()){ 
	                    System.out.println(hashSet);
	                   // }
	                     
	                  }
	         
	         
	}
	

