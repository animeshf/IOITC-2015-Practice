
/**
 * Find Number of Increasing Sequences of Length K in an Array of Length N
 */
import java.util.*;
import java.io.*;
class Increasing_Sequences_Of_Length_K
{
    static int BIT[]=new int[100020];
    static int mod = 5000000;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int a[]=new int[N+1];
        int dp[][]=new int[N+2][K+1];
        for(int i=1;i<=N;i++)
        {
            a[i]=sc.nextInt()+1;
            dp[i][1]=1;
        }
        for(int i=2;i<=K;i++)
        {
            Arrays.fill(BIT,0);
            for(int j=2;j<=N;j++)
            {  
                update(a[j-1],dp[j-1][i-1]);
                dp[j][i]+=query(a[j]-1);
                if(dp[j][i]>=mod)
                    dp[j][i]-=mod;
            }
        }
        int ans=0;
        for(int i=1;i<=N;i++)
        {
            ans+=dp[i][K];
            if(ans>=mod)
                ans-=mod;
        }
        System.out.println(ans);
    }

    static void update(int idx , int val)
    {
        for(;idx<=100000;idx+=idx&-idx)
            {
            	BIT[idx]+=val;
            	if(BIT[idx]>=mod)
            	BIT[idx]-=mod;
            }
    }

    static int query(int idx)
    {
        int ans=0;
        for(;idx>0;idx-=idx&-idx)
        {
            ans+=BIT[idx];
            if(ans>=mod)
                ans-=mod;
        }
        return ans;    
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