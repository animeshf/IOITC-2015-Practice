/**
 * Techniques:
 * 
 * Find Kth Ancestor of a node in Log N
 * Find all nodes at a level X from the Root
 * Find all nodes that are CHILDREN of Node and are in the SUBTREE of Node and ARE AT A DISTANCE K from Node
 * using LowerBound , UpperBound AND DFS property
 */
import java.util.*;
import java.io.*;
class BloodCousins_KthAncestor_LCA_LowUppBound
{
    static ArrayList<ArrayList<Integer>> v = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> level = new ArrayList<ArrayList<Integer>>();
    static int dp[][] = new int[100010][18];
    static int depth [] = new int [100001];
    static int start [] = new int [100001];
    static int finish[] = new int [100001];
    static int time = 0;
    static int N , Q;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        for(int i=0;i<=100010;i++)
        {
            v.add(new ArrayList<Integer>());
            level.add(new ArrayList<Integer>());
        }
        for(int i = 1 ; i <= N ; i++)
        {
            int parent = sc.nextInt();
            v.get(parent).add(i);
            for(int j = 0 ; j < 18 ; j++)
                dp[i][j] = -1;
        }
        dfs(0,1,-1);
        preprocess();
        Q = sc.nextInt();
        for(int i=1;i<=N;i++)
        {
            Collections.sort(level.get(i));
        }
        StringBuilder output = new StringBuilder();
        while(Q-->0)
        {
            int node = sc.nextInt(), k = sc.nextInt();
            int anc = ancestor(node,k);
            output.append(solve(anc,k)-1).append(" ");
        }
        System.out.println(output);
    }

    static int ancestor(int node , int k)
    {
        while(k>0)
        {
            int lim = (int)(Math.log(k)/Math.log(2)); // Nearest power of 2 <= k
            node = dp[node][lim];              // node = 2^limith ancestor of node
            if(node==-1) return -1;
            k-=(1<<lim); 
        } 
        return node;
    }

    static int solve(int node ,int dist)
    {
        if(node<=0)return 1;
        if(dist+depth[node]>=N)return 1;
        return upper_bound(level.get(dist+depth[node]) , finish[node])
             - lower_bound(level.get(dist+depth[node]) , start[node]) ;
    }

    static int upper_bound(ArrayList<Integer> x , int val)
    {
        // Returns first index > val
        int l = 0;int h = x.size()-1;
        if(h==-1)return 0; // null arraylist
        while(l!=h)
        {
            int mid = (l+h) / 2;
            if(x.get(mid)>val)
                h = mid;
            else
                l = mid+1;
        }
        if(x.get(l)<=val)        return x.size(); // edge case
        else                     return l;
    }

    static int lower_bound(ArrayList<Integer> x , int val)
    {
        // Returns first index with value >= val
        int l = 0;int h = x.size()-1;
        if(h==-1)return 0; // null list
        while(l!=h)
        {
            int mid = (l+h) / 2;
            if(x.get(mid)>=val)
                h = mid;
            else
                l = mid + 1;
        }
        if(x.get(l)<val) return x.size(); // edge case
        else             return l;
    }

    static void preprocess()
    {
        for(int j=1;j<18;j++)
        {
            for(int i=1;i<=N;i++)
            {
                if(dp[i][j-1]!=-1)
                    dp[i][j] = dp[dp[i][j-1]][j-1];
            }
        }
    }

    static void dfs(int node , int lvl , int par)
    {
        start[node] = ++time;
        level.get(lvl).add(start[node]);
        depth[node] = lvl;	
        dp[node][0] = par;
        for(int next : v.get(node))
            dfs(next,lvl+1,node);
        finish[node]= time;
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