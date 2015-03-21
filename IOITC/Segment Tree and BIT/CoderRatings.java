import java.util.*;
import java.io.*;
class CoderRatings
{
    static int segTree[]=new int[500000];
    static pair ratings[]=new pair[300001];
    static int INF=101010101;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        pair ratings[]=new pair[N+1];
        for(int i=0;i<=N;i++)
            ratings[i]=new pair();
        for(int i=1;i<=N;i++)
        {
            int x=sc.nextInt();
            int y=sc.nextInt();
            ratings[i].first=x;
            ratings[i].second=y;
            ratings[i].index=i;
            ratings[i].same=0;
        }
        ratings[0].first=INF;
        ratings[0].second=INF-1;
        int ans[]=new int[N+1];
        Arrays.sort(ratings);
        for(int i=N;i>=1;i--)
        {
            int val=ratings[i].second;
            int temp=query(1,1,100000,1,val);
            temp-=ratings[i].same;
            update(val,1,1,100000);
            ans[ratings[i].index]=temp;
        }
        StringBuilder ansaa = new StringBuilder();
        for(int i=1;i<=N;i++)
            ansaa.append(ans[i]).append("\n");
        System.out.println(ansaa);
    }

    static void update(int val ,int node , int from , int to )
    {
        if(from==to)
        {
            segTree[node]++;
        }
        else
        {
            int mid = (from+to)/2;
            int leftChild = node*2;
            int rightChild=leftChild+1;
            if(val <= mid)
                update(val,leftChild,from,mid);
            else
                update(val,rightChild,mid+1,to);
            segTree[node]=segTree[leftChild]+segTree[rightChild];
        }
    }

    static int query ( int node , int from , int to , int qs , int qe )
    {
        if(qs > to || qe <from)
            return 0;
        if(from>=qs &&  to <=qe)
            return segTree[node];
        int leftChild=node*2;
        int rightChild=leftChild+1;
        int mid = (from+to)/2;
        return query(leftChild , from , mid , qs , qe)+ query(rightChild,mid+1,to,qs,qe);
    }
}
class pair implements Comparable<pair>
{
    int first,second,index,same;
    public pair()
    {
        first=0;
        second=0;
        index=0;
        same=0;
    }

    public pair(int first,int second,int index,int same)
    {
        this.first=first;
        this.second=second;
        this.same=same;
        this.index=index;
    }

    public int compareTo(pair o)
    {
        if(o.first!=this.first)
            return o.first-this.first;
        else if(o.second==this.second)
        {
            o.same=o.same+1;
            return 1;
        }
        else
            return o.second-this.second;
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

