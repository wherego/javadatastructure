package LoserTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * ������,��<b>�������</b>������Դ���й鲢����<br>
 * ԭ��2���ӽ��ȽϺ�İ��ߣ��ϴ�ֵ���������ǵĸ���㣬��ʤ�ߣ���Сֵ���͵����Ǹ����ĸ��ڵ�ȥ�����Ƚ�
 *
 * @author Anthony
 * @param <T>
 */
public class LoserTree<T> {
    /**
     * ����Դ�б�,ΪҶ�ӽڵ��ṩ����,����Դ�����˳��<b>��������</b>
     */
    private Iterator<T>[] datas;
    /**
     * Ҷ�ӽڵ�,�����ʵ�ʵ�����<br>
     * Ҷ�ӽڵ������Դ��һһ��Ӧ��,����һ��Ҷ�ӽڵ��¼��һ������Դ�ĵ�ǰ����
     */
    private Object[] leafs;
    /**
     * ��Ҷ�ӽڵ�,��¼Ҷ�ӽڵ���±�, ���ݽڵ��ֵ���Զ�λ��Ҷ�ӽڵ���ָ�������<br>
     * �����������õ����������
     */
    private int[] nodes;
 
    /**
     * Ҷ�ӽڵ����ݵıȽ϶���
     */
    private Comparator<T> comparator;
 
    /**
     * ���췽��,����Ԫ�ص�Comparable�ӿ�ʵ�ֽ�������
     *
     */
    public LoserTree(List<Iterator<T>> branches) {
        this(branches, new Comparator<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public int compare(T o1, T o2) {
                return ((Comparable<T>) o1).compareTo(o2);
            }
        });
    }
 
    /**
     * ���췽��, ָ������Դ��֧�ĵ�������Ԫ�رȽ϶���<br>
     * �����������������������Comparator����ıȽϽ������һ��
     *
     * @param brs
     * @param comparator
     */
    @SuppressWarnings("unchecked")
    public LoserTree(List<Iterator<T>> brs, Comparator<T> comparator) {
        this.datas = brs.toArray(new Iterator[0]);
        this.comparator = comparator;
        this.init();
    }
 
    /**
     * ��ʼ������������<br>
     */
    private void init() {
        int size = this.datas.length;
        this.leafs = new Object[size];
        this.nodes = new int[size];
 
        // ΪҶ�ӽڵ㸳ֵ
        for (int i = 0; i < size; i++) {
            this.put(i);
        }
 
        // �ҵ�Ҷ�ӽڵ�����Сֵ���±�
        int winner = 0;
        for (int i = 1; i < size; i++) {
            if (loser(i, winner)) {
                winner = i;
            }
        }
        // ��Ҷ�ӽڵ�ȫ����ʼ��Ϊ��Сֵ��Ӧ��Ҷ�ӽڵ��±�
        Arrays.fill(nodes, winner);
 
        // �Ӻ���ǰ���ε�����Ҷ�ӽڵ�
        for (int i = size - 1; i >= 0; i--)
            adjust(i);
    }
 
    /**
     * ���õ�index��Ҷ�ӽڵ����һ������<br>
     * �������Դ�ѽ���,������Ϊnull
     *
     * @param index
     */
    private void put(int index) {
        Iterator<T> branch = this.datas[index];
        this.leafs[index] = branch.hasNext() ? branch.next() : null;
    }
 
    /**
     * ��ȡ��index��Ҷ�ӽڵ�ĵ�ǰ����
     *
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    private T get(int index) {
        return (T) leafs[index];
    }
 
    /**
     * �ж�index1��Ӧ�Ľڵ��Ƿ�<b>С��</b>index2��Ӧ�Ľڵ�
     *
     * @param index1
     * @param index2
     * @return
     */
    private boolean loser(int index1, int index2) {
        T t1 = (T) get(index1);
        T t2 = (T) get(index2);
        if (t1 == null)
            return false;
        if (t2 == null)
            return true;
        // ����, ��Ҷ�ڵ��������ʱ�ȽϷ�֧������Ϊ��ʵ�������㷨���ȶ���
        int n = comparator.compare(t1, t2);
        return n != 0 ? n < 0 : index1 < index2;
    }
 
    /**
     * ������index��Ҷ�ӽڵ�<br>
     * �����������Ϊ: Ҷ�ӽڵ�͸��ڵ�Ƚ�, ���ߣ��ϴ�ֵ�����ڸ��ڵ�λ��, ʤ�ߣ���Сֵ���������游�ڵ�Ƚϣ�ֱ������
     *
     * @param index
     */
    private void adjust(int index) {
        int size = this.datas.length;
        int t = (size + index) / 2; // ���ڵ�
        while (t > 0) {
            // �����ǰֵ�ȸ��ڵ�Ҫ����ô���ڸ��ڵ�λ�ã�����������游�ڵ�Ƚ�
            if (loser(nodes[t], index)) {
                int temp = nodes[t];
                nodes[t] = index;
                index = temp;
            }
            t /= 2;
        }
        nodes[0] = index; // ���ڵ�ʼ��Ϊ��Сֵ
    }
 
    /**
     * ���ζ�ȡ����Դ�����ݽ��й鲢����, ���������������б�<br>
     *
     * @return ��С�����˳��
     */
    public List<T> merge(int bufferSize) {
        List<T> list = new ArrayList<T>();
        T top = null;
        while ((top = get(nodes[0])) != null) {
            list.add(top); // ȡ����Сֵ
            put(nodes[0]); // ������Ӧ�����ݱ�ȡ������Ҫ������Դ���ٶ���һ��
            adjust(nodes[0]); // �����²����Ҷ�ӽڵ����µ�����
            if (list.size() == bufferSize)
                break;
        }
        return list;
    }
}