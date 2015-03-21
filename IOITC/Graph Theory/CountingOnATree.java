import java.util.*;
import java.io.*;
class CountingOnATree
{
    static ArrayList<ArrayList<Edge>> map = new ArrayList<ArrayList<Edge>>();
    static int ans=0;
    static int[] edges = new int[1001];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        for(int i=0;i<=N;i++)
            map.add(new ArrayList<Edge>());
        for(int i=1;i<N;i++)
        {
            int x=sc.nextInt() , y =sc.nextInt() ,z=sc.nextInt();
            map.get(x).add(new Edge(y,i));
            map.get(y).add(new Edge(x,i));
            edges[i]=z;
        }
        StringBuilder op=new StringBuilder();
        for(int i=1;i<=N;i++)
        {
            dfs(i,0,-1);
        }
        int temp=ans/2;
        op.append(temp).append("\n");
        int Q =sc.nextInt();
        while(Q-->0)
        {
            ans=0;
            int edgeNumber=sc.nextInt(),newEdgeCost=sc.nextInt();
            edges[edgeNumber]=newEdgeCost;
            for(int i=1;i<=N;i++)
            {
                dfs(i,0,-1);
            }
            temp=ans/2;
            op.append(temp).append("\n");
        }
        System.out.println(op);
    }
    
    static void dfs(int node ,int gcd , int parent)
    {
        if(gcd==1)
            ans++;
        int size= map.get(node).size();
        for(int i=0;i<size;i++)
        {
            int nextNode=map.get(node).get(i).node;
            int edgeWeight=edges[map.get(node).get(i).index]; 
            if(nextNode!=parent)
                dfs(nextNode,hcf(gcd,edgeWeight),node);
        }
    }

    static int hcf(int a , int b)
    {
        if(b==0)
            return a;
        else
            return hcf(b,a%b);
    }
}
class Edge
{
    int node,index;
    public Edge()
    {
        node=0;
        index=0;
    }

    public Edge(int a ,int b)
    {
        node=a;
        index=b;
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

