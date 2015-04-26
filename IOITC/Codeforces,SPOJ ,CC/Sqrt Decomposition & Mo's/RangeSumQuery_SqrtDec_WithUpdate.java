/*
 * Classic Range Sum problem using Square Root Decomposition
 * Time Complexity O(N root N).
 */
import java.io.*;
import java.util.*;
class RangeSumQuery_SqrtDec_WithUpdate
{
    public static void main(String[]args)
    {
        InputReader1 sc = new InputReader1(System.in);
        int N = sc.nextInt();
        int A[] = new int[N];
        int len = (int)Math.sqrt(N) + 1;
        int B[] = new int [len+1];
        for(int i=0;i<N;i++) A[i]= sc.nextInt();
        for(int i=0;i<N;i++) B[i/len] += A[i];
        int Q = sc.nextInt();
        while(Q-->0)
        {
            int l = sc.nextInt() , r = sc.nextInt();
            int sum = 0;
            /*
             * To add update functionality to the code,
             * Simply do this:
             * say we need to Update A[x] to newVal
             * Find Block Number of x ,c_x = x/len 
             * int T = A[x];
             * A[x] = newVal;
             * B[c_x] += A[x] - T;
             */
            int c_l = l / len, c_r = r / len;
            if (c_l == c_r) // If the block number is same 
                for (int i=l; i<=r; ++i) // Brute Force
                    sum += A[i];
            else 
            {
                // Brute Force from L to Next Block Start (c_l+1)
                for (int i=l, end=(c_l+1)*len-1; i<=end; ++i)
                    sum += A[i];
                // Add Block Sums from Next Block Start to Block Before c_r
                for (int i=c_l+1; i<=c_r-1; ++i)
                    sum += B[i];
                // Brute force from c_r to r.    
                for (int i=c_r*len; i<=r; ++i)
                    sum += A[i];
            }
            System.out.println(sum);
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