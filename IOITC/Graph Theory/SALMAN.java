import java.util.*;
import java.io.*;
class SALMAN    
{
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
    static int arr [] = new int [100001];
    static int buildArray [] = new int [100001];
    static long segTree [] = new long[400004];
    static int segTree2[] = new int[400004];
    static long lazyTree[] = new long[400004];
    static int lazyTree2[]= new int[400004];
    static int start [] = new int  [100001];
    static int finish [] =new int  [100001];
    static int N , Q , time;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        for(int i=0;i<=100000;i++)
            adjList.add(new ArrayList<Integer>());
        for(int t = 1 ; t<= T ; t++)
        {
            System.out.print("Case "+t+":\n");
            N = sc.nextInt();
            Q = sc.nextInt();
            for(int i=0;i<=N;i++)adjList.get(i).clear();
            time = 0;
            for(int i = 1 ; i<= N ; i++)
            {
                int parent = sc.nextInt() , val = sc.nextInt();
                adjList.get(parent).add(i);
                arr[i] = val;
            }
            dfs(1);
            build(1,time,1);
            while(Q-->0)
            {
                int type = sc.nextInt() , node = sc.nextInt();
                if(type==1)
                {
                    System.out.println(sumQuery(1,time,1,start[node],finish[node]));
                }
                else
                {
                    int mx = minQuery(1,time,1,start[node],finish[node]);
                    mx = Math.min(mx,1000);
                    update(1,time,1,start[node],finish[node],mx);
                }
            }
        }
    }
    static long sumQuery(int l , int r , int node , int qs , int qe)
    {
        if(lazyTree[node]!=0)
        {
            segTree[node] += (long)(r-l+1) * lazyTree[node];
            if(l!=r)
            {
                lazyTree[node*2]   += lazyTree[node];
                lazyTree[node*2+1] += lazyTree[node];
            }
            lazyTree[node] = 0;
        }
        if(l>qe || r < qs)
        return 0;
        if(l>=qs && r<=qe)
        return segTree[node];
        int mid = (l+r)>>1;
        return sumQuery(l,mid,node*2,qs,qe)+sumQuery(mid+1,r,node*2+1,qs,qe);
    }
    static int minQuery(int l , int r ,int node , int qs , int qe)
    {
        if(lazyTree2[node]!=0)
        {
            segTree2[node] += lazyTree2[node];
            segTree2[node] = Math.min(segTree2[node] , 1000);
            if(l!=r)
            {
                lazyTree2[node*2]   += lazyTree2[node];
                lazyTree2[node*2+1] += lazyTree2[node];
            }
            lazyTree2[node] =0;
        }
        if(l>qe || r < qs)
        return 1000;
        if(l>=qs && r<=qe)
        return segTree2[node];
        int mid = (l+r)>>1;
        return Math.min(minQuery(l,mid,node*2,qs,qe),minQuery(mid+1,r,node*2+1,qs,qe));
    }
    static void build(int l , int r , int node)
    {
        lazyTree[node] = 0;
        lazyTree2[node] = 0;
        if(l==r)
        {
            segTree[node] = (long)arr[buildArray[l]];
            segTree2[node] =arr[buildArray[l]];
            return;
        }
        int mid = (l+r)>>1;
        int lc = node<<1; 
        int rc = lc+1;
        build(l,mid,lc);
        build(mid+1,r,rc);
        segTree[node] = segTree[lc] + segTree[rc];
        segTree2[node] = Math.min(segTree2[lc],segTree2[rc]);
    }
    static void update(int l , int r , int node , int qs , int qe , int val)
    {
        
        if(lazyTree[node]!=0)
        {
            segTree[node]+=(long)1 * lazyTree[node]*(long)(r-l+1);
            if(l!=r)
            {
                int lc = node<<1;
                int rc = lc+1;
                lazyTree[lc]+=lazyTree[node];
                lazyTree[rc]+=lazyTree[node];
            }
            lazyTree[node]=0;   
        }
        
        if(lazyTree2[node]!=0)
        {
            segTree2[node]+=lazyTree2[node];
            segTree2[node]=Math.min(segTree2[node],1000);
            if(l!=r)
            {
                int lc = node<<1;
                int rc = lc+1;
                lazyTree2[lc]+=lazyTree2[node];
                lazyTree2[rc]+=lazyTree2[node];
            }
            lazyTree2[node] = 0;
        }
        
        if(l>=qs && r<=qe)
        {
            segTree[node]+=(r-l+1)*val;
            if(l!=r)
            {
                lazyTree[node*2]  += val;
                lazyTree[node*2+1]+= val; 
            }
            segTree2[node]+=val;
            segTree2[node]=Math.min(segTree2[node],1000);
            if(l!=r)
            {
                lazyTree2[node*2]  += val;
                lazyTree2[node*2+1]+= val;
            }           
            return;
        }
        int mid = (l+r)>>1;
        int lc = node<<1;
        int rc = lc+1;
        update(l,mid,lc,qs,qe,val);
        update(mid+1,r,rc,qs,qe,val);
        segTree[node]  = segTree[lc] + segTree[rc];
        segTree2[node] = Math.min(segTree2[lc] , segTree2[rc]);
    }   

    static void dfs(int node)
    {
        start [ node ] = ++time;
        buildArray [ time ] = node;
        for(int next : adjList.get(node))
            dfs(next);
        finish [ node ] = time;
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