import java.util.*;
import java.io.*;
class Catering_Contract_Remix_DP_ON_TREES_2
{
    static ArrayList<ArrayList<Edge>>adjList = new ArrayList<ArrayList<Edge>>();
    static int N,M=99;
    static int dp[][]=new int[100001][101];
    // Best you can do if the number 'j' is assigned to the subtree rooted at 'i'.
    static int contractor[]=new int[100001];
    static int INF = 1000000000;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        for(int i=0;i<=100010;i++)
            adjList.add(new ArrayList<Edge>());
        N = sc.nextInt();
        for(int i=1;i<N;i++)
        {
            int u = sc.nextInt() , v = sc.nextInt() , w = sc.nextInt();
            adjList.get(u).add(new Edge(v,w));
            adjList.get(v).add(new Edge(u,w));
        }
        for(int i=1;i<=N;i++)
        {
            contractor[i] = sc.nextInt();
            M=Math.max(M,contractor[i]);
            if(contractor[i]>-1) // Unchangeable values!
            {
                for(int j=0;j<=100;j++)
                    dp[i][j]=INF; // you cant change it cause its fixed
                dp[i][contractor[i]]=0; // set to zero
            }
        }
        dfs(1,-1);
        int ans=INF;
        for(int i=0;i<=M;i++)
            ans=Math.min(ans,dp[1][i]);
        System.out.println(ans);
    }

    static void dfs(int node, int parent)
    {
        int sz = adjList.get(node).size();
        for(int i=0;i<sz;i++)
        {
            int next = adjList.get(node).get(i).to;
            int cost = adjList.get(node).get(i).weight;
            if(next==parent)continue;
            dfs(next,node);
            int minimum = INF;
            int minimum_index=0;
            // Find the index associated with the least value of the child of node
            for(int j=0;j<=M;j++)
            {
                if(dp[next][j]<minimum)
                {
                    minimum = dp[next][j];
                    minimum_index=j;
                }
            }
            /*
             * Instead of iterating through all possibilties, we can just consider two:
             * minimum_index = that index of child that gives minimum cost of subtree rooted at child
             * assigning a number j to the current node can be:
             * 1) assigning the same number as the minimum_index of the child , so edge cost can be ignored
             * 2) assigning a "different number" than the minimum_index of child :
             * In this case take minimum of :
             * a)Assigning this "different number" to the child as well allowing us to ignore the Edge Cost.
             * b)Taking the minimum_value of child(value associated with minimum_index), 
             *   in which case we need to consider the Edge Cost as well.
             */
            for(int j=0;j<=M;j++)
            {
                if(j==minimum_index) 
                    dp[node][j]+=dp[next][j];
                else
                    dp[node][j]+=Math.min(dp[next][j],dp[next][minimum_index]+cost);
            }
        }
    }
}
class Edge
{
    int to , weight;
    public Edge()
    {
        to=0;
        weight=0;
    }

    public Edge(int a , int b)
    {
        to=a;
        weight=b;
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