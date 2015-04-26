import java.io.*;
import java.util.*;
class DSUBerland
{
    static int parent[] = new int[1001];
    static int size [] = new int [1001];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        for(int i=1;i<=N;i++)
        {
            parent [i] = i;
            size[i] = 1;
        }
        ArrayList<Pair> rem = new ArrayList<Pair>();
        ArrayList<Pair> ins = new ArrayList<Pair>();
        for(int i=1;i<N;i++)
        {
            int u = sc.nextInt();
            int v = sc.nextInt();
            if(find(u) == find(v))
                rem.add(new Pair(u,v));
            else
                union(u,v);
        }
        
        for(int i=1;i<=N;i++)
        {
            for(int j=i+1;j<=N;j++)
            {
                if(find(i)!=find(j))
                {
                    ins.add(new Pair(i,j));
                    union(i,j);
                }
            }
        }
        System.out.println(ins.size());
        for(int i=0;i<ins.size();i++)
        System.out.println(rem.get(i).first+" "+rem.get(i).second+" "+ins.get(i).first+" "+ins.get(i).second);     
    }
    static int find(int x)
    {
        if(parent[x]==x)return x;
        else return parent[x] = find(parent[x]);
    }
    static void union(int i , int j)
    {
        int u = find(i);
        int v = find(j);
        if(u==v)return;
        if(size[u] < size[v])
        {
            size[v] += size[u];
            parent[u]=v;
        }
        else
        {
            size[u]+=size[v];
            parent[v]=u;
        }
    }
}
class Pair
{
    int first , second;
    public Pair(int first ,int second)
    {
        this.first = first;
        this.second = second;
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