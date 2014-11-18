package unionFindSet;

public class UnionFindSet {
	
	private int[] set;
    private int[] size;
    private int count;
     
    public UnionFindSet(int n)
    {
        count = n;
        set = new int[n];
        size = new int[n];
        for (int i=0; i<n; ++i)
        {
            set[i] = i;
            size[i] = 1;
        }
    }
     
    public void union(int p, int q)
    {
        int x = find(p);
        int y = find(q);
        if (x == y)
        {
            return;
        }
        if (size[x] < size[y])
        {
            set[x] = y;
            size[y] += size[x];
        }
        else
        {
            set[y] = x;
            size[x] += size[y];
        }
        count--;
    }
    public int find(int p)
    {
        while (p != set[p])
        {
            p = set[p];
        }
        return p;
        /*// path compression
         if (p != set[p])
         {
             set[p] = find(set[p]);
         }
         return set[p];
         */
    }
    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }
    public int count()
    {
        return count;
    }

}
