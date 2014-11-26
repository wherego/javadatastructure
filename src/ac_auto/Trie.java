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
	             
	
          //求字符地址，方便将该字符放入到26叉树中的哪一叉中
	             int k = word[0]-'a';
	             
	 
	             //如果该叉树为空，则初始化
	             if (root.childNodes[k] == null)
	             {
	                 root.childNodes[k] = new TrieNode();
	 
	                //记录下字符
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
	             
	             //说明是最后一个字符，统计该词出现的次数
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
	             //根节点入队
	             queue.offer(root);
	 
	            while (queue.peek() != null)
	             {
	                 //出队
	            	TrieNode temp = queue.poll();
	 
	                 //失败节点
	                TrieNode failNode = null;
	 
	                //26叉树
	                 for (int i = 0; i < 26; i++)
	                {
                    //代码技巧：用BFS方式，从当前节点找其孩子节点，此时孩子节点
	                     //         的父亲正是当前节点，（避免了parent节点的存在）
	                    if (temp.childNodes[i] == null)
	                        continue;
	
	                  //如果当前是根节点，则根节点的失败指针指向root
	                     if (temp == root)
	                    {
	                        temp.childNodes[i].faliNode = root;
	                    }
	                     else
	                    {
	                       //获取出队节点的失败指针
	                        failNode = temp.faliNode;
	
	                         //沿着它父节点的失败指针走，一直要找到一个节点，直到它的儿子也包含该节点。
	                         while (failNode != null)
	                        {
	                             //如果不为空，则在父亲失败节点中往子节点中深入。
	                            if (failNode.childNodes[i] != null)
	                            {
	                                temp.childNodes[i].faliNode = failNode.childNodes[i];
	                                break;
	                            }
	                             //如果无法深入子节点，则退回到父亲失败节点并向root节点往根部延伸，直到null
	                           //（一个回溯再深入的过程，非常有意思）
	                             failNode = failNode.faliNode;
	                        }
	 
	                        //等于null的话，指向root节点
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
	                 //计算位置
	                 int index =s[i]-'a';
	 
	                 //如果当前匹配的字符在trie树中无子节点并且不是root，则要走失败指针
	                //回溯的去找它的当前节点的子节点
	                while ((head.childNodes[index] == null) && (head != root))
	                     head = head.faliNode;
	 
	                 //获取该叉树
	                 head = head.childNodes[index];
	 
	                 //如果为空，直接给root,表示该字符已经走完毕了
	                 if (head == null)
	                     head = root;
	 
	                 TrieNode temp = head;
	 
	                 //在trie树中匹配到了字符，标记当前节点为已访问，并继续寻找该节点的失败节点。
	                 //直到root结束，相当于走了一个回旋。(注意：最后我们会出现一个freq=-1的失败指针链)
	                 while (temp!= root && temp.freq != -1)
	                 {
	                    freq+= temp.freq;
	 
	                    //将找到的id追加到集合中
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
	                    
	                    
	                     System.out.println("在主串{0}中存在模式串的编号为:{1}");
	                     System.out.println(tmps);
	                    // Iterator<Integer> setIntIt =hashSet.iterator();
	                     
	                  //   while(setIntIt.hasNext()){ 
	                    System.out.println(hashSet);
	                   // }
	                     
	                  }
	         
	         
	}
	

