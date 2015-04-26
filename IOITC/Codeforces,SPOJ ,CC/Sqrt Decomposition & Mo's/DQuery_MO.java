import java.io.*;
import java.util.*;
class DQuery_MO
{
    static int a[] = new int[30010];
    static query Q[]=new query[200010];
    static int ans[] = new int[200010];
    static int c[] = new int [1000010];
    static int cans , n , sqn ,q;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        n = sc.nextInt();
        for(int i=1;i<=n;i++) 
        a[i] = sc.nextInt();
        sqn = (int)Math.sqrt(n);
        q = sc.nextInt();
        for(int i=0;i<q;i++)
        {
            int l = sc.nextInt() , r = sc.nextInt();
            Q[i] = new query(l,r,i,l/sqn);
            // l/sqn signifies the block in which the current query will go to..
        }
        Arrays.sort(Q,0,q);
        int cl=0 ,cr=0;
        for(int i=0;i<q;i++)
        {
            while(Q[i].l>cl) // if cl pointer out of range of query L , rem cur index value and increment pointer
            {
                rem(cl);
                cl++;
            }

            while(Q[i].l<cl) // If query L value < than cl pointer value , add (cur value-1) and decrement pointer
            {
                // we add cl-1 because cl is already added once
                add(cl-1);
                cl--;
            }

            while(Q[i].r>cr) // If query R value > cr pointer value  , add (cur value+1) and increment  pointer
            {
                // we added cr+1 because cr is already added once
                add(cr+1);
                cr++;
            }

            while(Q[i].r<cr) // If query R value is lesser than cr pointer , then remove cr and decrement pointer
            {
                rem(cr);
                cr--;
            }
            // Now cl = L and cr= R for current query
            ans[Q[i].pos]=cans; // cans is globally updated
            // It can be proved that cl,cr wont move more than ROOT N times for each query
        }
        StringBuilder op = new StringBuilder();
        for(int i=0;i<q;i++)
            op.append(ans[i]).append("\n");
        System.out.print(op);    
    }

    static void rem(int i)
    {
        if(--c[a[i]]==0)   cans--;
    }

    static void add(int i)
    {
        if(++c[a[i]]==1)cans++;
    }
}
class query implements Comparable<query>
{
    int l , r , pos , bl;
    public query(int l , int r, int pos , int bl)
    {
        this.l = l;
        this.r =r;
        this.pos=pos; // query number
        this.bl=bl; // block number
    }

    public int compareTo(query q)
    {
        if(this.bl!=q.bl) return this.bl - q.bl; // sort first based on block number
        else return this.r - q.r; // if block number is same , sort on basis of query END Value
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