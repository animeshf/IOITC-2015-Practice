import java.util.*;
import java.io.*;
class FREQUENT
{
    static int a[]=new int[100001];
    static int segTree[]=new int[800004];
    static int bucket[]=new int[200001];
    static int rightMost[]=new int[200001];
    static int leftMost[]=new int[200001];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        StringBuilder op = new StringBuilder();
        while(true)
        {
            for(int i = 0 ; i<=200000;i++)
            {
                if(i<=100000)a[i]=0;
                bucket[i]=0;rightMost[i]=0;leftMost[i]=0;segTree[i]=0;
            }
            for(int i=200001;i<=800000;i++)
            segTree[i]=0;
            int N = sc.nextInt();
            if(N==0)
            break;
            int Q = sc.nextInt();
            for(int i=1 ; i<=N ; i++)
            {
                a[i] = sc.nextInt();
                a[i]+=100000;
                bucket[a[i]]++;
                if(leftMost[a[i]]==0)
                    leftMost[a[i]]=i;
                rightMost[a[i]]=Math.max(rightMost[a[i]],i);
            }
            build(1,1,200000);
            while(Q-->0)
            {
                int l = sc.nextInt();
                int r = sc.nextInt();
                int left = a[l];
                int right = a[r];
                if(left == right)
                {
                    op.append(r-l+1).append("\n");
                    continue;
                }
                int rightMostofLeft = rightMost[left];
                int leftMostofRight = leftMost[right];
                int ans = Math.max(rightMostofLeft - l+1 ,r - leftMostofRight+1);
                ans=Math.max(ans,query(1,1,200000,left+1,right-1));
                op.append(ans).append("\n");
            }
        }
        System.out.println(op);
    }	

    static void build(int node , int l , int r)
    {
        if(l==r)
        {
            segTree[node]=bucket[l];
            return;
        }
        int lc = node*2; int rc = lc+1; int mid = (l+r)/2;
        build(lc,l,mid);
        build(rc,mid+1,r);
        segTree[node]=Math.max(segTree[lc],segTree[rc]);
    }

    static int query(int node , int l , int r , int qs , int qe)
    {
        if(l>=qs&&r<=qe)
            return segTree[node];
        if(l>qe || r<qs)
            return 0;
        int lc = node*2; int rc = lc+1; int mid = (l+r)/2;
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

