/**
   5 2
   1 1 1 7 9
   4 3 5 6 2
 */
import java.util.*;
import java.io.*;
class MachinesPartial
{
    static int dp[][][]=new int[10001][101][2];
    static int M [] = new int [10001];
    static int P [] = new int [10001];
    static int N,K;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        K--;
        for(int i=0;i<N;i++)
            M[i]=sc.nextInt();
        for(int i=0;i<N;i++)
            P[i]=sc.nextInt();
        for(int i=0;i<=10000;i++)
            {
                for(int j=0;j<=100;j++)
                {
                    dp[i][j][0]=-1; dp[i][j][1]=-1;
                }
            }
        int answer = Math.min(solve(0,K,0),solve(0,K,1));    
        System.out.println(answer);
    }
    static int solve(int curPos , int consec , int idx)
    {
        if(curPos==N) // Reached End
        return 0;
        if(dp[curPos][consec][idx]!=-1)
        return dp[curPos][consec][idx];
        int ans = Integer.MAX_VALUE;
        if(idx==0)//MAC
            {
                ans=Math.min(ans,M[curPos]+solve(curPos+1,K,1));
                if(consec>0)
                ans=Math.min(ans,M[curPos]+solve(curPos+1,consec-1,0));
            }
        else // PC
            {
                ans = Math.min(ans,P[curPos]+solve(curPos+1,K,0));
                if(consec>0)
                ans=Math.min(ans,P[curPos]+solve(curPos+1,consec-1,1));
            }
        dp[curPos][consec][idx] = ans;
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