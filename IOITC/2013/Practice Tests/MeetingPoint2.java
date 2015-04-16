import java.util.*;
import java.io.*;
class MeetingPoint2
{
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(1000001);
    static long zeroes = 0 , ones = 0 , ans = 0 , V = 0;
    static int N,M;
    static int LABEL [] = new int [1000000];
    static int visited [] = new int [1000000];
    static boolean specialCyclePresent = false;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for(int i=0;i<=N;i++) 
            adj.add(new ArrayList<Integer>());
        for(int i=0;i<M;i++)
        {
            int u = sc.nextInt() , v = sc.nextInt() ;
            adj.get(u).add(v); 
            adj.get(v).add(u);
        }
        for(int i=0;i<N;i++)
        {
            if(visited[i]==0)
            {
                dfs(i,0);
                if(specialCyclePresent)
                    ans+=(V*V);
                else
                    ans +=(zeroes*zeroes)+(ones *ones);
            }
            zeroes = 0;
            ones = 0;
            specialCyclePresent = false;
            V = 0;
        }
        System.out.println(ans);
    }

    static void dfs(int node , int lbl)
    {
        visited[node] = 1;
        LABEL[node] = lbl; 
        V++;
        if(lbl==0)zeroes++;
        else ones++;
        int sz = adj.get(node).size();
        for(int i=0;i<sz;i++)
        {
            int v = adj.get(node).get(i);
            if(visited[v] == 1 && LABEL[v] == lbl)
                specialCyclePresent = true;
            else if(visited[v] == 0)
                dfs(v , 1-lbl);
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