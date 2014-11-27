package Hash;

import java.util.LinkedList;
import java.util.List;


public class SeparateChainingHashTable<T> {

    private static final int DEFAULT_TABLE_SIZE = 101;

    private List<T> [] list;

    private int size;

    public SeparateChainingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size){
        list = new LinkedList[nextPrime(size)];
        for (int i=0;i<list.length;i++)
            list[i] = new LinkedList<T>();
    }

    public void insert(T x){
        List<T> theList = list[myhash(x)];
        if(!theList.contains(x)){
            theList.add(x);
            if (++size > list.length)
                rehash();
        }
    }

    public void remove(T x){
        List<T> theList = list[myhash(x)];
        if(theList.contains(x)){
            theList.remove(x);
            size--;
        }
    }

    public boolean contains(T x){
        List<T> theList = list[myhash(x)];
        return theList.contains(x);
    }

    public void makeEmpty(){
        for (int i=0;i<list.length;i++)
            list[i].clear();
        size = 0;
    }

    private void rehash(){
        List<T>[] oldList = list;
        list = new List[nextPrime(2*list.length)];
        for (int j=0;j<list .length;j++)
            list[j] = new LinkedList<T>();
        size = 0;
        for (int i=0;i<oldList.length;i++)
            for (T item : oldList[i])
                insert(item);
    }

    private int myhash(T x){
        int hashVal = x.hashCode();
        hashVal %= list.length;
        if(hashVal < 0)
            hashVal += list.length;
        return hashVal;
    }

    private static int nextPrime(int n){
        int m = 0;
        while (!isPrime(n)){
            n++;
        }
        return n;
    }

    private static boolean isPrime(int n){
        int m = (int)Math.sqrt(n);
        for (int i=2;i<=m;i++){
            if(n % m == 0)
                return false;
        }
        return true;
    }
}