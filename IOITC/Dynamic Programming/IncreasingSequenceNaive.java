
/**
 * SPOJ INCSEQ
 */
import java.util.*;
import java.io.*;
class IncreasingSequenceNaive
{
    static int a[]=new int[10001];
    static int mod = 5000000;
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        for(int i = 1 ; i<=N ; i++)
        {
            a[i]= sc.nextInt();
        }
        int dp[][]=new int[N+1][K+1];
        // dp[i][j]= Number of Increasing Sequences ending at 'i' of Length 'j'
        for(int i = 1 ; i<=N ; i++)
            dp[i][1]=1; // Number of Increasing Sequences ending at 'i' of Length 1 = 1

        for(int i = 1 ; i<=N ; i++)
        {
            for(int j = 1 ; j<=i-1 ; j++)
            {
                if(a[i]>a[j]) // If Increasing Subsequence
                {
                    for(int k = 2; k<=K ; k++) 
                    {
                        dp[i][k]+=dp[j][k-1];
                        dp[i][k]%=mod;
                    }
                    // Number of Increasing Sequences ending at 'i' of length "k" +=
                    // Number of increasing Sequences ending at 'j' of length k-1
                    // Here j<i so increasing is guaranteed , do it for all k <=K
                }
            }
        }
        long ans=0;
        // Answer =  Number of Increasing Sequences of Length K ending at any position from 1 to N
        for(int i =1;i<=N ; i++)
        {
            ans+=dp[i][K];
            ans%=mod;
        }
        System.out.println(ans);
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

/**
 * // last = last_index of curr subsequence
// length = curr subseq length
long long  solve(last, length)
{
// if length is 1, number is 1
if(length == 1)
return 1;
int sum = 0;
// lets take every previous index possible
for(int i = 0; i < last; i++)
// if this matches the conditions of Incresing subsequence
if(a[i] < a[last] && a[i] != 0)
// add the answer
sum += solve(i, length - 1);
// return
return sum;
}

long long ans = 0;
for(int i = 0; i < N; i++){
ans += solve(i, K);
}
cout << ans << endl;

 */
