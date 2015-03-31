import java.util.*;
import java.io.*;
class CountPrimesLazyPropagation
{
    static int flip [] = new int [50000];
    static int segmentTree[]=new int [50000];
    static int sieve[] = new int [1000001];
    static int a[]=new int[10001];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        Arrays.fill(sieve,1);
        sieve[1]=0;
        int limit = (int)Math.sqrt(1000001);
        for(int i=2;i<=limit;i++)
        {
            if(sieve[i]==1)
            {
                for(int j=i+i;j<1000001;j+=i)
                    sieve[j]=0;
            }
        }
        int T = sc.nextInt();
        StringBuilder op = new StringBuilder();
        while(T-->0)
        {
            Arrays.fill(flip,-1);
            int N = sc.nextInt();
            int Q = sc.nextInt();
            for(int i=1;i<=N;i++)
                a[i]=sc.nextInt();
            build(1,1,N);
            while(Q-->0)
            {
                int type = sc.nextInt();
                int l = sc.nextInt();
                int r = sc.nextInt();
                if(type==1)
                    op.append(query(1,1,N,l,r)).append("\n");
                else
                    update(1,1,N,l,r,sieve[sc.nextInt()]);
            }
        }
        System.out.println(op);
    }

    static void build(int node , int l , int r)
    {
        if(l==r)
        {
            segmentTree[node]=sieve[a[l]];
            return;
        }
        int mid = (l+r)>>1; int lc = node*2; int rc = lc+1;
        build(lc,l,mid);
        build(rc,mid+1,r);
        segmentTree[node]=segmentTree[lc]+segmentTree[rc];
    }

    static void update(int node , int l , int r , int qs , int qe , int amount)
    {
        if(flip[node]!=-1)
        {
            segmentTree[node]=flip[node]*(r-l+1);  // Either all in the range will be zero or all will be 1
            if(l!=r) // Transfer "Needs to be Updated" status to children
            {
                flip[node*2]=flip[node];
                flip[node*2+1]=flip[node];
            }
            flip[node]=-1; // Reset status as already updation is done
        }
        if(l>qe || r<qs)
            return;
        int mid = (l+r)/2; int lc = node*2; int rc = lc+1;
        if(l>=qs && r<=qe)
        {
            segmentTree[node] = (r-l+1)*amount; // amount is 0 or 1 so all values in the range will be 1 or 0
            if(l!=r) // If not a leaf node "transfer needs to be updated" status
            {
                flip[lc]=amount;
                flip[rc]=amount;
            }
            return;//Important
        }
        update(lc,l,mid,qs,qe,amount);
        update(rc,mid+1,r,qs,qe,amount);
        segmentTree[node]=segmentTree[lc]+segmentTree[rc];
    }

    static int query(int node , int l , int r , int qs , int qe)
    {
        if(flip[node]!=-1) // Needs to be Updated!
        {
            segmentTree[node]=(r-l+1)*flip[node]; // All values in the range will be 1 or 0 
            if(l!=r)//If not a leaf node , transfer lazy status to children
            {
                flip[node*2]=flip[node];
                flip[node*2+1]=flip[node];
            }
            flip[node]=-1; // Reset needs to be updated status
        }
        if(l>qe || r<qs)
            return 0;
        if(l>=qs && r<=qe)
            return segmentTree[node];
        return query(node*2,l,(l+r)/2,qs,qe)+query(node*2+1,((l+r)/2)+1,r,qs,qe);
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

