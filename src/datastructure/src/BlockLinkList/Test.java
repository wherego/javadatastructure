package BlockLinkList;
import java.util.*;
public class Test {
	
	
	
	         public static void main(String[] args)
	          {
	              ArrayList<Integer> list = new ArrayList<Integer>();
	              list.add(8959);
	              list.add(30290);
	              list.add(18854);
	              list.add(7418);
	              list.add(28749);
	              list.add(17313);
	              list.add(5877);
	              list.add(27208);
	              list.add(15771);
	              list.add(4335);
	            	
	  
	             
	              ArrayList<Integer> tmplist = new ArrayList<Integer>();
	              
	              tmplist.add(8959);
	              tmplist.add(30290);
	              tmplist.add(18854);
	              tmplist.add(7418);
	              tmplist.add(28749);
	              tmplist.add(17313);
	              tmplist.add(5877);
	              tmplist.add(27208);
	              tmplist.add(15771);
	              tmplist.add(4335);
	              tmplist.add(7418);
	              tmplist.add(28749);
	              tmplist.add(17313);
	              tmplist.add(5877);
	              tmplist.add(27208);
	              tmplist.add(15771);
	              tmplist.add(4335);
	              
	  
	              BlockLinkList blockList = new BlockLinkList();
	              blockList.blockLinkNode=new BlockLinkNode(null,null,list);
	              
	              for(int i=0;i<=tmplist.size()-1;i++)
	              {
	                  blockList.Add(tmplist.get(i));
	              }
	  
	              //var b = blockList.IsExist(333);
	              //blockList.GetCount();
	  
	              System.out.println(blockList.Get(27208));
	  
	  
	             
	              //for (int i = 0; i < 5000; i++)
	              //{
	              //    var rand = new Random((int)DateTime.Now.Ticks).Next(0, list.Count);
	  
	              //    System.Threading.Thread.Sleep(2);
	  
	              //    Console.WriteLine("\n**************************************\n当前要删除元素：{0}", list[rand]);
	  
	              //    blockList.Remove(list[rand]);
	  
	              //    Console.WriteLine("\n\n");
	  
	              //    if (blockList.GetCount() == 0)
	              //    {
	              //        Console.Read();
	              //        return;
	              //    }
	              //} 
	              
	          }
	      }


