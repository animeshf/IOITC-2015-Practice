import java.util.*;
import java.io.*;
class SubarrayRank 
{
    static int BIT[][]=new int[1001][10001];
    static int a[]=new int[10001];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int T = sc.nextInt();
        StringBuilder ans = new StringBuilder();
        while(T-->0)
        {
            reset();
            int N = sc.nextInt();
            for(int i=1;i<=N;i++)
            {
                a[i]=sc.nextInt();
                update(a[i],i,1);
            }
            int Q = sc.nextInt();
            while(Q-->0)
            {
                int type=sc.nextInt();
                if(type==1)
                {
                    int idx = sc.nextInt() , newValue = sc.nextInt();
                    int curValue = a[idx];
                    update(curValue,idx,-1);
                    a[idx]=newValue;
                    update(newValue,idx,1);
                }
                else
                {
                    int qs = sc.nextInt();
                    int qe = sc.nextInt();
                    int K = sc.nextInt();
                    int l = 1 , r = 1000 , mid;
                    while(l<=r)
                    {
                        mid = (l+r)>>1;
                        int temp = query(mid-1,qe)-query(mid-1,qs-1);
                        if(temp+1>K)
                        r=mid-1;
                        else 
                        l=mid+1;
                    }
                    ans.append(r).append("\n");
                }
            }
        }
        System.out.println(ans);
    }

    static int query(int x ,int y)
    {
        int ans=0;
        for(int i=x;i>0;i-=i&-i)
            for(int j=y;j>0;j-=j&-j)
                ans+=BIT[i][j];
        return ans;        
    }

    static void update(int x , int y , int val)
    {
        for(int i = x;i<=1000;i+=i&-i)
            for(int j=y;j<=10000;j+=j&-j)
                BIT[i][j]+=val;
    }

    static void reset()
    {
        for(int i=0;i<=1000;i++)
            for(int j=0;j<=10000;j++)
                BIT[i][j]=0;
        for(int i=0;i<=10000;i++)
            a[i]=0;
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
