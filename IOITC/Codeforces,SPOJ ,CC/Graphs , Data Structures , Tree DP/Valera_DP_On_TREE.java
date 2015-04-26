import java.util.*;
import java.io.*;
class Valera_DP_On_TREE
{
    static int N;
    static int mark[] = new int [200010];
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static int DP[] = new int   [200010];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        for(int i=0;i<200010;i++)
            adjList.add(new ArrayList<Integer>());
        N = sc.nextInt();
        for(int i=1;i<N;i++)
        {
            int u = sc.nextInt() , v = sc.nextInt() , cost = sc.nextInt();
            if(cost==2)
            {
                mark[u]=1; // Mark EndPoints of Trouble Edge
                mark[v]=1; 
            }
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        dfs(1,-1);
        int c=0; 
        StringBuilder ans = new StringBuilder();
        for(int i=1;i<=N;i++)
        {
            if(mark[i]==1 && DP[i]==1)
            {
                ans.append(i).append(" ");
                c++;
            }
        }
        System.out.print(c+"\n"+ans);
    }

    static void dfs(int node , int parent)
    {
        DP[node] = mark[node];
        for(int v:adjList.get(node))
        {
            if(v!=parent)
            {
                dfs(v,node);
                DP[node]+=DP[v];
            }
        }
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

    