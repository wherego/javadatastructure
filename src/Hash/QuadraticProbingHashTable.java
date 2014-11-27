package Hash;

public class QuadraticProbingHashTable<T> {
    private static class HashEntry<T>{
        public T element;
        public boolean isActive;

        public HashEntry(T e){
            this(e,true);
        }

        public HashEntry(T e,boolean i){
            element = e;
            isActive = i;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 11;

    private HashEntry<T>[] array;

    private int size;

    public QuadraticProbingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size){
        allocateArray(size);
        makeEmpty();
    }

    public void makeEmpty(){
        size = 0;
        for(int i = 0;i < array.length;i++){
            array[i] = null;
        }
    }

    public boolean contains(T x){
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    public void insert(T x){
        int currentPos = findPos(x);
        if(isActive(currentPos))
            return;
        array[currentPos] = new HashEntry<T>(x,true);
        if( ++size  > array.length/2)
            rehash();
    }

    public void remove(T x){
        int currentPos = findPos(x);
        if(isActive(currentPos)){
            array[currentPos].isActive = false;
            size--;
        }
    }

    private void allocateArray(int arraySize){
        array = new HashEntry[arraySize];
    }

    private boolean isActive(int currentPos){
        return array[currentPos] != null  && array[currentPos].isActive;
    }

    private int findPos(T x){
        int offset = 1;
        int currentPos = myhash(x);
        while (array[currentPos] != null && !array[currentPos].element.equals(x)){
            currentPos += offset;
            offset += 2;
            if(currentPos >= array.length)
                currentPos -= array.length;
        }
        return currentPos;
    }

    private void rehash(){
        HashEntry<T>[] oldArrey = array;
        allocateArray(nextPrime(array.length*2));
        size = 0;
        for(int i = 0;i < oldArrey.length;i++)
            if(oldArrey[i] != null && oldArrey[i].isActive)
                insert(oldArrey[i].element);
    }

    private int myhash(T x){
        int hashVal = x.hashCode();
        hashVal %= array.length;
        if(hashVal < 0)
            hashVal += array.length;
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