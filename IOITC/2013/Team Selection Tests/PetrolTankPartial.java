import java.util.*;
import java.io.*;
class PetrolTankPartial
{ 
    static int parent[]=new int[100001];
    static int sz[]=new int[100001];
    static ArrayList<Edge> edges = new ArrayList<Edge>();
    static ArrayList<ArrayList<pair>> mst = new ArrayList<ArrayList<pair>>();
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
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
        while(Q-->0)
        {
            int source=sc.nextInt();
            int destination=sc.nextInt();
            dfs(source,destination,0,-1);
        }
    }
    
    static void dfs(int s , int d , int max , int par)
    {
        if(s==d)
        {
            System.out.println(max);
            return;
        }
        int size = mst.get(s).size();
        for(int i=0;i<size;i++)
        {
            pair o = mst.get(s).get(i);
            int go=o.dest;
            int ed=o.weight;
            if(go!=par)
            dfs(go,d,Math.max(max,ed),s);
        }
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

