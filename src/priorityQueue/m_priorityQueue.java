package priorityQueue;
import java.util.*;

class m_priorityQueue extends PriorityQueue<m_priorityQueue>   
implements Comparable<m_priorityQueue>      
{  
private char primary;  
private int secondary;  
private String item;  

public m_priorityQueue(String td,char pri,int sec){  
item = td;  
primary = pri;  
secondary = sec;  
}  
//for comparable  
public int compareTo(m_priorityQueue arg) {  
if(primary > arg.primary)  
return +1;  
if(primary == arg.primary)  
if(secondary > arg.secondary)  
return +1;  
else if(secondary == arg.secondary)  
return 0;  
return -1;  
}  
public String toString()  
{  
return Character.toString(primary)+ secondary + ": " + item;  
}   
public void add(String td,char pri,int sec)  
{  
super.add(new m_priorityQueue(td,pri,sec));  
}  
public void run()  
{  
add("sixth",'C',4);  
add("second",'A',2);  
add("fourth",'B',7);  
add("fifth",'C',3);  
add("first",'A',1);  
add("third",'B',1);  
while(!isEmpty())  
System.out.println(remove());  
}  

public static void main(String[] args) {  
    // TODO Auto-generated method stub   
      
    m_priorityQueue pqueue=new m_priorityQueue(null, '0', 0);  
    pqueue.run();  

}  

}  
