import java.util.*;
import java.io.*;
class Bridges
{ 
    static ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
    static int parent[]=new int[100001];
    static int visited[]=new int[100001];
    static int disc[]=new int[100001];
    static int low[]=new int[100001];
    static int ap[]=new int[100001];
    static int ans=0;
    static int time=0;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        while(T-->0)
        {
            map.clear();
            Arrays.fill(parent,-1);
            Arrays.fill(visited,-1);
            Arrays.fill(ap,-1);
            Arrays.fill(disc,0);
            Arrays.fill(low,0);
            int N = sc.nextInt(); int M = sc.nextInt();
            for(int i=0;i<=N;i++)
                map.add(new ArrayList<Integer>());
                for(int i=0;i<M;i++)
            {
                int u=sc.nextInt(),v =sc.nextInt();
                map.get(u).add(v);
                map.get(v).add(u);
            }
            ans=0;time=1;
            for(int i=1;i<=N;i++)if(visited[i]==-1)dfs(i);
            System.out.println(ans);
        }
    }

    static void dfs (int u)
    {
        visited[u]=1;
        disc[u]=time;
        low[u]=time++;
        int sz = map.get(u).size();
        for(int i=0;i<sz;i++)
        {
            int v = map.get(u).get(i);
            if(visited[v]==-1)
            {
                parent[v]=u;
                dfs(v);
                low[u]  = Math.min(low[u], low[v]);
                if (low[v] > disc[u])
                    ans++;
            }
            else if (v != parent[u])
                low[u]  = Math.min(low[u], disc[v]);
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

