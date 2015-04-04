/**
 * Consider the following tree

1 -> 5, 6, 7 
5 -> 2, 4, 8 
6 -> 3 
7 -> 13, 9 
4 -> 10, 11

If we write down the nodes in dfs order we will get 1, 5, 2, 4, 10, 11, 8, 6, 3, 7, 13, 9

We can see that the nodes of a sub tree always appear contiguously.
So if we can keep track of the start and end point of the range for a subtree 
we can update the ranges in our segment tree.

Let us make use of dfs order property 
Let us maintain three two arrays start and end. 
start[u] will denote the position at which the node u lies in the dfs order.
end[u] will denote the index at which the sub tree rooted at u will end.

In the above example start[5] = 1 end[5] = 6 start[3] = 8 and end[3] = 8

Our segment tree will have n leaf nodes corresponding to the nodes of our tree in dfs order.
Each leaf node will be storing the initial distance from the root. 
All the internal nodes will have a value of zero.
When the value of an edge (u,v) (u being closer to the root) changes by an amount c in the type I query we ask the segment tree to update this value for the whole range (start[v], end[v])
 */
import java.util.*;
import java.io.*;
class AncestorsSum2
{
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
    static int count = 1;
    static int S[]=new int[100001];
    static int E[]=new int[100001];
    static int BIT[]=new int[100001];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        for(int i=0;i<=100000;i++)
            adj.add(new ArrayList<Integer>());
        int N = sc.nextInt();
        while(N-->1)
        {
            int u = sc.nextInt();int v = sc.nextInt();
            adj.get(u).add(v); adj.get(v).add(u);
        }
        dfs(0,-1); // Flatten the tree into an array!
        int Q = sc.nextInt();
        StringBuilder output = new StringBuilder();
        while(Q-->0)
        {
            char x = sc.next().charAt(0);
            if(x=='S')
            {
                int inp = sc.nextInt();
                output.append(query(S[inp])).append("\n");
                // The index where The inp node is present
                // Now just calculate sum when you go up the path from node to root.
            }
            else
            {
                int inp = sc.nextInt();
                int val = sc.nextInt();
                update(S[inp],val); // Range Update and Point Query
                update(E[inp]+1,-val); // Range Update and Point Query
            }
        }
        System.out.println(output);
    }

    static void dfs(int node , int parent)
    {
        S[node]=count++;
        int sz = adj.get(node).size();
        for(int i=0;i<sz;i++)
            if(adj.get(node).get(i)!=parent)
                dfs(adj.get(node).get(i),node);
        E[node]=count-1;        
    }

    static void update(int idx,int val)
    {
        for(;idx<=100000;idx+=idx&-idx)
            BIT[idx]+=val;
    }

    static long query(int idx)
    {
        long ans=0;
        for(; idx>0; idx-=(idx&-idx))
            ans+=BIT[idx];
        return ans;
    }
}
class InputReader1 
{

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader1(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String next() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public String nextLine() {
        int c = read();
        //while (c != '\n' && c != '\r' && c != '\t' && c != -1)
        //c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (c != '\n' && c != '\r' && c != '\t' && c != -1);
        return res.toString();
    }

    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

}
