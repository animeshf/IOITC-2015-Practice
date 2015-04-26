import java.io.*;
import java.util.*;
class InterestingArray
{
    static int MAXN = 111101;
    static int LOGN = 32 , INF = Integer.MAX_VALUE;
    static int X[][] = new int[LOGN][MAXN];
    static int ST[] = new int[MAXN*4];
    static int A[] = new int[MAXN];
    static int L[]=new int[MAXN] , R[]=new int[MAXN] , Q[]=new int[MAXN];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N=sc.nextInt() , M = sc.nextInt();
        for(int j=0;j<M;j++)
        {
            int l =sc.nextInt() , r = sc.nextInt() , val=sc.nextInt();
            L[j] = l ; R[j] = r ; Q[j] = val ;
            for(int i=0;i<LOGN;i++)
            {
                if((val&(1<<i))>0)
                {
                    X[i][l]++;
                    X[i][r+1]--;
                }
            }
        }
        for(int i=0;i<LOGN;i++)
            for(int j=1;j<=N;j++)
                X[i][j] += X[i][j-1];
        for(int j=1;j<=N;j++)
        {
            for(int i=0;i<LOGN;i++)
                if(X[i][j]>0)
                    A[j]+=(1<<i);
        }
        build(1,1,N);
        boolean pos=true;
        for(int j=0;j<M;j++)
        {
            int ans = query(1,1,N,L[j],R[j]);
            if(ans!=Q[j])
            {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
        for(int i=1;i<=N;i++)System.out.print(A[i]+" ");
    }

    static void build(int node , int l , int r)
    {
        if(l==r)
        {
            ST[node] = A[l];
            return;
        }
        int mid = (l+r)/2 ;
        build(node*2 , l , mid);
        build(node*2+1,mid+1,r);
        ST[node] = ST[node*2] & ST[node*2+1];
    }

    static int query(int node , int l , int r , int qs , int qe)
    {
        if(qe < l || r < qs) return INF;
        if(qs<=l && r<=qe) return ST[node];
        int mid = (l+r)/2;
        if (qe <= mid)
            return query(2 * node, l, mid, qs, qe);
        if (mid < qs)
            return query(2 * node + 1, mid + 1, r, qs, qe);
        return query(2 * node, l, mid, qs, qe) & query(2 * node + 1, mid + 1, r, qs, qe);
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

    