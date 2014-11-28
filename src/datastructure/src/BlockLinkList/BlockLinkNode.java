package BlockLinkList;

import java.util.ArrayList;



public class BlockLinkNode {
	
	
         
         public BlockLinkNode prev;

         
         public BlockLinkNode next;

        
         public ArrayList<Integer> list;
         
         public BlockLinkNode(BlockLinkNode prev,BlockLinkNode next,ArrayList<Integer> list)
         {
         	
         	this.prev=prev;
         	this.next=next;
         	this.list=list;
         	
         }
         
     }


