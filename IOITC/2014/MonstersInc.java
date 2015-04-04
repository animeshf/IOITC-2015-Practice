import java.io.*;
import java.util.*;
class MonstersInc
{
    static tree col [] =  new tree[2006];
    static ArrayList<ArrayList<Integer>> fac = new ArrayList<ArrayList<Integer>>();
    static int mod = 1000000007;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        for(int i=0;i<2005;i++)
        {
            col[i]=new tree();
            fac.add(new ArrayList<Integer>());
        }
        factorize();
        int N = sc.nextInt();
        int M = sc.nextInt();
        int Q = sc.nextInt();
        ArrayList<ArrayList<Integer>> values = new ArrayList<ArrayList<Integer>>();
        for(int i=1;i<=2005;i++)
            values.add(new ArrayList<Integer>());

        for(int i=1;i<=N;i++)
        {
            for(int j=1;j<=M;j++)
            {
                int val = sc.nextInt();
                int nos = i+j;
                col[nos].size++;
                values.get(nos).add(val);
            }
        }

        for(int i=2;i<=N+M;i++)
        {
            int sz = col[i].size;
            ArrayList<Integer> lis = values.get(i);
            Build(col[i].segTree,1,1,sz,lis);
        }
        PrintWriter out = new PrintWriter(System.out);
        while(Q-->0)
        {
            int type = sc.nextInt();
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int k = sc.nextInt();
            if(type==0)
            {
                int inflation = sc.nextInt();
                int sz = fac.get(k).size();
                for(int i=0;i<sz;i++)
                {
                    int x = fac.get(k).get(i);
                    int L = getLeft(x1,y1,x2,y2,x,M);
                    int R = getRight(x1,y1,x2,y2,x,M);
                    if(L<=0||R<=0||R<L||R>col[x].size)
                        continue;
                    update(col[x].segTree,col[x].lazyTree,1,1,col[x].size,L,R,inflation);
                } 
            }
            else
            {
                int sz = fac.get(k).size();
                long ans=0;
                if(k<2)
                {
                    out.println("0");
                    continue;
                }
                for(int i=0;i<sz;i++)
                {
                    int x = fac.get(k).get(i);
                    int L = getLeft(x1,y1,x2,y2,x,M);
                    int R = getRight(x1,y1,x2,y2,x,M);
                    if(L<=0||R<=0||R<L||R>col[x].size)
                        continue;
                    ans+=query(col[x].segTree,col[x].lazyTree,1,1,col[x].size,L,R);
                    ans%=mod;
                }
                out.println(ans);
                out.flush();
            }
        }
        out.close();
    }

    static int getLeft(int x1, int y1, int x2, int y2, int index,int m)
    {
        int l;
        if(x1 + y1 > index || x2 + y2 < index)
            l=0;
        else if(index - x1 <= y2)
        {
            if(index <= 1 + m)  
                l = x1;
            else 
                l = m - (index - x1) + 1;
        }
        else
        {
            if(index <= 1 + m)  
                l = index - y2;
            else 
                l = m - y2 + 1;
        }
        return l;
    }

    static int getRight(int x1,int y1,int x2,int y2,int index, int m)
    {
        int r;
        if(index - y1 <= x2)
        {
            if(index <= 1 + m)  
                r = index - y1;
            else
                r = m - y1 + 1;
        }
        else
        {
            if(index <= 1 + m)  
                r = x2;
            else
                r = m - (index - x2) + 1;
        }
        return r;
    }

    static void update(long[]segtree,long[]lazytree,int node , int l , int r , int qs , int qe ,int val)
    {
        if(lazytree[node]!=0)
        {
            segtree[node]+=(r-l+1)*lazytree[node];
            segtree[node]%=mod;
            if(l!=r)
            {
                lazytree[node*2]+=lazytree[node];
                lazytree[node*2]%=mod;
                lazytree[node*2+1]+=lazytree[node];
                lazytree[node*2+1]%=mod;
            }
            lazytree[node]=0;    
        }
        if(l>qe || r<qs)
            return;
        if(l>=qs && r<=qe)
        {
            segtree[node]+=((long)(r-l+1))*val;
            segtree[node]%=mod;
            if(l!=r)
            {
                lazytree[node*2]+=val;
                lazytree[node*2]%=mod;
                lazytree[node*2+1]+=val;
                lazytree[node*2+1]%=mod;
            }
            return;
        }
        int mid = (l+r)/2;
        int lc = node*2; int rc = lc+1;     
        update(segtree,lazytree,lc,l,mid,qs,qe,val);
        update(segtree,lazytree,rc,mid+1,r,qs,qe,val);
        segtree[node]=segtree[lc]+segtree[rc];
        segtree[node]%=mod;
    }

    static long query(long segtree[], long lazytree[], int node , int l , int r, int qs , int qe)
    {
        if(lazytree[node]!=0) 
        { 
            segtree[node]+=(r-l+1)*lazytree[node]; 
            segtree[node]%=mod;
            if(l!=r)
            { 
                lazytree[node*2]+=lazytree[node];
                lazytree[node*2]%=mod;
                lazytree[node*2+1]+=lazytree[node];
                lazytree[node*2+1]%=mod;
            }
            lazytree[node]=0;
        }
        if(l>qe||r<qs) 
            return 0;
        if(l>=qs&&r<=qe)
            return segtree[node];
        int mid = (l+r)/2;
        return 
        (query(segtree,lazytree,node*2,l,mid,qs,qe)+ query(segtree,lazytree,node*2+1,mid+1,r,qs,qe)) %mod;
    }

    static void Build(long[]segtree,int node ,int l , int r , ArrayList<Integer> x)
    {
        if(l==r)
        {
            segtree[node]=x.get(l-1);
            segtree[node]%=mod;
            return;
        }
        int lc = node*2;
        int rc = lc+1;
        int mid = (l+r)/2;
        Build(segtree,lc,l,mid,x);
        Build(segtree,rc,mid+1,r,x);
        segtree[node]=segtree[lc]+segtree[rc];
        segtree[node]%=mod;
    }

    static void factorize()
    {
        for(int i=2;i<=2000;i++)
        {
            for(int j=2;j<=i;j++)
            {
                if(i%j==0)
                    fac.get(i).add(j);
            }
        }
    }
}
class tree
{
    long segTree[]=new long[5000];
    long lazyTree[]=new long[5000];
    int size=0;
    public tree()
    {
        Arrays.fill(segTree,0);
        Arrays.fill(lazyTree,0);
        size=0;
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