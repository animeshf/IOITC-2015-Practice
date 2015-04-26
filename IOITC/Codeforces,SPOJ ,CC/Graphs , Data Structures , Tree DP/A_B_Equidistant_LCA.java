import java.io.*;
import java.util.*;
class A_B_Equidistant_LCA
{
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static int L [] = new int [100010];
    static int ST[] = new int[100010];
    static int DP[][] = new int[100010][18] , N;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        for(int i=0;i<=N;i++)adjList.add(new ArrayList<Integer>());
        for(int i=1;i<N;i++)
        {
            int u = sc.nextInt() , v = sc.nextInt();
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        dfs(1,0,-1);
        preprocess();
        int Q = sc.nextInt();

        while(Q-->0)
        {
            int x = sc.nextInt() , y = sc.nextInt();
            System.out.println(solve(x,y));
        }
    }
    static int move(int node , int dist)
    {
        while(dist!=0)
        {
            int fac = (int) (Math.log(dist) / Math.log(2));
            node = DP[node][fac];
            dist -= (1<<fac);
        }
        return node;
    }
    static int solve(int x , int y)
    {
        if(x==y) return N;
        if(L[x] < L[y]){ int t=x;x=y;y=t;}
        int lca = LCA(x,y);
        int dist = L[x]+L[y]-(2*L[lca]);
        if(dist%2==1)return 0;
        if(L[x] - L[lca] == L[y] - L[lca])
        {
            int X = move(x,L[x]-L[lca]-1);
            int Y = move(y,L[y]-L[lca]-1);
            return N - (ST[X] + ST[Y]);
        }
        int X = move(x,dist/2 - 1);
        int Y = DP[X][0];
        return ST[Y] - ST[X];
    }
    static int LCA(int x , int y)
    {
        if(L[x] < L[y])
        {
            int t = x; x=y; y=t;
        }
        for(int i=17 ; i>=0; i--)
        {
            if(L[x] - (1<<i )  >= L[y])
                x = DP[x][i];
        }
        if(x==y)return x;
        for(int i = 17 ; i>=0; i--)
        {
            if(DP[x][i] != DP[y][i])
            {
                x = DP[x][i];
                y = DP[y][i];
            }
        }
        return DP[x][0];   
    }

    static void preprocess()
    {
        for(int j=1;j<18;j++)for(int i=1;i<=N;i++)DP[i][j] = -1;

        for(int j=1;j<18;j++)
        {
            for(int i=1;i<=N;i++)
            {
                if(DP[i][j-1]!=-1)
                    DP[i][j] = DP[DP[i][j-1]][j-1];
            }
        }
    }

    static void dfs(int node , int depth , int parent)
    {
        DP[node][0]= parent;
        L[node] = depth;
        ST[node] = 1;
        for(int next : adjList.get(node))
        {
            if(next!=parent)
            {
                dfs(next,depth+1,node);
                ST[node]+=ST[next];
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