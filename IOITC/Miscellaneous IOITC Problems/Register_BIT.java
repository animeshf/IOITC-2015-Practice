import java.util.*;
import java.io.*;
class Register
{
    static int BIT[]=new int[100001];
    static int IND[]=new int[100001];
    static int POS[]=new int[100001];
    static pair p[]= new pair[1000001];
    static int N,Q;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        for(int i=0;i<=100000;i++)p[i]=new pair();
        N=sc.nextInt();
        Q=sc.nextInt();
        for(int i=1;i<=N;i++)
        {
            p[i].first=sc.nextInt();
            p[i].second=i;
        }
        Arrays.sort(p,1,1+N);
        for(int i=1;i<=N;i++)
        {
            IND[p[i].second]=i;
            POS[i]=p[i].second;
        }
        p[0].first=0;
        for(int i=1;i<=N;i++)  
            update(i,p[i].first-p[i-1].first);
        // since index 'i' in BIT stores a[i]-a[i-1] Cumulative Frequence at pos 'i' gives us A[i]
        StringBuilder answer = new StringBuilder();
        while(Q-->0)
        {
            int type=sc.nextInt(),x=sc.nextInt();
            if(type==1)
            {
                int y=query(IND[x]);
                int z=POS[lowerbound(y+1)-1];
                swap(x,z);
                POS[IND[x]]=x;
                POS[IND[z]]=z;
                update(IND[x],1);
                update(IND[x]+1,-1);
            }
            else if(type==2)
                answer.append(N+1-lowerbound(x)).append("\n");
            else
                update(lowerbound(x),-1);
        }
        System.out.println(answer);
    }	
    static int lowerbound(int val)
    {
        int l=1,r=N;
        if(query(r)<val)
            return N+1;
        while(l<r)
        {
            int mid = (l+r)>>1;
            if(query(mid)>=val)
                r=mid;
            else
                l=mid+1;
        }
        return l;
    }
    static void swap(int a ,int b)
    {
        int t = IND[a];
        IND[a]=IND[b];
        IND[b]=t;
    }
    static int query(int idx)
    {
        int ans=0;
        for(;idx>0;idx-=idx&-idx)
            ans+=BIT[idx];
        return ans;    
    }

    static void update(int idx,int val)
    {
        for(;idx<=N;idx+=idx&-idx)
            BIT[idx]+=val;
    }
}
class pair implements Comparable<pair>
{
    int first,second;
    public pair()
    {
        first=0;
        second=0;
    }

    public pair(int a , int b)
    {
        first=a;
        second=b;
    }

    public int compareTo(pair o)
    {
        return this.first-o.first;
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

       