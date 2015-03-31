import java.io.*;
import java.util.*;
class GSS1
{
    static node SegmentTree[]=new node[200004];
    static int a[]=new int[50001];
    static int N;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        for(int i=1;i<=200003;i++)
        SegmentTree[i]=new node();
        for(int i=1;i<=N;i++)
            a[i] = sc.nextInt();
        build(1,1,N);
        int M =sc.nextInt();
        StringBuilder op = new StringBuilder();
        while(M-->0)
        {
            int left = sc.nextInt();
            int right = sc.nextInt();
            int ans = query(1,1,N,left,right).maxSum;
            op.append(ans).append("\n");
        }
        System.out.println(op);
    }
    
    static node query(int n , int l , int r , int qs , int qe)
    {
        if(l==qs&&r==qe)
        return SegmentTree[n]; 
        int lc = n*2;
        int rc= lc+1;
        int mid=(l+r)/2;
        if(qs>mid)
        return query(rc,mid+1,r,qs,qe);
        if(qe<=mid)
        return query(lc,l,mid,qs,qe);
        node x = query(lc,l,mid,qs,mid);
        node y = query(rc,mid+1,r,mid+1,qe);
        node res=merge(x,y);
        return res;
    }
    
    static void build(int n , int l , int r)
    {
        if(l==r)
        {
            SegmentTree[n].sum=a[l];
            SegmentTree[n].prefixMaxSum=a[l];
            SegmentTree[n].suffixMaxSum=a[l];
            SegmentTree[n].maxSum=a[l]; 
            return;
        }
        int lc = n*2;
        int rc = lc+1;
        int mid = (l+r)/2;
        build(lc,l,mid);
        build(rc,mid+1,r);
        SegmentTree[n]=merge(SegmentTree[lc],SegmentTree[rc]);
    }

    static node merge(node lc , node rc)
    {
        node x=new node();
        x.sum = lc.sum + rc.sum;
        x.prefixMaxSum = Math.max(lc.prefixMaxSum,lc.sum+rc.prefixMaxSum);
        x.suffixMaxSum = Math.max(rc.suffixMaxSum, rc.sum + lc.suffixMaxSum);
        x.maxSum = 
        Math.max(x.prefixMaxSum, Math.max(x.suffixMaxSum, Math.max
        (lc.maxSum, Math.max(rc.maxSum, 
        lc.suffixMaxSum + rc.prefixMaxSum))));
        return x;
    }
}
class node
{
    int sum;
    int prefixMaxSum;
    int suffixMaxSum;
    int maxSum;
    public node()
    {
        sum=-99999999;prefixMaxSum=-99999999;suffixMaxSum=-99999999;maxSum=-99999999;
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

