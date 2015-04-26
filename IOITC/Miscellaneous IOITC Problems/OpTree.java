import java.io.*;
import java.util.*;
class OpTree
{
    static long IN[] = new long[100001];
    static long D[] = new long[100001];
    static long MOD = 1000000007;
    static ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>();
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        Arrays.fill(D,-1);
        for(int i=0;i<=100000;i++) adjList.add(new ArrayList<Edge>());
        int N = sc.nextInt(),M = sc.nextInt();
        while(M-->0)
        {
            int u = sc.nextInt() , v= sc.nextInt();
            long w = sc.nextLong();
            adjList.get(u).add(new Edge(v,w));
            adjList.get(v).add(new Edge(u,w));
        }
        PriorityQueue<Item> pq = new PriorityQueue<Item>();
        pq.add(new Item(0,0));
        while(!pq.isEmpty())
        {
            Item cur = pq.poll();
            int node = cur.node;
            long cost = cur.cost;
            if(D[node] !=-1 && D[node] == cost) // Visited but cost is same too.
                IN[node]++;
            else if(D[node]!=-1)continue; // Visited
            else
            {
                D[node] = cost;
                IN[node] = 1;
                for(Edge next : adjList.get(node))
                {
                    int nextNode = next.to;
                    long nextWeight = next.weight;
                    pq.add(new Item(nextNode,cost+nextWeight));
                }
            }
        }
        long ans = 1;
        for(int i=0;i<N;i++)
        {
            ans*=IN[i];
            ans%=MOD;
        }
        System.out.println(ans);    
    }
}
class Edge
{
    int to; 
    long weight;
    public Edge(int to , long weight)
    {
        this.to=to;
        this.weight=weight;
    }
}
class Item implements Comparable<Item>
{
    int node ; long cost;
    public Item(int node ,long cost)
    {
        this.node = node;
        this.cost = cost;
    }

    public int compareTo(Item it)
    {
        return Long.compare(this.cost , it.cost);
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