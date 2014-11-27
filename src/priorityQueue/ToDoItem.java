package priorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;


public class ToDoItem implements Comparable<ToDoItem>{
    private char primary;
    private int secondary;
    private String item;
    
    public ToDoItem(char primary,int secondary,String item)
    {
        super();
        this.primary = primary;
        this.secondary = secondary;
        this.item = item;
    }

    @Override
    public int compareTo(ToDoItem o) {
        // TODO Auto-generated method stub
        
        if(this.primary > o.primary)
            return 1;
        if(this.primary == o.primary)
        {
            if(this.secondary > o.secondary)
                return 1;
            else if(this.secondary == o.secondary)
                return 0;
        }
        return -1;
    }
    
     public String toString() {  
            return Character.toString(primary) + this.secondary + " : " + this.item;  
        }
     
     public static void main(String [] args)
     {
         Queue<ToDoItem> q = new PriorityQueue<ToDoItem>();
         q.add(new ToDoItem('C', 4, "Empty trash"));
            q.add(new ToDoItem('A', 2, "Feed dog"));
            q.add(new ToDoItem('B', 7, "Feed bird"));
            q.add(new ToDoItem('C', 3, "Mow lawn"));
            q.add(new ToDoItem('A', 1, "Water lawn"));
            q.add(new ToDoItem('B', 1, "Feed cat"));
            
            while (!q.isEmpty()) {
                System.out.println(q.remove());
            }
            
            Queue<ToDoItem> q1 = new PriorityQueue<ToDoItem>(1,
                    Collections.reverseOrder());
            q1.add(new ToDoItem('C', 4, "Empty trash"));
            q1.add(new ToDoItem('A', 2, "Feed dog"));
            q1.add(new ToDoItem('B', 7, "Feed bird"));
            q1.add(new ToDoItem('C', 3, "Mow lawn"));
            q1.add(new ToDoItem('A', 1, "Water lawn"));
            q1.add(new ToDoItem('B', 1, "Feed cat"));

            while (!q1.isEmpty()) {
                System.out.println(q1.remove());
            }
            
     }
     
     

}
