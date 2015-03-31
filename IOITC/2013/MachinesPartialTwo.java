/**
5 2
1 1 1 7 9
4 3 5 6 2
 */
import java.util.*;
import java.io.*;
class MachinesPartialTwo
{
    static int dp[][][]=new int[10001][101][2],M [] = new int [10001],P [] = new int [10001];
    static int N,K;
    // 0 = Mac, 1 = PC
    //DP[i][j][0]=min cost of operations starting from i with j more consecutive operations allowed when ith choice is MAC.
    //DP[i][j][1]=min cost of operations starting from i with j more consecutive operations allowed when ith choice is PC.
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        N = sc.nextInt();K = sc.nextInt();
        K--;  // For Zero Based
        for(int i=0;i<N;i++) M[i]=sc.nextInt();
        for(int i=0;i<N;i++) P[i]=sc.nextInt();

        for(int i=0;i<=K;i++)
        {
            dp[N][i][0]=0; // Base Cases
            dp[N][i][1]=0;
        }
        for(int i=N-1;i>=0;i--)
        {
            for(int j=0;j<=K;j++)
            {
                dp[i][j][0] = M[i] + dp[i + 1][K][1];// If you use a PC after Mac
                dp[i][j][1] = P[i] + dp[i + 1][K][0];// If you use a Mac after PC
                if(j>0) // If consecutive selection allowed
                {
                    dp[i][j][0] = Math.min(dp[i][j][0], M[i] + dp[i + 1][j - 1][0]); // Using a Mac after Mac
                    dp[i][j][1] = Math.min(dp[i][j][1], P[i] + dp[i + 1][j - 1][1]); // Using a PC after PC
                }
            }
        }
        /* Minimum of starting at 'i' with atmost K consecutive operations when you begin 
        on a Mac or on a PC*/
        System.out.println(Math.min(dp[0][K][0],dp[0][K][1]));
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