
/*
 There can be many ways to go from s to d, we only need the one which minimizes the maximum weight edge, so we first find
 an MST of the graph. Any path from s to d will go through their Lowest Common Ancestor c, so we need the maximum weight edges
 from s to c and c to d. So we create two tables P and C of size |V|*log|V| and the i,jth entry of the table P stores the 2^jth anscestor
 of i and the corresponding entry of table C stores the maximum weight edge encountered from i to its ancestor. As any number can be
 written as an addition of the powers of two, we can answer the queries quite efficiently in O(log|V|) time per query.
*/
import java.util.*;
import java.io.*;
class PetrolTank
{ 
    static int parent[]=new int[100001]; // For Union Find
    static int sz[]=new int[100001]; // Union by Size
    static int P[][]=new int[100001][20]; // P[i][j] = 2^jth ancestor of node 'i'
    static int C[][]=new int[100001][20]; // C[i][j] = Max weight edge from node 'i' to 2^jth ancestor of 'i'
    static int L[]=new int[100001]; // Level of Node 'i'
    static int LN=20; // Log N 
    static int N; // Max N = 100000
    static ArrayList<Edge> edges = new ArrayList<Edge>();
    static ArrayList<ArrayList<pair>> mst = new ArrayList<ArrayList<pair>>();
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        int M = sc.nextInt();
        for(int i=0;i<=N;i++)
        {
            parent[i]=i;
            sz[i]=1;
            mst.add(new ArrayList<pair>());
        }
        for(int i=0;i<M;i++)
        {
            int s = sc.nextInt(); int d = sc.nextInt(); int w = sc.nextInt();
            edges.add(new Edge(s,d,w));
        }
        Collections.sort(edges);
        for(int i=0;i<M;i++)
        {
            int u = root(edges.get(i).from);
            int v = root(edges.get(i).to);
            int cost=edges.get(i).weight;
            if(u!=v)
            {
                mst.get(u).add(new pair(v,cost));
                mst.get(v).add(new pair(u,cost));
                union(u,v);
            }
        }
        int Q = sc.nextInt();
        dfs(1,0,0,0);
        preprocess();
        StringBuilder answer = new StringBuilder();
        while(Q-->0)
        {
            int source=sc.nextInt();
            int destination=sc.nextInt();
            int ans= query(source,destination);
            answer.append(ans).append("\n");
        }
        System.out.println(answer);
    }

    static void dfs(int node , int parent , int level , int weigh)
    {
        L[node]=level;
        P[node][0]=parent;
        C[node][0]=weigh;
        int size = mst.get(node).size();
        for(int i=0;i<size;i++)
        {
            pair o = mst.get(node).get(i);
            int go=o.dest;
            int wt=o.weight;
            if(go!=parent) // DFS only if forward!=parent
                dfs(go,node,level+1,wt);
        }
    }

    static void preprocess()
    {
        for(int j=1;j<LN;j++)
        {
            for(int i=1;i<=N;i++)
            {
                P[i][j]=P[P[i][j-1]][j-1]; // 2^jth ancestor of i= 2^j-1 th ancestor of 2^j-1th ancestor of i
                C[i][j]=Math.max(C[i][j-1],C[P[i][j-1]][j-1]); // Max of both (moving bottom up)
            }
        }
    }

    static int query(int p , int q)
    {
        if(L[p]<L[q])
        {
            int temp=p;
            p=q;
            q=temp;
        }
        // Made sure that p is lower than q in the tree
        int max=-1;
        for(int i=LN-1;i>=0;i--)
        {
            if(L[p]-(1<<i) >=L[q])
            {
                max=Math.max(max,C[p][i]);
                p=P[p][i]; // Move p upwards till p and q on the same level
            }
        }
        if(p==q)
            return max;
        // Once they are on the same level . move both upwards each time, till they are just below their LCA
        for(int i=LN-1;i>=0;i--)
        {
            if(P[p][i]!=P[q][i])
            {
                max=Math.max(max,Math.max(C[p][i],C[q][i]));
                p=P[p][i];
                q=P[q][i];
            }
        }
        return Math.max(max,Math.max(C[p][0],C[q][0]));
    }

    static int root (int x)
    {
        if (parent[x] == x)
            return x;
        else 
            return parent[x] = root(parent[x]);
    }

    static void union (int u, int v)
    {
        u = root(u);
        v = root(v);
        if(sz[u]<sz[v])
        {
            parent[u]=v;
            sz[v]+=sz[u];
        }
        else
        {
            parent[v]=u;
            sz[u]+=sz[v];
        }
    }   
}
class pair
{
    int dest , weight;
    public pair(int a , int b)
    {
        dest=a;
        weight=b;
    }
}
class Edge implements Comparable<Edge>
{
    int from , to , weight;
    public Edge(int a , int b , int c)
    {
        from=a;
        to=b;
        weight=c;
    }

    public int compareTo( Edge e)
    {
        return this.weight - e.weight;
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

