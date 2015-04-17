
import java.util.*;
import java.io.*;
class ComputerNetwork
{
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static pair DP[][]=new pair[10001][2];
    static int D[]=new int[10001]; // Discovery Time
    static int VIS[]=new int[10001];
    static tuple ANS = new tuple(0,new pair(0,0)); //(Longest Path , End Points of LP)
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
        System.out.println((1-ANS.second.first)+" "+(1-ANS.second.second)); // we had stored them as -x
    }

    static int dfs(int node , int parent , int depth)
    {
        VIS[node]=1;
        DP[node][0] = DP[node][1] = new pair(0,-node); // (-NODE) for easier lexicographical check
        // DP[X][0] = Maximum Weighted Path in the subtree rooted at X where X IS ONE END POINT OF THE PATH
        // DP[X][1] = Second  Max '     '     '    '      '     '    '   '   '   '  '  '  '   '    '   '   '
        D[node] = depth; // Depth (disc time)
        int ret = depth; // Lowest Back Edge
        int rem = 1;  // to solve multiple edges problem
        // Usually edges from node --> parent do not qualify as back edges
        // If there are multiple edges from node to parent ,one of them can be considered as back edge.
        for(int i=0;i<adjList.get(node).size();i++)
        {
            int v = adjList.get(node).get(i); // Adj List 
            if(VIS[v]==0) // If not visited(must keep visited array as it is a graph)
            {
                int db = dfs(v,node,depth+1); 
                pair tr = DP[v][0];
                ret = Math.min(ret,db); // Lowest back edge from that child
                if(db>depth)   // Bridge!
                    tr.first++;  // Increase weight of path as bridge found
                if(pair_greater(tr,DP[node][0]))
                {
                    DP[node][1]=DP[node][0];
                    DP[node][0]=tr;
                }
                else if(pair_greater(tr,DP[node][1]))
                    DP[node][1] = tr;
            }
            else
            {
                if(v==parent && rem==1) rem--; // initial check , if multiple edges then it qualifies as back edge
                else 
                    ret = Math.min(ret,D[v]); //Lowest Back Edge Value
            }
        }
        int dd = DP[node][0].first + DP[node][1].first ; //Sum of Second max path and Max Path
        pair v1 = new pair(DP[node][0].second , DP[node][1].second); // End Points of the path
        if(v1.first < v1.second) // Swap for lexico purpose
        { 
            int t = v1.first;
            v1.first=v1.second;
            v1.second=t;
        }
        tuple cans = new tuple(dd,v1);
        if(tuple_greater(cans,ANS))
            ANS=cans;
        return ret;     // lowest back edge
    }

    static boolean pair_greater(pair p , pair q)
    {
        if((p.first==q.first && p.second>=q.second)||p.first>q.first)
            return true;
        else
            return false;
    }

    static boolean tuple_greater(tuple p , tuple q)
    {
        if((p.first>q.first)||(p.first==q.first && p.second.first>q.second.first)||
        (p.first==q.first && p.second.first==q.second.first && p.second.second>q.second.second))   
            return true;
        else
            return false;  
    }
}
class tuple
{
    int first; 
    pair second;
    public tuple(int a , pair b)
    {
        first = a;
        second = b;
    }
}
class pair
{
    int first,second;
    public pair(int first , int second)
    {
        this.first = first;
        this.second= second;
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
