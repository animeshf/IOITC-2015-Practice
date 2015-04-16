import java.util.*;
import java.io.*;
class LongestPathInATree_DP_CLARITY
{
    static int N = 10001;
    static int dp[][]=new int[N][2];
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
    static int INF = -10000;
    static int n;
    /**dp[node][0] = Best you can do in the subtree rooted at node
    when node is one of the end points of the longest path
    dp[node][1] = Best you can do in the subtree rooted at node
    when no such restictions are there.
    Therefore answer is dp[1][1]!**/
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        n = sc.nextInt();
        for(int i=0;i<=10000;i++)
            adj.add(new ArrayList<Integer>());
        for(int i=1;i<n;i++)
        {
            int a=sc.nextInt(),b=sc.nextInt();
            adj.get(a).add(b);// a-->b
            adj.get(b).add(a);// b-->a
        }
        dfs1(1,-1);
        dfs2(1,-1);
        System.out.println(dp[1][1]);
    }

    static void dfs1(int node , int parent)
    {
        dp[node][0]=0;
        int sz = adj.get(node).size();
        for(int i=0;i<sz;i++)
        {
            int next = adj.get(node).get(i);
            if(next!=parent)
            {
                dfs1(next,node);
                dp[node][0]=Math.max(dp[node][0],dp[next][0]+1);
            }
        }
    }

    static void dfs2(int node , int parent)
    {
        dp[node][1]=0;
        int max = INF , second_max=INF;
        int sz = adj.get(node).size();
        for(int i=0;i<sz;i++)
        {
            int next = adj.get(node).get(i);
            if(next!=parent)
            {
                dfs2(next,node);
                dp[node][1]=Math.max(dp[node][1],dp[next][1]);
                if(dp[next][0]>=max)
                {
                    second_max=max;
                    max=dp[next][0];
                }
                else if(dp[next][0]>second_max)
                {
                    second_max=dp[next][0];
                }
            }
        }
        int temp = Math.max(max,second_max);
        dp[node][1]=Math.max(dp[node][1],temp+1);
        dp[node][1]=Math.max(dp[node][1],max+second_max+2);
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