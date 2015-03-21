/*
 * SPOJ ORDERSET = http://www.spoj.com/problems/ORDERSET/
 * Queries of 2 types :
 * 1.) Number of elements less than X
 * 2.) Kth smallest element in an array
 * 
 * Prerequisites : Segment Tree , Co-ordinate Compression
 * 
 * Coded by Animesh Fatehpuria
 */
import java.util.*;
import java.io.*;
class OrderStatistics
{
    static int segTree[]=new int[800002];
    static char type[]=new char[200002];
    static int input[]=new int[200002];
    static int vals[]=new int[200002];
    static int original[]=new int[200002];
    static Map<Integer,Integer> compress = new HashMap<Integer , Integer>();
    static int k=0;
    public static void main (String[]args) 
    {
        InputReader1 sc = new InputReader1(System.in);
        int n = sc.nextInt();
        int temp=-1;
        for(int i=1;i<=n;i++)
        {
            String x = sc.next();
            char lol=x.charAt(0);
            type[i]=lol;
            temp=sc.nextInt();
            input[i]=temp;
            if(lol=='I'||lol=='C'||lol=='D')
                vals[k++]=temp;
        } 
        Arrays.sort(vals,0,k);
        Arrays.fill(original,-1000000009);
        for(int i=0;i<k;i++)
        {
            compress.put(vals[i],i+1);
            original[i+1]=vals[i];
        }
        StringBuilder ans = new StringBuilder();
        for(int i=1;i<=n;i++)
        {
            if(type[i]=='I')
            {
                int val=compress.get(input[i]);
                update(0,200000,1,val,1);
            }
            else if(type[i]=='D')
            {
                int val=compress.get(input[i]);
                update(0,200000,1,val,0);
            }
            else if(type[i]=='C')
            {
                int val=compress.get(input[i]);
                int temrp = query(0,200000,1,0,val-1);
                ans.append(temrp).append("\n");
            }
            else if(type[i]=='K')
            {
                int val=input[i];
                int l = kthSmall(0,200000,1,val);
                if(original[l]==-1000000009)
                    ans.append("invalid\n");
                else
                {
                    ans.append(original[l]).append("\n");
                }
            }
        }
        System.out.println(ans);
    }
    static void update(int l , int r , int node , int pos, int val)
    {
        if(l==r)
        {
            segTree[node]=val;
        }
        else
        {
            int mid = (l+r)/2;
            int leftChild=node*2;
            int rightChild = leftChild+1;
            if(mid>=pos)
            update(l,mid,leftChild,pos,val);
            else
            update(mid+1,r,rightChild,pos,val);
            segTree[node]=segTree[leftChild]+segTree[rightChild];
        }
    }
    static int kthSmall(int l , int r , int node , int pos)
    {
        if(l==r)
        return r;
        else
        {
            int mid=(l+r)/2;
            int lc = 2*node;
            int rc=lc+1;
            if(segTree[lc]>=pos)
            return kthSmall(l,mid,lc,pos);
            else
            return kthSmall(mid+1,r,rc,pos-segTree[lc]);
        }
    }
    static int query ( int l , int r , int node , int qs , int qe)
    {
        if(qs > r || qe < l)
        return 0;
        else if(l>=qs && r<=qe)
        return segTree[node];
        else
        {
            int mid = (l+r)/2;
            int leftChild=node*2;
            int rightChild=leftChild+1;
            return query(l,mid,leftChild,qs,qe)+query(mid+1,r,rightChild,qs,qe);
        }
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

