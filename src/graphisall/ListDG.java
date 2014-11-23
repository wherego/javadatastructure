package graphisall;
/**
* Java: �޻�·����ͼ(Directed Acyclic Graph)����������
* ��DAGͼ��ͨ���ڽӱ�ʵ�ֵġ�
*
* @author judyge
* @date 2014/11/22
*/

import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
public class ListDG {
	
	// �ڽӱ��б��Ӧ������Ķ���
	private class ENode {
	int ivex; // �ñ���ָ��Ķ����λ��
	ENode nextEdge; // ָ����һ������ָ��
	}
	// �ڽӱ��б�Ķ���
	private class VNode {
	char data; // ������Ϣ
	ENode firstEdge; // ָ���һ�������ö���Ļ�
	};
	private List<VNode> mVexs; // ��������
	/*
	* ����ͼ(�Լ���������)
	*/
	public ListDG() {
	// ����"������"��"����"
	System.out.printf("input vertex number: ");
	int vlen = readInt();
	System.out.printf("input edge number: ");
	int elen = readInt();
	if ( vlen < 1 || elen < 1 || (elen > (vlen*(vlen - 1)))) {
	System.out.printf("input error: invalid parameters!\n");
	return ;
	}
	// ��ʼ��"����"
	mVexs = new ArrayList<VNode>();
	for (int i = 0; i < vlen; i++) {
	System.out.printf("vertex(%d): ", i);
	// �½�VNode
	VNode vnode = new VNode();
	vnode.data = readChar();
	vnode.firstEdge = null;
	// ��vnode��ӵ�����mVexs��
	mVexs.add(vnode);
	}
	// ��ʼ��"��"
	//mMatrix = new int[vlen][vlen];
	for (int i = 0; i < elen; i++) {
	// ��ȡ�ߵ���ʼ����ͽ�������
	System.out.printf("edge(%d):", i);
	char c1 = readChar();
	char c2 = readChar();
	int p1 = getPosition(c1);
	int p2 = getPosition(c2);
	// ��ʼ��node1
	ENode node1 = new ENode();
	node1.ivex = p2;
	// ��node1���ӵ�"p1���������ĩβ"
	if(mVexs.get(p1).firstEdge == null)
	mVexs.get(p1).firstEdge = node1;
	else
	linkLast(mVexs.get(p1).firstEdge, node1);
	}
	}
	/*
	* ����ͼ(�����ṩ�ľ���)
	*
	* ����˵����
	* vexs -- ��������
	* edges -- ������
	*/
	public ListDG(char[] vexs, char[][] edges) {
	// ��ʼ��"������"��"����"
	int vlen = vexs.length;
	int elen = edges.length;
	// ��ʼ��"����"
	mVexs = new ArrayList<VNode>();
	for (int i = 0; i < vlen; i++) {
	// �½�VNode
	VNode vnode = new VNode();
	vnode.data = vexs[i];
	vnode.firstEdge = null;
	// ��vnode��ӵ�����mVexs��
	mVexs.add(vnode);
	}
	// ��ʼ��"��"
	for (int i = 0; i < elen; i++) {
	// ��ȡ�ߵ���ʼ����ͽ�������
	char c1 = edges[i][0];
	char c2 = edges[i][1];
	// ��ȡ�ߵ���ʼ����ͽ�������
	int p1 = getPosition(edges[i][0]);
	int p2 = getPosition(edges[i][1]);
	// ��ʼ��node1
	ENode node1 = new ENode();
	node1.ivex = p2;
	// ��node1���ӵ�"p1���������ĩβ"
	if(mVexs.get(p1).firstEdge == null)
	mVexs.get(p1).firstEdge = node1;
	else
	linkLast(mVexs.get(p1).firstEdge, node1);
	}
	}
	/*
	* ��node�ڵ����ӵ�list�����
	*/
	private void linkLast(ENode list, ENode node) {
	ENode p = list;
	while(p.nextEdge!=null)
	p = p.nextEdge;
	p.nextEdge = node;
	}
	/*
	* ����chλ��
	*/
	private int getPosition(char ch) {
	for(int i=0; i<mVexs.size(); i++)
	if(mVexs.get(i).data==ch)
	return i;
	return -1;
	}
	/*
	* ��ȡһ�������ַ�
	*/
	private char readChar() {
	char ch='0';
	do {
	try {
	ch = (char)System.in.read();
	} catch (IOException e) {
	e.printStackTrace();
	}
	} while(!((ch>='a'&&ch<='z') || (ch>='A'&&ch<='Z')));
	return ch;
	}
	/*
	* ��ȡһ�������ַ�
	*/
	private int readInt() {
	Scanner scanner = new Scanner(System.in);
	return scanner.nextInt();
	}
	/*
	* ���������������ͼ�ĵݹ�ʵ��
	*/
	private void DFS(int i, boolean[] visited) {
	ENode node;
	visited[i] = true;
	System.out.printf("%c ", mVexs.get(i).data);
	node = mVexs.get(i).firstEdge;
	while (node != null) {
	if (!visited[node.ivex])
	DFS(node.ivex, visited);
	node = node.nextEdge;
	}
	}
	/*
	* ���������������ͼ
	*/
	public void DFS() {
	boolean[] visited = new boolean[mVexs.size()]; // ������ʱ��
	// ��ʼ�����ж��㶼û�б�����
	for (int i = 0; i < mVexs.size(); i++)
	visited[i] = false;
	System.out.printf("== DFS: ");
	for (int i = 0; i < mVexs.size(); i++) {
	if (!visited[i])
	DFS(i, visited);
	}
	System.out.printf("\n");
	}
	/*
	* ����������������������Ĳ�α�����
	*/
	public void BFS() {
	int head = 0;
	int rear = 0;
	int[] queue = new int[mVexs.size()]; // �������
	boolean[] visited = new boolean[mVexs.size()]; // ������ʱ��
	for (int i = 0; i < mVexs.size(); i++)
	visited[i] = false;
	System.out.printf("== BFS: ");
	for (int i = 0; i < mVexs.size(); i++) {
	if (!visited[i]) {
	visited[i] = true;
	System.out.printf("%c ", mVexs.get(i).data);
	queue[rear++] = i; // �����
	}
	while (head != rear) {
	int j = queue[head++]; // ������
	ENode node = mVexs.get(j).firstEdge;
	while (node != null) {
	int k = node.ivex;
	if (!visited[k])
	{
	visited[k] = true;
	System.out.printf("%c ", mVexs.get(k).data);
	queue[rear++] = k;
	}
	node = node.nextEdge;
	}
	}
	}
	System.out.printf("\n");
	}
	/*
	* ��ӡ�������ͼ
	*/
	public void print() {
	System.out.printf("== List Graph:\n");
	for (int i = 0; i < mVexs.size(); i++) {
	System.out.printf("%d(%c): ", i, mVexs.get(i).data);
	ENode node = mVexs.get(i).firstEdge;
	while (node != null) {
	System.out.printf("%d(%c) ", node.ivex, mVexs.get(node.ivex).data);
	node = node.nextEdge;
	}
	System.out.printf("\n");
	}
	}
	/*
	* ��������
	*
	* ����ֵ��
	* -1 -- ʧ��(�����ڴ治���ԭ����)
	* 0 -- �ɹ����򣬲�������
	* 1 -- ʧ��(������ͼ���л���)
	*/
	public int topologicalSort() {
	int index = 0;
	int num = mVexs.size();
	int[] ins; // �������
	char[] tops; // �������������飬��¼ÿ���ڵ����������š�
	Queue<Integer> queue; // �������
	ins = new int[num];
	tops = new char[num];
	queue = new LinkedList<Integer>();
	// ͳ��ÿ������������
	for(int i = 0; i < num; i++) {
	ENode node = mVexs.get(i).firstEdge;
	while (node != null) {
	ins[node.ivex]++;
	node = node.nextEdge;
	}
	}
	// ���������Ϊ0�Ķ��������
	for(int i = 0; i < num; i ++)
	if(ins[i] == 0)
	queue.offer(i); // �����
	while (!queue.isEmpty()) { // ���зǿ�
	int j = queue.poll().intValue(); // �����С�j�Ƕ�������
	tops[index++] = mVexs.get(j).data; // ���ö�����ӵ�tops�У�tops��������
	ENode node = mVexs.get(j).firstEdge;// ��ȡ�Ըö���Ϊ���ĳ��߶���
	// ����"node"�����Ľڵ����ȼ�1��
	// ����1֮�󣬸ýڵ�����Ϊ0���򽫸ýڵ���ӵ������С�
	while(node != null) {
	// ���ڵ�(���Ϊnode.ivex)����ȼ�1��
	ins[node.ivex]--;
	// ���ڵ�����Ϊ0������"�����"
	if( ins[node.ivex] == 0)
	queue.offer(node.ivex); // �����
	node = node.nextEdge;
	}
	}
	if(index != num) {
	System.out.printf("Graph has a cycle\n");
	return 1;
	}
	// ��ӡ����������
	System.out.printf("== TopSort: ");
	for(int i = 0; i < num; i ++)
	System.out.printf("%c ", tops[i]);
	System.out.printf("\n");
	return 0;
	}
	public static void main(String[] args) {
	char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
	char[][] edges = new char[][]{
	{'A', 'G'},
	{'B', 'A'},
	{'B', 'D'},
	{'C', 'F'},
	{'C', 'G'},
	{'D', 'E'},
	{'D', 'F'}};
	ListDG pG;
	// �Զ���"ͼ"(����������)
	//pG = new ListDG();
	// �������е�"ͼ"
	pG = new ListDG(vexs, edges);
	pG.print(); // ��ӡͼ
	//pG.DFS(); // ������ȱ���
	//pG.BFS(); // ������ȱ���
	pG.topologicalSort(); // ��������
	}

}
