import java.util.*;
import java.io.*;
class ComputerNetwork
{
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static pair DP[][]=new pair[10001][2];
    static int D[]=new int[10001];
    static int VIS[]=new int[10001];
    static pairer ANS = new pairer(0,new pair(0,0));
    static int N,M;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        /**
         * Find an edge which minimises number of bridges in the graph
         */
        for(int i=0;i<=10000;i++)
            adjList.add(new ArrayList<Integer>());
        N = sc.nextInt();
        M = sc.nextInt();
        for(int i=1;i<=M;i++)
        {
            int from = sc.nextInt() , to = sc.nextInt();
            from--;to--;
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }
        dfs(0,0,0);
        System.out.println((1-ANS.second.len)+" "+(1-ANS.second.node));
    }

    static int dfs(int node , int parent , int depth)
    {
        VIS[node]=1;
        DP[node][0] = DP[node][1] = new pair(0,-node);
        D[node] = depth;
        int ret = depth;
        int rem = 1;
        for(int i=0;i<adjList.get(node).size();i++)
        {
            int v = adjList.get(node).get(i);
            if(VIS[v]==0)
            {
                int db = dfs(v,node,depth+1);
                pair tr = DP[v][0];
                ret = Math.min(ret,db);
                if(db>depth)
                    tr.len++;
                if((tr.len==DP[node][0].len && tr.node>=DP[node][0].node) || tr.len>DP[node][0].len)
                {
                    DP[node][1]=DP[node][0];
                    DP[node][0]=tr;
                }
                else if((tr.len==DP[node][1].len && tr.node>=DP[node][1].node) || tr.len>DP[node][1].len)
                    DP[node][1] = tr;
            }
            else
            {
                if(v==parent && rem==1) rem--;
                else
                    ret = Math.min(ret,D[v]);
            }
        }
        int dd = DP[node][0].len+DP[node][1].len;
        pair v1 = new pair(DP[node][0].node , DP[node][1].node);
        if(v1.len < v1.node)
        {
            int t = v1.len;
            v1.len=v1.node;
            v1.node=t;
        }
        pairer cans = new pairer(dd,v1);
        if((cans.first>ANS.first)||(cans.first==ANS.first && cans.second.len>ANS.second.len)||
        (cans.first==ANS.first && cans.second.len==ANS.second.len && cans.second.node>ANS.second.node))
            ANS=cans;
        return ret;    
    }
}
class pairer
{
    int first;
    pair second;
    public pairer(int a , pair b)
    {
        first = a;
        second = b;
    }
}
class pair
{
    int len,node;
    public pair(int len , int node)
    {
        this.len=len;
        this.node=node;
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
