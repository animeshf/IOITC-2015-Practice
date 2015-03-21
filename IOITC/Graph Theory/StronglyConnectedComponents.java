
/*
 * Web Islands - SPOJ 
 * Author - Animesh Fatehpuria
 * Kosaraju's Algorithm for SCC
 */
import java.util.*;
import java.io.*;
class StronglyConnectedComponents
{
    static ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
    // Original Adjacency List ^
    static ArrayList<ArrayList<Integer>> t = new ArrayList<ArrayList<Integer>>();
    // Transpose Graph^
    static int st[]=new int[1000005]; // Stores the order in which vertices should be processed for SCC
    // Last element consists of node which has maximum finishing time 
    static int c=0;
    static int vis[]=new int[1000005]; // Visited Array
    static int ans[]=new int[1000005]; // Store answer for each ID
    static int temp[]=new int[1000005];static int co=0;
    static int min=100100101; // INF val (arbitrary)
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        for(int i=0;i<=N;i++)
        {
            a.add(new ArrayList<Integer>()); // Initializing
            t.add(new ArrayList<Integer>()); // Initializing
        }
        for(int i=0;i<M;i++)
        {
            int from = sc.nextInt();
            int to = sc.nextInt();
            a.get(from).add(to);
            t.get(to).add(from);
        }
        for(int i=0;i<N;i++)
        {
            if(vis[i]==1)continue;
            dfs(i); // Doing DFS on Normal Graph
        }
        
        for(int i=0;i<N;i++)vis[i]=0;
        
        for(int i=c-1;i>=0;i--)
        {
           if(vis[st[i]]==1)continue;
           dfs2(st[i]); // Find each component of st[i]
           for(int j=co-1;j>=0;j--)
           {
             ans[temp[j]]=min;  // Each ID in the component is stored in temp
             // Now we can store answer for each ID in ans
             // Answer for each ID in a particular component is the Minimum ID in that component
           }
           co=0; //Reset counter to zero
           min=1001010101;//Reset min to INF
        }
        StringBuilder op = new StringBuilder();
        for(int i=0;i<N;i++)
        op.append(ans[i]).append("\n");
        System.out.println(op);
    }
    static void dfs(int x)
    {
        if(vis[x]==1)
        return;
        vis[x]=1;
        int sz=a.get(x).size();
        for(int i=0;i<sz;i++)
            dfs(a.get(x).get(i)); 
        st[c++]=x;  // Hence stack stores the vertices in increasing order of finishing time
        // When we traverse from c to zero we traverse in decreasing order of finishing time
    }
    static void dfs2(int x)
    {
        if(vis[x]==1)
        return;
        vis[x]=1; 
        min=Math.min(x,min); // Stores the minimum ID in each component
        int sz=t.get(x).size();
        for(int i=0;i<sz;i++)
            dfs2(t.get(x).get(i)); // DFS call
        temp[co++]=x;    // Stores the IDs present in each component , Order doesnt matter
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

