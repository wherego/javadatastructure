package unionFindSet;
import unionFindSet.UnionFindSet;

public class test {
	
	 public static void main (String[] args) throws java.lang.Exception
	    {
	        UnionFindSet ufs = new UnionFindSet(10);
	        ufs.union(1, 2);
	        ufs.union(3, 4);
	        ufs.union(5, 6);
	        ufs.union(3, 6);
	        ufs.union(7, 9);
	        System.out.println(ufs.count());
	        System.out.println(ufs.find(6));
	        System.out.println(ufs.connected(4, 5));
	        System.out.println(ufs.connected(8, 9));
	    }

}
