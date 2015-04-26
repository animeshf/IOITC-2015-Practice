/**
DP[i][j] = Minimum possible steps to convert the array from i+1 to N if A[i] is set as j. 
j can be 0, 1, 2 or 3(which means original value).
We do not need any other value other than 0, 1, 2. since A[i] can be as large as 10000000.
I just make default as 3. If j == 3, j = A[i] .
We only allow any index to have 4 values.
0, 1, 2, and Orignal Value that it has. Set Original Value  as j.
if(j == 3) j = A[i];
Now iterate for every possible value we can change i + 1 to and proceed.
**/
import java.io.*;
import java.util.*;
class Unequal
{
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt() , K = sc.nextInt(), INF = 99999999;
        int A[]=new int[N+1];
        A[N] = 0;
        for(int i=0;i<N;i++)
        A[i]=sc.nextInt();
        int next[][]=new int[N+1][4];
        int dp[][]=new int[N+1][4];
        for(int i=0;i<=N;i++)
            for(int j=0;j<4;j++)
                dp[i][j] = INF;
        for(int i=0;i<4;i++)dp[N][i]=0;
        for(int i=N-1;i>=0;i--)
        {
            for(int m=0;m<Math.min(4,K);m++)
            {
                int j=m;
                if(j==3)j=A[i];
                for(int k = 0;k<Math.min(4,K);k++)
                {
                    int l=k;
                    if(l==3) l = A[i+1];
                    if(j==l)continue; // We wouldn't want to make A[i] == A[i+1] so continue .
                    int curr_ans = ((i == (N - 1) || A[i + 1] == l) ? 0 : 1) + dp[i + 1][k];
                    if(curr_ans < dp[i][m]) // Minimum
                    {
                        dp[i][m] = curr_ans; 
                        next[i][m] = l;  // To BackTrack Later On.
                    }
                }
            }
        }
        int ans = INF;
        int start = -1;
        for(int i = 0; i < Math.min(4, K); i++)
        {
            int j = i;
            if(j == 3) j = A[0];
            int curr_ans = ((A[0] == j) ? 0 : 1) + dp[0][i];  
            if(curr_ans < ans)
            {
                ans = curr_ans;
                start = j;
            }
        }
        StringBuilder op = new StringBuilder();
        op.append(ans).append("\n");
        for(int i = 0; i < N; i++)
        {
            op.append(start).append(" ");
            start = next[i][Math.min(start, 3)];
        }
        System.out.print(op);
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
