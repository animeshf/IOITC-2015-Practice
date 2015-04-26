import java.util.*;
import java.io.*;
class On_Changing_Tree
{
    static int N , Q ;
    static long BIT [][] = new long [2][300010];
    static long MOD = 1000000007;
    static ArrayList<ArrayList<Integer>> adjList =  new ArrayList<ArrayList<Integer>>();
    static int start[] =new int [300010];
    static int finish[]=new int [300010];
    static int timer = 0 ;
    static int level[] =new int [300010];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        for(int i=0;i<=300010;i++)adjList.add(new ArrayList<Integer>());
        for(int i=2;i<=N;i++)
        {
            int par = sc.nextInt();
            adjList.get(par).add(i);
        }
        dfs(1,0);
        Q = sc.nextInt();
        StringBuilder output = new StringBuilder();
        while(Q-->0)
        {
            int type = sc.nextInt();
            int v = sc.nextInt();
            if(type==1)
            {
                long x = sc.nextLong();
                long k = sc.nextLong();
                update(0,x+k*level[v],start[v]);
                update(0,-x-k*level[v],finish[v]+1);
                update(1,k,start[v]);
                update(1,-k,finish[v]+1);
            }
            else
            {
                long v1 = query(0,start[v]);
                long v2 =  mod(query(1,start[v])*level[v]);
                long ans = mod(v1-v2);
                output.append(ans).append("\n");
            }
        }
        System.out.println(output);
    }
    static long mod(long x)
    {
        x%=MOD;
        while(x<0)x+=MOD;
        return x;
    }
    static void update(int id , long val , int idx)
    {
        for(;idx<=N;idx+=idx&-idx)
            BIT[id][idx]=mod(BIT[id][idx]+val);
    }

    static long query(int id , int idx)
    {
        long sum = 0;
        for(;idx>0;idx-=idx&-idx)
        sum=mod(sum+BIT[id][idx]);
        return sum;       
    }

    static void dfs(int node , int lvl)
    {
        start[node] = ++timer;
        level[node] = lvl;
        for(int next: adjList.get(node))    
            dfs(next,lvl+1);
        finish[node] = timer;    
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