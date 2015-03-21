import java.util.*;
import java.io.*;
class Swords
{
    static int segTree[]=new int[400000];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        int T[]=new int[N+1];
        int D[]=new int[N+1];
        for(int i=1;i<=N;i++)
        {
            T[i]=sc.nextInt(); D[i]=sc.nextInt();
        }
        int DP[]=new int[N+2];
        DP[N]=0;
        DP[N+1]=0;
        update(1,1,100001,N+1,N);
        update(1,1,100001,N,N-1);
        for(int i=N-1;i>=1;i--)
            {
                DP[i]=DP[i+1]; 
                int limit=Math.min(100001,i+T[i]+D[i]+1);
                DP[i]=Math.max(DP[i],query(1,1,100001,i+T[i]+2,limit)-i-T[i]);
                update(1,1,100001,i,DP[i]+i-1);
            }
        System.out.println(DP[1]);    
    }
    static void update(int node , int l , int r , int pos , int val)
    {
        if(l==r)
        {
            segTree[node]=val;
            return;
        }
        int mid = (l+r)/2;
        int lc = node *2;
        int rc= lc+1;
        if(mid>=pos)
        update(lc,l,mid,pos,val);
        else
        update(rc,mid+1,r,pos,val);
        segTree[node]=Math.max(segTree[lc],segTree[rc]);
    }
    static int query(int node , int l , int r , int qs , int qe)
    {
        if(l>qe || r<qs)
        return 0;
        else if(l>=qs&&r<=qe)
        return segTree[node];
        int mid = (l+r)/2;
        int lc = node*2;
        int rc=lc+1;
        return Math.max(query(lc,l,mid,qs,qe),query(rc,mid+1,r,qs,qe));
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

