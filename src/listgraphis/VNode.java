package listgraphis;
import listgraphis.Edge;
public class VNode {
	
	int from;//边的起点  
	Edge first;//以from为起点的第一条边  
	public VNode(int from){  
	this.from=from;  
	first=null; 
	}
}
