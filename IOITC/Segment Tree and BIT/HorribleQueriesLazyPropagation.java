import java.util.*;
import java.io.*;
class HorribleQueriesLazyPropagation
{ 
    static long segTree[]=new long[500001];
    static long lazyTree[]=new long[500001];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        while(T-->0)
        {
            int N = sc.nextInt(), Q =sc.nextInt();
            Arrays.fill(segTree,0);
            Arrays.fill(lazyTree,0);
            StringBuilder ans = new StringBuilder();
            while(Q-->0)
            {
                int type = sc.nextInt();
                if(type==0)
                {
                    int l = sc.nextInt();
                    int r = sc.nextInt();
                    int val= sc.nextInt();
                    update(0,N-1,1,l-1,r-1,val);
                }
                else
                {
                    int l = sc.nextInt();
                    int r = sc.nextInt();
                    long temp = sum(0,N-1,1,l-1,r-1);
                    ans.append(temp).append("\n");
                }
            }
            System.out.println(ans);
        }
    }
    
    static long update(int l,int r,int node,int ql,int qr,int val)
    {
        if(l>qr||r<ql)
        {
            return 0;
        }
        if(l>=ql&&r<=qr)
        {
            lazyTree[node]+=(long)val;
            long range = r-l+1;
            return (range * (long) val);
        }
        int mid = (l+r)>>1;
        long temp = update(l,mid,node*2+1,ql,qr,val)+update(mid+1,r,node*2+2,ql,qr,val);
        segTree[node]+=temp;
        return temp;
    }

    static long sum(int l,int r,int node,int ql,int qr)
    {
        if(l>qr||r<ql)
        {
            return 0;
        }
        if(l>=ql&&r<=qr)
        {
            long range = r-l+1;
            return segTree[node] + range * lazyTree[node];
        }
        children(l,r,node);
        int mid = (l+r)>>1;
        return sum(l,mid,node*2+1,ql,qr) + sum(mid+1,r,node*2+2,ql,qr);
    }

    static void children(int l,int r,int node)
    {
        lazyTree[node*2+1]+=lazyTree[node];
        lazyTree[node*2+2]+=lazyTree[node];
        long range = r-l+1;
        segTree[node]+= range * lazyTree[node];
        lazyTree[node]=0;
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

