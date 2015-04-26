import java.io.*;
import java.util.*;
class QueriesOnTree_Buffer
{
    static int N = 100050;
    static int MAX_BUF = 105;
    static long mod  = 1000000007;
    static long INF = 1<<(long)55;
    static long cnt[] = new long [N];
    static long ans[] = new long [N];
    static ArrayList<ArrayList<Integer>> depth = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
    static long ct[] = new long [N];
    static int buffer[]=new int[MAX_BUF],pos[]=new int[N],sz[]=new int[N],Till=0,x;
    static int H = 0,n,m;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        n = sc.nextInt() ; m = sc.nextInt();
        // O(N root N log N)
        for(int i=0;i<=100005;i++)
        {
            adj.add(new ArrayList<Integer>());
            depth.add(new ArrayList<Integer>());
        }
        for(int i=1;i<n;i++)
        {
            int u = sc.nextInt() , v = sc.nextInt();
            adj.get(u).add(v);
        }
        dfs0(1,0);
        while(m-->0)
        {
            int id = sc.nextInt();
            if(id==1){
                int u = sc.nextInt(),v=sc.nextInt();
                buffer[Till] = u; // The node 
                ct[Till++] = v; //  Value to be Updated for i'th query.
                cnt[u] += v; // Update Value to be incremented in the subtree rooted at u.
                if(Till == MAX_BUF) rebuild(); //  > Sqrt N
            }
            else{
                int x = sc.nextInt();
                System.out.println(get(x));
            }
        }
    }

    static long get(int x)
    {
        long ret = ans[x]; // Current answer for SubTree rooted at X
        for(int i=0;i<Till;i++) // Till has a maxValue of sqrt N
        {
            ret+= ((long)end(buffer[i],pos[x]+sz[x]-1) - (long)start(buffer[i],pos[x])+1)*ct[i];
            //pos[x]+sz[x]-1 gives the finish time of subtree rooted at x .
            //pos[x]+1 is the start time of subtree rooted at x .
            // Distance buffer[i] from root ---> if there are any nodes in the subtree rooted at x 
            // which are at a distance buffer[i] from root , add the update values of those to the cur query.
            // The number of Nodes in the subtree rooted at x which are at a distance buffer[i] from node
            // can be computed by simply doing UpperBound(level,FinishTime[x]) - LowerBound(level,StartTime[x])+1 
            // This can be multiplied by update value ct[i].
        }
        return ret;
    }

    static void rebuild()
    {
        dfs1(1,0);
        for(int i=0;i<n+1;i++) cnt[i]=0;
        Till=0;
    }

    static long dfs1(int v , int dep)
    {
        long ret  = 0;
        ret += cnt[dep]; // Summation of Update Values for past sqrt M queries
        for(int i=0;i<adj.get(v).size();i++)
            ret+=dfs1(adj.get(v).get(i),dep+1);
        ans[v]+=ret;
        return ret;
    }

    static int start(int y , int x) // Lowerbound
    {
        int lo = 0 , hi = depth.get(y).size();
        while(lo<hi)
        {
            int mid = (lo+hi)/2;
            if(depth.get(y).get(mid) < x) lo=mid+1;
            else hi=mid;
        }
        return lo;
    }

    static int end(int y , int x) // Upperbound
    {
        int lo = -1, hi = depth.get(y).size() -1;
        while(lo<hi)
        {
            int mid = (lo+hi+1)/2;
            if(depth.get(y).get(mid) <= x) lo = mid;
            else hi = mid - 1;
        }
        return lo;
    }

    static void dfs0(int v , int dep)
    {
        pos[v] = H++; // Order in which they are visited.
        depth.get(dep).add(pos[v]); // Add the visiting index for that depth
        // Note: Nodes in the subtree of a particular node will have a V.Index > V.Index of Node and also
        // till increase till a limit = V.Index of Node + Size of Subtree Rooted at Node.
        sz[v] = 1;
        for(int i=0;i<adj.get(v).size();i++)
        {
            dfs0(adj.get(v).get(i) , dep+1 );
            sz[v] += sz[adj.get(v).get(i)];
        }
        ans[v] = 0;
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