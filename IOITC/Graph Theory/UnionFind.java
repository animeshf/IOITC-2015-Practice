/*
 * Super Fast Implementation of the Union - Find algorithm
 * 
 */
import java.util.*;
class UnionFind
{
    static int[] id;
    static int[]sz;
    public static void main(String[]args)
    {
        Scanner sc = new Scanner(System.in);
        int N = 10; // Arbitrary
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) 
        {
            id[i] = i;
            sz[i]=1;
        }
        union(0,1);
        union(2,3);
        union(4,5);
        union(6,7);
        union(8,9);
        union(1,9);
        union(3,5);
        union(5,7);
        // Hence 0 1 8 9 and 2 3 4 5 6 7 are the two components
        System.out.println(connected(1,0)); // true 
        System.out.println(connected(2,7)); // true
        System.out.println(connected(2,1)); // false
    }

    static int root(int i)
    {
        if(id[i]==i)
        return i;
        else
        return id[i]=root(id[i]); // Path compression
    }

    static boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    static void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if (i == j)
            return;
        if (sz[i] < sz[j])  // Quick Union Function 
        // Reduces Union to LOG N Worst case and Flattens the Tree
        { 
            id[i] = j; 
            sz[j] += sz[i]; 
        }
        else 
        { 
            id[j] = i; 
            sz[i] += sz[j];
        } 
    }
}
