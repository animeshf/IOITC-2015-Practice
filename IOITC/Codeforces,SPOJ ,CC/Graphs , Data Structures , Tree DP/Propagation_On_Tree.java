import java.util.*;
import java.io.*;
public class Propagation_On_Tree
{
    static int N,M;
    static int A[]=new int[200002];
    static int level[]=new int[200002],start[]=new int[200002],end[]=new int[200002];
    static int BIT[]=new int[200002];
    static int time = 0;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static void update(int pos , int val)
    {
        for(;pos<=200000;pos+=pos&-pos)
            BIT[pos] += val;
    }

    static int query(int pos)
    {
        int sum=0;
        for(;pos>0;pos-=pos&-pos)sum+=BIT[pos];
        return sum;
    }

    static void dfs(int node , int par , int lvl)
    {
        start[node] = ++time;
        level[node] = lvl;
        for(int next : adjList.get(node))
            if(next!=par) dfs(next,node,lvl+1);
        end[node] = time;
    }

    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        for(int i=0;i<=N;i++)adjList.add(new ArrayList<Integer>());
        M = sc.nextInt();
        for(int i=1;i<=N;i++) A[i] = sc.nextInt();
        for(int i=1;i<N;i++)
        {
            int u = sc.nextInt() , v = sc.nextInt();
            adjList.get(u).add(v);
        }
        dfs(1,-1,0);
        while(M-->0)
        {
            int type = sc.nextInt();
            int nod =  sc.nextInt();
            if(type==1)
            {
                int val = sc.nextInt();
                if(level[nod]%2==0)
                {
                    update(start[nod],val);
                    update(end[nod]+1,-val);
                }
                else
                {
                    update(start[nod],-val);
                    update(end[nod]+1,val);
                }
            }
            else
            {
                if(level[nod]%2==0)
                    System.out.println(A[nod]+query(start[nod]));
                else
                    System.out.println(A[nod]-query(start[nod]));
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