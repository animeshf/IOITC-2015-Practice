import java.util.*;
import java.io.*;
class ChooseCapital_DP_ON_TREES 
{
    static ArrayList<ArrayList<Node>> adjList = new ArrayList<ArrayList<Node>> () ;
    static int VISITED [] = new int [200001] , totalUpward=0 ;
    static Node NODES [] = new Node [200001];
    public static void main(String[]args)
    {        
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        for(int i=0;i<=N;i++)
        {
            adjList.add(new ArrayList<Node>());
        }
        for(int i=1;i<N;i++)
        {
            int u = sc.nextInt() , v = sc.nextInt();
            adjList.get(u).add(new Node(v,1,0,0));
            adjList.get(v).add(new Node(u,0,0,0));
        }
        NODES[1] = new Node(1,1,0,0);
        dfs(1,0,0);
        int ans = Integer.MAX_VALUE;
        for(int i = 1 ; i <= N ; i++)
        {
            ans = Math.min(ans ,  totalUpward -  2*(NODES[i].upw) + NODES[i].dist);
        }
        System.out.println(ans);
        for (int i = 1; i<=N; i++)
        {
            if (ans ==  totalUpward -2*(NODES[i].upw) + NODES[i].dist)
            {
                System.out.print(i+" ");
            }
        }
    }

    static void dfs(int node , int dist , int upw)
    {
        VISITED[node] = 1 ;
        int size = adjList.get(node).size();
        for( int i = 0 ; i < size ; i++ )
        {
            Node next = adjList.get(node).get(i);
            
            if(VISITED[next.data] == 1)
                continue;

            next.dist = dist + 1 ;

            if(next.dir == 0)
            {
                next.upw = upw + 1 ;
                totalUpward++;
            }

            else
                next.upw = upw;
                
            NODES[next.data] = new Node(next.data,next.dir,next.dist,next.upw); 
            
            dfs(next.data , next.dist , next.upw);
        }
    }
}
class Node
{
    int data , dir , dist , upw;
    public Node( int data ,int dir , int dist , int upw)
    {
        this.data = data;
        this.dir = dir;
        this.dist = dist;
        this.upw = upw;
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
