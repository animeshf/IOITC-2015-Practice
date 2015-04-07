import java.util.*;
import java.io.*;
class Compilation
{
    static int N;
    static int T[]=new int[100010];
    static int F=1;
    static int S[]=new int[100010];
    static long forward[]=new long[100010];
    static long upto[]=new long[100010];
    static ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
    static ArrayList<ArrayList<Integer>> in = new ArrayList<ArrayList<Integer>>();
    static int inDegree[]=new int[100010];
    static long minTime=0;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N=sc.nextInt();
        int M = sc.nextInt();
        for(int i=0;i<=N;i++)
        {
            out.add(new ArrayList<Integer>());
            in.add(new ArrayList<Integer>());
        }
        for(int i=1;i<=N;i++)
            S[i]=sc.nextInt();
        for(int i=0;i<M ;i++)
        {
            int u = sc.nextInt();
            int v = sc.nextInt();
            out.get(u).add(v);
            in.get(v).add(u);
            inDegree[v]++;
        }
        for(int i=1;i<=N;i++)
            if(inDegree[i]==0)T[F++]=i;
        Toposort();
        PreProcess();
        int Q = sc.nextInt();
        System.out.println(minTime);
        StringBuilder op = new StringBuilder();
        while(Q-->0)
        {
            int job = sc.nextInt();
            int inflation = sc.nextInt();
            op.append(Math.max(minTime,forward[job]+upto[job]+inflation)).append("\n");
        }
        System.out.println(op);
    }

    static void Toposort()
    {
        int Lo=1;

        while(Lo<F) 
        {
            int node = T[Lo];
            int size = out.get(node).size();
            for(int i=0;i<size;i++)
            {
                int nextNode = out.get(node).get(i);
                inDegree[nextNode]--;
                if(inDegree[nextNode]==0)
                    T[F++]=nextNode;
            }
            Lo++;
        }

    }

    static void PreProcess()
    {
            for(int i=N;i>=1;i--)
            {
                int node = T[i];
                forward[node]=S[node];
                int sz = out.get(node).size();
                for(int j=0;j<sz;j++)
                {
                    int next=out.get(node).get(j);
                    forward[node]=Math.max(forward[node],forward[next]+S[node]);
                }
                minTime= Math.max(minTime , forward[node]);
            }

            for(int i=1;i<=N;i++)
            {
                int node = T[i];
                upto[node]=0;
                int sz = in.get(node).size();
                for(int j=0;j<sz;j++)
                {
                    int next=in.get(node).get(j);
                    upto[node]=Math.max(upto[node],upto[next]+S[next]);
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

