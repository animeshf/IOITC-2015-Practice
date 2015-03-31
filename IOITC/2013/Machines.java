import java.util.*;
import java.io.*;
class Machines
{
    static long dp_pc[]=new long[300001],dp_mac[]=new long[300001];
    static int M [] = new int [300001],P [] = new int [300001];
    static int N,K;
    static long segtree_pc[] = new long[1200004],segtree_mac[]=new long[1200004];
    static long INF=Long.MAX_VALUE;
    public static void main(String[]args)
    {
        /** 
        dp_mac[i] = Best you can do considering i'th choice is mac
        dp_pc[i] = Best you can do considering i'th choice is pc
        sum_mac[j] - sum_mac[i] = Total cost of using (j-i+1) consecutive Macs from i-->j.
        sum_pc[j] - sum_pc[i] = Total cost of using (j-i+1)consecutive PCs from i-->j

        FOR dp_mac[i]:
        For all 'i' , I need the segtree to return X= minimum(sum_mac[j] + dp_pc[j+1]) for all j>i and j<=Min(i+K,N-1).
        Therefore X-sum_mac[i] = Best we can do by taking the 'i+1'th computer as MAC
        and we know that dp_pc[i+1] = Best we can do by taking "i+1'th computer as PC
        Then dp_mac[i]=M[i]+Min(X-sum_mac[i],dp_pc[i+1]) 
        Similarly for dp_pc[i].

        Therefore:
        dp_mac[i]=M[i]+Math.min(query(1,1,0,N-1,i+1,limit)-sum_mac[i],dp_pc[i+1]);
        The Segment Tree query does not consider the case where we immediately switch to a PC from a Mac or Vice Versa
        Hence , dp_mac[i] = Min(Best we can do by taking next computer to be Mac , Best we can do by taking next computer to be PC)
        Similarly , dp_pc[i] = P[i]+Math.min(query(2,1,0,N-1,i+1,limit)-sum_pc[i],dp_mac[i+1])
        Min(Best we can do by taking next as PC , Best we can do by Taking next as MAC)
         **/
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        K = sc.nextInt()-1;
        long sum_mac[]=new long[N]; // Cumulative Frequency
        long sum_pc[]=new long[N]; // Cumulative Frequency
        for(int i=0;i<N;i++) 
        {
            M[i]=sc.nextInt();
            if(i==0)
                sum_mac[i]=M[i];
            else
                sum_mac[i]=M[i]+sum_mac[i-1];
        }
        for(int i=0;i<N;i++)
        {
            P[i]=sc.nextInt();
            if(i==0)
                sum_pc[i]=P[i];
            else
                sum_pc[i]=P[i]+sum_pc[i-1];
        }
        dp_mac[N-1]=M[N-1]; dp_pc[N-1]=P[N-1]; // Base Cases
        Arrays.fill(segtree_mac,INF);
        Arrays.fill(segtree_pc,INF);
        // Type 1 = segtree_mac , Type 2 - segtree_pc
        update(1,1,0,N-1,N-1,sum_mac[N-1]); // Base Cases
        update(2,1,0,N-1,N-1,sum_pc[N-1]); // Base Cases
        for(int i=N-2;i>=0;i--)
        {
            int limit = Math.min(N-1,i+K);
            dp_mac[i]=M[i]+Math.min(query(1,1,0,N-1,i+1,limit)-sum_mac[i],dp_pc[i+1]);//Either the next is pc or mac
            dp_pc[i]= P[i]+Math.min(query(2,1,0,N-1,i+1,limit)-sum_pc[i],dp_mac[i+1]);//The next is mac or pc 
            update(1,1,0,N-1,i,sum_mac[i]+dp_pc[i+1]);
            update(2,1,0,N-1,i,sum_pc[i]+dp_mac[i+1]);
        }
        System.out.println(Math.min(dp_pc[0],dp_mac[0]));
    }

    static void update(int type , int node , int l , int r , int pos , long val)
    {
        if(type==1)
        {
            if(l==r)
            {
                segtree_mac[node]=val;
                return;
            }
            int lc = node*2; int rc=lc+1; int mid = (l+r)/2;
            if(mid>=pos)
                update(type,lc,l,mid,pos,val);
            else
                update(type,rc,mid+1,r,pos,val);
            segtree_mac[node]=Math.min(segtree_mac[lc],segtree_mac[rc]);    
        }
        else
        {
            if(l==r)
            {
                segtree_pc[node]=val;
                return;
            }
            int lc = node*2; int rc=lc+1; int mid = (l+r)/2;
            if(mid>=pos)
                update(type,lc,l,mid,pos,val);
            else
                update(type,rc,mid+1,r,pos,val);
            segtree_pc[node]=Math.min(segtree_pc[lc],segtree_pc[rc]);   
        }
    }

    static long query(int type,int node , int l , int r , int qs , int qe)
    {
        if(type==1)
        {
            if(l>qe || r<qs)
                return INF;
            if(l>=qs && r<=qe)
                return segtree_mac[node];
            int mid = (l+r)/2; int lc = node*2; int rc = lc+1;
            return Math.min(query(type,lc,l,mid,qs,qe),query(type,rc,mid+1,r,qs,qe));
        }
        else
        {
            if(l>qe || r<qs)
                return INF;
            if(l>=qs && r<=qe)
                return segtree_pc[node];
            int mid = (l+r)/2; int lc = node*2; int rc = lc+1;
            return Math.min(query(type,lc,l,mid,qs,qe),query(type,rc,mid+1,r,qs,qe));
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