import java.io.*;
import java.util.*;
class TreeandQuery_Mo
{
    static int C [] = new int [100010]; // Colour
    static int A [] = new int [100010]; // Compressed Tree into Array
    static query Q[] = new query[100010]; // To store Queries
    static int ANS[] = new int [100010]; // To store Answers
    static int CUR[]=new int[100010]; // To store number of colors with a specific frequency of occurrence.
    static int CNT[]=new int[100010]; // To store frequency of each colour .
    static int S[] = new int[100010]; // To store Start Time in Euler Array
    static int F[] = new int[100010]; // To store Finish Time in Euler Array
    static int N,q,SQ,time=0;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(); // ADJLIST
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt(); q = sc.nextInt();
        for(int i=0;i<=N;i++)adjList.add(new ArrayList<Integer>());
        int sq = (int)Math.sqrt(N);
        for(int i=1;i<=N;i++) C[i] = sc.nextInt();
        for(int i=1;i<N;i++)
        {
            int u = sc.nextInt() , v = sc.nextInt();
            adjList.get(u).add(v); adjList.get(v).add(u);
        }
        dfs(1,-1);
        for(int i=1;i<=q;i++)
        {
            int node = sc.nextInt() , val = sc.nextInt();
            Q[i] = new query(S[node],F[node],val,i,S[node]/sq);
            /* For each query ,
             * L , R is S[node] and F[node] in the compressed array
             * Minimum Frequency of Any color is stored in val
             * Query Index is stored as i
             * Block Number is Query L value / Sqrt(N) as done above
             */
        }
        Arrays.sort(Q,1,q+1); // Sort by block number and for same block sort in asc order or R value
        int curl=0, curr = 0;
        for(int i=1;i<=q;i++)
        {
            int l = Q[i].l;
            int r = Q[i].r;
            while(curl>l)
            {
                --curl;
                CNT[A[curl]]++; // Increase count of the specific colour
                CUR[CNT[A[curl]]]++; // Increase number of color with atleast that specific frequency
            }
            while(curr<r)
            {
                ++curr;
                CNT[A[curr]]++; // Increase count of the specific colour
                CUR[CNT[A[curr]]]++; // Increase number of colors with atleast that frequency
            }
            while(l>curl)
            {
                CNT[A[curl]]--; // Decrease Frequency of that specific colour
                CUR[CNT[A[curl]]+1]--; // Decrease number of colors with that specific frequency
                curl++;
            }
            while(r<curr)
            {
                CNT[A[curr]]--;// Decrease Frequency of that specific colour
                CUR[CNT[A[curr]]+1]--; // Decrease Number of colors with that sp. freq
                curr--;
            }
            // N ROOT N because each pointer moves maximum sqrt(N) times for each query
            ANS[Q[i].idx]=CUR[Q[i].val];
        }
        for(int i=1;i<=q;i++)
            System.out.println(ANS[i]);
    }

    static void dfs(int node ,int parent)
    {
        S[node] = ++time;
        A[time] = C[node]; // Store the colour!
        for(int next : adjList.get(node))
        {
            if(next!=parent) dfs(next,node);
        }
        F[node] = time;
    }
}
class query implements Comparable<query>
{
    int l , r, val,idx ,bl;
    public query(int l , int r, int val , int idx ,int bl)
    {
        this.l = l;
        this.r = r;
        this.val=val;
        this.idx=idx;
        this.bl=bl;
    }

    public int compareTo(query q)
    {
        if(this.bl!=q.bl) return this.bl-q.bl; // sort acc to block number
        else return this.r-q.r; // if same block , then sort acc to r value
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