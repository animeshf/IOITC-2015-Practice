/*
 * SPOJ Light Switching Problem
 */
import java.util.*;
import java.io.*;
class LITESWITCHINGLAZYPROPAGATION
{
    static int lazy [] = new int [400005];
    static int segmentTree[]=new int [400005];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        Arrays.fill(segmentTree,0);
        Arrays.fill(segmentTree,0);
        StringBuilder op = new StringBuilder();
        while(M-->0)
        {
            int type = sc.nextInt(), l = sc.nextInt() , r =sc.nextInt();
            if(type==0)
                update(1,1,N,l,r);
            else
                op.append(query(1,1,N,l,r)).append("\n");
        }
        System.out.println(op);
    }

    static void update(int node , int l , int r , int qs , int qe)
    {
        if(lazy[node]==1) // Needs to be updated
        {
            segmentTree[node] = (r-l+1) - segmentTree[node]; // The Number of Heads gets reversed each time
            // Two reversals == original , hence mark leaf nodes with opposite of what they currently have :D
            if(l!=r) // Not a leaf
            {
                lazy[node*2]=1-lazy[node*2];// reverse the state (dont mark 1 because the same update twice = original status of coins)
                lazy[node*2+1]=1-lazy[node*2+1]; // reverse state
            }
            lazy[node]=0; // reset
        }
        if(l>qe || r<qs)
            return;
        if(l>=qs && r<=qe) // If completely within range
        {
            segmentTree[node] = (r-l+1) - segmentTree[node]; // Change value of range
            if(l!=r)
            {
                lazy[node*2]=1-lazy[node*2]; // Reverse State of Range
                lazy[node*2+1]=1-lazy[node*2+1]; // Reverse State of Range
            }
            return;
        }
        update(node*2,l,(l+r)/2,qs,qe);
        update(node*2+1,(l+r)/2+1,r,qs,qe);
        segmentTree[node]=segmentTree[node*2]+segmentTree[node*2+1];
    }

    static int query(int node , int l , int r ,int qs , int qe)
    {
        if(l>qe||r<qs)
            return 0;
        if(lazy[node]==1)
        {
            segmentTree[node]=(r-l+1)-segmentTree[node]; // Reverse Number of Heads
            if(l!=r)
            {
                lazy[node*2]=1-lazy[node*2]; // Reverse State
                lazy[node*2+1]=1-lazy[node*2+1]; // Reverse State
            }
            lazy[node]=0;
        }
        if(l>=qs && r<=qe)
            return segmentTree[node];
        return query(node*2,l,(l+r)/2,qs,qe)+query(node*2+1,(l+r)/2+1,r,qs,qe);
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

