import java.util.*;
import java.io.*;
class IsItATree
{
    static int id[]=new int[10001];
    static int sz[]=new int[10001];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        boolean tree=true;
        for(int i=0;i<=N;i++)
        {
            id[i]=i;
            sz[i]=1;
        }
        while(M-->0)
        {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(root(a)==root(b))
            {
                tree=false;
                break;
            }
            union(a,b);
        }
        if(tree)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    static int root(int x)
    {
        if(id[x]==x)
            return x;
        else
            return id[x]=root(id[x]);
    }

    static void union(int a , int b)
    {
        if(sz[a] < sz[b])
        {
            id[a]=b;
            sz[b]+=sz[a];
        }
        else
        {
            id[b]=a;
            sz[a]+=sz[b];
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

