import java.io.*;
import java.util.*;
class PashmakOnGraph
{
    static int N,M;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        Edge E[] = new Edge[M];
        int  TEMP [] = new int[N+1];
        int  DP   [] = new int[N+1];
        for(int i=0;i<M;i++)
            E[i] = new Edge(sc.nextInt(),sc.nextInt(),sc.nextInt());
        Arrays.sort(E);
        int i=0;
        while(i<M)
        {
            int l = i , r = i;
            while(r<=M-2 && E[l].w == E[r+1].w)r++;
            for(int j=l;j<=r;j++)
            TEMP[E[j].v] = DP[E[j].v];
            for(int j=l;j<=r;j++)
                TEMP[E[j].v] = Math.max(DP[E[j].u]+1,TEMP[E[j].v]);
            for(int j=l;j<=r;j++)
                DP[E[j].v]   = TEMP[E[j].v];
            i=r+1;
        }
        int ans=Integer.MIN_VALUE;
        for(int val:DP) ans=Math.max(ans,val);
        System.out.println(ans);
    }
}
class Edge implements Comparable <Edge>
{
    int u,v,w;
    public Edge(int u,int v ,int w)
    {
        this.u=u;
        this.v=v;
        this.w=w;
    }

    public int compareTo(Edge e)
    {
        return this.w - e.w;
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