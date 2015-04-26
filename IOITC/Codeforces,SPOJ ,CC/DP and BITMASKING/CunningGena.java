/**
 * Cunning Gena
 * DP + Bitmasking
 */
import java.io.*;
import java.util.*;
public class CunningGena
{
    static int N = 105 , MX = 21 ;
    static long MOD = 1000000007 , INF = Long.MAX_VALUE;
    static long mask [] = new long [N];
    static long DP   [] = new long [1<<MX];
    static pair K    [] = new pair [N];
    static long X    [] = new long [N] , M  [] = new long [N];
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int n = sc.nextInt() , m = sc.nextInt() , b = sc.nextInt();
        for(int i=1;i<(1<<m);i++)DP[i]=INF;
        DP[0] = 0;
        for(int i=0;i<n;i++)
        {
            X[i] = sc.nextLong();
            K[i] = new pair(sc.nextLong(),i);
            M[i] = sc.nextLong();
            for(int j=0;j<M[i];j++)
            {
                long u = sc.nextLong();
                u--; mask[i] |= (1<<u); // Creating List of Programs it can solve!
            }
        }
        Arrays.sort(K,0,n); // Sort based on Number of Monitors needed.
        long ans = INF;
        for(int j=0;j<n;j++)
        {
            int cur = K[j].second;
            long price = K[j].first * b;
            for(int i=0;i<(1<<m);i++)
            {
                if(DP[i]!=INF)
                    DP[(int)(i|mask[cur])] = Math.min(DP[(int)(i|mask[cur])],DP[i]+X[cur]);
                    // Choose best between current minimum and choosing the current person to solve
            }
            if(DP[(1<<m)-1]==INF)continue;
            ans = Math.min(ans,DP[(1<<m)-1]+price);
        }
        if(ans==INF)ans=-1;
        System.out.println(ans);
    }
}
class pair implements Comparable<pair>
{
    long first; int second;
    public pair(long a, int b)
    {
        first = a; second = b;
    }

    public int compareTo(pair o)
    {
        if(this.first==o.first)return this.second-o.second;
        return Long.compare(this.first,o.first);
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