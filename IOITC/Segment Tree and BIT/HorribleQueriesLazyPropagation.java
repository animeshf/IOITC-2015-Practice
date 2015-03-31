import java.util.*;
import java.io.*;
class HorribleQueriesLazyPropagation
{ 
    static long segTree[]=new long[400004];
    static long lazyTree[]=new long[400004];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        StringBuilder ans = new StringBuilder();
        while(T-->0)
        { 
            int N = sc.nextInt(), Q =sc.nextInt();
            Arrays.fill(segTree,0);
            Arrays.fill(lazyTree,0);
            while(Q-->0)
            {
                int type = sc.nextInt();
                if(type==0)
                {
                    int l = sc.nextInt();
                    int r = sc.nextInt();
                    long val= sc.nextLong();
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
        }
        System.out.println(ans);
    }

    static void update(int l,int r,int node,int ql,int qr,long val)
    { 
        if(lazyTree[node]!=0) // Hence the node needs to be updated
        {
            segTree[node]+=(r-l+1)*lazyTree[node]; // Update it
            if(l!=r) // Transfer "Needs to Be Updated" status to the children (if any)
            {
                lazyTree[node*2+1]+=lazyTree[node];
                lazyTree[node*2+2]+=lazyTree[node];
            }
            lazyTree[node]=0;     // Reset "Needs to be Updated status" to zero
        }
        if(l>qr||r<ql) // Outside Range
        {
            return;
        }
        if(l>=ql && r<=qr) // Entirely within the range
        {
            segTree[node]+=(r-l+1)*val; // Update the range value by adding (range length) * increm
            if(l!=r) // If not leaf node
            {
                lazyTree[node*2+1] += val; // Mark Children to be Updated by same increm
                lazyTree[node*2+2] += val; // Mark Children to be Updated by same increm
            }
            return; // Important
        }
        int mid = (l+r)>>1;
        update(l,mid,node*2+1,ql,qr,val);
        update(mid+1,r,node*2+2,ql,qr,val);
        segTree[node]=segTree[node*2+1]+segTree[node*2+2]; // Normal
    }

    static long sum(int l,int r,int node,int ql,int qr)
    {
        if(lazyTree[node]!=0) // Needs to be Updated
        { 
            segTree[node]+=(r-l+1)*lazyTree[node]; // Update value of node by adding increment
            if(l!=r) // If not a leaf
            { 
                lazyTree[node*2+1]+=lazyTree[node];// Transfer "Needs to Be Updated" status to the children 
                lazyTree[node*2+2]+=lazyTree[node];
            }
            lazyTree[node]=0;;//Reset 
        }
        if(l>qr||r<ql) // If outside range entirely!
        {
            return 0;
        }
        if(l>=ql&&r<=qr) // If entirely within range , return value
        {
            return segTree[node];
        }
        int mid = (l+r)>>1;
        return sum(l,mid,node*2+1,ql,qr) + sum(mid+1,r,node*2+2,ql,qr);
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

