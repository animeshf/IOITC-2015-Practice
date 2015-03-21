import java.util.*;
import java.io.*;
class INTERSECTIONS
{
    static int segTree[]=new int[4000001];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        while(T-->0)
        {
            int N = sc.nextInt();
            int a[]=new int[N];
            for(int i=0 ; i<N ;i++)
            {
                a[i]=sc.nextInt();
            }
            Arrays.fill(segTree,0);
            long ans=0;
            for(int i=N-1;i>=0;i--)
            {
                ans+=query(1,1,N,1,a[i]-1);
                update(a[i],1,1,N);
            }
            System.out.println(ans);
        }
    }

    static void update(int val ,int node , int from , int to )
    {
        if(from==to)
        {
            segTree[node]++;
        }
        else
        {
            int mid = (from+to)/2;
            int leftChild = node*2;
            int rightChild=leftChild+1;
            if(val <= mid)
                update(val,leftChild,from,mid);
            else
                update(val,rightChild,mid+1,to);
            segTree[node]=segTree[leftChild]+segTree[rightChild];
        }
    }

    static int query ( int node , int from , int to , int qs , int qe )
    {
        if(qs > to || qe <from)
            return 0;
        if(from>=qs &&  to <=qe)
            return segTree[node];
        int leftChild=node*2;
        int rightChild=leftChild+1;
        int mid = (from+to)/2;
        return query(leftChild , from , mid , qs , qe)+ query(rightChild,mid+1,to,qs,qe);
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
