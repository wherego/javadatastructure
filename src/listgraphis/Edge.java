package listgraphis;

public class Edge {
	
	int to;//边的终点    
	Edge next;//具有同一起点的下一条边  
	public Edge(int to){  
	this.to=to;  
	next=null;  
	}
}
