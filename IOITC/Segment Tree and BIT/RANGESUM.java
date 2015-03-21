/**
 * Problem - RANGESUM
 * SPOJ - SEGMENT TREE IMPLEMENTATION
 * NUMBER OF NODES IN SEGMENT TREE ISNT FIXED
 * */
import java.util.*;
import java.io.*;
class RANGESUM
{
    static long segTree[]=new long[800000];
    static int index = 100001;
    public static void main ( String [] args )
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        long a[]=new long[index+N];
        Arrays.fill(a,0);
        for(int i=0;i<N;i++)
        {
            a[index+i]=sc.nextInt();
        }
        int rightmostVertex=index+N-1;
        fill(1,0,index+N-1,a);
        int Q = sc.nextInt();
        StringBuilder answer=new StringBuilder();
        while(Q-->0)
        {
            int type=sc.nextInt();
            if(type==1)
            {
                int l  = sc.nextInt();
                int r = sc.nextInt();
                l--;r--;
                long ans =query(1,0,rightmostVertex,l+index,r+index);
                answer.append(ans).append("\n");
            }
            else
            {
                int x = sc.nextInt();
                index--;
                update(1,0,rightmostVertex,x,index);
            }
        }
        System.out.println(answer);
    }
    static void update(int node , int start , int end , long val , int pos)
    {
        if(start==end)
        {
            segTree[node]=val;
            return;
        }
        else
        {
            int lc = node*2;
            int rc=lc+1;
            int mid=(start+end)/2;
            if(mid>=pos)
            update(lc,start,mid,val,pos);
            else
            update(rc,mid+1,end,val,pos);
            segTree[node]=segTree[lc]+segTree[rc];
        }
    }
    static void fill(int node , int start , int end , long a[])
    {
        if(start==end)
        {
            segTree[node]=a[start];
            return;
        }
        int lc = 2*node;
        int rc=lc+1;
        int mid=(start+end)/2;
        fill(lc,start,mid,a);
        fill(rc,mid+1,end,a);
        segTree[node]=segTree[lc]+segTree[rc];
    }
    static long query(int node , int start , int end , int qs , int qe)
    {
        if(qs>end || qe<start)
        return 0;
        else if(start>=qs && end<=qe)
        return segTree[node];
        int lc = node*2;
        int rc=lc+1;
        int mid=(start+end)/2;
        return query(lc,start,mid,qs,qe)+query(rc,mid+1,end,qs,qe);
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
