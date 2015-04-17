import java.util.*;
import java.io.*;
class FEYNMAN
{
    static int sinkReachable [] = new int [ 100001 ];
    static int disc [] = new int  [ 100001 ];
    static int low [] =  new int  [ 100001 ];
    static int vis [] =  new int  [ 100001 ];
    static int bridges = 0 , time = 0 , N , M ;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>> ();
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        for(int i = 0 ; i <= 100000 ; i++) 
        adjList.add(new ArrayList<Integer>());
        while( T-->0 )
        {
            reset();
            N = sc.nextInt(); M = sc.nextInt();
            while(M-->0)
            {
                int u = sc.nextInt() , v = sc.nextInt();
                adjList.get(u).add(v) ;
                adjList.get(v).add(u) ;
            }
            sinkReachable[N] = 1;
            dfs(1 , -1);
            System.out.println(bridges);
        }
    }
    static void dfs(int node , int parent)
        {
            vis[ node ] = 1;
            disc[ node ] = low [ node ] = ++time;
            for(int next : adjList.get(node))
            {
                if(vis[next] == 0)
                {
                    dfs(next,node);
                    low[node] = Math.min(low[node],low[next]);
                    if(sinkReachable[next] == 1)
                        sinkReachable[node] = 1;
                    if( sinkReachable[next] == 1 && low[next] > disc [node])
                        bridges++;                      
                }
                else if(next!=parent)
                {
                    low[node] = Math.min(low[node] , disc[next]);
                    if(sinkReachable[next] == 1)
                        sinkReachable[node] = 1;
                }
            }
        }
    static void reset()
    {
        Arrays.fill(sinkReachable,0);
        Arrays.fill(disc , 0);
        Arrays.fill(low , 0);
        Arrays.fill(vis , 0);
        bridges = 0 ;
        time = 0 ;
        for(int i = 0 ; i <= 100000; i++ )
            adjList.get(i).clear();
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