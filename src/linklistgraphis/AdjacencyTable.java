package linklistgraphis;
/**
 * �ļ�����AdjacencyTable.java
 * ���ڰ���Graph
 * ���ڣ�2014-1-2 ����9:54:32
 * �汾��Ϣ��version V1.0
 * Copyright Corporation 2014
 * ��Ȩ����: 
 *
 */ 
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
 
/**
 *
 * ��Ŀ���ƣ�judyblog
 * �����ƣ�AdjacencyTable
 * ���������ڽӱ� ���� + ͷ�ڵ� (����� + �߱�)
 * �����ˣ�judyge
 * ����ʱ�䣺2014-11-12
 * �޸��ˣ�
 * �޸�ʱ�䣺
 * �޸ı�ע��
 * @version
 */

public class AdjacencyTable {
	
	
	/** �ڽӱ� (�����)   */   
    private List<Vexnode> list;
    /** ������   */   
    private int n;
    /** ����   */   
    private int e;
     
    /**  ���������ͼ��������ͼ  flag=true ������ͼ*/   
    private boolean flag;
    /**  ��ʶ�Ƿ����  */   
    private boolean[] visited;
    public AdjacencyTable(int n, boolean flag) {
        this.n = n;
        this.flag = flag;
        //����ͼ
        if(flag){
            this.e = n * (n - 1 ) / 2;
        }else{
            this.e = n * (n - 1);
        }
        visited = new boolean[n];
        Arrays.fill(visited, false);
    }
 
    /**
     *
     * ��Ŀ���ƣ�jobdu
     * �����ƣ�Arcnode
     * ������������
     * �����ˣ��ƴ���
     * ����ʱ�䣺2014-1-2 ����9:55:44
     * �޸��ˣ�
     * �޸�ʱ�䣺
     * �޸ı�ע��
     * @version
     */
    private final class Arcnode{
        private int adjvex; //�ڽӵ���,ָʾ�뼸��vi�ڽӵĵ���ͼ�еı��
        private Arcnode nextarc; // ָʾ��һ���߻���Ľڵ�
        private Object info; //������Ϣ
    }
     
    /**
     *
     * ��Ŀ���ƣ�jobdu
     * �����ƣ�Vexnode
     * ��������ͷ�ڵ���
     * �����ˣ��ƴ���
     * ����ʱ�䣺2014-1-2 ����9:58:52
     * �޸��ˣ�
     * �޸�ʱ�䣺
     * �޸ı�ע��
     * @version
     */
    private final class Vexnode{
         
        //�Ͷ����йص���Ϣ
        private int vexData;
        /**  ��һ���ڽӵ�  */   
        private Arcnode firstArc;
 
    }
     
    /**
     * �������ƣ�createAdjacencyTable()
     * ���������������ڽӱ�
     * @param  flag: ָʾ��������ͼ��������ͼ���ڽӱ� true Ϊ ����ͼ
     * @return String    
     * @throws Exception 
     * @Exception 
     */
    public void createAdjacencyTable() throws Exception{
        list = new ArrayList<Vexnode>();
        Scanner scanner = new Scanner(System.in);
        Vexnode node = null;
        //���������
        System.out.println("�����붥��ֵ�� **************");
        for(int i=0;i<n;i++){
            node = new Vexnode();
            node.vexData = scanner.nextInt();
            node.firstArc = null;
            list.add(node);
        }
         
        System.out.println("������ߣ� ******************");
        //�߱�ڵ�
        Arcnode arcNode = null;
        //�����߱�
        for(int i=0;i<e;i++){
            //����һ����
            int begin = scanner.nextInt() ;
            int end = scanner.nextInt();
            if(begin==0 && end==0){
                System.out.println(" ��������***");
                break;//�������
            }
            //����ߵ�����������Ŵ����б���
            if(begin > list.size() || end > list.size()){
                throw new Exception("begin �� end �±�Խ��");
            }
            //��beginΪͷ��endΪβ,�����б��0��ʼ�򶼼�һ
            arcNode = new Arcnode();
            arcNode.adjvex = end;
            arcNode.nextarc = null;
            insertNode(arcNode, begin-1);
            //��������ͼ
            if(flag){
                arcNode = new Arcnode();
                arcNode.adjvex = begin ;
                arcNode.nextarc = null;
                insertNode(arcNode, end-1);
            }
             
        }
    }
 
 
    /**
     * �������ƣ�insertNode()
     * �������� �� ����ڵ�
     * @param  
     * @return String    
     * @Exception 
     */
    private void insertNode(Arcnode arcNode, int begin) {
        //���Ҷ�����������Ϊbegin�ĵ�һ������Ϊ��
        if(list.get(begin).firstArc == null){
            list.get(begin).firstArc = arcNode;
        }else{
            //�����Ϊ�գ�����Һ����б��ҵ����һ���������
            Arcnode temp = new Arcnode();
            temp = list.get(begin).firstArc;
            while(temp.nextarc != null){
                temp = temp.nextarc;
            }
            temp.nextarc = arcNode;
        }
    }
 
    /**
     * �������ƣ�dfs()
     * ����������
     * @param  begin:��ʼ�ڵ�
     * @return String    
     * @Exception 
     */
     
    public void dfs(int begin){
        //���ڷ��ʵ�
//      System.out.println("���ڷ��ʵ�  " + begin + " ���ڵ�");
        System.out.print(list.get(begin).vexData + " -> ");
        visited[begin] = true;//��ʶ������
        Arcnode temp = list.get(begin).firstArc;
        while(temp != null){
            //δ������
            if(visited[temp.adjvex - 1] == false){
                dfs(temp.adjvex - 1);//����
            }
            temp = temp.nextarc;
        }
    }
     
    /**
     * �������ƣ�dfsRecuive()
     * ����������������������ݹ����
     * @param  
     * @return String    
     * @Exception 
     */
    public void dfsRecuive(int begin){
        Arrays.fill(visited, false);
         
        //�趨ջ�����ڴ洢�ڵ����
        Stack<Integer> stack = new Stack<Integer>();
        stack.clear();
        stack.add(begin);
        visited[begin] = true;
        //ջ�ǿ�
        while(!stack.isEmpty()){
            //��ջ,������
            int temp = stack.pop();
            System.out.print((temp + 1) + " -> ");
             
             
            //����δ���ʵ��ڽӵ�ѹ��ջ
            Arcnode node = list.get(temp).firstArc;
            while(node != null ){
                if(visited[node.adjvex - 1] == false){
                    stack.add(node.adjvex - 1);
                    visited[node.adjvex - 1] = true;
                }
                node = node.nextarc;
            }
//          visited[begin] = true;
        }
    }
    /**
     * �������ƣ�bfs()
     * �������������������������Ҫʹ�ö��и���
     * @param  
     * @return String    
     * @Exception 
     */
    public void bfs(int begin){
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<Integer>();
        System.out.print((begin + 1) + " -> ");
        visited[begin] = true;
        queue.clear();
        //��������ż��뵽������
        queue.add(begin);
        //���зǿ�
        while(!queue.isEmpty()){
            //��ͷԪ�س�����
            int temp = queue.poll();
            //��ȡ��ͷԪ�صĵ�һ���ڽӵ�
            Arcnode node = list.get(temp).firstArc;
            while(node != null){
                //�ڵ�δ������
                if(visited[node.adjvex - 1] == false){
                    //��ʼ����
                    System.out.print(node.adjvex + " -> ");
                    visited[node.adjvex - 1] = true;
                    //�ڵ�Ķ�����Ϣ��ջ
                    queue.add(node.adjvex - 1);
                }
                node = node.nextarc;
            }
        }
         
    }
     
    /**
     * �������ƣ�search()
     * ����������������ͨ������ǿ��ͨͼ
     * @param  
     * @return String    
     * @Exception 
     */
    public void searchConnectedComponent(){
        //��ʶ�Ƿ�����ͨͼ,Ĭ����
        System.out.println("     ***������ͨͼ��ʼ******");
        boolean flag = true;
        for(int i=0;i<n;i++){
            //�������û�б��������Ըýڵ�Ϊ��ڲ�ѯ
            if(visited[i] == false){
                flag = false;
                System.out.println("    *******��ѯ��ͨ���� ��ڽڵ�Ϊ�� " + (i+1) + "**********");
                dfs(i);
                //bfs(i);
                System.out.println();
                System.out.println("    ********�� " + (i+1) + " Ϊ��ڵ���ͨ������ѯ���� ********");
            }
        }
         
        if(flag){
            System.out.println("     ��Ϊ��ͨͼ����ͨ����Ϊ�䱾��");
        }
         
        System.out.println("     ***������ͨͼ����******");
    }
    public static void main(String[] args) throws Exception{
        AdjacencyTable table = new AdjacencyTable(8 , true);
        table.createAdjacencyTable();
        /*System.out.println("The first adjvex is : " + table.list.get(0).firstArc.adjvex);
        System.out.println("��ȶ����������ݹ鷽ʽ�� **************");
        table.dfs(0);
        System.out.println();
        System.out.println("��ȶ����������ǵݹ鷽ʽ�� **************");
        table.dfsRecuive(0);
        System.out.println();
        System.out.println("������������� **************");*/
/*      for(boolean b : table.visited){
            System.out.println(b);
        }
*/     
//      table.bfs(0);
//      System.out.println();
        table.searchConnectedComponent();
         
 
    }
 
    public List<Vexnode> getList() {
        return list;
    }
 
 
    public void setList(List<Vexnode> list) {
        this.list = list;
    }
 
 
    public int getN() {
        return n;
    }
 
    public void setN(int n) {
        this.n = n;
    }
 
    public int getE() {
        return e;
    }
 
    public void setE(int e) {
        this.e = e;
    }
	

}
